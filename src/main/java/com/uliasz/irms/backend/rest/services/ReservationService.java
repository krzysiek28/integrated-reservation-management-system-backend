package com.uliasz.irms.backend.rest.services;

import com.uliasz.irms.backend.rest.objects.request.ReserveRequest;
import com.uliasz.irms.internal.common.enums.ReservationStatus;
import com.uliasz.irms.internal.common.models.ReservationModel;
import com.uliasz.irms.internal.common.providers.ReservationProvider;
import com.uliasz.irms.internal.common.services.ReservationDataAccessService;
import com.uliasz.irms.internal.emailService.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationDataAccessService reservationDataAccessService;
    private final ReservationProvider reservationProvider;
    private final EmailService emailService;

    public List<ReservationModel> getAvailableReservationsByDateRange(Date startDate, Date endDate) {
        return reservationProvider.getAvailableReservationsByDateRange(startDate, endDate);
    }

    public ReservationModel reserve(Long reservationId, ReserveRequest reserveRequest) {
        if(!StringUtils.isEmpty(reserveRequest.getPersonalDataModel().getContactEmail())) {
            emailService.sendReservationMail(reserveRequest.getPersonalDataModel().getContactEmail(), reservationProvider.getReservationById(reservationId), reserveRequest);
        }
        return reservationDataAccessService.updateReservationByAdditionalInformation(reservationId, reserveRequest, ReservationStatus.RESERVED);
    }

    public ReservationModel changeReservationStatusAndRemoveAdditionalData(Long reservationId, String status) {
        return reservationDataAccessService.updateReservationStatusAndRemoveAdditionalInfo(reservationId, ReservationStatus.getByValue(status));
    }

    public ReservationModel getReservationById(Long id) {
        return reservationProvider.getReservationById(id);
    }

    public List<ReservationModel> getOwnReservations(Long userId) {
        return reservationDataAccessService.getOwnReservations(userId);
    }
}
