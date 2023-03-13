package com.konzerra.selim_server.domain.gate.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GateSaveDto {
    private String name;
    private String image;
    private Long categoryId;

}

