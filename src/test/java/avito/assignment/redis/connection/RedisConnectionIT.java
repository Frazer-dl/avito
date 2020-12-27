package avito.assignment.redis.connection;

import avito.assignment.redis.ITUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

class RedisConnectionIT {

    @Test
    void connectionTest() {
        try {
            Jedis jedis = new Jedis("localhost");
            Assertions.assertEquals(jedis.ping(), ITUtil.REDIS_CONNECTION_OK);
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}
