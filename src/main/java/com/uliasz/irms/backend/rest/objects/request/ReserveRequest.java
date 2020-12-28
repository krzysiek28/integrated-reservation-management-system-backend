package com.uliasz.irms.backend.rest.objects.request;

import com.uliasz.irms.internal.common.models.PersonalDataModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReserveRequest {
    private Long userId;
    private String comment;
    @NotBlank
    private PersonalDataModel personalDataModel;
}
