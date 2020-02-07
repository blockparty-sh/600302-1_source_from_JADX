package com.bitcoin.mwallet.app.flows.discover.discover;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.viper.ScreenView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.annotations.Nullable;
import p015io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/discover/discover/DiscoverView;", "Lcom/bitcoin/mwallet/app/viper/ScreenView;", "Lcom/bitcoin/mwallet/app/flows/discover/discover/DiscoverBuilder;", "Lcom/bitcoin/mwallet/app/flows/discover/discover/DiscoverPresenter;", "()V", "onActivityCreated", "", "savedInstanceState", "Landroid/os/Bundle;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: DiscoverView.kt */
public final class DiscoverView extends ScreenView<DiscoverBuilder, DiscoverPresenter> {
    private HashMap _$_findViewCache;

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

    public DiscoverView() {
        super(C1018R.layout.fragment_screen_discover_discover, Reflection.getOrCreateKotlinClass(DiscoverBuilder.class));
    }

    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter = new SectionedRecyclerViewAdapter();
        FeaturedSectionAdapter featuredSectionAdapter = new FeaturedSectionAdapter((DiscoverPresenter) getPresenter());
        UsefulLinkSectionAdapter usefulLinkSectionAdapter = new UsefulLinkSectionAdapter((DiscoverPresenter) getPresenter());
        sectionedRecyclerViewAdapter.addSection(featuredSectionAdapter);
        sectionedRecyclerViewAdapter.addSection(usefulLinkSectionAdapter);
        RecyclerView recyclerView = (RecyclerView) _$_findCachedViewById(C1018R.C1021id.externalLinkList);
        Intrinsics.checkExpressionValueIsNotNull(recyclerView, "externalLinkList");
        recyclerView.setAdapter(sectionedRecyclerViewAdapter);
        ((DiscoverPresenter) getPresenter()).getExternalLinks().observe(getViewLifecycleOwner(), new DiscoverView$onActivityCreated$1(sectionedRecyclerViewAdapter));
        ((DiscoverPresenter) getPresenter()).getFeaturedBusinesses().observe(getViewLifecycleOwner(), new DiscoverView$onActivityCreated$2(sectionedRecyclerViewAdapter));
    }
}
