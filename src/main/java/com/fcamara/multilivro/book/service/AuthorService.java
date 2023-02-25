package com.fcamara.multilivro.book.service;

import com.fcamara.multilivro.book.model.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorService {
    Author findAuthor(UUID id);
    List<Author> getAllAuthors();
    Author saveAuthor(Author author);
    void deleteAuthorById(UUID id);
}
