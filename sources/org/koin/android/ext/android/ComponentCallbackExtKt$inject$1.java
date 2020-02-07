package org.koin.android.ext.android;

import android.content.ComponentCallbacks;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, mo37405d2 = {"<anonymous>", "T", "", "invoke", "()Ljava/lang/Object;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ComponentCallbackExt.kt */
public final class ComponentCallbackExtKt$inject$1 extends Lambda implements Function0<T> {
    final /* synthetic */ Function0 $parameters;
    final /* synthetic */ Qualifier $qualifier;
    final /* synthetic */ ComponentCallbacks $this_inject;

    public ComponentCallbackExtKt$inject$1(ComponentCallbacks componentCallbacks, Qualifier qualifier, Function0 function0) {
        this.$this_inject = componentCallbacks;
        this.$qualifier = qualifier;
        this.$parameters = function0;
        super(0);
    }

    @NotNull
    public final T invoke() {
        ComponentCallbacks componentCallbacks = this.$this_inject;
        Qualifier qualifier = this.$qualifier;
        Function0 function0 = this.$parameters;
        Scope rootScope = ComponentCallbackExtKt.getKoin(componentCallbacks).getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return rootScope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
    }
}
