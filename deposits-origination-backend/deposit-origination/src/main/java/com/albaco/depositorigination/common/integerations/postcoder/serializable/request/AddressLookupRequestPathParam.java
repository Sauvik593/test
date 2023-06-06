package com.albaco.depositorigination.common.integerations.postcoder.serializable.request;

import com.albaco.depositorigination.common.utils.ClassToStringMap;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressLookupRequestPathParam implements ClassToStringMap {
    private String apiKey;
    private String countryCode;
    private String searchTerm;
}