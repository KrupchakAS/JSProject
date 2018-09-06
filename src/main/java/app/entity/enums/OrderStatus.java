package app.entity.enums;

public enum OrderStatus {
    AWAITING_PAYMENT((byte) 1),
    AWAITING_SHIPMENT((byte) 2),
    SHIPPED((byte) 3),
    DELIVERED((byte) 4);

    private final Byte value;

    OrderStatus(final Byte value) {
        this.value = value;
    }

    public Byte getValue() {
        return value;
    }

    public static OrderStatus valueOf(final Byte value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        return null;
    }
}
