package com.konzerra.selim_server.domain.news.impl;

import com.konzerra.selim_server.common.StatusResponse;
import com.konzerra.selim_server.common.exception.NewsNotFoundException;
import com.konzerra.selim_server.domain.news.News;
import com.konzerra.selim_server.domain.news.NewsMapper;
import com.konzerra.selim_server.domain.news.NewsRepository;
import com.konzerra.selim_server.domain.news.NewsService;
import com.konzerra.selim_server.domain.news.dto.NewsDetailsDto;
import com.konzerra.selim_server.domain.news.dto.NewsDto;
import com.konzerra.selim_server.domain.news.dto.NewsRequest;
import com.konzerra.selim_server.domain.security.jwt.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final TokenService tokenService;

    @Override
    public Page<NewsDto> getAllNews(Pageable pageable) {
        Page<News> news = newsRepository.findAllByOrderByPublishedDateDesc(pageable);
        return news.map(newsMapper::newsEntityToDto);
    }

    @Override
    public NewsDetailsDto getNewsById(int id) {
        News news = newsRepository.findById(id).orElseThrow(NewsNotFoundException::new);
        return newsMapper.newsEntityToDetailsDto(news);
    }

    @Override
    public Page<NewsDto> getSimilarNewsTo(int newsId) {
        return null;
    }

    @Override
    public ResponseEntity<StatusResponse> saveNews(NewsRequest newsRequest) {
        News news = new News(
                newsRequest.getTitle(),
                newsRequest.getText(),
                LocalDate.now(),
                tokenService.getUserFromToken()
        );

        newsRepository.save(news);
        return ResponseEntity.ok(new StatusResponse(HttpStatus.OK));
    }

    @Override
    public ResponseEntity<StatusResponse> updateNews(int id, NewsRequest newsRequest) {
        News news = newsRepository.findById(id).orElseThrow(NewsNotFoundException::new);

        news.setTitle(newsRequest.getTitle());
        news.setText(newsRequest.getText());

        newsRepository.save(news);
        return ResponseEntity.ok(new StatusResponse(HttpStatus.OK));
    }
}
