package com.konzerra.selim_server.domain.project;

import com.konzerra.selim_server.domain.project.dto.ProjectResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface ProjectService {
    Page<ProjectResponse> getAll(Pageable pageable);
    ProjectResponse getById(int id);
    ProjectResponse save(MultipartFile multipartFile);
    ProjectResponse updateById(int id, MultipartFile multipartFile);
    void deleteById(int id);
}
