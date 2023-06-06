package com.albaco.depositorigination.weborigination.api.v1.model;

import com.albaco.depositorigination.common.enums.CustomerEmploymentStatusEnum;
import com.albaco.depositorigination.common.enums.CustomerSourceTypeEnum;
import com.albaco.depositorigination.common.enums.CustomerTitleEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class CustomerModel extends BaseModel {

    @Column(nullable = false, length = 20)
    private String customerId;

    @Column(nullable = false, length = 12)
    private String mobileNumber;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private CustomerTitleEnum title;

    @Column(nullable = false, length = 100)
    private String firstName;

    @Column(length = 100)
    private String middleName;

    @Column(nullable = false, length = 100)
    private String lastName;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private CustomerEmploymentStatusEnum employmentStatus;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(nullable = false)
    private Long nationalityFk;

    @Column(length = 30)
    @Enumerated(EnumType.STRING)
    private CustomerSourceTypeEnum sourceType;

    @Column(length = 20)
    private String sourceApplicationId;
}
