package com.redis.wmp.redis.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import java.util.List;

import static io.lettuce.core.ReadFrom.*;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.cluster.nodes}")
    private List<String> nodes;

    @Bean
    public RedisConnectionFactory redisConnectionFactory()
    {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(nodes);

        LettuceClientConfiguration build = LettuceClientConfiguration.builder()
                .readFrom(MASTER)
                .build();

        return new LettuceConnectionFactory(redisClusterConfiguration, build);
    }

    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setEnableTransactionSupport(true);

        return redisTemplate;
    }
}
