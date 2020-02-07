package com.bitcoin.mwallet.app.flows.sendv2.selectaddress;

import android.content.ClipData;
import android.content.ClipData.Item;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SelectAddressView.kt */
final class SelectAddressView$bindButtons$4 implements OnClickListener {
    final /* synthetic */ SelectAddressView this$0;

    SelectAddressView$bindButtons$4(SelectAddressView selectAddressView) {
        this.this$0 = selectAddressView;
    }

    public final void onClick(View view) {
        Context context = this.this$0.getContext();
        CharSequence charSequence = null;
        Object systemService = context != null ? context.getSystemService("clipboard") : null;
        if (!(systemService instanceof ClipboardManager)) {
            systemService = null;
        }
        ClipboardManager clipboardManager = (ClipboardManager) systemService;
        SelectAddressPresenter selectAddressPresenter = (SelectAddressPresenter) this.this$0.getPresenter();
        if (clipboardManager != null) {
            ClipData primaryClip = clipboardManager.getPrimaryClip();
            if (primaryClip != null) {
                Item itemAt = primaryClip.getItemAt(0);
                if (itemAt != null) {
                    charSequence = itemAt.getText();
                }
            }
        }
        selectAddressPresenter.pasteTapped(String.valueOf(charSequence));
    }
}
