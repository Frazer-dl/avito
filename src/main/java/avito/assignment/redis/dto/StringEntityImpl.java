package avito.assignment.redis.dto;

public class StringEntityImpl implements Entities<String> {

    @Override
    public String convert(String str) {
        return str;
    }
}
