package org.koin.core.module;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¨\u0006\u0004"}, mo37405d2 = {"plus", "", "Lorg/koin/core/module/Module;", "module", "koin-core"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: Module.kt */
public final class ModuleKt {
    @NotNull
    public static final List<Module> plus(@NotNull List<Module> list, @NotNull Module module) {
        Intrinsics.checkParameterIsNotNull(list, "$this$plus");
        Intrinsics.checkParameterIsNotNull(module, "module");
        return CollectionsKt.plus((Collection<? extends T>) list, (Iterable<? extends T>) CollectionsKt.listOf(module));
    }
}
