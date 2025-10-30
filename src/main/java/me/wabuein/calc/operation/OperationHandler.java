package me.wabuein.calc.operation;

import lombok.Getter;
import me.wabuein.calc.Calculator;
import org.bson.Document;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OperationHandler {

    @Getter
    private final List<Operation> operations;
    private final Calculator calculator;

    private final ExecutorService asyncExecutorService;

    /**
     * Initializes the operation handler
     *
     * @param calculator instance of main class
     */

    public OperationHandler(Calculator calculator) {
        this.calculator = calculator;
        this.operations = new LinkedList<>();

        this.asyncExecutorService = Executors.newSingleThreadExecutor();
        //this.loadOperations();
    }

    /**
     * Loads all operations
     */

    /*
    private void loadOperations() {
        asyncExecutorService.submit(() -> {
            for (Document document : calculator.getMongoHandler().getOperations().find())
                operations.add(new Operation(document));
        });
    }

     */

    /**
     * Adds an operation to the list
     *
     * @param operation operation to save
     */

    public final void saveOperation(Operation operation) {
        operations.add(operation);
    }

    /**
     * Creates operation
     *
     * @param a first number
     * @param b second number
     * @param operationType type of operation
     * @return {@link Operation}
     */

    public final Operation createOperation(long a, long b, OperationType operationType) {
        final Operation operation = new Operation();

        operation.setA(a);
        operation.setB(b);
        operation.setOperationType(operationType);

        this.saveOperation(operation);

        return operation;
    }

}
