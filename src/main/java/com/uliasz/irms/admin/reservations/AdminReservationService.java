package com.uliasz.irms.admin.reservations;

import com.uliasz.irms.internal.common.models.ReservationModel;
import com.uliasz.irms.internal.common.providers.ReservationProvider;
import com.uliasz.irms.internal.common.services.ReservationDataAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminReservationService {

    private final ReservationDataAccessService reservationDataAccessService;
    private final ReservationProvider reservationProvider;

    public List<ReservationModel> getReservationsByDate(Date date) {
        return reservationProvider.getReservationsByDate(date);
    }

    public List<ReservationModel> addReservations(List<ReservationModel> reservations) {
        return reservationDataAccessService.createReservations(reservations);
    }

    public void deleteReservation(Long id) {
        reservationDataAccessService.deleteReservation(id);
    }
}
