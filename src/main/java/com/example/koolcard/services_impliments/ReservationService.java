package com.example.koolcard.services_impliments;

import com.example.koolcard.entities.Reservation;
import com.example.koolcard.repository.ReservationRepository;
import com.example.koolcard.services.ReservationInterface;
import com.example.koolcard.entities.TableEntity;
import com.example.koolcard.enumeration.TableStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService implements ReservationInterface {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        // Get the table to be reserved
        TableEntity table = reservation.getTable();

        // Check if the table is already reserved or occupied
        if (table.getStatus() == TableStatus.RESERVED || table.getStatus() == TableStatus.OCCUPIED) {
            throw new IllegalStateException("This table is not available for reservation.");
        }

        // Set the table's status to "RESERVED"
        table.setStatus(TableStatus.RESERVED);

        // Save the updated table entity
        reservation.setTable(table);

        // Save the reservation
        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found with ID: " + id));
    }

    @Override
    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        Reservation existingReservation = getReservationById(id);
        existingReservation.setNumberOfPeople(updatedReservation.getNumberOfPeople());
        existingReservation.setReservationStartDateTime(updatedReservation.getReservationStartDateTime());
        return reservationRepository.save(existingReservation);
    }

    @Override
    public void deleteReservation(Long id) {
        if (reservationRepository.existsById(id)) {
            // Get the reservation to be deleted
            Reservation reservation = reservationRepository.findById(id).get();
            TableEntity table = reservation.getTable();

            // Reset the table status to "AVAILABLE"
            table.setStatus(TableStatus.AVAILABLE);
            reservationRepository.deleteById(id);
        } else {
            throw new RuntimeException("Reservation not found with ID: " + id);
        }
    }
}
