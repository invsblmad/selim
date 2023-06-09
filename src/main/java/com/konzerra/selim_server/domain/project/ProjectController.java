package com.konzerra.selim_server.domain.project;

import com.konzerra.selim_server.domain.project.dto.ProjectResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping("/public/projects")
    public Page<ProjectResponse> getAll(Pageable pageable) {
        return projectService.getAll(pageable);
    }

    @GetMapping("/public/projects/{id}")
    public ProjectResponse getById(@PathVariable int id) {
        return projectService.getById(id);
    }

    @PostMapping("/protected/projects")
    public ResponseEntity<ProjectResponse> save(@RequestPart("image") MultipartFile image) {
        var response = projectService.save(image);
        return ResponseEntity
                .status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/protected/projects/{id}")
    public ProjectResponse updateById(@PathVariable int id, @RequestPart("image") MultipartFile image) {
        return projectService.updateById(id, image);
    }

    @DeleteMapping("/protected/projects/{id}")
    public void deleteById(@PathVariable int id) {
        projectService.deleteById(id);
    }
}
