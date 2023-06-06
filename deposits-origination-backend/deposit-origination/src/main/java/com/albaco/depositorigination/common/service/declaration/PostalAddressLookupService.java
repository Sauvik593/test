package com.albaco.depositorigination.common.service.declaration;

import com.albaco.depositorigination.common.response.PostalAddress;

import java.util.List;

public interface PostalAddressLookupService {
    List<PostalAddress> getAddressListFromPostCode(String postCode);
}
