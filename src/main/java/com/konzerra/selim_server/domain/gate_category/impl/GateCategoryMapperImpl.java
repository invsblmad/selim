package com.konzerra.selim_server.domain.gate_category.impl;

import com.konzerra.selim_server.domain.gate_category.GateCategory;
import com.konzerra.selim_server.domain.gate_category.GateCategoryMapper;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategoryResponseDto;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategorySaveDto;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategoryUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GateCategoryMapperImpl implements GateCategoryMapper {

    @Override
    public GateCategory toEntity(GateCategorySaveDto dto) {
        GateCategory entity = new GateCategory();
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    @Override
    public GateCategory toEntity(GateCategoryUpdateDto dto) {
        GateCategory entity = new GateCategory();
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    @Override
    public GateCategoryResponseDto toDto(GateCategory entity) {
        GateCategoryResponseDto dto = new GateCategoryResponseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setDescription(entity.getDescription());
        return dto;
    }

    @Override
    public List<GateCategoryResponseDto> toDtoList(List<GateCategory> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
