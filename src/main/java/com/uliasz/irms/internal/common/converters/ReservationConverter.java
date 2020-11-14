package com.uliasz.irms.internal.common.converters;

import com.uliasz.irms.internal.common.enums.ReservationStatus;
import com.uliasz.irms.internal.common.models.AppUserModel;
import com.uliasz.irms.internal.common.models.PersonalDataModel;
import com.uliasz.irms.internal.common.models.ReservationModel;
import com.uliasz.irms.internal.common.models.VisitDetailsModel;
import com.uliasz.irms.internal.database.entities.AppUserEntity;
import com.uliasz.irms.internal.database.entities.PersonalDataEntity;
import com.uliasz.irms.internal.database.entities.ReservationEntity;
import com.uliasz.irms.internal.database.entities.VisitDetailsEntity;

public final class ReservationConverter {

    public static ReservationModel convertToModel(ReservationEntity reservationEntity) {
        return ReservationModel.builder()
                .id(reservationEntity.getId())
                .date(reservationEntity.getDate())
                .timeFrom(reservationEntity.getTimeFrom())
                .timeTo(reservationEntity.getTimeTo())
                .status(ReservationStatus.valueOf(reservationEntity.getStatus()))
                .comment(reservationEntity.getComment())
                .user(convertToUserModel(reservationEntity.getUser()))
                .visitDetails(convertToVisitDetailsModel(reservationEntity.getVisitDetails()))
                .personalData(convertToPersonalDataModel(reservationEntity.getPersonalData()))
                .build();
    }

    private static AppUserModel convertToUserModel(AppUserEntity appUserEntity) {
        return appUserEntity != null
                ? AppUserConverter.convertToModel(appUserEntity)
                : null;
    }

    private static VisitDetailsModel convertToVisitDetailsModel(VisitDetailsEntity visitDetailsEntity) {
        return visitDetailsEntity != null
                ? VisitDetailsConverter.convertToModel(visitDetailsEntity)
                : null;
    }

    private static PersonalDataModel convertToPersonalDataModel(PersonalDataEntity personalDataEntity) {
        return personalDataEntity != null
                ? (PersonalDataConverter.convertToModel(personalDataEntity))
                : null;
    }

    public static ReservationEntity convertToEntity(ReservationModel reservationModel) {
        return ReservationEntity.builder()
                .id(reservationModel.getId())
                .date(reservationModel.getDate())
                .timeFrom(reservationModel.getTimeFrom())
                .timeTo(reservationModel.getTimeTo())
                .status(reservationModel.getStatus().name())
                .comment(reservationModel.getComment())
                .user(convertToUserEntity(reservationModel.getUser()))
                .visitDetails(convertToVisitDetailsEntity(reservationModel.getVisitDetails()))
                .personalData(convertToPersonalDataEntity(reservationModel.getPersonalData()))
                .build();
    }

    private static AppUserEntity convertToUserEntity(AppUserModel appUserModel) {
        return appUserModel != null
                ? AppUserConverter.convertToEntity(appUserModel)
                : null;
    }

    private static VisitDetailsEntity convertToVisitDetailsEntity(VisitDetailsModel visitDetailsModel) {
        return visitDetailsModel != null
                ? VisitDetailsConverter.convertToEntity(visitDetailsModel)
                : null;
    }

    private static PersonalDataEntity convertToPersonalDataEntity(PersonalDataModel personalDataModel) {
        return personalDataModel != null
                ? (PersonalDataConverter.convertToEntity(personalDataModel))
                : null;
    }
}
