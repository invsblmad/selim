package com.konzerra.selim_server.domain.projects;

import com.konzerra.selim_server.domain.user.User;
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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
