package com.fcamara.multilivro.archive.service.implementation;

import com.fcamara.multilivro.archive.model.Archive;
import com.fcamara.multilivro.archive.service.ArchiveService;
import com.fcamara.multilivro.archive.service.FileArchiveFacade;
import com.fcamara.multilivro.archive.service.FileService;
import com.fcamara.multilivro.exception.BasicException;
import com.fcamara.multilivro.exception.LogLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileArchiveFacadeImp implements FileArchiveFacade {
    private final FileService fileService;
    private final ArchiveService archiveService;
    @Override
    public Archive saveFileAndArchive(MultipartFile file, String archiveName) {
        if (file.getOriginalFilename() == null || file.getOriginalFilename().lastIndexOf('.') == -1) throw new BasicException("File name cannot be null", HttpStatus.BAD_REQUEST, LogLevel.WARN);
        String fullFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = fullFileName.substring(file.getOriginalFilename().lastIndexOf('.'));

        Archive archive = new Archive();
        archive.setName(archiveName);
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
