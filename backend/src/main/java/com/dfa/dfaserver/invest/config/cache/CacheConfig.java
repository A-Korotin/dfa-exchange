package com.dfa.dfaserver.invest.config.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@RequiredArgsConstructor
@EnableCaching
@EnableScheduling
public class CacheConfig {

    private final CacheManager cacheManager;

    @Scheduled(fixedDelayString = "${app.config.cache.ttl}")
    @SuppressWarnings("ConstantConditions")
    public void clearCaches() {
        cacheManager.getCacheNames().parallelStream().forEach(name -> cacheManager.getCache(name).clear());
    }
}
