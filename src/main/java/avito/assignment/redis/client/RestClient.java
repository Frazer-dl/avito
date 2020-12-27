package avito.assignment.redis.client;

import avito.assignment.redis.model.Entity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

public interface RestClient {

    void post(String url, Entity entity);
    void operation(String id, String url, HttpMethod http);
}
