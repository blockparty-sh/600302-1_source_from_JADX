package com.bitcoin.mwallet.app.components.walletselector;

import androidx.arch.core.util.Function;
import com.bitcoin.mwallet.core.models.credential.CredentialType;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import com.bitcoin.mwallet.core.views.walletsummary.WalletSummaryView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00040\u0001H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryView;", "walletBalances", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "apply"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WalletSelectorPresenter.kt */
final class WalletSelectorPresenter$walletSummaries$1<I, O> implements Function<X, Y> {
    public static final WalletSelectorPresenter$walletSummaries$1 INSTANCE = new WalletSelectorPresenter$walletSummaries$1();

    WalletSelectorPresenter$walletSummaries$1() {
    }

    @NotNull
    public final List<WalletSummaryView> apply(@NotNull List<WalletInfoBalance> list) {
        Intrinsics.checkParameterIsNotNull(list, "walletBalances");
        Iterable iterable = list;
        Collection arrayList = new ArrayList();
        for (Object next : iterable) {
            WalletInfoBalance walletInfoBalance = (WalletInfoBalance) next;
            if ((walletInfoBalance.getMultiSig() || walletInfoBalance.getCredentialType() == CredentialType.ENCRYPTED_MNEMONIC || walletInfoBalance.getCredentialType() == CredentialType.MNEMONIC_AND_PROTECTED) ? false : true) {
                arrayList.add(next);
            }
        }
        Iterable<Object> iterable2 = (List) arrayList;
        Function1 fromWalletBalance = WalletSummaryView.Companion.fromWalletBalance();
        Collection arrayList2 = new ArrayList();
        for (Object invoke : iterable2) {
            Object invoke2 = fromWalletBalance.invoke(invoke);
            if (invoke2 != null) {
                arrayList2.add(invoke2);
            }
        }
        return (List) arrayList2;
    }
}
