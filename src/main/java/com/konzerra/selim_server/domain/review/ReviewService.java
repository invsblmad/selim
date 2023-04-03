package com.konzerra.selim_server.domain.review;

import com.konzerra.selim_server.domain.review.dto.ReviewResponseDto;
import com.konzerra.selim_server.domain.review.dto.ReviewSaveDto;
import com.konzerra.selim_server.domain.review.dto.ReviewUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {
    List<ReviewResponseDto> getAllReviews();
    ReviewResponseDto saveReview(ReviewSaveDto reviewSaveDto, MultipartFile image);
    ReviewResponseDto updateReview(ReviewUpdateDto reviewUpdateDto);
    void deleteReview(Long id);
    List<ReviewResponseDto> getReviewsByGateCategory(Long categoryId);
    Page<ReviewResponseDto> getPaginatedReviews(Long categoryId, int pageNumber, int pageSize, String sortDirection, String sortField);
}

