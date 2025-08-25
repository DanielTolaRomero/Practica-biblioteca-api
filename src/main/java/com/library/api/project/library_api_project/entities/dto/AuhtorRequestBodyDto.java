package com.library.api.project.library_api_project.entities.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;

public class AuhtorRequestBodyDto {

    @NotBlank
    private String name;

    private LocalDate birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    



}
