package com.example.koolcard.controller;

import com.example.koolcard.entities.Reservation;
import com.example.koolcard.services.ReservationInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationInterface reservationService;

    // Constructor injection of ReservationService
    public ReservationController(ReservationInterface reservationService) {
        this.reservationService = reservationService;
    }

    // Endpoint to create a reservation
    @PostMapping("/create")
    public ResponseEntity<Reservation> createReservation(@RequestBody Reservation reservation) {
        try {
            Reservation createdReservation = reservationService.createReservation(reservation);
            return ResponseEntity.ok(createdReservation);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body(null); // Return 400 Bad Request if table is not available
        }
    }

    // Endpoint to get all reservations
    @GetMapping("/all")
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    // Endpoint to get reservation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationService.getReservationById(id));
    }

    // Endpoint to update a reservation
    @PutMapping("/update/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.updateReservation(id, reservation));
    }

    // Endpoint to delete a reservation
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        try {
            reservationService.deleteReservation(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).build(); // Return 404 if reservation not found
        }
    }
}
