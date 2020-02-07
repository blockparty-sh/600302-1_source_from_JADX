package com.bitcoin.mwallet.app.flows.settings.currencies;

import android.view.View;
import android.view.View.OnClickListener;
import androidx.preference.PreferenceManager;
import com.bitcoin.mwallet.app.flows.settings.currencies.dialog.CurrencyConfirmationDialog;
import com.bitcoin.mwallet.core.preferences.SharedPreference;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: CurrenciesAdapter.kt */
final class CurrencyPreferenceViewHolder$bind$1 implements OnClickListener {
    final /* synthetic */ Pair $item;
    final /* synthetic */ CurrencyPreferenceViewHolder this$0;

    CurrencyPreferenceViewHolder$bind$1(CurrencyPreferenceViewHolder currencyPreferenceViewHolder, Pair pair) {
        this.this$0 = currencyPreferenceViewHolder;
        this.$item = pair;
    }

    public final void onClick(View view) {
        this.this$0.setSelectedCurrency(((Currency) this.$item.getFirst()).getCurrencyCode());
        String string = PreferenceManager.getDefaultSharedPreferences(this.this$0.getView().getContext()).getString(SharedPreference.FIAT_CURRENCY.getKey(), null);
        if (this.this$0.getFm() != null && !Intrinsics.areEqual((Object) ((Currency) this.$item.getFirst()).getCurrencyCode(), (Object) string)) {
            new CurrencyConfirmationDialog(this.this$0).show(this.this$0.getFm(), "Unsupported_dialog");
        }
    }
}
