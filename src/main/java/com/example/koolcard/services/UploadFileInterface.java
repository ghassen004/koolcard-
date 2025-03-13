package com.example.koolcard.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface UploadFileInterface {


    ResponseEntity<?> uploadFile(MultipartFile  fileToBeUploaded);
    ResponseEntity<?> downloadFile(String filename);
}
