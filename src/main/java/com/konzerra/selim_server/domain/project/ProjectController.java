package com.konzerra.selim_server.domain.project;

import com.konzerra.selim_server.domain.project.dto.ProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public Page<ProjectResponse> getAllProjects(Pageable pageable) {
        return projectService.getAllProjects(pageable);
    }
}
