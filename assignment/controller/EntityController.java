package avito.assignment.controller;

import avito.assignment.dao.EntityRepository;
import avito.assignment.model.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.*;

@RestController
public class EntityController {


    @Autowired
    EntityRepository entityRepository;

    @GetMapping("/entity")
    public List<Entity> getEntities() {
        Iterable<Entity> entities = entityRepository.findAll();
        List<Entity> entityList = new ArrayList<>();
        entities.forEach(entityList::add);
        return entityList;
    }

    @GetMapping("/entity/{id}")
    public Optional<Entity> getEntity(@PathVariable String id) {
        return entityRepository.findById(id);
    }

    @PutMapping("/entity/{id}")
    public Optional<Entity> setEntity(@RequestBody Entity newEntity, @PathVariable String id) {
        Optional<Entity> optionalEntity = entityRepository.findById(id);
        if (optionalEntity.isPresent()) {
            Entity entity = optionalEntity.get();
            String entityBody = newEntity.getEntityBody();
            switch (entity.getType().toLowerCase(Locale.ROOT)) {
                case "string":
                    entity.setEntityBody(entityBody);
                    entity.setType();
                    break;
                case "list":
                    String[] strings = entityBody.split("(\"|')([^\"']+)");
                    entityRepository.save(entity);
            }
        }
            return optionalEntity;
    }
}

