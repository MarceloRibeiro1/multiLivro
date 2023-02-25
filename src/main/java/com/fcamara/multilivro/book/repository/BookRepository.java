package com.fcamara.multilivro.book.repository;

import com.fcamara.multilivro.book.dto.BookWithCoverDto;
import com.fcamara.multilivro.book.dto.BookWithPagesDto;
import com.fcamara.multilivro.book.dto.BookWithRecomendationsDto;
import com.fcamara.multilivro.book.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, UUID> {
    Page<Book> findAllByAuthor_AliasAndDeletedFalse(String alias, Pageable pageable);
    Page<Book> findAllByTitleLikeAndDeletedFalse(String title, Pageable pageable);
    @Query(value =
            "select b.id as id, b.author as author, b.category as category, " +
            "b.pages as pages, b.title as title, bk.archive as archive, " +
            "bk.description as description, bk.publisher as publisher, bk .year as year " +
                "from Book b " +
                "inner join BookCover bk on " +
                "b.id = bk.bookId  " +
                "and bk.version = (SELECT MAX(c2.version) FROM BookCover c2 WHERE c2.bookId = b.id) " +
                "where b.id = :id " +
                "and b.deleted = false ")
    Optional<BookWithCoverDto> findOneWithBookWithCover(UUID id);
    @Query(value =
            "select b.id as id, b.author as author, b.pages as pages, b.title as title, bf.archive as archive " +
                "from Book b " +
                "inner join BookFile bf on " +
                "b.id = bf.bookId " +
                "and bf.version = (SELECT MAX(c2.version) FROM BookFile c2 WHERE c2.bookId = b.id) " +
                "where b.id = :id " +
                "and b.deleted = false")
    Optional<BookWithPagesDto> findOneWithBookWithPages(UUID id);
    @Query(value =
            "select b.id as id, b.author as author, b.category as category, b.title as title, br.readNext as readNext " +
                    "from Book b " +
                    "inner join BookRecomendations br on " +
                    "b.id = br.bookId " +
                    "and br.version = (SELECT MAX(c2.version) FROM BookRecomendations c2 WHERE c2.bookId = b.id) " +
                    "where b.id = :id " +
                    "and b.deleted = false")
    Optional<BookWithRecomendationsDto> findOneWithBookWithRecomendations(UUID id);
}
