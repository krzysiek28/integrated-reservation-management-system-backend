package com.uliasz.irms.internal.common.converters;

import com.uliasz.irms.internal.common.enums.ReservationStatus;
import com.uliasz.irms.internal.common.models.ReservationModel;
import com.uliasz.irms.internal.database.entities.ReservationEntity;

public final class ReservationConverter {

    public static ReservationModel convertToModel(ReservationEntity reservationEntity) {
        return ReservationModel.builder()
                .id(reservationEntity.getId())
                .date(reservationEntity.getDate())
                .timeFrom(reservationEntity.getTimeFrom())
                .timeTo(reservationEntity.getTimeTo())
                .status(ReservationStatus.valueOf(reservationEntity.getStatus()))
                .comment(reservationEntity.getComment())
                .user(AppUserConverter.convertToModel(reservationEntity.getUser()))
                .visitDetails(VisitDetailsConverter.convertToModel(reservationEntity.getVisitDetails()))
                .personalData(PersonalDataConverter.convertToModel(reservationEntity.getPersonalData()))
                .build();
    }

    public static ReservationEntity convertToEntity(ReservationModel reservationModel) {
        return ReservationEntity.builder()
                .id(reservationModel.getId())
                .date(reservationModel.getDate())
                .timeFrom(reservationModel.getTimeFrom())
                .timeTo(reservationModel.getTimeTo())
                .status(reservationModel.getStatus().name())
                .comment(reservationModel.getComment())
                .user(AppUserConverter.convertToEntity(reservationModel.getUser()))
                .visitDetails(VisitDetailsConverter.convertToEntity(reservationModel.getVisitDetails()))
                .personalData(PersonalDataConverter.convertToEntity(reservationModel.getPersonalData()))
                .build();
    }
}
