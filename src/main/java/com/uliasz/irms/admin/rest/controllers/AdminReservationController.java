package com.uliasz.irms.admin.rest.controllers;

import com.uliasz.irms.admin.rest.objects.ReservationSearchRequest;
import com.uliasz.irms.admin.rest.services.AdminReservationService;
import com.uliasz.irms.internal.common.models.ReservationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/adminReservationsApi")
@RequiredArgsConstructor
public class AdminReservationController {

    private final AdminReservationService adminReservationService;

    @GetMapping(value = "/reservationsByDate")
    public ResponseEntity<List<ReservationModel>> getReservationsByDate(@RequestParam Date date) {
        return ResponseEntity.ok(adminReservationService.getReservationsByDate(date));
    }

    @PostMapping(value = "/reservation")
    public ResponseEntity<ReservationModel> addReservation(@RequestBody ReservationModel reservation) {
        return ResponseEntity.ok(adminReservationService.addReservation(reservation));
    }

    @PostMapping(value = "/reservations")
    public ResponseEntity<List<ReservationModel>> addReservations(@RequestBody List<ReservationModel> reservations) {
        return ResponseEntity.ok(adminReservationService.addReservations(reservations));
    }

    @PostMapping(value = "/searchReservations")
    public ResponseEntity<List<ReservationModel>> addReservations(@Valid @RequestBody ReservationSearchRequest searchRequest) {
        return ResponseEntity.ok(adminReservationService.searchReservations(searchRequest));
    }

    @DeleteMapping(value = "/reservation/{id}")
    public ResponseEntity<Long> deleteReservation(@PathVariable Long id) {
        adminReservationService.deleteReservation(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping(value = "/getClosedReservations")
    public ResponseEntity<List<ReservationModel>> getClosedReservations() {
        return ResponseEntity.ok(adminReservationService.getClosedReservations());
    }
}
