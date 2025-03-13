package com.example.koolcard.repository;


import com.example.koolcard.entities.UploadFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Optional;

@Repository
public interface UploadFileRepository extends JpaRepository<UploadFile, Long> {
    Optional<UploadFile> findById(Long id);
    Optional<UploadFile> findByFilename(String filename);
    boolean existsByFilename(String filename);
}
