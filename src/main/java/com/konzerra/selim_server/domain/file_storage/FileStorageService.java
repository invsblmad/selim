package com.konzerra.selim_server.domain.file_storage;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileStorageService {
    String save(MultipartFile multipartFile, String folder);

    File findByName(String fileName, String folderName);
    void update(MultipartFile multipartFile, String folderName, String fileName);
    void delete(String folderName, String fileName);
}
