package com.fcamara.multilivro.archive.service.implementation;

import com.fcamara.multilivro.archive.model.Archive;
import com.fcamara.multilivro.archive.service.ArchiveService;
import com.fcamara.multilivro.archive.service.FileArchiveFacade;
import com.fcamara.multilivro.archive.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileArchiveFacadeImp implements FileArchiveFacade {
    private final FileService fileService;
    private final ArchiveService archiveService;
    @Override
    public Archive saveFileAndArchive(MultipartFile file, String archiveName) {
        Archive archive = archiveService.saveArchive(archiveName);
        fileService.saveFile(file, archive.getId());
        return archive;
    }

    @Override
    public void deleteFileAndArchiveById(UUID id) {
        archiveService.deleteArchiveById(id);
        fileService.deleteFileById(id);
    }
}
