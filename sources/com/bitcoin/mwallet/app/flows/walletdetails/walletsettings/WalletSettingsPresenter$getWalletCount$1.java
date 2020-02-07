package com.bitcoin.mwallet.app.flows.walletdetails.walletsettings;

import com.bitcoin.mwallet.core.models.wallet.C1261Wallet;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, mo37405d2 = {"<anonymous>", "", "wallets", "", "Lcom/bitcoin/mwallet/core/models/wallet/Wallet;", "wallet", "invoke", "(Ljava/util/List;Lcom/bitcoin/mwallet/core/models/wallet/Wallet;)Ljava/lang/Integer;"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WalletSettingsPresenter.kt */
final class WalletSettingsPresenter$getWalletCount$1 extends Lambda implements Function2<List<? extends C1261Wallet>, C1261Wallet, Integer> {
    public static final WalletSettingsPresenter$getWalletCount$1 INSTANCE = new WalletSettingsPresenter$getWalletCount$1();

    WalletSettingsPresenter$getWalletCount$1() {
        super(2);
    }

    @Nullable
    public final Integer invoke(@Nullable List<C1261Wallet> list, @Nullable C1261Wallet wallet) {
        int i;
        if (list == null || wallet == null) {
            return null;
        }
        Iterable<C1261Wallet> iterable = list;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            i = 0;
            for (C1261Wallet coin : iterable) {
                if (coin.getCoin() == wallet.getCoin()) {
                    i++;
                    if (i < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
        } else {
            i = 0;
        }
        return Integer.valueOf(i);
    }
}
