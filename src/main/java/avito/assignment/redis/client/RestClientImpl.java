package avito.assignment.redis.client;

import avito.assignment.redis.model.Entity;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Collections;

public class RestClientImpl implements RestClient {
    private final RestTemplate restTemplate = new RestTemplate();
    private static final HttpHeaders headers = new HttpHeaders();
    private ResponseEntity<String> response;
    private HttpEntity<String> entity = new HttpEntity<>(headers);

    private final String URL_CONSTANT = "http://localhost:8082/entity";

    public static void main(String[] args) {
        RestClientImpl restClient = new RestClientImpl();

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        restClient.operation("", "", HttpMethod.GET); //get all keys
        restClient.operation( "3","/", HttpMethod.GET); //get key by id
        restClient.operation("3", "/", HttpMethod.DELETE); //delete by id
        restClient.operation( "3","/", HttpMethod.GET); //get key by id (checking delete operation)
        restClient.operation("", "/save", HttpMethod.GET); //save dump file to directory
    }

    @Override
    public void post(String url, Entity entity) {

    }

    @Override
    public void operation(String id, String operator, HttpMethod http) {
        String resourceURL = URL_CONSTANT + operator + id;
        response = restTemplate.exchange(resourceURL, http, entity, String.class);
        System.out.println(response);
    }
}
