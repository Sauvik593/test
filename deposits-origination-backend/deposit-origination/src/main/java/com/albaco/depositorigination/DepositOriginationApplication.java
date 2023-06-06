package com.albaco.depositorigination;

import com.albaco.depositorigination.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class DepositOriginationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepositOriginationApplication.class, args);
	}

}
