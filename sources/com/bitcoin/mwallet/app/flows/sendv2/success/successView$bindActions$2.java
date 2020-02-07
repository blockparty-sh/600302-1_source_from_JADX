package com.bitcoin.mwallet.app.flows.sendv2.success;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: successView.kt */
final class successView$bindActions$2 implements OnClickListener {
    final /* synthetic */ successView this$0;

    successView$bindActions$2(successView successview) {
        this.this$0 = successview;
    }

    public final void onClick(View view) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://explorer.bitcoin.com/");
        String ticker = ((successPresenter) this.this$0.getPresenter()).getSendWhatData().getCoin().getTicker();
        if (ticker != null) {
            String lowerCase = ticker.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
            sb.append(lowerCase);
            sb.append("/tx/");
            sb.append(((successPresenter) this.this$0.getPresenter()).getTxId().getId());
            this.this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(sb.toString())));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
