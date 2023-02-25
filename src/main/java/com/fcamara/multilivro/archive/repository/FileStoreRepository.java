package com.fcamara.multilivro.archive.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FileStoreRepository {
    @Transactional
    void createFile(String fileName, byte[] data);
    @Transactional
    byte[] readFile(String fileName);
    @Transactional
    String readFile(String fileName, Pageable pageable);
    @Transactional
    void deleteFile(String fileName);
}
