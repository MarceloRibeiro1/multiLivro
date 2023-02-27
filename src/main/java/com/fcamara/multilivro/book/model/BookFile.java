package com.fcamara.multilivro.book.model;

import com.fcamara.multilivro.archive.model.Archive;
import com.fcamara.multilivro.audit.model.AbstractAuditingEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@Data
public class BookFile extends AbstractAuditingEntity {
    @Id
    @GeneratedValue
    @ApiModelProperty(hidden = true)
    private UUID id;
    @OneToOne
    @ApiModelProperty(hidden = true)
    private Archive archive;
    private UUID bookId;
    private Integer version = 0;

}
