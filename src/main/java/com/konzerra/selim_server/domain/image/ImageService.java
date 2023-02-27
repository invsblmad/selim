package com.konzerra.selim_server.domain.image;

public interface ImageService {
    Image save(Image image);

    Image update(Long id, Image image);

    String getImageUrl(Long id);

    Image findById(Long id);
}

