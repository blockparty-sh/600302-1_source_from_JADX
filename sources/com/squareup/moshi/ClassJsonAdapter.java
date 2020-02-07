package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter.Factory;
import com.squareup.moshi.JsonReader.Options;
import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.Nullable;

final class ClassJsonAdapter<T> extends JsonAdapter<T> {
    public static final Factory FACTORY = new Factory() {
        @Nullable
        public JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi) {
            if (!(type instanceof Class) && !(type instanceof ParameterizedType)) {
                return null;
            }
            Class rawType = Types.getRawType(type);
            if (rawType.isInterface() || rawType.isEnum()) {
                return null;
            }
            if (Util.isPlatformType(rawType) && !Types.isAllowedPlatformType(rawType)) {
                StringBuilder sb = new StringBuilder();
                sb.append("Platform ");
                sb.append(Util.typeAnnotatedWithAnnotations(type, set));
                sb.append(" requires explicit JsonAdapter to be registered");
                throw new IllegalArgumentException(sb.toString());
            } else if (!set.isEmpty()) {
                return null;
            } else {
                if (rawType.isAnonymousClass()) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Cannot serialize anonymous class ");
                    sb2.append(rawType.getName());
                    throw new IllegalArgumentException(sb2.toString());
                } else if (rawType.isLocalClass()) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("Cannot serialize local class ");
                    sb3.append(rawType.getName());
                    throw new IllegalArgumentException(sb3.toString());
                } else if (rawType.getEnclosingClass() != null && !Modifier.isStatic(rawType.getModifiers())) {
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append("Cannot serialize non-static nested class ");
                    sb4.append(rawType.getName());
                    throw new IllegalArgumentException(sb4.toString());
                } else if (!Modifier.isAbstract(rawType.getModifiers())) {
                    ClassFactory classFactory = ClassFactory.get(rawType);
                    TreeMap treeMap = new TreeMap();
                    while (type != Object.class) {
                        createFieldBindings(moshi, type, treeMap);
                        type = Types.getGenericSuperclass(type);
                    }
                    return new ClassJsonAdapter(classFactory, treeMap).nullSafe();
                } else {
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("Cannot serialize abstract class ");
                    sb5.append(rawType.getName());
                    throw new IllegalArgumentException(sb5.toString());
                }
            }
        }

        private void createFieldBindings(Moshi moshi, Type type, Map<String, FieldBinding<?>> map) {
            Field[] declaredFields;
            Class rawType = Types.getRawType(type);
            boolean isPlatformType = Util.isPlatformType(rawType);
            for (Field field : rawType.getDeclaredFields()) {
                if (includeField(isPlatformType, field.getModifiers())) {
                    Type resolve = Util.resolve(type, rawType, field.getGenericType());
                    Set jsonAnnotations = Util.jsonAnnotations((AnnotatedElement) field);
                    String name = field.getName();
                    JsonAdapter adapter = moshi.adapter(resolve, jsonAnnotations, name);
                    field.setAccessible(true);
                    Json json = (Json) field.getAnnotation(Json.class);
                    if (json != null) {
                        name = json.name();
                    }
                    FieldBinding fieldBinding = new FieldBinding(name, field, adapter);
                    FieldBinding fieldBinding2 = (FieldBinding) map.put(name, fieldBinding);
                    if (fieldBinding2 != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Conflicting fields:\n    ");
                        sb.append(fieldBinding2.field);
                        sb.append("\n    ");
                        sb.append(fieldBinding.field);
                        throw new IllegalArgumentException(sb.toString());
                    }
                }
            }
        }

        private boolean includeField(boolean z, int i) {
            if (Modifier.isStatic(i) || Modifier.isTransient(i)) {
                return false;
            }
            if (Modifier.isPublic(i) || Modifier.isProtected(i) || !z) {
                return true;
            }
            return false;
        }
    };
    private final ClassFactory<T> classFactory;
    private final FieldBinding<?>[] fieldsArray;
    private final Options options;

    static class FieldBinding<T> {
        final JsonAdapter<T> adapter;
        final Field field;
        final String name;

        FieldBinding(String str, Field field2, JsonAdapter<T> jsonAdapter) {
            this.name = str;
            this.field = field2;
            this.adapter = jsonAdapter;
        }

        /* access modifiers changed from: 0000 */
        public void read(JsonReader jsonReader, Object obj) throws IOException, IllegalAccessException {
            this.field.set(obj, this.adapter.fromJson(jsonReader));
        }

        /* access modifiers changed from: 0000 */
        public void write(JsonWriter jsonWriter, Object obj) throws IllegalAccessException, IOException {
            this.adapter.toJson(jsonWriter, this.field.get(obj));
        }
    }

    ClassJsonAdapter(ClassFactory<T> classFactory2, Map<String, FieldBinding<?>> map) {
        this.classFactory = classFactory2;
        this.fieldsArray = (FieldBinding[]) map.values().toArray(new FieldBinding[map.size()]);
        this.options = Options.m288of((String[]) map.keySet().toArray(new String[map.size()]));
    }

    public T fromJson(JsonReader jsonReader) throws IOException {
        try {
            T newInstance = this.classFactory.newInstance();
            try {
                jsonReader.beginObject();
                while (jsonReader.hasNext()) {
                    int selectName = jsonReader.selectName(this.options);
                    if (selectName == -1) {
                        jsonReader.skipName();
                        jsonReader.skipValue();
                    } else {
                        this.fieldsArray[selectName].read(jsonReader, newInstance);
                    }
                }
                jsonReader.endObject();
                return newInstance;
            } catch (IllegalAccessException unused) {
                throw new AssertionError();
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e2) {
            throw Util.rethrowCause(e2);
        } catch (IllegalAccessException unused2) {
            throw new AssertionError();
        }
    }

    public void toJson(JsonWriter jsonWriter, T t) throws IOException {
        FieldBinding<?>[] fieldBindingArr;
        try {
            jsonWriter.beginObject();
            for (FieldBinding<?> fieldBinding : this.fieldsArray) {
                jsonWriter.name(fieldBinding.name);
                fieldBinding.write(jsonWriter, t);
            }
            jsonWriter.endObject();
        } catch (IllegalAccessException unused) {
            throw new AssertionError();
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("JsonAdapter(");
        sb.append(this.classFactory);
        sb.append(")");
        return sb.toString();
    }
}
