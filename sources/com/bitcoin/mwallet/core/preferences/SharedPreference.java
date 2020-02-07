package com.bitcoin.mwallet.core.preferences;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017¨\u0006\u0018"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/preferences/SharedPreference;", "", "key", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getKey", "()Ljava/lang/String;", "FIAT_CURRENCY", "NETWORK_FEE", "LANGUAGE", "APP_INFO", "CONTACTS", "NOTIFICATION", "APP_LOCK", "SHARE_APP", "EXPORT_LEGACY", "DISPLAY_WALLET_MNEMONIC", "DELETE_WALLET", "RENAME_WALLET", "SPEND_LOCK", "TRANSACTION_THRESHOLD", "TRANSACTION_THRESHOLD_ENABLE", "TRANSACTION_THRESHOLD_AMOUNT", "TRANSACTION_THRESHOLD_CURRENCY", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SharedPreference.kt */
public enum SharedPreference {
    FIAT_CURRENCY("preference_fiat_currency"),
    NETWORK_FEE("preference_network_fee"),
    LANGUAGE("preference_language"),
    APP_INFO("help_app_info"),
    CONTACTS("contacts_key"),
    NOTIFICATION("preference_notifications"),
    APP_LOCK("preference_app_locks"),
    SHARE_APP("share_app"),
    EXPORT_LEGACY("preference_wallet_export"),
    DISPLAY_WALLET_MNEMONIC("menu_wallet_display_mnemonic"),
    DELETE_WALLET("menu_wallet_delete"),
    RENAME_WALLET("menu_wallet_rename"),
    SPEND_LOCK("menu_wallet_spend_lock"),
    TRANSACTION_THRESHOLD("menu_wallet_transaction_threshold"),
    TRANSACTION_THRESHOLD_ENABLE("menu_transaction_threshold_enable"),
    TRANSACTION_THRESHOLD_AMOUNT("menu_transaction_threshold_amount"),
    TRANSACTION_THRESHOLD_CURRENCY("menu_transaction_threshold_currency");
    
    @NotNull
    private final String key;

    private SharedPreference(String str) {
        this.key = str;
    }

    @NotNull
    public final String getKey() {
        return this.key;
    }
}
