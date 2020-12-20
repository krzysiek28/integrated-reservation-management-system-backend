package com.uliasz.irms.internal.common.enums;

public enum UserRoles {
    USER("USER_ROLE"), ADMIN("ADMIN_ROLE");

    private String value;

    UserRoles(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
