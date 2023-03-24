package com.konzerra.selim_server.domain.news;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    Page<News> findAllByOrderByPublishedDateDesc(Pageable pageable);

    @Query(value = "select * from tb_news n where n.id != :currentId and to_tsvector(n.text)"
            + " @@ cast(replace(cast(websearch_to_tsquery(:currentText) as text), '&', '|') as tsquery)" +
            " order by n.published_date desc limit 3", nativeQuery = true)
    List<News> findSimilar(@Param("currentId") int currentId, @Param("currentText") String currentText);
}
