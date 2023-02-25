package com.fcamara.multilivro.book.dto;

import com.fcamara.multilivro.book.model.Book;
import com.fcamara.multilivro.book.model.BookCover;
import com.fcamara.multilivro.book.model.BookFile;
import com.fcamara.multilivro.book.model.BookRecomendations;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewBookDTO {
    private Book book;
    private BookCover cover;
    private BookFile file;
    private BookRecomendations recomendations;
    public void setIds(UUID id){
        book.setId(id);
        cover.setBookId(id);
        recomendations.setBookId(id);
        file.setBookId(id);
    }


}
