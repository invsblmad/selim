package com.konzerra.selim_server.domain.news.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsResponse {
    private int id;
    private String title;
    private String coverImage;
}
