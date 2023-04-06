package com.konzerra.selim_server.domain.review.dto;

import com.konzerra.selim_server.domain.gate_category.dto.GateCategorySimpleResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String customerImage;
    private String reviewText;
    private GateCategorySimpleResponseDto gateCategory;
}
