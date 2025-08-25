package com.library.api.project.library_api_project.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.library.api.project.library_api_project.entities.Author;
import com.library.api.project.library_api_project.entities.dto.AuthorDto;

import java.util.List;
import java.util.Optional;


@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    public Optional<Author> findByName(String name);
    
    @Query("select new com.library.api.project.library_api_project.entities.dto.AuthorDto(a.id, a.name) from Author a")
    public List<AuthorDto> getNameAllAuthors();
}
