package com.fcamara.multilivro.rent.repository;

import com.fcamara.multilivro.book.model.Book;
import com.fcamara.multilivro.rent.model.Rent;
import com.fcamara.multilivro.user.model.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RentRepository extends PagingAndSortingRepository<Rent, UUID>, JpaSpecificationExecutor<Rent> {
    Page<Rent> findAllByUserId(UUID userId, Pageable pageable);
    @Query("select r from Rent r where lower(r.book.title) like %:bookName% and r.user.id = :userId and r.deleted = false")
    Page<Rent> findAllByUserIdAndBookTitleLike(UUID userId, String bookName, Pageable pageable);
    Iterable<Rent> findAllByUserAndBookAndDeletedFalse(AppUser user, Book book);
}
