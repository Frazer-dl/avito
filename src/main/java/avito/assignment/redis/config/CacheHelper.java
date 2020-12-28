package avito.assignment.redis.config;

import avito.assignment.redis.ITUtil;
import lombok.Getter;
import lombok.Setter;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.PersistentCacheManager;
import org.ehcache.config.CacheConfiguration;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.EntryUnit;
import org.ehcache.config.units.MemoryUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import java.io.File;
import java.time.Duration;


@Getter
@Setter
@Component("cache")
public class CacheHelper {

    private CacheManager cacheManager;
    private Cache<Long, Object> cache;
    private CacheConfiguration<Long, Object> cacheConfiguration;
    public Long ttl = 1000L;
    Object object = new Object();

    public CacheHelper() {
         cacheConfiguration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Long.class, Object.class, ResourcePoolsBuilder.heap(10))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ttl)))
                .build();
         cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
         cacheManager.init();
         putInCash();
    }

    public CacheConfiguration<Long, Object> setConfig(Long ttl) {
        cacheManager.close();
        cacheConfiguration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Long.class, Object.class, ResourcePoolsBuilder.heap(10))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ttl)))
                .build();
        return cacheConfiguration;
    }

    public void putInCash() {
        cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();
        cache = cacheManager.createCache("cache", cacheConfiguration);
    }

    public Cache<Long, Object> getCache() {
        return cacheManager.getCache("cache", Long.class, Object.class);
    }

    public boolean saveToDirectory() {
        PersistentCacheManager persistentCacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                .with(CacheManagerBuilder.persistence(ITUtil.STORAGE_PATH + File.separator + "cache_dump"))
                .withCache("persistent-cache", CacheConfigurationBuilder
                        .newCacheConfigurationBuilder(Long.class, String.class, ResourcePoolsBuilder.newResourcePoolsBuilder()
                                .heap(10, EntryUnit.ENTRIES)
                                .disk(10, MemoryUnit.MB, true))).build(true);

        persistentCacheManager.close();

        File f = new File(ITUtil.STORAGE_PATH + File.separator + "cache_dump");
        return f.exists() && !f.isDirectory();
    }
}
