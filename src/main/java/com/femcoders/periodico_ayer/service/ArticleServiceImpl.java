package com.femcoders.periodico_ayer.service;

import com.femcoders.periodico_ayer.repository.ArticleRepository;
import com.femcoders.periodico_ayer.repository.UserRepository;
import com.femcoders.periodico_ayer.entity.Article;
import com.femcoders.periodico_ayer.entity.User;

import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleServiceImpl implements ArticleService {
    
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    
    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ResponseEntity<Article> addArticle(Article article) {
        
        if (article.getUser() == null || article.getUser().getId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        
        Optional<User> userOptional = userRepository.findById(article.getUser().getId());
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        
        article.setUser(userOptional.get());
        
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
                    
                    
                    if (article.getUser() != null && article.getUser().getId() != null) {
                        Optional<User> userOptional = userRepository.findById(article.getUser().getId());
                        userOptional.ifPresent(existingArticle::setUser);
                    }
                    
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