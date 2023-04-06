package com.konzerra.selim_server.domain.gate_category;

import com.konzerra.selim_server.domain.gate_category.dto.GateCategoryResponseDto;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategorySaveDto;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategorySimpleResponseDto;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategoryUpdateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GateCategoryMapper {

    GateCategory toEntity(GateCategorySaveDto dto);

    GateCategory toEntity(GateCategoryUpdateDto dto);

    GateCategoryResponseDto toDto(GateCategory entity);

    GateCategorySimpleResponseDto toSimpleDto(GateCategory entity);

    List<GateCategoryResponseDto> toDtoList(List<GateCategory> entities);
}
