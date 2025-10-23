package com.femcoders.periodico_ayer.mapper;

import com.femcoders.periodico_ayer.dto.request.ArticleRequest;
import com.femcoders.periodico_ayer.dto.response.ArticleResponse;
import com.femcoders.periodico_ayer.entity.Article;
import com.femcoders.periodico_ayer.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "publicationDate", expression = "java(java.time.LocalDateTime.now())")
    Article toEntity(ArticleRequest request, User user);
    
    @Mapping(target = "userId", source = "user.id")
    ArticleResponse toResponse(Article article);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "publicationDate", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(ArticleRequest request, @MappingTarget Article article);
}