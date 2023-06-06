package com.albaco.depositorigination.common.integerations.postcoder.service;

import com.albaco.depositorigination.common.convertor.PostalAddressConverter;
import com.albaco.depositorigination.common.integerations.postcoder.serializable.response.AddressLookupResponse;
import com.albaco.depositorigination.common.response.PostalAddress;
import com.albaco.depositorigination.common.service.declaration.PostalAddressLookupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
public class PostCoderAddressLookupService implements PostalAddressLookupService {

    @Autowired
    PostCoderTransportService postCoderTransportService;

    @Override
    public List<PostalAddress> getAddressListFromPostCode(String postCode) {
        List<AddressLookupResponse> addressListFromPostCode = postCoderTransportService.getAddressListFromPostCode(postCode);
        return (new PostalAddressConverter()).convertList(addressListFromPostCode);
    }
}
