package com.konzerra.selim_server.domain.gate_category_advantages.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GateCategoryAdvantageResponseDto {
    private Long id;
    private String title;
    private String text;

}
