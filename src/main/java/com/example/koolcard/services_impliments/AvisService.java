package com.example.koolcard.services.impl;

import com.example.koolcard.entities.Avis;
import com.example.koolcard.repository.AvisRepository;
import com.example.koolcard.services.AvisInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AvisService implements AvisInterface {

    @Autowired
    private AvisRepository avisRepository;

    @Override
    public Avis addAvis(Avis avis) {
        return avisRepository.save(avis);
    }

    @Override
    public void deleteAvis(Long id) {
        if (avisRepository.existsById(id)) {
            avisRepository.deleteById(id);
        } else {
            throw new RuntimeException("Avis non trouvé !");
        }
    }

    @Override
    public List<Avis> getAvisByPlat(Long platId) {
        return avisRepository.findByPlatId(platId);
    }

    @Override
    public List<Avis> getAvisByUser(Long userId) {
        return avisRepository.findByUserId(userId);
    }
}
