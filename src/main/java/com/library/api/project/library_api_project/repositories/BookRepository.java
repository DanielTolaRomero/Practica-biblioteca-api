package com.library.api.project.library_api_project.repositories;

import java.util.concurrent.atomic.LongAccumulator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.api.project.library_api_project.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{

}
