package com.uliasz.irms.admin.rest.services;

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

    /**
     * Add single reservation
     * @param reservation - represented by ReservationModel
     * @return added reservation model on success
     */
    public ReservationModel addReservation(ReservationModel reservation) {
        return reservationDataAccessService.createReservation(reservation);
    }

    /**
     * Add collection of reservations
     * @param reservations - collection of ReservationModel
     * @return added collection of reservations on success
     */
    public List<ReservationModel> addReservations(List<ReservationModel> reservations) {
        return reservationDataAccessService.createReservations(reservations);
    }
}
