package com.fcamara.multilivro.rent.controller;

import com.fcamara.multilivro.book.dto.BookWithAllAttributesDTOimp;
import com.fcamara.multilivro.exception.BasicException;
import com.fcamara.multilivro.exception.DefaultAbstractException;
import com.fcamara.multilivro.rent.model.Rent;
import com.fcamara.multilivro.rent.model.RentState;
import com.fcamara.multilivro.rent.service.RentService;
import com.fcamara.multilivro.user.model.AppUser;
import com.fcamara.multilivro.user.service.AuthService;
import com.fcamara.multilivro.utils.Util;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1/rent")
@AllArgsConstructor
@Slf4j
public class RentController {
    private final RentService rentService;
    private final AuthService authService;
    public static final String RENT_DOMAIN = "rent";

    @PatchMapping("/save/{rentId}/{state}")
    public ResponseEntity<Rent> updateBookRent(@PathVariable UUID rentId, @PathVariable RentState state) {
        log.info("Patch request to update a BookRent state : " + rentId + state);

        Rent rent = rentService.setBookRentState(rentId, state);

        return ResponseEntity.ok(rent);
    }

    @PostMapping("/all/{userId}")
    public ResponseEntity<Page<Rent>> findAllByUserId(
            @PathVariable UUID userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(required = false) String[] sort) {
        log.info("Get request to find all BookRent by userId : " + userId);

        Pageable pageable = Util.getPageable(page, size, direction, sort);

        return ResponseEntity.ok(rentService.getAllBookRentByUserId(userId, pageable));
    }

    @GetMapping("/all/{userId}/{authorId}")
    public ResponseEntity<Page<Rent>> findAllByUserIdAndAuthor(
            @PathVariable UUID userId,
            @PathVariable UUID authorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(required = false) String[] sort) {
        log.info("Get request to find all BookRent by userId : " + userId);

        Pageable pageable = Util.getPageable(page, size, direction, sort);

        return ResponseEntity.ok(rentService.findAllByUserIdAndBookAuthorId(userId, authorId, pageable));
    }

    @PostMapping("/filter/{userId}/{bookName}")
    public ResponseEntity<Page<Rent>> findAllByUserIdAndAuthor(@PathVariable UUID userId, @PathVariable String bookName, @RequestBody Pageable pageable) {
        log.info("Get request to find all BookRent by userId : " + userId);

        return ResponseEntity.ok(rentService.findBookRentByBookTitleLike(userId, bookName, pageable));
    }

    @GetMapping("/{rentId}")
    public ResponseEntity<Rent> findById(@PathVariable UUID rentId) {
        log.info("Get request to find BookRent by rentId : " + rentId);

        return ResponseEntity.ok(rentService.findBookRentById(rentId));
    }

    @GetMapping("/consume-book/{rentId}")
    public ResponseEntity<BookWithAllAttributesDTOimp> consumeRentedBook(@PathVariable UUID rentId) {
        log.info("Get request to consume Book by rentId : " + rentId);

        BookWithAllAttributesDTOimp book = rentService.consumeBookByRentId(rentId)
                .orElseThrow(() -> new BasicException("Could not consume Book"));

        return ResponseEntity.ok(book);
    }

    /**
     * {@code POST /new/{bookId}} : Create a new rent with the given book
     *
     * @param bookId the book to be rented
     * @return BookRent with all information about the rent
     */
    @PostMapping("/new/{bookId}")
    public ResponseEntity<Rent> newRent(@PathVariable UUID bookId) {
        try {
            AppUser user = authService.getCurrentUser();
            Rent rent = rentService.newRent(bookId, user);

            return ResponseEntity.ok(rent);
        } catch (DefaultAbstractException ex) {
            Util.logger("newRent",this.getClass(),RENT_DOMAIN, ex, Optional.of(bookId));
            throw ex;
        } catch (Exception ex) {
            Util.logger("newRent",this.getClass(),RENT_DOMAIN, ex, Optional.of(bookId));
            throw ex;
        }
    }
}
