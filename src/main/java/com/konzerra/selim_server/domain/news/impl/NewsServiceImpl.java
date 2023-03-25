package com.konzerra.selim_server.domain.news.impl;

import com.konzerra.selim_server.domain.file_storage.FileStorageService;
import com.konzerra.selim_server.domain.news.News;
import com.konzerra.selim_server.domain.news.NewsMapper;
import com.konzerra.selim_server.domain.news.NewsRepository;
import com.konzerra.selim_server.domain.news.NewsService;
import com.konzerra.selim_server.domain.news.dto.NewsDetailsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsRequest;
import com.konzerra.selim_server.exception.ImageAlreadyExistsException;
import com.konzerra.selim_server.exception.ImageNotExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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
        Page<News> news = newsRepository.findAllByOrderByPublishedDateDesc(pageable);
        return news.map(newsMapper::newsEntityToDto);
    }

    @Override
    public NewsDetailsResponse getById(int id) {
        News news = findNewsById(id);
        return newsMapper.newsEntityToDetailsDto(news);
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
        return similarNews.stream().map(newsMapper::newsEntityToDto).toList();
    }

    @Override
    public NewsDetailsResponse save(NewsRequest newsRequest) {
        News news = newsMapper.newsDtoToEntity(newsRequest);
        news.setPublishedDate(LocalDate.now());

        News savedNews = newsRepository.save(news);
        return newsMapper.newsEntityToDetailsDto(savedNews);
    }

    @Override
    public NewsDetailsResponse saveImagesById(int id, Optional<MultipartFile> coverImage,
                                          Optional<MultipartFile> contentImage) {
        News news = findNewsById(id);

        coverImage.ifPresent(file -> saveCoverImage(news, file));
        contentImage.ifPresent(file -> saveContentImage(news, file));

        return newsMapper.newsEntityToDetailsDto(news);
    }

    private void saveCoverImage(News news, MultipartFile coverImage) {
        if (news.getCoverImage() != null)
            throw new ImageAlreadyExistsException("The cover image of the news" +
                    "already exists: " + news.getCoverImage());
        String path = fileStorageService.save(coverImage, "news");
        news.setCoverImage(path);
    }

    private void saveContentImage(News news, MultipartFile contentImage) {
        if (news.getContentImage() != null)
            throw new ImageAlreadyExistsException("The content image of the news " +
                    "already exists: " + news.getContentImage());
        String path = fileStorageService.save(contentImage, "news");
        news.setContentImage(path);
    }

    @Override
    public NewsDetailsResponse updateById(int id, NewsRequest newsRequest) {
        News news = findNewsById(id);

        news.setTitle(newsRequest.getTitle());
        news.setText(newsRequest.getText());

        News updatedNews = newsRepository.save(news);
        return newsMapper.newsEntityToDetailsDto(updatedNews);
    }

    @Override
    public NewsDetailsResponse updateImagesById(int id, Optional<MultipartFile> coverImage,
                                                Optional<MultipartFile> contentImage) {
        News news = findNewsById(id);
        coverImage.ifPresent(
                file -> updateImage(file, news.getCoverImage(), "cover"));

        contentImage.ifPresent(
                file -> updateImage(file, news.getContentImage(), "content"));
        return newsMapper.newsEntityToDetailsDto(news);
    }

    private void updateImage(MultipartFile image, String path, String imageType) {
        if (path == null)
            throw new ImageNotExistsException("The " + imageType + " image of the news doesn't exist");
        fileStorageService.update(image, path);
    }
}
