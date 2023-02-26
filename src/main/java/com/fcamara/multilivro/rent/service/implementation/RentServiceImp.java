package com.fcamara.multilivro.rent.service.implementation;

import com.fcamara.multilivro.book.dto.BookWithAllAttributesDTOimp;
import com.fcamara.multilivro.book.model.Book;
import com.fcamara.multilivro.book.repository.BookRepository;
import com.fcamara.multilivro.exception.BasicException;
import com.fcamara.multilivro.rent.model.Rent;
import com.fcamara.multilivro.rent.model.RentState;
import com.fcamara.multilivro.rent.repository.RentRepository;
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
public class RentServiceImp implements RentService {
    private final RentRepository repository;
    private final BookRepository bookRepository;

    @Override
    public Rent findBookRentById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new BasicException("No such book rent"));
    }

    @Override
    public Page<Rent> findBookRentByBookTitleLike(UUID userId, String bookName, Pageable pageable) {
        return repository.findAllByUserIdAndBookTitleLike(userId, bookName, pageable);
    }

    @Override
    public Rent newRent(UUID bookId, AppUser user) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BasicException("No such book"));
        Rent rent = new Rent(user, book);
        return repository.save(rent);
    }

    @Override
    public Page<Rent> findAllBookRents(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<Rent> getAllBookRentByUserId(UUID userId, Pageable pageable) {
        return repository.findAllByUserId(userId, pageable);
    }
    @Override
    public Page<Rent> findAllByUserIdAndBookAuthorId(UUID userId, UUID authorId, Pageable pageable) {
        return repository.findAllByUserIdAndBookAuthorId(userId, authorId, pageable);
    }

    @Override
    public Rent saveBookRent(Rent rent) {
        return repository.save(rent);
    }

    @Override
    public Rent setBookRentState(UUID id, RentState state) {
        Rent rent = findBookRentById(id);
        rent.setState(state);
        return saveBookRent(rent);
    }

    @Override
    public void deleteBookRentById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<BookWithAllAttributesDTOimp> consumeBookByRentId(UUID rentId) {
        Rent rent = findBookRentById(rentId);
        return rent.getState().consumeBook(rent.getBook().getId(),bookRepository);
    }
}
