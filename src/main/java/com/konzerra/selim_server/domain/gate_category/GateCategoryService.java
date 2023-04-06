package com.konzerra.selim_server.domain.gate_category;

import com.konzerra.selim_server.domain.gate_category.dto.GateCategoryResponseDto;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategorySaveDto;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategoryUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GateCategoryService {

    GateCategoryResponseDto save(MultipartFile image, GateCategorySaveDto dto);

    GateCategoryResponseDto update(MultipartFile image, GateCategoryUpdateDto dto);

    List<GateCategoryResponseDto> findAll();

    Page<GateCategoryResponseDto> findAllPaginated(Pageable pageable);

    GateCategoryResponseDto findById(Long id);

    void deleteById(Long id);
}

