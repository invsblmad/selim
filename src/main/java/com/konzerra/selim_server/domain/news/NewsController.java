package com.konzerra.selim_server.domain.news;

import com.konzerra.selim_server.domain.news.dto.NewsDetailsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/public/news")
    public Page<NewsResponse> getAll(Pageable pageable) {
        return newsService.getAll(pageable);
    }

    @GetMapping("/public/news/{id}")
    public NewsDetailsResponse getById(@PathVariable int id) {
        return newsService.getById(id);
    }

    @GetMapping("/public/news/{id}/similar-news")
    public List<NewsResponse> getSimilarById(@PathVariable int id) {
        return newsService.getSimilarById(id);
    }

    @PostMapping("/protected/news")
    public ResponseEntity<NewsDetailsResponse> save(@RequestBody NewsRequest newsRequest) {
        var response = newsService.save(newsRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/protected/news/{id}/images")
    public NewsDetailsResponse saveImages(@PathVariable int id,
                                          @RequestParam("cover") Optional<MultipartFile> coverImage,
                                          @RequestParam("content") Optional<MultipartFile> contentImage
    ) {
        return newsService.saveImages(id, coverImage, contentImage);
    }

    @PutMapping("/protected/news/{id}")
    public NewsDetailsResponse updateById(@PathVariable int id, @RequestBody NewsRequest newsRequest) {
        return newsService.updateById(id, newsRequest);
    }



}
