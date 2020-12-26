package avito.assignment.redis.dto;

import java.util.Arrays;
import java.util.List;

public class ListEntityImpl implements Entities<List<String>> {

    @Override
    public List<String> convert(String str) {
        return Arrays.asList(str.split(", "));
    }
}
