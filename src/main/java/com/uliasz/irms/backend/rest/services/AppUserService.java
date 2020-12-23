package com.uliasz.irms.backend.rest.services;

import com.uliasz.irms.internal.common.models.AppUserModel;
import com.uliasz.irms.internal.common.models.PersonalDataModel;
import com.uliasz.irms.internal.common.providers.AppUserProvider;
import com.uliasz.irms.internal.common.services.AppUserDataAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserProvider appUserProvider;
    private final AppUserDataAccessService appUserDataAccessService;

    public AppUserModel getUserById(Long id) {
        return appUserProvider.getUserById(id);
    }

    public AppUserModel updateUserPersonalData(Long id, PersonalDataModel personalDataModel) {
        return appUserDataAccessService.updateUserPersonalData(id, personalDataModel);
    }
}
