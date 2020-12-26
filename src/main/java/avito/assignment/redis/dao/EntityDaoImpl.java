package avito.assignment.redis.dao;

import avito.assignment.redis.dto.ListEntityImpl;
import avito.assignment.redis.dto.MapEntityImpl;
import avito.assignment.redis.dto.StringEntityImpl;
import avito.assignment.redis.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.List;
import java.util.Locale;

@Repository
public class EntityDaoImpl implements EntityDao {

    @Autowired
    private RedisTemplate redisTemplate;
    private StringEntityImpl stringEntity;
    private ListEntityImpl listEntity;
    private MapEntityImpl mapEntity;


    private static final String STRING = "STRING";
    private static final String LIST = "LIST";
    private static final String MAP = "MAP";

    private boolean ttlCheck(Entity entity) {
        return entity.getTtl() != null;
    }

    @Override
    public boolean saveEntity(Entity entity) {
        try {
        switch (entity.getType().toUpperCase(Locale.ROOT)) {
            case STRING:
                redisTemplate.opsForHash().put(STRING, entity.getKey(),
                        stringEntity.convert(entity.getEntityBody()));
                if (ttlCheck(entity)) {
                    redisTemplate.expire(entity.getKey(), Duration.ofSeconds(entity.getTtl()));
                }
                return true;
            case LIST:
                redisTemplate.opsForList().rightPushAll(LIST, listEntity.convert(entity.getEntityBody()));
                if (ttlCheck(entity)) {
                    redisTemplate.expire(entity.getKey(), Duration.ofSeconds(entity.getTtl()));
                }
                return true;
            case MAP:
                redisTemplate.opsForList().rightPushAll(LIST, listEntity.convert(entity.getEntityBody()));
                if (ttlCheck(entity)) {
                    redisTemplate.expire(entity.getKey(), Duration.ofSeconds(entity.getTtl()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
      return false;
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
