package com.example.koolcard.services;

import com.example.koolcard.entities.Reservation;
import java.util.List;

public interface ReservationInterface {
    Reservation createReservation(Reservation reservation);
    List<Reservation> getAllReservations();
    Reservation getReservationById(Long id);
    Reservation updateReservation(Long id, Reservation updatedReservation);
    void deleteReservation(Long id);
}
