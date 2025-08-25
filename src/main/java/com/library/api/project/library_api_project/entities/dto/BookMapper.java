package com.library.api.project.library_api_project.entities.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.library.api.project.library_api_project.entities.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDto(Book book);

    Book toEntity(BookRequestBodyDto dto);

    BookReadDto toReadDto(Book book);

    Book toEntityFromReadDto(BookReadDto dto);

    void updateEntityFromDto(BookRequestBodyDto dto, @MappingTarget Book book);

}
