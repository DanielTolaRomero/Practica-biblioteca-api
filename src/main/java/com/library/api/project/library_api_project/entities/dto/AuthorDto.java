package com.library.api.project.library_api_project.entities.dto;

import java.time.LocalDate;

import com.library.api.project.library_api_project.entities.Author;

public class AuthorDto {

    private Long id;
    private String name;
    private LocalDate birthdate;

    public AuthorDto(Author author) {
        this.id = author.getId();
        this.name = author.getName();
        this.birthdate = author.getBirthDate();
    }
    
    
    public AuthorDto(Long id, String name, LocalDate birthdate) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
    }


    public AuthorDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public AuthorDto() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public LocalDate getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
    
    

}
