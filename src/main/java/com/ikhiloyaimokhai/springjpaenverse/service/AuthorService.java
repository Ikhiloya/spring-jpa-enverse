package com.ikhiloyaimokhai.springjpaenverse.service;

import com.ikhiloyaimokhai.springjpaenverse.dao.AuthorRepository;
import com.ikhiloyaimokhai.springjpaenverse.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;


    public Author save(Author author) {
        return authorRepository.save(author);
    }


    public List<Author> getAuthors() {
        return authorRepository.findAll();
    }

}
