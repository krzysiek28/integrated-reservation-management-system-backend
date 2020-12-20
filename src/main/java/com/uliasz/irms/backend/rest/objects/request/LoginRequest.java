package com.uliasz.irms.backend.rest.objects.request;

import com.sun.istack.NotNull;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class LoginRequest {
    @NotBlank
    private String userName;
    @NotBlank
    private String password;
}
