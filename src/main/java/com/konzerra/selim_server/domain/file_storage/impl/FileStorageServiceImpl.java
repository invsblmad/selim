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

    private String gateCategory;
    private String gate;
    private String review;

    @PostConstruct
    private void init() {
        this.projectsPath = rootPath + "/projects";
        this.newsPath = rootPath + "/news";
        this.gateCategory = rootPath + "/gate_category";
        this.gate = rootPath + "/gate";
        this.review = rootPath + "/review";

        checkIfPathExists(rootPath);
        checkIfPathExists(projectsPath);
        checkIfPathExists(newsPath);
        checkIfPathExists(gateCategory);
        checkIfPathExists(gate);
        checkIfPathExists(review);

    }

    private void checkIfPathExists(String path) {
        File file = new File(path);
        if (!file.exists()) {
            System.out.println(path);
            file.mkdirs();
        }
    }

    /*
    returns file name
     */
    @Override
    public String save(MultipartFile multipartFile, String folder) {
        String fileName = getUniqueFileName(multipartFile);
        String path = getFullPathOf(folder) + "/" + fileName;
        try {
            multipartFile.transferTo(new File(path));
            return fileName;
        } catch (IOException e) {
            throw new FileStorageException(e.getMessage());
        }
    }

    @Override
    public File findByName(String fileName, String folder) {
        File existingFile = null;
        try {
            existingFile = new File(rootPath+"/"+folder+"/"+fileName);
            System.out.println("cathching file");
        } catch (Exception e) {
            throw new FileStorageException(e.getMessage());
        }
        return existingFile;
    }

    private String getFullPathOf(String folder) {
        return switch (folder) {
            case "news" -> newsPath;
            case "projects" -> projectsPath;
            case "gate_category" -> gateCategory;
            case "gate" -> gate;
            case "review" -> review;
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
    public void update(
            MultipartFile multipartFile,
            String folderName,
            String fileName
    ) {
        String path = rootPath + "/" + folderName +"/"+fileName;
        File existingFile = new File(path);
        try {
            multipartFile.transferTo(existingFile);
        } catch (IOException e) {
            throw new FileStorageException(e.getMessage());
        }
    }

    @Override
    public void delete(String folderName, String fileName) {
        String path = rootPath + "/" + folderName +"/"+fileName;
        File file = new File(path);
        file.delete();
    }

}
