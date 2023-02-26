package com.fcamara.multilivro.archive.service.implementation;

import com.fcamara.multilivro.archive.model.Archive;
import com.fcamara.multilivro.archive.service.ArchiveService;
import com.fcamara.multilivro.archive.service.FileArchiveFacade;
import com.fcamara.multilivro.archive.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class FileArchiveFacadeImp implements FileArchiveFacade {
    private final FileService fileService;
    private final ArchiveService archiveService;
    @Override
    public Archive saveFileAndArchive(MultipartFile file, String archiveName) {
        if (isNull(file.getOriginalFilename())) throw new RuntimeException();
        String fullFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = fullFileName.substring(file.getOriginalFilename().lastIndexOf('.'));

        Archive archive = new Archive();
        archive.setName(archiveName.substring(0, archiveName.lastIndexOf('.')));
        archive.setType(extension);

        archive = archiveService.saveArchive(archive);
        fileService.saveFile(file, archive.getId());
        return archive;
    }

    @Override
    public void deleteFileAndArchiveById(UUID id) {
        archiveService.deleteArchiveById(id);
        fileService.deleteFileById(id);
    }
}
