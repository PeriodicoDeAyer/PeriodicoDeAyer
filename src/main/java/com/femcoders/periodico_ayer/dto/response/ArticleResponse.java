package com.femcoders.periodico_ayer.dto.response;

import lombok.Data;

 @Data
public class ArticleResponse {
    private Long id;
    private String title;
    private String content;
    private String category;
    private Long userId;

    public ArticleResponse(Long id, String title, String content, String category, Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.userId = userId;
    }
}
