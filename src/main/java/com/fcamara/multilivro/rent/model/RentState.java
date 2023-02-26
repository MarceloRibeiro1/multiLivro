package com.fcamara.multilivro.rent.model;

import com.fcamara.multilivro.book.dto.BookWithAllAttributesDTO;
import com.fcamara.multilivro.book.dto.BookWithCoverDto;
import com.fcamara.multilivro.book.dto.BookWithPagesDto;
import com.fcamara.multilivro.book.dto.BookWithRecomendationsDto;
import com.fcamara.multilivro.book.repository.BookRepository;
import com.fcamara.multilivro.exception.LogLevel;
import com.fcamara.multilivro.rent.exception.InvalidBookConsumptionException;
import org.springframework.http.HttpStatus;

import java.util.Optional;
import java.util.UUID;

public enum RentState {
    REGISTERED {
        @Override
        public Optional<BookWithAllAttributesDTO> consumeBook(UUID bookId, BookRepository repository) {
            Optional<BookWithCoverDto> bookDto = repository.findOneWithBookWithCover(bookId);
            return bookDto.map(BookWithAllAttributesDTO::new);
        }
    },
    INITIALIZED {
        @Override
        public Optional<BookWithAllAttributesDTO> consumeBook(UUID bookId, BookRepository repository) {
            Optional<BookWithPagesDto> bookDto = repository.findOneWithBookWithPages(bookId);
            return bookDto.map(BookWithAllAttributesDTO::new);
        }
    },
    FINALIZED {
        @Override
        public Optional<BookWithAllAttributesDTO> consumeBook(UUID bookId, BookRepository repository) {
            Optional<BookWithRecomendationsDto> bookDto = repository.findOneWithBookWithRecomendations(bookId);
            return bookDto.map(BookWithAllAttributesDTO::new);
        }
    },
    PARALIZED,
    ARCHIVED;

    public Optional<BookWithAllAttributesDTO> consumeBook(UUID bookId, BookRepository repository) {
        throw new InvalidBookConsumptionException("Invalid book consumption", HttpStatus.UNAUTHORIZED, LogLevel.WARN);
    }

}
