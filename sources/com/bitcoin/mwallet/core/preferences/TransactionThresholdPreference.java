package com.bitcoin.mwallet.core.preferences;

import com.bitcoin.mwallet.core.models.wallet.WalletId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002B\u0007\b\u0002¢\u0006\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/preferences/TransactionThresholdPreference;", "", "Lcom/bitcoin/mwallet/core/preferences/ITransactionThreshold;", "(Ljava/lang/String;I)V", "AMOUNT", "CURRENCY", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: TransactionThresholdPreference.kt */
public enum TransactionThresholdPreference implements ITransactionThreshold {
    ;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/preferences/TransactionThresholdPreference$AMOUNT;", "Lcom/bitcoin/mwallet/core/preferences/TransactionThresholdPreference;", "getPreferencesKey", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: TransactionThresholdPreference.kt */
    static final class AMOUNT extends TransactionThresholdPreference {
        AMOUNT(String str, int i) {
            super(str, i, null);
        }

        @NotNull
        public String getPreferencesKey(@NotNull WalletId walletId) {
            Intrinsics.checkParameterIsNotNull(walletId, "walletId");
            StringBuilder sb = new StringBuilder();
            sb.append(walletId.toString());
            sb.append("_tt_amount");
            return sb.toString();
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0001\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/preferences/TransactionThresholdPreference$CURRENCY;", "Lcom/bitcoin/mwallet/core/preferences/TransactionThresholdPreference;", "getPreferencesKey", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: TransactionThresholdPreference.kt */
    static final class CURRENCY extends TransactionThresholdPreference {
        CURRENCY(String str, int i) {
            super(str, i, null);
        }

        @NotNull
        public String getPreferencesKey(@NotNull WalletId walletId) {
            Intrinsics.checkParameterIsNotNull(walletId, "walletId");
            StringBuilder sb = new StringBuilder();
            sb.append(walletId.toString());
            sb.append("_tt_currency");
            return sb.toString();
        }
    }
}
