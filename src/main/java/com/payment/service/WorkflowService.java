package com.payment.service;

import org.springframework.stereotype.Service;

@Service
public class WorkflowService {

    private final ZeebeClientService zeebeClientService;

    public WorkflowService(ZeebeClientService zeebeClientService) {
        this.zeebeClientService = zeebeClientService;
    }

    public void deployAndStartWorkflow() {
        String bpmnFilePath = "path/to/your/process.bpmn"; // Specify the BPMN file path
        String processId = "process_payment"; // BPMN process ID

        // Deploy the workflow
        zeebeClientService.deployWorkflow(bpmnFilePath);

        // Start a workflow instance
        zeebeClientService.startWorkflowInstance(processId);
    }
}