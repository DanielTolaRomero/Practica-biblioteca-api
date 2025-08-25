package com.library.api.project.library_api_project.entities.dto;

import java.time.LocalDate;

public class AuthorReadDto {
    private Long id;
    private String name;
    private LocalDate birthdate;

    public AuthorReadDto(Long id, String name) {
        this.id = id;
        this.name = name;
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
