package com.library.api.project.library_api_project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.library.api.project.library_api_project.entities.Author;
import com.library.api.project.library_api_project.entities.Book;
import com.library.api.project.library_api_project.entities.dto.BookRequestBodyDto;
import com.library.api.project.library_api_project.entities.dto.BookDto;
import com.library.api.project.library_api_project.entities.dto.BookMapper;
import com.library.api.project.library_api_project.repositories.AuthorRepository;
import com.library.api.project.library_api_project.repositories.BookRepository;


@Service
public class BookService {
    @Autowired
    private BookRepository repo;

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private BookMapper mapper;

    // ------------------------------------------CREATE--------------------------------------------------
    
    public BookDto createBook(BookRequestBodyDto bookDto){
            if (repo.findByIsbn(bookDto.getIsbn()).isPresent()) {
                throw new IllegalArgumentException("El ISBN ya existe");
            }
            if (repo.findByTitle(bookDto.getTitle()).isPresent()) {
                throw new IllegalArgumentException("El titulo ya existe");
            }
            
            Author author = authorRepo.findById(bookDto.getAuthorId())
                            .orElseThrow(() -> new IllegalArgumentException("El autor no existe"));

            Book book = mapper.toEntity(bookDto);
            
            author.saveBook(book);

            return mapper.toDto(repo.save(book));
        
    }

    // ----------------------------------------------READ----------------------------------------------
    public List<BookDto> getBooks(){
        List<BookDto> bookList = new ArrayList<>();
        repo.findAll().forEach(book -> {
            bookList.add(mapper.toDto(book));
        });
        return bookList;
    }

    public List<BookDto> getBooksByAuthorNAme(String name){
        List<BookDto> bookList = new ArrayList<>();
        repo.findByAuthorName(name).forEach(book -> {
            bookList.add(mapper.toDto(book));
        });
        return bookList;
    }

    // ----------------------------------------------UPDATE---------------------------------------------
    public BookDto UpdateBook(BookRequestBodyDto bookDto, long id) {
        Book book = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        Author author = authorRepo.findById(bookDto.getAuthorId()).orElseThrow(()-> new IllegalArgumentException("El author no existe"));

        mapper.updateEntityFromDto(bookDto, book);

        author.saveBook(book);

        return mapper.toDto(repo.save(book));
    }

    // ---------------------------------------------DELETE-----------------------------------------------------

    @DeleteMapping
    public void deleteBook(long id){
        Book book = repo.findById(id).orElseThrow(()->new IllegalArgumentException("Book not found"));
        book = book.getAuthor().removeBook(book);
        repo.delete(book);


    }

    public Optional<BookDto> getById(long id) {
        Book book = repo.findById(id).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        return Optional.of(mapper.toDto(book));
    }
}

