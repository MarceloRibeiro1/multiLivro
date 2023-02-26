package com.fcamara.multilivro.book.repository;

import com.fcamara.multilivro.audit.repository.JpaAuditableRepository;
import com.fcamara.multilivro.book.model.BookFile;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookFileRepository extends JpaAuditableRepository<BookFile, UUID> {
    Optional<BookFile> findFirstByBookIdAndDeletedFalseOrderByVersionDesc(UUID uuid);
}
