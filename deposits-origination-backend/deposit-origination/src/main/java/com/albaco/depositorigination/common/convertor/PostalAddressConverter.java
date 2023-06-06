package com.albaco.depositorigination.common.convertor;

import com.albaco.depositorigination.common.integerations.postcoder.serializable.response.AddressLookupResponse;
import com.albaco.depositorigination.common.response.PostalAddress;
import lombok.NonNull;
import org.codehaus.jackson.map.util.BeanUtil;
import org.springframework.beans.BeanUtils;

public class PostalAddressConverter implements Convertor<PostalAddress, AddressLookupResponse> {
    @Override
    public PostalAddress convertNonNull(@NonNull AddressLookupResponse source) {
        //TODO: for this object we can use BeanUtils to copy, there might be certain cases where we have to transform the object
        //note: no need to handle null case here if using convertor utility.
        var address = new PostalAddress();
        BeanUtils.copyProperties(source, address);
        return address;
    }
}
