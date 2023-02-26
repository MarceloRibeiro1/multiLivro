package com.fcamara.multilivro.archive.service;

import com.fcamara.multilivro.archive.model.Archive;

import java.util.UUID;

public interface ArchiveService {
    Archive saveArchive(Archive archive);
    void deleteArchiveById(UUID id);
    Archive findArchiveById(UUID id);
}
