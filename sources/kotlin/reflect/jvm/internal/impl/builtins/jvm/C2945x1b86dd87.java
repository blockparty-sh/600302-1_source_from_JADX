package kotlin.reflect.jvm.internal.impl.builtins.jvm;

import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.jvm.internal.impl.descriptors.ClassDescriptor;
import kotlin.reflect.jvm.internal.impl.load.java.components.JavaResolverCache;
import kotlin.reflect.jvm.internal.impl.load.java.lazy.descriptors.LazyJavaClassDescriptor;
import org.jetbrains.annotations.NotNull;

/* renamed from: kotlin.reflect.jvm.internal.impl.builtins.jvm.JvmBuiltInsSettings$getAdditionalFunctions$fakeJavaClassDescriptor$1 */
/* compiled from: JvmBuiltInsSettings.kt */
final class C2945x1b86dd87 extends Lambda implements Function0<LazyJavaClassDescriptor> {
    final /* synthetic */ LazyJavaClassDescriptor $javaAnalogueDescriptor;
    final /* synthetic */ ClassDescriptor $kotlinMutableClassIfContainer;

    C2945x1b86dd87(LazyJavaClassDescriptor lazyJavaClassDescriptor, ClassDescriptor classDescriptor) {
        this.$javaAnalogueDescriptor = lazyJavaClassDescriptor;
        this.$kotlinMutableClassIfContainer = classDescriptor;
        super(0);
    }

    @NotNull
    public final LazyJavaClassDescriptor invoke() {
        LazyJavaClassDescriptor lazyJavaClassDescriptor = this.$javaAnalogueDescriptor;
        JavaResolverCache javaResolverCache = JavaResolverCache.EMPTY;
        Intrinsics.checkExpressionValueIsNotNull(javaResolverCache, "JavaResolverCache.EMPTY");
        return lazyJavaClassDescriptor.copy$descriptors_jvm(javaResolverCache, this.$kotlinMutableClassIfContainer);
    }
}
