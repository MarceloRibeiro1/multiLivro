package com.fcamara.multilivro.book.service.implementation;

import com.fcamara.multilivro.archive.model.Archive;
import com.fcamara.multilivro.archive.service.FileArchiveFacade;
import com.fcamara.multilivro.book.dto.NewBookDTO;
import com.fcamara.multilivro.book.model.Book;
import com.fcamara.multilivro.book.repository.BookCoverRepository;
import com.fcamara.multilivro.book.repository.BookFIleRepository;
import com.fcamara.multilivro.book.repository.BookRecomendationsRepository;
import com.fcamara.multilivro.book.repository.BookRepository;
import com.fcamara.multilivro.book.service.BookService;
import com.fcamara.multilivro.handler.CustomException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@AllArgsConstructor
public class BookServiceImp implements BookService {
    private final BookRepository repository;
    private final FileArchiveFacade archiveFacade;
    private final BookCoverRepository coverRepository;
    private final BookRecomendationsRepository recommendationsRepository;
    private final BookFIleRepository fileRepository;
    @Override
    public Book findBookById(UUID id) {
        return repository.findById(id).orElseThrow(() -> new CustomException("No such book"));
    }

    @Override
    public Page<Book> findAllBooks(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Book saveBook(Book book) {
        return repository.save(book);
    }

    @Override
    @Transactional
    public void createNewBook(
            NewBookDTO fullBook,
            MultipartFile coverFile,
            MultipartFile pagesFile) {
        UUID id = UUID.randomUUID();
        fullBook.setIds(id);

        Archive coverArchive = archiveFacade.saveFileAndArchive(coverFile, coverFile.getName());
        Archive pagesArchive = archiveFacade.saveFileAndArchive(pagesFile, pagesFile.getName());
        fullBook.getCover().setArchive(coverArchive);
        fullBook.getFile().setArchive(pagesArchive);
        repository.save(fullBook.getBook());
        coverRepository.save(fullBook.getCover());
        fileRepository.save(fullBook.getFile());
        recommendationsRepository.save(fullBook.getRecomendations());
    }

    @Override
    public void deleteBookById(UUID id) {
        repository.deleteById(id);
    }


    @Override
    public Page<Book> findAllBooksByAuthor(String author, Pageable page) {
        return repository.findAllByAuthor_AliasAndDeletedFalse(author, page);
    }
    @Override
    public Page<Book> findAllBooksByTitle(String title, Pageable page) {
        return repository.findAllByTitleLikeAndDeletedFalse(title, page);
    }
}
