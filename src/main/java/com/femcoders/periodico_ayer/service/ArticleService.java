package com.femcoders.periodico_ayer.service;

import com.femcoders.periodico_ayer.entity.Article;
import org.springframework.http.ResponseEntity;
import java.util.List;

public interface ArticleService {
    
    ResponseEntity<Article> addArticle(Article article);

    ResponseEntity<List<Article>> getAllArticles();

    ResponseEntity<Article> getArticleById(Long id);

    ResponseEntity<Article> updateArticle(Long id, Article article);

    ResponseEntity<Void> deleteArticle(Long id);
}
