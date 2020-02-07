package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectcurrency;

import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\b"}, mo37405d2 = {"com/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/SelectCurrencyAdapter$Companion$DIFF_CALLBACK$1", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Ljava/util/Currency;", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectCurrencyAdapter.kt */
public final class SelectCurrencyAdapter$Companion$DIFF_CALLBACK$1 extends ItemCallback<Currency> {
    SelectCurrencyAdapter$Companion$DIFF_CALLBACK$1() {
    }

    public boolean areItemsTheSame(@NotNull Currency currency, @NotNull Currency currency2) {
        Intrinsics.checkParameterIsNotNull(currency, "oldItem");
        Intrinsics.checkParameterIsNotNull(currency2, "newItem");
        return Intrinsics.areEqual((Object) currency.getCurrencyCode(), (Object) currency2.getCurrencyCode());
    }

    public boolean areContentsTheSame(@NotNull Currency currency, @NotNull Currency currency2) {
        Intrinsics.checkParameterIsNotNull(currency, "oldItem");
        Intrinsics.checkParameterIsNotNull(currency2, "newItem");
        return Intrinsics.areEqual((Object) currency.getCurrencyCode(), (Object) currency2.getCurrencyCode());
    }
}
