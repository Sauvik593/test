package com.albaco.depositorigination.common.integerations.postcoder.service;

import com.albaco.depositorigination.common.integerations.postcoder.serializable.request.AddressLookupRequestPathParam;
import com.albaco.depositorigination.common.integerations.postcoder.serializable.request.AddressLookupRequestQuery;
import com.albaco.depositorigination.common.integerations.postcoder.serializable.request.AddressLookupRequestQuery.ExcludeFields;
import com.albaco.depositorigination.common.integerations.postcoder.serializable.response.AddressLookupResponse;
import com.albaco.depositorigination.common.utils.CommonUtils;
import com.azure.security.keyvault.secrets.SecretClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.lang.invoke.TypeDescriptor;
import java.lang.reflect.ParameterizedType;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PostCoderTransportService {

    //TODO: make it configurable so that it can be defined in different beans
    @Value("${deposits.origination.web.postcoder.key}")
    private String key;

    @Value("${deposits.origination.web.postcoder.identifier}")
    private String identifier;

    @Value("${deposits.origination.web.postcoder.url}")
    private String addressUrlTemplate;
    private final RestTemplate restTemplate;

    PostCoderTransportService() {
        restTemplate = new RestTemplate();
    }

    private <T> T getRequest(String url, ParameterizedTypeReference<T> claz) {
        var headers = new HttpHeaders();
        var entity = new HttpEntity<String>(headers);
        var response = restTemplate.exchange(url, HttpMethod.GET, entity, claz);
        return response.getBody();
    }

    public List<AddressLookupResponse> getAddressListFromPostCode(String postCode) {
        var addressLookupRequestPathParam = new AddressLookupRequestPathParam(key, "UK", postCode);
        var addressLookupRequestQuery = AddressLookupRequestQuery.builder()
                .postCodeOnly(true)
                .addTags(List.of(AddressLookupRequestQuery.AddTags.latitude, AddressLookupRequestQuery.AddTags.longitude))
                .lines(2)
                .exclude(List.of(ExcludeFields.organisation))
                .format("json")
                .build();

        String url = buildUrl(addressUrlTemplate, addressLookupRequestQuery, addressLookupRequestPathParam);
        ParameterizedTypeReference<List<AddressLookupResponse>> responseType = new ParameterizedTypeReference<>() {
        };
        return getRequest(url, responseType);
    }

    private String buildUrl(String urlTemplate, AddressLookupRequestQuery addressLookupRequestQuery, AddressLookupRequestPathParam addressLookupRequestPathParam) {
        UriTemplate uriTemplate = new UriTemplate(urlTemplate);
        UriBuilder uriBuilder = UriBuilder.fromPath(urlTemplate);
        addressLookupRequestQuery.toMap().forEach(uriBuilder::queryParam);
        URI output = uriBuilder.buildFromMap(addressLookupRequestPathParam.toMap());
        return output.toASCIIString();
    }
}
