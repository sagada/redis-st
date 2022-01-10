package com.redis.wmp.redis.web.config;

import com.redis.wmp.redis.web.domain.book.Book;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.resource.ClientResources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import static io.lettuce.core.ReadFrom.*;

@Configuration
public class RedisConfig {

    @Value("${spring.redis.cluster.nodes}")
    private List<String> nodes;

    @Bean
    public RedisConnectionFactory redisConnectionFactory()
    {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(nodes);


        ClusterTopologyRefreshOptions clusterTopologyRefreshOptions = ClusterTopologyRefreshOptions.builder()
                .enablePeriodicRefresh()
                .enableAllAdaptiveRefreshTriggers()
                .enablePeriodicRefresh(Duration.ofSeconds(5)) // periodic refresh
                .build();

        ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
                .topologyRefreshOptions(clusterTopologyRefreshOptions)
                .autoReconnect(true)
                .disconnectedBehavior(ClientOptions.DisconnectedBehavior.REJECT_COMMANDS)
                .build();

        LettuceClientConfiguration build = LettuceClientConfiguration.builder()
                .readFrom(REPLICA)
                .clientOptions(clusterClientOptions)
                .build();

        return new LettuceConnectionFactory(redisClusterConfiguration, build);
    }

    @Bean
    public RedisTemplate<Long, Book> redisTemplate(RedisConnectionFactory redisConnectionFactory)
    {
        RedisTemplate<Long, Book> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setEnableTransactionSupport(true);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }
}
