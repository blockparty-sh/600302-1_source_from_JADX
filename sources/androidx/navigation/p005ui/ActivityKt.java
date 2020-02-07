package androidx.navigation.p005ui;

import androidx.appcompat.app.AppCompatActivity;
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

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0007\u001a\u00020\b¨\u0006\t"}, mo37405d2 = {"setupActionBarWithNavController", "", "Landroidx/appcompat/app/AppCompatActivity;", "navController", "Landroidx/navigation/NavController;", "drawerLayout", "Landroidx/drawerlayout/widget/DrawerLayout;", "configuration", "Landroidx/navigation/ui/AppBarConfiguration;", "navigation-ui-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* renamed from: androidx.navigation.ui.ActivityKt */
/* compiled from: Activity.kt */
public final class ActivityKt {
    public static final void setupActionBarWithNavController(@NotNull AppCompatActivity appCompatActivity, @NotNull NavController navController, @Nullable DrawerLayout drawerLayout) {
        Intrinsics.checkParameterIsNotNull(appCompatActivity, "$this$setupActionBarWithNavController");
        Intrinsics.checkParameterIsNotNull(navController, "navController");
        NavGraph graph = navController.getGraph();
        Intrinsics.checkExpressionValueIsNotNull(graph, "navController.graph");
        Function0 function0 = AppBarConfigurationKt$AppBarConfiguration$1.INSTANCE;
        AppBarConfiguration build = new Builder(graph).setDrawerLayout(drawerLayout).setFallbackOnNavigateUpListener((OnNavigateUpListener) (function0 != 0 ? new C0503x56421ee5(function0) : function0)).build();
        Intrinsics.checkExpressionValueIsNotNull(build, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        NavigationUI.setupActionBarWithNavController(appCompatActivity, navController, build);
    }

    public static /* synthetic */ void setupActionBarWithNavController$default(AppCompatActivity appCompatActivity, NavController navController, AppBarConfiguration appBarConfiguration, int i, Object obj) {
        if ((i & 2) != 0) {
            NavGraph graph = navController.getGraph();
            Intrinsics.checkExpressionValueIsNotNull(graph, "navController.graph");
            DrawerLayout drawerLayout = null;
            Function0 function0 = AppBarConfigurationKt$AppBarConfiguration$1.INSTANCE;
            appBarConfiguration = new Builder(graph).setDrawerLayout(drawerLayout).setFallbackOnNavigateUpListener((OnNavigateUpListener) (function0 != 0 ? new C0503x56421ee5(function0) : function0)).build();
            Intrinsics.checkExpressionValueIsNotNull(appBarConfiguration, "AppBarConfiguration.Buil…eUpListener)\n    .build()");
        }
        setupActionBarWithNavController(appCompatActivity, navController, appBarConfiguration);
    }

    public static final void setupActionBarWithNavController(@NotNull AppCompatActivity appCompatActivity, @NotNull NavController navController, @NotNull AppBarConfiguration appBarConfiguration) {
        Intrinsics.checkParameterIsNotNull(appCompatActivity, "$this$setupActionBarWithNavController");
        Intrinsics.checkParameterIsNotNull(navController, "navController");
        Intrinsics.checkParameterIsNotNull(appBarConfiguration, "configuration");
        NavigationUI.setupActionBarWithNavController(appCompatActivity, navController, appBarConfiguration);
    }
}
