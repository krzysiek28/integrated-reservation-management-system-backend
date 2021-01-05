package com.uliasz.irms.internal.jobService;

import com.uliasz.irms.internal.common.enums.ReservationStatus;
import com.uliasz.irms.internal.common.providers.ReservationProvider;
import com.uliasz.irms.internal.common.services.ReservationDataAccessService;
import com.uliasz.irms.internal.database.entities.ReservationEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@SchedulerJob
@Slf4j
@RequiredArgsConstructor
@Service
public class UpdateExpiredReservations {

    private final ReservationDataAccessService reservationDataAccessService;
    private final ReservationProvider reservationProvider;

    @Scheduled(cron = "0 30 2/5 * * ?")
    public void cronJob() {
        log.info("Job Scheduler starts...");
        List<ReservationEntity> expiredReservations = findExpiredReservations();

        expiredReservations.forEach(reservation -> {
            reservationDataAccessService.deleteReservation(reservation.getId());
            log.info(String.format("Deleted reservation id: %d, date: %s, timeFrom: %s, timeTo: %s",
                    reservation.getId(), reservation.getDate().toString(), reservation.getTimeFrom().toString(), reservation.getTimeTo().toString()));
        });

        List<ReservationEntity> completedVisits = findCompletedVisits();
        completedVisits.forEach(reservation -> {
            reservationDataAccessService.updateReservationStatus(reservation.getId(), ReservationStatus.CLOSED);
            log.info(String.format("Update reservation status from RESERVED to CLOSED. Reservation id: %d, date: %s, timeFrom: %s, timeTo: %s",
                    reservation.getId(), reservation.getDate().toString(), reservation.getTimeFrom().toString(), reservation.getTimeTo().toString()));
        });

        log.info("Job Scheduler finished");
    }

    private List<ReservationEntity> findExpiredReservations() {
        return reservationProvider.getReservationsEntityByDateBeforeAndStatus(new Date(), ReservationStatus.AVAILABLE);
    }

    private List<ReservationEntity> findCompletedVisits() {
        return reservationProvider.getReservationsEntityByDateBeforeAndStatus(new Date(), ReservationStatus.RESERVED);
    }
}
