package com.uliasz.irms.internal.common.exceptions;

public class PersonalDataNotFoundException extends RuntimeException {

    public PersonalDataNotFoundException(Long id) {
        super("Could not find personal data, but it was expected: " + id);
    }
}
