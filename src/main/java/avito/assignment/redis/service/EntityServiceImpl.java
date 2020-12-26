package avito.assignment.redis.service;

import avito.assignment.redis.model.Entity;
import avito.assignment.redis.dao.EntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class EntityServiceImpl implements EntityService {

    @Autowired
    private EntityDao entityDao;

    @Override
    public boolean saveEntity(Entity entity) {
        return entityDao.saveEntity(entity);
    }

    @Override
    public Set<Entity> getAllKeys() {
        return entityDao.getAllKeys();
    }

    @Override
    public Entity getEntityById(String id) { return entityDao.getEntityById(id); }

    @Override
    public boolean deleteEntity(String id) {
        return entityDao.deleteEntity(id);
    }

}
