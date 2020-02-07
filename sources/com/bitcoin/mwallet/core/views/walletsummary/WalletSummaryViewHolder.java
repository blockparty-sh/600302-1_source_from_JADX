package com.bitcoin.mwallet.core.views.walletsummary;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.spongycastle.i18n.ErrorBundle;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "card", "Landroidx/constraintlayout/widget/ConstraintLayout;", "coinIcon", "Landroid/widget/ImageView;", "subtitle", "Landroid/widget/TextView;", "getView", "()Landroid/view/View;", "walletBalancePrimary", "walletBalanceSecondary", "walletName", "bind", "", "summary", "Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryView;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletSummaryViewHolder.kt */
public final class WalletSummaryViewHolder extends ViewHolder {
    private final ConstraintLayout card;
    private final ImageView coinIcon;
    private final TextView subtitle;
    @NotNull
    private final View view;
    private final TextView walletBalancePrimary;
    private final TextView walletBalanceSecondary;
    private final TextView walletName;

    public WalletSummaryViewHolder(@NotNull View view2) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        super(view2);
        this.view = view2;
        View findViewById = this.view.findViewById(C1018R.C1021id.coinIcon);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.coinIcon)");
        this.coinIcon = (ImageView) findViewById;
        View findViewById2 = this.view.findViewById(C1018R.C1021id.coinName);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.coinName)");
        this.subtitle = (TextView) findViewById2;
        View findViewById3 = this.view.findViewById(C1018R.C1021id.walletName);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "view.findViewById(R.id.walletName)");
        this.walletName = (TextView) findViewById3;
        View findViewById4 = this.view.findViewById(C1018R.C1021id.walletBalancePrimary);
        Intrinsics.checkExpressionValueIsNotNull(findViewById4, "view.findViewById(R.id.walletBalancePrimary)");
        this.walletBalancePrimary = (TextView) findViewById4;
        View findViewById5 = this.view.findViewById(C1018R.C1021id.walletBalanceSecondary);
        Intrinsics.checkExpressionValueIsNotNull(findViewById5, "view.findViewById(R.id.walletBalanceSecondary)");
        this.walletBalanceSecondary = (TextView) findViewById5;
        View findViewById6 = this.view.findViewById(C1018R.C1021id.walletCard);
        Intrinsics.checkExpressionValueIsNotNull(findViewById6, "view.findViewById(R.id.walletCard)");
        this.card = (ConstraintLayout) findViewById6;
    }

    @NotNull
    public final View getView() {
        return this.view;
    }

    public final void bind(@NotNull WalletSummaryView walletSummaryView) {
        Intrinsics.checkParameterIsNotNull(walletSummaryView, ErrorBundle.SUMMARY_ENTRY);
        this.coinIcon.setImageResource(walletSummaryView.getCoin().getIconResId());
        this.coinIcon.setContentDescription(this.view.getResources().getString(walletSummaryView.getCoin().getIconContentDescriptionResId()));
        TextView textView = this.subtitle;
        Context context = this.view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "view.context");
        textView.setText(walletSummaryView.getSubtitle(context));
        this.walletName.setText(walletSummaryView.getName());
        this.walletBalancePrimary.setText(walletSummaryView.getBalancePrimary());
        this.walletBalanceSecondary.setText(walletSummaryView.getBalanceSecondary());
    }
}
