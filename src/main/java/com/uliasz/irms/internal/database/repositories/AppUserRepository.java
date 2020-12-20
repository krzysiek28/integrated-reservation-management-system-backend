package com.uliasz.irms.internal.database.repositories;

import com.uliasz.irms.internal.database.entities.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUserEntity, Long> {
    Optional<AppUserEntity> findByLogin(String login);
    Boolean existsByLogin(String username);
    Boolean existsByEmail(String email);
}
