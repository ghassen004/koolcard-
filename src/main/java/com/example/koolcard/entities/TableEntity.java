package com.example.koolcard.entities;

import com.example.koolcard.enumeration.TableStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant_table") // Avoids reserved SQL keyword conflicts
public class TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "La capacité doit être au moins de 1")
    @Column(nullable = false)
    private int capacity;

    @NotNull(message = "Le statut de la table est requis")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TableStatus status;
}
