package com.konzerra.selim_server.domain.gate_category_advantages.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GateCategoryAdvantageSaveDto {
    private String title;
    private String text;
}
