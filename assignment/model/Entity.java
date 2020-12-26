package avito.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@AllArgsConstructor
@Getter
@Setter
@RedisHash("entity")
public class Entity implements Serializable {
    private static final long serialVersionUID = 1L;
    private @NonNull String type;
    private @NonNull String id;
    private @NonNull String entityBody;
}
