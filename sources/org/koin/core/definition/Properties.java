package org.koin.core.definition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.koin.core.error.MissingPropertyException;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B\u001b\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\u0010\u0005J\u0015\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003HÂ\u0003J\u001f\u0010\u0007\u001a\u00020\u00002\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003HÆ\u0001J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u001c\u0010\u000b\u001a\u0002H\f\"\u0004\b\u0000\u0010\f2\u0006\u0010\r\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010\u000eJ\u001b\u0010\u000f\u001a\u0004\u0018\u0001H\f\"\u0004\b\u0000\u0010\f2\u0006\u0010\r\u001a\u00020\u0004¢\u0006\u0002\u0010\u000eJ\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J$\u0010\u0012\u001a\u00020\u0013\"\u0004\b\u0000\u0010\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u0002H\fH\u0002¢\u0006\u0002\u0010\u0015J\t\u0010\u0016\u001a\u00020\u0004HÖ\u0001R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, mo37405d2 = {"Lorg/koin/core/definition/Properties;", "", "data", "", "", "(Ljava/util/Map;)V", "component1", "copy", "equals", "", "other", "get", "T", "key", "(Ljava/lang/String;)Ljava/lang/Object;", "getOrNull", "hashCode", "", "set", "", "value", "(Ljava/lang/String;Ljava/lang/Object;)V", "toString", "koin-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Properties.kt */
public final class Properties {
    private final Map<String, Object> data;

    public Properties() {
        this(null, 1, null);
    }

    private final Map<String, Object> component1() {
        return this.data;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Map, code=java.util.Map<java.lang.String, java.lang.Object>, for r1v0, types: [java.util.Map] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ org.koin.core.definition.Properties copy$default(org.koin.core.definition.Properties r0, java.util.Map<java.lang.String, java.lang.Object> r1, int r2, java.lang.Object r3) {
        /*
            r2 = r2 & 1
            if (r2 == 0) goto L_0x0006
            java.util.Map<java.lang.String, java.lang.Object> r1 = r0.data
        L_0x0006:
            org.koin.core.definition.Properties r0 = r0.copy(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.koin.core.definition.Properties.copy$default(org.koin.core.definition.Properties, java.util.Map, int, java.lang.Object):org.koin.core.definition.Properties");
    }

    @NotNull
    public final Properties copy(@NotNull Map<String, Object> map) {
        Intrinsics.checkParameterIsNotNull(map, "data");
        return new Properties(map);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.data, (java.lang.Object) ((org.koin.core.definition.Properties) r2).data) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof org.koin.core.definition.Properties
            if (r0 == 0) goto L_0x0013
            org.koin.core.definition.Properties r2 = (org.koin.core.definition.Properties) r2
            java.util.Map<java.lang.String, java.lang.Object> r0 = r1.data
            java.util.Map<java.lang.String, java.lang.Object> r2 = r2.data
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r2 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r2 = 0
            return r2
        L_0x0015:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.koin.core.definition.Properties.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        Map<String, Object> map = this.data;
        if (map != null) {
            return map.hashCode();
        }
        return 0;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Properties(data=");
        sb.append(this.data);
        sb.append(")");
        return sb.toString();
    }

    public Properties(@NotNull Map<String, Object> map) {
        Intrinsics.checkParameterIsNotNull(map, "data");
        this.data = map;
    }

    public /* synthetic */ Properties(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            map = new ConcurrentHashMap();
        }
        this(map);
    }

    public final <T> void set(@NotNull String str, T t) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        Map<String, Object> map = this.data;
        if (t != null) {
            map.put(str, t);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Any");
    }

    @Nullable
    public final <T> T getOrNull(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        T t = this.data.get(str);
        if (!(t instanceof Object)) {
            return null;
        }
        return t;
    }

    public final <T> T get(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "key");
        T t = this.data.get(str);
        if (!(t instanceof Object)) {
            t = null;
        }
        if (t != null) {
            return t;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("missing property for '");
        sb.append(str);
        sb.append('\'');
        throw new MissingPropertyException(sb.toString());
    }
}
