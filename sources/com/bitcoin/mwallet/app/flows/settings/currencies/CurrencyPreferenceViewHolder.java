package com.bitcoin.mwallet.app.flows.settings.currencies;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.flows.settings.currencies.dialog.CurrencyConfirmationDialogListener;
import com.bitcoin.mwallet.core.preferences.SharedPreference;
import com.leanplum.internal.Constants.Params;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u001a\u0010\u0016\u001a\u00020\u00172\u0012\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001b0\u0019J\b\u0010\u001c\u001a\u00020\u0017H\u0016J\b\u0010\u001d\u001a\u00020\u0017H\u0016R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u001e"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrencyPreferenceViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/bitcoin/mwallet/app/flows/settings/currencies/dialog/CurrencyConfirmationDialogListener;", "view", "Landroid/view/View;", "fm", "Landroidx/fragment/app/FragmentManager;", "presenter", "Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrenciesPresenter;", "(Landroid/view/View;Landroidx/fragment/app/FragmentManager;Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrenciesPresenter;)V", "getFm", "()Landroidx/fragment/app/FragmentManager;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrenciesPresenter;", "selectedCurrency", "", "getSelectedCurrency", "()Ljava/lang/String;", "setSelectedCurrency", "(Ljava/lang/String;)V", "getView", "()Landroid/view/View;", "bind", "", "item", "Lkotlin/Pair;", "Ljava/util/Currency;", "", "onCancel", "onConfirm", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CurrenciesAdapter.kt */
public final class CurrencyPreferenceViewHolder extends ViewHolder implements CurrencyConfirmationDialogListener {
    @Nullable

    /* renamed from: fm */
    private final FragmentManager f310fm;
    @NotNull
    private final CurrenciesPresenter presenter;
    @Nullable
    private String selectedCurrency;
    @NotNull
    private final View view;

    public void onCancel() {
    }

    @NotNull
    public final View getView() {
        return this.view;
    }

    @Nullable
    public final FragmentManager getFm() {
        return this.f310fm;
    }

    @NotNull
    public final CurrenciesPresenter getPresenter() {
        return this.presenter;
    }

    public CurrencyPreferenceViewHolder(@NotNull View view2, @Nullable FragmentManager fragmentManager, @NotNull CurrenciesPresenter currenciesPresenter) {
        Intrinsics.checkParameterIsNotNull(view2, "view");
        Intrinsics.checkParameterIsNotNull(currenciesPresenter, "presenter");
        super(view2);
        this.view = view2;
        this.f310fm = fragmentManager;
        this.presenter = currenciesPresenter;
    }

    @Nullable
    public final String getSelectedCurrency() {
        return this.selectedCurrency;
    }

    public final void setSelectedCurrency(@Nullable String str) {
        this.selectedCurrency = str;
    }

    public void onConfirm() {
        if (this.selectedCurrency != null) {
            PreferenceManager.getDefaultSharedPreferences(this.view.getContext()).edit().putString(SharedPreference.FIAT_CURRENCY.getKey(), this.selectedCurrency).apply();
            this.presenter.clearAllTransactionThresholds();
        }
    }

    public final void bind(@NotNull Pair<Currency, Boolean> pair) {
        Intrinsics.checkParameterIsNotNull(pair, Params.IAP_ITEM);
        View findViewById = this.view.findViewById(C1018R.C1021id.currencyTextView);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "view.findViewById<TextView>(R.id.currencyTextView)");
        ((TextView) findViewById).setText(((Currency) pair.getFirst()).getDisplayName());
        View findViewById2 = this.view.findViewById(C1018R.C1021id.currencyCodeTextView);
        Intrinsics.checkExpressionValueIsNotNull(findViewById2, "view.findViewById<TextVi….id.currencyCodeTextView)");
        ((TextView) findViewById2).setText(((Currency) pair.getFirst()).getCurrencyCode());
        String str = "view.findViewById<ImageV…urrencySelectedImageView)";
        if (((Boolean) pair.getSecond()).booleanValue()) {
            View findViewById3 = this.view.findViewById(C1018R.C1021id.currencySelectedImageView);
            Intrinsics.checkExpressionValueIsNotNull(findViewById3, str);
            ((ImageView) findViewById3).setVisibility(0);
        } else {
            View findViewById4 = this.view.findViewById(C1018R.C1021id.currencySelectedImageView);
            Intrinsics.checkExpressionValueIsNotNull(findViewById4, str);
            ((ImageView) findViewById4).setVisibility(4);
        }
        this.view.setOnClickListener(new CurrencyPreferenceViewHolder$bind$1(this, pair));
    }
}
