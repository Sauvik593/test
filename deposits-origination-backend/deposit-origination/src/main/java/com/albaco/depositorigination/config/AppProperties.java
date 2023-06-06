package com.albaco.depositorigination.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "config")
@Getter
@Setter
public class AppProperties {

    private String message;
}
