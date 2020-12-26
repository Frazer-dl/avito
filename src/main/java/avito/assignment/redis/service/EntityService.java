package avito.assignment.redis.service;

import avito.assignment.redis.model.Entity;

import java.util.List;

public interface EntityService {

    boolean saveEntity(Entity entity);

    List<Entity> getAllKeys();

    Entity getEntityById(String key);

    boolean deleteEntity(String key);
}
