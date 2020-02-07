package org.koin.core.instance;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.koin.core.Koin;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.scope.Scope;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0016\b\u0002\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\u0004\u0018\u0001`\t¢\u0006\u0002\u0010\nR\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\u0004\u0018\u0001`\tX\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, mo37405d2 = {"Lorg/koin/core/instance/InstanceContext;", "", "koin", "Lorg/koin/core/Koin;", "scope", "Lorg/koin/core/scope/Scope;", "_parameters", "Lkotlin/Function0;", "Lorg/koin/core/parameter/DefinitionParameters;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Lorg/koin/core/Koin;Lorg/koin/core/scope/Scope;Lkotlin/jvm/functions/Function0;)V", "getKoin", "()Lorg/koin/core/Koin;", "parameters", "getParameters", "()Lorg/koin/core/parameter/DefinitionParameters;", "getScope", "()Lorg/koin/core/scope/Scope;", "koin-core"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: DefinitionInstance.kt */
public final class InstanceContext {
    private final Function0<DefinitionParameters> _parameters;
    @Nullable
    private final Koin koin;
    @NotNull
    private final DefinitionParameters parameters;
    @Nullable
    private final Scope scope;

    public InstanceContext() {
        this(null, null, null, 7, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0013, code lost:
        if (r1 != null) goto L_0x001a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public InstanceContext(@org.jetbrains.annotations.Nullable org.koin.core.Koin r1, @org.jetbrains.annotations.Nullable org.koin.core.scope.Scope r2, @org.jetbrains.annotations.Nullable kotlin.jvm.functions.Function0<org.koin.core.parameter.DefinitionParameters> r3) {
        /*
            r0 = this;
            r0.<init>()
            r0.koin = r1
            r0.scope = r2
            r0._parameters = r3
            kotlin.jvm.functions.Function0<org.koin.core.parameter.DefinitionParameters> r1 = r0._parameters
            if (r1 == 0) goto L_0x0016
            java.lang.Object r1 = r1.invoke()
            org.koin.core.parameter.DefinitionParameters r1 = (org.koin.core.parameter.DefinitionParameters) r1
            if (r1 == 0) goto L_0x0016
            goto L_0x001a
        L_0x0016:
            org.koin.core.parameter.DefinitionParameters r1 = org.koin.core.parameter.DefinitionParametersKt.emptyParametersHolder()
        L_0x001a:
            r0.parameters = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.koin.core.instance.InstanceContext.<init>(org.koin.core.Koin, org.koin.core.scope.Scope, kotlin.jvm.functions.Function0):void");
    }

    public /* synthetic */ InstanceContext(Koin koin2, Scope scope2, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            koin2 = null;
        }
        if ((i & 2) != 0) {
            scope2 = koin2 != null ? koin2.getRootScope() : null;
        }
        if ((i & 4) != 0) {
            function0 = null;
        }
        this(koin2, scope2, function0);
    }

    @Nullable
    public final Koin getKoin() {
        return this.koin;
    }

    @Nullable
    public final Scope getScope() {
        return this.scope;
    }

    @NotNull
    public final DefinitionParameters getParameters() {
        return this.parameters;
    }
}
