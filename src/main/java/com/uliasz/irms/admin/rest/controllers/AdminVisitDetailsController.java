package com.uliasz.irms.admin.rest.controllers;

import com.uliasz.irms.admin.rest.services.AdminVisitDetailsService;
import com.uliasz.irms.internal.common.models.VisitDetailsModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adminVisitDetailsApi")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminVisitDetailsController {

    private final AdminVisitDetailsService adminVisitDetailsService;

    @PostMapping(value = "/addVisitDetails")
    public ResponseEntity<VisitDetailsModel> addVisitDetails(@RequestBody VisitDetailsModel visitDetailsModel, @RequestParam Long reservationId) {
        return ResponseEntity.ok(adminVisitDetailsService.addVisitDetails(visitDetailsModel, reservationId));
    }
}
