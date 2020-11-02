package com.uliasz.irms.internal.common.models;

import com.uliasz.irms.internal.common.enums.ReservationStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReservationModel {
    private Long id;
    private Date date;
    private Date timeFrom;
    private Date timeTo;
    private ReservationStatus status;
    private String comment;
    private AppUserModel user;
    private VisitDetailsModel visitDetails;
    private PersonalDataModel personalData;
}
