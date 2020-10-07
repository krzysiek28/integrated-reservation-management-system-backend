package com.uliasz.irms.internal.common.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonalDataModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
}
