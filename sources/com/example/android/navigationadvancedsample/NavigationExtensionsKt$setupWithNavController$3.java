package com.example.android.navigationadvancedsample;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentManager.OnBackStackChangedListener;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlin.jvm.internal.Ref.IntRef;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "", "onBackStackChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: NavigationExtensions.kt */
final class NavigationExtensionsKt$setupWithNavController$3 implements OnBackStackChangedListener {
    final /* synthetic */ IntRef $firstFragmentGraphId;
    final /* synthetic */ String $firstFragmentTag;
    final /* synthetic */ FragmentManager $fragmentManager;
    final /* synthetic */ BooleanRef $isOnFirstFragment;
    final /* synthetic */ MutableLiveData $selectedNavController;
    final /* synthetic */ BottomNavigationView $this_setupWithNavController;

    NavigationExtensionsKt$setupWithNavController$3(BottomNavigationView bottomNavigationView, BooleanRef booleanRef, FragmentManager fragmentManager, String str, IntRef intRef, MutableLiveData mutableLiveData) {
        this.$this_setupWithNavController = bottomNavigationView;
        this.$isOnFirstFragment = booleanRef;
        this.$fragmentManager = fragmentManager;
        this.$firstFragmentTag = str;
        this.$firstFragmentGraphId = intRef;
        this.$selectedNavController = mutableLiveData;
    }

    public final void onBackStackChanged() {
        if (!this.$isOnFirstFragment.element) {
            FragmentManager fragmentManager = this.$fragmentManager;
            String str = this.$firstFragmentTag;
            Intrinsics.checkExpressionValueIsNotNull(str, "firstFragmentTag");
            if (!NavigationExtensionsKt.isOnBackStack(fragmentManager, str)) {
                this.$this_setupWithNavController.setSelectedItemId(this.$firstFragmentGraphId.element);
            }
        }
        NavController navController = (NavController) this.$selectedNavController.getValue();
        if (navController != null) {
            Intrinsics.checkExpressionValueIsNotNull(navController, "controller");
            if (navController.getCurrentDestination() == null) {
                NavGraph graph = navController.getGraph();
                Intrinsics.checkExpressionValueIsNotNull(graph, "controller.graph");
                navController.navigate(graph.getId());
            }
        }
    }
}
