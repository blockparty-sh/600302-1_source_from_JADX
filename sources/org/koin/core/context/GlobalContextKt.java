package org.koin.core.context;

import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.koin.core.KoinApplication;
import org.koin.core.module.Module;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0002\u0010\u0005\u001a#\u0010\u0006\u001a\u00020\u00072\u001b\u0010\b\u001a\u0017\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\tj\u0002`\n¢\u0006\u0002\b\u000b\u001a\u0006\u0010\f\u001a\u00020\u0001\u001a\u001f\u0010\r\u001a\u00020\u00012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0002\u0010\u0005\u001a\u0014\u0010\r\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e¨\u0006\u000f"}, mo37405d2 = {"loadKoinModules", "", "modules", "", "Lorg/koin/core/module/Module;", "([Lorg/koin/core/module/Module;)V", "startKoin", "Lorg/koin/core/KoinApplication;", "appDeclaration", "Lkotlin/Function1;", "Lorg/koin/dsl/KoinAppDeclaration;", "Lkotlin/ExtensionFunctionType;", "stopKoin", "unloadKoinModules", "", "koin-core"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: GlobalContext.kt */
public final class GlobalContextKt {
    @NotNull
    public static final KoinApplication startKoin(@NotNull Function1<? super KoinApplication, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "appDeclaration");
        KoinApplication create = KoinApplication.Companion.create();
        GlobalContext.start(create);
        function1.invoke(create);
        create.createEagerInstances();
        return create;
    }

    public static final void stopKoin() {
        GlobalContext.stop();
    }

    public static final void loadKoinModules(@NotNull Module... moduleArr) {
        Intrinsics.checkParameterIsNotNull(moduleArr, "modules");
        GlobalContext.get().modules((Module[]) Arrays.copyOf(moduleArr, moduleArr.length));
    }

    public static final void unloadKoinModules(@NotNull Module... moduleArr) {
        Intrinsics.checkParameterIsNotNull(moduleArr, "modules");
        GlobalContext.get().unloadModules((Module[]) Arrays.copyOf(moduleArr, moduleArr.length));
    }

    public static final void unloadKoinModules(@NotNull List<Module> list) {
        Intrinsics.checkParameterIsNotNull(list, "modules");
        GlobalContext.get().unloadModules(list);
    }
}
