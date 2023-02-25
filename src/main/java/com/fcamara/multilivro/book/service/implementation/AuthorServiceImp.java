package com.fcamara.multilivro.book.service.implementation;

import com.fcamara.multilivro.book.model.Author;
import com.fcamara.multilivro.book.repository.AuthorRepository;
import com.fcamara.multilivro.book.service.AuthorService;
import com.fcamara.multilivro.handler.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@AllArgsConstructor
public class AuthorServiceImp implements AuthorService {
    private final AuthorRepository repository;

    @Override
    public Author findAuthor(UUID id) {
        return repository.findById(id).orElseThrow(() -> new CustomException("No such author"));
    }

    @Override
    public List<Author> getAllAuthors() {
        return repository.findAll();
    }

    @Override
    public Author saveAuthor(Author author) {
        return repository.save(author);
    }

    @Override
    public void deleteAuthorById(UUID id) {
        repository.deleteById(id);
    }
}
