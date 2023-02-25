package com.fcamara.multilivro.archive.service;

import com.fcamara.multilivro.archive.model.Archive;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface FileArchiveFacade {
    Archive saveFileAndArchive(MultipartFile file, String archiveName);
    void deleteFileAndArchiveById(UUID id);
}
