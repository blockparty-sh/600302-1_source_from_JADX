package com.bitcoin.mwallet.app.components.amountselection;

import android.app.Activity;
import androidx.fragment.app.FragmentManager;
import com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectcurrency.OnCurrencySelectedListener;
import com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectcurrency.SelectCurrencyView;
import com.bitcoin.mwallet.app.viper.RouterBase;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.CastExtraArgs;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f¨\u0006\r"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/components/amountselection/AmountSelectionRouterBase;", "Lcom/bitcoin/mwallet/app/viper/RouterBase;", "()V", "onBackPressed", "", "onClosePressed", "activity", "Landroid/app/Activity;", "toCurrencySelect", "fm", "Landroidx/fragment/app/FragmentManager;", "listener", "Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectcurrency/OnCurrencySelectedListener;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AmountSelectionRouterBase.kt */
public class AmountSelectionRouterBase extends RouterBase {
    public final void toCurrencySelect(@NotNull FragmentManager fragmentManager, @NotNull OnCurrencySelectedListener onCurrencySelectedListener) {
        Intrinsics.checkParameterIsNotNull(fragmentManager, "fm");
        Intrinsics.checkParameterIsNotNull(onCurrencySelectedListener, CastExtraArgs.LISTENER);
        new SelectCurrencyView(onCurrencySelectedListener).show(fragmentManager, "currency_select");
    }

    public final void onBackPressed() {
        getNavController().popBackStack();
    }

    public final void onClosePressed(@NotNull Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        activity.finish();
    }
}
