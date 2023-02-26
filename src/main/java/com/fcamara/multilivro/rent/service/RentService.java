package com.fcamara.multilivro.rent.service;

import com.fcamara.multilivro.book.dto.BookWithAllAttributesDTOimp;
import com.fcamara.multilivro.rent.model.Rent;
import com.fcamara.multilivro.rent.model.RentState;
import com.fcamara.multilivro.user.model.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public interface RentService {
    Rent findBookRentById(UUID id);
    Page<Rent> findBookRentByBookTitleLike(UUID userId, String bookName, Pageable pageable);
    Rent newRent(UUID bookId, AppUser user);
    Page<Rent> findAllBookRents(Pageable pageable);
    Page<Rent> getAllBookRentByUserId(UUID userId, Pageable pageable);
    Page<Rent> findAllByUserIdAndBookAuthorId(UUID userId, UUID authorId, Pageable pageable);
    Rent saveBookRent(Rent rent);
    Rent setBookRentState(UUID id, RentState state);
    void deleteBookRentById(UUID id);
    Optional<BookWithAllAttributesDTOimp> consumeBookByRentId(UUID rentId);
}
