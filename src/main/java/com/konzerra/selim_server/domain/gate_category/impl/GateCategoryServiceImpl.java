package com.konzerra.selim_server.domain.gate_category.impl;

import com.konzerra.selim_server.domain.gate_category.GateCategory;
import com.konzerra.selim_server.domain.gate_category.GateCategoryMapper;
import com.konzerra.selim_server.domain.gate_category.GateCategoryRepository;
import com.konzerra.selim_server.domain.gate_category.GateCategoryService;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategoryResponseDto;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategorySaveDto;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategoryUpdateDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GateCategoryServiceImpl implements GateCategoryService {

    private final GateCategoryRepository repository;

    private final GateCategoryMapper mapper;

    public GateCategoryServiceImpl(GateCategoryRepository repository, GateCategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public GateCategoryResponseDto save(GateCategorySaveDto dto) {
        GateCategory entity = mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public GateCategoryResponseDto update(GateCategoryUpdateDto dto) {
        GateCategory entity = repository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("GateCategory not found with id " + dto.getId()));
        mapper.toEntity(dto);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    public List<GateCategoryResponseDto> findAll() {
        List<GateCategory> entities = repository.findAll();
        return mapper.toDtoList(entities);
    }

    @Override
    public Page<GateCategoryResponseDto> findAllPaginated(Pageable pageable) {
        Page<GateCategory> page = repository.findAll(pageable);
        return page.map(mapper::toDto);
    }

    public GateCategoryResponseDto findById(Long id) {
        GateCategory entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("GateCategory not found with id " + id));
        return mapper.toDto(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
