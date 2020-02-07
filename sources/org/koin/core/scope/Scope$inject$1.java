package org.koin.core.scope;

import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import org.koin.core.qualifier.Qualifier;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0004\n\u0002\b\u0004\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, mo37405d2 = {"<anonymous>", "T", "invoke", "()Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Scope.kt */
public final class Scope$inject$1 extends Lambda implements Function0<T> {
    final /* synthetic */ Function0 $parameters;
    final /* synthetic */ Qualifier $qualifier;
    final /* synthetic */ Scope this$0;

    public Scope$inject$1(Scope scope, Qualifier qualifier, Function0 function0) {
        this.this$0 = scope;
        this.$qualifier = qualifier;
        this.$parameters = function0;
        super(0);
    }

    public final T invoke() {
        Scope scope = this.this$0;
        Qualifier qualifier = this.$qualifier;
        Function0 function0 = this.$parameters;
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return scope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
    }
}
