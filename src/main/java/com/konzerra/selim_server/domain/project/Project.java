package com.konzerra.selim_server.domain.project;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_projects")
@Data
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String image;

    @Column(name = "published_date", columnDefinition = "TIMESTAMP(0) WITHOUT TIME ZONE")
    private LocalDateTime publishedDate;

    public Project(String image) {
        this.image = image;
        this.publishedDate = LocalDateTime.now();
    }
}
