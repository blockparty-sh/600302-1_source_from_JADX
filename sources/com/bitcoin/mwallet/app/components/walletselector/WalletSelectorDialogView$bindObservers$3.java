package com.bitcoin.mwallet.app.components.walletselector;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, mo37405d2 = {"<anonymous>", "", "showEmptyLayout", "", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Boolean;)V"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WalletSelectorDialogView.kt */
final class WalletSelectorDialogView$bindObservers$3<T> implements Observer<Boolean> {
    final /* synthetic */ View $layoutView;
    final /* synthetic */ WalletSelectorPresenter $presenter;
    final /* synthetic */ WalletSelectorDialogView this$0;

    WalletSelectorDialogView$bindObservers$3(WalletSelectorDialogView walletSelectorDialogView, View view, WalletSelectorPresenter walletSelectorPresenter) {
        this.this$0 = walletSelectorDialogView;
        this.$layoutView = view;
        this.$presenter = walletSelectorPresenter;
    }

    public final void onChanged(Boolean bool) {
        Intrinsics.checkExpressionValueIsNotNull(bool, "showEmptyLayout");
        String str = "layoutView.findViewById<…(R.id.walletSelectLayout)";
        String str2 = "layoutView.findViewById<…>(R.id.emptyWalletLayout)";
        if (bool.booleanValue()) {
            View findViewById = this.$layoutView.findViewById(C1018R.C1021id.emptyWalletLayout);
            Intrinsics.checkExpressionValueIsNotNull(findViewById, str2);
            ((LinearLayout) findViewById).setVisibility(0);
            View findViewById2 = this.$layoutView.findViewById(C1018R.C1021id.walletSelectLayout);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, str);
            ((LinearLayout) findViewById2).setVisibility(8);
            TextView textView = (TextView) this.this$0._$_findCachedViewById(C1018R.C1021id.noWalletDescription);
            Intrinsics.checkExpressionValueIsNotNull(textView, "noWalletDescription");
            textView.setText(this.this$0.getString(C1018R.string.wallet_selector_empty_description, this.$presenter.getSelectedCoin().getValue()));
            TextView textView2 = (TextView) this.this$0._$_findCachedViewById(C1018R.C1021id.noWalletTitle);
            Intrinsics.checkExpressionValueIsNotNull(textView2, "noWalletTitle");
            textView2.setText(this.this$0.getString(C1018R.string.wallet_selector_empty_title, this.$presenter.getSelectedCoin().getValue()));
            TextView textView3 = (TextView) this.this$0._$_findCachedViewById(C1018R.C1021id.addWalletButton);
            Intrinsics.checkExpressionValueIsNotNull(textView3, "addWalletButton");
            textView3.setText(this.this$0.getString(C1018R.string.wallet_selector_empty_add_button, this.$presenter.getSelectedCoin().getValue()));
            return;
        }
        View findViewById3 = this.$layoutView.findViewById(C1018R.C1021id.emptyWalletLayout);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, str2);
        ((LinearLayout) findViewById3).setVisibility(8);
        View findViewById4 = this.$layoutView.findViewById(C1018R.C1021id.walletSelectLayout);
        Intrinsics.checkExpressionValueIsNotNull(findViewById4, str);
        ((LinearLayout) findViewById4).setVisibility(0);
    }
}
