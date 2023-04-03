package com.konzerra.selim_server.domain.news.impl;

import com.konzerra.selim_server.domain.file_storage.FileStorageService;
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
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;
    private final FileStorageService fileStorageService;

    @Override
    public Page<NewsResponse> getAll(Pageable pageable) {
        Page<News> news = newsRepository.findAll(pageable);
        return news.map(newsMapper::entityToDto);
    }

    @Override
    public NewsDetailsResponse getById(int id) {
        News news = findNewsById(id);
        return newsMapper.entityToDetailsDto(news);
    }

    private News findNewsById(int id) {
        return newsRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("News is not found with id " + id)
        );
    }

    @Override
    public List<NewsResponse> getSimilarById(int id) {
        News news = findNewsById(id);
        var similarNews = newsRepository.findSimilar(news.getId(), news.getText());
        return similarNews.stream().map(newsMapper::entityToDto).toList();
    }

    @Override
    public NewsDetailsResponse save(NewsRequest newsRequest, MultipartFile coverImage,
                                    Optional<MultipartFile> contentImage) {
        News news = newsMapper.dtoToEntity(newsRequest);
        news.setPublishedDate(LocalDateTime.now());

        saveCoverImage(news, coverImage);
        contentImage.ifPresent(image -> saveContentImage(news, image));

        News savedNews = newsRepository.save(news);
        return newsMapper.entityToDetailsDto(savedNews);
    }

    private void saveCoverImage(News news, MultipartFile coverImage) {
        String path = fileStorageService.save(coverImage, "news");
        news.setCoverImage(path);
    }

    private void saveContentImage(News news, MultipartFile contentImage) {
        String path = fileStorageService.save(contentImage, "news");
        news.setContentImage(path);
    }

    @Override
    public NewsDetailsResponse updateById(int id, NewsRequest newsRequest, Optional<MultipartFile> coverImage,
                                          Optional<MultipartFile> contentImage) {
        News news = findNewsById(id);
        news.update(newsRequest.getTitle(), newsRequest.getText());

        coverImage.ifPresent(image -> updateImage(image, news.getCoverImage()));
        contentImage.ifPresent(image -> updateImage(image, news.getContentImage()));

        News updatedNews = newsRepository.save(news);
        return newsMapper.entityToDetailsDto(updatedNews);
    }

    private void updateImage(MultipartFile image, String path) {
        fileStorageService.update(image, path);
    }

    @Override
    public void deleteById(int id) {
        News news = findNewsById(id);

        deleteImage(news.getCoverImage());
        if (news.getContentImage() != null) deleteImage(news.getContentImage());

        newsRepository.delete(news);
    }

    private void deleteImage(String imagePath) {
        fileStorageService.delete(imagePath);
    }
}
