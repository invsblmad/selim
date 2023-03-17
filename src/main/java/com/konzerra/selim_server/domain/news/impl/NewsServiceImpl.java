package com.konzerra.selim_server.domain.news.impl;

import com.konzerra.selim_server.domain.news.News;
import com.konzerra.selim_server.domain.news.NewsMapper;
import com.konzerra.selim_server.domain.news.NewsRepository;
import com.konzerra.selim_server.domain.news.NewsService;
import com.konzerra.selim_server.domain.news.dto.NewsDetailsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsRequest;
import com.konzerra.selim_server.domain.news.dto.NewsView;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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
        News news = findNewsById(id);
        return newsMapper.newsEntityToDetailsDto(news);
    }

    private News findNewsById(int id) {
        return newsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("News is not found with id " + id)
        );
    }

    @Override
    public List<NewsView> getSimilarNewsTo(int newsId) {
        News news = findNewsById(newsId);
        return newsRepository.findSimilarNewsByText(news.getText());
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
        News news = findNewsById(id);

        news.setTitle(newsRequest.getTitle());
        news.setText(newsRequest.getText());

        News updatedNews = newsRepository.save(news);
        return newsMapper.newsEntityToDetailsDto(updatedNews);
    }
}
