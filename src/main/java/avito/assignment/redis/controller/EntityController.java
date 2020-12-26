package avito.assignment.redis.controller;

import avito.assignment.redis.model.Entity;
import avito.assignment.redis.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EntityController {

    @Autowired
    private EntityService entityService;

    @PostMapping("/entity")
    public ResponseEntity<String> saveEntity(@RequestBody Entity entity) {
        boolean result = entityService.saveEntity(entity);
        if (result) {
            return ResponseEntity.ok("Entity created successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/entity")
    public ResponseEntity<List<Entity>> fetchAllEntity() {
        List<Entity> entities;
        entities = entityService.getAllKeys();
        return ResponseEntity.ok(entities);
    }

    @GetMapping("/entity/{key}")
    public ResponseEntity<Entity> fetchEntityById(@PathVariable("key") String key) {
        Entity entity;
        entity = entityService.getEntityById(key);
        return ResponseEntity.ok(entity);
    }

    @DeleteMapping("/entity/{key}")
    public ResponseEntity<String> delEntityById(@PathVariable("key") String key) {
        boolean result = entityService.deleteEntity(key);
        if (result) {
            return ResponseEntity.ok("Entity created successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
