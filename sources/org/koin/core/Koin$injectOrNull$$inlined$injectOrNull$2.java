package org.koin.core;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.Nullable;
import org.koin.core.logger.Logger;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;
import org.koin.ext.KClassExtKt;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0004\n\u0002\b\u0005\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, mo37405d2 = {"<anonymous>", "T", "invoke", "()Ljava/lang/Object;", "org/koin/core/scope/Scope$injectOrNull$1"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Scope.kt */
public final class Koin$injectOrNull$$inlined$injectOrNull$2 extends Lambda implements Function0<T> {
    final /* synthetic */ Function0 $parameters;
    final /* synthetic */ Qualifier $qualifier;
    final /* synthetic */ Scope this$0;

    public Koin$injectOrNull$$inlined$injectOrNull$2(Scope scope, Qualifier qualifier, Function0 function0) {
        this.this$0 = scope;
        this.$qualifier = qualifier;
        this.$parameters = function0;
        super(0);
    }

    @Nullable
    public final T invoke() {
        String str = "T?";
        Scope scope = this.this$0;
        Qualifier qualifier = this.$qualifier;
        Function0 function0 = this.$parameters;
        try {
            Intrinsics.reifiedOperationMarker(4, str);
            return scope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
        } catch (Exception unused) {
            Logger logger = KoinApplication.Companion.getLogger();
            StringBuilder sb = new StringBuilder();
            sb.append("Can't get instance for ");
            Intrinsics.reifiedOperationMarker(4, str);
            sb.append(KClassExtKt.getFullName(Reflection.getOrCreateKotlinClass(Object.class)));
            logger.error(sb.toString());
            return null;
        }
    }
}
