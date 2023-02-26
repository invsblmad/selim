package com.konzerra.selim_server.domain.gate_category;

import com.konzerra.selim_server.domain.gate_category.dto.GateCategoryResponseDto;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategorySaveDto;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategoryUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GateCategoryService {

    GateCategoryResponseDto save(GateCategorySaveDto dto);

    GateCategoryResponseDto update(GateCategoryUpdateDto dto);

    List<GateCategoryResponseDto> findAll();

    Page<GateCategoryResponseDto> findAllPaginated(Pageable pageable);

    GateCategoryResponseDto findById(Long id);

    void deleteById(Long id);
}

