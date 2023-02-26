package com.fcamara.multilivro.book.controller;

import com.fcamara.multilivro.book.model.BookCover;
import com.fcamara.multilivro.book.model.BookFile;
import com.fcamara.multilivro.book.service.AppendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/books/append")
@RequiredArgsConstructor
public class BookAppendController {
    private final AppendService appendService;
    @PostMapping("/cover")
    public ResponseEntity<Void> appendCover(
            @ModelAttribute BookCover cover,
            @RequestParam MultipartFile file) {
        appendService.appendCover(cover, file);

        return ResponseEntity.noContent().build();
    }
    @PostMapping("/book")
    public ResponseEntity<Void> appendBook(
            @ModelAttribute BookFile bookFile,
            @RequestParam MultipartFile file) {
        appendService.appendFile(bookFile, file);

        return ResponseEntity.noContent().build();
    }
}
