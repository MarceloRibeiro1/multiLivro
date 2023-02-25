package com.fcamara.multilivro.book.controller;

import com.fcamara.multilivro.book.dto.NewBookDTO;
import com.fcamara.multilivro.book.model.Book;
import com.fcamara.multilivro.book.service.BookService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("/api/v1/books")
@AllArgsConstructor
@Slf4j
public class BookController {
    private final BookService bookService;

    @GetMapping("/find-all")
    public ResponseEntity<Page<Book>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(required = false) String[] sort){
        log.info("Post Request to get a list of pageable books");

        Pageable pageable;
        if(isNull(sort))
            pageable = PageRequest.of(page, size);
        else
            pageable = PageRequest.of(page, size, Sort.by(direction,sort));
        return ResponseEntity.ok(bookService.findAllBooks(pageable));
    }
    @GetMapping("/find-all-by-author/{authorAlias}")
    public ResponseEntity<Page<Book>> findAllByAuthor(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(required = false) String[] sort,
            @PathVariable String authorAlias){
        log.info("Post Request to get a list of pageable books by author");
        Pageable pageable;

        if(isNull(sort))
            pageable = PageRequest.of(page, size);
        else
            pageable = PageRequest.of(page, size, Sort.by(direction,sort));


        return ResponseEntity.ok(bookService.findAllBooksByAuthor(authorAlias, pageable));
    }
    @GetMapping("/find/{title}")
    public ResponseEntity<Page<Book>> findAllByTitle(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(required = false) String[] sort,
            @PathVariable String title){
        log.info("Post Request to get a list of pageable books by author");
        Pageable pageable;

        if(isNull(sort))
            pageable = PageRequest.of(page, size);
        else
            pageable = PageRequest.of(page, size, Sort.by(direction,sort));

        return ResponseEntity.ok(bookService.findAllBooksByTitle(title, pageable));
    }

    @GetMapping("/find-by-id/{bookId}")
    public ResponseEntity<Book> findByBookId(@PathVariable UUID bookId){
        return ResponseEntity.ok(bookService.findBookById(bookId));
    }

    @PostMapping("/save")
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID bookId){
        bookService.deleteBookById(bookId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/new", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "Create a new book", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createNewBook(
            @RequestParam MultipartFile coverFile,
            @RequestParam MultipartFile pagesFile,
            @ModelAttribute NewBookDTO newBookDTO
            ) {
        //String s = file.getOriginalFilename();
        //System.out.println(file.getName());
        bookService.createNewBook(newBookDTO, coverFile, pagesFile);
        return ResponseEntity.noContent().build();
    }
}
