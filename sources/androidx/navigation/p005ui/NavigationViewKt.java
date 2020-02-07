package androidx.navigation.p005ui;

import androidx.navigation.NavController;
import com.google.android.material.navigation.NavigationView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, mo37405d2 = {"setupWithNavController", "", "Lcom/google/android/material/navigation/NavigationView;", "navController", "Landroidx/navigation/NavController;", "navigation-ui-ktx_release"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* renamed from: androidx.navigation.ui.NavigationViewKt */
/* compiled from: NavigationView.kt */
public final class NavigationViewKt {
    public static final void setupWithNavController(@NotNull NavigationView navigationView, @NotNull NavController navController) {
        Intrinsics.checkParameterIsNotNull(navigationView, "$this$setupWithNavController");
        Intrinsics.checkParameterIsNotNull(navController, "navController");
        NavigationUI.setupWithNavController(navigationView, navController);
    }
}
