package com.uliasz.irms.internal.common.exceptions;

public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(Long id) {
        super("Could not find reservation " + id);
    }
}
