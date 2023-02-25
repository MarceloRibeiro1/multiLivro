package com.fcamara.multilivro.archive.service;

import com.fcamara.multilivro.archive.model.Archive;

import java.util.UUID;

public interface ArchiveService {
    Archive saveArchive(String name);
    void deleteArchiveById(UUID id);
    Archive findArchiveById(UUID id);
}
