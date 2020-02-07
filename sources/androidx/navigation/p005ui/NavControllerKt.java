package androidx.navigation.p005ui;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.p005ui.AppBarConfiguration.Builder;
import androidx.navigation.p005ui.AppBarConfiguration.OnNavigateUpListener;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, mo37405d2 = {"navigateUp", "", "Landroidx/navigation/NavController;", "drawerLayout", "Landroidx/drawerlayout/widget/DrawerLayout;", "appBarConfiguration", "Landroidx/navigation/ui/AppBarConfiguration;", "navigation-ui-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* renamed from: androidx.navigation.ui.NavControllerKt */
/* compiled from: NavController.kt */
public final class NavControllerKt {
    public static final boolean navigateUp(@NotNull NavController navController, @Nullable DrawerLayout drawerLayout) {
        Intrinsics.checkParameterIsNotNull(navController, "$this$navigateUp");
        NavGraph graph = navController.getGraph();
        Intrinsics.checkExpressionValueIsNotNull(graph, "graph");
        Function0 function0 = AppBarConfigurationKt$AppBarConfiguration$1.INSTANCE;
        AppBarConfiguration build = new Builder(graph).setDrawerLayout(drawerLayout).setFallbackOnNavigateUpListener((OnNavigateUpListener) (function0 != 0 ? new C0503x56421ee5(function0) : function0)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        return NavigationUI.navigateUp(navController, build);
    }

    public static final boolean navigateUp(@NotNull NavController navController, @NotNull AppBarConfiguration appBarConfiguration) {
        Intrinsics.checkParameterIsNotNull(navController, "$this$navigateUp");
        Intrinsics.checkParameterIsNotNull(appBarConfiguration, "appBarConfiguration");
        return NavigationUI.navigateUp(navController, appBarConfiguration);
    }
}
