package com.femcoders.periodico_ayer.service;

import com.femcoders.periodico_ayer.repository.ArticleRepository;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;



import com.femcoders.periodico_ayer.entity.Article;

@Service
public class ArticleServiceImpl implements ArticleService {
    
    private ArticleRepository articleRepository;
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

@Override

    public ResponseEntity<Article> addArticle(Article article) {
        if (article.getPublicationDate() == null) {
            article.setPublicationDate(LocalDateTime.now());
        }
        Article savedArticle = articleRepository.save(article);
        return new ResponseEntity<>(savedArticle, HttpStatus.CREATED);
    }

@Override

    public ResponseEntity<List<Article>> getAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

@Override
    public ResponseEntity<Article> getArticleById(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        return article.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

@Override
    public ResponseEntity<Article> updateArticle(Long id, Article article) {
        return articleRepository.findById(id)
                .map(existingArticle -> {
                    existingArticle.setTitle(article.getTitle());
                    existingArticle.setContent(article.getContent());
                    existingArticle.setCategory(article.getCategory());
                    existingArticle.setPublicationDate(article.getPublicationDate());
                    existingArticle.setUser(article.getUser());
                    Article saved = articleRepository.save(existingArticle);
                    return new ResponseEntity<>(saved, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            }
    @Override
    public ResponseEntity<Void> deleteArticle(Long id) {
        if (!articleRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
        articleRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}