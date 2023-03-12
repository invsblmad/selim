package com.konzerra.selim_server.domain.news;

import com.konzerra.selim_server.domain.user.User;
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
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public News(String title, String text, LocalDate publishedDate, User user) {
        this.title = title;
        this.text = text;
        this.publishedDate = publishedDate;
        this.user = user;
    }
}
