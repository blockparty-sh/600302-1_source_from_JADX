package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter.Factory;
import com.squareup.moshi.JsonReader.Options;
import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.text.Typography;

final class StandardJsonAdapters {
    static final JsonAdapter<Boolean> BOOLEAN_JSON_ADAPTER = new JsonAdapter<Boolean>() {
        public String toString() {
            return "JsonAdapter(Boolean)";
        }

        public Boolean fromJson(JsonReader jsonReader) throws IOException {
            return Boolean.valueOf(jsonReader.nextBoolean());
        }

        public void toJson(JsonWriter jsonWriter, Boolean bool) throws IOException {
            jsonWriter.value(bool.booleanValue());
        }
    };
    static final JsonAdapter<Byte> BYTE_JSON_ADAPTER = new JsonAdapter<Byte>() {
        public String toString() {
            return "JsonAdapter(Byte)";
        }

        public Byte fromJson(JsonReader jsonReader) throws IOException {
            return Byte.valueOf((byte) StandardJsonAdapters.rangeCheckNextInt(jsonReader, "a byte", -128, 255));
        }

        public void toJson(JsonWriter jsonWriter, Byte b) throws IOException {
            jsonWriter.value((long) (b.intValue() & 255));
        }
    };
    static final JsonAdapter<Character> CHARACTER_JSON_ADAPTER = new JsonAdapter<Character>() {
        public String toString() {
            return "JsonAdapter(Character)";
        }

        public Character fromJson(JsonReader jsonReader) throws IOException {
            String nextString = jsonReader.nextString();
            if (nextString.length() <= 1) {
                return Character.valueOf(nextString.charAt(0));
            }
            StringBuilder sb = new StringBuilder();
            sb.append(Typography.quote);
            sb.append(nextString);
            sb.append(Typography.quote);
            throw new JsonDataException(String.format(StandardJsonAdapters.ERROR_FORMAT, new Object[]{"a char", sb.toString(), jsonReader.getPath()}));
        }

        public void toJson(JsonWriter jsonWriter, Character ch) throws IOException {
            jsonWriter.value(ch.toString());
        }
    };
    static final JsonAdapter<Double> DOUBLE_JSON_ADAPTER = new JsonAdapter<Double>() {
        public String toString() {
            return "JsonAdapter(Double)";
        }

        public Double fromJson(JsonReader jsonReader) throws IOException {
            return Double.valueOf(jsonReader.nextDouble());
        }

        public void toJson(JsonWriter jsonWriter, Double d) throws IOException {
            jsonWriter.value(d.doubleValue());
        }
    };
    private static final String ERROR_FORMAT = "Expected %s but was %s at path %s";
    public static final Factory FACTORY = new Factory() {
        public JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi) {
            if (!set.isEmpty()) {
                return null;
            }
            if (type == Boolean.TYPE) {
                return StandardJsonAdapters.BOOLEAN_JSON_ADAPTER;
            }
            if (type == Byte.TYPE) {
                return StandardJsonAdapters.BYTE_JSON_ADAPTER;
            }
            if (type == Character.TYPE) {
                return StandardJsonAdapters.CHARACTER_JSON_ADAPTER;
            }
            if (type == Double.TYPE) {
                return StandardJsonAdapters.DOUBLE_JSON_ADAPTER;
            }
            if (type == Float.TYPE) {
                return StandardJsonAdapters.FLOAT_JSON_ADAPTER;
            }
            if (type == Integer.TYPE) {
                return StandardJsonAdapters.INTEGER_JSON_ADAPTER;
            }
            if (type == Long.TYPE) {
                return StandardJsonAdapters.LONG_JSON_ADAPTER;
            }
            if (type == Short.TYPE) {
                return StandardJsonAdapters.SHORT_JSON_ADAPTER;
            }
            if (type == Boolean.class) {
                return StandardJsonAdapters.BOOLEAN_JSON_ADAPTER.nullSafe();
            }
            if (type == Byte.class) {
                return StandardJsonAdapters.BYTE_JSON_ADAPTER.nullSafe();
            }
            if (type == Character.class) {
                return StandardJsonAdapters.CHARACTER_JSON_ADAPTER.nullSafe();
            }
            if (type == Double.class) {
                return StandardJsonAdapters.DOUBLE_JSON_ADAPTER.nullSafe();
            }
            if (type == Float.class) {
                return StandardJsonAdapters.FLOAT_JSON_ADAPTER.nullSafe();
            }
            if (type == Integer.class) {
                return StandardJsonAdapters.INTEGER_JSON_ADAPTER.nullSafe();
            }
            if (type == Long.class) {
                return StandardJsonAdapters.LONG_JSON_ADAPTER.nullSafe();
            }
            if (type == Short.class) {
                return StandardJsonAdapters.SHORT_JSON_ADAPTER.nullSafe();
            }
            if (type == String.class) {
                return StandardJsonAdapters.STRING_JSON_ADAPTER.nullSafe();
            }
            if (type == Object.class) {
                return new ObjectJsonAdapter(moshi).nullSafe();
            }
            Class rawType = Types.getRawType(type);
            JsonAdapter<?> generatedAdapter = Util.generatedAdapter(moshi, type, rawType);
            if (generatedAdapter != null) {
                return generatedAdapter;
            }
            if (rawType.isEnum()) {
                return new EnumJsonAdapter(rawType).nullSafe();
            }
            return null;
        }
    };
    static final JsonAdapter<Float> FLOAT_JSON_ADAPTER = new JsonAdapter<Float>() {
        public String toString() {
            return "JsonAdapter(Float)";
        }

        public Float fromJson(JsonReader jsonReader) throws IOException {
            float nextDouble = (float) jsonReader.nextDouble();
            if (jsonReader.isLenient() || !Float.isInfinite(nextDouble)) {
                return Float.valueOf(nextDouble);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("JSON forbids NaN and infinities: ");
            sb.append(nextDouble);
            sb.append(" at path ");
            sb.append(jsonReader.getPath());
            throw new JsonDataException(sb.toString());
        }

        public void toJson(JsonWriter jsonWriter, Float f) throws IOException {
            if (f != null) {
                jsonWriter.value((Number) f);
                return;
            }
            throw new NullPointerException();
        }
    };
    static final JsonAdapter<Integer> INTEGER_JSON_ADAPTER = new JsonAdapter<Integer>() {
        public String toString() {
            return "JsonAdapter(Integer)";
        }

        public Integer fromJson(JsonReader jsonReader) throws IOException {
            return Integer.valueOf(jsonReader.nextInt());
        }

        public void toJson(JsonWriter jsonWriter, Integer num) throws IOException {
            jsonWriter.value((long) num.intValue());
        }
    };
    static final JsonAdapter<Long> LONG_JSON_ADAPTER = new JsonAdapter<Long>() {
        public String toString() {
            return "JsonAdapter(Long)";
        }

        public Long fromJson(JsonReader jsonReader) throws IOException {
            return Long.valueOf(jsonReader.nextLong());
        }

        public void toJson(JsonWriter jsonWriter, Long l) throws IOException {
            jsonWriter.value(l.longValue());
        }
    };
    static final JsonAdapter<Short> SHORT_JSON_ADAPTER = new JsonAdapter<Short>() {
        public String toString() {
            return "JsonAdapter(Short)";
        }

        public Short fromJson(JsonReader jsonReader) throws IOException {
            return Short.valueOf((short) StandardJsonAdapters.rangeCheckNextInt(jsonReader, "a short", -32768, 32767));
        }

        public void toJson(JsonWriter jsonWriter, Short sh) throws IOException {
            jsonWriter.value((long) sh.intValue());
        }
    };
    static final JsonAdapter<String> STRING_JSON_ADAPTER = new JsonAdapter<String>() {
        public String toString() {
            return "JsonAdapter(String)";
        }

        public String fromJson(JsonReader jsonReader) throws IOException {
            return jsonReader.nextString();
        }

        public void toJson(JsonWriter jsonWriter, String str) throws IOException {
            jsonWriter.value(str);
        }
    };

    static final class EnumJsonAdapter<T extends Enum<T>> extends JsonAdapter<T> {
        private final T[] constants;
        private final Class<T> enumType;
        private final String[] nameStrings;
        private final Options options;

        EnumJsonAdapter(Class<T> cls) {
            this.enumType = cls;
            try {
                this.constants = (Enum[]) cls.getEnumConstants();
                this.nameStrings = new String[this.constants.length];
                for (int i = 0; i < this.constants.length; i++) {
                    T t = this.constants[i];
                    Json json = (Json) cls.getField(t.name()).getAnnotation(Json.class);
                    this.nameStrings[i] = json != null ? json.name() : t.name();
                }
                this.options = Options.m288of(this.nameStrings);
            } catch (NoSuchFieldException e) {
                StringBuilder sb = new StringBuilder();
                sb.append("Missing field in ");
                sb.append(cls.getName());
                throw new AssertionError(sb.toString(), e);
            }
        }

        public T fromJson(JsonReader jsonReader) throws IOException {
            int selectString = jsonReader.selectString(this.options);
            if (selectString != -1) {
                return this.constants[selectString];
            }
            String path = jsonReader.getPath();
            String nextString = jsonReader.nextString();
            StringBuilder sb = new StringBuilder();
            sb.append("Expected one of ");
            sb.append(Arrays.asList(this.nameStrings));
            sb.append(" but was ");
            sb.append(nextString);
            sb.append(" at path ");
            sb.append(path);
            throw new JsonDataException(sb.toString());
        }

        public void toJson(JsonWriter jsonWriter, T t) throws IOException {
            jsonWriter.value(this.nameStrings[t.ordinal()]);
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("JsonAdapter(");
            sb.append(this.enumType.getName());
            sb.append(")");
            return sb.toString();
        }
    }

    static final class ObjectJsonAdapter extends JsonAdapter<Object> {
        private final JsonAdapter<Boolean> booleanAdapter;
        private final JsonAdapter<Double> doubleAdapter;
        private final JsonAdapter<List> listJsonAdapter;
        private final JsonAdapter<Map> mapAdapter;
        private final Moshi moshi;
        private final JsonAdapter<String> stringAdapter;

        public String toString() {
            return "JsonAdapter(Object)";
        }

        ObjectJsonAdapter(Moshi moshi2) {
            this.moshi = moshi2;
            this.listJsonAdapter = moshi2.adapter(List.class);
            this.mapAdapter = moshi2.adapter(Map.class);
            this.stringAdapter = moshi2.adapter(String.class);
            this.doubleAdapter = moshi2.adapter(Double.class);
            this.booleanAdapter = moshi2.adapter(Boolean.class);
        }

        public Object fromJson(JsonReader jsonReader) throws IOException {
            switch (jsonReader.peek()) {
                case BEGIN_ARRAY:
                    return this.listJsonAdapter.fromJson(jsonReader);
                case BEGIN_OBJECT:
                    return this.mapAdapter.fromJson(jsonReader);
                case STRING:
                    return this.stringAdapter.fromJson(jsonReader);
                case NUMBER:
                    return this.doubleAdapter.fromJson(jsonReader);
                case BOOLEAN:
                    return this.booleanAdapter.fromJson(jsonReader);
                case NULL:
                    return jsonReader.nextNull();
                default:
                    StringBuilder sb = new StringBuilder();
                    sb.append("Expected a value but was ");
                    sb.append(jsonReader.peek());
                    sb.append(" at path ");
                    sb.append(jsonReader.getPath());
                    throw new IllegalStateException(sb.toString());
            }
        }

        public void toJson(JsonWriter jsonWriter, Object obj) throws IOException {
            Class<Object> cls = obj.getClass();
            if (cls == Object.class) {
                jsonWriter.beginObject();
                jsonWriter.endObject();
                return;
            }
            this.moshi.adapter((Type) toJsonType(cls), Util.NO_ANNOTATIONS).toJson(jsonWriter, obj);
        }

        /* JADX WARNING: Incorrect type for immutable var: ssa=java.lang.Class<?>, code=java.lang.Class, for r2v0, types: [java.lang.Class<?>, java.lang.Class] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private java.lang.Class<?> toJsonType(java.lang.Class r2) {
            /*
                r1 = this;
                java.lang.Class<java.util.Map> r0 = java.util.Map.class
                boolean r0 = r0.isAssignableFrom(r2)
                if (r0 == 0) goto L_0x000b
                java.lang.Class<java.util.Map> r2 = java.util.Map.class
                return r2
            L_0x000b:
                java.lang.Class<java.util.Collection> r0 = java.util.Collection.class
                boolean r0 = r0.isAssignableFrom(r2)
                if (r0 == 0) goto L_0x0015
                java.lang.Class<java.util.Collection> r2 = java.util.Collection.class
            L_0x0015:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.StandardJsonAdapters.ObjectJsonAdapter.toJsonType(java.lang.Class):java.lang.Class");
        }
    }

    private StandardJsonAdapters() {
    }

    static int rangeCheckNextInt(JsonReader jsonReader, String str, int i, int i2) throws IOException {
        int nextInt = jsonReader.nextInt();
        if (nextInt >= i && nextInt <= i2) {
            return nextInt;
        }
        throw new JsonDataException(String.format(ERROR_FORMAT, new Object[]{str, Integer.valueOf(nextInt), jsonReader.getPath()}));
    }
}
