package com.fcamara.multilivro.book.dto;

import com.fcamara.multilivro.book.model.Author;
import com.fcamara.multilivro.book.model.Book;

import java.util.Set;
import java.util.UUID;

public interface BookWithRecomendationsDto {
    UUID getId();
    Author getAuthor();
    String getCategory();
    String getTitle();
    Set<Book> getReadNext();
}
