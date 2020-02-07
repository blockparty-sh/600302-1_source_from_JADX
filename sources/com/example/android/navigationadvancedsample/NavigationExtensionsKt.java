package com.example.android.navigationadvancedsample;

import android.content.Intent;
import android.util.SparseArray;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentManager.BackStackEntry;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlin.jvm.internal.Ref.IntRef;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000N\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\u0018\u0010\b\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a(\u0010\r\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\fH\u0002\u001a\u0014\u0010\u0011\u001a\u00020\u0007*\u00020\u00032\u0006\u0010\u0012\u001a\u00020\nH\u0002\u001a2\u0010\u0013\u001a\u00020\u0001*\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u00162\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0002\u001a\"\u0010\u0019\u001a\u00020\u0001*\u00020\u00142\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\n0\u001b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a6\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d*\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u00162\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018¨\u0006\u001f"}, mo37405d2 = {"attachNavHostFragment", "", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "navHostFragment", "Landroidx/navigation/fragment/NavHostFragment;", "isPrimaryNavFragment", "", "detachNavHostFragment", "getFragmentTag", "", "index", "", "obtainNavHostFragment", "fragmentTag", "navGraphId", "containerId", "isOnBackStack", "backStackName", "setupDeepLinks", "Lcom/google/android/material/bottomnavigation/BottomNavigationView;", "navGraphIds", "", "intent", "Landroid/content/Intent;", "setupItemReselected", "graphIdToTagMap", "Landroid/util/SparseArray;", "setupWithNavController", "Landroidx/lifecycle/LiveData;", "Landroidx/navigation/NavController;", "app_replaceRelease"}, mo37406k = 2, mo37407mv = {1, 1, 15})
/* compiled from: NavigationExtensions.kt */
public final class NavigationExtensionsKt {
    @NotNull
    public static final LiveData<NavController> setupWithNavController(@NotNull BottomNavigationView bottomNavigationView, @NotNull List<Integer> list, @NotNull FragmentManager fragmentManager, int i, @NotNull Intent intent) {
        BottomNavigationView bottomNavigationView2 = bottomNavigationView;
        List<Integer> list2 = list;
        FragmentManager fragmentManager2 = fragmentManager;
        Intrinsics.checkParameterIsNotNull(bottomNavigationView2, "$this$setupWithNavController");
        Intrinsics.checkParameterIsNotNull(list2, "navGraphIds");
        Intrinsics.checkParameterIsNotNull(fragmentManager2, "fragmentManager");
        Intrinsics.checkParameterIsNotNull(intent, "intent");
        SparseArray sparseArray = new SparseArray();
        MutableLiveData mutableLiveData = new MutableLiveData();
        IntRef intRef = new IntRef();
        intRef.element = 0;
        int i2 = 0;
        for (Object next : list2) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            int intValue = ((Number) next).intValue();
            String fragmentTag = getFragmentTag(i2);
            NavHostFragment obtainNavHostFragment = obtainNavHostFragment(fragmentManager2, fragmentTag, intValue, i);
            NavController navController = obtainNavHostFragment.getNavController();
            Intrinsics.checkExpressionValueIsNotNull(navController, "navHostFragment.navController");
            NavGraph graph = navController.getGraph();
            Intrinsics.checkExpressionValueIsNotNull(graph, "navHostFragment.navController.graph");
            int id = graph.getId();
            if (i2 == 0) {
                intRef.element = id;
            }
            sparseArray.put(id, fragmentTag);
            if (bottomNavigationView.getSelectedItemId() == id) {
                mutableLiveData.setValue(obtainNavHostFragment.getNavController());
                attachNavHostFragment(fragmentManager2, obtainNavHostFragment, i2 == 0);
            } else {
                detachNavHostFragment(fragmentManager2, obtainNavHostFragment);
            }
            i2 = i3;
        }
        int i4 = i;
        ObjectRef objectRef = new ObjectRef();
        objectRef.element = (String) sparseArray.get(bottomNavigationView.getSelectedItemId());
        String str = (String) sparseArray.get(intRef.element);
        BooleanRef booleanRef = new BooleanRef();
        booleanRef.element = Intrinsics.areEqual((Object) (String) objectRef.element, (Object) str);
        FragmentManager fragmentManager3 = fragmentManager;
        BooleanRef booleanRef2 = booleanRef;
        NavigationExtensionsKt$setupWithNavController$2 navigationExtensionsKt$setupWithNavController$2 = new NavigationExtensionsKt$setupWithNavController$2(fragmentManager3, sparseArray, objectRef, str, booleanRef, mutableLiveData);
        bottomNavigationView2.setOnNavigationItemSelectedListener(navigationExtensionsKt$setupWithNavController$2);
        setupItemReselected(bottomNavigationView2, sparseArray, fragmentManager2);
        setupDeepLinks(bottomNavigationView, list, fragmentManager, i, intent);
        NavigationExtensionsKt$setupWithNavController$3 navigationExtensionsKt$setupWithNavController$3 = new NavigationExtensionsKt$setupWithNavController$3(bottomNavigationView, booleanRef2, fragmentManager3, str, intRef, mutableLiveData);
        fragmentManager2.addOnBackStackChangedListener(navigationExtensionsKt$setupWithNavController$3);
        return mutableLiveData;
    }

    private static final void setupDeepLinks(@NotNull BottomNavigationView bottomNavigationView, List<Integer> list, FragmentManager fragmentManager, int i, Intent intent) {
        int i2 = 0;
        for (Object next : list) {
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            NavHostFragment obtainNavHostFragment = obtainNavHostFragment(fragmentManager, getFragmentTag(i2), ((Number) next).intValue(), i);
            if (obtainNavHostFragment.getNavController().handleDeepLink(intent)) {
                int selectedItemId = bottomNavigationView.getSelectedItemId();
                NavController navController = obtainNavHostFragment.getNavController();
                String str = "navHostFragment.navController";
                Intrinsics.checkExpressionValueIsNotNull(navController, str);
                NavGraph graph = navController.getGraph();
                String str2 = "navHostFragment.navController.graph";
                Intrinsics.checkExpressionValueIsNotNull(graph, str2);
                if (selectedItemId != graph.getId()) {
                    NavController navController2 = obtainNavHostFragment.getNavController();
                    Intrinsics.checkExpressionValueIsNotNull(navController2, str);
                    NavGraph graph2 = navController2.getGraph();
                    Intrinsics.checkExpressionValueIsNotNull(graph2, str2);
                    bottomNavigationView.setSelectedItemId(graph2.getId());
                }
            }
            i2 = i3;
        }
    }

    private static final void setupItemReselected(@NotNull BottomNavigationView bottomNavigationView, SparseArray<String> sparseArray, FragmentManager fragmentManager) {
        bottomNavigationView.setOnNavigationItemReselectedListener(new NavigationExtensionsKt$setupItemReselected$1(sparseArray, fragmentManager));
    }

    private static final void detachNavHostFragment(FragmentManager fragmentManager, NavHostFragment navHostFragment) {
        fragmentManager.beginTransaction().detach(navHostFragment).commitNow();
    }

    private static final void attachNavHostFragment(FragmentManager fragmentManager, NavHostFragment navHostFragment, boolean z) {
        Fragment fragment = navHostFragment;
        FragmentTransaction attach = fragmentManager.beginTransaction().attach(fragment);
        if (z) {
            attach.setPrimaryNavigationFragment(fragment);
        }
        attach.commitNow();
    }

    private static final NavHostFragment obtainNavHostFragment(FragmentManager fragmentManager, String str, int i, int i2) {
        NavHostFragment navHostFragment = (NavHostFragment) fragmentManager.findFragmentByTag(str);
        if (navHostFragment != null) {
            return navHostFragment;
        }
        NavHostFragment create = NavHostFragment.create(i);
        Intrinsics.checkExpressionValueIsNotNull(create, "NavHostFragment.create(navGraphId)");
        fragmentManager.beginTransaction().add(i2, create, str).commitNow();
        return create;
    }

    /* access modifiers changed from: private */
    public static final boolean isOnBackStack(@NotNull FragmentManager fragmentManager, String str) {
        int backStackEntryCount = fragmentManager.getBackStackEntryCount();
        for (int i = 0; i < backStackEntryCount; i++) {
            BackStackEntry backStackEntryAt = fragmentManager.getBackStackEntryAt(i);
            Intrinsics.checkExpressionValueIsNotNull(backStackEntryAt, "getBackStackEntryAt(index)");
            if (Intrinsics.areEqual((Object) backStackEntryAt.getName(), (Object) str)) {
                return true;
            }
        }
        return false;
    }

    private static final String getFragmentTag(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append("bottomNavigation#");
        sb.append(i);
        return sb.toString();
    }
}
