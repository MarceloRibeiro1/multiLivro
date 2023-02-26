package com.fcamara.multilivro.rent.repository;

import com.fcamara.multilivro.rent.model.Rent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RentRepository extends PagingAndSortingRepository<Rent, UUID> {
    Page<Rent> findAllByUserId(UUID userId, Pageable pageable);
    Page<Rent> findAllByUserIdAndBookAuthorId(UUID userId, UUID authorId, Pageable pageable);
    Page<Rent> findAllByUserIdAndBookTitleLike(UUID userId, String bookName, Pageable pageable);
}
