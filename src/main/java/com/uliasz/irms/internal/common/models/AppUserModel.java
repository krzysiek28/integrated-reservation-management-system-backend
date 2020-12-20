package com.uliasz.irms.internal.common.models;

import com.uliasz.irms.internal.common.enums.UserRoles;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppUserModel {
    private Long id;
    private String login;
    private String password;
    private PersonalDataModel personalData;
    private UserRoles role;
    private Boolean enabled;
    private String email;
}
