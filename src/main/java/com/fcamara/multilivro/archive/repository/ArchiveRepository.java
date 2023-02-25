package com.fcamara.multilivro.archive.repository;

import com.fcamara.multilivro.archive.model.Archive;
import com.fcamara.multilivro.audit.repository.JpaAuditableRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ArchiveRepository extends JpaAuditableRepository<Archive, UUID> {
}
