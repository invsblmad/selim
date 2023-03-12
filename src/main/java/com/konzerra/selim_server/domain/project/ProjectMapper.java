package com.konzerra.selim_server.domain.project;

import com.konzerra.selim_server.domain.project.dto.ProjectDto;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDto projectEntityToDto(Project project);
}
