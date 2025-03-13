package com.example.koolcard.services_impliments;

import com.example.koolcard.entities.UploadFile;
import com.example.koolcard.repository.UploadFileRepository;
import com.example.koolcard.services.UploadFileInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.Optional;

@Service
public class UploadFileService implements UploadFileInterface {
    private final UploadFileRepository uploadFileRepository;

    @Autowired
    public UploadFileService(UploadFileRepository uploadFileRepository) {
        this.uploadFileRepository = uploadFileRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UploadFileService.class);

    @Override
    public ResponseEntity<?> uploadFile(MultipartFile fileToBeUploaded) {
        try {
            if (!this.fileExists(fileToBeUploaded.getOriginalFilename())) {
                UploadFile file = new UploadFile();
                file.setFilename(fileToBeUploaded.getOriginalFilename());
                file.setContentType(fileToBeUploaded.getContentType());
                file.setSize(fileToBeUploaded.getSize());
                file.setData(fileToBeUploaded.getBytes());

                UploadFile savedFile = this.uploadFileRepository.save(file);

                return new ResponseEntity<>("File Uploaded Successfully: " + savedFile.getFilename(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("File already exists", HttpStatus.CONFLICT);
            }
        } catch (IOException e) {
            LOGGER.error("Error getting data from file", e);
            return new ResponseEntity<>("Error when getting data from file", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> downloadFile(String filename) {
        Optional<UploadFile> optionalFile = this.uploadFileRepository.findByFilename(filename);

        if (optionalFile.isPresent()) {
            UploadFile file = optionalFile.get();

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(file.getContentType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                    .body(file.getData());
        } else {
            return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
        }
    }

    private boolean fileExists(String filename) {
        return uploadFileRepository.existsByFilename(filename);
    }
}
