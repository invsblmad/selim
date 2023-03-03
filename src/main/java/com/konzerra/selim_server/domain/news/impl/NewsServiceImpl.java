package com.konzerra.selim_server.domain.news.impl;

import com.konzerra.selim_server.domain.exception.NewsNotFoundException;
import com.konzerra.selim_server.domain.news.News;
import com.konzerra.selim_server.domain.news.NewsMapper;
import com.konzerra.selim_server.domain.news.NewsRepository;
import com.konzerra.selim_server.domain.news.NewsService;
import com.konzerra.selim_server.domain.news.dto.DetailedNewsDto;
import com.konzerra.selim_server.domain.news.dto.NewsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    @Override
    public Page<NewsDto> getAllNews(Pageable pageable) {
        Page<News> news = newsRepository.findAllByOrderByPublishedDateDesc(pageable);
        return news.map(newsMapper::mapToDto);
    }

    @Override
    public DetailedNewsDto getNewsById(int id) {
        News news = newsRepository.findById(id).orElseThrow(NewsNotFoundException::new);
        return newsMapper.mapToDetailedDto(news);
    }

    @Override
    public Page<NewsDto> getSimilarNewsTo(int newsId) {
        return null;
    }
}
