package com.konzerra.selim_server.domain.news;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_news")
@Data
@NoArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    @Column(columnDefinition = "text")
    private String text;
    @Column(name = "cover_image")
    private String coverImage;
    @Column(name = "content_image")
    private String contentImage;
    @Column(name = "published_date", columnDefinition = "TIMESTAMP(0) WITHOUT TIME ZONE")
    private LocalDateTime publishedDate;

    public void update(String title, String text) {
        this.title = title;
        this.text = text;
    }
}
