package com.library.api.project.library_api_project.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.api.project.library_api_project.entities.Author;
import com.library.api.project.library_api_project.entities.dto.AuhtorRequestBodyDto;
import com.library.api.project.library_api_project.entities.dto.AuthorDto;
import com.library.api.project.library_api_project.entities.dto.AuthorMapperI;
import com.library.api.project.library_api_project.entities.dto.AuthorReadDto;
import com.library.api.project.library_api_project.repositories.AuthorRepository;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repo;

    @Autowired
    private AuthorMapperI mapper;

    public AuthorReadDto createAuthor(AuhtorRequestBodyDto author){        
        return mapper.toReadDto(repo.save(mapper.toEntity(author)));
    }

    public List<AuthorReadDto> getAuthors(){
        List<AuthorReadDto> authors = new ArrayList<>();
        repo.findAll().forEach(author ->{
            authors.add(mapper.toReadDto(author));
        });;
        return authors;
    }

    public Optional<AuthorReadDto> getAuthorById(long id) {
        AuthorReadDto dto = mapper.toReadDto(repo.findById(id).orElseThrow(() -> new IllegalArgumentException("El usuario no fue encontrado")));
        return Optional.of(dto);
    }

    public List<AuthorReadDto> getNameAllAuthors(){
        return repo.getNameAllAuthors();
    }

    public List<String> getNameAuthors(){
        return getAuthors().stream().map(AuthorReadDto::getName).toList();
    }

    public Object updateAuthor(Long id, AuhtorRequestBodyDto dto) {
        Author author =  repo.findById(id).orElseThrow(()-> new IllegalArgumentException("El usuario no existe"));
        mapper.updateEntityDromDto(dto,author);
        return repo.save(author);
    }

    public void deleteAuthor(Long id) {
        Author author = repo.findById(id).orElseThrow(()->new IllegalArgumentException("El author no existe"));
        repo.delete(author);
    }
    

}
