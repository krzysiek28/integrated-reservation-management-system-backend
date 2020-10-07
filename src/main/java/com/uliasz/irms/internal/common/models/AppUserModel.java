package com.uliasz.irms.internal.common.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppUserModel {
    private Long id;
    private String login;
    private String password;
    private PersonalDataModel personalData;
    private String role;
    private Boolean enabled;
}
