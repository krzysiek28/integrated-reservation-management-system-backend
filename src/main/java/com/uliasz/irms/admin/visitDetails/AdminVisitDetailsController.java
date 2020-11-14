package com.uliasz.irms.admin.visitDetails;

import com.uliasz.irms.internal.common.models.VisitDetailsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminVisitDetailsApi")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class AdminVisitDetailsController {

    private final AdminVisitDetailsService adminVisitDetailsService;

    @PostMapping(value = "/addVisitDetails")
    public ResponseEntity<VisitDetailsModel> addVisitDetails(@RequestBody VisitDetailsModel visitDetailsModel, @RequestParam Long reservationId) {
        return ResponseEntity.ok(adminVisitDetailsService.addVisitDetails(visitDetailsModel, reservationId));
    }

}
