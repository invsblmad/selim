package com.konzerra.selim_server.domain.news;

import com.konzerra.selim_server.domain.news.dto.NewsDetailsDto;
import com.konzerra.selim_server.domain.news.dto.NewsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
