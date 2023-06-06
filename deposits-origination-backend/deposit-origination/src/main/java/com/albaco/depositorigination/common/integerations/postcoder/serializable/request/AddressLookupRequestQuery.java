package com.albaco.depositorigination.common.integerations.postcoder.serializable.request;

import com.albaco.depositorigination.common.utils.ClassToStringMap;
import com.albaco.depositorigination.common.utils.serialisers.ListToCsvSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.impl.IndexedListSerializer;
import lombok.Builder;
import lombok.Data;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * There are some additional parameters supported. We may go with the default values as of now
 * Refer: <a href="https://postcoder.com/docs/address-lookup/address">...</a> for the details
 * page: starts with 0 which is default value, have to check what happens if it's not zero
 * Royal mail related fields nyb and alias, do we need to use it?
 * lines: taking as 2
 * identifier: deposit.origination.web, should be configurable while creating the client
 * postCodeOnly: true
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddressLookupRequestQuery implements ClassToStringMap {

    private String identifier;
    private Integer lines;
    @JsonSerialize(using = ListToCsvSerializer.class)
    private List<IncludeFields> include;
    @JsonSerialize(using = ListToCsvSerializer.class)
    private List<ExcludeFields> exclude;
    @JsonSerialize(using = ListToCsvSerializer.class)
    private List<AddTags> addTags;

    private Boolean postCodeOnly;
    private Integer page;
    private String format;

    public static enum ExcludeFields {
        pobox, organisation, departmentname, buildingname, subbuildingname, number, premise, dependentstreet, street, doubledependentlocality, dependentlocalit;
    }

    public static enum IncludeFields {
        county, posttown, postcode
    }

    /**
     * There are more supported fields for tag, but we may not use it for now.
     */
    public static enum AddTags {
        latitude, longitude;
    }
}
