package com.example.koolcard.repository;

import com.example.koolcard.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Custom queries can be added here if needed
}
