package com.uliasz.irms.internal.common.converters;

import com.uliasz.irms.internal.common.enums.UserRoles;
import com.uliasz.irms.internal.common.models.AppUserModel;
import com.uliasz.irms.internal.common.models.PersonalDataModel;
import com.uliasz.irms.internal.database.entities.AppUserEntity;
import com.uliasz.irms.internal.database.entities.PersonalDataEntity;

public final class AppUserConverter {

    public static AppUserModel convertToModel(AppUserEntity appUserEntity) {
        return AppUserModel.builder()
                .id(appUserEntity.getId())
                .login(appUserEntity.getLogin())
                .personalData(convertToPersonalDataModel(appUserEntity.getPersonalData()))
                .email(appUserEntity.getEmail())
                .role(UserRoles.getByValue(appUserEntity.getRole()))
                .enabled(appUserEntity.getEnabled())
                .build();
    }

    private static PersonalDataModel convertToPersonalDataModel(PersonalDataEntity personalDataEntity) {
        return personalDataEntity != null
                ? PersonalDataConverter.convertToModel(personalDataEntity)
                : null;
    }

    public static AppUserEntity convertToEntity(AppUserModel appUserModel) {
        return AppUserEntity.builder()
                .id(appUserModel.getId())
                .login(appUserModel.getLogin())
                .personalData(convertToPersonalDataEntity(appUserModel.getPersonalData()))
                .email(appUserModel.getEmail())
                .role(appUserModel.getRole().getValue())
                .enabled(appUserModel.getEnabled())
                .build();
    }

    private static PersonalDataEntity convertToPersonalDataEntity(PersonalDataModel personalDataModel) {
        return personalDataModel != null
                ? PersonalDataConverter.convertToEntity(personalDataModel)
                : null;
    }
}
