package com.uliasz.irms.backend.reservations;

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

    @GetMapping(value = "/availableReservationsByDate")
    public ResponseEntity<List<ReservationModel>> getAvailableReservationsByDate(@RequestParam Date date) {
        return ResponseEntity.ok(reservationService.getAvailableReservationsByDate(date));
    }

    @PutMapping(value = "/updateReservationStatus")
    public ResponseEntity<ReservationModel> updateReservationStatus(@RequestParam Long reservationId, @RequestParam ReservationStatus status) {
        return ResponseEntity.ok(reservationService.updateReservationStatus(reservationId, status));
    }
}
