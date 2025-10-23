package com.femcoders.periodico_ayer.service;

import com.femcoders.periodico_ayer.dto.request.ArticleRequest;
import com.femcoders.periodico_ayer.dto.response.ArticleResponse;
import com.femcoders.periodico_ayer.entity.Article;
import com.femcoders.periodico_ayer.entity.User;
import com.femcoders.periodico_ayer.mapper.ArticleMapper;
import com.femcoders.periodico_ayer.repository.ArticleRepository;
import com.femcoders.periodico_ayer.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ArticleServiceImplTest {

    @Mock
    private ArticleRepository articleRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ArticleMapper articleMapper;

    @InjectMocks
    private ArticleServiceImpl articleService;

    private User user;
    private Article article;
    private ArticleRequest articleRequest;
    private ArticleResponse articleResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Usuario
        user = new User();
        user.setId(1L);
        user.setUsername("Ana");
        user.setEmail("ana@example.com");

        // Artículo
        article = new Article();
        article.setId(1L);
        article.setTitle("Test Title");
        article.setContent("Test Content");
        article.setCategory("Tech");
        article.setPublicationDate(LocalDateTime.now());
        article.setUser(user);

        // Request
        articleRequest = new ArticleRequest();
        articleRequest.setUserId(1L);
        articleRequest.setTitle("Test Title");
        articleRequest.setContent("Test Content");
        articleRequest.setCategory("Tech");

        // Response
        articleResponse = new ArticleResponse(
                1L,
                "Test Title",
                "Test Content",
                "Tech",
                1L
        );
    }

    @Test
    void testAddArticleSuccess() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(articleMapper.toEntity(articleRequest, user)).thenReturn(article);
        when(articleRepository.save(article)).thenReturn(article);
        when(articleMapper.toResponse(article)).thenReturn(articleResponse);

        ResponseEntity<ArticleResponse> response = articleService.addArticle(articleRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(articleResponse, response.getBody());
    }

    @Test
    void testAddArticleUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<ArticleResponse> response = articleService.addArticle(articleRequest);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetAllArticles() {
        List<Article> articles = List.of(article);
        when(articleRepository.findAll()).thenReturn(articles);

        ResponseEntity<List<Article>> response = articleService.getAllArticles();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(articles, response.getBody());
    }

    @Test
    void testGetArticleByIdFound() {
        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));

        ResponseEntity<Article> response = articleService.getArticleById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(article, response.getBody());
    }

    @Test
    void testGetArticleByIdNotFound() {
        when(articleRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<Article> response = articleService.getArticleById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteArticleSuccess() {
        when(articleRepository.existsById(1L)).thenReturn(true);

        ResponseEntity<Void> response = articleService.deleteArticle(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(articleRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteArticleNotFound() {
        when(articleRepository.existsById(1L)).thenReturn(false);

        ResponseEntity<Void> response = articleService.deleteArticle(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(articleRepository, never()).deleteById(anyLong());
    }
}
