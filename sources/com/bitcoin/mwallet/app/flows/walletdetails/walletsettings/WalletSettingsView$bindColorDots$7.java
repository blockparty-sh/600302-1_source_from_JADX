package com.bitcoin.mwallet.app.flows.walletdetails.walletsettings;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WalletSettingsView.kt */
final class WalletSettingsView$bindColorDots$7 implements OnClickListener {
    final /* synthetic */ ImageView $pinkDot;
    final /* synthetic */ WalletSettingsView this$0;

    WalletSettingsView$bindColorDots$7(WalletSettingsView walletSettingsView, ImageView imageView) {
        this.this$0 = walletSettingsView;
        this.$pinkDot = imageView;
    }

    public final void onClick(View view) {
        WalletSettingsView walletSettingsView = this.this$0;
        walletSettingsView.dotSelected(this.$pinkDot, ContextCompat.getColor(walletSettingsView.requireContext(), C1018R.C1019color.walletColorPink));
    }
}
