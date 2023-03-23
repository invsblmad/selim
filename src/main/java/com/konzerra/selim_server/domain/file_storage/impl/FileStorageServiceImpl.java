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
    private String projectsPath;
    private String newsPath;

    @PostConstruct
    private void init() {
        this.projectsPath = rootPath + "/projects";
        this.newsPath = rootPath + "/news";

        checkIfPathExists(rootPath);
        checkIfPathExists(projectsPath);
        checkIfPathExists(newsPath);
    }

    private void checkIfPathExists(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    @Override
    public String save(MultipartFile multipartFile, String folder) {
        String path = getFullPathOf(folder) + "/" + getUniqueFileName(multipartFile);
        try {
            multipartFile.transferTo(new File(path));
            return path;
        } catch (IOException e) {
            throw new FileStorageException(e.getMessage());
        }
    }

    private String getFullPathOf(String folder) {
        return switch (folder) {
            case "news" -> newsPath;
            case "projects" -> projectsPath;
            default -> null;
        };
    }

    private String getUniqueFileName(MultipartFile multipartFile) {
        String originalFileName = multipartFile.getOriginalFilename();
        assert originalFileName != null;

        return UUID.randomUUID() + getFileExtension(originalFileName);
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    @Override
    public void update(MultipartFile multipartFile, String existingPath) {
        File existingFile = new File(existingPath);
        try {
            multipartFile.transferTo(existingFile);
        } catch (IOException e) {
            throw new FileStorageException(e.getMessage());
        }
    }

    @Override
    public void delete(String path) {
        File file = new File(path);
        file.delete();
    }

}
