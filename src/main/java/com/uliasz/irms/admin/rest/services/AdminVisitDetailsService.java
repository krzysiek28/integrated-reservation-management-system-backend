package com.uliasz.irms.admin.rest.services;

import com.uliasz.irms.internal.common.models.ReservationModel;
import com.uliasz.irms.internal.common.models.VisitDetailsModel;
import com.uliasz.irms.internal.common.providers.ReservationProvider;
import com.uliasz.irms.internal.common.services.VisitDetailsDataAccessService;
import com.uliasz.irms.internal.emailService.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/adminVisitDetailsApi")
@RequiredArgsConstructor
public class AdminVisitDetailsService {

    private final VisitDetailsDataAccessService visitDetailsDataAccessService;
    private final ReservationProvider reservationProvider;
    private final EmailService emailService;

    public VisitDetailsModel addVisitDetails(VisitDetailsModel visitDetailsModel, Long reservationId) {
        ReservationModel reservation = reservationProvider.getReservationById(reservationId);
        emailService.sendVisitInfoMail(reservation.getPersonalData().getContactEmail(), visitDetailsModel, reservation);
        return visitDetailsDataAccessService.addVisitDetailsModel(visitDetailsModel, reservationId);
    }
}
