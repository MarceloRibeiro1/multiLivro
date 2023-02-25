package com.fcamara.multilivro.book.dto;

import com.fcamara.multilivro.archive.model.Archive;
import com.fcamara.multilivro.book.model.Author;

import java.util.UUID;

public interface BookWithPagesDto {
    UUID getId();
    Author getAuthor();
    Integer getPages();
    String getTitle();
    Archive getArchive();
}
