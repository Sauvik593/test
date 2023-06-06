package com.albaco.depositorigination.weborigination.api.v1.controller;

import com.albaco.depositorigination.common.response.ApiResponseInfo;
import com.albaco.depositorigination.common.utils.CommonUtils;
import com.albaco.depositorigination.weborigination.api.v1.dao.*;
import com.albaco.depositorigination.weborigination.api.v1.model.*;
import com.albaco.depositorigination.weborigination.api.v1.redisrepo.SessionRepository;
import com.albaco.depositorigination.websecurity.AuthorizationTypes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
@Slf4j
public class WebOriginationController {

    @Autowired
    NationalityRepository nationalityRepository;

    @Autowired
    CustomerAddressRepository customerAddressRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SecurityQuestionRepository securityQuestionRepository;

    @Autowired
    DepositApplicationRepository depositApplicationRepository;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;
    
    @Autowired
    SessionRepository sessionRepository;

    @AuthorizationTypes(openToAll = true)
    @GetMapping("deposit-origination/web/v1/create-session")
    public ApiResponseInfo createSession() throws Exception {
        String sessionKey = CommonUtils.generateRandomStringWithPrefix(16, null);
        String applicationId = CommonUtils.generateRandomStringWithPrefix(16, "DOW");
        SessionRedisModel sessionRedisModel = new SessionRedisModel(sessionKey, applicationId);
        sessionRepository.save(sessionRedisModel);
        return new ApiResponseInfo(0, "OK", sessionRedisModel);
    }

    @AuthorizationTypes(openToUnAuthenticatedSessionUsers = true)
    @GetMapping("deposit-origination/web/v1/welcome")
    public ApiResponseInfo welcome() {
        String welcomeMsg = "Welcome to Springboot planet";
        List<NationalityModel> nationalities = nationalityRepository.getAll();
//        SessionRedisModel sessionRedisModel = new SessionRedisModel("ashwin", "ashwin");
//        sessionRepository.save(sessionRedisModel);
        log.info("An INFO Message");
//        System.out.println(sessionRepository.findById("ashwin").get());
        return new ApiResponseInfo(0, welcomeMsg, nationalities);
    }
}
