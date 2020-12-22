package com.uliasz.irms.internal.common.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonalDataModel {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;

}
