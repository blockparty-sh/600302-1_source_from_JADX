package com.bitcoin.mwallet.app.flows.receive.receive;

import android.view.View;
import android.widget.RadioButton;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.credential.CredentialType;
import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, mo37405d2 = {"<anonymous>", "", "wallets", "", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveView.kt */
final class ReceiveView$bindDataObservers$2<T> implements Observer<List<? extends C1261Wallet>> {
    final /* synthetic */ ReceiveView this$0;

    ReceiveView$bindDataObservers$2(ReceiveView receiveView) {
        this.this$0 = receiveView;
    }

    public final void onChanged(List<C1261Wallet> list) {
        Intrinsics.checkExpressionValueIsNotNull(list, "wallets");
        Iterable iterable = list;
        Collection arrayList = new ArrayList();
        Iterator it = iterable.iterator();
        while (true) {
            boolean z = false;
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            C1261Wallet wallet = (C1261Wallet) next;
            if (!(wallet.isMultiSig() || wallet.getCredentialId().getType() == CredentialType.ENCRYPTED_MNEMONIC || wallet.getCredentialId().getType() == CredentialType.MNEMONIC_AND_PROTECTED)) {
                z = true;
            }
            if (z) {
                arrayList.add(next);
            }
        }
        Iterable iterable2 = (List) arrayList;
        Collection arrayList2 = new ArrayList();
        for (Object next2 : iterable2) {
            if (((C1261Wallet) next2).getCoin() == Coin.BCH) {
                arrayList2.add(next2);
            }
        }
        List list2 = (List) arrayList2;
        Collection arrayList3 = new ArrayList();
        for (Object next3 : iterable2) {
            if (((C1261Wallet) next3).getCoin() == Coin.BTC) {
                arrayList3.add(next3);
            }
        }
        List list3 = (List) arrayList3;
        View view = this.this$0.getView();
        RadioButton radioButton = null;
        RadioButton radioButton2 = view != null ? (RadioButton) view.findViewById(C1018R.C1021id.bitcoinCashFilterButton) : null;
        View view2 = this.this$0.getView();
        RadioButton radioButton3 = view2 != null ? (RadioButton) view2.findViewById(C1018R.C1021id.slpFilterButton) : null;
        View view3 = this.this$0.getView();
        if (view3 != null) {
            radioButton = (RadioButton) view3.findViewById(C1018R.C1021id.bitcoinFilterButton);
        }
        if (radioButton2 != null) {
            radioButton2.setClickable(!list2.isEmpty());
        }
        if (radioButton3 != null) {
            radioButton3.setClickable(!list2.isEmpty());
        }
        if (radioButton != null) {
            radioButton.setClickable(!list3.isEmpty());
        }
    }
}
