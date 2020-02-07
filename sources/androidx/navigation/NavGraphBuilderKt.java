package androidx.navigation;

import androidx.annotation.IdRes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a:\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0001\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00042\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\bH\b\u001a:\u0010\u0000\u001a\u00020\t*\u00020\n2\b\b\u0003\u0010\u0003\u001a\u00020\u00042\b\b\u0001\u0010\u0005\u001a\u00020\u00042\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u0007¢\u0006\u0002\b\bH\b¨\u0006\u000b"}, mo37405d2 = {"navigation", "", "Landroidx/navigation/NavGraphBuilder;", "id", "", "startDestination", "builder", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "Landroidx/navigation/NavGraph;", "Landroidx/navigation/NavigatorProvider;", "navigation-common-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: NavGraphBuilder.kt */
public final class NavGraphBuilderKt {
    public static /* synthetic */ NavGraph navigation$default(NavigatorProvider navigatorProvider, int i, int i2, Function1 function1, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = 0;
        }
        Intrinsics.checkParameterIsNotNull(navigatorProvider, "$this$navigation");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        NavGraphBuilder navGraphBuilder = new NavGraphBuilder(navigatorProvider, i, i2);
        function1.invoke(navGraphBuilder);
        return navGraphBuilder.build();
    }

    @NotNull
    public static final NavGraph navigation(@NotNull NavigatorProvider navigatorProvider, @IdRes int i, @IdRes int i2, @NotNull Function1<? super NavGraphBuilder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(navigatorProvider, "$this$navigation");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        NavGraphBuilder navGraphBuilder = new NavGraphBuilder(navigatorProvider, i, i2);
        function1.invoke(navGraphBuilder);
        return navGraphBuilder.build();
    }

    public static final void navigation(@NotNull NavGraphBuilder navGraphBuilder, @IdRes int i, @IdRes int i2, @NotNull Function1<? super NavGraphBuilder, Unit> function1) {
        Intrinsics.checkParameterIsNotNull(navGraphBuilder, "$this$navigation");
        Intrinsics.checkParameterIsNotNull(function1, "builder");
        NavGraphBuilder navGraphBuilder2 = new NavGraphBuilder(navGraphBuilder.getProvider(), i, i2);
        function1.invoke(navGraphBuilder2);
        navGraphBuilder.destination(navGraphBuilder2);
    }
}
