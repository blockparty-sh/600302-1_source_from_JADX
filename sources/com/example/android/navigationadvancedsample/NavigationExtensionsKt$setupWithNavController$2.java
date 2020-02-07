package com.example.android.navigationadvancedsample;

import android.util.SparseArray;
import android.view.MenuItem;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.fragment.NavHostFragment;
import com.bitcoin.mwallet.C1018R;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.leanplum.internal.Constants.Params;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.BooleanRef;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "item", "Landroid/view/MenuItem;", "onNavigationItemSelected"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: NavigationExtensions.kt */
final class NavigationExtensionsKt$setupWithNavController$2 implements OnNavigationItemSelectedListener {
    final /* synthetic */ String $firstFragmentTag;
    final /* synthetic */ FragmentManager $fragmentManager;
    final /* synthetic */ SparseArray $graphIdToTagMap;
    final /* synthetic */ BooleanRef $isOnFirstFragment;
    final /* synthetic */ ObjectRef $selectedItemTag;
    final /* synthetic */ MutableLiveData $selectedNavController;

    NavigationExtensionsKt$setupWithNavController$2(FragmentManager fragmentManager, SparseArray sparseArray, ObjectRef objectRef, String str, BooleanRef booleanRef, MutableLiveData mutableLiveData) {
        this.$fragmentManager = fragmentManager;
        this.$graphIdToTagMap = sparseArray;
        this.$selectedItemTag = objectRef;
        this.$firstFragmentTag = str;
        this.$isOnFirstFragment = booleanRef;
        this.$selectedNavController = mutableLiveData;
    }

    public final boolean onNavigationItemSelected(@NotNull MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, Params.IAP_ITEM);
        if (this.$fragmentManager.isStateSaved()) {
            return false;
        }
        T t = (String) this.$graphIdToTagMap.get(menuItem.getItemId());
        if (!(!Intrinsics.areEqual((Object) (String) this.$selectedItemTag.element, (Object) t))) {
            return false;
        }
        this.$fragmentManager.popBackStack(this.$firstFragmentTag, 1);
        Fragment findFragmentByTag = this.$fragmentManager.findFragmentByTag(t);
        if (findFragmentByTag != null) {
            NavHostFragment navHostFragment = (NavHostFragment) findFragmentByTag;
            if (!Intrinsics.areEqual((Object) this.$firstFragmentTag, (Object) t)) {
                Fragment fragment = navHostFragment;
                FragmentTransaction primaryNavigationFragment = this.$fragmentManager.beginTransaction().setCustomAnimations(C1018R.anim.nav_default_enter_anim, C1018R.anim.nav_default_exit_anim, C1018R.anim.nav_default_pop_enter_anim, C1018R.anim.nav_default_pop_exit_anim).attach(fragment).setPrimaryNavigationFragment(fragment);
                SparseArray sparseArray = this.$graphIdToTagMap;
                int size = sparseArray.size();
                for (int i = 0; i < size; i++) {
                    sparseArray.keyAt(i);
                    if (!Intrinsics.areEqual((Object) (String) sparseArray.valueAt(i), (Object) t)) {
                        Fragment findFragmentByTag2 = this.$fragmentManager.findFragmentByTag(this.$firstFragmentTag);
                        if (findFragmentByTag2 == null) {
                            Intrinsics.throwNpe();
                        }
                        primaryNavigationFragment.detach(findFragmentByTag2);
                    }
                }
                primaryNavigationFragment.addToBackStack(this.$firstFragmentTag).setReorderingAllowed(true).commit();
            }
            ObjectRef objectRef = this.$selectedItemTag;
            objectRef.element = t;
            this.$isOnFirstFragment.element = Intrinsics.areEqual((Object) (String) objectRef.element, (Object) this.$firstFragmentTag);
            this.$selectedNavController.setValue(navHostFragment.getNavController());
            return true;
        }
        throw new TypeCastException("null cannot be cast to non-null type androidx.navigation.fragment.NavHostFragment");
    }
}
