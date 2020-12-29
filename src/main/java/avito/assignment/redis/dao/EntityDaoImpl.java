package avito.assignment.redis.dao;

import avito.assignment.redis.ITUtil;
import avito.assignment.redis.config.CacheHelper;
import avito.assignment.redis.model.Entity;
import org.ehcache.Cache;
import org.ehcache.expiry.Expirations;
import org.ehcache.xml.model.Expiry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SuppressWarnings({ "rawtypes", "unused" })
@Repository
public class EntityDaoImpl implements EntityDao {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CacheHelper cache;

    @Override
    public boolean saveEntity(Entity entity) {
        try {
            redisTemplate.opsForHash().put(ITUtil.HASH_KEY, entity.getId(), entity.getEntityBody());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Set<Entity> getAllKeys() {
        Set<Entity> entities;
        entities = redisTemplate.opsForHash().keys(ITUtil.HASH_KEY);
        return entities;
    }

    @Override
    public Entity getEntityById(long id) {
        Entity entity = new Entity();
        entity.setEntityBody(redisTemplate.opsForHash().get(ITUtil.HASH_KEY, id));
        entity.setId(id);
        return entity;
    }

    @Override
    public boolean deleteEntity(long id) {
        try {
            redisTemplate.opsForHash().delete(ITUtil.HASH_KEY, id);
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

    @Override
    public void setRedisTtl(long ttl) {
        redisTemplate.expire(ITUtil.HASH_KEY, ttl, TimeUnit.SECONDS);
    }

    @Override
    public boolean saveCache(Entity entity) {
        cache.getCache().put(entity.getId(), entity.getEntityBody());
        return cache.getCache().containsKey(entity.getId());
    }

    @Override
    public Set<Entity> getCacheAllKeys() {
        Set<Entity> entities = new HashSet<>();
        for (Cache.Entry<Long, Object> entry: cache.getCache()) {
            Entity entity = new Entity();
            Long id = entry.getKey();
            entity.setId(id);
            entity.setEntityBody(entry.getValue());
            entities.add(entity);
        }
        return entities;
    }

    @Override
    public Entity getCacheById(long id) {
        Entity entity = new Entity();
        entity.setId(id);
        entity.setEntityBody(cache.getCache().get(id));
        return entity;
    }

    @Override
    public boolean deleteCache(long id) {
        cache.getCache().remove(id);
        return !cache.getCache().containsKey(id);
    }

    @Override
    public boolean saveCacheToDirectory() {
        return cache.saveToDirectory();
    }

    @Override
    public void setCacheTtl(long ttl) {
        cache.setConfig(ttl);
        cache.putInCash();
    }
}
