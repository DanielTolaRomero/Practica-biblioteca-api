package com.library.api.project.library_api_project.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.api.project.library_api_project.entities.dto.AuhtorRequestBodyDto;
import com.library.api.project.library_api_project.entities.dto.AuthorReadDto;
import com.library.api.project.library_api_project.services.AuthorService;

import jakarta.validation.Valid;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;





@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService service;

    // create

    @PostMapping
    public ResponseEntity postMethodName(@Valid @RequestBody AuhtorRequestBodyDto entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAuthor(entity));
    }

    // read
    @GetMapping
    public ResponseEntity<?> getAuthor(){
        return ResponseEntity.ok(service.getAuthors());
    }

    @GetMapping("/names")
    public ResponseEntity<?> getAllNAmeStrings() {
        return ResponseEntity.ok(service.getNameAllAuthors());
    }
    

    @GetMapping("/{id}")
    public ResponseEntity getAuthorById(@PathVariable long id) {
        Optional<AuthorReadDto> optionalAuthor = service.getAuthorById(id);
        if(optionalAuthor.isPresent()){
            return ResponseEntity.ok(optionalAuthor.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message","Autor no encontrado"));
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity updateAuthor(@PathVariable Long id, @RequestBody AuhtorRequestBodyDto dto) {
        return ResponseEntity.ok(service.updateAuthor(id, dto));
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity deleteAuthor(@PathVariable Long id){
        service.deleteAuthor(id);
        return ResponseEntity.ok("Se elimino el author");
    }
    
    

}
