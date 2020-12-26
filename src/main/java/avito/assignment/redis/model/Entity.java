package avito.assignment.redis.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Entity implements Serializable {
    private String key;
    private String type;
    private String entityBody;
    private Integer ttl;
}
