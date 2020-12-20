package com.uliasz.irms.security.services;

import com.uliasz.irms.internal.database.entities.AppUserEntity;
import com.uliasz.irms.internal.database.repositories.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<AppUserEntity> appUser = appUserRepository.findByLogin(userName);
        return createUser(appUser.orElseThrow(() -> new UsernameNotFoundException(userName)));
    }

    private UserDetailsImpl createUser(AppUserEntity appUser) {
        return UserDetailsImpl.build(appUser);
    }
}

