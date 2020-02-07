package com.bitcoin.mwallet.app.components.warningdialog;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WarningView.kt */
final class WarningView$onCreateView$2 implements OnClickListener {
    final /* synthetic */ WarningView this$0;

    WarningView$onCreateView$2(WarningView warningView) {
        this.this$0 = warningView;
    }

    public final void onClick(View view) {
        this.this$0.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.this$0.getActionUrl())));
        this.this$0.dismiss();
    }
}
