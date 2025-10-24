package com.femcoders.periodico_ayer.mapper;

import com.femcoders.periodico_ayer.dto.request.UserRequest;
import com.femcoders.periodico_ayer.dto.response.UserResponse;
import com.femcoders.periodico_ayer.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "articles", ignore = true)
    User toEntity(UserRequest request);
    
    
    UserResponse toResponse(User user);
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "articles", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromRequest(UserRequest request, @MappingTarget User user);
}