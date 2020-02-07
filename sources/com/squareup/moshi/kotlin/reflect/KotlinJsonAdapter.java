package com.squareup.moshi.kotlin.reflect;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.JsonDataException;
import com.squareup.moshi.JsonReader;
import com.squareup.moshi.JsonReader.Options;
import com.squareup.moshi.JsonWriter;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.AbstractMap;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KParameter;
import kotlin.reflect.KProperty1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002:\u0002\u001e\u001fB9\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u001c\u0010\u0005\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00070\u0006\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0015\u0010\u0012\u001a\u00028\u00002\u0006\u0010\u0013\u001a\u00020\u0014H\u0016¢\u0006\u0002\u0010\u0015J\u001f\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u001bJ\b\u0010\u001c\u001a\u00020\u001dH\u0016R'\u0010\u0005\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00028\u0000\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006 "}, mo37405d2 = {"Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter;", "T", "Lcom/squareup/moshi/JsonAdapter;", "constructor", "Lkotlin/reflect/KFunction;", "bindings", "", "Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter$Binding;", "", "options", "Lcom/squareup/moshi/JsonReader$Options;", "(Lkotlin/reflect/KFunction;Ljava/util/List;Lcom/squareup/moshi/JsonReader$Options;)V", "getBindings", "()Ljava/util/List;", "getConstructor", "()Lkotlin/reflect/KFunction;", "getOptions", "()Lcom/squareup/moshi/JsonReader$Options;", "fromJson", "reader", "Lcom/squareup/moshi/JsonReader;", "(Lcom/squareup/moshi/JsonReader;)Ljava/lang/Object;", "toJson", "", "writer", "Lcom/squareup/moshi/JsonWriter;", "value", "(Lcom/squareup/moshi/JsonWriter;Ljava/lang/Object;)V", "toString", "", "Binding", "IndexedParameterMap", "moshi-kotlin"}, mo37406k = 1, mo37407mv = {1, 1, 11})
/* compiled from: KotlinJsonAdapter.kt */
public final class KotlinJsonAdapter<T> extends JsonAdapter<T> {
    @NotNull
    private final List<Binding<T, Object>> bindings;
    @NotNull
    private final KFunction<T> constructor;
    @NotNull
    private final Options options;

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\b\u0018\u0000*\u0004\b\u0001\u0010\u0001*\u0004\b\u0002\u0010\u00022\u00020\u0003B9\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00020\u0007\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00020\u0007HÆ\u0003J\u0015\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\tHÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u000bHÆ\u0003JQ\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000bHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0003HÖ\u0003J\u0013\u0010\u001d\u001a\u00028\u00022\u0006\u0010\u001e\u001a\u00028\u0001¢\u0006\u0002\u0010\u001fJ\t\u0010 \u001a\u00020!HÖ\u0001J\u001b\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00028\u00012\u0006\u0010\u001e\u001a\u00028\u0002¢\u0006\u0002\u0010%J\t\u0010&\u001a\u00020\u0005HÖ\u0001R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006'"}, mo37405d2 = {"Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter$Binding;", "K", "P", "", "name", "", "adapter", "Lcom/squareup/moshi/JsonAdapter;", "property", "Lkotlin/reflect/KProperty1;", "parameter", "Lkotlin/reflect/KParameter;", "(Ljava/lang/String;Lcom/squareup/moshi/JsonAdapter;Lkotlin/reflect/KProperty1;Lkotlin/reflect/KParameter;)V", "getAdapter", "()Lcom/squareup/moshi/JsonAdapter;", "getName", "()Ljava/lang/String;", "getParameter", "()Lkotlin/reflect/KParameter;", "getProperty", "()Lkotlin/reflect/KProperty1;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "get", "value", "(Ljava/lang/Object;)Ljava/lang/Object;", "hashCode", "", "set", "", "result", "(Ljava/lang/Object;Ljava/lang/Object;)V", "toString", "moshi-kotlin"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: KotlinJsonAdapter.kt */
    public static final class Binding<K, P> {
        @NotNull
        private final JsonAdapter<P> adapter;
        @NotNull
        private final String name;
        @Nullable
        private final KParameter parameter;
        @NotNull
        private final KProperty1<K, P> property;

