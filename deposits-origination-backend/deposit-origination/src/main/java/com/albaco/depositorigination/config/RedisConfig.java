package com.albaco.depositorigination.config;

import com.albaco.depositorigination.common.integerations.postcoder.service.PostCoderTransportService;
import com.azure.security.keyvault.secrets.SecretClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@Configuration
@EnableRedisRepositories("com.albaco.depositorigination.weborigination.redisrepo")
@PropertySource("classpath:application.properties")
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String hostname;

    @Value("${spring.data.redis.password}")
    private String password;

    @Value("${spring.data.redis.port}")
    private Integer port;

    @Value("${spring.data.redis.ssl}")
    private Boolean useSsl;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
        connectionFactory.setHostName(hostname);
        connectionFactory.setPort(port);
        connectionFactory.setPassword(password);
        connectionFactory.setUseSsl(useSsl);
        return connectionFactory;
    }
    @Bean
    public RedisTemplate<?, ?> redisTemplate() {
        RedisTemplate<byte[], byte[]> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        return template;
    }
}
