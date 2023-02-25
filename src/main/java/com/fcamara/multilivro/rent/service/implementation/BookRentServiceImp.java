package com.fcamara.multilivro.rent.service.implementation;

import com.fcamara.multilivro.book.dto.BookWithAllAttributesDTOimp;
import com.fcamara.multilivro.book.model.Book;
import com.fcamara.multilivro.book.repository.BookRepository;
import com.fcamara.multilivro.handler.CustomException;
import com.fcamara.multilivro.rent.model.BookRent;
import com.fcamara.multilivro.rent.model.RentState;
import com.fcamara.multilivro.rent.repository.BookRentRepository;
import com.fcamara.multilivro.rent.service.RentService;
import com.fcamara.multilivro.user.model.AppUser;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
@Repository
@AllArgsConstructor
@Transactional
public class BookRentServiceImp implements RentService {
    private final BookRentRepository repository;
    private final BookRepository bookRepository;

    @Override
    public BookRent findBookRentById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new CustomException("No such book rent"));
    }

    @Override
    public Page<BookRent> findBookRentByBookTitleLike(UUID userId, String bookName, Pageable pageable) {
        return repository.findAllByUserIdAndBookTitleLike(userId, bookName, pageable);
    }

    @Override
    public BookRent newRent(UUID bookId, AppUser user) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new CustomException("No such book"));
        BookRent rent = new BookRent(user, book);
        return repository.save(rent);
    }

    @Override
    public Page<BookRent> findAllBookRents(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<BookRent> getAllBookRentByUserId(UUID userId, Pageable pageable) {
        return repository.findAllByUserId(userId, pageable);
    }
    @Override
    public Page<BookRent> findAllByUserIdAndBookAuthorId(UUID userId, UUID authorId, Pageable pageable) {
        return repository.findAllByUserIdAndBookAuthorId(userId, authorId, pageable);
    }

    @Override
    public BookRent saveBookRent(BookRent bookRent) {
        return repository.save(bookRent);
    }

    @Override
    public BookRent setBookRentState(UUID id, RentState state) {
        BookRent bookRent = findBookRentById(id);
        bookRent.setState(state);
        return saveBookRent(bookRent);
    }

    @Override
    public void deleteBookRentById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<BookWithAllAttributesDTOimp> consumeBookByRentId(UUID rentId) {
        BookRent bookRent = findBookRentById(rentId);
        return bookRent.getState().consumeBook(bookRent.getBook().getId(),bookRepository);
    }
}
