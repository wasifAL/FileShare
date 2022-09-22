package com.wsf.file.share;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
@RestController
public class FileShare {
    public static void main(String[] args) {
        SpringApplication.run(FileShare.class, args);
    }
    public static String folderPath =  "//";
    public static Path filePath = Paths.get(folderPath);

    @GetMapping({
            "/files",
            "/files/",
    })
    public ResponseEntity<String[]> getListFiles() {
        return ResponseEntity.status(HttpStatus.OK).body(new File(folderPath).list());
    }
    @GetMapping({
            "/files/{id}",
    })
    public ResponseEntity<String[]> getListFileById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(new File(folderPath).list());
    }

    @PostMapping("/files/")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            createDirIfNotExist();
            byte[] bytes = new byte[0];
            bytes = file.getBytes();
            Files.write(Paths.get(folderPath + file.getOriginalFilename()), bytes);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("Files uploaded successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body("Exception occurred for: " + file.getOriginalFilename() + "!");
        }
    }

    /**
     * Create directory to save files, if not exist
     */
    private void createDirIfNotExist() {
        File directory = new File(folderPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }
}