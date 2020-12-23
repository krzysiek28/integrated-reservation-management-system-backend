package com.uliasz.irms.internal.common.providers;

import com.uliasz.irms.internal.common.converters.AppUserConverter;
import com.uliasz.irms.internal.common.exceptions.ReservationNotFoundException;
import com.uliasz.irms.internal.common.models.AppUserModel;
import com.uliasz.irms.internal.database.entities.AppUserEntity;
import com.uliasz.irms.internal.database.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppUserProvider {

    private final AppUserRepository userRepository;

    public AppUserModel getUserById(Long id) {
        Optional<AppUserEntity> user = userRepository.findById(id);
        return user.map(AppUserConverter::convertToModel)
                .orElseThrow(() -> new ReservationNotFoundException(id));
    }
}
