package com.ikhiloyaimokhai.springjpaenverse.controller;

import com.ikhiloyaimokhai.springjpaenverse.model.Author;
import com.ikhiloyaimokhai.springjpaenverse.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class AuthorController {
    @Autowired
    AuthorService authorService;


    @PostMapping("author")
    public Author saveAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @GetMapping("authors")
    public List<Author> saveAuthor() {
        return authorService.getAuthors();
    }
}
