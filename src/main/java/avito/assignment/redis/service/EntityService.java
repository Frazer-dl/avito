package avito.assignment.redis.service;

import avito.assignment.redis.model.Entity;

import java.util.Set;

public interface EntityService {

    boolean saveEntity(Entity entity);

    Set<Entity> getAllKeys();

    Entity getEntityById(long id);

    boolean deleteEntity(long id);

    boolean saveDb();

    void setRedisTtl(long ttl);

    boolean saveCache(Entity entity);

    Set<Entity> getCacheAllKeys();

    Entity getCacheById(long id);

    boolean deleteCache(long id);

    boolean saveCacheToDirectory();

    void setCacheTtl(long ttl);
}
