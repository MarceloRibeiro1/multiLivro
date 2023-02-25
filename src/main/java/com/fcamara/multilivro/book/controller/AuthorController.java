package com.fcamara.multilivro.book.controller;

import com.fcamara.multilivro.book.model.Author;
import com.fcamara.multilivro.book.service.AuthorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/authors")
@AllArgsConstructor
@Slf4j
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping("/find-all")
    public ResponseEntity<List<Author>> findAll(){
        log.info("Get request to find all Author");
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{authorId}")
    public ResponseEntity<Author> findByAuthorId(@PathVariable UUID authorId){
        log.info("Get request to find a author : " + authorId);
        return ResponseEntity.ok(authorService.findAuthor(authorId));
    }

    @PostMapping("/save")
    public ResponseEntity<Author> saveAuthor(@RequestBody Author author){
        log.info("Post request to save a author : " + author.getId());

        return ResponseEntity.ok(authorService.saveAuthor(author));
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable UUID authorId){
        log.info("Delete request to delete a author : " + authorId);
        authorService.deleteAuthorById(authorId);
        return ResponseEntity.noContent().build();
    }
}
