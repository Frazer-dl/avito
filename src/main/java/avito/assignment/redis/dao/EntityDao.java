package avito.assignment.redis.dao;

import avito.assignment.redis.model.Entity;

import java.util.Set;

public interface EntityDao {

    boolean saveEntity(Entity entity);

    Set<Entity> getAllKeys();

    Entity getEntityById(long id);

    boolean deleteEntity(long id);

    boolean saveDb();
}
