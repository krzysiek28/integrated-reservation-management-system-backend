package com.uliasz.irms.backend.rest.controllers;

import com.uliasz.irms.backend.rest.services.ReservationService;
import com.uliasz.irms.internal.common.enums.ReservationStatus;
import com.uliasz.irms.internal.common.models.ReservationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservationsApi")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping(value = "/availableReservationsByDateRange")
    public ResponseEntity<List<ReservationModel>> getAvailableReservationsByDateRange(@RequestParam Date startDate, @RequestParam Date endDate) {
        return ResponseEntity.ok(reservationService.getAvailableReservationsByDateRange(startDate, endDate));
    }

    //todo do usunięcie, trzeba całe encje modyfikować kto rezerwuje,
    @PutMapping(value = "/updateReservationStatus")
    public ResponseEntity<ReservationModel> updateReservationStatus(@RequestParam Long reservationId, @RequestParam ReservationStatus status) {
        return ResponseEntity.ok(reservationService.updateReservationStatus(reservationId, status));
    }

    //way to delete reservation by name lastName and phoneNumber

    @GetMapping(value = "/reservation/{id}")
    public ResponseEntity<ReservationModel> getReservation(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    //only for login users
    @DeleteMapping(value = "/reservation/{id}")
    public ResponseEntity<Long> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.ok(id);
    }
}
