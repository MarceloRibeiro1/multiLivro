package com.fcamara.multilivro.book.service.implementation;

import com.fcamara.multilivro.archive.model.Archive;
import com.fcamara.multilivro.archive.service.FileArchiveFacade;
import com.fcamara.multilivro.book.model.BookCover;
import com.fcamara.multilivro.book.repository.BookCoverRepository;
import com.fcamara.multilivro.book.service.AppendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppendServiceImp implements AppendService {
    private final BookCoverRepository coverRepository;
    private final FileArchiveFacade archiveFacade;

    @Override
    public void appendCover(BookCover cover, MultipartFile coverFile) {
        Archive archive = archiveFacade.saveFileAndArchive(coverFile, coverFile.getName());
        cover.setArchive(archive);
        Optional<BookCover> latestCover = coverRepository.findFirstByBookIdAndDeletedFalseOrderByVersionDesc(cover.getBookId());
        latestCover.ifPresent(c -> cover.setVersion(c.getVersion()+1));

        coverRepository.save(cover);
    }
}
