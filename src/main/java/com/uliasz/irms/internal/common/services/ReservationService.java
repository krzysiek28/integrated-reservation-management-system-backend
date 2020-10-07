package com.uliasz.irms.internal.common.services;

import com.uliasz.irms.internal.common.converters.ReservationConverter;
import com.uliasz.irms.internal.common.models.ReservationModel;
import com.uliasz.irms.internal.database.entities.ReservationEntity;
import com.uliasz.irms.internal.database.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public void createReservation(ReservationModel reservationModel) {

    }

    public void updateReservation(ReservationModel reservationModel) {
        Optional<ReservationEntity> reservation = reservationRepository.findById(reservationModel.getId());
        if(!reservation.isPresent()) {
            log.warn(String.format("Cannot update reservation with id: %d, because did not exist"));
            throw new EntityNotFoundException(String.format("Reservation not found, id: %d", reservationModel.getId()));
        }
        reservationRepository.save(ReservationConverter.convertToEntity(reservationModel));
    }

    public void deleteReservation(Long id) {
        if(!reservationRepository.existsById(id)) {
            log.warn(String.format("Cannot delete reservation with id: %d, because did not exist"));
            throw new EntityNotFoundException(String.format("Reservation not found, id: %d", id));
        }
        log.info(String.format("Reservation with id: %d was removed"));
        reservationRepository.deleteById(id);
    }
}
