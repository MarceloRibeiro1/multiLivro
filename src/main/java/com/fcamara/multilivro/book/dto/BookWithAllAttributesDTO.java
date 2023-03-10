package com.fcamara.multilivro.book.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookWithAllAttributesDTO {
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
    public BookWithAllAttributesDTO(
            BookWithPagesDto bookDto
    ) {
        this.id = bookDto.getId();
        this.author = bookDto.getAuthor();
        this.pages = bookDto.getPages();
        this.title = bookDto.getTitle();
        this.archive = bookDto.getArchive();
    }
    public BookWithAllAttributesDTO(
            BookWithRecomendationsDto bookDto
    ) {
        this.id = bookDto.getId();
        this.author = bookDto.getAuthor();
        this.category = bookDto.getCategory();
        this.title = bookDto.getTitle();
        this.readNext = bookDto.getReadNext();
    }
    public BookWithAllAttributesDTO(
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
