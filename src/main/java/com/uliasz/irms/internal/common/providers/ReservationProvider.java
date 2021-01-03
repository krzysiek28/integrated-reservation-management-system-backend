package com.uliasz.irms.internal.common.providers;

import com.uliasz.irms.internal.common.converters.ReservationConverter;
import com.uliasz.irms.internal.common.enums.ReservationStatus;
import com.uliasz.irms.internal.common.exceptions.ReservationNotFoundException;
import com.uliasz.irms.internal.common.models.ReservationModel;
import com.uliasz.irms.internal.common.utils.DateUtil;
import com.uliasz.irms.internal.database.entities.ReservationEntity;
import com.uliasz.irms.internal.database.repositories.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ReservationProvider {
    private final ReservationRepository reservationRepository;

    public ReservationModel getReservationById(Long id) {
        Optional<ReservationEntity> reservation = reservationRepository.findById(id);
        return reservation.map(ReservationConverter::convertToModel)
                .orElseThrow(() -> new ReservationNotFoundException(id));
    }

    public List<ReservationModel> getReservationsByDate(Date date) {
        List<ReservationEntity> reservations = reservationRepository.findByDate(date);
        return reservations.stream()
                .map(ReservationConverter::convertToModel)
                .sorted(Comparator.comparing(ReservationModel::getDate).reversed())
                .collect(Collectors.toList());
    }

    public List<ReservationModel> getReservationsByDateRange(Date startDate, Date endDate) {
        List<ReservationEntity> reservations = reservationRepository.findAll();
        return reservations.stream()
                .filter(res -> DateUtil.isDateBetween(res.getDate(), startDate, endDate))
                .map(ReservationConverter::convertToModel)
                .sorted(Comparator.comparing(ReservationModel::getDate).reversed())
                .collect(Collectors.toList());
    }

    public List<ReservationModel> getAvailableReservationsByDateRange(Date startDate, Date endDate) {
        List<ReservationEntity> reservations = reservationRepository.findByStatus(ReservationStatus.AVAILABLE.name());
        Date today = new Date();
        return reservations.stream()
                .filter(res -> DateUtil.isDateBetween(res.getDate(), startDate, endDate))
                .filter(res -> DateUtil.isDateAfter(res.getDate(), getDatePlusDay(startDate)) || DateUtil.isTimeAfter(res.getTimeFrom(), today))
                .map(ReservationConverter::convertToModel)
                .sorted(Comparator.comparing(ReservationModel::getDate).reversed())
                .collect(Collectors.toList());
    }

    public List<ReservationModel> getReservationsForLastMonthWithClosedStatus() {
        Date dateMinusOneMonth = getDateMinusMonth();
        return reservationRepository.findByStatus(ReservationStatus.CLOSED.name())
                .stream()
                .filter(reservationEntity -> DateUtil.isDateAfter(reservationEntity.getDate(), dateMinusOneMonth))
                .map(ReservationConverter::convertToModel)
                .sorted(Comparator.comparing(ReservationModel::getDate).reversed())
                .collect(Collectors.toList());
    }

    private Date getDateMinusMonth() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    private Date getDatePlusDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }
}
