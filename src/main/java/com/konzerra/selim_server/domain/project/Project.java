package com.konzerra.selim_server.domain.project;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_projects")
@Data
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String image;

    public Project(String image) {
        this.image = image;
    }
}
