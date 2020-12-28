package com.uliasz.irms.backend.rest.services;

import com.uliasz.irms.backend.rest.objects.request.ReserveRequest;
import com.uliasz.irms.internal.common.enums.ReservationStatus;
import com.uliasz.irms.internal.common.models.ReservationModel;
import com.uliasz.irms.internal.common.providers.ReservationProvider;
import com.uliasz.irms.internal.common.services.ReservationDataAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationDataAccessService reservationDataAccessService;
    private final ReservationProvider reservationProvider;

    public List<ReservationModel> getAvailableReservationsByDateRange(Date startDate, Date endDate) {
        return reservationProvider.getAvailableReservationsByDateRange(startDate, endDate);
    }

    public ReservationModel reserve(Long reservationId, ReserveRequest reserveRequest) {
        return reservationDataAccessService.updateReservationByAdditionalInformation(reservationId, reserveRequest, ReservationStatus.RESERVED);
    }

    public ReservationModel updateReservationStatus(Long reservationId, ReservationStatus status) {
        return reservationDataAccessService.updateReservationStatus(reservationId, status);
    }

    public ReservationModel getReservationById(Long id) {
        return reservationProvider.getReservationById(id);
    }

    public void deleteReservation(Long id) {
        reservationDataAccessService.deleteReservation(id);
    }
}
