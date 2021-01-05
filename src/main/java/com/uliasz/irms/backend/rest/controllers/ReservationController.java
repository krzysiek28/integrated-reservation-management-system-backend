package com.uliasz.irms.backend.rest.controllers;

import com.uliasz.irms.backend.rest.objects.request.ReserveRequest;
import com.uliasz.irms.backend.rest.services.ReservationService;
import com.uliasz.irms.internal.common.enums.ReservationStatus;
import com.uliasz.irms.internal.common.models.ReservationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservationsApi")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping(value = "/availableReservationsByDateRange")
    public ResponseEntity<List<ReservationModel>> getAvailableReservationsByDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        return ResponseEntity.ok(reservationService.getAvailableReservationsByDateRange(startDate, endDate));
    }

    @PatchMapping(value = "/reserve/{id}")
    public ResponseEntity<ReservationModel> reserve(@PathVariable Long id, @RequestBody ReserveRequest reserveRequest) {
        return ResponseEntity.ok(reservationService.reserve(id, reserveRequest));
    }

    @GetMapping(value = "/getOwnReservations")
    public ResponseEntity<List<ReservationModel>> getOwnReservations(@RequestParam Long userId) {
        return ResponseEntity.ok(reservationService.getOwnReservations(userId));
    }

    @PatchMapping(value = "/changeStatus/{id}")
    public ResponseEntity<ReservationModel> changeStatusToClosed(@PathVariable Long id, @RequestBody String status) {
        if (!ReservationStatus.AVAILABLE.getValue().equals(status)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Cannot change status to something other than AVAILABLE for owned permission");
        }
        return ResponseEntity.ok(reservationService.changeReservationStatusAndRemoveAdditionalData(id, status));
    }

    @GetMapping(value = "/reservation/{id}")
    public ResponseEntity<ReservationModel> getReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }
}
