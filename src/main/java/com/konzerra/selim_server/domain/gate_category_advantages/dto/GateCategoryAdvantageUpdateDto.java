package com.konzerra.selim_server.domain.gate_category_advantages.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GateCategoryAdvantageUpdateDto {
    private Long id;
    private String title;
    private String text;
}
