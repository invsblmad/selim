package com.konzerra.selim_server.domain.gate;

import com.konzerra.selim_server.domain.gate.dto.GateResponseDto;
import com.konzerra.selim_server.domain.gate.dto.GateSaveDto;
import com.konzerra.selim_server.domain.gate.dto.GateUpdateDto;
import com.konzerra.selim_server.domain.gate_category.GateCategory;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategoryResponseDto;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategorySaveDto;
import com.konzerra.selim_server.domain.gate_category.dto.GateCategoryUpdateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GateMapper {
    Gate toEntity(GateSaveDto dto);

    Gate toEntity(GateUpdateDto dto);

    GateResponseDto toDto(Gate entity);

    List<GateResponseDto> toDtoList(List<Gate> entities);
}
