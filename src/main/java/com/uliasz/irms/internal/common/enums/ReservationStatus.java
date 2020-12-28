package com.uliasz.irms.internal.common.enums;

public enum ReservationStatus {
    RESERVED("RESERVED"), AVAILABLE("AVAILABLE"), CANCELED("CANCELED"), CLOSED("CLOSED");

    private String value;

    ReservationStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static ReservationStatus getByValue(String value) {
        for (ReservationStatus role : ReservationStatus.values()) {
            if (role.getValue().equals(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException();
    }
}
