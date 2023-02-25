package com.fcamara.multilivro.rent.controller;

import com.fcamara.multilivro.book.dto.BookWithAllAttributesDTOimp;
import com.fcamara.multilivro.handler.CustomException;
import com.fcamara.multilivro.rent.model.BookRent;
import com.fcamara.multilivro.rent.model.RentState;
import com.fcamara.multilivro.rent.service.RentService;
import com.fcamara.multilivro.user.model.AppUser;
import com.fcamara.multilivro.user.service.AuthService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/rent")
@AllArgsConstructor
@Slf4j
public class BookRentController {
    private final RentService rentService;
    private final AuthService authService;

    @PatchMapping("/save/{rentId}/{state}")
    public ResponseEntity<BookRent> updateBookRent(@PathVariable UUID rentId, @PathVariable RentState state) {
        log.info("Patch request to update a BookRent state : " + rentId + state);

        BookRent rent = rentService.setBookRentState(rentId, state);

        return ResponseEntity.ok(rent);
    }

    @PostMapping("/all")
    public ResponseEntity<Page<BookRent>> findAll(@RequestBody Pageable pageable) {
        log.info("Get request to find all BookRent");

        return ResponseEntity.ok(rentService.findAllBookRents(pageable));
    }

    @PostMapping("/all/{userId}")
    public ResponseEntity<Page<BookRent>> findAllByUserId(@PathVariable UUID userId, @RequestBody Pageable pageable) {
        log.info("Get request to find all BookRent by userId : " + userId);

        return ResponseEntity.ok(rentService.getAllBookRentByUserId(userId, pageable));
    }

    @PostMapping("/all/{userId}/{authorId}")
    public ResponseEntity<Page<BookRent>> findAllByUserIdAndAuthor(@PathVariable UUID userId, @PathVariable UUID authorId, @RequestBody Pageable pageable) {
        log.info("Get request to find all BookRent by userId : " + userId);

        return ResponseEntity.ok(rentService.findAllByUserIdAndBookAuthorId(userId, authorId, pageable));
    }

    @PostMapping("/filter/{userId}/{bookName}")
    public ResponseEntity<Page<BookRent>> findAllByUserIdAndAuthor(@PathVariable UUID userId, @PathVariable String bookName, @RequestBody Pageable pageable) {
        log.info("Get request to find all BookRent by userId : " + userId);

        return ResponseEntity.ok(rentService.findBookRentByBookTitleLike(userId, bookName, pageable));
    }

    @GetMapping("/{rentId}")
    public ResponseEntity<BookRent> findById(@PathVariable UUID rentId) {
        log.info("Get request to find BookRent by rentId : " + rentId);

        return ResponseEntity.ok(rentService.findBookRentById(rentId));
    }

    @GetMapping("/consume-book/{rentId}")
    public ResponseEntity<BookWithAllAttributesDTOimp> consumeRentedBook(@PathVariable UUID rentId) {
        log.info("Get request to consume Book by rentId : " + rentId);

        BookWithAllAttributesDTOimp book = rentService.consumeBookByRentId(rentId)
                .orElseThrow(() -> new CustomException("Could not consume Book"));

        return ResponseEntity.ok(book);
    }

    /**
     * {@code POST /new/{bookId}} : Create a new rent with the given book
     *
     * @param bookId the book to be rented
     * @return BookRent with all information about the rent
     */
    @PostMapping("/new/{bookId}")
    public ResponseEntity<BookRent> newRent(@PathVariable UUID bookId) {
        log.info("Create new Book rent for bookId : " + bookId);
        AppUser user = authService.getCurrentUser();
        log.info("Create new Book rent for user : " + user.getId() );

        BookRent rent = rentService.newRent(bookId, user);

        return ResponseEntity.ok(rent);
    }
}
