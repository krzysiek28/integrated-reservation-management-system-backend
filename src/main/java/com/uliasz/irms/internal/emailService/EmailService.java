package com.uliasz.irms.internal.emailService;

import com.uliasz.irms.backend.rest.objects.request.ReserveRequest;
import com.uliasz.irms.internal.common.models.ReservationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;

    public String sendReservationMail(String emailTo, ReservationModel reservationModel, ReserveRequest reserveRequest) {
        Context context = new Context();
        context.setVariable("footer", "Zintegrowany system zarządzania rezerwacją");
        context.setVariable("title", "Zarezerwowano wizytę!");
        context.setVariable("description", "Dane dotyczące wizyty");
        context.setVariable("hasUserAccount", !StringUtils.isEmpty(reserveRequest.getUserId()));
        context.setVariable("reservationModel", reservationModel);
        context.setVariable("personalDataModel", reserveRequest.getPersonalDataModel());
        context.setVariable("comment", reserveRequest.getComment());
        String body = templateEngine.process("reservation-email-template", context);
        emailSender.sendEmail("krzysztof.uliasz@gmail.com" /*emailTo*/, "Irms: Potwierdzenie rezerwacji", body);
        return "index";
    }

    public String sendVisitInfoMail() {
        Context context = new Context();
        context.setVariable("header", "Zintegrowany system zarządzania rezerwacją");
        context.setVariable("title", "Zarezerwowano wizytę!");
        context.setVariable("description", "Dane dotyczące wizyty");
        String body = templateEngine.process("reservation-email-template", context);
        emailSender.sendEmail("krzysztof.uliasz@gmail.com", "Irms: Potwierdzenie rezerwacji", body);
        return "index";
    }
}
