package com.library.api.project.library_api_project.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class BookUpdateDto {

    @NotNull(message = "El titulo no puede ser nulo")
    @NotEmpty(message = "El titulo no puede estar vacio")
    @NotBlank(message = "El titulo es obligatorio")
    private String title;
    
    private Integer publishedYear;

    @NotNull(message = "el ISBN no puede ser nulo")
    @NotEmpty(message = "el ISBN no puede estar vacio")
    @NotBlank(message = "El ISBN es obligatorio")
    private String isbn;

    @NotNull(message = "El Author es obligatorio")
    private Long authorId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    

}
