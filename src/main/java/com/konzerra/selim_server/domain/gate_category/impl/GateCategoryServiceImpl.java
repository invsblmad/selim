package com.konzerra.selim_server.domain.gate_category.impl;

import com.konzerra.selim_server.domain.file_storage.FileStorageService;
import com.konzerra.selim_server.domain.gate_category.GateCategory;
import com.konzerra.selim_server.domain.gate_category.GateCategoryMapper;
import com.konzerra.selim_server.domain.gate_category.GateCategoryRepository;
import com.konzerra.selim_server.domain.gate_category.GateCategoryService;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategoryResponseDto;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategorySaveDto;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategoryUpdateDto;
import com.konzerra.selim_server.domain.gate_category_advantages.GateCategoryAdvantageMapper;
import com.konzerra.selim_server.exception.NotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GateCategoryServiceImpl implements GateCategoryService {

    private final GateCategoryRepository repository;

    private final FileStorageService fileStorageService;
    private final GateCategoryMapper mapper;

    private final GateCategoryAdvantageMapper advantageMapper;

    @Autowired
    public GateCategoryServiceImpl(GateCategoryRepository repository, FileStorageService fileStorageService, GateCategoryMapper mapper, GateCategoryAdvantageMapper advantageMapper) {
        this.repository = repository;
        this.fileStorageService = fileStorageService;
        this.mapper = mapper;
        this.advantageMapper = advantageMapper;
    }

    @Override
    public GateCategoryResponseDto save(MultipartFile image, GateCategorySaveDto dto) {
        GateCategory entity = mapper.toEntity(dto);
        entity.setImage(fileStorageService.save(image));
        entity.setAdvantages(dto.getAdvantages()
                .stream()
                .map(advantageMapper::toEntity)
                .collect(Collectors.toList()));
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
    public GateCategoryResponseDto update(MultipartFile image, GateCategoryUpdateDto dto) {
        GateCategory entity = repository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("GateCategory not found with id " + dto.getId()));
        mapper.toEntity(dto);
        if(image != null){
            fileStorageService.update(image,entity.getImage());
        }
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }

    @Override
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
        GateCategory gateCategory = repository.findById(id).orElseThrow(() -> new NotFoundException("Gate Category not found with id: "+ id));
        fileStorageService.delete(gateCategory.getImage());
        repository.delete(gateCategory);
    }
}
