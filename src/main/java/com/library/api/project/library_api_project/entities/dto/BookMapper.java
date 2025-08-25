package com.library.api.project.library_api_project.entities.dto;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.library.api.project.library_api_project.entities.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookDto toDto(Book book);

    Book toEntity(BookCreateDto dto);

    void updateEntityFromDto(BookUpdateDto dto, @MappingTarget Book book);

}
