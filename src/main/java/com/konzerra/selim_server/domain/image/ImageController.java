package com.konzerra.selim_server.domain.image;


import com.konzerra.selim_server.domain.file_storage.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@RestController

public class ImageController {

    private final FileStorageService fileStorageService;

    @Autowired
    public ImageController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }



    @GetMapping(ImageApi.getByName)
    public ResponseEntity<Resource> getImage(@PathVariable String folderName,@PathVariable String fileName) throws IOException {
        File file = this.fileStorageService.findByName(fileName, folderName);
        if (!file.exists() || !file.isFile()) {
            throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
        }

        InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        headers.setContentLength(file.length());
        headers.set("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");

        return new ResponseEntity<Resource>(inputStreamResource, headers, HttpStatus.OK);
    }


}