        /* JADX WARNING: Incorrect type for immutable var: ssa=com.squareup.moshi.JsonAdapter, code=com.squareup.moshi.JsonAdapter<P>, for r2v0, types: [com.squareup.moshi.JsonAdapter] */
        /* JADX WARNING: Incorrect type for immutable var: ssa=kotlin.reflect.KProperty1, code=kotlin.reflect.KProperty1<K, P>, for r3v0, types: [kotlin.reflect.KProperty1] */
        @org.jetbrains.annotations.NotNull
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static /* bridge */ /* synthetic */ com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter.Binding copy$default(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter.Binding r0, java.lang.String r1, com.squareup.moshi.JsonAdapter<P> r2, kotlin.reflect.KProperty1<K, P> r3, kotlin.reflect.KParameter r4, int r5, java.lang.Object r6) {
            /*
                r6 = r5 & 1
                if (r6 == 0) goto L_0x0006
                java.lang.String r1 = r0.name
            L_0x0006:
                r6 = r5 & 2
                if (r6 == 0) goto L_0x000c
                com.squareup.moshi.JsonAdapter<P> r2 = r0.adapter
            L_0x000c:
                r6 = r5 & 4
                if (r6 == 0) goto L_0x0012
                kotlin.reflect.KProperty1<K, P> r3 = r0.property
            L_0x0012:
                r5 = r5 & 8
                if (r5 == 0) goto L_0x0018
                kotlin.reflect.KParameter r4 = r0.parameter
            L_0x0018:
                com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter$Binding r0 = r0.copy(r1, r2, r3, r4)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter.Binding.copy$default(com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter$Binding, java.lang.String, com.squareup.moshi.JsonAdapter, kotlin.reflect.KProperty1, kotlin.reflect.KParameter, int, java.lang.Object):com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter$Binding");
        }

        @NotNull
        public final String component1() {
            return this.name;
        }

        @NotNull
        public final JsonAdapter<P> component2() {
            return this.adapter;
        }

        @NotNull
        public final KProperty1<K, P> component3() {
            return this.property;
        }

        @Nullable
        public final KParameter component4() {
            return this.parameter;
        }

        @NotNull
        public final Binding<K, P> copy(@NotNull String str, @NotNull JsonAdapter<P> jsonAdapter, @NotNull KProperty1<K, ? extends P> kProperty1, @Nullable KParameter kParameter) {
            Intrinsics.checkParameterIsNotNull(str, "name");
            Intrinsics.checkParameterIsNotNull(jsonAdapter, "adapter");
            Intrinsics.checkParameterIsNotNull(kProperty1, "property");
            return new Binding<>(str, jsonAdapter, kProperty1, kParameter);
        }

        /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
            if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.parameter, (java.lang.Object) r3.parameter) != false) goto L_0x0033;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
            /*
                r2 = this;
                if (r2 == r3) goto L_0x0033
                boolean r0 = r3 instanceof com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter.Binding
                if (r0 == 0) goto L_0x0031
                com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter$Binding r3 = (com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter.Binding) r3
                java.lang.String r0 = r2.name
                java.lang.String r1 = r3.name
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x0031
                com.squareup.moshi.JsonAdapter<P> r0 = r2.adapter
                com.squareup.moshi.JsonAdapter<P> r1 = r3.adapter
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x0031
                kotlin.reflect.KProperty1<K, P> r0 = r2.property
                kotlin.reflect.KProperty1<K, P> r1 = r3.property
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
                if (r0 == 0) goto L_0x0031
                kotlin.reflect.KParameter r0 = r2.parameter
                kotlin.reflect.KParameter r3 = r3.parameter
                boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
                if (r3 == 0) goto L_0x0031
                goto L_0x0033
            L_0x0031:
                r3 = 0
                return r3
            L_0x0033:
                r3 = 1
                return r3
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter.Binding.equals(java.lang.Object):boolean");
        }

        public int hashCode() {
            String str = this.name;
            int i = 0;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            JsonAdapter<P> jsonAdapter = this.adapter;
            int hashCode2 = (hashCode + (jsonAdapter != null ? jsonAdapter.hashCode() : 0)) * 31;
            KProperty1<K, P> kProperty1 = this.property;
            int hashCode3 = (hashCode2 + (kProperty1 != null ? kProperty1.hashCode() : 0)) * 31;
            KParameter kParameter = this.parameter;
            if (kParameter != null) {
                i = kParameter.hashCode();
            }
            return hashCode3 + i;
        }

        @NotNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Binding(name=");
            sb.append(this.name);
            sb.append(", adapter=");
            sb.append(this.adapter);
            sb.append(", property=");
            sb.append(this.property);
            sb.append(", parameter=");
            sb.append(this.parameter);
            sb.append(")");
            return sb.toString();
        }

        public Binding(@NotNull String str, @NotNull JsonAdapter<P> jsonAdapter, @NotNull KProperty1<K, ? extends P> kProperty1, @Nullable KParameter kParameter) {
            Intrinsics.checkParameterIsNotNull(str, "name");
            Intrinsics.checkParameterIsNotNull(jsonAdapter, "adapter");
            Intrinsics.checkParameterIsNotNull(kProperty1, "property");
            this.name = str;
            this.adapter = jsonAdapter;
            this.property = kProperty1;
            this.parameter = kParameter;
        }

        @NotNull
        public final String getName() {
            return this.name;
        }

        @NotNull
        public final JsonAdapter<P> getAdapter() {
            return this.adapter;
        }

        @NotNull
        public final KProperty1<K, P> getProperty() {
            return this.property;
        }

        @Nullable
        public final KParameter getParameter() {
            return this.parameter;
        }

        public final P get(K k) {
            return this.property.get(k);
        }

        public final void set(K k, P p) {
            if (p != KotlinJsonAdapterKt.ABSENT_VALUE) {
                KProperty1<K, P> kProperty1 = this.property;
                if (kProperty1 != null) {
                    ((KMutableProperty1) kProperty1).set(k, p);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.reflect.KMutableProperty1<K, P>");
            }
        }
    }

    @Metadata(mo37403bv = {1, 0, 2}, mo37404d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010&\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B#\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0007¢\u0006\u0002\u0010\bJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0002H\u0016J\u0013\u0010\u0016\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0015\u001a\u00020\u0002H\u0002R(\u0010\t\u001a\u0016\u0012\u0012\u0012\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u000b0\n8VX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0007¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0017"}, mo37405d2 = {"Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapter$IndexedParameterMap;", "Lkotlin/collections/AbstractMap;", "Lkotlin/reflect/KParameter;", "", "parameterKeys", "", "parameterValues", "", "(Ljava/util/List;[Ljava/lang/Object;)V", "entries", "", "", "getEntries", "()Ljava/util/Set;", "getParameterKeys", "()Ljava/util/List;", "getParameterValues", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "containsKey", "", "key", "get", "moshi-kotlin"}, mo37406k = 1, mo37407mv = {1, 1, 11})
    /* compiled from: KotlinJsonAdapter.kt */
    public static final class IndexedParameterMap extends AbstractMap<KParameter, Object> {
        @NotNull
        private final List<KParameter> parameterKeys;
        @NotNull
        private final Object[] parameterValues;

        public final /* bridge */ boolean containsKey(Object obj) {
            if (obj instanceof KParameter) {
                return containsKey((KParameter) obj);
            }
            return false;
        }

        public final /* bridge */ Object get(Object obj) {
            if (obj instanceof KParameter) {
                return get((KParameter) obj);
            }
            return null;
        }

        public final /* bridge */ Object getOrDefault(Object obj, Object obj2) {
            return obj instanceof KParameter ? getOrDefault((KParameter) obj, obj2) : obj2;
        }

        public /* bridge */ Object getOrDefault(KParameter kParameter, Object obj) {
            return super.getOrDefault(kParameter, obj);
        }

        @NotNull
        public final List<KParameter> getParameterKeys() {
            return this.parameterKeys;
        }

        @NotNull
        public final Object[] getParameterValues() {
            return this.parameterValues;
        }

        public IndexedParameterMap(@NotNull List<? extends KParameter> list, @NotNull Object[] objArr) {
            Intrinsics.checkParameterIsNotNull(list, "parameterKeys");
            Intrinsics.checkParameterIsNotNull(objArr, "parameterValues");
            this.parameterKeys = list;
            this.parameterValues = objArr;
        }

        @NotNull
        public Set<Entry<KParameter, Object>> getEntries() {
            Iterable<KParameter> iterable = this.parameterKeys;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            int i = 0;
            for (KParameter simpleEntry : iterable) {
                int i2 = i + 1;
                arrayList.add(new SimpleEntry(simpleEntry, this.parameterValues[i]));
                i = i2;
            }
            Iterable iterable2 = (List) arrayList;
            Collection linkedHashSet = new LinkedHashSet();
            for (Object next : iterable2) {
                if (((SimpleEntry) next).getValue() != KotlinJsonAdapterKt.ABSENT_VALUE) {
                    linkedHashSet.add(next);
                }
            }
            return (Set) linkedHashSet;
        }

        public boolean containsKey(@NotNull KParameter kParameter) {
            Intrinsics.checkParameterIsNotNull(kParameter, "key");
            return this.parameterValues[kParameter.getIndex()] != KotlinJsonAdapterKt.ABSENT_VALUE;
        }

        @Nullable
        public Object get(@NotNull KParameter kParameter) {
            Intrinsics.checkParameterIsNotNull(kParameter, "key");
            Object obj = this.parameterValues[kParameter.getIndex()];
            if (obj != KotlinJsonAdapterKt.ABSENT_VALUE) {
                return obj;
            }
            return null;
        }
    }

    @NotNull
    public final KFunction<T> getConstructor() {
        return this.constructor;
    }

    @NotNull
    public final List<Binding<T, Object>> getBindings() {
        return this.bindings;
    }

    public KotlinJsonAdapter(@NotNull KFunction<? extends T> kFunction, @NotNull List<Binding<T, Object>> list, @NotNull Options options2) {
        Intrinsics.checkParameterIsNotNull(kFunction, "constructor");
        Intrinsics.checkParameterIsNotNull(list, "bindings");
        Intrinsics.checkParameterIsNotNull(options2, "options");
        this.constructor = kFunction;
        this.bindings = list;
        this.options = options2;
    }

    @NotNull
    public final Options getOptions() {
        return this.options;
    }

    public T fromJson(@NotNull JsonReader jsonReader) {
        Intrinsics.checkParameterIsNotNull(jsonReader, "reader");
        int size = this.constructor.getParameters().size();
        Object[] objArr = new Object[this.bindings.size()];
        int length = objArr.length;
        for (int i = 0; i < length; i++) {
            objArr[i] = KotlinJsonAdapterKt.ABSENT_VALUE;
        }
        jsonReader.beginObject();
        while (true) {
            Binding binding = null;
            if (jsonReader.hasNext()) {
                int selectName = jsonReader.selectName(this.options);
                if (selectName != -1) {
                    binding = (Binding) this.bindings.get(selectName);
                }
                if (binding == null) {
                    jsonReader.skipName();
                    jsonReader.skipValue();
                } else if (objArr[selectName] == KotlinJsonAdapterKt.ABSENT_VALUE) {
                    objArr[selectName] = binding.getAdapter().fromJson(jsonReader);
                    if (objArr[selectName] == null && !binding.getProperty().getReturnType().isMarkedNullable()) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Non-null value '");
                        sb.append(binding.getProperty().getName());
                        sb.append("' was null at ");
                        sb.append(jsonReader.getPath());
                        throw new JsonDataException(sb.toString());
                    }
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Multiple values for '");
                    sb2.append(((KParameter) this.constructor.getParameters().get(selectName)).getName());
                    sb2.append("' at ");
                    sb2.append(jsonReader.getPath());
                    throw new JsonDataException(sb2.toString());
                }
            } else {
                jsonReader.endObject();
                for (int i2 = 0; i2 < size; i2++) {
                    if (objArr[i2] == KotlinJsonAdapterKt.ABSENT_VALUE && !((KParameter) this.constructor.getParameters().get(i2)).isOptional()) {
                        if (((KParameter) this.constructor.getParameters().get(i2)).getType().isMarkedNullable()) {
                            objArr[i2] = null;
                        } else {
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append("Required value '");
                            sb3.append(((KParameter) this.constructor.getParameters().get(i2)).getName());
                            sb3.append("' missing at ");
                            sb3.append(jsonReader.getPath());
                            throw new JsonDataException(sb3.toString());
                        }
                    }
                }
                KFunction<T> kFunction = this.constructor;
                T callBy = kFunction.callBy(new IndexedParameterMap(kFunction.getParameters(), objArr));
                int size2 = this.bindings.size();
                while (size < size2) {
                    Object obj = this.bindings.get(size);
                    if (obj == null) {
                        Intrinsics.throwNpe();
                    }
                    ((Binding) obj).set(callBy, objArr[size]);
                    size++;
                }
                return callBy;
            }
        }
    }

    public void toJson(@NotNull JsonWriter jsonWriter, @Nullable T t) {
        Intrinsics.checkParameterIsNotNull(jsonWriter, "writer");
        if (t != null) {
            jsonWriter.beginObject();
            for (Binding binding : this.bindings) {
                if (binding != null) {
                    jsonWriter.name(binding.getName());
                    binding.getAdapter().toJson(jsonWriter, binding.get(t));
                }
            }
            jsonWriter.endObject();
            return;
        }
        throw new NullPointerException("value == null");
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("KotlinJsonAdapter(");
        sb.append(this.constructor.getReturnType());
        sb.append(')');
        return sb.toString();
    }
}
