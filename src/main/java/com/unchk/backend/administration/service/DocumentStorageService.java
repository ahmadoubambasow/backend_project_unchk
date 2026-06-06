package com.unchk.backend.administration.service;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.nio.file.StandardCopyOption;

import java.util.UUID;

@Service
public class DocumentStorageService {

    @Value("${app.upload.documents-dir}")
    private String uploadDir;

    public String storeDocument(

            MultipartFile file

    ) throws IOException {

        if (file.isEmpty()) {

            throw new RuntimeException(
                    "Fichier vide"
            );
        }

        Path uploadPath =

                Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {

            Files.createDirectories(
                    uploadPath
            );
        }

        String originalFilename =

                file.getOriginalFilename();

        String extension = "";

        if (

                originalFilename != null

                        &&

                        originalFilename.contains(".")

        ) {

            extension =

                    originalFilename.substring(

                            originalFilename.lastIndexOf(".")
                    );
        }

        String fileName =

                UUID.randomUUID()

                        + extension;

        Path destination =

                uploadPath.resolve(
                        fileName
                );

        Files.copy(

                file.getInputStream(),

                destination,

                StandardCopyOption.REPLACE_EXISTING
        );

        return fileName;
    }
}