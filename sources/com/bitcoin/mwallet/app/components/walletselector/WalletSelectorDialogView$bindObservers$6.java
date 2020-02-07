package com.bitcoin.mwallet.app.components.walletselector;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.bitcoin.mwallet.core.entity.WalletType;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, mo37405d2 = {"<anonymous>", "", "buttonView", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WalletSelectorDialogView.kt */
final class WalletSelectorDialogView$bindObservers$6 implements OnCheckedChangeListener {
    final /* synthetic */ WalletSelectorPresenter $presenter;

    WalletSelectorDialogView$bindObservers$6(WalletSelectorPresenter walletSelectorPresenter) {
        this.$presenter = walletSelectorPresenter;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.$presenter.getSelectedCoin().postValue(WalletType.BTC);
        }
    }
}