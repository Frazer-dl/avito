package avito.assignment.redis.dto;

public interface Entities<T> {

    T convert(String str);
}
