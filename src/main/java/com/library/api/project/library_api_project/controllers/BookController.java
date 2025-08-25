package com.library.api.project.library_api_project.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.library.api.project.library_api_project.entities.Book;
import com.library.api.project.library_api_project.entities.dto.BookCreateDto;
import com.library.api.project.library_api_project.entities.dto.BookDto;
import com.library.api.project.library_api_project.entities.dto.BookUpdateDto;
import com.library.api.project.library_api_project.entities.dto.BookMapper;
import com.library.api.project.library_api_project.services.BookService;

import jakarta.validation.Valid;

import java.lang.StackWalker.Option;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService service;

    @PostMapping
    public ResponseEntity createBook(@Valid @RequestBody BookCreateDto book) {
        BookDto newBook = service.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }


    @GetMapping
    public ResponseEntity getBooks() {
        return ResponseEntity.ok(service.getBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity getBookById(@PathVariable long id){
        Optional<BookDto> optionalBook = service.getById(id);
        if (!optionalBook.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
        return ResponseEntity.ok(optionalBook.get());
    }
    

    @GetMapping("/by-author/{name}")
    public List<BookDto> getBookByAuthorName(@PathVariable String name) {
        return service.getBooksByAuthorNAme(name);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity updateBook(@PathVariable long id, @Valid @RequestBody BookUpdateDto bookDto) {
        BookDto book = service.UpdateBook(bookDto,id);
        return ResponseEntity.ok(book);
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBook(@PathVariable long id){
        service.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    
    

}
