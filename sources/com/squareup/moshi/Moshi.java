package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter.Factory;
import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

public final class Moshi {
    static final List<Factory> BUILT_IN_FACTORIES = new ArrayList(5);
    /* access modifiers changed from: private */
    public final Map<Object, JsonAdapter<?>> adapterCache = new LinkedHashMap();
    private final List<Factory> factories;
    /* access modifiers changed from: private */
    public final ThreadLocal<LookupChain> lookupChainThreadLocal = new ThreadLocal<>();

    public static final class Builder {
        final List<Factory> factories = new ArrayList();

        public <T> Builder add(final Type type, final JsonAdapter<T> jsonAdapter) {
            if (type == null) {
                throw new IllegalArgumentException("type == null");
            } else if (jsonAdapter != null) {
                return add((Factory) new Factory() {
                    @Nullable
                    public JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi) {
                        if (!set.isEmpty() || !Util.typesMatch(type, type)) {
                            return null;
                        }
                        return jsonAdapter;
                    }
                });
            } else {
                throw new IllegalArgumentException("jsonAdapter == null");
            }
        }

        public <T> Builder add(final Type type, final Class<? extends Annotation> cls, final JsonAdapter<T> jsonAdapter) {
            if (type == null) {
                throw new IllegalArgumentException("type == null");
            } else if (cls == null) {
                throw new IllegalArgumentException("annotation == null");
            } else if (jsonAdapter == null) {
                throw new IllegalArgumentException("jsonAdapter == null");
            } else if (!cls.isAnnotationPresent(JsonQualifier.class)) {
                StringBuilder sb = new StringBuilder();
                sb.append(cls);
                sb.append(" does not have @JsonQualifier");
                throw new IllegalArgumentException(sb.toString());
            } else if (cls.getDeclaredMethods().length <= 0) {
                return add((Factory) new Factory() {
                    @Nullable
                    public JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi) {
                        if (!Util.typesMatch(type, type) || set.size() != 1 || !Util.isAnnotationPresent(set, cls)) {
                            return null;
                        }
                        return jsonAdapter;
                    }
                });
            } else {
                throw new IllegalArgumentException("Use JsonAdapter.Factory for annotations with elements");
            }
        }

        public Builder add(Factory factory) {
            if (factory != null) {
                this.factories.add(factory);
                return this;
            }
            throw new IllegalArgumentException("factory == null");
        }

        public Builder add(Object obj) {
            if (obj != null) {
                return add((Factory) AdapterMethodsFactory.get(obj));
            }
            throw new IllegalArgumentException("adapter == null");
        }

        /* access modifiers changed from: 0000 */
        public Builder addAll(List<Factory> list) {
            this.factories.addAll(list);
            return this;
        }

        @CheckReturnValue
        public Moshi build() {
            return new Moshi(this);
        }
    }

    static final class Lookup<T> extends JsonAdapter<T> {
        @Nullable
        JsonAdapter<T> adapter;
        final Object cacheKey;
        @Nullable
        final String fieldName;
        final Type type;

        Lookup(Type type2, @Nullable String str, Object obj) {
            this.type = type2;
            this.fieldName = str;
            this.cacheKey = obj;
        }

        public T fromJson(JsonReader jsonReader) throws IOException {
            JsonAdapter<T> jsonAdapter = this.adapter;
            if (jsonAdapter != null) {
                return jsonAdapter.fromJson(jsonReader);
            }
            throw new IllegalStateException("JsonAdapter isn't ready");
        }

        public void toJson(JsonWriter jsonWriter, T t) throws IOException {
            JsonAdapter<T> jsonAdapter = this.adapter;
            if (jsonAdapter != null) {
                jsonAdapter.toJson(jsonWriter, t);
                return;
            }
            throw new IllegalStateException("JsonAdapter isn't ready");
        }

        public String toString() {
            JsonAdapter<T> jsonAdapter = this.adapter;
            return jsonAdapter != null ? jsonAdapter.toString() : super.toString();
        }
    }

    final class LookupChain {
        final List<Lookup<?>> callLookups = new ArrayList();
        boolean exceptionAnnotated;
        final Deque<Lookup<?>> stack = new ArrayDeque();

        LookupChain() {
        }

        /* JADX WARNING: type inference failed for: r2v4, types: [com.squareup.moshi.JsonAdapter<T>] */
        /* access modifiers changed from: 0000 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* JADX WARNING: Unknown variable types count: 1 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public <T> com.squareup.moshi.JsonAdapter<T> push(java.lang.reflect.Type r5, @javax.annotation.Nullable java.lang.String r6, java.lang.Object r7) {
            /*
                r4 = this;
                java.util.List<com.squareup.moshi.Moshi$Lookup<?>> r0 = r4.callLookups
                int r0 = r0.size()
                r1 = 0
            L_0x0007:
                if (r1 >= r0) goto L_0x0028
                java.util.List<com.squareup.moshi.Moshi$Lookup<?>> r2 = r4.callLookups
                java.lang.Object r2 = r2.get(r1)
                com.squareup.moshi.Moshi$Lookup r2 = (com.squareup.moshi.Moshi.Lookup) r2
                java.lang.Object r3 = r2.cacheKey
                boolean r3 = r3.equals(r7)
                if (r3 == 0) goto L_0x0025
                java.util.Deque<com.squareup.moshi.Moshi$Lookup<?>> r5 = r4.stack
                r5.add(r2)
                com.squareup.moshi.JsonAdapter<T> r5 = r2.adapter
                if (r5 == 0) goto L_0x0024
                com.squareup.moshi.JsonAdapter<T> r2 = r2.adapter
            L_0x0024:
                return r2
            L_0x0025:
                int r1 = r1 + 1
                goto L_0x0007
            L_0x0028:
                com.squareup.moshi.Moshi$Lookup r0 = new com.squareup.moshi.Moshi$Lookup
                r0.<init>(r5, r6, r7)
                java.util.List<com.squareup.moshi.Moshi$Lookup<?>> r5 = r4.callLookups
                r5.add(r0)
                java.util.Deque<com.squareup.moshi.Moshi$Lookup<?>> r5 = r4.stack
                r5.add(r0)
                r5 = 0
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.Moshi.LookupChain.push(java.lang.reflect.Type, java.lang.String, java.lang.Object):com.squareup.moshi.JsonAdapter");
        }

        /* access modifiers changed from: 0000 */
        public <T> void adapterFound(JsonAdapter<T> jsonAdapter) {
            ((Lookup) this.stack.getLast()).adapter = jsonAdapter;
        }

        /* access modifiers changed from: 0000 */
        public void pop(boolean z) {
            this.stack.removeLast();
            if (this.stack.isEmpty()) {
                Moshi.this.lookupChainThreadLocal.remove();
                if (z) {
                    synchronized (Moshi.this.adapterCache) {
                        int size = this.callLookups.size();
                        for (int i = 0; i < size; i++) {
                            Lookup lookup = (Lookup) this.callLookups.get(i);
                            JsonAdapter<T> jsonAdapter = (JsonAdapter) Moshi.this.adapterCache.put(lookup.cacheKey, lookup.adapter);
                            if (jsonAdapter != null) {
                                lookup.adapter = jsonAdapter;
                                Moshi.this.adapterCache.put(lookup.cacheKey, jsonAdapter);
                            }
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public IllegalArgumentException exceptionWithLookupStack(IllegalArgumentException illegalArgumentException) {
            if (this.exceptionAnnotated) {
                return illegalArgumentException;
            }
            this.exceptionAnnotated = true;
            if (this.stack.size() == 1 && ((Lookup) this.stack.getFirst()).fieldName == null) {
                return illegalArgumentException;
            }
            StringBuilder sb = new StringBuilder(illegalArgumentException.getMessage());
            Iterator descendingIterator = this.stack.descendingIterator();
            while (descendingIterator.hasNext()) {
                Lookup lookup = (Lookup) descendingIterator.next();
                sb.append("\nfor ");
                sb.append(lookup.type);
                if (lookup.fieldName != null) {
                    sb.append(' ');
                    sb.append(lookup.fieldName);
                }
            }
            return new IllegalArgumentException(sb.toString(), illegalArgumentException);
        }
    }

    static {
        BUILT_IN_FACTORIES.add(StandardJsonAdapters.FACTORY);
        BUILT_IN_FACTORIES.add(CollectionJsonAdapter.FACTORY);
        BUILT_IN_FACTORIES.add(MapJsonAdapter.FACTORY);
        BUILT_IN_FACTORIES.add(ArrayJsonAdapter.FACTORY);
        BUILT_IN_FACTORIES.add(ClassJsonAdapter.FACTORY);
    }

    Moshi(Builder builder) {
        ArrayList arrayList = new ArrayList(builder.factories.size() + BUILT_IN_FACTORIES.size());
        arrayList.addAll(builder.factories);
        arrayList.addAll(BUILT_IN_FACTORIES);
        this.factories = Collections.unmodifiableList(arrayList);
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> adapter(Type type) {
        return adapter(type, Util.NO_ANNOTATIONS);
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> adapter(Class<T> cls) {
        return adapter((Type) cls, Util.NO_ANNOTATIONS);
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> adapter(Type type, Class<? extends Annotation> cls) {
        if (cls != null) {
            return adapter(type, Collections.singleton(Types.createJsonQualifierImplementation(cls)));
        }
        throw new NullPointerException("annotationType == null");
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> adapter(Type type, Class<? extends Annotation>... clsArr) {
        if (clsArr.length == 1) {
            return adapter(type, clsArr[0]);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(clsArr.length);
        for (Class<? extends Annotation> createJsonQualifierImplementation : clsArr) {
            linkedHashSet.add(Types.createJsonQualifierImplementation(createJsonQualifierImplementation));
        }
        return adapter(type, Collections.unmodifiableSet(linkedHashSet));
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> adapter(Type type, Set<? extends Annotation> set) {
        return adapter(type, set, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001c, code lost:
        r1 = (com.squareup.moshi.Moshi.LookupChain) r4.lookupChainThreadLocal.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        if (r1 != null) goto L_0x0030;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0026, code lost:
        r1 = new com.squareup.moshi.Moshi.LookupChain(r4);
        r4.lookupChainThreadLocal.set(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0030, code lost:
        r7 = r1.push(r5, r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0035, code lost:
        if (r7 == null) goto L_0x003b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0037, code lost:
        r1.pop(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003a, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r7 = r4.factories.size();
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0042, code lost:
        if (r2 >= r7) goto L_0x005d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0044, code lost:
        r3 = ((com.squareup.moshi.JsonAdapter.Factory) r4.factories.get(r2)).create(r5, r6, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0050, code lost:
        if (r3 != null) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0052, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0055, code lost:
        r1.adapterFound(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0058, code lost:
        r1.pop(true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005c, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r2 = new java.lang.StringBuilder();
        r2.append("No JsonAdapter for ");
        r2.append(com.squareup.moshi.internal.Util.typeAnnotatedWithAnnotations(r5, r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0077, code lost:
        throw new java.lang.IllegalArgumentException(r2.toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0078, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x007a, code lost:
        r5 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x007f, code lost:
        throw r1.exceptionWithLookupStack(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0080, code lost:
        r1.pop(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0083, code lost:
        throw r5;
     */
    @javax.annotation.CheckReturnValue
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.squareup.moshi.JsonAdapter<T> adapter(java.lang.reflect.Type r5, java.util.Set<? extends java.lang.annotation.Annotation> r6, @javax.annotation.Nullable java.lang.String r7) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x008f
            if (r6 == 0) goto L_0x0087
            java.lang.reflect.Type r5 = com.squareup.moshi.internal.Util.canonicalize(r5)
            java.lang.Object r0 = r4.cacheKey(r5, r6)
            java.util.Map<java.lang.Object, com.squareup.moshi.JsonAdapter<?>> r1 = r4.adapterCache
            monitor-enter(r1)
            java.util.Map<java.lang.Object, com.squareup.moshi.JsonAdapter<?>> r2 = r4.adapterCache     // Catch:{ all -> 0x0084 }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x0084 }
            com.squareup.moshi.JsonAdapter r2 = (com.squareup.moshi.JsonAdapter) r2     // Catch:{ all -> 0x0084 }
            if (r2 == 0) goto L_0x001b
            monitor-exit(r1)     // Catch:{ all -> 0x0084 }
            return r2
        L_0x001b:
            monitor-exit(r1)     // Catch:{ all -> 0x0084 }
            java.lang.ThreadLocal<com.squareup.moshi.Moshi$LookupChain> r1 = r4.lookupChainThreadLocal
            java.lang.Object r1 = r1.get()
            com.squareup.moshi.Moshi$LookupChain r1 = (com.squareup.moshi.Moshi.LookupChain) r1
            if (r1 != 0) goto L_0x0030
            com.squareup.moshi.Moshi$LookupChain r1 = new com.squareup.moshi.Moshi$LookupChain
            r1.<init>()
            java.lang.ThreadLocal<com.squareup.moshi.Moshi$LookupChain> r2 = r4.lookupChainThreadLocal
            r2.set(r1)
        L_0x0030:
            com.squareup.moshi.JsonAdapter r7 = r1.push(r5, r7, r0)
            r0 = 0
            if (r7 == 0) goto L_0x003b
            r1.pop(r0)
            return r7
        L_0x003b:
            java.util.List<com.squareup.moshi.JsonAdapter$Factory> r7 = r4.factories     // Catch:{ IllegalArgumentException -> 0x007a }
            int r7 = r7.size()     // Catch:{ IllegalArgumentException -> 0x007a }
            r2 = 0
        L_0x0042:
            if (r2 >= r7) goto L_0x005d
            java.util.List<com.squareup.moshi.JsonAdapter$Factory> r3 = r4.factories     // Catch:{ IllegalArgumentException -> 0x007a }
            java.lang.Object r3 = r3.get(r2)     // Catch:{ IllegalArgumentException -> 0x007a }
            com.squareup.moshi.JsonAdapter$Factory r3 = (com.squareup.moshi.JsonAdapter.Factory) r3     // Catch:{ IllegalArgumentException -> 0x007a }
            com.squareup.moshi.JsonAdapter r3 = r3.create(r5, r6, r4)     // Catch:{ IllegalArgumentException -> 0x007a }
            if (r3 != 0) goto L_0x0055
            int r2 = r2 + 1
            goto L_0x0042
        L_0x0055:
            r1.adapterFound(r3)     // Catch:{ IllegalArgumentException -> 0x007a }
            r5 = 1
            r1.pop(r5)
            return r3
        L_0x005d:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x007a }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x007a }
            r2.<init>()     // Catch:{ IllegalArgumentException -> 0x007a }
            java.lang.String r3 = "No JsonAdapter for "
            r2.append(r3)     // Catch:{ IllegalArgumentException -> 0x007a }
            java.lang.String r5 = com.squareup.moshi.internal.Util.typeAnnotatedWithAnnotations(r5, r6)     // Catch:{ IllegalArgumentException -> 0x007a }
            r2.append(r5)     // Catch:{ IllegalArgumentException -> 0x007a }
            java.lang.String r5 = r2.toString()     // Catch:{ IllegalArgumentException -> 0x007a }
            r7.<init>(r5)     // Catch:{ IllegalArgumentException -> 0x007a }
            throw r7     // Catch:{ IllegalArgumentException -> 0x007a }
        L_0x0078:
            r5 = move-exception
            goto L_0x0080
        L_0x007a:
            r5 = move-exception
            java.lang.IllegalArgumentException r5 = r1.exceptionWithLookupStack(r5)     // Catch:{ all -> 0x0078 }
            throw r5     // Catch:{ all -> 0x0078 }
        L_0x0080:
            r1.pop(r0)
            throw r5
        L_0x0084:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0084 }
            throw r5
        L_0x0087:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.String r6 = "annotations == null"
            r5.<init>(r6)
            throw r5
        L_0x008f:
            java.lang.NullPointerException r5 = new java.lang.NullPointerException
            java.lang.String r6 = "type == null"
            r5.<init>(r6)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.Moshi.adapter(java.lang.reflect.Type, java.util.Set, java.lang.String):com.squareup.moshi.JsonAdapter");
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> nextAdapter(Factory factory, Type type, Set<? extends Annotation> set) {
        if (set != null) {
            Type canonicalize = Util.canonicalize(type);
            int indexOf = this.factories.indexOf(factory);
            if (indexOf != -1) {
                int size = this.factories.size();
                for (int i = indexOf + 1; i < size; i++) {
                    JsonAdapter<T> create = ((Factory) this.factories.get(i)).create(canonicalize, set, this);
                    if (create != null) {
                        return create;
                    }
                }
                StringBuilder sb = new StringBuilder();
                sb.append("No next JsonAdapter for ");
                sb.append(Util.typeAnnotatedWithAnnotations(canonicalize, set));
                throw new IllegalArgumentException(sb.toString());
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unable to skip past unknown factory ");
            sb2.append(factory);
            throw new IllegalArgumentException(sb2.toString());
        }
        throw new NullPointerException("annotations == null");
    }

    @CheckReturnValue
    public Builder newBuilder() {
        return new Builder().addAll(this.factories.subList(0, this.factories.size() - BUILT_IN_FACTORIES.size()));
    }

    private Object cacheKey(Type type, Set<? extends Annotation> set) {
        if (set.isEmpty()) {
            return type;
        }
        return Arrays.asList(new Object[]{type, set});
    }
}