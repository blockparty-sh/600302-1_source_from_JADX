package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectcurrency;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bitcoin.mwallet.C1018R;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import com.leanplum.internal.Constants.Params;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/SelectCurrencyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "Landroid/view/View;", "listener", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/OnCurrencySelectedListener;", "(Landroid/view/View;Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/OnCurrencySelectedListener;)V", "getListener", "()Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/OnCurrencySelectedListener;", "getView", "()Landroid/view/View;", "bind", "", "item", "Ljava/util/Currency;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectCurrencyViewHolder.kt */
public final class SelectCurrencyViewHolder extends ViewHolder {
    @NotNull
    private final OnCurrencySelectedListener listener;
    @NotNull
    private final View view;

    public SelectCurrencyViewHolder(@NotNull View view2, @NotNull OnCurrencySelectedListener onCurrencySelectedListener) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        Intrinsics.checkParameterIsNotNull(onCurrencySelectedListener, CastExtraArgs.LISTENER);
        super(view2);
        this.view = view2;
        this.listener = onCurrencySelectedListener;
    }

    @NotNull
    public final OnCurrencySelectedListener getListener() {
        return this.listener;
    }

    @NotNull
    public final View getView() {
        return this.view;
    }

    public final void bind(@NotNull Currency currency) {
        Intrinsics.checkParameterIsNotNull(currency, Params.IAP_ITEM);
        View findViewById = this.view.findViewById(C1018R.C1021id.currencyTextView);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById<TextView>(R.id.currencyTextView)");
        ((TextView) findViewById).setText(currency.getDisplayName());
        View findViewById2 = this.view.findViewById(C1018R.C1021id.currencyCodeTextView);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById<TextVi….id.currencyCodeTextView)");
        ((TextView) findViewById2).setText(currency.getCurrencyCode());
        this.view.setOnClickListener(new SelectCurrencyViewHolder$bind$1(this, currency));
    }
}
