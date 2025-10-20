package com.femcoders.periodico_ayer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.femcoders.periodico_ayer.entity.Article;
import com.femcoders.periodico_ayer.service.ArticleService;


public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleservice) {
        this.articleService = articleservice;
    }

    @PostMapping
    public ResponseEntity<Article> createrArticle(@RequestBody Article article) {
        return articleService.addArticle(article);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable Long id, @RequestBody Article article) {
        return articleService.updateArticle(id, article);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteArticle(@PathVariable Long id) {
        return articleService.deleteArticle(id);
    }


    
}
