package com.konzerra.selim_server.domain.news.dto;

import lombok.Data;

@Data
public class NewsRequest {
    private String title;
    private String text;
}
