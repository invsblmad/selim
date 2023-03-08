package com.konzerra.selim_server.domain.projects.impl;

import com.konzerra.selim_server.domain.projects.Project;
import com.konzerra.selim_server.domain.projects.ProjectMapper;
import com.konzerra.selim_server.domain.projects.dto.ProjectDto;

public class ProjectMapperImpl implements ProjectMapper {
    @Override
    public ProjectDto mapToDto(Project project) {
        return new ProjectDto(project.getId(), project.getImage());
    }
}
