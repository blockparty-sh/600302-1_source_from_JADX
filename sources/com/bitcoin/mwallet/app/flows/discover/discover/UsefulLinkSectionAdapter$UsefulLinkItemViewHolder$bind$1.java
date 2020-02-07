package com.bitcoin.mwallet.app.flows.discover.discover;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import com.bitcoin.mwallet.app.flows.discover.discover.UsefulLinkSectionAdapter.UsefulLinkItemViewHolder;
import com.bitcoin.mwallet.core.models.discover.Link;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: UsefulLinkSectionAdapter.kt */
final class UsefulLinkSectionAdapter$UsefulLinkItemViewHolder$bind$1 implements OnClickListener {
    final /* synthetic */ Link $item;
    final /* synthetic */ UsefulLinkItemViewHolder this$0;

    UsefulLinkSectionAdapter$UsefulLinkItemViewHolder$bind$1(UsefulLinkItemViewHolder usefulLinkItemViewHolder, Link link) {
        this.this$0 = usefulLinkItemViewHolder;
        this.$item = link;
    }

    public final void onClick(View view) {
        String str = "shop_screen_tap_on_standard_business";
        this.this$0.getPresenter().getAnalyticsService().track(str, MapsKt.mapOf(TuplesKt.m309to(CommonProperties.f657ID, this.$item.getUrl())));
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(this.$item.getUrl()));
        this.this$0.getView().getContext().startActivity(intent);
    }
}
