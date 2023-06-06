package com.albaco.depositorigination.weborigination.api.v1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public class BaseModel {
    // define common fields and methods for all models here

    @Generated(value = GenerationTime.INSERT)
    @Id
    private Long id;

    @Generated(value = GenerationTime.INSERT)
    @Column(nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    @Generated(value = GenerationTime.ALWAYS)
    @Column(nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedTime;
}
