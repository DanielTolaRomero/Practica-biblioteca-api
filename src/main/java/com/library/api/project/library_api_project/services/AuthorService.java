package com.library.api.project.library_api_project.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.library.api.project.library_api_project.entities.Author;
import com.library.api.project.library_api_project.repositories.AuthorRepository;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repo;

    public Author createAuthor(String name, Date birthdate){
        Author author = new Author(name,birthdate);
        
        return repo.save(author);
    }

}
