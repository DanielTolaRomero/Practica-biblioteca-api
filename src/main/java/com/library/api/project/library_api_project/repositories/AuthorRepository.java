package com.library.api.project.library_api_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.api.project.library_api_project.entities.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

}
