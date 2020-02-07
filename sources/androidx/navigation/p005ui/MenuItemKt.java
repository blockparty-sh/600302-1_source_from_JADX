package androidx.navigation.p005ui;

import android.view.MenuItem;
import androidx.navigation.NavController;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, mo37405d2 = {"onNavDestinationSelected", "", "Landroid/view/MenuItem;", "navController", "Landroidx/navigation/NavController;", "navigation-ui-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* renamed from: androidx.navigation.ui.MenuItemKt */
/* compiled from: MenuItem.kt */
public final class MenuItemKt {
    public static final boolean onNavDestinationSelected(@NotNull MenuItem menuItem, @NotNull NavController navController) {
        Intrinsics.checkParameterIsNotNull(menuItem, "$this$onNavDestinationSelected");
        Intrinsics.checkParameterIsNotNull(navController, "navController");
        return NavigationUI.onNavDestinationSelected(menuItem, navController);
    }
}
