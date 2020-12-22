package com.uliasz.irms.internal.common.providers;

import com.uliasz.irms.internal.common.converters.ReservationConverter;
import com.uliasz.irms.internal.common.enums.ReservationStatus;
import com.uliasz.irms.internal.common.exceptions.ReservationNotFoundException;
import com.uliasz.irms.internal.common.models.ReservationModel;
import com.uliasz.irms.internal.common.utils.DateUtil;
import com.uliasz.irms.internal.database.entities.ReservationEntity;
import com.uliasz.irms.internal.database.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReservationProvider {
    private final ReservationRepository reservationRepository;

    public ReservationModel getReservationById(Long id) {
        Optional<ReservationEntity> reservation = reservationRepository.findById(id);
        return reservation.map(ReservationConverter::convertToModel)
                .orElseThrow(() -> new ReservationNotFoundException(id));
    }

    public List<ReservationModel> getReservationsByDate(Date date) {
        List<ReservationEntity> reservations = reservationRepository.findByDate(date);
        return reservations.stream()
                .map(ReservationConverter::convertToModel)
                .collect(Collectors.toList());
    }

    public List<ReservationModel> getReservationsByDateRange(Date startDate, Date endDate) {
        List<ReservationEntity> reservations = reservationRepository.findAll();
        return reservations.stream()
                .filter(res -> DateUtil.isDateBetween(res.getDate(), startDate, endDate))
                .map(ReservationConverter::convertToModel)
                .collect(Collectors.toList());
    }

    public List<ReservationModel> getAvailableReservationsByDateRange(Date startDate, Date endDate) {
        List<ReservationEntity> reservations = reservationRepository.findByStatus(ReservationStatus.AVAILABLE.name());
        return reservations.stream()
                .filter(res -> DateUtil.isDateBetween(res.getDate(), startDate, endDate))
                .map(ReservationConverter::convertToModel)
                .collect(Collectors.toList());
    }

    public List<ReservationModel> getReservationsByClosedStatus() {
        return reservationRepository.findByStatus(ReservationStatus.CLOSED.name())
                .stream()
                .map(ReservationConverter::convertToModel)
                .sorted(Comparator.comparing(ReservationModel::getDate).reversed())
                .collect(Collectors.toList());
    }

    public List<ReservationModel> getAvailableReservationsByDate(Date date) {
        List<ReservationEntity> reservations = reservationRepository.findByDateAndStatus(date, ReservationStatus.AVAILABLE.name());
        return reservations.stream()
                .map(ReservationConverter::convertToModel)
                .collect(Collectors.toList());
    }

    public boolean reservationIsAvailable(Long id) {
        return ReservationStatus.AVAILABLE.equals(getReservationById(id).getStatus());
    }

//    public List<ReservationModel> getAvailableReservationsInMonth(Month month) {
//        List<ReservationEntity> reservations = reservationRepository.findByDate(date);
//        return reservations.stream()
//                .map(ReservationConverter::convertToReservationModel)
//                .collect(Collectors.toList());
//    }
}
