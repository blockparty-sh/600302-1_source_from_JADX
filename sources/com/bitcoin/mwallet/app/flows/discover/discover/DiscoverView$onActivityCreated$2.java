package com.bitcoin.mwallet.app.flows.discover.discover;

import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.core.models.discover.FeaturedBusiness;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import p015io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import p015io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, mo37405d2 = {"<anonymous>", "", "featuredBusinesses", "", "Lcom/bitcoin/mwallet/core/models/discover/FeaturedBusiness;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: DiscoverView.kt */
final class DiscoverView$onActivityCreated$2<T> implements Observer<List<? extends FeaturedBusiness>> {
    final /* synthetic */ SectionedRecyclerViewAdapter $sectionedAdapter;

    DiscoverView$onActivityCreated$2(SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter) {
        this.$sectionedAdapter = sectionedRecyclerViewAdapter;
    }

    public final void onChanged(List<FeaturedBusiness> list) {
        Section section = this.$sectionedAdapter.getSection(0);
        if (section != null) {
            FeaturedSectionAdapter featuredSectionAdapter = (FeaturedSectionAdapter) section;
            Intrinsics.checkExpressionValueIsNotNull(list, "featuredBusinesses");
            featuredSectionAdapter.setNewData(list);
            this.$sectionedAdapter.notifyDataSetChanged();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.app.flows.discover.discover.FeaturedSectionAdapter");
    }
}
