package com.fcamara.multilivro.book.model;

import com.fcamara.multilivro.audit.model.AbstractAuditingEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

@Entity
@Data
public class Book extends AbstractAuditingEntity {
    @Id
    @GeneratedValue
    @ApiModelProperty(hidden = true)
    private UUID id;
    private String title;
    private Integer pages;
    private String category;
    @ManyToOne
    private Author author;
}
