package com.example.koolcard.controller;

import com.example.koolcard.services_impliments.UploadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class UploadFileController {
    private final UploadFileService fileService;

    @Autowired
    public UploadFileController(UploadFileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile fileToBeUploaded) {
        return this.fileService.uploadFile(fileToBeUploaded);
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<?> downloadFile(@PathVariable String filename) {
        return this.fileService.downloadFile(filename);
    }
}
