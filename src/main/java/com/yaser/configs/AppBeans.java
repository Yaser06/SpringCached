package com.yaser.configs;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

public class AppBeans {

    @Bean
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        simpleCacheManager.setCaches(
                Arrays.asList(
                        new ConcurrentMapCache("people"),
                        new ConcurrentMapCache("specific-age")
                )
        );
        return simpleCacheManager;
    }
}
