package com.uliasz.irms.backend.reservations;

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

    public List<ReservationModel> getAvailableReservationsByDate(Date date) {
        return reservationProvider.getAvailableReservationsByDate(date);
    }

    public ReservationModel updateReservationStatus(Long reservationId, ReservationStatus status) {
        return reservationDataAccessService.updateReservationStatus(reservationId, status);
    }
}
