package com.fcamara.multilivro.audit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractAuditingEntity implements Serializable {

    @CreatedBy
    @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    @ApiModelProperty(hidden = true)
    private String createdBy = "admin";

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @ApiModelProperty(hidden = true)
    private Instant createdDate = Instant.now();

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Instant lastModifiedDate = Instant.now();

    @NotNull
    @Column(name = "deleted", nullable = false)
    @ApiModelProperty(hidden = true)
    private boolean deleted = false;

    public abstract UUID getId();
}
