package com.konzerra.selim_server.domain.news;

import com.konzerra.selim_server.domain.news.dto.NewsDetailsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsRequest;
import com.konzerra.selim_server.domain.news.dto.NewsView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NewsService {
    Page<NewsResponse> getAllNews(Pageable pageable);
    NewsDetailsResponse getNewsById(int id);
    List<NewsView> getSimilarNewsTo(int newsId);

    NewsDetailsResponse saveNews(NewsRequest newsRequest);
    NewsDetailsResponse updateNews(int id, NewsRequest newsRequest);
}
