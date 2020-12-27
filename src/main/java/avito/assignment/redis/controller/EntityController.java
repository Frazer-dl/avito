package avito.assignment.redis.controller;

import avito.assignment.redis.model.Entity;
import avito.assignment.redis.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ResponseEntity<List<Entity>> getAllEntity() {
        List<Entity> entities = new ArrayList<>(entityService.getAllKeys());
        return ResponseEntity.ok(entities);
    }


    @GetMapping("/entity/{id}")
    public ResponseEntity<Entity> getEntityById(@PathVariable("id") long id) {
        Entity entity;
        entity = entityService.getEntityById(id);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/entity/save")
    public ResponseEntity<String> saveDb() {
        boolean result = entityService.saveDb();
        if (result) {
            return ResponseEntity.ok("Dump saved successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(value = "/entity/{id}")
    public ResponseEntity<String> delEntityById(@PathVariable("id") long id) {
        boolean result = entityService.deleteEntity(id);
        if (result) {
            return ResponseEntity.ok("Entity deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
