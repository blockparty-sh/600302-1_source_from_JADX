package com.bitcoin.mwallet.app.components.walletselector;

import android.view.View;
import android.view.View.OnClickListener;
import androidx.fragment.app.FragmentManager;
import com.bitcoin.mwallet.core.entity.WalletType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WalletSelectorDialogView.kt */
final class WalletSelectorDialogView$bindObservers$4 implements OnClickListener {
    final /* synthetic */ WalletSelectorPresenter $presenter;
    final /* synthetic */ WalletSelectorDialogView this$0;

    WalletSelectorDialogView$bindObservers$4(WalletSelectorDialogView walletSelectorDialogView, WalletSelectorPresenter walletSelectorPresenter) {
        this.this$0 = walletSelectorDialogView;
        this.$presenter = walletSelectorPresenter;
    }

    public final void onClick(View view) {
        WalletSelectorPresenter walletSelectorPresenter = this.$presenter;
        Object value = walletSelectorPresenter.getSelectedCoin().getValue();
        if (value == null) {
            Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(value, "presenter.selectedCoin.value!!");
        WalletType walletType = (WalletType) value;
        FragmentManager childFragmentManager = this.this$0.getChildFragmentManager();
        Intrinsics.checkExpressionValueIsNotNull(childFragmentManager, "childFragmentManager");
        walletSelectorPresenter.toCreateWallet(walletType, childFragmentManager);
    }
}
