package org.koin.dsl;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.koin.core.definition.BeanDefinition;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a+\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\u0004\u001a,\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0001*\u0006\u0012\u0002\b\u00030\u00012\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0007H\u0004¢\u0006\u0002\u0010\b\u001a?\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u001e\u0010\t\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u0002H\u0002`\fH\u0004\u001a?\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u001e\u0010\r\u001a\u001a\u0012\u0006\u0012\u0004\u0018\u0001H\u0002\u0012\u0004\u0012\u00020\u000b0\nj\b\u0012\u0004\u0012\u0002H\u0002`\u000eH\u0004¨\u0006\u000f"}, mo37405d2 = {"bind", "Lorg/koin/core/definition/BeanDefinition;", "T", "clazz", "Lkotlin/reflect/KClass;", "binds", "classes", "", "(Lorg/koin/core/definition/BeanDefinition;[Lkotlin/reflect/KClass;)Lorg/koin/core/definition/BeanDefinition;", "onClose", "Lkotlin/Function1;", "", "Lorg/koin/core/definition/OnCloseCallback;", "onRelease", "Lorg/koin/core/definition/OnReleaseCallback;", "koin-core"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: DefinitionBinding.kt */
public final class DefinitionBindingKt {
    @NotNull
    public static final <T> BeanDefinition<T> bind(@NotNull BeanDefinition<T> beanDefinition, @NotNull KClass<?> kClass) {
        Intrinsics.checkParameterIsNotNull(beanDefinition, "$this$bind");
        Intrinsics.checkParameterIsNotNull(kClass, "clazz");
        beanDefinition.getSecondaryTypes().add(kClass);
        return beanDefinition;
    }

    @NotNull
    public static final BeanDefinition<?> binds(@NotNull BeanDefinition<?> beanDefinition, @NotNull KClass<?>[] kClassArr) {
        Intrinsics.checkParameterIsNotNull(beanDefinition, "$this$binds");
        Intrinsics.checkParameterIsNotNull(kClassArr, "classes");
        CollectionsKt.addAll((Collection<? super T>) beanDefinition.getSecondaryTypes(), (T[]) kClassArr);
        return beanDefinition;
    }

    @NotNull
    public static final <T> BeanDefinition<T> onRelease(@NotNull BeanDefinition<T> beanDefinition, @NotNull Function1<? super T, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(beanDefinition, "$this$onRelease");
        Intrinsics.checkParameterIsNotNull(function1, "onRelease");
        beanDefinition.setOnRelease(function1);
        return beanDefinition;
    }

    @NotNull
    public static final <T> BeanDefinition<T> onClose(@NotNull BeanDefinition<T> beanDefinition, @NotNull Function1<? super T, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(beanDefinition, "$this$onClose");
        Intrinsics.checkParameterIsNotNull(function1, "onClose");
        beanDefinition.setOnClose(function1);
        return beanDefinition;
    }
}
