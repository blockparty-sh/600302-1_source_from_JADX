package com.bitcoin.mwallet.app.components.onboardingpager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager.widget.ViewPager.OnPageChangeListener;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.ComponentView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J&\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0016J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u000eH\u0016J \u0010\u001a\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000eH\u0016J\u0010\u0010\u001e\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u001f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/onboardingpager/OnBoardingPagerView;", "Lcom/bitcoin/mwallet/app/viper/ComponentView;", "Lcom/bitcoin/mwallet/app/components/onboardingpager/OnBoardingPagerBuilder;", "Lcom/bitcoin/mwallet/app/components/onboardingpager/OnBoardingPagerPresenter;", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "()V", "indicatorList", "", "Landroid/widget/TextView;", "getIndicatorList", "()Ljava/util/List;", "addDotsIndicator", "", "position", "", "onActivityCreated", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onPageScrollStateChanged", "state", "onPageScrolled", "positionOffset", "", "positionOffsetPixels", "onPageSelected", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: OnBoardingPagerView.kt */
public final class OnBoardingPagerView extends ComponentView<OnBoardingPagerBuilder, OnBoardingPagerPresenter> implements OnPageChangeListener {
    private HashMap _$_findViewCache;
    @NotNull
    private final List<TextView> indicatorList = new ArrayList();

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view == null) {
            View view2 = getView();
            if (view2 == null) {
                return null;
            }
            view = view2.findViewById(i);
            this._$_findViewCache.put(Integer.valueOf(i), view);
        }
        return view;
    }

    public /* synthetic */ void onDestroyView() {
        super.onDestroyView();
        _$_clearFindViewByIdCache();
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public OnBoardingPagerView() {
        super(C1018R.layout.fragment_component_onboardingpager, Reflection.getOrCreateKotlinClass(OnBoardingPagerBuilder.class));
    }

    @NotNull
    public final List<TextView> getIndicatorList() {
        return this.indicatorList;
    }

    public void onPageSelected(int i) {
        addDotsIndicator(i);
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        Intrinsics.checkParameterIsNotNull(layoutInflater, "inflater");
        View inflate = layoutInflater.inflate(C1018R.layout.fragment_component_onboardingpager, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "inflater.inflate(R.layou…gpager, container, false)");
        return inflate;
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        View view = getView();
        OnBoardingPagerAdapter onBoardingPagerAdapter = null;
        ViewPager viewPager = view != null ? (ViewPager) view.findViewById(C1018R.C1021id.slideLayout) : null;
        if (viewPager != null) {
            FragmentActivity activity = getActivity();
            if (activity != null) {
                FragmentManager supportFragmentManager = activity.getSupportFragmentManager();
                if (supportFragmentManager != null) {
                    Intrinsics.checkExpressionValueIsNotNull(supportFragmentManager, "it");
                    onBoardingPagerAdapter = new OnBoardingPagerAdapter(supportFragmentManager, ((OnBoardingPagerPresenter) getPresenter()).getOnBoardElementList());
                }
            }
            viewPager.setAdapter(onBoardingPagerAdapter);
        }
        addDotsIndicator(0);
        if (viewPager != null) {
            viewPager.addOnPageChangeListener(this);
        }
    }

    private final void addDotsIndicator(int i) {
        this.indicatorList.clear();
        ((LinearLayout) _$_findCachedViewById(C1018R.C1021id.dotIndicatorLayout)).removeAllViews();
        for (OnBoardElement onBoardElement : ((OnBoardingPagerPresenter) getPresenter()).getOnBoardElementList()) {
            TextView textView = new TextView(getContext());
            textView.setText(HtmlCompat.fromHtml("&#8226;", 0));
            textView.setTextSize(35.0f);
            textView.setTextColor(getResources().getColor(C1018R.C1019color.colorPagerUnselected));
            this.indicatorList.add(textView);
            ((LinearLayout) _$_findCachedViewById(C1018R.C1021id.dotIndicatorLayout)).addView(textView);
        }
        if (this.indicatorList.size() > 0) {
            ((TextView) this.indicatorList.get(i)).setTextColor(getResources().getColor(C1018R.C1019color.colorPagerSelected));
        }
    }
}
