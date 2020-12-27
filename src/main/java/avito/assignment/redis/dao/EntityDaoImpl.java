package avito.assignment.redis.dao;

import avito.assignment.redis.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({ "rawtypes", "unused" })
@Repository
public class EntityDaoImpl implements EntityDao {

    @Autowired
    private RedisTemplate redisTemplate;

    private final String HASH_KEY = "Entity";

    private boolean ttlCheck(Entity entity) {
        return entity.getTtl() != null;
    }

    @Override
    public boolean saveEntity(Entity entity) {
        try {
            redisTemplate.opsForHash().put(HASH_KEY, entity.getId(), entity.getEntityBody());
            if (ttlCheck(entity)) {
                redisTemplate.expire(entity.getId(), entity.getTtl(), TimeUnit.MINUTES);
                    return true;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Set<Entity> getAllKeys() {
        Set<Entity> entities;
        entities = redisTemplate.opsForHash().keys(HASH_KEY);
        return entities;
    }

    @Override
    public Entity getEntityById(long id) {
        Entity entity = new Entity();
        entity.setEntityBody(redisTemplate.opsForHash().get(HASH_KEY, id));
        entity.setId(id);
        entity.setTtl(redisTemplate.getExpire(id));
        return entity;
    }

    @Override
    public boolean deleteEntity(long id) {
        try {
            redisTemplate.opsForHash().delete(HASH_KEY, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean saveDb() {
        redisTemplate.getConnectionFactory().getConnection().save();
        return true;
    }
}
