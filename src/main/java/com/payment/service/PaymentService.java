package com.payment.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public String validatePayment(String paymentStatus) {
        if ("valid".equalsIgnoreCase(paymentStatus)) {
            return "Payment validated successfully.";
        } else {
            return "Payment validation failed!";
        }
    }

    public String processPayment() {
        return "Payment processed successfully.";
    }

    public String sendConfirmation() {
        return "Payment confirmation sent.";
    }
}