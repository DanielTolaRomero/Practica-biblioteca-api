package com.library.api.project.library_api_project.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.api.project.library_api_project.entities.Author;
import com.library.api.project.library_api_project.entities.dto.AuthorDto;
import com.library.api.project.library_api_project.repositories.AuthorRepository;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repo;

    public Author createAuthor(Author author){        
        return repo.save(author);
    }

    public List<Author> getAuthors(){
        return repo.findAll();
    }

    public Optional<Author> getAuthorById(long id) {
        return repo.findById(id);
    }

    public List<AuthorDto> getNameAllAuthors(){
        return repo.getNameAllAuthors();
    }

    public List<String> getNameAuthors(){
        return getAuthors().stream().map(Author::getName).toList();
    }
    

}
