package com.fcamara.multilivro.audit.repository;

import com.fcamara.multilivro.audit.model.AbstractAuditingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Transactional
@NoRepositoryBean
public interface JpaAuditableRepository<T extends AbstractAuditingEntity, ID extends UUID> extends JpaRepository<T, ID> {
    @Query("select t from #{#entityName} t where t.id = :id and t.deleted = false")
    @Modifying
    Optional<T> findById(ID id);

    @Query("select t from #{#entityName} t where t.deleted = false")
    @Modifying
    List<T> findAll();

    @Override
    @Query("select e from #{#entityName} e where e.id in ?1 and e.deleted = false")
    List<T> findAllById(Iterable<ID> ids);

    @Query("update #{#entityName} t set t.deleted = true where t.id = :id")
    @Modifying
    void deleteById(UUID id);

    @Modifying
    default void delete(T entity) {
        deleteById(entity.getId());
    }

    @Override
    default void deleteAll(Iterable<? extends T> entities) {
        entities.forEach(entitiy -> deleteById(entitiy.getId()));
    }
}
