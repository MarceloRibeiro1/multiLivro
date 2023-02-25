package com.fcamara.multilivro.archive.service.implementation;

import com.fcamara.multilivro.archive.model.Archive;
import com.fcamara.multilivro.archive.repository.ArchiveRepository;
import com.fcamara.multilivro.archive.service.ArchiveService;
import com.fcamara.multilivro.handler.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ArchiveServiceImp implements ArchiveService {
    private final ArchiveRepository archiveRepository;
    @Override
    public Archive saveArchive(String name) {
        UUID id = UUID.randomUUID();
        Archive archive = new Archive();
        archive.setName(name);
        archive.setId(id);
        return archiveRepository.save(archive);
    }

    @Override
    public void deleteArchiveById(UUID id) {
        archiveRepository.deleteById(id);
    }

    @Override
    public Archive findArchiveById(UUID id) {
        return archiveRepository.findById(id)
                .orElseThrow(() -> new CustomException("No such archive"));
    }
}
