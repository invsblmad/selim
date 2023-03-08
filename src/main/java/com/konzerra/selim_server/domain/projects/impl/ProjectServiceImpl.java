package com.konzerra.selim_server.domain.projects.impl;

import com.konzerra.selim_server.domain.projects.Project;
import com.konzerra.selim_server.domain.projects.ProjectMapper;
import com.konzerra.selim_server.domain.projects.ProjectRepository;
import com.konzerra.selim_server.domain.projects.ProjectService;
import com.konzerra.selim_server.domain.projects.dto.ProjectDto;
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
    public Page<ProjectDto> getAllProjects(Pageable pageable) {
        Page<Project> projects = projectRepository.findAll(pageable);
        return projects.map(projectMapper::projectEntityToDto);
    }
}
