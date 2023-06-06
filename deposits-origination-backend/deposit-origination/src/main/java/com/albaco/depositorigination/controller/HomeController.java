package com.albaco.depositorigination.controller;

import com.albaco.depositorigination.common.UrlConstants;
import com.albaco.depositorigination.common.response.PostalAddress;
import com.albaco.depositorigination.common.service.declaration.PostalAddressLookupService;
import com.albaco.depositorigination.config.AppProperties;
import com.albaco.depositorigination.websecurity.AuthorizationTypes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class HomeController {

    @Autowired
    private AppProperties appProperties;

    @Autowired
    PostalAddressLookupService postalAddressLookupService;

    @AuthorizationTypes(openToAll = true)
    @GetMapping("/ping")
    public String ping() {
        log.debug("******************* Ping URL: Application working. *******************");
        log.info(appProperties.getMessage());
        return "OK";
    }

    //TODO: open it only for the user with session
    @AuthorizationTypes(openToAll = true)
    @GetMapping("deposit-origination/api/v1/address-lookup/{postcode}")
    public List<PostalAddress> addressLookup(@PathVariable("postcode") String postCode) {
        return postalAddressLookupService.getAddressListFromPostCode(postCode);
    }

    @AuthorizationTypes(internalForward = true)
    @RequestMapping(value = UrlConstants.ACCESS_DENIED_URL)
    public void httpError403() throws AccessDeniedException {
        throw new AccessDeniedException(null);
    }
}
