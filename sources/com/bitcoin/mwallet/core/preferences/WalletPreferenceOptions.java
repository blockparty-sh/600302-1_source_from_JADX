package com.bitcoin.mwallet.core.preferences;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/preferences/WalletPreferenceOptions;", "", "key", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "COLOR", "SPENDING_AUTH", "HIDE_BALANCE", "SHOW_ALL_TOKENS", "SHOW_SLP_TRANSACTIONS", "HAS_BACKED_UP", "TRANSACTION_THRESHOLD", "LAST_TXHISTORY_UPDATE", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletPreference.kt */
public enum WalletPreferenceOptions {
    COLOR("_color"),
    SPENDING_AUTH("_spending_auth"),
    HIDE_BALANCE("_hide_balance"),
    SHOW_ALL_TOKENS("_show_all_tokens"),
    SHOW_SLP_TRANSACTIONS("_show_slp_transactions"),
    HAS_BACKED_UP("_has_backed_up"),
    TRANSACTION_THRESHOLD("_transaction_threshold_"),
    LAST_TXHISTORY_UPDATE("_txhistory_update_");
    
    @NotNull
    private final String key;

    private WalletPreferenceOptions(String str) {
        this.key = str;
    }

    @NotNull
    public final String getKey() {
        return this.key;
    }
}
