package com.example.koolcard.services_impliments;

import com.example.koolcard.entities.Plat;
import com.example.koolcard.repository.PlatRepository;
import com.example.koolcard.services.PlatInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlatService implements PlatInterface {

    private final PlatRepository platRepository;

    public PlatService(PlatRepository platRepository) {
        this.platRepository = platRepository;
    }

    @Override
    public Plat addPlat(Plat plat) {
        return platRepository.save(plat);
    }

    @Override
    public List<Plat> addManyPlats(List<Plat> plats) {
        return platRepository.saveAll(plats);  // Bulk save plats
    }

    @Override
    public List<Plat> getAllPlats() {
        return platRepository.findAll();
    }

    @Override
    public Plat getPlatById(Long id) {
        return platRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plat not found with ID: " + id));
    }

    @Override
    public Plat updatePlat(Long id, Plat updatedPlat) {
        Plat existingPlat = getPlatById(id);
        existingPlat.setName(updatedPlat.getName());
        existingPlat.setDescription(updatedPlat.getDescription());
        existingPlat.setPrice(updatedPlat.getPrice());
        existingPlat.setCategory(updatedPlat.getCategory()); // Update category
        return platRepository.save(existingPlat);
    }

    @Override
    public void deletePlat(Long id) {
        if (platRepository.existsById(id)) {
            platRepository.deleteById(id);
        } else {
            throw new RuntimeException("Plat not found with ID: " + id);
        }
    }
}
