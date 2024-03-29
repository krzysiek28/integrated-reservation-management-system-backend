package com.uliasz.irms.internal.common.services;

import com.uliasz.irms.backend.rest.objects.request.ReserveRequest;
import com.uliasz.irms.internal.common.converters.PersonalDataConverter;
import com.uliasz.irms.internal.common.converters.ReservationConverter;
import com.uliasz.irms.internal.common.enums.ReservationStatus;
import com.uliasz.irms.internal.common.exceptions.PersonalDataNotFoundException;
import com.uliasz.irms.internal.common.exceptions.ReservationNotFoundException;
import com.uliasz.irms.internal.common.exceptions.UserNotFountException;
import com.uliasz.irms.internal.common.models.ReservationModel;
import com.uliasz.irms.internal.database.entities.AppUserEntity;
import com.uliasz.irms.internal.database.entities.PersonalDataEntity;
import com.uliasz.irms.internal.database.entities.ReservationEntity;
import com.uliasz.irms.internal.database.repositories.AppUserRepository;
import com.uliasz.irms.internal.database.repositories.PersonalDataRepository;
import com.uliasz.irms.internal.database.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationDataAccessService {

    private final ReservationRepository reservationRepository;
    private final PersonalDataRepository personalDataRepository;
    private final AppUserRepository appUserRepository;

    public List<ReservationModel> createReservations(List<ReservationModel> reservations) {
        return reservations.stream().map(this::createReservation).collect(Collectors.toList());
    }

    public ReservationModel createReservation(ReservationModel reservationModel) {
        return ReservationConverter.convertToModel(reservationRepository.save(ReservationConverter.convertToEntity(reservationModel)));
    }

    public List<ReservationModel> getOwnReservations(Long userId) {
       return reservationRepository.findByUserId(userId).stream()
               .map(ReservationConverter::convertToModel)
               .sorted(Comparator.comparing(ReservationModel::getDate).reversed())
               .collect(Collectors.toList());
    }

    public ReservationModel updateReservationByAdditionalInformation(Long reservationId, ReserveRequest reserveRequest, ReservationStatus reservationStatus) {
        Optional<ReservationEntity> reservation = reservationRepository.findById(reservationId);
        if (reservation.isEmpty()) {
            log.warn(String.format("Cannot update reservation with id: %d, because did not exist", reservationId));
            throw new ReservationNotFoundException(reservationId);
        }
        ReservationEntity reservationEntity = reservation.get();
        PersonalDataEntity personalDataEntity = PersonalDataConverter.convertToEntity(reserveRequest.getPersonalDataModel());
        personalDataRepository.save(personalDataEntity);
        reservationEntity.setPersonalData(personalDataEntity);
        reservationEntity.setStatus(reservationStatus.getValue());
        reservationEntity.setComment(reserveRequest.getComment());

        setUserIfExist(reserveRequest.getUserId(), reservationEntity);

        return ReservationConverter.convertToModel(reservationRepository.save(reservationEntity));
    }

    private void setUserIfExist(Long userId, ReservationEntity reservationEntity) {
        if (userId != null) {
            Optional<AppUserEntity> userOpt = appUserRepository.findById(userId);
            if (userOpt.isEmpty()) {
                log.warn(String.format("Cannot update user with id: %d, because did not exist", userId));
                throw new UserNotFountException(userId);
            }
            reservationEntity.setUser(userOpt.get());
        }
    }

    public ReservationModel updateReservationStatus(Long reservationId, ReservationStatus reservationStatus) {
        Optional<ReservationEntity> reservation = reservationRepository.findById(reservationId);
        if (reservation.isEmpty()) {
            log.warn(String.format("Cannot update reservation with id: %d, because did not exist", reservationId));
            throw new ReservationNotFoundException(reservationId);
        }
        ReservationEntity reservationEntity = reservation.get();
        log.info(String.format("Update reservation %d, status from %s to %s", reservationId, reservationEntity.getStatus(), reservationStatus.name()));
        reservationEntity.setStatus(reservationStatus.name());
        return ReservationConverter.convertToModel(reservationRepository.save(reservationEntity));
    }

    public void deleteReservation(Long id) {
        if (!reservationRepository.existsById(id)) {
            log.warn(String.format("Cannot delete reservation with id: %d, because did not exist", id));
            throw new ReservationNotFoundException(id);
        }
        log.info(String.format("Reservation with id: %d was removed", id));
        reservationRepository.deleteById(id);
    }

    public ReservationModel updateReservationStatusAndRemoveAdditionalInfo(Long reservationId, ReservationStatus reservationStatus) {
        Optional<ReservationEntity> reservation = reservationRepository.findById(reservationId);
        if (reservation.isEmpty()) {
            log.warn(String.format("Cannot update reservation with id: %d, because did not exist", reservationId));
            throw new ReservationNotFoundException(reservationId);
        }

        ReservationEntity reservationEntity = reservation.get();
        reservationEntity.setStatus(reservationStatus.name());
        reservationEntity.setComment(null);
        reservationEntity.setPersonalData(null);
        reservationEntity.setUser(null);

        return ReservationConverter.convertToModel(reservationRepository.save(reservationEntity));
    }

/*    public void deletePersonalDataIfExist(ReservationEntity reservationEntity) {
        PersonalDataEntity personalData = reservationEntity.getPersonalData();
        if (personalData != null) {
            Optional<PersonalDataEntity> personalDataOpt = personalDataRepository.findById(personalData.getId());
            if (personalDataOpt.isEmpty()) {
                log.warn(String.format("Cannot get reservation with id: %d, because did not exist", personalData.getId()));
                throw new PersonalDataNotFoundException(personalData.getId());
            }
            reservationEntity.setPersonalData(null);
            if (reservationEntity.getUser() != null) {
                personalDataRepository.deleteById(personalData.getId());
            }
        }
    }*/
}
