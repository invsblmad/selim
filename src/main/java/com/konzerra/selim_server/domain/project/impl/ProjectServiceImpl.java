package com.konzerra.selim_server.domain.project.impl;

import com.konzerra.selim_server.domain.project.Project;
import com.konzerra.selim_server.domain.project.ProjectMapper;
import com.konzerra.selim_server.domain.project.ProjectRepository;
import com.konzerra.selim_server.domain.project.ProjectService;
import com.konzerra.selim_server.domain.project.dto.ProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Override
    public Page<ProjectResponse> getAllProjects(Pageable pageable) {
        Page<Project> projects = projectRepository.findAll(pageable);
        return projects.map(projectMapper::projectEntityToDto);
    }
}
