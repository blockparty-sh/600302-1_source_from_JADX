package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter.Factory;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

abstract class CollectionJsonAdapter<C extends Collection<T>, T> extends JsonAdapter<C> {
    public static final Factory FACTORY = new Factory() {
        @Nullable
        public JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi) {
            Class<Set> rawType = Types.getRawType(type);
            if (!set.isEmpty()) {
                return null;
            }
            if (rawType == List.class || rawType == Collection.class) {
                return CollectionJsonAdapter.newArrayListAdapter(type, moshi).nullSafe();
            }
            if (rawType == Set.class) {
                return CollectionJsonAdapter.newLinkedHashSetAdapter(type, moshi).nullSafe();
            }
            return null;
        }
    };
    private final JsonAdapter<T> elementAdapter;

    /* access modifiers changed from: 0000 */
    public abstract C newCollection();

    private CollectionJsonAdapter(JsonAdapter<T> jsonAdapter) {
        this.elementAdapter = jsonAdapter;
    }

    static <T> JsonAdapter<Collection<T>> newArrayListAdapter(Type type, Moshi moshi) {
        return new CollectionJsonAdapter<Collection<T>, T>(moshi.adapter(Types.collectionElementType(type, Collection.class))) {
            public /* bridge */ /* synthetic */ Object fromJson(JsonReader jsonReader) throws IOException {
                return CollectionJsonAdapter.super.fromJson(jsonReader);
            }

            public /* bridge */ /* synthetic */ void toJson(JsonWriter jsonWriter, Object obj) throws IOException {
                CollectionJsonAdapter.super.toJson(jsonWriter, (Collection) obj);
            }

            /* access modifiers changed from: 0000 */
            public Collection<T> newCollection() {
                return new ArrayList();
            }
        };
    }

    static <T> JsonAdapter<Set<T>> newLinkedHashSetAdapter(Type type, Moshi moshi) {
        return new CollectionJsonAdapter<Set<T>, T>(moshi.adapter(Types.collectionElementType(type, Collection.class))) {
            public /* bridge */ /* synthetic */ Object fromJson(JsonReader jsonReader) throws IOException {
                return CollectionJsonAdapter.super.fromJson(jsonReader);
            }

            public /* bridge */ /* synthetic */ void toJson(JsonWriter jsonWriter, Object obj) throws IOException {
                CollectionJsonAdapter.super.toJson(jsonWriter, (Set) obj);
            }

            /* access modifiers changed from: 0000 */
            public Set<T> newCollection() {
                return new LinkedHashSet();
            }
        };
    }

    public C fromJson(JsonReader jsonReader) throws IOException {
        C newCollection = newCollection();
        jsonReader.beginArray();
        while (jsonReader.hasNext()) {
            newCollection.add(this.elementAdapter.fromJson(jsonReader));
        }
        jsonReader.endArray();
        return newCollection;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=C, code=C<java.lang.Object>, for r4v0, types: [C, C<java.lang.Object>, java.util.Collection] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void toJson(com.squareup.moshi.JsonWriter r3, C<java.lang.Object> r4) throws java.io.IOException {
        /*
            r2 = this;
            r3.beginArray()
            java.util.Iterator r4 = r4.iterator()
        L_0x0007:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L_0x0017
            java.lang.Object r0 = r4.next()
            com.squareup.moshi.JsonAdapter<T> r1 = r2.elementAdapter
            r1.toJson(r3, r0)
            goto L_0x0007
        L_0x0017:
            r3.endArray()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.CollectionJsonAdapter.toJson(com.squareup.moshi.JsonWriter, java.util.Collection):void");
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.elementAdapter);
        sb.append(".collection()");
        return sb.toString();
    }
}
