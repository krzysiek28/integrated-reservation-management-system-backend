package com.uliasz.irms.backend.users;

import com.uliasz.irms.internal.database.entities.AppUserEntity;
import com.uliasz.irms.internal.database.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserService {

    private final AppUserRepository appUserRepository;

    public List<AppUserEntity> getAppUsers() {
        return appUserRepository.findAll();
    }

    public void saveUser(AppUserEntity entity) {
        appUserRepository.save(entity);
    }
}
