package avito.assignment.redis.service;

import avito.assignment.redis.model.Entity;
import avito.assignment.redis.dao.EntityDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntityServiceImpl implements EntityService {

    @Autowired
    private EntityDao entityDao;

    @Override
    public boolean saveEntity(Entity entity) {
        return entityDao.saveEntity(entity);
    }

    @Override
    public List<Entity> getAllKeys() {
        return entityDao.getAllKeys();
    }

    @Override
    public Entity getEntityById(String key) {
        return entityDao.getEntityById(key);
    }

    @Override
    public boolean deleteEntity(String key) {
        return entityDao.deleteEntity(key);
    }
}
