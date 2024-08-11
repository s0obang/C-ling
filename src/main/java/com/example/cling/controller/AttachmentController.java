package com.example.cling.controller;

import com.example.cling.entity.Attachment;
import com.example.cling.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class AttachmentController {

    private final AttachmentService attachmentService;

    @Autowired
    public AttachmentController(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadFile(@PathVariable("id") int id) {
        Attachment attachment = attachmentService.getAttachmentById(id);
        Path filePath = Paths.get(attachment.getAttachmentPath()).toAbsolutePath().normalize();

        Resource resource;
        try {
            resource = new UrlResource(filePath.toUri());
            if (!resource.exists()) {
                throw new RuntimeException("File not found: " + attachment.getOriginAttachmentName());
            }
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Error in file URL: " + attachment.getOriginAttachmentName(), ex);
        }

        String contentType;
        try {
            contentType = Files.probeContentType(filePath);
        } catch (IOException ex) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(org.springframework.http.MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getOriginAttachmentName() + "\"")
                .body(resource);
    }
}
