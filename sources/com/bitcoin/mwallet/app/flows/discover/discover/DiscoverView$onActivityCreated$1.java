package com.bitcoin.mwallet.app.flows.discover.discover;

import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.core.models.discover.Link;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import p015io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import p015io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "externalLinks", "", "Lcom/bitcoin/mwallet/core/models/discover/Link;", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: DiscoverView.kt */
final class DiscoverView$onActivityCreated$1<T> implements Observer<List<? extends Link>> {
    final /* synthetic */ SectionedRecyclerViewAdapter $sectionedAdapter;

    DiscoverView$onActivityCreated$1(SectionedRecyclerViewAdapter sectionedRecyclerViewAdapter) {
        this.$sectionedAdapter = sectionedRecyclerViewAdapter;
    }

    public final void onChanged(@NotNull List<Link> list) {
        Intrinsics.checkParameterIsNotNull(list, "externalLinks");
        Section section = this.$sectionedAdapter.getSection(1);
        if (section != null) {
            ((UsefulLinkSectionAdapter) section).setNewData(list);
            this.$sectionedAdapter.notifyDataSetChanged();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.app.flows.discover.discover.UsefulLinkSectionAdapter");
    }
}
