package com.fcamara.multilivro.rent.repository;

import com.fcamara.multilivro.rent.model.BookRent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRentRepository extends PagingAndSortingRepository<BookRent, UUID> {
    Page<BookRent> findAllByUserId(UUID userId, Pageable pageable);
    Page<BookRent> findAllByUserIdAndBookAuthorId(UUID userId, UUID authorId, Pageable pageable);
    Page<BookRent> findAllByUserIdAndBookTitleLike(UUID userId, String bookName, Pageable pageable);
}
