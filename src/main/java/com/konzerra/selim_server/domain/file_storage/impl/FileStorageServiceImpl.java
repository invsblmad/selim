package com.konzerra.selim_server.domain.file_storage.impl;

import com.konzerra.selim_server.exception.FileStorageException;
import com.konzerra.selim_server.domain.file_storage.FileStorageService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    @Value("${file.storage.path}")
    private String rootPath;

    @PostConstruct
    private void init() {
        File file = new File(rootPath);
        if (!file.exists()) {
            System.out.println(rootPath);
            file.mkdirs();
        }
    }

    @Override
    public String save(MultipartFile file) {
        String fileName = getUniqueFileName(file);
        String path = getFilePath(fileName);
        try {
            file.transferTo(new File(path));
            return fileName;
        } catch (IOException e) {
            throw new FileStorageException(e.getMessage());
        }
    }

    private String getUniqueFileName(MultipartFile multipartFile) {
        String originalFileName = multipartFile.getOriginalFilename();
        assert originalFileName != null;

        return UUID.randomUUID() + getFileExtension(originalFileName);
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private String getFilePath(String fileName) {
        return rootPath + "/" + fileName;
    }

    @Override
    public File findByName(String fileName) {
        try {
            String path = getFilePath(fileName);
            return new File(path);
        } catch (Exception e) {
            throw new FileStorageException(e.getMessage());
        }
    }

    @Override
    public void update(MultipartFile newFile, String fileName) {
        File existingFile = findByName(fileName);
        try {
            newFile.transferTo(existingFile);
        } catch (IOException e) {
            throw new FileStorageException(e.getMessage());
        }
    }

    @Override
    public void delete(String fileName) {
        File file = findByName(fileName);
        file.delete();
    }

}
