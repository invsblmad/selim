package com.konzerra.selim_server.domain.news.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class NewsResponse {
    private int id;
    private String title;
    private String coverImage;
}
