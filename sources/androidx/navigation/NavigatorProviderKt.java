package androidx.navigation;

import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a,\u0010\u0000\u001a\u0002H\u0001\"\u0010\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\u0010\u0007\u001a2\u0010\u0000\u001a\u0002H\u0001\"\u0010\b\u0000\u0010\u0001*\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002*\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00010\tH\n¢\u0006\u0002\u0010\n\u001a\u001d\u0010\u000b\u001a\u00020\f*\u00020\u00042\u000e\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002H\n\u001a7\u0010\u000e\u001a\u0014\u0012\u000e\b\u0001\u0012\n \u000f*\u0004\u0018\u00010\u00030\u0003\u0018\u00010\u0002*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\r\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0002H\n¨\u0006\u0010"}, mo37405d2 = {"get", "T", "Landroidx/navigation/Navigator;", "Landroidx/navigation/NavDestination;", "Landroidx/navigation/NavigatorProvider;", "name", "", "(Landroidx/navigation/NavigatorProvider;Ljava/lang/String;)Landroidx/navigation/Navigator;", "clazz", "Lkotlin/reflect/KClass;", "(Landroidx/navigation/NavigatorProvider;Lkotlin/reflect/KClass;)Landroidx/navigation/Navigator;", "plusAssign", "", "navigator", "set", "kotlin.jvm.PlatformType", "navigation-common-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: NavigatorProvider.kt */
public final class NavigatorProviderKt {
    @NotNull
    public static final <T extends Navigator<? extends NavDestination>> T get(@NotNull NavigatorProvider navigatorProvider, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(navigatorProvider, "$this$get");
        Intrinsics.checkParameterIsNotNull(str, "name");
        T navigator = navigatorProvider.getNavigator(str);
        Intrinsics.checkExpressionValueIsNotNull(navigator, "getNavigator(name)");
        return navigator;
    }

    @NotNull
    public static final <T extends Navigator<? extends NavDestination>> T get(@NotNull NavigatorProvider navigatorProvider, @NotNull KClass<T> kClass) {
        Intrinsics.checkParameterIsNotNull(navigatorProvider, "$this$get");
        Intrinsics.checkParameterIsNotNull(kClass, "clazz");
        T navigator = navigatorProvider.getNavigator(JvmClassMappingKt.getJavaClass(kClass));
        Intrinsics.checkExpressionValueIsNotNull(navigator, "getNavigator(clazz.java)");
        return navigator;
    }

    @Nullable
    public static final Navigator<? extends NavDestination> set(@NotNull NavigatorProvider navigatorProvider, @NotNull String str, @NotNull Navigator<? extends NavDestination> navigator) {
        Intrinsics.checkParameterIsNotNull(navigatorProvider, "$this$set");
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(navigator, "navigator");
        return navigatorProvider.addNavigator(str, navigator);
    }

    public static final void plusAssign(@NotNull NavigatorProvider navigatorProvider, @NotNull Navigator<? extends NavDestination> navigator) {
        Intrinsics.checkParameterIsNotNull(navigatorProvider, "$this$plusAssign");
        Intrinsics.checkParameterIsNotNull(navigator, "navigator");
        navigatorProvider.addNavigator(navigator);
    }
}
