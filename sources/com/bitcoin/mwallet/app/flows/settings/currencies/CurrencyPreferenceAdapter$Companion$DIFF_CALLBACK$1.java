package com.bitcoin.mwallet.app.flows.settings.currencies;

import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001J0\u0010\u0005\u001a\u00020\u00042\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0016J0\u0010\b\u001a\u00020\u00042\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00022\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002H\u0016¨\u0006\t"}, mo37405d2 = {"com/bitcoin/mwallet/app/flows/settings/currencies/CurrencyPreferenceAdapter$Companion$DIFF_CALLBACK$1", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lkotlin/Pair;", "Ljava/util/Currency;", "", "areContentsTheSame", "oldItem", "newItem", "areItemsTheSame", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CurrenciesAdapter.kt */
public final class CurrencyPreferenceAdapter$Companion$DIFF_CALLBACK$1 extends ItemCallback<Pair<? extends Currency, ? extends Boolean>> {
    CurrencyPreferenceAdapter$Companion$DIFF_CALLBACK$1() {
    }

    public boolean areItemsTheSame(@NotNull Pair<Currency, Boolean> pair, @NotNull Pair<Currency, Boolean> pair2) {
        Intrinsics.checkParameterIsNotNull(pair, "oldItem");
        Intrinsics.checkParameterIsNotNull(pair2, "newItem");
        return Intrinsics.areEqual((Object) ((Currency) pair.getFirst()).getCurrencyCode(), (Object) ((Currency) pair2.getFirst()).getCurrencyCode()) && ((Boolean) pair.getSecond()).booleanValue() == ((Boolean) pair2.getSecond()).booleanValue();
    }

    public boolean areContentsTheSame(@NotNull Pair<Currency, Boolean> pair, @NotNull Pair<Currency, Boolean> pair2) {
        Intrinsics.checkParameterIsNotNull(pair, "oldItem");
        Intrinsics.checkParameterIsNotNull(pair2, "newItem");
        return Intrinsics.areEqual((Object) ((Currency) pair.getFirst()).getCurrencyCode(), (Object) ((Currency) pair2.getFirst()).getCurrencyCode()) && ((Boolean) pair.getSecond()).booleanValue() == ((Boolean) pair2.getSecond()).booleanValue();
    }
}
