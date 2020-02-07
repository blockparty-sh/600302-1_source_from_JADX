package com.bitcoin.mwallet.app.components.onboardingpager;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0016R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/onboardingpager/OnBoardingPagerAdapter;", "Landroidx/fragment/app/FragmentPagerAdapter;", "fm", "Landroidx/fragment/app/FragmentManager;", "onBoardElementList", "", "Lcom/bitcoin/mwallet/app/components/onboardingpager/OnBoardElement;", "(Landroidx/fragment/app/FragmentManager;Ljava/util/List;)V", "getCount", "", "getItem", "Landroidx/fragment/app/Fragment;", "position", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: OnBoardingPagerAdapter.kt */
public final class OnBoardingPagerAdapter extends FragmentPagerAdapter {
    private final List<OnBoardElement> onBoardElementList;

    public OnBoardingPagerAdapter(@NotNull FragmentManager fragmentManager, @NotNull List<OnBoardElement> list) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        Intrinsics.checkParameterIsNotNull(list, "onBoardElementList");
        super(fragmentManager, 1);
        this.onBoardElementList = list;
    }

    @NotNull
    public Fragment getItem(int i) {
        return OnBoardingPagerHolderView.Companion.newInstance(((OnBoardElement) this.onBoardElementList.get(i)).getImageResource(), ((OnBoardElement) this.onBoardElementList.get(i)).getDescriptionResource());
    }

    public int getCount() {
        return this.onBoardElementList.size();
    }
}
