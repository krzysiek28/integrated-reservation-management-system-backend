package com.uliasz.irms.internal.database.repositories;

import com.uliasz.irms.internal.database.entities.VisitDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitDetailsRepository extends JpaRepository<VisitDetailsEntity, Long> {
}
