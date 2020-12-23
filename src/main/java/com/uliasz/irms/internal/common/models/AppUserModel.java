package com.uliasz.irms.internal.common.models;

import com.uliasz.irms.internal.common.enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserModel {
    private Long id;
    private String login;
    private PersonalDataModel personalData;
    private UserRoles role;
    private Boolean enabled;
    private String email;
}
