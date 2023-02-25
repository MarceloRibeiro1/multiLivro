package com.fcamara.multilivro.book.model;

import com.fcamara.multilivro.user.model.Person;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
@Table(name = "author")
public class Author extends Person {
    @Id
    @GeneratedValue
    private UUID id;
    @Column(name = "alias",unique = true)
    private String alias;
}
