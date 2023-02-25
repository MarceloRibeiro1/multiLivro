package com.fcamara.multilivro.archive.model;

import com.fcamara.multilivro.audit.model.AbstractAuditingEntity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
public class Archive extends AbstractAuditingEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
}
