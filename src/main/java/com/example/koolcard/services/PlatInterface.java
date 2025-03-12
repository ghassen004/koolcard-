package com.example.koolcard.services;

import com.example.koolcard.entities.Plat;
import java.util.List;

public interface PlatInterface {
    Plat addPlat(Plat plat);
    List<Plat> addManyPlats(List<Plat> plats);
    List<Plat> getAllPlats();
    Plat getPlatById(Long id);
    Plat updatePlat(Long id, Plat updatedPlat);
    void deletePlat(Long id);
}
