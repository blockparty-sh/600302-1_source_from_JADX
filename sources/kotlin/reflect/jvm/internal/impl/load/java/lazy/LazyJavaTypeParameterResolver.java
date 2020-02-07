package kotlin.reflect.jvm.internal.impl.load.java.lazy;

import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.jvm.internal.impl.descriptors.DeclarationDescriptor;
import kotlin.reflect.jvm.internal.impl.descriptors.TypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaTypeParameterDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameter;
import kotlin.reflect.jvm.internal.impl.load.java.structure.JavaTypeParameterListOwner;
import kotlin.reflect.jvm.internal.impl.storage.MemoizedFunctionToNullable;
import kotlin.reflect.jvm.internal.impl.utils.CollectionsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: resolvers.kt */
public final class LazyJavaTypeParameterResolver implements TypeParameterResolver {
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final LazyJavaResolverContext f699c;
    /* access modifiers changed from: private */
    public final DeclarationDescriptor containingDeclaration;
    private final MemoizedFunctionToNullable<JavaTypeParameter, LazyJavaTypeParameterDescriptor> resolve = this.f699c.getStorageManager().createMemoizedFunctionWithNullableValues(new LazyJavaTypeParameterResolver$resolve$1(this));
    /* access modifiers changed from: private */
    public final Map<JavaTypeParameter, Integer> typeParameters;
    /* access modifiers changed from: private */
    public final int typeParametersIndexOffset;

    public LazyJavaTypeParameterResolver(@NotNull LazyJavaResolverContext lazyJavaResolverContext, @NotNull DeclarationDescriptor declarationDescriptor, @NotNull JavaTypeParameterListOwner javaTypeParameterListOwner, int i) {
        Intrinsics.checkParameterIsNotNull(lazyJavaResolverContext, "c");
        Intrinsics.checkParameterIsNotNull(declarationDescriptor, "containingDeclaration");
        Intrinsics.checkParameterIsNotNull(javaTypeParameterListOwner, "typeParameterOwner");
        this.f699c = lazyJavaResolverContext;
        this.containingDeclaration = declarationDescriptor;
        this.typeParametersIndexOffset = i;
        this.typeParameters = CollectionsKt.mapToIndex(javaTypeParameterListOwner.getTypeParameters());
    }

    @Nullable
    public TypeParameterDescriptor resolveTypeParameter(@NotNull JavaTypeParameter javaTypeParameter) {
        Intrinsics.checkParameterIsNotNull(javaTypeParameter, "javaTypeParameter");
        LazyJavaTypeParameterDescriptor lazyJavaTypeParameterDescriptor = (LazyJavaTypeParameterDescriptor) this.resolve.invoke(javaTypeParameter);
        return lazyJavaTypeParameterDescriptor != null ? lazyJavaTypeParameterDescriptor : this.f699c.getTypeParameterResolver().resolveTypeParameter(javaTypeParameter);
    }
}
