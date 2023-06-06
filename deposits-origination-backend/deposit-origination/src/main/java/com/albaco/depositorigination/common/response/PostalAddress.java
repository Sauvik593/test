package com.albaco.depositorigination.common.response;

import lombok.Data;

@Data
public class PostalAddress {
    private String addressLine1;
    private String addressLine2;
    private String postTown;
    private String county;
    private String postCode;
    private String summaryLine;
    private String latitude;
    private String longitude;
}
