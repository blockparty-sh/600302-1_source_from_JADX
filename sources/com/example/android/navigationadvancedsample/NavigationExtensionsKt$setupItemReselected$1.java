package com.example.android.navigationadvancedsample;

import android.util.SparseArray;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemReselectedListener;
import com.leanplum.internal.Constants.Params;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "item", "Landroid/view/MenuItem;", "onNavigationItemReselected"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: NavigationExtensions.kt */
final class NavigationExtensionsKt$setupItemReselected$1 implements OnNavigationItemReselectedListener {
    final /* synthetic */ FragmentManager $fragmentManager;
    final /* synthetic */ SparseArray $graphIdToTagMap;

    NavigationExtensionsKt$setupItemReselected$1(SparseArray sparseArray, FragmentManager fragmentManager) {
        this.$graphIdToTagMap = sparseArray;
        this.$fragmentManager = fragmentManager;
    }

    public final void onNavigationItemReselected(@NotNull MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, Params.IAP_ITEM);
        Fragment findFragmentByTag = this.$fragmentManager.findFragmentByTag((String) this.$graphIdToTagMap.get(menuItem.getItemId()));
        if (findFragmentByTag != null) {
            NavController navController = ((NavHostFragment) findFragmentByTag).getNavController();
            Intrinsics.checkExpressionValueIsNotNull(navController, "selectedFragment.navController");
            NavGraph graph = navController.getGraph();
            Intrinsics.checkExpressionValueIsNotNull(graph, "navController.graph");
            navController.popBackStack(graph.getStartDestination(), false);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.navigation.fragment.NavHostFragment");
    }
}
