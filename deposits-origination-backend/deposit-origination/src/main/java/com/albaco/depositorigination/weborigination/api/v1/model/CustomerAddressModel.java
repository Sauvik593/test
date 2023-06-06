package com.albaco.depositorigination.weborigination.api.v1.model;

import com.albaco.depositorigination.common.enums.AddressTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer_addresses")
@Getter
@Setter
public class CustomerAddressModel extends BaseModel {

    @Column(nullable = false, length = 20)
    private String customerId;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private AddressTypeEnum addressType;

    private Integer idx;

    @Column(nullable = false, length = 256)
    private String addressLine1;

    @Column(length = 256)
    private String addressLine2;

    @Column(nullable = false, length = 256)
    private String town;

    @Column(nullable = false, length = 256)
    private String county;

    @Column(nullable = false, length = 256)
    private String postCode;

    @Column(nullable = false, length = 512)
    private String summary;

    @Column(nullable = false)
    private Integer residenceYears;

    @Column(nullable = false)
    private Integer residenceMonths;
}
