package com.fcamara.multilivro.rent.controller;

import com.fcamara.multilivro.book.dto.BookWithAllAttributesDTO;
import com.fcamara.multilivro.exception.BasicException;
import com.fcamara.multilivro.exception.DefaultAbstractException;
import com.fcamara.multilivro.exception.LogLevel;
import com.fcamara.multilivro.handler.ExceptionResponse;
import com.fcamara.multilivro.rent.model.Rent;
import com.fcamara.multilivro.rent.model.RentState;
import com.fcamara.multilivro.rent.service.RentService;
import com.fcamara.multilivro.user.model.AppUser;
import com.fcamara.multilivro.user.service.AuthService;
import com.fcamara.multilivro.utils.Util;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/rent")
@AllArgsConstructor
@Slf4j
public class RentController {
    private final RentService rentService;
    private final AuthService authService;
    public static final String RENT_DOMAIN = "rent";

    /**
     * {@code PATCH /save/{rentId}/{state}} : Patch request to update the rent state
     *
     * @param rentId the rent id to be updated
     * @param state the new state for the rent
     * @return the updated rent
     */
    @PatchMapping("/save/{rentId}/{state}")
    @ApiOperation(value = "Update Rent")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Rent.class, message = ""),
    })
    public ResponseEntity<Rent> updateRent(@PathVariable UUID rentId, @PathVariable RentState state) {
        try {
            Rent rent = rentService.setBookRentState(rentId, state);

            return ResponseEntity.ok(rent);
        } catch (DefaultAbstractException ex) {
            Util.logger("updateBookRent",this.getClass(),RENT_DOMAIN, ex, Optional.of(rentId));
            throw ex;
        } catch (Exception ex) {
            Util.logger("updateBookRent",this.getClass(),RENT_DOMAIN, ex);
            throw ex;
        }
    }

    /**
     * {@code GET /find-all} : Finds all rents for the user
     *
     * @param page query parameter to indicate the page to search for
     * @param size query parameter to indicate the size of the page to search for
     * @param direction query parameter to indicate the direction to search for
     * @param sort query parameter to indicate the how to sort for
     * @return Page of the specified Rent
     */
    @GetMapping("/find-all")
    @ApiOperation(value = "Find all User Rents")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Rent.class, message = ""),
            @ApiResponse(code = 500, message = "Error getting current user", response = ExceptionResponse.class),
    })
    public ResponseEntity<Page<Rent>> findAllByUserId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(required = false) String[] sort) {
        try {
            AppUser user = authService.getCurrentUser();
            Pageable pageable = Util.getPageable(page, size, direction, sort);

            return ResponseEntity.ok(rentService.getAllBookRentByUserId(user.getId(), pageable));
        } catch (DefaultAbstractException ex) {
            Util.logger("findAllByUserId",this.getClass(),RENT_DOMAIN, ex);
            throw ex;
        } catch (Exception ex) {
            Util.logger("findAllByUserId",this.getClass(),RENT_DOMAIN, ex);
            throw ex;
        }
    }

    /**
     * {@code GET /author/{authorId}} : Filter the rents for the specified book
     *
     * @param authorId the id from the author tho serach for rents
     * @param page query parameter to indicate the page to search for
     * @param size query parameter to indicate the size of the page to search for
     * @param direction query parameter to indicate the direction to search for
     * @param sort query parameter to indicate the how to sort for
     * @return Page of the specified Rent
     */
    @GetMapping("/author/{authorId}")
    @ApiOperation(value = "Find User Rents by Author")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Rent.class, message = ""),
            @ApiResponse(code = 500, message = "Error getting current user", response = ExceptionResponse.class),
    })
    public ResponseEntity<Page<Rent>> findAllByUserndAuthor(
            @PathVariable UUID authorId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(required = false) String[] sort) {
        try {
            AppUser user = authService.getCurrentUser();
            Pageable pageable = Util.getPageable(page, size, direction, sort);

            return ResponseEntity.ok(rentService.findAllByUserIdAndBookAuthorId(user.getId(), authorId, pageable));

        } catch (DefaultAbstractException ex) {
            Util.logger("findAllByUserndAuthor",this.getClass(),RENT_DOMAIN, ex);
            throw ex;
        } catch (Exception ex) {
            Util.logger("findAllByUserndAuthor",this.getClass(),RENT_DOMAIN, ex);
            throw ex;
        }
    }

    /**
     * {@code GET /book-name/{bookName}} : Filter the rents for the specified book
     *
     * @param bookName the string to filter the book rents
     * @param page query parameter to indicate the page to search for
     * @param size query parameter to indicate the size of the page to search for
     * @param direction query parameter to indicate the direction to search for
     * @param sort query parameter to indicate the how to sort for
     * @return Page of the specified Rent
     */
    @GetMapping("/book-name/{bookName}")
    @ApiOperation(value = "Find User Rents by Book Name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Rent.class, message = ""),
            @ApiResponse(code = 500, message = "Error getting current user", response = ExceptionResponse.class),
    })
    public ResponseEntity<Page<Rent>> findAllByUserAndBookName(
            @PathVariable String bookName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "ASC") Sort.Direction direction,
            @RequestParam(required = false) String[] sort) {
        try {
            AppUser user = authService.getCurrentUser();
            Pageable pageable = Util.getPageable(page, size, direction, sort);

            return ResponseEntity.ok(rentService.findBookRentByBookTitleLike(user.getId(), bookName, pageable));

        } catch (DefaultAbstractException ex) {
            Util.logger("findAllByUserAndBookName",this.getClass(),RENT_DOMAIN, ex);
            throw ex;
        } catch (Exception ex) {
            Util.logger("findAllByUserAndBookName",this.getClass(),RENT_DOMAIN, ex);
            throw ex;
        }
    }

    /**
     * {@code GET /{rentId}} : Get determined rent information
     *
     * @param rentId the rent to retrieve infrormation from
     * @return Rent
     */
    @GetMapping("/{rentId}")
    @ApiOperation(value = "Find by Id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = Rent.class, message = ""),
            @ApiResponse(code = 404, message = "No such Book", response = ExceptionResponse.class),
    })
    public ResponseEntity<Rent> findById(@PathVariable UUID rentId) {
        try {
            return ResponseEntity.ok(rentService.findBookRentById(rentId));
        } catch (DefaultAbstractException ex) {
            Util.logger("findById",this.getClass(),RENT_DOMAIN, ex, Optional.of(rentId));
            throw ex;
        } catch (Exception ex) {
            Util.logger("findById",this.getClass(),RENT_DOMAIN, ex);
            throw ex;
        }
    }

    /**
     * {@code GET /consume-book/{rentId}} : Get book information for the specified rent
     *
     * @param rentId the rent for which to find the book
     * @return BookWithAllAttributesDTO with the necessary book information
     */
    @GetMapping("/consume-book/{rentId}")
    @ApiOperation(value = "Consume Rent")
    @ApiResponses(value = {
            @ApiResponse(code = 200, response = BookWithAllAttributesDTO.class, message = ""),
            @ApiResponse(code = 401, message = "Invalid book consumption", response = ExceptionResponse.class),
            @ApiResponse(code = 404, message = "No such Book", response = ExceptionResponse.class),
    })
    public ResponseEntity<BookWithAllAttributesDTO> consumeRentedBook(@PathVariable UUID rentId) {
        try {
            BookWithAllAttributesDTO book = rentService.consumeBookByRentId(rentId)
                    .orElseThrow(() ->
                            new BasicException("No such Book", HttpStatus.NOT_FOUND, LogLevel.INFO));

            return ResponseEntity.ok(book);

        } catch (DefaultAbstractException ex) {
            Util.logger("consumeRentedBook",this.getClass(),RENT_DOMAIN, ex, Optional.of(rentId));
            throw ex;
        } catch (Exception ex) {
            Util.logger("consumeRentedBook",this.getClass(),RENT_DOMAIN, ex);
            throw ex;
        }
    }

    /**
     * {@code POST /new/{bookId}} : Create a new rent with the given book
     *
     * @param bookId the book to be rented
     * @return BookRent with all information about the rent
     */
    @PostMapping("/new/{bookId}")
    @ApiOperation(value = "New Rent")
    @ApiResponses(value = {
            @ApiResponse(code = 201, response = Rent.class, message = ""),
            @ApiResponse(code = 409, message = "Duplicate rent entry: { error message }", response = ExceptionResponse.class),
            @ApiResponse(code = 500, message = "Error getting current user", response = ExceptionResponse.class),
    })
    public ResponseEntity<Rent> newRent(@PathVariable UUID bookId) {
        try {
            AppUser user = authService.getCurrentUser();
            Rent rent = rentService.newRent(bookId, user);

            URI uri = UriComponentsBuilder
                    .fromPath("/api/v1/rent/{id}")
                    .buildAndExpand(rent.getId())
                    .toUri();

            return ResponseEntity
                    .created(uri)
                    .body(rent);

        } catch (DefaultAbstractException ex) {
            Util.logger("newRent",this.getClass(),RENT_DOMAIN, ex, Optional.of(bookId));
            throw ex;
        } catch (Exception ex) {
            Util.logger("newRent",this.getClass(),RENT_DOMAIN, ex);
            throw ex;
        }
    }
}
