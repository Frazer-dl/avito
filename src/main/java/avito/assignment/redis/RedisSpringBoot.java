package avito.assignment.redis;

import avito.assignment.redis.config.CacheHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedisSpringBoot {
    public static void main(String[] args) {
        SpringApplication.run(RedisSpringBoot.class, args);
    }

    public static CacheHelper create() {
        return new CacheHelper();
    }
}
