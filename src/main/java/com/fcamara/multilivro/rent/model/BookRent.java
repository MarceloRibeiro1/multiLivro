package com.fcamara.multilivro.rent.model;

import com.fcamara.multilivro.audit.model.AbstractAuditingEntity;
import com.fcamara.multilivro.book.model.Book;
import com.fcamara.multilivro.user.model.AppUser;
import lombok.Getter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
public class BookRent extends AbstractAuditingEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    private AppUser user;
    @ManyToOne
    private Book book;
    @Enumerated(EnumType.STRING)
    private RentState state = RentState.INITIALIZED;

    public BookRent(AppUser user, Book book) {
        super();
        this.user = user;
        this.book = book;
    }

    protected BookRent() {
        super();
    }

    public void setState(RentState state) {
        this.state = state;
    }
}
