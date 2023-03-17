package com.konzerra.selim_server.domain.news;

import com.konzerra.selim_server.domain.news.dto.NewsDetailsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsRequest;
import com.konzerra.selim_server.domain.news.dto.NewsView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    private final NewsService newsService;

    @GetMapping
    public Page<NewsResponse> getAllNews(Pageable pageable) {
        return newsService.getAllNews(pageable);
    }

    @GetMapping("/{id}")
    public NewsDetailsResponse getNewsById(@PathVariable int id) {
        return newsService.getNewsById(id);
    }

    @GetMapping("/{id}/similar-news")
    public List<NewsView> getSimilarNewsTo(@PathVariable int id) {
        return newsService.getSimilarNewsTo(id);
    }

    @PostMapping
    public ResponseEntity<NewsDetailsResponse> saveNews(@RequestBody NewsRequest newsRequest) {
        var response = newsService.saveNews(newsRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public NewsDetailsResponse updateNews(@PathVariable int id, @RequestBody NewsRequest newsRequest) {
        return newsService.updateNews(id, newsRequest);
    }

}
