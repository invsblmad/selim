package com.konzerra.selim_server.domain.news;

import com.konzerra.selim_server.common.StatusResponse;
import com.konzerra.selim_server.domain.news.dto.NewsDetailsDto;
import com.konzerra.selim_server.domain.news.dto.NewsDto;
import com.konzerra.selim_server.domain.news.dto.NewsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public Page<NewsDto> getAllNews(Pageable pageable) {
        return newsService.getAllNews(pageable);
    }

    @GetMapping("/{id}")
    public NewsDetailsDto getNewsById(@PathVariable int id) {
        return newsService.getNewsById(id);
    }

    @PostMapping
    public ResponseEntity<StatusResponse> saveNews(@RequestBody NewsRequest newsRequest) {
        return newsService.saveNews(newsRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StatusResponse> updateNews(@PathVariable int id, @RequestBody NewsRequest newsRequest) {
        return newsService.updateNews(id, newsRequest);
    }

}
