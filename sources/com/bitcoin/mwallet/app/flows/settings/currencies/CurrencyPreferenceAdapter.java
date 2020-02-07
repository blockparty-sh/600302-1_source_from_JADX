package com.bitcoin.mwallet.app.flows.settings.currencies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DiffUtil.ItemCallback;
import androidx.recyclerview.widget.ListAdapter;
import com.bitcoin.mwallet.C1018R;
import com.leanplum.internal.Constants.Params;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00182\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002\u0012\u0004\u0012\u00020\u00050\u0001:\u0001\u0018B\u0017\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0013H\u0016R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrencyPreferenceAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lkotlin/Pair;", "Ljava/util/Currency;", "", "Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrencyPreferenceViewHolder;", "fm", "Landroidx/fragment/app/FragmentManager;", "presenter", "Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrenciesPresenter;", "(Landroidx/fragment/app/FragmentManager;Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrenciesPresenter;)V", "getFm", "()Landroidx/fragment/app/FragmentManager;", "getPresenter", "()Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrenciesPresenter;", "onBindViewHolder", "", "holder", "position", "", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CurrenciesAdapter.kt */
public final class CurrencyPreferenceAdapter extends ListAdapter<Pair<? extends Currency, ? extends Boolean>, CurrencyPreferenceViewHolder> {
    public static final Companion Companion = new Companion(null);
    /* access modifiers changed from: private */
    @NotNull
    public static final ItemCallback<Pair<Currency, Boolean>> DIFF_CALLBACK = new CurrencyPreferenceAdapter$Companion$DIFF_CALLBACK$1();
    @Nullable

    /* renamed from: fm */
    private final FragmentManager f309fm;
    @NotNull
    private final CurrenciesPresenter presenter;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R#\u0010\u0003\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/settings/currencies/CurrencyPreferenceAdapter$Companion;", "", "()V", "DIFF_CALLBACK", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lkotlin/Pair;", "Ljava/util/Currency;", "", "getDIFF_CALLBACK", "()Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: CurrenciesAdapter.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final ItemCallback<Pair<Currency, Boolean>> getDIFF_CALLBACK() {
            return CurrencyPreferenceAdapter.DIFF_CALLBACK;
        }
    }

    @Nullable
    public final FragmentManager getFm() {
        return this.f309fm;
    }

    @NotNull
    public final CurrenciesPresenter getPresenter() {
        return this.presenter;
    }

    public CurrencyPreferenceAdapter(@Nullable FragmentManager fragmentManager, @NotNull CurrenciesPresenter currenciesPresenter) {
        Intrinsics.checkParameterIsNotNull(currenciesPresenter, "presenter");
        super(DIFF_CALLBACK);
        this.f309fm = fragmentManager;
        this.presenter = currenciesPresenter;
    }

    public void onBindViewHolder(@NotNull CurrencyPreferenceViewHolder currencyPreferenceViewHolder, int i) {
        Intrinsics.checkParameterIsNotNull(currencyPreferenceViewHolder, "holder");
        Pair pair = (Pair) getItem(i);
        Intrinsics.checkExpressionValueIsNotNull(pair, Params.IAP_ITEM);
        currencyPreferenceViewHolder.bind(pair);
    }

    @NotNull
    public CurrencyPreferenceViewHolder onCreateViewHolder(@NotNull ViewGroup viewGroup, int i) {
        Intrinsics.checkParameterIsNotNull(viewGroup, "parent");
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(C1018R.layout.layout_select_currency_cell, viewGroup, false);
        Intrinsics.checkExpressionValueIsNotNull(inflate, "view");
        return new CurrencyPreferenceViewHolder(inflate, this.f309fm, this.presenter);
    }
}
