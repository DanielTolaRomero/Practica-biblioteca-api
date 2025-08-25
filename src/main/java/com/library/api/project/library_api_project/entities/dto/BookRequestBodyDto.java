package com.library.api.project.library_api_project.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookRequestBodyDto {


    @NotBlank(message = "must not be blank")
    private String title;
    
    @NotNull(message = "must not be null")
    private Integer publishedYear;

    @NotBlank(message = "must not be blank")
    private String isbn;

    @NotNull(message = "must not be null")
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
