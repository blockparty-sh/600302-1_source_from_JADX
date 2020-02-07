package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter.Factory;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;

final class MapJsonAdapter<K, V> extends JsonAdapter<Map<K, V>> {
    public static final Factory FACTORY = new Factory() {
        @Nullable
        public JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi) {
            if (!set.isEmpty()) {
                return null;
            }
            Class<Map> rawType = Types.getRawType(type);
            if (rawType != Map.class) {
                return null;
            }
            Type[] mapKeyAndValueTypes = Types.mapKeyAndValueTypes(type, rawType);
            return new MapJsonAdapter(moshi, mapKeyAndValueTypes[0], mapKeyAndValueTypes[1]).nullSafe();
        }
    };
    private final JsonAdapter<K> keyAdapter;
    private final JsonAdapter<V> valueAdapter;

    MapJsonAdapter(Moshi moshi, Type type, Type type2) {
        this.keyAdapter = moshi.adapter(type);
        this.valueAdapter = moshi.adapter(type2);
    }

    public void toJson(JsonWriter jsonWriter, Map<K, V> map) throws IOException {
        jsonWriter.beginObject();
        for (Entry entry : map.entrySet()) {
            if (entry.getKey() != null) {
                jsonWriter.promoteValueToName();
                this.keyAdapter.toJson(jsonWriter, entry.getKey());
                this.valueAdapter.toJson(jsonWriter, entry.getValue());
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("Map key is null at ");
                sb.append(jsonWriter.getPath());
                throw new JsonDataException(sb.toString());
            }
        }
        jsonWriter.endObject();
    }

    public Map<K, V> fromJson(JsonReader jsonReader) throws IOException {
        LinkedHashTreeMap linkedHashTreeMap = new LinkedHashTreeMap();
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            jsonReader.promoteNameToValue();
            Object fromJson = this.keyAdapter.fromJson(jsonReader);
            Object fromJson2 = this.valueAdapter.fromJson(jsonReader);
            Object put = linkedHashTreeMap.put(fromJson, fromJson2);
            if (put != null) {
                StringBuilder sb = new StringBuilder();
                sb.append("Map key '");
                sb.append(fromJson);
                sb.append("' has multiple values at path ");
                sb.append(jsonReader.getPath());
                sb.append(": ");
                sb.append(put);
                sb.append(" and ");
                sb.append(fromJson2);
                throw new JsonDataException(sb.toString());
            }
        }
        jsonReader.endObject();
        return linkedHashTreeMap;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JsonAdapter(");
        sb.append(this.keyAdapter);
        sb.append("=");
        sb.append(this.valueAdapter);
        sb.append(")");
        return sb.toString();
    }
}
