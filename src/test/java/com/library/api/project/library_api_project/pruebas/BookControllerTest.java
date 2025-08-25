package com.library.api.project.library_api_project.pruebas;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.api.project.library_api_project.entities.Author;
import com.library.api.project.library_api_project.entities.Book;
import com.library.api.project.library_api_project.entities.dto.BookRequestBodyDto;
import com.library.api.project.library_api_project.repositories.AuthorRepository;
import com.library.api.project.library_api_project.repositories.BookRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long existingAuthorId = 1L;

    private Long existingBookId = 1L;

    @BeforeEach
    void setup() throws Exception {
        // limpiar DB antes de cada test
        bookRepository.deleteAll();
        authorRepository.deleteAll();

        // crear un author inicial antes de cada test
        Author author = new Author();
        author.setName("Gabriel García Márquez");
        author = authorRepository.save(author);

        existingAuthorId = author.getId();

        // Crear un libro inicial antes de cada test
        BookRequestBodyDto dto = new BookRequestBodyDto();
        dto.setTitle("Clean Code");
        dto.setPublishedYear(2008);
        dto.setIsbn("9780132350884");
        dto.setAuthorId(existingAuthorId);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());

        bookRepository.findAll().forEach(b -> {
            int i = 0;
            if (i < 1){
                existingBookId = b.getId();
                i++;
            }
        });

    }


    @Test
    @DisplayName("Debe crear un libro correctamente")
    void createBook_success() throws Exception {
        BookRequestBodyDto dto = new BookRequestBodyDto();
        dto.setTitle("Refactoring");
        dto.setIsbn("9780201485677");
        dto.setPublishedYear(1999);
        dto.setAuthorId(existingAuthorId);
        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("Refactoring"))
                .andExpect(jsonPath("$.author.id").value(existingAuthorId));
    }

    @Test
    @DisplayName("Debe obtener un libro existente por id")
    void getBookById_success() throws Exception {
        mockMvc.perform(get("/books/"+existingBookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(existingBookId))
                .andExpect(jsonPath("$.title").value("Clean Code"));
    }

    @Test
    @DisplayName("Debe devolver 404 si no existe el libro")
    void getBookById_notFound() throws Exception {
        mockMvc.perform(get("/books/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Book not found"));
    }

    @Test
    @DisplayName("Debe listar todos los libros")
    void getAllBooks_success() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].title").exists());
    }

    @Test
    @DisplayName("Debe actualizar un libro existente")
    void updateBook_success() throws Exception {
        BookRequestBodyDto dto = new BookRequestBodyDto();
        dto.setTitle("Clean Code (Updated)");
        dto.setPublishedYear(2009);
        dto.setIsbn("9780132350884");
        dto.setAuthorId(existingAuthorId);

        mockMvc.perform(put("/books/"+existingBookId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Clean Code (Updated)"))
                .andExpect(jsonPath("$.publishedYear").value(2009));
    }

    @Test
    @DisplayName("Debe devolver 404 al actualizar un libro inexistente")
    void updateBook_notFound() throws Exception {
        BookRequestBodyDto dto = new BookRequestBodyDto();
        dto.setTitle("Non Existent");
        dto.setPublishedYear(2024);
        dto.setIsbn("0000000000");
        dto.setAuthorId(existingAuthorId);

        mockMvc.perform(put("/books/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Book not found"));
    }

    @Test
    @DisplayName("Debe eliminar un libro existente")
    void deleteBook_success() throws Exception {
        mockMvc.perform(delete("/books/"+existingBookId))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Debe devolver 404 al eliminar un libro inexistente")
    void deleteBook_notFound() throws Exception {
        mockMvc.perform(delete("/books/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Book not found"));
    }

    @Test
    @DisplayName("Debe validar campos inválidos al crear libro")
    void createBook_validationErrors() throws Exception {
        BookRequestBodyDto dto = new BookRequestBodyDto();
        dto.setTitle("");
        dto.setPublishedYear(null);
        dto.setIsbn("");
        dto.setAuthorId(null);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors.title").value("must not be blank"))
                .andExpect(jsonPath("$.errors.publishedYear").value("must not be null"))
                .andExpect(jsonPath("$.errors.isbn").value("must not be blank"))
                .andExpect(jsonPath("$.errors.authorId").value("must not be null"));
    }
}
