package com.uliasz.irms.backend.rest.objects.request;

import com.sun.istack.NotNull;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
public class RegisterRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String userName;

    @Email
    @Size(max = 50)
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
