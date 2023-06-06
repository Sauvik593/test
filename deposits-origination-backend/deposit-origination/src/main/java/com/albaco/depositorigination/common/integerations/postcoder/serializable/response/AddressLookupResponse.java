package com.albaco.depositorigination.common.integerations.postcoder.serializable.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class AddressLookupResponse {

    @JsonProperty("addressline1")
    private String addressLine1;

    @JsonProperty("addressline2")
    private String addressLine2;

    @JsonProperty("summaryline")
    private String summaryLine;

    private String organisation;
    private String number;
    private String premise;
    private String street;

    @JsonProperty("posttown")
    private String postTown;
    private String county;

    @JsonProperty("postcode")
    private String postCode;
    private String latitude;
    private String longitude;
}
