package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view;

import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view.MnemonicTabView.WhenMappings;
import com.bitcoin.mwallet.core.interactors.CreateWalletInteractor.CreateWalletResult;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "result", "Lcom/bitcoin/mwallet/core/interactors/CreateWalletInteractor$CreateWalletResult;", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: MnemonicTabView.kt */
final class MnemonicTabView$onCreateView$1<T> implements Observer<CreateWalletResult> {
    final /* synthetic */ MnemonicTabView this$0;

    MnemonicTabView$onCreateView$1(MnemonicTabView mnemonicTabView) {
        this.this$0 = mnemonicTabView;
    }

    public final void onChanged(@Nullable CreateWalletResult createWalletResult) {
        if (createWalletResult != null && WhenMappings.$EnumSwitchMapping$0[createWalletResult.ordinal()] != 1) {
            this.this$0.hideLoading();
        }
    }
}
