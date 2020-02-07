package com.bitcoin.mwallet.app.components.walletmanagement.importwallet.view;

import android.view.View;
import android.view.View.OnClickListener;
import com.bitcoin.mwallet.app.components.walletmanagement.importwallet.presenter.ImportWalletPresenter;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import kotlin.Metadata;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: MnemonicTabView.kt */
final class MnemonicTabView$addChipToGroup$1 implements OnClickListener {
    final /* synthetic */ Chip $chip;
    final /* synthetic */ ChipGroup $chipGroup;
    final /* synthetic */ String $name;
    final /* synthetic */ MnemonicTabView this$0;

    MnemonicTabView$addChipToGroup$1(MnemonicTabView mnemonicTabView, ChipGroup chipGroup, Chip chip, String str) {
        this.this$0 = mnemonicTabView;
        this.$chipGroup = chipGroup;
        this.$chip = chip;
        this.$name = str;
    }

    public final void onClick(View view) {
        this.$chipGroup.removeView(this.$chip);
        ImportWalletPresenter presenter = this.this$0.getPresenter();
        if (presenter != null) {
            presenter.onDeleteMnemonic(this.$name);
        }
    }
}
