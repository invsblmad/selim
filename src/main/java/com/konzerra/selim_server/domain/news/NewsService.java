package com.konzerra.selim_server.domain.news;

import com.konzerra.selim_server.domain.news.dto.NewsDetailsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface NewsService {
    Page<NewsResponse> getAll(Pageable pageable);
    NewsDetailsResponse getById(int id);
    List<NewsResponse> getSimilarById(int id);

    NewsDetailsResponse save(NewsRequest newsRequest);

    NewsDetailsResponse saveImagesById(int id, Optional<MultipartFile> coverImage,
                                   Optional<MultipartFile> contentImage);
    NewsDetailsResponse updateById(int id, NewsRequest newsRequest);

    NewsDetailsResponse updateImagesById(int id, Optional<MultipartFile> coverImage,
                                         Optional<MultipartFile> contentImage);
}
