package avito.assignment.redis.dao;

import avito.assignment.redis.model.Entity;

import java.util.List;

public interface EntityDao {

    boolean saveEntity(Entity entity);

    List<Entity> getAllKeys();

    Entity getEntityById(String key);

    boolean deleteEntity(String key);
}
