package com.albaco.depositorigination.weborigination.api.v1.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash(value = "web-origination-session", timeToLive = 60L)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SessionRedisModel implements Serializable {

    @Id
    private String sessionId;
    private String applicationId;

}
