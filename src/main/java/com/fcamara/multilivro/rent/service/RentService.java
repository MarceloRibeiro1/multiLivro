package com.fcamara.multilivro.rent.service;

import com.fcamara.multilivro.book.dto.BookWithAllAttributesDTOimp;
import com.fcamara.multilivro.rent.model.BookRent;
import com.fcamara.multilivro.rent.model.RentState;
import com.fcamara.multilivro.user.model.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface RentService {
    BookRent findBookRentById(UUID id);
    Page<BookRent> findBookRentByBookTitleLike(UUID userId,String bookName, Pageable pageable);
    BookRent newRent(UUID bookId, AppUser user);
    Page<BookRent> findAllBookRents(Pageable pageable);
    Page<BookRent> getAllBookRentByUserId(UUID userId, Pageable pageable);
    Page<BookRent> findAllByUserIdAndBookAuthorId(UUID userId, UUID authorId, Pageable pageable);
    BookRent saveBookRent(BookRent bookRent);
    BookRent setBookRentState(UUID id, RentState state);
    void deleteBookRentById(UUID id);
    Optional<BookWithAllAttributesDTOimp> consumeBookByRentId(UUID rentId);
}
