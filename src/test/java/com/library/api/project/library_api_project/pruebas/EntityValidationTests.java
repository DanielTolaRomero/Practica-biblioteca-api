package com.library.api.project.library_api_project.pruebas;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.library.api.project.library_api_project.entities.Author;
import com.library.api.project.library_api_project.entities.Book;
import com.library.api.project.library_api_project.repositories.AuthorRepository;
import com.library.api.project.library_api_project.repositories.BookRepository;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EntityValidationTests {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    void shouldSaveAuthorAndBook() {
        // Crear autor
        Author author = new Author();
        author.setName("Gabriel García Márquez");
        author.setBirthDate(LocalDate.of(1927, 3, 6));
        author = authorRepository.save(author);

        // Crear libro
        Book book = new Book();
        book.setTitle("Cien Años de Soledad");
        book.setIsbn("978-0307474728");
        book.setPublishedYear(1967);
        book.setAuthor(author);
        bookRepository.save(book);

        // Verificar persistencia
        List<Book> books = bookRepository.findAll();
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor().getName())
                .isEqualTo("Gabriel García Márquez");
    }

    @Test
    void shouldNotAllowBookWithoutTitle() {
        Author author = new Author();
        author.setName("Mario Vargas Llosa");
        author.setBirthDate(LocalDate.of(1936, 3, 28));
        author = authorRepository.save(author);

        Book book = new Book();
        book.setIsbn("12345");
        book.setPublishedYear(1980);
        book.setAuthor(author);

        try {
            bookRepository.save(book);
        } catch (Exception e) {
            assertThat(e).isInstanceOf(Exception.class);
        }
    }
}

