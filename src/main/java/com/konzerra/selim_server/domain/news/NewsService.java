package com.konzerra.selim_server.domain.news;

import com.konzerra.selim_server.domain.news.dto.NewsDetailsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface NewsService {
    Page<NewsResponse> getAll(Pageable pageable);
    NewsDetailsResponse getById(int id);
    Page<NewsResponse> getSimilarById(int id, Pageable pageable);

    NewsDetailsResponse save(NewsRequest newsRequest);

    NewsDetailsResponse saveImages(int id, Optional<MultipartFile> coverImage,
                                   Optional<MultipartFile> contentImage);
    NewsDetailsResponse updateById(int id, NewsRequest newsRequest);
}
