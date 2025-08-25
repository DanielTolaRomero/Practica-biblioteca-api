package com.library.api.project.library_api_project.entities.dto;

import com.library.api.project.library_api_project.entities.Author;
import com.library.api.project.library_api_project.entities.Book;



public class BookDto {

    private Long id;

    private String title;

    private Integer publishedYear;

    private AuthorDto author;

    private String isbn;

    public BookDto() {
    }

    public BookDto(
            String title,
            Integer publishedYear,
            Long authorId,
            String isbn) {
        this.title = title;
        this.publishedYear = publishedYear;
        this.isbn = isbn;
    }

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

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = new AuthorDto(author);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
}
