package com.konzerra.selim_server.domain.project.impl;

import com.konzerra.selim_server.domain.file_storage.FileStorageService;
import com.konzerra.selim_server.domain.project.Project;
import com.konzerra.selim_server.domain.project.ProjectMapper;
import com.konzerra.selim_server.domain.project.ProjectRepository;
import com.konzerra.selim_server.domain.project.ProjectService;
import com.konzerra.selim_server.domain.project.dto.ProjectResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final FileStorageService fileStorageService;

    @Override
    public Page<ProjectResponse> getAll(Pageable pageable) {
        Page<Project> projects = projectRepository.findAll(pageable);
        return projects.map(projectMapper::entityToDto);
    }

    @Override
    public ProjectResponse getById(int id) {
        Project project = findProjectById(id);
        return projectMapper.entityToDto(project);
    }

    private Project findProjectById(int id) {
        return projectRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Project is not found with id " + id)
        );
    }

    @Override
    public ProjectResponse save(MultipartFile multipartFile) {
        String imagePath = fileStorageService.save(multipartFile, "projects");
        Project savedProject = projectRepository.save(
                new Project(imagePath, LocalDateTime.now()));
        return projectMapper.entityToDto(savedProject);
    }

    @Override
    public ProjectResponse updateById(int id, MultipartFile multipartFile) {
        Project project = findProjectById(id);
        fileStorageService.update(multipartFile, "projects",project.getImage());
        return projectMapper.entityToDto(project);
    }

    @Override
    public void deleteById(int id) {
        Project project = findProjectById(id);
        fileStorageService.delete("projects", project.getImage());
        projectRepository.delete(project);
    }
}
