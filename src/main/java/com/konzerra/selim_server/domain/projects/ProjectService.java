package com.konzerra.selim_server.domain.projects;

import com.konzerra.selim_server.domain.projects.dto.ProjectDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {
    Page<ProjectDto> getAllProjects(Pageable pageable);
}
