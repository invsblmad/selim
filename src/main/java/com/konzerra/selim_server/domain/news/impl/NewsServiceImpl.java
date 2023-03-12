package com.konzerra.selim_server.domain.news.impl;

import com.konzerra.selim_server.domain.news.News;
import com.konzerra.selim_server.domain.news.NewsMapper;
import com.konzerra.selim_server.domain.news.NewsRepository;
import com.konzerra.selim_server.domain.news.NewsService;
import com.konzerra.selim_server.domain.news.dto.NewsDetailsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    @Override
    public Page<NewsResponse> getAllNews(Pageable pageable) {
        Page<News> news = newsRepository.findAllByOrderByPublishedDateDesc(pageable);
        return news.map(newsMapper::newsEntityToDto);
    }

    @Override
    public NewsDetailsResponse getNewsById(int id) {
        News news = newsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("News is not found with id " + id)
        );
        return newsMapper.newsEntityToDetailsDto(news);
    }

    @Override
    public Page<NewsResponse> getSimilarNewsTo(int newsId) {
        return null;
    }

    @Override
    public NewsDetailsResponse saveNews(NewsRequest newsRequest) {
        News news = newsMapper.newsDtoToEntity(newsRequest);
        news.setPublishedDate(LocalDate.now());

        News savedNews = newsRepository.save(news);
        return newsMapper.newsEntityToDetailsDto(savedNews);
    }

    @Override
    public NewsDetailsResponse updateNews(int id, NewsRequest newsRequest) {
        News news = newsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("News is not found with id " + id)
        );

        news.setTitle(newsRequest.getTitle());
        news.setText(newsRequest.getText());

        News updatedNews = newsRepository.save(news);
        return newsMapper.newsEntityToDetailsDto(updatedNews);
    }
}
