package com.femcoders.periodico_ayer.service;

import com.femcoders.periodico_ayer.dto.request.ArticleRequest;
import com.femcoders.periodico_ayer.dto.response.ArticleResponse;
import com.femcoders.periodico_ayer.entity.Article;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ArticleService {
    
    ResponseEntity<ArticleResponse> addArticle(ArticleRequest article);

    ResponseEntity<List<Article>> getAllArticles();

    ResponseEntity<Article> getArticleById(Long id);

    ResponseEntity<Article> updateArticle(Long id, Article article);

    ResponseEntity<Void> deleteArticle(Long id);
}
