package com.unchk.backend.uploads.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
@CrossOrigin("*")
public class FileUploadController {

    private static final String UPLOAD_DIR =
            "uploads";

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(

            @RequestParam("file")
            MultipartFile file

    ) throws IOException {

        if (file.isEmpty()) {

            return ResponseEntity.badRequest()

                    .body(
                            "Aucun fichier sélectionné"
                    );
        }

        Path uploadPath =

                Paths.get(
                        UPLOAD_DIR
                );

        if (

                !Files.exists(
                        uploadPath
                )

        ) {

            Files.createDirectories(
                    uploadPath
            );
        }

        String originalFileName =

                file.getOriginalFilename();

        String extension = "";

        if (

                originalFileName != null

                        &&

                        originalFileName.contains(".")

        ) {

            extension =

                    originalFileName.substring(

                            originalFileName.lastIndexOf(".")
                    );
        }

        String fileName =

                UUID.randomUUID()

                        + extension;

        Path filePath =

                uploadPath.resolve(
                        fileName
                );

        Files.copy(

                file.getInputStream(),

                filePath,

                StandardCopyOption.REPLACE_EXISTING
        );

        return ResponseEntity.ok(

                "/uploads/" + fileName
        );
    }
}