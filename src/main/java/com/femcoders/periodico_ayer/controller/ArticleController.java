package com.femcoders.periodico_ayer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.femcoders.periodico_ayer.dto.request.ArticleRequest;
import com.femcoders.periodico_ayer.dto.response.ArticleResponse;
import com.femcoders.periodico_ayer.entity.Article;
import com.femcoders.periodico_ayer.service.ArticleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/periodicoayer")
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleservice) {
        this.articleService = articleservice;
    }

    @PostMapping
    public ResponseEntity<ArticleResponse> createArticle(@Valid @RequestBody ArticleRequest article) {
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