package com.bitcoin.mwallet;

import com.bitcoin.mwallet.core.workers.WorkManagerGlobalFactory;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.definition.DefinitionFactory;
import org.koin.core.definition.Kind;
import org.koin.core.definition.Options;
import org.koin.core.module.Module;
import org.koin.core.qualifier.Qualifier;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, mo37405d2 = {"<anonymous>", "", "Lorg/koin/core/module/Module;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: KoinConfig.kt */
final class KoinConfig$workManagerModule$1 extends Lambda implements Function1<Module, Unit> {
    public static final KoinConfig$workManagerModule$1 INSTANCE = new KoinConfig$workManagerModule$1();

    KoinConfig$workManagerModule$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Module) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(@NotNull Module module) {
        Intrinsics.checkParameterIsNotNull(module, "$receiver");
        Function2 function2 = C10011.INSTANCE;
        Qualifier qualifier = null;
        DefinitionFactory definitionFactory = DefinitionFactory.INSTANCE;
        Kind kind = Kind.Single;
        BeanDefinition beanDefinition = new BeanDefinition(qualifier, Reflection.getOrCreateKotlinClass(WorkManagerGlobalFactory.class));
        beanDefinition.setDefinition(function2);
        beanDefinition.setKind(kind);
        module.declareDefinition(beanDefinition, new Options(false, false));
    }
}
