package com.konzerra.selim_server.domain.news;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    @Column(name = "published_date")
    private LocalDate publishedDate;

}
