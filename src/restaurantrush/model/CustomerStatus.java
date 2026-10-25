package restaurantrush.model;

public enum CustomerStatus {
    WAITING("waiting for a table"),
    SEATED("seated"),
    ORDERED("waiting for food"),
    EATING("eating"),
    READY_TO_PAY("ready to pay"),
    PAID("paid"),
    LEFT("left");

    private final String description;

    CustomerStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public boolean isWaitingForService() {
        return this == WAITING || this == SEATED || this == ORDERED;
    }

    public boolean occupiesTable() {
        return this == SEATED || this == ORDERED || this == EATING || this == READY_TO_PAY;
    }

    public boolean isFinished() {
        return this == PAID || this == LEFT;
    }
}
