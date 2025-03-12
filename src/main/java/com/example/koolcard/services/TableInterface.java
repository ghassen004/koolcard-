package com.example.koolcard.services;

import com.example.koolcard.entities.TableEntity;
import java.util.List;

public interface TableInterface {
    TableEntity addTable(TableEntity table);
    List<TableEntity> addManyTables(List<TableEntity> tables);  // Corrected method name
    List<TableEntity> getAllTables();
    TableEntity getTableById(Long id);
    TableEntity updateTable(Long id, TableEntity updatedTable);
    void deleteTable(Long id);
}
