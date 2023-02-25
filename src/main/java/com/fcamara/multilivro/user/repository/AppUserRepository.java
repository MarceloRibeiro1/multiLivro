package com.fcamara.multilivro.user.repository;

import com.fcamara.multilivro.audit.repository.JpaAuditableRepository;
import com.fcamara.multilivro.user.model.AppUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppUserRepository extends JpaAuditableRepository<AppUser, UUID> {
    Optional<AppUser> findByUsername(String username);
}
