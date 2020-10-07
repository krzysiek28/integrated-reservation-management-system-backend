package com.uliasz.irms.internal.database.repositories;

import com.uliasz.irms.internal.database.entities.AppUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUserEntity, Long> {
}
