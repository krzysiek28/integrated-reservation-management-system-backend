package com.uliasz.irms.internal.database.repositories;

import com.uliasz.irms.internal.database.entities.PersonalDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDataRepository extends JpaRepository<PersonalDataEntity, Long> {
}
