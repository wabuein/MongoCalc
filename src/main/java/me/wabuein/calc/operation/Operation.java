package me.wabuein.calc.operation;

import lombok.Getter;
import lombok.Setter;
import org.bson.Document;

@Getter
@Setter
public class Operation {

    private final boolean loaded;

    private long a, b;

    private OperationType operationType;
    private OperationStage operationStage;

    /**
     * Initializes new operation instance
     */

    public Operation() {
        this.loaded = false;
    }

    /**
     * Loads a previous operation saved on a document BSON
     *
     * @param document document to make operation from
     */

    public Operation(Document document) {
        this.a = document.getLong("a");
        this.b = document.getLong("b");
        this.operationType = OperationType.valueOf(document.getString("type"));

        this.loaded = true;
    }

    /**
     * Gets the result of the operation
     *
     * @return {@link Float}
     */

    public final long getResult() {

        switch (operationType) {
            case ADD -> {
                return (a+b);
            }

            case SUBTRACT -> {
                return (a-b);
            }

            case MULTIPLY -> {
                return (a * b);
            }

            case DIVIDE -> {
                return (a / b);
            }

            default -> {
                break;
            }
        }

        return 0;
    }

    /**
     * Makes a BSON document
     *
     * @return {@link Document}
     */

    public final Document toBson() {
        return new Document()
                .append("a", a)
                .append("b", b)
                .append("type", operationType.name());
    }


}
