package com.uliasz.irms.admin.reservations;

import com.uliasz.irms.internal.common.models.ReservationModel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/adminReservationsApi")
@CrossOrigin(origins = "http://localhost:4200")
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
}
