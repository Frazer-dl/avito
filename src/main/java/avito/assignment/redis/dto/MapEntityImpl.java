package avito.assignment.redis.dto;

import java.util.HashMap;
import java.util.Map;

public class MapEntityImpl implements Entities<Map<String, String>> {

    @Override
    public Map<String, String> convert(String str) {
        Map<String, String> map = new HashMap<>();
        String[] pairs = str.split(", ");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":");
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }
}
