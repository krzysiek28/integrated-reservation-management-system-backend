package com.uliasz.irms.admin.rest.services;

import com.uliasz.irms.admin.rest.objects.ReservationSearchRequest;
import com.uliasz.irms.internal.common.enums.ReservationStatus;
import com.uliasz.irms.internal.common.models.ReservationModel;
import com.uliasz.irms.internal.common.providers.ReservationProvider;
import com.uliasz.irms.internal.common.services.ReservationDataAccessService;
import com.uliasz.irms.internal.common.utils.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
        reservation.setStatus(ReservationStatus.AVAILABLE);
        return reservationDataAccessService.createReservation(reservation);
    }

    /**
     * Add collection of reservations
     * @param reservations - collection of ReservationModel
     * @return added collection of reservations on success
     */
    public List<ReservationModel> addReservations(List<ReservationModel> reservations) {
        reservations.forEach(reservationModel -> reservationModel.setStatus(ReservationStatus.AVAILABLE));
        return reservationDataAccessService.createReservations(reservations);
    }

    public List<ReservationModel> searchReservations(ReservationSearchRequest searchRequest) {
        List<ReservationModel> reservations = reservationProvider.getReservationsByDateRange(searchRequest.getDateFrom(), searchRequest.getDateTo());
        if(searchRequest.getReservationStatus().isPresent()) {
            reservations = reservations.stream().filter(reservationModel -> searchRequest.getReservationStatus().get().equals(reservationModel.getStatus())).collect(Collectors.toList());
        }
        if(searchRequest.getTimeFrom() != null) {
            reservations = reservations.stream().filter(reservationModel -> DateUtil.isTimeAfter(reservationModel.getTimeFrom(), searchRequest.getTimeFrom())).collect(Collectors.toList());
        }
        if(searchRequest.getTimeTo() != null) {
            reservations = reservations.stream().filter(reservationModel -> DateUtil.isTimeBefore(reservationModel.getTimeTo(), searchRequest.getTimeTo())).collect(Collectors.toList());
        }
        return reservations.stream()
                .sorted(Comparator.comparing(ReservationModel::getTimeFrom))
                .sorted(Comparator.comparing(ReservationModel::getDate))
                .collect(Collectors.toList());
    }

    public void deleteReservation(Long id) {
        reservationDataAccessService.deleteReservation(id);
    }

    public List<ReservationModel> getClosedReservationsFomLastMonth() {
        return reservationProvider.getReservationsForLastMonthWithClosedStatus();
    }

    public ReservationModel changeReservationStatus(Long reservationId, String status) {
        return reservationDataAccessService.updateReservationStatus(reservationId, ReservationStatus.getByValue(status));
    }
}
