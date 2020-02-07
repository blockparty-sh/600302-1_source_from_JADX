package com.bitcoin.mwallet.core.preferences;

import android.content.SharedPreferences;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010/\u001a\u000200J\b\u0010(\u001a\u00020'H\u0002J\u0010\u00101\u001a\u00020\b2\u0006\u00102\u001a\u00020\bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R(\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\b8F@FX\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\bXD¢\u0006\u0002\n\u0000R$\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u000f8F@FX\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R$\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u000f8F@FX\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0012\"\u0004\b\u0017\u0010\u0014R$\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u000f8F@FX\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R$\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0007\u001a\u00020\u001b8F@FX\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R$\u0010!\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u000f8F@FX\u000e¢\u0006\f\u001a\u0004\b\"\u0010\u0012\"\u0004\b#\u0010\u0014R$\u0010$\u001a\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u000f8F@FX\u000e¢\u0006\f\u001a\u0004\b%\u0010\u0012\"\u0004\b&\u0010\u0014R(\u0010(\u001a\u0004\u0018\u00010'2\b\u0010\u0007\u001a\u0004\u0018\u00010'8F@FX\u000e¢\u0006\f\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.¨\u00063"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/preferences/WalletPreference;", "", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "applicationPreferences", "Landroid/content/SharedPreferences;", "(Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Landroid/content/SharedPreferences;)V", "value", "", "color", "getColor", "()Ljava/lang/String;", "setColor", "(Ljava/lang/String;)V", "colorDefault", "", "hasBackedUp", "getHasBackedUp", "()Z", "setHasBackedUp", "(Z)V", "hasSpendingAuth", "getHasSpendingAuth", "setHasSpendingAuth", "hideBalance", "getHideBalance", "setHideBalance", "", "lastTxHistoryUpdate", "getLastTxHistoryUpdate", "()J", "setLastTxHistoryUpdate", "(J)V", "showAllTokens", "getShowAllTokens", "setShowAllTokens", "showSlpTransaction", "getShowSlpTransaction", "setShowSlpTransaction", "Ljava/math/BigDecimal;", "thresholdAmount", "getThresholdAmount", "()Ljava/math/BigDecimal;", "setThresholdAmount", "(Ljava/math/BigDecimal;)V", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "clearPreferences", "", "walletPreferenceKey", "key", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletPreference.kt */
public final class WalletPreference {
    private final SharedPreferences applicationPreferences;
    private final String colorDefault = "#2933BC";
    @NotNull
    private final WalletId walletId;

    public WalletPreference(@NotNull WalletId walletId2, @NotNull SharedPreferences sharedPreferences) {
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Intrinsics.checkParameterIsNotNull(sharedPreferences, "applicationPreferences");
        this.walletId = walletId2;
        this.applicationPreferences = sharedPreferences;
    }

    @NotNull
    public final WalletId getWalletId() {
        return this.walletId;
    }

    @Nullable
    public final String getColor() {
        return this.applicationPreferences.getString(walletPreferenceKey(WalletPreferenceOptions.COLOR.getKey()), this.colorDefault);
    }

    public final void setColor(@Nullable String str) {
        this.applicationPreferences.edit().putString(walletPreferenceKey(WalletPreferenceOptions.COLOR.getKey()), str).apply();
    }

    public final boolean getHasSpendingAuth() {
        return this.applicationPreferences.getBoolean(walletPreferenceKey(WalletPreferenceOptions.SPENDING_AUTH.getKey()), false);
    }

    public final void setHasSpendingAuth(boolean z) {
        this.applicationPreferences.edit().putBoolean(walletPreferenceKey(WalletPreferenceOptions.SPENDING_AUTH.getKey()), z).apply();
    }

    public final boolean getHideBalance() {
        return this.applicationPreferences.getBoolean(walletPreferenceKey(WalletPreferenceOptions.HIDE_BALANCE.getKey()), false);
    }

    public final void setHideBalance(boolean z) {
        this.applicationPreferences.edit().putBoolean(walletPreferenceKey(WalletPreferenceOptions.HIDE_BALANCE.getKey()), z).apply();
    }

    public final boolean getShowAllTokens() {
        return this.applicationPreferences.getBoolean(walletPreferenceKey(WalletPreferenceOptions.SHOW_ALL_TOKENS.getKey()), true);
    }

    public final void setShowAllTokens(boolean z) {
        this.applicationPreferences.edit().putBoolean(walletPreferenceKey(WalletPreferenceOptions.SHOW_ALL_TOKENS.getKey()), z).apply();
    }

    public final boolean getShowSlpTransaction() {
        return this.applicationPreferences.getBoolean(walletPreferenceKey(WalletPreferenceOptions.SHOW_SLP_TRANSACTIONS.getKey()), true);
    }

    public final void setShowSlpTransaction(boolean z) {
        this.applicationPreferences.edit().putBoolean(walletPreferenceKey(WalletPreferenceOptions.SHOW_SLP_TRANSACTIONS.getKey()), z).apply();
    }

    public final boolean getHasBackedUp() {
        return this.applicationPreferences.getBoolean(walletPreferenceKey(WalletPreferenceOptions.HAS_BACKED_UP.getKey()), false);
    }

    public final void setHasBackedUp(boolean z) {
        this.applicationPreferences.edit().putBoolean(walletPreferenceKey(WalletPreferenceOptions.HAS_BACKED_UP.getKey()), z).apply();
    }

    @Nullable
    public final BigDecimal getThresholdAmount() {
        return thresholdAmount();
    }

    public final void setThresholdAmount(@Nullable BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            this.applicationPreferences.edit().putFloat(walletPreferenceKey(WalletPreferenceOptions.TRANSACTION_THRESHOLD.getKey()), 0.0f).apply();
        } else {
            this.applicationPreferences.edit().putFloat(walletPreferenceKey(WalletPreferenceOptions.TRANSACTION_THRESHOLD.getKey()), bigDecimal.floatValue()).apply();
        }
    }

    public final long getLastTxHistoryUpdate() {
        return this.applicationPreferences.getLong(walletPreferenceKey(WalletPreferenceOptions.LAST_TXHISTORY_UPDATE.getKey()), 0);
    }

    public final void setLastTxHistoryUpdate(long j) {
        this.applicationPreferences.edit().putLong(walletPreferenceKey(WalletPreferenceOptions.LAST_TXHISTORY_UPDATE.getKey()), j).apply();
    }

    private final BigDecimal thresholdAmount() {
        return new BigDecimal(String.valueOf(this.applicationPreferences.getFloat(walletPreferenceKey(WalletPreferenceOptions.TRANSACTION_THRESHOLD.getKey()), 0.0f)));
    }

    public final void clearPreferences() {
        for (WalletPreferenceOptions key : WalletPreferenceOptions.values()) {
            this.applicationPreferences.edit().remove(walletPreferenceKey(key.getKey())).apply();
        }
    }

    private final String walletPreferenceKey(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.walletId.toString());
        sb.append(str);
        return sb.toString();
    }
}
