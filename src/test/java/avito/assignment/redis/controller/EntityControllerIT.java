package avito.assignment.redis.controller;

import avito.assignment.redis.ITUtill;
import avito.assignment.redis.dao.EntityDao;
import avito.assignment.redis.model.Entity;
import avito.assignment.redis.service.EntityService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;


class EntityControllerIT implements EntityDao, EntityService {

    private final Entity entity = ITUtill.getEntity();
    private final List<Entity> entityList = ITUtill.getEntities();

    @Test
    void saveEntity() {
        saveEntity(entity);
        List<Entity> entityList1 = ITUtill.getEntities();
        entityList1.add(ITUtill.getEntity());
        Assertions.assertEquals(entityList1, entityList);
    }

    @Test
    void getEntityById() {
        Entity entity = getEntityById(ITUtill.ID);
        Assertions.assertEquals(ITUtill.getEntities().get(0).getId(), entity.getId());
    }

    @Test
    void delEntityById() {
        deleteEntity(ITUtill.ID);
        Assertions.assertNotEquals(ITUtill.getEntities(), ITUtill.delEntity(ITUtill.ID));
    }

    @Override
    public boolean saveEntity(Entity entity) {
        entityList.add(entity);
        return true;
    }

    @Override
    public Set<Entity> getAllKeys() {
        return null;
    }

    @Override
    public Entity getEntityById(long id) {
        for (Entity entity: ITUtill.getEntities()) {
            if (id == entity.getId()) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public boolean deleteEntity(long id) {
        return ITUtill.getEntities() != ITUtill.delEntity(ITUtill.ID);
    }

    @Override
    public boolean saveDb() {
        return true;
    }

    @Override
    public void setRedisTtl(long ttl) {

    }

    @Override
    public boolean saveCache(Entity entity) {
        return false;
    }

    @Override
    public Set<Entity> getCacheAllKeys() {
        return null;
    }

    @Override
    public Entity getCacheById(long id) {
        return null;
    }

    @Override
    public boolean deleteCache(long id) {
        return false;
    }

    @Override
    public boolean saveCacheToDirectory() {
        return false;
    }

    @Override
    public void setCacheTtl(long ttl) {

    }
}
