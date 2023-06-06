package com.albaco.depositorigination.weborigination.api.v1.model;

import com.albaco.depositorigination.common.enums.DepositApplicationTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "deposit_application")
@Getter
@Setter
public class DepositApplicationModel extends BaseModel {

    @Column(nullable = false, length = 20)
    private String applicationId;

    @Column(nullable = false, length = 20)
    private String customerId;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date requirementsConsent;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DepositApplicationTypeEnum accountType;

    @Column(nullable = false)
    private Integer intendedDepositInPence;

    @Column(length = 8)
    private String sortCode;

    @Column(length = 10)
    private String accountNumber;

    @Temporal(TemporalType.TIMESTAMP)
    private Date tncAgreementConsentTs;

    @Temporal(TemporalType.TIMESTAMP)
    private Date personalDetailsAllowedTs;
}
