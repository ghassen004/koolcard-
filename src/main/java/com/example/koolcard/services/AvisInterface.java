package com.example.koolcard.services;

import com.example.koolcard.entities.Avis;
import java.util.List;

public interface AvisInterface {
    Avis addAvis(Avis avis);
    void deleteAvis(Long id);
    List<Avis> getAvisByPlat(Long platId);
    List<Avis> getAvisByUser(Long userId);
}
