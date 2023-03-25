package com.konzerra.selim_server.domain.review;

import com.konzerra.selim_server.domain.review.dto.ReviewResponseDto;
import com.konzerra.selim_server.domain.review.dto.ReviewSaveDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toEntity(ReviewSaveDto dto);

    Review toEntity(ReviewResponseDto dto);

    ReviewResponseDto toDto(Review entity);

    List<ReviewResponseDto> toDtoList(List<Review> entities);
}
