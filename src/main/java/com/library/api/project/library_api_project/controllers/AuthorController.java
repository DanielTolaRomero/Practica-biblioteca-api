package com.library.api.project.library_api_project.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.api.project.library_api_project.entities.Author;
import com.library.api.project.library_api_project.entities.dto.AuthorDto;
import com.library.api.project.library_api_project.services.AuthorService;

import jakarta.validation.Valid;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService service;

    @PostMapping
    public ResponseEntity postMethodName(@Valid @RequestBody Author entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAuthor(entity));
    }

    @GetMapping
    public ResponseEntity<List<Author>> getAuthor(){
        return ResponseEntity.ok(service.getAuthors());
    }

    @GetMapping("/all-names")
    public List<AuthorDto> getAllNAmeStrings() {
        return service.getNameAllAuthors();
    }
    

    @GetMapping("/{id}")
    public ResponseEntity getAuthorById(@PathVariable long id) {
        Optional<Author> optionalAuthor = service.getAuthorById(id);
        if(optionalAuthor.isPresent()){
            return ResponseEntity.ok(optionalAuthor.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Autor no encontrado"));
    }
    

    
    

}
