package com.konzerra.selim_server.domain.projects;

import com.konzerra.selim_server.domain.projects.dto.ProjectDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDto projectEntityToDto(Project project);
}
