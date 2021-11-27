package com.example.bookshop.config;/**
 * @Auther: shu'ai
 * @Date: 2021/10/31 20:56
 * @Description:
 */

/**
 * @ClassName:RedisListenerConfig
 * @Auther: shu'ai
 * @Description: redis监听过期
 * @Date: 2021/10/31 20:56
 * @Version: v1.0
 */


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

@Configuration
public class RedisListenerConfig {

    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
}

