package com.fcamara.multilivro.archive.service.implementation;

import com.fcamara.multilivro.archive.model.Archive;
import com.fcamara.multilivro.archive.repository.ArchiveRepository;
import com.fcamara.multilivro.archive.repository.FileStoreRepository;
import com.fcamara.multilivro.archive.service.FileService;
import com.fcamara.multilivro.exception.BasicException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class FileServiceImp implements FileService {
    private final ArchiveRepository archiveRepository;
    private final FileStoreRepository fileStoreRepository;
    private static final String PATTERN = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}\\.[a-zA-Z]+";

    @Override
    public byte[] findFile(String fileName) {
        if (!fileName.matches(PATTERN))
            throw new BasicException("filename invalid");

        return fileStoreRepository.readFile(fileName);
    }

    @Override
    public String findFile(String name, Pageable pageable) {
        return fileStoreRepository.readFile(name, pageable);
    }

    @Override
    public void saveFile(MultipartFile file, UUID id) {
        try {
            if (file.getOriginalFilename() == null) throw new RuntimeException();
            String fullFileName = StringUtils.cleanPath(file.getOriginalFilename());

            String fileId = id.toString();
            int i = fullFileName.lastIndexOf('.');
            fileStoreRepository.createFile(
                    fileId.concat(fullFileName.substring(i)),
                    file.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteFileById(UUID id) {
        Archive archive = archiveRepository.findById(id)
                .orElseThrow(() -> new BasicException("No such archive"));

        archiveRepository.delete(archive);

        fileStoreRepository.deleteFile(archive.getName());
    }
}
