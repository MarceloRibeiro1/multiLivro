package com.fcamara.multilivro.user.model;

import com.fcamara.multilivro.audit.model.AbstractAuditingEntity;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public abstract class Person extends AbstractAuditingEntity implements Serializable {
    @NonNull
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
}
