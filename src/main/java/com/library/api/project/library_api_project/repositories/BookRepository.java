package com.library.api.project.library_api_project.repositories;




import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.library.api.project.library_api_project.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{

    public List<Book> findByAuthorName(String name);

    public Optional<Book> findByIsbn(String isbn);

    public Optional<Book> findByTitle(String title);
}
