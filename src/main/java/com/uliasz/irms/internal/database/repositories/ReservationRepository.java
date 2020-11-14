package com.uliasz.irms.internal.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.uliasz.irms.internal.database.entities.ReservationEntity;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    List<ReservationEntity> findByDate(Date date);
    List<ReservationEntity> findByDateAndStatus(Date date, String status);
    List<ReservationEntity> findByDateAfterAndDateBeforeAndStatus(Date startDate, Date endDate, String status);
}
