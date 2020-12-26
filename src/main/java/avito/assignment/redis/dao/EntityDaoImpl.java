package avito.assignment.redis.dao;

import avito.assignment.redis.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.concurrent.TimeUnit;


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
                redisTemplate.expire(entity.getId(), entity.getTtl(), TimeUnit.SECONDS);
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
    public Entity getEntityById(String id) {
        Entity entity;
        entity = (Entity) redisTemplate.opsForHash().get(HASH_KEY, id);
        return entity;
    }

    @Override
    public boolean deleteEntity(String id) {
        try {
            redisTemplate.opsForHash().delete(HASH_KEY, id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
