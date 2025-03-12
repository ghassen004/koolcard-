package com.example.koolcard.repository;

import com.example.koolcard.entities.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AvisRepository extends JpaRepository<Avis, Long> {
    List<Avis> findByPlatId(Long platId);
    List<Avis> findByUserId(Long userId);
}
