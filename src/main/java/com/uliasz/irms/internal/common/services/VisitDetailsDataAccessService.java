package com.uliasz.irms.internal.common.services;

import com.uliasz.irms.internal.common.converters.VisitDetailsConverter;
import com.uliasz.irms.internal.common.exceptions.ReservationNotFoundException;
import com.uliasz.irms.internal.common.models.VisitDetailsModel;
import com.uliasz.irms.internal.common.providers.ReservationProvider;
import com.uliasz.irms.internal.database.entities.ReservationEntity;
import com.uliasz.irms.internal.database.entities.VisitDetailsEntity;
import com.uliasz.irms.internal.database.repositories.ReservationRepository;
import com.uliasz.irms.internal.database.repositories.VisitDetailsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class VisitDetailsDataAccessService {

    private final VisitDetailsRepository visitDetailsRepository;
    private final ReservationRepository reservationRepository;

    public VisitDetailsModel addVisitDetailsModel(VisitDetailsModel visitDetailsModel, Long reservationId) {
        Optional<ReservationEntity> reservation = reservationRepository.findById(reservationId);
        ReservationEntity reservationEntity = reservation.orElseThrow(()  -> new ReservationNotFoundException(reservationId));

        VisitDetailsEntity visitDetails = visitDetailsRepository.save(VisitDetailsConverter.convertToEntity(visitDetailsModel));
        reservationEntity.setVisitDetails(visitDetails);
        reservationRepository.save(reservationEntity);

        return VisitDetailsConverter.convertToModel(visitDetails);
    }
}
