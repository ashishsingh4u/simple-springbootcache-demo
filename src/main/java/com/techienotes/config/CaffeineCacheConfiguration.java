package com.techienotes.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;

//@Configuration
@Slf4j
public class CaffeineCacheConfiguration {

    @Bean
    public Caffeine<Object, Object> caffeineConfig() {
        // Not required as we already have cache expiration in BookServiceImpl
        // return Caffeine.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES);
        return Caffeine.newBuilder();
    }

    @Bean
    public CacheManager cacheManager(Caffeine<Object, Object> caffeine) {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(caffeine);
        return caffeineCacheManager;
    }
}
