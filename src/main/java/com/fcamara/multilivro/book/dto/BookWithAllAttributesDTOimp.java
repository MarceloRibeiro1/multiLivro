package com.fcamara.multilivro.book.dto;

import com.fcamara.multilivro.archive.model.Archive;
import com.fcamara.multilivro.book.model.Author;
import com.fcamara.multilivro.book.model.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookWithAllAttributesDTOimp {
    private UUID id;
    private String title;
    private Integer pages;
    private String category;
    private Author author;
    private Archive archive;
    private String description;
    private String year;
    private String publisher;
    private Set<Book> readNext;
    public BookWithAllAttributesDTOimp(
            BookWithPagesDto bookDto
    ) {
        this.id = bookDto.getId();
        this.author = bookDto.getAuthor();
        this.pages = bookDto.getPages();
        this.title = bookDto.getTitle();
        this.archive = bookDto.getArchive();
    }
    public BookWithAllAttributesDTOimp(
            BookWithRecomendationsDto bookDto
    ) {
        this.id = bookDto.getId();
        this.author = bookDto.getAuthor();
        this.category = bookDto.getCategory();
        this.title = bookDto.getTitle();
        this.readNext = bookDto.getReadNext();
    }
    public BookWithAllAttributesDTOimp(
            BookWithCoverDto bookDto
    ) {
        this.id = bookDto.getId();
        this.author = bookDto.getAuthor();
        this.category = bookDto.getCategory();
        this.pages = bookDto.getPages();
        this.title = bookDto.getTitle();
        this.archive = bookDto.getArchive();
        this.description = bookDto.getDescription();
        this.publisher = bookDto.getPublisher();
        this.year = bookDto.getYear();
    }
}
