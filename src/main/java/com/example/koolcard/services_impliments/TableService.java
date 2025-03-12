package com.example.koolcard.services_impliments;

import com.example.koolcard.entities.TableEntity;
import com.example.koolcard.repository.TableRepository;
import com.example.koolcard.services.TableInterface;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService implements TableInterface {

    private final TableRepository tableRepository;

    public TableService(TableRepository tableRepository) {
        this.tableRepository = tableRepository;
    }

    @Override
    public TableEntity addTable(TableEntity table) {
        return tableRepository.save(table);
    }

    @Override
    public List<TableEntity> addManyTables(List<TableEntity> tables) {
        return tableRepository.saveAll(tables);  // Corrected method to save multiple tables
    }

    @Override
    public List<TableEntity> getAllTables() {
        return tableRepository.findAll();
    }

    @Override
    public TableEntity getTableById(Long id) {
        return tableRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Table not found with ID: " + id));
    }

    @Override
    public TableEntity updateTable(Long id, TableEntity updatedTable) {
        TableEntity existingTable = getTableById(id);
        existingTable.setCapacity(updatedTable.getCapacity());
        existingTable.setStatus(updatedTable.getStatus());
        return tableRepository.save(existingTable);
    }

    @Override
    public void deleteTable(Long id) {
        if (tableRepository.existsById(id)) {
            tableRepository.deleteById(id);
        } else {
            throw new RuntimeException("Table not found with ID: " + id);
        }
    }
}
