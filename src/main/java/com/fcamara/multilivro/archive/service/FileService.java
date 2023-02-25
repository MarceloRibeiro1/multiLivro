package com.fcamara.multilivro.archive.service;

import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileService {
    byte[] findFile(String name);
    String findFile(String name, Pageable pageable);
    void saveFile(MultipartFile file, UUID id);
    void deleteFileById(UUID id);
}
