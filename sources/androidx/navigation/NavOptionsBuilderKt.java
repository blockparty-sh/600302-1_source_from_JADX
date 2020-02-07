package androidx.navigation;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006¨\u0006\u0007"}, mo37405d2 = {"navOptions", "Landroidx/navigation/NavOptions;", "optionsBuilder", "Lkotlin/Function1;", "Landroidx/navigation/NavOptionsBuilder;", "", "Lkotlin/ExtensionFunctionType;", "navigation-common-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: NavOptionsBuilder.kt */
public final class NavOptionsBuilderKt {
    @NotNull
    public static final NavOptions navOptions(@NotNull Function1<? super NavOptionsBuilder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(function1, "optionsBuilder");
        NavOptionsBuilder navOptionsBuilder = new NavOptionsBuilder();
        function1.invoke(navOptionsBuilder);
        return navOptionsBuilder.build$navigation_common_ktx_release();
    }
}
