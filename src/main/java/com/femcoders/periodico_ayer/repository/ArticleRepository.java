package com.femcoders.periodico_ayer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.femcoders.periodico_ayer.entity.Article;
import java.util.List;
import com.femcoders.periodico_ayer.entity.User;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitleContainingIgnoreCase(String title);

    List<Article> findByCategory(String category);

    List<Article> findByUser(User user);

}
