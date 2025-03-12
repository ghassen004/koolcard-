package com.example.koolcard.controller;

import com.example.koolcard.entities.Plat;
import com.example.koolcard.services.PlatInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plats")
public class PlatController {

    private final PlatInterface platService;

    public PlatController(PlatInterface platService) {
        this.platService = platService;
    }

    @PostMapping("/add")
    public ResponseEntity<Plat> addPlat(@RequestBody Plat plat) {
        return ResponseEntity.ok(platService.addPlat(plat));
    }

    @PostMapping("/addmany")
    public ResponseEntity<List<Plat>> addManyPlats(@RequestBody List<Plat> plats) {
        return ResponseEntity.ok(platService.addManyPlats(plats));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Plat>> getAllPlats() {
        return ResponseEntity.ok(platService.getAllPlats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Plat> getPlatById(@PathVariable Long id) {
        return ResponseEntity.ok(platService.getPlatById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Plat> updatePlat(@PathVariable Long id, @RequestBody Plat plat) {
        return ResponseEntity.ok(platService.updatePlat(id, plat));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlat(@PathVariable Long id) {
        platService.deletePlat(id);
        return ResponseEntity.noContent().build();
    }
}
