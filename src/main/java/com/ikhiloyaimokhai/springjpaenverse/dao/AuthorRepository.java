package com.ikhiloyaimokhai.springjpaenverse.dao;


import com.ikhiloyaimokhai.springjpaenverse.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.history.RevisionRepository;

import java.util.Optional;


@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>, RevisionRepository<Author, Integer, Integer> {

    Optional<Author> findAuthorByName(String name);
}

