package com.uliasz.irms.admin.rest.objects;

import com.uliasz.irms.internal.common.enums.ReservationStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Optional;

@Data
public class ReservationSearchRequest {
    @NotBlank
    private Date dateFrom;
    @NotBlank
    private Date dateTo;
    private String timeFrom;
    private String timeTo;
    private Optional<ReservationStatus> reservationStatus;
}
