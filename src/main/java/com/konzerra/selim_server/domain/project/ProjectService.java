package com.konzerra.selim_server.domain.project;

import com.konzerra.selim_server.domain.project.dto.ProjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProjectService {
    Page<ProjectResponse> getAllProjects(Pageable pageable);
}
