package com.payment.service;

import io.camunda.zeebe.client.ZeebeClient;
import org.springframework.stereotype.Service;

@Service
public class ZeebeClientService {

    private final ZeebeClient zeebeClient;

    public ZeebeClientService() {
        // Create Zeebe Client
        zeebeClient = ZeebeClient.newClientBuilder()
                .gatewayAddress("localhost:26500") // The Zeebe broker is running locally
                .usePlaintext() // Use plaintext for local development (use TLS for production)
                .build();

        System.out.println("Connected to Zeebe gateway on localhost:26500");
    }

    // Method to deploy a BPMN workflow
    public void deployWorkflow(String bpmnFilePath) {
        zeebeClient.newDeployCommand()
                .addResourceFromClasspath(bpmnFilePath)
                .send()
                .join(); // Wait for the deployment to complete

        System.out.println("Workflow deployed: " + bpmnFilePath);
    }

    // Method to start a workflow instance
    public void startWorkflowInstance(String processId) {
        zeebeClient.newCreateInstanceCommand()
                .bpmnProcessId(processId) // Specify the BPMN process ID
                .latestVersion() // Start the latest version of the process
                .send()
                .join(); // Wait for the workflow instance to start

        System.out.println("Workflow instance started for: " + processId);
    }

    // Method to close the Zeebe client
    public void closeClient() {
        zeebeClient.close(); // Close the client when it's no longer needed
        System.out.println("Zeebe client closed");
    }
}
