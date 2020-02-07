package org.koin.core.qualifier;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.koin.ext.KClassExtKt;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\u0002\u0010\u0004J\r\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0003J\u0017\u0010\b\u001a\u00020\u00002\f\b\u0002\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\b\u0010\u000f\u001a\u00020\u0010H\u0016R\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, mo37405d2 = {"Lorg/koin/core/qualifier/TypeQualifier;", "Lorg/koin/core/qualifier/Qualifier;", "type", "Lkotlin/reflect/KClass;", "(Lkotlin/reflect/KClass;)V", "getType", "()Lkotlin/reflect/KClass;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "koin-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: TypeQualifier.kt */
public final class TypeQualifier implements Qualifier {
    @NotNull
    private final KClass<?> type;

    /* JADX WARNING: Incorrect type for immutable var: ssa=kotlin.reflect.KClass, code=kotlin.reflect.KClass<?>, for r1v0, types: [kotlin.reflect.KClass] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ org.koin.core.qualifier.TypeQualifier copy$default(org.koin.core.qualifier.TypeQualifier r0, kotlin.reflect.KClass<?> r1, int r2, java.lang.Object r3) {
        /*
            r2 = r2 & 1
            if (r2 == 0) goto L_0x0006
            kotlin.reflect.KClass<?> r1 = r0.type
        L_0x0006:
            org.koin.core.qualifier.TypeQualifier r0 = r0.copy(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.koin.core.qualifier.TypeQualifier.copy$default(org.koin.core.qualifier.TypeQualifier, kotlin.reflect.KClass, int, java.lang.Object):org.koin.core.qualifier.TypeQualifier");
    }

    @NotNull
    public final KClass<?> component1() {
        return this.type;
    }

    @NotNull
    public final TypeQualifier copy(@NotNull KClass<?> kClass) {
        Intrinsics.checkParameterIsNotNull(kClass, "type");
        return new TypeQualifier(kClass);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.type, (java.lang.Object) ((org.koin.core.qualifier.TypeQualifier) r2).type) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof org.koin.core.qualifier.TypeQualifier
            if (r0 == 0) goto L_0x0013
            org.koin.core.qualifier.TypeQualifier r2 = (org.koin.core.qualifier.TypeQualifier) r2
            kotlin.reflect.KClass<?> r0 = r1.type
            kotlin.reflect.KClass<?> r2 = r2.type
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
        throw new UnsupportedOperationException("Method not decompiled: org.koin.core.qualifier.TypeQualifier.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        KClass<?> kClass = this.type;
        if (kClass != null) {
            return kClass.hashCode();
        }
        return 0;
    }

    public TypeQualifier(@NotNull KClass<?> kClass) {
        Intrinsics.checkParameterIsNotNull(kClass, "type");
        this.type = kClass;
    }

    @NotNull
    public final KClass<?> getType() {
        return this.type;
    }

    @NotNull
    public String toString() {
        return KClassExtKt.getFullName(this.type);
    }
}
