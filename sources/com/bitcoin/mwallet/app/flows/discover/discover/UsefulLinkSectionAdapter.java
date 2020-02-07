package com.bitcoin.mwallet.app.flows.discover.discover;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.discover.Link;
import com.leanplum.internal.Constants.Params;
import com.squareup.picasso.Picasso;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongycastle.i18n.MessageBundle;
import p015io.github.luizgrp.sectionedrecyclerviewadapter.Section;
import p015io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0002\u001c\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0011H\u0016J\u001a\u0010\u0018\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0019\u001a\u00020\u000fH\u0016J\u0016\u0010\u001a\u001a\u00020\u00162\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u001e"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/discover/discover/UsefulLinkSectionAdapter;", "Lio/github/luizgrp/sectionedrecyclerviewadapter/Section;", "presenter", "Lcom/bitcoin/mwallet/app/flows/discover/discover/DiscoverPresenter;", "(Lcom/bitcoin/mwallet/app/flows/discover/discover/DiscoverPresenter;)V", "data", "", "Lcom/bitcoin/mwallet/core/models/discover/Link;", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/discover/discover/DiscoverPresenter;", "getContentItemsTotal", "", "getHeaderViewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "getItemViewHolder", "onBindHeaderViewHolder", "", "holder", "onBindItemViewHolder", "position", "setNewData", "links", "HeaderViewHolder", "UsefulLinkItemViewHolder", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: UsefulLinkSectionAdapter.kt */
public final class UsefulLinkSectionAdapter extends Section {
    @NotNull
    private List<Link> data = CollectionsKt.emptyList();
    @NotNull
    private final DiscoverPresenter presenter;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/discover/discover/UsefulLinkSectionAdapter$HeaderViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "getView", "()Landroid/view/View;", "bind", "", "title", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: UsefulLinkSectionAdapter.kt */
    public static final class HeaderViewHolder extends ViewHolder {
        @NotNull
        private final View view;

        public HeaderViewHolder(@NotNull View view2) {
            Intrinsics.checkParameterIsNotNull(view2, "view");
            super(view2);
            this.view = view2;
        }

        @NotNull
        public final View getView() {
            return this.view;
        }

        public final void bind(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, MessageBundle.TITLE_ENTRY);
            View findViewById = this.view.findViewById(C1018R.C1021id.sectionHeader);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById<TextView>(R.id.sectionHeader)");
            ((TextView) findViewById).setText(str);
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/discover/discover/UsefulLinkSectionAdapter$UsefulLinkItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "presenter", "Lcom/bitcoin/mwallet/app/flows/discover/discover/DiscoverPresenter;", "(Landroid/view/View;Lcom/bitcoin/mwallet/app/flows/discover/discover/DiscoverPresenter;)V", "linkImage", "Landroid/widget/ImageView;", "linkName", "Landroid/widget/TextView;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/discover/discover/DiscoverPresenter;", "getView", "()Landroid/view/View;", "bind", "", "item", "Lcom/bitcoin/mwallet/core/models/discover/Link;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: UsefulLinkSectionAdapter.kt */
    public static final class UsefulLinkItemViewHolder extends ViewHolder {
        private final ImageView linkImage;
        private final TextView linkName;
        @NotNull
        private final DiscoverPresenter presenter;
        @NotNull
        private final View view;

        public UsefulLinkItemViewHolder(@NotNull View view2, @NotNull DiscoverPresenter discoverPresenter) {
            Intrinsics.checkParameterIsNotNull(view2, "view");
            Intrinsics.checkParameterIsNotNull(discoverPresenter, "presenter");
            super(view2);
            this.view = view2;
            this.presenter = discoverPresenter;
            View findViewById = this.view.findViewById(C1018R.C1021id.usefulLinkTitleTextView);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.usefulLinkTitleTextView)");
            this.linkName = (TextView) findViewById;
            View findViewById2 = this.view.findViewById(C1018R.C1021id.usefulLinkImageView);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.usefulLinkImageView)");
            this.linkImage = (ImageView) findViewById2;
        }

        @NotNull
        public final DiscoverPresenter getPresenter() {
            return this.presenter;
        }

        @NotNull
        public final View getView() {
            return this.view;
        }

        public final void bind(@NotNull Link link) {
            Intrinsics.checkParameterIsNotNull(link, Params.IAP_ITEM);
            this.linkName.setText(link.getTitle());
            if (!Intrinsics.areEqual((Object) link.getLogo_image_url(), (Object) "")) {
                Picasso.get().load(link.getLogo_image_url()).into(this.linkImage);
            }
            this.view.setOnClickListener(new UsefulLinkSectionAdapter$UsefulLinkItemViewHolder$bind$1(this, link));
        }
    }

    @NotNull
    public final DiscoverPresenter getPresenter() {
        return this.presenter;
    }

    public UsefulLinkSectionAdapter(@NotNull DiscoverPresenter discoverPresenter) {
        Intrinsics.checkParameterIsNotNull(discoverPresenter, "presenter");
        super(SectionParameters.builder().itemResourceId(C1018R.layout.layout_discover_useful_link_item_cell).headerResourceId(C1018R.layout.layout_discover_section_header).build());
        this.presenter = discoverPresenter;
    }

    @NotNull
    public final List<Link> getData() {
        return this.data;
    }

    public final void setData(@NotNull List<Link> list) {
        Intrinsics.checkParameterIsNotNull(list, "<set-?>");
        this.data = list;
    }

    public void setNewData(@NotNull List<Link> list) {
        Intrinsics.checkParameterIsNotNull(list, "links");
        this.data = list;
    }

    public int getContentItemsTotal() {
        return this.data.size();
    }

    @NotNull
    public ViewHolder getItemViewHolder(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        return new UsefulLinkItemViewHolder(view, this.presenter);
    }

    public void onBindItemViewHolder(@Nullable ViewHolder viewHolder, int i) {
        if (viewHolder != null) {
            ((UsefulLinkItemViewHolder) viewHolder).bind((Link) this.data.get(i));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.app.flows.discover.discover.UsefulLinkSectionAdapter.UsefulLinkItemViewHolder");
    }

    @NotNull
    public ViewHolder getHeaderViewHolder(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        return new HeaderViewHolder(view);
    }

    public void onBindHeaderViewHolder(@Nullable ViewHolder viewHolder) {
        if (viewHolder != null) {
            ((HeaderViewHolder) viewHolder).bind("Useful Links");
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.app.flows.discover.discover.UsefulLinkSectionAdapter.HeaderViewHolder");
    }
}
