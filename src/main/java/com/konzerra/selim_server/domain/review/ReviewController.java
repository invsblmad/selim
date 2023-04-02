package com.konzerra.selim_server.domain.review;

import com.konzerra.selim_server.domain.file_storage.FileStorageService;
import com.konzerra.selim_server.domain.review.dto.ReviewResponseDto;
import com.konzerra.selim_server.domain.review.dto.ReviewSaveDto;
import com.konzerra.selim_server.domain.review.dto.ReviewUpdateDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ReviewController {
    private final ReviewService reviewService;
    private final FileStorageService fileStorageService;

    @Autowired
    public ReviewController(ReviewService reviewService, FileStorageService fileStorageService) {
        this.reviewService = reviewService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping(ReviewApi.getAll)
    public List<ReviewResponseDto> getAllGates() {
        return reviewService.getAllReviews();
    }

    @PostMapping(value = ReviewApi.save,consumes = "multipart/form-data")
    public ResponseEntity<ReviewResponseDto> saveGate(@RequestPart("file") MultipartFile file, @RequestPart("reviewSaveDto") ReviewSaveDto reviewSaveDto) {
        reviewService.saveReview(reviewSaveDto);
        fileStorageService.save(file,"review");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(ReviewApi.update)
    public ReviewResponseDto updateGate(@RequestBody ReviewUpdateDto reviewUpdateDto) {
        return reviewService.updateReview(reviewUpdateDto);
    }

    @DeleteMapping(ReviewApi.deleteById)
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(ReviewApi.getAllByCategoryId)
    public List<ReviewResponseDto> getReviewsByCategory(@PathVariable Long categoryId) {
        return reviewService.getReviewsByGateCategory(categoryId);
    }

    @GetMapping(ReviewApi.getAllPaginated)
    public Page<ReviewResponseDto> getPaginatedGates(
            @PathVariable Long categoryId,
            @PathVariable int pageNumber,
            @PathVariable int pageSize,
            @PathVariable String sortDirection,
            @PathVariable String sortField
    ) {
        return reviewService.getPaginatedReviews(categoryId,pageNumber, pageSize, sortDirection, sortField);
    }
}
