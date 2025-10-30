package me.wabuein.calc;

import lombok.Getter;
import me.wabuein.calc.listener.OperationListener;
import me.wabuein.calc.mongo.MongoHandler;
import me.wabuein.calc.operation.OperationHandler;
import me.wabuein.calc.util.Configuration;

@Getter
public class Calculator {

    private final Configuration configuration;
    private final MongoHandler mongoHandler;
    private final OperationListener operationListener;
    private final OperationHandler operationHandler;

    /**
     * Initializes main class, gets rid of useless static calls
     */

    public Calculator() {
        this.configuration = new Configuration();
        this.mongoHandler = new MongoHandler(this);
        this.operationHandler = new OperationHandler(this);
        this.operationListener = new OperationListener(this);
    }
    /**
     * Runs the application
     *
     * @param args arguments to run to
     */

    public static void main(String[] args) {
        new Calculator();
    }

}
