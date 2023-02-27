package com.konzerra.selim_server.domain.image.impl;

import com.konzerra.selim_server.domain.image.Image;
import com.konzerra.selim_server.domain.image.ImageRepository;
import com.konzerra.selim_server.domain.image.ImageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Image update(Long id, Image image) {
        Image existingImage = findById(id);
        existingImage.setName(image.getName());
        existingImage.setContent(image.getContent());
        return imageRepository.save(existingImage);
    }

    @Override
    public String getImageUrl(Long id) {
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/images/" + id)
                .toUriString();
    }

    @Override
    public Image findById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Image not found with id: " + id));
    }
}

