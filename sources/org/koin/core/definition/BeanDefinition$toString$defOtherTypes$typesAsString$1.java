package org.koin.core.definition;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.koin.ext.KClassExtKt;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "T", "it", "Lkotlin/reflect/KClass;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: BeanDefinition.kt */
final class BeanDefinition$toString$defOtherTypes$typesAsString$1 extends Lambda implements Function1<KClass<?>, String> {
    public static final BeanDefinition$toString$defOtherTypes$typesAsString$1 INSTANCE = new BeanDefinition$toString$defOtherTypes$typesAsString$1();

    BeanDefinition$toString$defOtherTypes$typesAsString$1() {
        super(1);
    }

    @NotNull
    public final String invoke(@NotNull KClass<?> kClass) {
        Intrinsics.checkParameterIsNotNull(kClass, "it");
        return KClassExtKt.getFullName(kClass);
    }
}
