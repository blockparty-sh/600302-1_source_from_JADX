package org.koin.core.scope;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.instance.DefinitionInstance;
import org.koin.core.instance.InstanceContext;
import org.koin.core.instance.ScopeDefinitionInstance;
import org.koin.core.qualifier.Qualifier;
import org.koin.dsl.ScopeSet;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012 \b\u0002\u0010\u0004\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006`\u0007¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J!\u0010\u000e\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006`\u0007HÆ\u0003J5\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032 \b\u0002\u0010\u0004\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006`\u0007HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\u0015\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u0019J\t\u0010\u001a\u001a\u00020\u001bHÖ\u0001R)\u0010\u0004\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u001d"}, mo37405d2 = {"Lorg/koin/core/scope/ScopeDefinition;", "", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "definitions", "Ljava/util/HashSet;", "Lorg/koin/core/definition/BeanDefinition;", "Lkotlin/collections/HashSet;", "(Lorg/koin/core/qualifier/Qualifier;Ljava/util/HashSet;)V", "getDefinitions", "()Ljava/util/HashSet;", "getQualifier", "()Lorg/koin/core/qualifier/Qualifier;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "release", "", "instance", "Lorg/koin/core/scope/Scope;", "release$koin_core", "toString", "", "Companion", "koin-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ScopeDefinition.kt */
public final class ScopeDefinition {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final HashSet<BeanDefinition<?>> definitions;
    @NotNull
    private final Qualifier qualifier;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, mo37405d2 = {"Lorg/koin/core/scope/ScopeDefinition$Companion;", "", "()V", "from", "Lorg/koin/core/scope/ScopeDefinition;", "set", "Lorg/koin/dsl/ScopeSet;", "koin-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ScopeDefinition.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ScopeDefinition from(@NotNull ScopeSet scopeSet) {
            Intrinsics.checkParameterIsNotNull(scopeSet, "set");
            ScopeDefinition scopeDefinition = new ScopeDefinition(scopeSet.getQualifier(), null, 2, null);
            scopeDefinition.getDefinitions().addAll(scopeSet.getDefinitions());
            return scopeDefinition;
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.HashSet, code=java.util.HashSet<org.koin.core.definition.BeanDefinition<?>>, for r2v0, types: [java.util.HashSet] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ org.koin.core.scope.ScopeDefinition copy$default(org.koin.core.scope.ScopeDefinition r0, org.koin.core.qualifier.Qualifier r1, java.util.HashSet<org.koin.core.definition.BeanDefinition<?>> r2, int r3, java.lang.Object r4) {
        /*
            r4 = r3 & 1
            if (r4 == 0) goto L_0x0006
            org.koin.core.qualifier.Qualifier r1 = r0.qualifier
        L_0x0006:
            r3 = r3 & 2
            if (r3 == 0) goto L_0x000c
            java.util.HashSet<org.koin.core.definition.BeanDefinition<?>> r2 = r0.definitions
        L_0x000c:
            org.koin.core.scope.ScopeDefinition r0 = r0.copy(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.koin.core.scope.ScopeDefinition.copy$default(org.koin.core.scope.ScopeDefinition, org.koin.core.qualifier.Qualifier, java.util.HashSet, int, java.lang.Object):org.koin.core.scope.ScopeDefinition");
    }

    @NotNull
    public final Qualifier component1() {
        return this.qualifier;
    }

    @NotNull
    public final HashSet<BeanDefinition<?>> component2() {
        return this.definitions;
    }

    @NotNull
    public final ScopeDefinition copy(@NotNull Qualifier qualifier2, @NotNull HashSet<BeanDefinition<?>> hashSet) {
        Intrinsics.checkParameterIsNotNull(qualifier2, "qualifier");
        Intrinsics.checkParameterIsNotNull(hashSet, "definitions");
        return new ScopeDefinition(qualifier2, hashSet);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.definitions, (java.lang.Object) r3.definitions) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof org.koin.core.scope.ScopeDefinition
            if (r0 == 0) goto L_0x001d
            org.koin.core.scope.ScopeDefinition r3 = (org.koin.core.scope.ScopeDefinition) r3
            org.koin.core.qualifier.Qualifier r0 = r2.qualifier
            org.koin.core.qualifier.Qualifier r1 = r3.qualifier
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            java.util.HashSet<org.koin.core.definition.BeanDefinition<?>> r0 = r2.definitions
            java.util.HashSet<org.koin.core.definition.BeanDefinition<?>> r3 = r3.definitions
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r3 = 0
            return r3
        L_0x001f:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.koin.core.scope.ScopeDefinition.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        Qualifier qualifier2 = this.qualifier;
        int i = 0;
        int hashCode = (qualifier2 != null ? qualifier2.hashCode() : 0) * 31;
        HashSet<BeanDefinition<?>> hashSet = this.definitions;
        if (hashSet != null) {
            i = hashSet.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ScopeDefinition(qualifier=");
        sb.append(this.qualifier);
        sb.append(", definitions=");
        sb.append(this.definitions);
        sb.append(")");
        return sb.toString();
    }

    public ScopeDefinition(@NotNull Qualifier qualifier2, @NotNull HashSet<BeanDefinition<?>> hashSet) {
        Intrinsics.checkParameterIsNotNull(qualifier2, "qualifier");
        Intrinsics.checkParameterIsNotNull(hashSet, "definitions");
        this.qualifier = qualifier2;
        this.definitions = hashSet;
    }

    public /* synthetic */ ScopeDefinition(Qualifier qualifier2, HashSet hashSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 2) != 0) {
            hashSet = new HashSet();
        }
        this(qualifier2, hashSet);
    }

    @NotNull
    public final HashSet<BeanDefinition<?>> getDefinitions() {
        return this.definitions;
    }

    @NotNull
    public final Qualifier getQualifier() {
        return this.qualifier;
    }

    public final void release$koin_core(@NotNull Scope scope) {
        Intrinsics.checkParameterIsNotNull(scope, "instance");
        Iterable iterable = this.definitions;
        Collection arrayList = new ArrayList();
        for (Object next : iterable) {
            if (((BeanDefinition) next).getInstance() instanceof ScopeDefinitionInstance) {
                arrayList.add(next);
            }
        }
        for (BeanDefinition instance : (List) arrayList) {
            DefinitionInstance instance2 = instance.getInstance();
            if (instance2 != null) {
                InstanceContext instanceContext = new InstanceContext(null, scope, null, 5, null);
                instance2.release(instanceContext);
            }
        }
    }
}
