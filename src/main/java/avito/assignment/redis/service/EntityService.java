package avito.assignment.redis.service;

import avito.assignment.redis.model.Entity;

import java.util.Set;

public interface EntityService {

    boolean saveEntity(Entity entity);

    Set<Entity> getAllKeys();

    Entity getEntityById(long id);

    boolean deleteEntity(long id);

    boolean saveDb();
}
