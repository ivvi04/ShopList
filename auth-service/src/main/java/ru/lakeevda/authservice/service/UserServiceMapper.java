package ru.lakeevda.authservice.service;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import ru.lakeevda.authservice.dto.UserRequest;
import ru.lakeevda.authservice.dto.UserResponse;
import ru.lakeevda.authservice.entity.UserEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserServiceMapper {

    UserResponse toResponse(UserEntity entity);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    UserEntity partialUpdate(UserRequest dto, @MappingTarget UserEntity entity);
}
