package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view;

import android.graphics.Typeface;
import android.widget.RadioButton;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.core.models.Coin;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "c", "Lcom/bitcoin/mwallet/core/models/Coin;", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: MnemonicTabView.kt */
final class MnemonicTabView$onCreateView$2<T> implements Observer<Coin> {
    final /* synthetic */ RadioButton $bchSelectionButton;
    final /* synthetic */ RadioButton $btcSelectionButton;

    MnemonicTabView$onCreateView$2(RadioButton radioButton, RadioButton radioButton2) {
        this.$bchSelectionButton = radioButton;
        this.$btcSelectionButton = radioButton2;
    }

    public final void onChanged(@NotNull Coin coin) {
        Intrinsics.checkParameterIsNotNull(coin, "c");
        RadioButton radioButton = this.$bchSelectionButton;
        if (radioButton != null) {
            radioButton.setTypeface(coin == Coin.BCH ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        }
        RadioButton radioButton2 = this.$bchSelectionButton;
        boolean z = true;
        if (radioButton2 != null) {
            radioButton2.setChecked(coin == Coin.BCH);
        }
        RadioButton radioButton3 = this.$btcSelectionButton;
        if (radioButton3 != null) {
            radioButton3.setTypeface(coin == Coin.BTC ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        }
        RadioButton radioButton4 = this.$btcSelectionButton;
        if (radioButton4 != null) {
            if (coin != Coin.BTC) {
                z = false;
            }
            radioButton4.setChecked(z);
        }
    }
}
