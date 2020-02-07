package com.bitcoin.mwallet.app.flows.walletdetails.walletsettings;

import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.view.View;
import android.widget.ImageView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "hexColor", "", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WalletSettingsView.kt */
final class WalletSettingsView$bindObservers$2<T> implements Observer<String> {
    final /* synthetic */ WalletSettingsView this$0;

    WalletSettingsView$bindObservers$2(WalletSettingsView walletSettingsView) {
        this.this$0 = walletSettingsView;
    }

    public final void onChanged(String str) {
        View view = this.this$0.getView();
        if (view != null) {
            ImageView imageView = (ImageView) view.findViewById(C1018R.C1021id.walletHeaderColorDot);
            if (imageView != null) {
                imageView.setColorFilter(Color.parseColor(str), Mode.MULTIPLY);
            }
        }
    }
}
