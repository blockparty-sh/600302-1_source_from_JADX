package com.bitcoin.mwallet.app.flows.walletdetails.deletewallet;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "wallet", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: DeleteWalletView.kt */
final class DeleteWalletView$bindObservers$3<T> implements Observer<C1261Wallet> {
    final /* synthetic */ DeleteWalletView this$0;

    DeleteWalletView$bindObservers$3(DeleteWalletView deleteWalletView) {
        this.this$0 = deleteWalletView;
    }

    public final void onChanged(C1261Wallet wallet) {
        if (wallet != null) {
            View view = this.this$0.getView();
            if (view != null) {
                TextView textView = (TextView) view.findViewById(C1018R.C1021id.deleteWalletName);
                if (textView != null) {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = this.this$0.getResources().getString(C1018R.string.settings_delete_x_wallet);
                    Intrinsics.checkExpressionValueIsNotNull(string, "resources.getString(R.st…settings_delete_x_wallet)");
                    Object[] objArr = {wallet.getName()};
                    String format = String.format(string, Arrays.copyOf(objArr, objArr.length));
                    Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                    textView.setText(format);
                }
            }
        }
    }
}
