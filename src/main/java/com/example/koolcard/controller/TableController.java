package com.example.koolcard.controller;

import com.example.koolcard.entities.TableEntity;
import com.example.koolcard.services.TableInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class TableController {

    private final TableInterface tableService;

    public TableController(TableInterface tableService) {
        this.tableService = tableService;
    }

    @PostMapping("/add")
    public ResponseEntity<TableEntity> addTable(@RequestBody TableEntity table) {
        return ResponseEntity.ok(tableService.addTable(table));
    }

    @PostMapping("/addmany")  // New endpoint to add multiple tables
    public ResponseEntity<List<TableEntity>> addManyTables(@RequestBody List<TableEntity> tables) {
        return ResponseEntity.ok(tableService.addManyTables(tables));  // Corrected the method name here
    }

    @GetMapping("/all")
    public ResponseEntity<List<TableEntity>> getAllTables() {
        return ResponseEntity.ok(tableService.getAllTables());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TableEntity> getTableById(@PathVariable Long id) {
        return ResponseEntity.ok(tableService.getTableById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TableEntity> updateTable(@PathVariable Long id, @RequestBody TableEntity table) {
        return ResponseEntity.ok(tableService.updateTable(id, table));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTable(@PathVariable Long id) {
        tableService.deleteTable(id);
        return ResponseEntity.noContent().build();
    }
}
