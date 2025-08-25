package com.library.api.project.library_api_project.entities.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.library.api.project.library_api_project.entities.Author;

@Mapper(componentModel = "spring")
public interface AuthorMapperI {

    Author toEntity(AuhtorRequestBodyDto dto);

    AuthorDto toDto(Author author);

    AuthorReadDto toReadDto(Author author);

    Author toEntityFromReadDto(AuthorReadDto dto);

    void updateEntityDromDto(AuhtorRequestBodyDto dto, @MappingTarget Author author);

}
