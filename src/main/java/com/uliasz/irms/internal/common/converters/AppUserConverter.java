package com.uliasz.irms.internal.common.converters;

import com.uliasz.irms.internal.common.models.AppUserModel;
import com.uliasz.irms.internal.database.entities.AppUserEntity;

public final class AppUserConverter {

    public static AppUserModel convertToModel(AppUserEntity appUserEntity) {
        return AppUserModel.builder()
                .id(appUserEntity.getId())
                .login(appUserEntity.getLogin())
                .password(appUserEntity.getPassword())
                .personalData(PersonalDataConverter.convertToModel(appUserEntity.getPersonalData()))
                .role(appUserEntity.getRole())
                .enabled(appUserEntity.getEnabled())
                .build();
    }

    public static AppUserEntity convertToEntity(AppUserModel appUserModel) {
        return AppUserEntity.builder()
                .id(appUserModel.getId())
                .login(appUserModel.getLogin())
                .password(appUserModel.getPassword())
                .personalData(PersonalDataConverter.convertToEntity(appUserModel.getPersonalData()))
                .role(appUserModel.getRole())
                .enabled(appUserModel.getEnabled())
                .build();
    }
}
