package model;

public class Operation {
    private String operationType;
    private int value;

    public Operation(String operationType, int value) {
        this.operationType = operationType;
        this.value = value;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
