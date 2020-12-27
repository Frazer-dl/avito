package avito.assignment.redis.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("Entity")
public class Entity implements Serializable {
    private long id;
    private Object entityBody;
    private Long ttl;
}
