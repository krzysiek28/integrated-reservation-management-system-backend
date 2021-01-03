package com.uliasz.irms.internal.emailService;

import com.uliasz.irms.backend.rest.objects.request.ReserveRequest;
import com.uliasz.irms.internal.common.models.ReservationModel;
import com.uliasz.irms.internal.common.models.VisitDetailsModel;
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
        context.setVariable("description", "Dane dotyczące wizyty:");
        context.setVariable("hasUserAccount", !StringUtils.isEmpty(reserveRequest.getUserId()));
        context.setVariable("reservationModel", reservationModel);
        context.setVariable("personalDataModel", reserveRequest.getPersonalDataModel());
        context.setVariable("comment", reserveRequest.getComment());
        String body = templateEngine.process("reservation-email-template", context);
        emailSender.sendEmail("krzysztof.uliasz@gmail.com" /*emailTo*/, "Irms: Potwierdzenie rezerwacji", body);
        return "index";
    }

    public String sendSuccessfullyRegistrationMail(String emailTo, String login) {
        Context context = new Context();
        context.setVariable("footer", "Zintegrowany system zarządzania rezerwacją");
        context.setVariable("title", "Założono konto w serwisie IRMS");
        context.setVariable("description", "Zarejestrowałeś się w serwisie IRMS pod nazwą " + login +
                ". Uzupełnił swoje dane osobowe w celu łatwiejszego rezerwowania wizyt. W zakładce Rezerwacje>Przeglądaj Twoje wizyty, możesz przeglądać zarezerwowane wizyty oraz " +
                "już odbyte wraz z dostępem do notatki sporządzonej po wizycie.");
        String body = templateEngine.process("registration-successfully-email-template", context);
        emailSender.sendEmail("krzysztof.uliasz@gmail.com" /*emailTo*/, "Irms: Potwierdzenie rezerwacji", body);
        return "index";
    }

    public String sendVisitInfoMail(String emailTo, VisitDetailsModel visitDetailsModel, ReservationModel reservationModel) {
        Context context = new Context();
        context.setVariable("header", "Zintegrowany system zarządzania rezerwacją");
        context.setVariable("title", "Zarezerwowano wizytę!");
        context.setVariable("description", "Dane dotyczące wizyty");
        String body = templateEngine.process("reservation-email-template", context);
        emailSender.sendEmail("krzysztof.uliasz@gmail.com", "Irms: Potwierdzenie rezerwacji", body);
        return "index";
    }
}
