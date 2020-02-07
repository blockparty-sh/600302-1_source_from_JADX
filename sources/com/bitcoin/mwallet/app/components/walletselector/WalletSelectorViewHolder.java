package com.bitcoin.mwallet.app.components.walletselector;

import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.slp.Slp;
import com.bitcoin.mwallet.core.views.walletsummary.WalletSummaryView;
import com.leanplum.internal.Constants.Params;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "walletSelectorListener", "Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;", "(Landroid/view/View;Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;)V", "getView", "()Landroid/view/View;", "getWalletSelectorListener", "()Lcom/bitcoin/mwallet/app/components/walletselector/WalletSelectorListener;", "bind", "", "item", "Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryView;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletSelectorViewHolder.kt */
public final class WalletSelectorViewHolder extends ViewHolder {
    @NotNull
    private final View view;
    @NotNull
    private final WalletSelectorListener walletSelectorListener;

    @NotNull
    public final View getView() {
        return this.view;
    }

    @NotNull
    public final WalletSelectorListener getWalletSelectorListener() {
        return this.walletSelectorListener;
    }

    public WalletSelectorViewHolder(@NotNull View view2, @NotNull WalletSelectorListener walletSelectorListener2) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        Intrinsics.checkParameterIsNotNull(walletSelectorListener2, "walletSelectorListener");
        super(view2);
        this.view = view2;
        this.walletSelectorListener = walletSelectorListener2;
    }

    public final void bind(@NotNull WalletSummaryView walletSummaryView) {
        Intrinsics.checkParameterIsNotNull(walletSummaryView, Params.IAP_ITEM);
        View findViewById = this.view.findViewById(C1018R.C1021id.walletNameTextView);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById<TextVi…(R.id.walletNameTextView)");
        ((TextView) findViewById).setText(walletSummaryView.getName());
        this.view.setOnClickListener(new WalletSelectorViewHolder$bind$1(this, walletSummaryView));
        String str = "view.findViewById<TextVi…id.walletValueCoinAmount)";
        String str2 = "view.findViewById<TextVi…id.walletValueFiatAmount)";
        if (walletSummaryView.getSlpInfo() == null || walletSummaryView.getTokenBalance() == null) {
            View findViewById2 = this.view.findViewById(C1018R.C1021id.walletValueFiatAmount);
            Intrinsics.checkExpressionValueIsNotNull(findViewById2, str2);
            ((TextView) findViewById2).setText(walletSummaryView.getBalancePrimary());
            View findViewById3 = this.view.findViewById(C1018R.C1021id.walletValueCoinAmount);
            Intrinsics.checkExpressionValueIsNotNull(findViewById3, str);
            ((TextView) findViewById3).setText(walletSummaryView.getBalanceSecondary());
        } else {
            View findViewById4 = this.view.findViewById(C1018R.C1021id.walletValueFiatAmount);
            Intrinsics.checkExpressionValueIsNotNull(findViewById4, str2);
            ((TextView) findViewById4).setText(walletSummaryView.getBalanceSecondary());
            View findViewById5 = this.view.findViewById(C1018R.C1021id.walletValueCoinAmount);
            Intrinsics.checkExpressionValueIsNotNull(findViewById5, str);
            TextView textView = (TextView) findViewById5;
            StringBuilder sb = new StringBuilder();
            BigDecimal tokenBalance = walletSummaryView.getTokenBalance();
            if (tokenBalance == null) {
                Intrinsics.throwNpe();
            }
            String plainString = tokenBalance.toPlainString();
            if (plainString == null) {
                Intrinsics.throwNpe();
            }
            sb.append(plainString);
            sb.append(' ');
            Slp slpInfo = walletSummaryView.getSlpInfo();
            if (slpInfo == null) {
                Intrinsics.throwNpe();
            }
            sb.append(slpInfo.getTicker());
            textView.setText(sb.toString());
        }
        ((ImageView) this.view.findViewById(C1018R.C1021id.walletColorImageView)).setColorFilter(Color.parseColor(walletSummaryView.getColor()), Mode.MULTIPLY);
    }
}
