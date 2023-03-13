package com.konzerra.selim_server.domain.file_storage;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String save(MultipartFile multipartFile, String folder);
    void update(MultipartFile multipartFile, String existingPath);
    void delete(String path);
}
