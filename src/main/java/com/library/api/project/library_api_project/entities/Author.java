package com.library.api.project.library_api_project.entities;


import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;



@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "El nombre del autor no puede ser nulo")
    @NotEmpty(message = "El nombre del autor no pueder se vacio")
    @NotBlank(message = "El nombre del autor es obligatorio")
    @Column(unique = true, nullable = false)
    private String name;
    
    private LocalDate birthDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "author")
    private List<Book> books;

    public Author() {
    }

    public Author(@NotBlank(message = "El nombre es obligatorio") String name) {
        this.name = name;
    }


    public Author(@NotBlank(message = "El nombre es obligatorio") String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

    public Author(@NotBlank(message = "El nombre es obligatorio") String name, LocalDate birthDate, List<Book> books) {
        this.name = name;
        this.birthDate = birthDate;
        this.books = books;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Author other = (Author) obj;
        if (id != other.id)
            return false;
        return true;
    }

    public void saveBook(Book book){
        books.add(book);
        book.setAuthor(this);
    }

    public Book removeBook(Book book){
        books.remove(book);
        book.setAuthor(null);
        return book;
    }

}
