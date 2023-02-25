package com.fcamara.multilivro.book.model;

import com.fcamara.multilivro.audit.model.AbstractAuditingEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class BookRecomendations extends AbstractAuditingEntity {
    @Id
    @GeneratedValue
    @ApiModelProperty(hidden = true)
    private UUID id;
    @ManyToMany
    private Set<Book> readNext;
    private UUID bookId;
    private Integer version;

    private void addRecomendation(Book book) {
        readNext.add(book);
    }

    private void removeRecomendation(Book book) {
        readNext.remove(book);
    }
}
