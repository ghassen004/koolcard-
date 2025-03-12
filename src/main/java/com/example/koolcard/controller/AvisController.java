package com.example.koolcard.controller;

import com.example.koolcard.entities.Avis;
import com.example.koolcard.services.AvisInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/avis")
public class AvisController {

    @Autowired
    private AvisInterface avisInterface;

    @PostMapping("/add")
    public ResponseEntity<Avis> addAvis(@RequestBody @Valid Avis avis) {
        return ResponseEntity.ok(avisInterface.addAvis(avis));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAvis(@PathVariable Long id) {
        avisInterface.deleteAvis(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/plat/{platId}")
    public ResponseEntity<List<Avis>> getAvisByPlat(@PathVariable Long platId) {
        return ResponseEntity.ok(avisInterface.getAvisByPlat(platId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Avis>> getAvisByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(avisInterface.getAvisByUser(userId));
    }
}
