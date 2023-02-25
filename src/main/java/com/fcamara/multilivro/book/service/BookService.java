package com.fcamara.multilivro.book.service;

import com.fcamara.multilivro.book.dto.NewBookDTO;
import com.fcamara.multilivro.book.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface BookService {
    Book findBookById(UUID id);
    Book saveBook(Book book);
    void createNewBook(NewBookDTO fullBook, MultipartFile coverFile, MultipartFile pagesFile);
    void deleteBookById(UUID id);
    Page<Book> findAllBooks(Pageable page);
    Page<Book> findAllBooksByAuthor(String author, Pageable page);
    Page<Book> findAllBooksByTitle(String title, Pageable page);
}
