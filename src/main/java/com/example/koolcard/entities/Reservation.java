package com.example.koolcard.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Number of people is required")
    @Column(nullable = false)
    private int numberOfPeople;

    @NotNull(message = "Reservation start date and time is required")
    @Column(nullable = false)
    private LocalDateTime reservationStartDateTime;  // Start time of the reservation

    // Link to TableEntity (Many-to-One)
    @ManyToOne
    @JoinColumn(name = "table_id", nullable = false)
    private TableEntity table;

}
