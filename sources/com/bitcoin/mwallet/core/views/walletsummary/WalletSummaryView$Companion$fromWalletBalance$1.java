package com.bitcoin.mwallet.core.views.walletsummary;

import com.bitcoin.mwallet.core.extensions.BigDecimalKt;
import com.bitcoin.mwallet.core.extensions.SatoshisKt;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.credential.CredentialType;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.bitcoin.mwallet.core.models.wallet.WalletInfoBalance;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "Lcom/bitcoin/mwallet/core/views/walletsummary/WalletSummaryView;", "info", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: WalletSummaryView.kt */
final class WalletSummaryView$Companion$fromWalletBalance$1 extends Lambda implements Function1<WalletInfoBalance, WalletSummaryView> {
    public static final WalletSummaryView$Companion$fromWalletBalance$1 INSTANCE = new WalletSummaryView$Companion$fromWalletBalance$1();

    WalletSummaryView$Companion$fromWalletBalance$1() {
        super(1);
    }

    @Nullable
    public final WalletSummaryView invoke(@Nullable WalletInfoBalance walletInfoBalance) {
        if (walletInfoBalance == null) {
            return null;
        }
        CredentialType credentialType = walletInfoBalance.getCredentialType();
        WalletId walletId = walletInfoBalance.getWalletId();
        String name = walletInfoBalance.getName();
        Coin coin = walletInfoBalance.getCoin();
        String color = walletInfoBalance.getWalletPreference().getColor();
        if (color == null) {
            Intrinsics.throwNpe();
        }
        WalletSummaryView walletSummaryView = new WalletSummaryView(credentialType, walletId, name, coin, color, walletInfoBalance.getMultiSig(), BigDecimalKt.toFiatString$default(walletInfoBalance.getFiatBalance(), walletInfoBalance.getFiatCurrency(), false, 2, null), SatoshisKt.toCoinString(walletInfoBalance.getSatoshis(), walletInfoBalance.getCoin()), null, null, null, 1792, null);
        return walletSummaryView;
    }
}
