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
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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

    private ArticleRequest request;
    private User user;
    private Article article;
    private ArticleResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setUsername("Hector");
        user.setEmail("hector@lavoe.com");

        request = new ArticleRequest();
        request.setTitle("El periódico de ayer");
        request.setContent("Un artículo con más de 50 caracteres, completamente válido y bien escrito.");
        request.setCategory("Noticias");
        request.setUserId(1L);

        article = Article.builder()
                .id(1L)
                .title(request.getTitle())
                .content(request.getContent())
                .category(request.getCategory())
                .user(user)
                .build();

        response = new ArticleResponse(1L, request.getTitle(), request.getContent(), request.getCategory(), 1L);
    }

    @Test
    void addArticle_success() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(articleMapper.toEntity(request, user)).thenReturn(article);
        when(articleRepository.save(article)).thenReturn(article);
        when(articleMapper.toResponse(article)).thenReturn(response);

        ResponseEntity<ArticleResponse> result = articleService.addArticle(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(articleRepository, times(1)).save(article);
    }

    @Test
    void addArticle_userNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<ArticleResponse> result = articleService.addArticle(request);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        verify(articleRepository, never()).save(any());
    }

    @Test
    void getAllArticles_returnsList() {
        when(articleRepository.findAll()).thenReturn(List.of(article));

        ResponseEntity<List<Article>> result = articleService.getAllArticles();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().size());
    }

    @Test
    void getArticleById_found() {
        when(articleRepository.findById(1L)).thenReturn(Optional.of(article));

        ResponseEntity<Article> result = articleService.getArticleById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(article, result.getBody());
    }

    @Test
    void getArticleById_notFound() {
        when(articleRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<Article> result = articleService.getArticleById(99L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}
