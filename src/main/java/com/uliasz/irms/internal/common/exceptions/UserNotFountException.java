package com.uliasz.irms.internal.common.exceptions;

public class UserNotFountException extends RuntimeException {

    public UserNotFountException(Long id) {
        super("Could not find user " + id);
    }
}
