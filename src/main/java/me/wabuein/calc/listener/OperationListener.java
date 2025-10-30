package me.wabuein.calc.listener;

import me.wabuein.calc.Calculator;
import me.wabuein.calc.operation.Operation;
import me.wabuein.calc.operation.OperationStage;
import me.wabuein.calc.operation.OperationType;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OperationListener {

    private final ExecutorService listenerService;
    private final Calculator calculator;


    private Operation operation;

    /**
     * Console listener, listens to lines =
     */

    public OperationListener(Calculator calculator) {
        this.calculator = calculator;

        this.listenerService = Executors.newSingleThreadExecutor();
        this.start();
    }

    /**
     * Starts the operation listener
     */

    private void start() {
        listenerService.submit(this::readLoop);
    }

    /**
     * Stops the operation listener
     */

    public void stop() {
        listenerService.shutdownNow();
    }

    /**
     * Reads the loop of the operation listener
     */

    private void readLoop() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Console listener started. Type 'quit' to exit.");
        while (true) {
            if (!scanner.hasNextLine()) break;
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            if ("quit".equalsIgnoreCase(line)) {
                System.out.println("Shutting down console listener.");
                break;
            }
            handleInput(line);
        }
        scanner.close();
    }

    /**
     * Handles the operation listener
     *
     * @param line line to read
     */

    private void handleInput(String line) {
        if (line.equalsIgnoreCase("start")) {
            if (operation != null) {
                System.out.println("Operation has already started please " + operation.getOperationStage().getTitle());
                return;
            }

            operation = new Operation();
            operation.setOperationStage(OperationStage.SETTING_A);
            System.out.println("Please enter your first number");
            return;
        }

        if (!operation.getOperationStage().equals(OperationStage.SETTING_OPERATION_TYPE)) {
            try {
                long i = Long.parseLong(line);

                if (operation.getOperationStage().equals(OperationStage.SETTING_A)) {
                    operation.setA(i);
                    operation.setOperationStage(OperationStage.SETTING_B);

                    System.out.println("Please enter your second number");
                    return;
                }

                if (operation.getOperationStage().equals(OperationStage.SETTING_B)) {
                    operation.setB(i);
                    operation.setOperationStage(OperationStage.SETTING_OPERATION_TYPE);

                    System.out.println("Please choose an operation\nA: Add\nB: Subtract\nC: Multiplication\nD: Division");
                    return;
                }

                System.out.println("Please choose an operation\nA: Add\nB: Subtract\nC: Multiplication\nD: Division");
            } catch (NumberFormatException e) {
                System.out.println("Please " + operation.getOperationStage().getTitle());
            }
            return;
        }

        if (line.equalsIgnoreCase("A"))
            operation.setOperationType(OperationType.ADD);
        else if (line.equalsIgnoreCase("B"))
            operation.setOperationType(OperationType.SUBTRACT);
        else if (line.equalsIgnoreCase("C"))
            operation.setOperationType(OperationType.MULTIPLY);
        else if (line.equalsIgnoreCase("D"))
            operation.setOperationType(OperationType.DIVIDE);
        else {
            System.out.println("Please choose an operation\nA: Add\nB: Subtract\nC: Multiplication\nD: Division");
            return;
        }

        System.out.println("Your answer is " + operation.getResult() + "\nWrite 'start' to run a new operation");
        calculator.getOperationHandler().saveOperation(operation);

        operation = null;
    }

}
