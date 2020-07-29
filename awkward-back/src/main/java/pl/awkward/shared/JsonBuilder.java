package pl.awkward.shared;

import java.util.HashMap;
import java.util.Map;

public final class JsonBuilder <T, U> {

    private final Map<T, U> map;
    private boolean cache;
    private final StringBuilder builder;

    public JsonBuilder() {
        this(1);
    }

    public JsonBuilder(int initialize) {
        this.builder = new StringBuilder();
        if (initialize >= 0)
            this.map = new HashMap<>(initialize);
        else
            this.map = new HashMap<>();
    }

    public void put(T key, U object) {
        this.map.put(key, object);
        this.cache = false;
    }

    public String getJson() {
        if (!this.cache && !this.map.isEmpty()) {
            this.builder.setLength(0);
            this.builder.append("{\n");

            this.map.forEach(
                    (key, obj) -> this.builder.append("\t\"").append(key)
                            .append("\" : ")
                            .append("\"").append(obj).append("\",\n")
            );
            this.builder.deleteCharAt(this.builder.length() - 2);
            this.builder.append("}");
            this.cache = true;
        }
        return this.builder.toString();
    }
}
