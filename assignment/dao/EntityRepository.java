package avito.assignment.dao;

import avito.assignment.model.Entity;
import org.springframework.data.repository.CrudRepository;

public interface EntityRepository extends CrudRepository<Entity, String> {

}
