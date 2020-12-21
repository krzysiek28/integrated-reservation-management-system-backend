package com.uliasz.irms.internal.common.enums;

public enum UserRoles {
    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private String value;

    UserRoles(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
