package com.payment.controller;

import com.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public String payment(){
        return "Process Payment Application Start...";
    }

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(@RequestParam String paymentStatus) {
        if (paymentStatus == null || paymentStatus.isEmpty()) {
            return ResponseEntity.badRequest().body("Payment status is required!");
        }
        String validationMessage = paymentService.validatePayment(paymentStatus);
        if (validationMessage.contains("failed")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationMessage);
        }
        String processMessage = paymentService.processPayment();
        String confirmationMessage = paymentService.sendConfirmation();
        return ResponseEntity.ok(validationMessage + " " + processMessage + " " + confirmationMessage);
    }
}