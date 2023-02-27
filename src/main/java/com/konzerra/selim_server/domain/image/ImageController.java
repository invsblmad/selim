package com.konzerra.selim_server.domain.image;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController

public class ImageController {
    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(ImageApi.save)
    public ResponseEntity<Image> saveImage(@RequestParam("file") MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setContent(file.getBytes());
        image = imageService.save(image);
        String imageUrl = imageService.getImageUrl(image.getId());
        image.setName(imageUrl);
        return ResponseEntity.ok(imageService.update(image.getId(), image));
    }

    @PutMapping(ImageApi.update)
    public ResponseEntity<Image> updateImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) throws IOException {
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        image.setContent(file.getBytes());
        image = imageService.update(id, image);
        String imageUrl = imageService.getImageUrl(image.getId());
        image.setName(imageUrl);
        return ResponseEntity.ok(imageService.update(image.getId(), image));
    }

    @GetMapping(ImageApi.getById)
    public ResponseEntity<Resource> getImage(@PathVariable Long id) {
        Image image = imageService.findById(id);
        ByteArrayResource resource = new ByteArrayResource(image.getContent());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                .body(resource);
    }
}

