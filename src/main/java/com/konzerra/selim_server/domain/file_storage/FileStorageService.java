package com.konzerra.selim_server.domain.file_storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileStorageService {
    String save(MultipartFile file);

    File findByName(String fileName);
    void update(MultipartFile newFile, String fileName);
    void delete(String fileName);
}
