package com.albaco.depositorigination.weborigination.api.v1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "nationality")
@Getter
@Setter
public class NationalityModel extends BaseModel {

    @Column(length = 4)
    private String countryCode;
    @Column(nullable = false, length = 50)
    private String country;

    @Column(nullable = false, length = 20)
    private String display_text;
}
