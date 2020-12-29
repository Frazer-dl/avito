package avito.assignment.redis;

import avito.assignment.redis.model.Entity;

import java.util.ArrayList;
import java.util.List;

public final class ITUtill {
    public static final String REDIS_CONNECTION_OK = "PONG";
    public static final String ENTITY_CREATED = "<200 OK OK,Entity created successfully!,[]>";

    //Entity param
    public static final Long ID = 1L;
    public static final Object ENTITY_BODY = "{Key1:[String1, String2], Key2:String3}";
    public static final Long TTL = 10L;



    public static Entity getEntity() {
        Entity entity = new Entity();
        entity.setId(ID);
        entity.setEntityBody(ENTITY_BODY);
        return entity;
    }

    public static List<Entity> getEntities() {
        List<Entity> list = new ArrayList<>();
        list.add(getEntity());
        list.add(getEntity());
        list.get(1).setId(2L);
        list.add(getEntity());
        list.get(2).setId(3L);
        return list;
    }

    public static List<Entity> saveEntity(Entity entity) {
        return ITUtill.getEntities();
    }

    public static List<Entity> delEntity(Long id) {
        List<Entity> list = getEntities();
        list.removeIf(entity -> entity.getId() == id);
        return list;
    }
}
