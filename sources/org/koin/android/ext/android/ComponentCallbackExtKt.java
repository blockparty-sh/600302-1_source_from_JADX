package org.koin.android.ext.android;

import android.content.ComponentCallbacks;
import androidx.exifinterface.media.ExifInterface;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.koin.core.Koin;
import org.koin.core.KoinComponent;
import org.koin.core.context.GlobalContext;
import org.koin.core.parameter.DefinitionParameters;
import org.koin.core.qualifier.Qualifier;
import org.koin.core.scope.Scope;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00004\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a:\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001\"\u0006\b\u0001\u0010\u0002\u0018\u0001*\u00020\u00032\u0016\b\n\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\u0004\u0018\u0001`\u0007H\b¢\u0006\u0002\u0010\b\u001aB\u0010\t\u001a\u0002H\n\"\n\b\u0000\u0010\n\u0018\u0001*\u00020\u000b*\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0016\b\n\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\u0004\u0018\u0001`\u0007H\b¢\u0006\u0002\u0010\u000e\u001a\n\u0010\u000f\u001a\u00020\u0010*\u00020\u0003\u001aC\u0010\u0011\u001a\b\u0012\u0004\u0012\u0002H\n0\u0012\"\n\b\u0000\u0010\n\u0018\u0001*\u00020\u000b*\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\u0016\b\n\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005j\u0004\u0018\u0001`\u0007H\b¨\u0006\u0013"}, mo37405d2 = {"bind", "S", "P", "Landroid/content/ComponentCallbacks;", "parameters", "Lkotlin/Function0;", "Lorg/koin/core/parameter/DefinitionParameters;", "Lorg/koin/core/parameter/ParametersDefinition;", "(Landroid/content/ComponentCallbacks;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "get", "T", "", "qualifier", "Lorg/koin/core/qualifier/Qualifier;", "(Landroid/content/ComponentCallbacks;Lorg/koin/core/qualifier/Qualifier;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getKoin", "Lorg/koin/core/Koin;", "inject", "Lkotlin/Lazy;", "koin-android_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: ComponentCallbackExt.kt */
public final class ComponentCallbackExtKt {
    @NotNull
    public static final Koin getKoin(@NotNull ComponentCallbacks componentCallbacks) {
        Intrinsics.checkParameterIsNotNull(componentCallbacks, "$this$getKoin");
        if (componentCallbacks instanceof KoinComponent) {
            return ((KoinComponent) componentCallbacks).getKoin();
        }
        return GlobalContext.get().getKoin();
    }

    static /* synthetic */ Lazy inject$default(ComponentCallbacks componentCallbacks, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        Intrinsics.needClassReification();
        return LazyKt.lazy(new ComponentCallbackExtKt$inject$1(componentCallbacks, qualifier, function0));
    }

    private static final <T> Lazy<T> inject(@NotNull ComponentCallbacks componentCallbacks, Qualifier qualifier, Function0<DefinitionParameters> function0) {
        Intrinsics.needClassReification();
        return LazyKt.lazy(new ComponentCallbackExtKt$inject$1(componentCallbacks, qualifier, function0));
    }

    static /* synthetic */ Object get$default(ComponentCallbacks componentCallbacks, Qualifier qualifier, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            qualifier = null;
        }
        if ((i & 2) != 0) {
            function0 = null;
        }
        Scope rootScope = getKoin(componentCallbacks).getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return rootScope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
    }

    private static final <T> T get(@NotNull ComponentCallbacks componentCallbacks, Qualifier qualifier, Function0<DefinitionParameters> function0) {
        Scope rootScope = getKoin(componentCallbacks).getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        return rootScope.get(Reflection.getOrCreateKotlinClass(Object.class), qualifier, function0);
    }

    static /* synthetic */ Object bind$default(ComponentCallbacks componentCallbacks, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            function0 = null;
        }
        Scope rootScope = getKoin(componentCallbacks).getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LATITUDE_SOUTH);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(4, "P");
        return rootScope.bind(Reflection.getOrCreateKotlinClass(Object.class), orCreateKotlinClass, function0);
    }

    private static final <S, P> S bind(@NotNull ComponentCallbacks componentCallbacks, Function0<DefinitionParameters> function0) {
        Scope rootScope = getKoin(componentCallbacks).getRootScope();
        Intrinsics.reifiedOperationMarker(4, ExifInterface.LATITUDE_SOUTH);
        KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(Object.class);
        Intrinsics.reifiedOperationMarker(4, "P");
        return rootScope.bind(Reflection.getOrCreateKotlinClass(Object.class), orCreateKotlinClass, function0);
    }
}
