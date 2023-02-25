package com.fcamara.multilivro.book.repository;

import com.fcamara.multilivro.audit.repository.JpaAuditableRepository;
import com.fcamara.multilivro.book.model.BookCover;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookCoverRepository extends JpaAuditableRepository<BookCover, UUID> {
    Optional<BookCover> findFirstByBookIdAndDeletedFalseOrderByVersionDesc(UUID uuid);
}
