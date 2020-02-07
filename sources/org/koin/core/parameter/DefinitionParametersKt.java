package org.koin.core.parameter;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a#\u0010\u0002\u001a\u00020\u00012\u0016\u0010\u0003\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u0004\"\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006*\u0016\u0010\u0007\"\b\u0012\u0004\u0012\u00020\u00010\b2\b\u0012\u0004\u0012\u00020\u00010\b¨\u0006\t"}, mo37405d2 = {"emptyParametersHolder", "Lorg/koin/core/parameter/DefinitionParameters;", "parametersOf", "parameters", "", "", "([Ljava/lang/Object;)Lorg/koin/core/parameter/DefinitionParameters;", "ParametersDefinition", "Lkotlin/Function0;", "koin-core"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: DefinitionParameters.kt */
public final class DefinitionParametersKt {
    @NotNull
    public static final DefinitionParameters parametersOf(@NotNull Object... objArr) {
        Intrinsics.checkParameterIsNotNull(objArr, "parameters");
        if (objArr.length <= 5) {
            return new DefinitionParameters(Arrays.copyOf(objArr, objArr.length));
        }
        throw new IllegalStateException("Can't build DefinitionParameters for more than 5 arguments".toString());
    }

    @NotNull
    public static final DefinitionParameters emptyParametersHolder() {
        return new DefinitionParameters(new Object[0]);
    }
}
