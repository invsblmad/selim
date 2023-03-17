package com.konzerra.selim_server.domain.news;

import com.konzerra.selim_server.domain.news.dto.NewsResponse;
import com.konzerra.selim_server.domain.news.dto.NewsView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    Page<News> findAllByOrderByPublishedDateDesc(Pageable pageable);

    @Query(value = "select n.id as id, n.title as title, n.cover_image as coverImage from tb_news n " +
            "where to_tsvector(n.text) @@ phraseto_tsquery(?1)", nativeQuery = true)
    List<NewsView> findSimilarNewsByText(String text);
}
