package org.koin.dsl;

import androidx.exifinterface.media.ExifInterface;
import java.util.HashSet;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.DefinitionFactory;
import org.koin.core.definition.Kind;
import org.koin.core.error.DefinitionOverrideException;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012 \b\u0002\u0010\u0004\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006`\u0007¢\u0006\u0002\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J!\u0010\u000e\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006`\u0007HÆ\u0003J5\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032 \b\u0002\u0010\u0004\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006`\u0007HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003JX\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0006\"\u0006\b\u0000\u0010\u0014\u0018\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0015\u001a\u00020\u00112)\b\b\u0010\u0016\u001a#\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u0002H\u00140\u0017j\b\u0012\u0004\u0012\u0002H\u0014`\u001a¢\u0006\u0002\b\u001bH\bJ\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001JN\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0006\"\u0006\b\u0000\u0010\u0014\u0018\u00012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00032)\b\b\u0010\u0016\u001a#\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u0002H\u00140\u0017j\b\u0012\u0004\u0012\u0002H\u0014`\u001a¢\u0006\u0002\b\u001bH\bJb\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u00140\u0006\"\u0006\b\u0000\u0010\u0014\u0018\u00012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010!\u001a\u00020\u00112\b\b\u0002\u0010\u0015\u001a\u00020\u00112)\b\b\u0010\u0016\u001a#\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u0002H\u00140\u0017j\b\u0012\u0004\u0012\u0002H\u0014`\u001a¢\u0006\u0002\b\u001bH\bJ\b\u0010\"\u001a\u00020#H\u0016R)\u0010\u0004\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005j\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0006`\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006$"}, mo37405d2 = {"Lorg/koin/dsl/ScopeSet;", "", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "definitions", "Ljava/util/HashSet;", "Lorg/koin/core/definition/BeanDefinition;", "Lkotlin/collections/HashSet;", "(Lorg/koin/core/qualifier/Qualifier;Ljava/util/HashSet;)V", "getDefinitions", "()Ljava/util/HashSet;", "getQualifier", "()Lorg/koin/core/qualifier/Qualifier;", "component1", "component2", "copy", "equals", "", "other", "factory", "T", "override", "definition", "Lkotlin/Function2;", "Lorg/koin/core/scope/Scope;", "Lorg/koin/core/parameter/DefinitionParameters;", "Lorg/koin/core/definition/Definition;", "Lkotlin/ExtensionFunctionType;", "hashCode", "", "scoped", "name", "single", "createdAtStart", "toString", "", "koin-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ScopeSet.kt */
public final class ScopeSet {
    @NotNull
    private final HashSet<BeanDefinition<?>> definitions;
    @NotNull
    private final Qualifier qualifier;

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.HashSet, code=java.util.HashSet<org.koin.core.definition.BeanDefinition<?>>, for r2v0, types: [java.util.HashSet] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ org.koin.dsl.ScopeSet copy$default(org.koin.dsl.ScopeSet r0, org.koin.core.qualifier.Qualifier r1, java.util.HashSet<org.koin.core.definition.BeanDefinition<?>> r2, int r3, java.lang.Object r4) {
        /*
            r4 = r3 & 1
            if (r4 == 0) goto L_0x0006
            org.koin.core.qualifier.Qualifier r1 = r0.qualifier
        L_0x0006:
            r3 = r3 & 2
            if (r3 == 0) goto L_0x000c
            java.util.HashSet<org.koin.core.definition.BeanDefinition<?>> r2 = r0.definitions
        L_0x000c:
            org.koin.dsl.ScopeSet r0 = r0.copy(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.koin.dsl.ScopeSet.copy$default(org.koin.dsl.ScopeSet, org.koin.core.qualifier.Qualifier, java.util.HashSet, int, java.lang.Object):org.koin.dsl.ScopeSet");
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
    public final ScopeSet copy(@NotNull Qualifier qualifier2, @NotNull HashSet<BeanDefinition<?>> hashSet) {
        Intrinsics.checkParameterIsNotNull(qualifier2, "qualifier");
        Intrinsics.checkParameterIsNotNull(hashSet, "definitions");
        return new ScopeSet(qualifier2, hashSet);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.definitions, (java.lang.Object) r3.definitions) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof org.koin.dsl.ScopeSet
            if (r0 == 0) goto L_0x001d
            org.koin.dsl.ScopeSet r3 = (org.koin.dsl.ScopeSet) r3
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
        throw new UnsupportedOperationException("Method not decompiled: org.koin.dsl.ScopeSet.equals(java.lang.Object):boolean");
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

    public ScopeSet(@NotNull Qualifier qualifier2, @NotNull HashSet<BeanDefinition<?>> hashSet) {
        Intrinsics.checkParameterIsNotNull(qualifier2, "qualifier");
        Intrinsics.checkParameterIsNotNull(hashSet, "definitions");
        this.qualifier = qualifier2;
        this.definitions = hashSet;
    }

    public /* synthetic */ ScopeSet(Qualifier qualifier2, HashSet hashSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
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

    static /* synthetic */ BeanDefinition scoped$default(ScopeSet scopeSet, Qualifier qualifier2, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier2 = null;
        }
        DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
        Qualifier qualifier3 = scopeSet.getQualifier();
        Kind kind = Kind.Scope;
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        BeanDefinition beanDefinition = new BeanDefinition(qualifier2, Reflection.getOrCreateKotlinClass(Object.class));
        beanDefinition.setDefinition(function2);
        beanDefinition.setKind(kind);
        beanDefinition.setScopeName(qualifier3);
        if (scopeSet.getDefinitions().add(beanDefinition)) {
            return beanDefinition;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't add definition ");
        sb.append(beanDefinition);
        sb.append(" as it already exists");
        throw new DefinitionOverrideException(sb.toString());
    }

    private final <T> BeanDefinition<T> scoped(Qualifier qualifier2, Function2<? super Scope, ? super DefinitionParameters, ? extends T> function2) {
        DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
        Qualifier qualifier3 = getQualifier();
        Kind kind = Kind.Scope;
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        BeanDefinition<T> beanDefinition = new BeanDefinition<>(qualifier2, Reflection.getOrCreateKotlinClass(Object.class));
        beanDefinition.setDefinition(function2);
        beanDefinition.setKind(kind);
        beanDefinition.setScopeName(qualifier3);
        if (getDefinitions().add(beanDefinition)) {
            return beanDefinition;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Can't add definition ");
        sb.append(beanDefinition);
        sb.append(" as it already exists");
        throw new DefinitionOverrideException(sb.toString());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Single definition can't be used in a scope")
    static /* synthetic */ BeanDefinition single$default(ScopeSet scopeSet, Qualifier qualifier2, boolean z, boolean z2, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            Qualifier qualifier3 = null;
        }
        int i2 = i & 2;
        int i3 = i & 4;
        throw new IllegalStateException("Single definition can't be used in a scope".toString());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Single definition can't be used in a scope")
    private final <T> BeanDefinition<T> single(Qualifier qualifier2, boolean z, boolean z2, Function2<? super Scope, ? super DefinitionParameters, ? extends T> function2) {
        throw new IllegalStateException("Single definition can't be used in a scope".toString());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Factory definition can't be used in a scope")
    static /* synthetic */ BeanDefinition factory$default(ScopeSet scopeSet, Qualifier qualifier2, boolean z, Function2 function2, int i, Object obj) {
        if ((i & 1) != 0) {
            Qualifier qualifier3 = null;
        }
        int i2 = i & 2;
        throw new IllegalStateException("Factory definition can't be used in a scope".toString());
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Factory definition can't be used in a scope")
    private final <T> BeanDefinition<T> factory(Qualifier qualifier2, boolean z, Function2<? super Scope, ? super DefinitionParameters, ? extends T> function2) {
        throw new IllegalStateException("Factory definition can't be used in a scope".toString());
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Scope['");
        sb.append(this.qualifier);
        sb.append("']");
        return sb.toString();
    }
}
