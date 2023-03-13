package com.konzerra.selim_server.domain.gate_category_advantages;

import com.konzerra.selim_server.domain.gate_category_advantages.dto.GateCategoryAdvantageResponseDto;
import com.konzerra.selim_server.domain.gate_category_advantages.dto.GateCategoryAdvantageSaveDto;
import com.konzerra.selim_server.domain.gate_category_advantages.dto.GateCategoryAdvantageUpdateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GateCategoryAdvantageMapper {
    GateCategoryAdvantage toEntity(GateCategoryAdvantageSaveDto dto);

    GateCategoryAdvantage toEntity(GateCategoryAdvantageUpdateDto dto);

    GateCategoryAdvantageResponseDto toDto(GateCategoryAdvantage entity);

    List<GateCategoryAdvantageResponseDto> toDtoList(List<GateCategoryAdvantage> entities);
}
