package com.uliasz.irms.internal.common.services;

import com.uliasz.irms.internal.common.converters.ReservationConverter;
import com.uliasz.irms.internal.common.enums.ReservationStatus;
import com.uliasz.irms.internal.common.exceptions.ReservationNotFoundException;
import com.uliasz.irms.internal.common.models.ReservationModel;
import com.uliasz.irms.internal.database.entities.ReservationEntity;
import com.uliasz.irms.internal.database.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationDataAccessService {

    private final ReservationRepository reservationRepository;

    public List<ReservationModel> createReservations(List<ReservationModel> reservations) {
        return reservations.stream().map(this::createReservation).collect(Collectors.toList());
    }

    private ReservationModel createReservation(ReservationModel reservationModel) {
        return ReservationConverter.convertToModel(reservationRepository.save(ReservationConverter.convertToEntity(reservationModel)));
    }

    public ReservationModel updateReservationStatus(Long reservationId, ReservationStatus reservationStatus) {
        Optional<ReservationEntity> reservation = reservationRepository.findById(reservationId);
        if (!reservation.isPresent()) {
            log.warn(String.format("Cannot update reservation with id: %d, because did not exist", reservationId));
            throw new ReservationNotFoundException(reservationId);
        }
        ReservationEntity reservationEntity = reservation.get();
        log.info(String.format("Update reservation %d, status from %s to %s", reservationId, reservationEntity.getStatus(), reservationStatus.name()));
        reservationEntity.setStatus(reservationStatus.name());
        return ReservationConverter.convertToModel(reservationRepository.save(reservationEntity));
    }

    public ReservationModel updateReservation(ReservationModel reservationModel) {
        Optional<ReservationEntity> reservation = reservationRepository.findById(reservationModel.getId());
        if (!reservation.isPresent()) {
            log.warn(String.format("Cannot update reservation with id: %d, because did not exist", reservationModel.getId()));
            throw new ReservationNotFoundException(reservationModel.getId());
        }
        return ReservationConverter.convertToModel(reservationRepository.save(ReservationConverter.convertToEntity(reservationModel)));
    }

    public void deleteReservation(Long id) {
        if (!reservationRepository.existsById(id)) {
            log.warn(String.format("Cannot delete reservation with id: %d, because did not exist", id));
            throw new ReservationNotFoundException(id);
        }
        log.info(String.format("Reservation with id: %d was removed"));
        reservationRepository.deleteById(id);
    }
}
