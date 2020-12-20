package com.uliasz.irms.internal.database.repositories;

import com.uliasz.irms.internal.database.entities.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

    List<ReservationEntity> findByDate(Date date);
    List<ReservationEntity> findByDateAndStatus(Date date, String status);
    List<ReservationEntity> findByStatus(String status);

    @Query("select r from ReservationEntity r where r.date >= :startDate and r.date <= :endDate and r.status = :status")
    List<ReservationEntity> findByDateBetweenAndAvailableStatus(@Param("startDate")Date startDate, @Param("endDate")Date endDate, @Param("status")String status);

}
