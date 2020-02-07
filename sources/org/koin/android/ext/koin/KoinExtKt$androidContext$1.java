package org.koin.android.ext.koin;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.scope.Scope;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "Landroid/content/Context;", "Lorg/koin/core/scope/Scope;", "it", "Lorg/koin/core/parameter/DefinitionParameters;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: KoinExt.kt */
final class KoinExtKt$androidContext$1 extends Lambda implements Function2<Scope, DefinitionParameters, Context> {
    final /* synthetic */ Context $androidContext;

    KoinExtKt$androidContext$1(Context context) {
        this.$androidContext = context;
        super(2);
    }

    @NotNull
    public final Context invoke(@NotNull Scope scope, @NotNull DefinitionParameters definitionParameters) {
        Intrinsics.checkParameterIsNotNull(scope, "$receiver");
        Intrinsics.checkParameterIsNotNull(definitionParameters, "it");
        return this.$androidContext;
    }
}
