package com.fcamara.multilivro.rent.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fcamara.multilivro.audit.model.AbstractAuditingEntity;
import com.fcamara.multilivro.book.model.Book;
import com.fcamara.multilivro.user.model.AppUser;
import lombok.Getter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
public class Rent extends AbstractAuditingEntity {
    @Id
    @GeneratedValue
    private UUID id;
    @ManyToOne
    @JsonIgnore
    private AppUser user;
    @ManyToOne
    private Book book;
    @Enumerated(EnumType.STRING)
    private RentState state = RentState.REGISTERED;

    public Rent(AppUser user, Book book) {
        super();
        this.user = user;
        this.book = book;
    }

    public Rent() {
    }

    public Rent(AppUser currentUser, String bookId) {
        this.user = currentUser;
        this.book = new Book();
        this.book.setId(UUID.fromString(bookId));
    }

    public void setState(RentState state) {
        this.state = state;
    }
}
