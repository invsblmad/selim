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
@RequestMapping("/projects")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;

    @GetMapping
    public Page<ProjectResponse> getAll(Pageable pageable) {
        return projectService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public ProjectResponse getById(@PathVariable int id) {
        return projectService.getById(id);
    }

    @PostMapping
    public ResponseEntity<ProjectResponse> save(@RequestParam("file") MultipartFile multipartFile) {
        var response = projectService.save(multipartFile);
        return ResponseEntity
                .status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ProjectResponse updateById(@PathVariable int id, @RequestParam("file") MultipartFile multipartFile) {
        return projectService.updateById(id, multipartFile);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable int id) {
        projectService.deleteById(id);
    }
}
