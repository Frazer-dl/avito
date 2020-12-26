package avito.assignment.redis.dao;

import avito.assignment.redis.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.List;

@Repository
public class EntityDaoImpl implements EntityDao {

    @Autowired
    private RedisTemplate redisTemplate;


    private static final String KEY = "ENTITY";

    @Override
    public boolean saveEntity(Entity entity) {
        if (entity.getTtl() != null) {
            try {
                redisTemplate.opsForHash().put(KEY, entity.getKey(), entity);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        } else {
            try {
                redisTemplate.opsForHash().put(KEY, entity.getKey(), entity);
                redisTemplate.expire(entity.getKey(), Duration.ofSeconds(entity.getTtl()));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    @Override
    public List<Entity> getAllKeys() {
        List<Entity> entities;
        entities = redisTemplate.opsForHash().values(KEY);
        return entities;
    }

    @Override
    public Entity getEntityById(String id) {
        Entity entity;
        entity = (Entity) redisTemplate.opsForHash().get(KEY, id);
        return entity;
    }

    @Override
    public boolean deleteEntity(String key) {
        try {
            redisTemplate.opsForHash().delete(KEY, key);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
