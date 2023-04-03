package com.konzerra.selim_server.domain.review.impl;


import com.konzerra.selim_server.domain.file_storage.FileStorageService;
import com.konzerra.selim_server.domain.gate_category.GateCategoryRepository;
import com.konzerra.selim_server.domain.review.Review;
import com.konzerra.selim_server.domain.review.ReviewMapper;
import com.konzerra.selim_server.domain.review.ReviewRepository;
import com.konzerra.selim_server.domain.review.ReviewService;
import com.konzerra.selim_server.domain.review.dto.ReviewResponseDto;
import com.konzerra.selim_server.domain.review.dto.ReviewSaveDto;
import com.konzerra.selim_server.domain.review.dto.ReviewUpdateDto;
import com.konzerra.selim_server.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final FileStorageService fileStorageService;

    private final GateCategoryRepository gateCategoryRepository;
    private final ReviewMapper mapper;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, FileStorageService fileStorageService, GateCategoryRepository gateCategoryRepository, ReviewMapper mapper) {
        this.reviewRepository = reviewRepository;
        this.fileStorageService = fileStorageService;
        this.gateCategoryRepository = gateCategoryRepository;
        this.mapper = mapper;
    }

    @Override
    public List<ReviewResponseDto> getAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return mapper.toDtoList(reviews);
    }

    @Override
    public ReviewResponseDto saveReview(ReviewSaveDto reviewSaveDto, MultipartFile image) {
        String filename = fileStorageService.save(image,"review");
        Review review = mapper.toEntity(reviewSaveDto);
        review.setCustomerImage(filename);
        Review savedReview = reviewRepository.save(review);
        return mapper.toDto(savedReview);
    }

    @Override
    public ReviewResponseDto updateReview(ReviewUpdateDto reviewUpdateDto) {
        Review review = reviewRepository.findById(reviewUpdateDto.getId())
                .orElseThrow(() -> new NotFoundException("Review not found with id: "+ reviewUpdateDto.getId()));
        review.setFirstName(reviewUpdateDto.getFirstName());
        review.setLastName(reviewUpdateDto.getLastName());
        review.setCustomerImage(reviewUpdateDto.getCustomerImage());
        review.setReviewText(reviewUpdateDto.getReviewText());
        review.setGateCategory(gateCategoryRepository.findById(reviewUpdateDto.getGateCategoryId())
                .orElseThrow(() -> new NotFoundException("GateCategory not found with id: "+ reviewUpdateDto.getGateCategoryId())));
        Review updatedReview = reviewRepository.save(review);
        return mapper.toDto(updatedReview);
    }

    @Override
    public void deleteReview(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Review not found with id: "+ id));
        reviewRepository.delete(review);
    }

    @Override
    public List<ReviewResponseDto> getReviewsByGateCategory(Long gateCategoryId) {
        List<Review> reviews = reviewRepository.findAllByGateCategory_Id(gateCategoryId);
        return mapper.toDtoList(reviews);
    }

    @Override
    public Page<ReviewResponseDto> getPaginatedReviews(Long categoryId, int pageNumber, int pageSize, String sortDirection, String sortField) {
        Sort sort = Sort.by(
                sortDirection.equals("ascending") ? Sort.Direction.ASC : Sort.Direction.DESC,
                sortField);
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Review> page = reviewRepository.findAllByGateCategory_Id(categoryId,pageable);
        return page.map(mapper::toDto);
    }
}
