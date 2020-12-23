package com.uliasz.irms.internal.common.converters;

import com.uliasz.irms.internal.common.models.PersonalDataModel;
import com.uliasz.irms.internal.database.entities.PersonalDataEntity;

public final class PersonalDataConverter {

    public static PersonalDataModel convertToModel(PersonalDataEntity personalDataEntity) {
        return PersonalDataModel.builder()
                .id(personalDataEntity.getId())
                .firstName(personalDataEntity.getFirstName())
                .lastName(personalDataEntity.getLastName())
                .phoneNumber(personalDataEntity.getPhoneNumber())
                .contactEmail(personalDataEntity.getEmail())
                .build();
    }

    public static PersonalDataEntity convertToEntity(PersonalDataModel personalDataModel) {
        return PersonalDataEntity.builder()
                .id(personalDataModel.getId())
                .firstName(personalDataModel.getFirstName())
                .lastName(personalDataModel.getLastName())
                .email(personalDataModel.getContactEmail())
                .build();
    }
}
