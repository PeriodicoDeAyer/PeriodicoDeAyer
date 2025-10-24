package com.femcoders.periodico_ayer.controller;

import com.femcoders.periodico_ayer.dto.request.ArticleRequest;
import com.femcoders.periodico_ayer.dto.response.ArticleResponse;
import com.femcoders.periodico_ayer.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import org.springframework.http.MediaType;

@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArticleService articleService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllArticles() throws Exception {
        when(articleService.getAllArticles()).thenReturn(ResponseEntity.ok(List.of()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/periodicoayer"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateArticle() throws Exception {
        ArticleRequest req = new ArticleRequest();
        req.setTitle("Título válido");
        req.setContent("Contenido con más de cincuenta caracteres para pasar la validación.");
        req.setCategory("Noticias");
        req.setUserId(1L);

        ArticleResponse response = new ArticleResponse(1L, req.getTitle(), req.getContent(), req.getCategory(), 1L);
        when(articleService.addArticle(Mockito.any(ArticleRequest.class)))
                .thenReturn(new ResponseEntity<>(response, HttpStatus.CREATED));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/periodicoayer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Título válido"));
    }
}
