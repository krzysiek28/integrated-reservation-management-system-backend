package com.uliasz.irms.internal.common.providers;

import com.uliasz.irms.internal.common.converters.ReservationConverter;
import com.uliasz.irms.internal.common.enums.ReservationStatus;
import com.uliasz.irms.internal.common.models.ReservationModel;
import com.uliasz.irms.internal.database.entities.ReservationEntity;
import com.uliasz.irms.internal.database.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityNotFoundException;
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
                .orElseThrow(() -> new EntityNotFoundException(String.format("Reservation not found, id %d", id)));
    }

    public List<ReservationModel> getReservationsByDate(Date date) {
        List<ReservationEntity> reservations = reservationRepository.findByDate(date);
        return reservations.stream()
                .map(ReservationConverter::convertToModel)
                .collect(Collectors.toList());
    }

    public List<ReservationModel> getAvailableReservationsByDate(Date date) {
        List<ReservationEntity> reservations = reservationRepository.findByDateAndStatus(date, ReservationStatus.AVAILABLE.name());
        return reservations.stream()
                .map(ReservationConverter::convertToModel)
                .collect(Collectors.toList());
    }

//    public List<ReservationModel> getAvailableReservationsInMonth(Month month) {
//        List<ReservationEntity> reservations = reservationRepository.findByDate(date);
//        return reservations.stream()
//                .map(ReservationConverter::convertToReservationModel)
//                .collect(Collectors.toList());
//    }
}
