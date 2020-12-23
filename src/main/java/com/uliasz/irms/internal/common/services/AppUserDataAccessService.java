package com.uliasz.irms.internal.common.services;

import com.uliasz.irms.internal.common.converters.AppUserConverter;
import com.uliasz.irms.internal.common.converters.PersonalDataConverter;
import com.uliasz.irms.internal.common.exceptions.UserNotFountException;
import com.uliasz.irms.internal.common.models.AppUserModel;
import com.uliasz.irms.internal.common.models.PersonalDataModel;
import com.uliasz.irms.internal.database.entities.AppUserEntity;
import com.uliasz.irms.internal.database.repositories.AppUserRepository;
import com.uliasz.irms.internal.database.repositories.PersonalDataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserDataAccessService {

    private final AppUserRepository appUserRepository;
    private final PersonalDataRepository personalDataRepository;

    public AppUserModel updateUserPersonalData(Long userId, PersonalDataModel personalDataModel) {
        Optional<AppUserEntity> userOpt = appUserRepository.findById(userId);

        if (!userOpt.isPresent()) {
            log.warn(String.format("Cannot update user with id: %d, because did not exist", userId));
            throw new UserNotFountException(userId);
        }
        AppUserEntity user = userOpt.get();

        user.setPersonalData(PersonalDataConverter.convertToEntity(personalDataModel));
        return AppUserConverter.convertToModel(appUserRepository.save(user));
    }
}
