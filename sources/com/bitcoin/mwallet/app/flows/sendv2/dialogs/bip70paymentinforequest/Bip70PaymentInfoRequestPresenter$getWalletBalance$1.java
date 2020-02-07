package com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest;

import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005H\n¢\u0006\u0002\b\u0006"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "balances", "", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Bip70PaymentInfoRequestPresenter.kt */
final class Bip70PaymentInfoRequestPresenter$getWalletBalance$1 extends Lambda implements Function2<WalletId, List<? extends WalletInfoBalance>, WalletInfoBalance> {
    public static final Bip70PaymentInfoRequestPresenter$getWalletBalance$1 INSTANCE = new Bip70PaymentInfoRequestPresenter$getWalletBalance$1();

    Bip70PaymentInfoRequestPresenter$getWalletBalance$1() {
        super(2);
    }

    @Nullable
    public final WalletInfoBalance invoke(@Nullable WalletId walletId, @Nullable List<WalletInfoBalance> list) {
        Object obj = null;
        if (list == null) {
            return null;
        }
        ListIterator listIterator = list.listIterator(list.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                break;
            }
            Object previous = listIterator.previous();
            if (Intrinsics.areEqual((Object) ((WalletInfoBalance) previous).getWalletId(), (Object) walletId)) {
                obj = previous;
                break;
            }
        }
        return (WalletInfoBalance) obj;
    }
}
