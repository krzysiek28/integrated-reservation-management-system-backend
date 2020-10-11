package com.uliasz.irms.admin.visitDetails;

import com.uliasz.irms.internal.common.models.VisitDetailsModel;
import com.uliasz.irms.internal.common.services.VisitDetailsDataAccessService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequestMapping("/adminVisitDetailsApi")
@RequiredArgsConstructor
public class AdminVisitDetailsService {

    private final VisitDetailsDataAccessService visitDetailsDataAccessService;

    public VisitDetailsModel addVisitDetails(VisitDetailsModel visitDetailsModel, Long reservationId) {
        return visitDetailsDataAccessService.addVisitDetailsModel(visitDetailsModel, reservationId);
    }
}
