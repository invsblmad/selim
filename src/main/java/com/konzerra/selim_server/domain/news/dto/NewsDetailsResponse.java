package com.konzerra.selim_server.domain.news.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsDetailsResponse {
    private int id;
    private String title;
    private String coverImage;
    private String text;
    private String contentImage;
}
