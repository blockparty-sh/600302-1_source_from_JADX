package com.bitcoin.mwallet.core.views.transaction;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.transactionhistory.TransactionHistoryPresenter;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import com.bitcoin.mwallet.core.utils.signature.SigningKey;
import com.leanplum.internal.Constants.Params;
import java.text.SimpleDateFormat;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;
import walletutils.Walletutils;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0017"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/views/transaction/TransactionSummaryViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "presenter", "Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/transactionhistory/TransactionHistoryPresenter;", "(Landroid/view/View;Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/transactionhistory/TransactionHistoryPresenter;)V", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/transactionhistory/TransactionHistoryPresenter;", "txDateTextView", "Landroid/widget/TextView;", "txDescription", "txIcon", "Landroid/widget/ImageView;", "txMemo", "txValuePrimary", "txValueSecondary", "getView", "()Landroid/view/View;", "bind", "", "item", "Lcom/bitcoin/mwallet/core/views/transaction/TransactionSummaryView;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: TransactionSummaryListAdapter.kt */
public final class TransactionSummaryViewHolder extends ViewHolder {
    @NotNull
    private final TransactionHistoryPresenter presenter;
    private final TextView txDateTextView;
    private final TextView txDescription;
    private final ImageView txIcon;
    private final TextView txMemo;
    private final TextView txValuePrimary;
    private final TextView txValueSecondary;
    @NotNull
    private final View view;

    @NotNull
    public final TransactionHistoryPresenter getPresenter() {
        return this.presenter;
    }

    @NotNull
    public final View getView() {
        return this.view;
    }

    public TransactionSummaryViewHolder(@NotNull View view2, @NotNull TransactionHistoryPresenter transactionHistoryPresenter) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        Intrinsics.checkParameterIsNotNull(transactionHistoryPresenter, "presenter");
        super(view2);
        this.view = view2;
        this.presenter = transactionHistoryPresenter;
        View findViewById = this.view.findViewById(C1018R.C1021id.txIcon);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById(R.id.txIcon)");
        this.txIcon = (ImageView) findViewById;
        View findViewById2 = this.view.findViewById(C1018R.C1021id.txDescription);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById(R.id.txDescription)");
        this.txDescription = (TextView) findViewById2;
        View findViewById3 = this.view.findViewById(C1018R.C1021id.txValuePrimary);
        Intrinsics.checkExpressionValueIsNotNull(findViewById3, "view.findViewById(R.id.txValuePrimary)");
        this.txValuePrimary = (TextView) findViewById3;
        View findViewById4 = this.view.findViewById(C1018R.C1021id.txValueSecondary);
        Intrinsics.checkExpressionValueIsNotNull(findViewById4, "view.findViewById(R.id.txValueSecondary)");
        this.txValueSecondary = (TextView) findViewById4;
        View findViewById5 = this.view.findViewById(C1018R.C1021id.txDateTextView);
        Intrinsics.checkExpressionValueIsNotNull(findViewById5, "view.findViewById(R.id.txDateTextView)");
        this.txDateTextView = (TextView) findViewById5;
        View findViewById6 = this.view.findViewById(C1018R.C1021id.transactionMemo);
        Intrinsics.checkExpressionValueIsNotNull(findViewById6, "view.findViewById(R.id.transactionMemo)");
        this.txMemo = (TextView) findViewById6;
    }

    public final void bind(@NotNull TransactionSummaryView transactionSummaryView) {
        String str;
        Intrinsics.checkParameterIsNotNull(transactionSummaryView, Params.IAP_ITEM);
        Context context = this.view.getContext();
        Intrinsics.checkExpressionValueIsNotNull(context, "view.context");
        ColorStateList colorStateList = context.getResources().getColorStateList(transactionSummaryView.getColorId());
        Intrinsics.checkExpressionValueIsNotNull(colorStateList, "view.context.resources.g…orStateList(item.colorId)");
        this.txIcon.setImageResource(transactionSummaryView.getIconResourceId());
        this.txIcon.setContentDescription(transactionSummaryView.getIconContentDescription());
        TextView textView = this.txDescription;
        String name = transactionSummaryView.getAction().name();
        if (name != null) {
            String lowerCase = name.toLowerCase();
            Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
            textView.setText(StringsKt.capitalize(lowerCase));
            this.txValuePrimary.setText(transactionSummaryView.getPrimaryValue());
            this.txValuePrimary.setTextColor(colorStateList);
            this.txValueSecondary.setText(transactionSummaryView.getSecondaryValue());
            this.txDateTextView.setText(new SimpleDateFormat("yyyy/MM/dd").format(transactionSummaryView.getTime()));
            if (this.presenter.getWallet() != null) {
                String memo = transactionSummaryView.getMemo();
                try {
                    CharSequence memo2 = transactionSummaryView.getMemo();
                    if (!(memo2 == null || memo2.length() == 0)) {
                        try {
                            C1261Wallet wallet = this.presenter.getWallet();
                            if (wallet != null) {
                                SigningKey signingKey = wallet.getSigningKey();
                                if (signingKey != null) {
                                    str = signingKey.getPrivateKey();
                                    memo = Walletutils.decrypt(str, transactionSummaryView.getMemo());
                                }
                            }
                            str = null;
                            memo = Walletutils.decrypt(str, transactionSummaryView.getMemo());
                        } catch (Exception unused) {
                            Timber.m426d("Decrypt Failed", new Object[0]);
                            memo = transactionSummaryView.getMemo();
                        }
                    }
                } catch (Exception e) {
                    Timber.m427d(e);
                }
                if (Intrinsics.areEqual((Object) memo, (Object) "")) {
                    this.txMemo.setVisibility(8);
                }
                this.txMemo.setText(memo);
                return;
            }
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
}
