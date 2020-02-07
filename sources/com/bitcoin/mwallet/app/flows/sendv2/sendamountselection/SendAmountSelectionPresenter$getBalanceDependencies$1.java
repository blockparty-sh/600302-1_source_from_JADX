package com.bitcoin.mwallet.app.flows.sendv2.sendamountselection;

import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00012\b\u0010\u0004\u001a\u0004\u0018\u00010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0006"}, mo37405d2 = {"<anonymous>", "Lkotlin/Pair;", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "Ljava/util/Currency;", "walletId", "displayCurrency", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SendAmountSelectionPresenter.kt */
final class SendAmountSelectionPresenter$getBalanceDependencies$1 extends Lambda implements Function2<WalletId, Currency, Pair<? extends WalletId, ? extends Currency>> {
    public static final SendAmountSelectionPresenter$getBalanceDependencies$1 INSTANCE = new SendAmountSelectionPresenter$getBalanceDependencies$1();

    SendAmountSelectionPresenter$getBalanceDependencies$1() {
        super(2);
    }

    @NotNull
    public final Pair<WalletId, Currency> invoke(@Nullable WalletId walletId, @Nullable Currency currency) {
        return new Pair<>(walletId, currency);
    }
}
