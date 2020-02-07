package com.bitcoin.mwallet.core.models.wallet;

import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.ApplicationClass;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.credential.CredentialType;
import com.bitcoin.mwallet.core.models.exchangerate.FiatExchangeRate;
import com.bitcoin.mwallet.core.preferences.WalletPreference;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001e\u001a\u00020\tHÆ\u0003J\t\u0010\u001f\u001a\u00020\u000bHÆ\u0003J\t\u0010 \u001a\u00020\rHÆ\u0003JE\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\u000bHÖ\u0001J\t\u0010&\u001a\u00020\u0007HÖ\u0001J$\u0010'\u001a\u00020(2\u0014\u0010)\u001a\u0010\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020+\u0018\u00010*2\u0006\u0010,\u001a\u00020-R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006."}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoSatoshis;", "", "credentialType", "Lcom/bitcoin/mwallet/core/models/credential/CredentialType;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "name", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "numCopayers", "", "satoshis", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "(Lcom/bitcoin/mwallet/core/models/credential/CredentialType;Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/lang/String;Lcom/bitcoin/mwallet/core/models/Coin;ILcom/bitcoin/bitcoink/tx/Satoshis;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "getCredentialType", "()Lcom/bitcoin/mwallet/core/models/credential/CredentialType;", "getName", "()Ljava/lang/String;", "getNumCopayers", "()I", "getSatoshis", "()Lcom/bitcoin/bitcoink/tx/Satoshis;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "toWalletInfoBalance", "Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "exchangeRates", "", "Lcom/bitcoin/mwallet/core/models/exchangerate/FiatExchangeRate;", "currency", "Ljava/util/Currency;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletInfoSatoshis.kt */
public final class WalletInfoSatoshis {
    @NotNull
    private final Coin coin;
    @NotNull
    private final CredentialType credentialType;
    @NotNull
    private final String name;
    private final int numCopayers;
    @NotNull
    private final Satoshis satoshis;
    @NotNull
    private final WalletId walletId;

    @NotNull
    public static /* synthetic */ WalletInfoSatoshis copy$default(WalletInfoSatoshis walletInfoSatoshis, CredentialType credentialType2, WalletId walletId2, String str, Coin coin2, int i, Satoshis satoshis2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            credentialType2 = walletInfoSatoshis.credentialType;
        }
        if ((i2 & 2) != 0) {
            walletId2 = walletInfoSatoshis.walletId;
        }
        WalletId walletId3 = walletId2;
        if ((i2 & 4) != 0) {
            str = walletInfoSatoshis.name;
        }
        String str2 = str;
        if ((i2 & 8) != 0) {
            coin2 = walletInfoSatoshis.coin;
        }
        Coin coin3 = coin2;
        if ((i2 & 16) != 0) {
            i = walletInfoSatoshis.numCopayers;
        }
        int i3 = i;
        if ((i2 & 32) != 0) {
            satoshis2 = walletInfoSatoshis.satoshis;
        }
        return walletInfoSatoshis.copy(credentialType2, walletId3, str2, coin3, i3, satoshis2);
    }

    @NotNull
    public final CredentialType component1() {
        return this.credentialType;
    }

    @NotNull
    public final WalletId component2() {
        return this.walletId;
    }

    @NotNull
    public final String component3() {
        return this.name;
    }

    @NotNull
    public final Coin component4() {
        return this.coin;
    }

    public final int component5() {
        return this.numCopayers;
    }

    @NotNull
    public final Satoshis component6() {
        return this.satoshis;
    }

    @NotNull
    public final WalletInfoSatoshis copy(@NotNull CredentialType credentialType2, @NotNull WalletId walletId2, @NotNull String str, @NotNull Coin coin2, int i, @NotNull Satoshis satoshis2) {
        Intrinsics.checkParameterIsNotNull(credentialType2, "credentialType");
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(satoshis2, "satoshis");
        WalletInfoSatoshis walletInfoSatoshis = new WalletInfoSatoshis(credentialType2, walletId2, str, coin2, i, satoshis2);
        return walletInfoSatoshis;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof WalletInfoSatoshis) {
                WalletInfoSatoshis walletInfoSatoshis = (WalletInfoSatoshis) obj;
                if (Intrinsics.areEqual((Object) this.credentialType, (Object) walletInfoSatoshis.credentialType) && Intrinsics.areEqual((Object) this.walletId, (Object) walletInfoSatoshis.walletId) && Intrinsics.areEqual((Object) this.name, (Object) walletInfoSatoshis.name) && Intrinsics.areEqual((Object) this.coin, (Object) walletInfoSatoshis.coin)) {
                    if (!(this.numCopayers == walletInfoSatoshis.numCopayers) || !Intrinsics.areEqual((Object) this.satoshis, (Object) walletInfoSatoshis.satoshis)) {
                        return false;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        CredentialType credentialType2 = this.credentialType;
        int i = 0;
        int hashCode = (credentialType2 != null ? credentialType2.hashCode() : 0) * 31;
        WalletId walletId2 = this.walletId;
        int hashCode2 = (hashCode + (walletId2 != null ? walletId2.hashCode() : 0)) * 31;
        String str = this.name;
        int hashCode3 = (hashCode2 + (str != null ? str.hashCode() : 0)) * 31;
        Coin coin2 = this.coin;
        int hashCode4 = (((hashCode3 + (coin2 != null ? coin2.hashCode() : 0)) * 31) + this.numCopayers) * 31;
        Satoshis satoshis2 = this.satoshis;
        if (satoshis2 != null) {
            i = satoshis2.hashCode();
        }
        return hashCode4 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WalletInfoSatoshis(credentialType=");
        sb.append(this.credentialType);
        sb.append(", walletId=");
        sb.append(this.walletId);
        sb.append(", name=");
        sb.append(this.name);
        sb.append(", coin=");
        sb.append(this.coin);
        sb.append(", numCopayers=");
        sb.append(this.numCopayers);
        sb.append(", satoshis=");
        sb.append(this.satoshis);
        sb.append(")");
        return sb.toString();
    }

    public WalletInfoSatoshis(@NotNull CredentialType credentialType2, @NotNull WalletId walletId2, @NotNull String str, @NotNull Coin coin2, int i, @NotNull Satoshis satoshis2) {
        Intrinsics.checkParameterIsNotNull(credentialType2, "credentialType");
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(satoshis2, "satoshis");
        this.credentialType = credentialType2;
        this.walletId = walletId2;
        this.name = str;
        this.coin = coin2;
        this.numCopayers = i;
        this.satoshis = satoshis2;
    }

    @NotNull
    public final CredentialType getCredentialType() {
        return this.credentialType;
    }

    @NotNull
    public final WalletId getWalletId() {
        return this.walletId;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    public final int getNumCopayers() {
        return this.numCopayers;
    }

    public /* synthetic */ WalletInfoSatoshis(CredentialType credentialType2, WalletId walletId2, String str, Coin coin2, int i, Satoshis satoshis2, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i2 & 32) != 0) {
            satoshis2 = Satoshis.Companion.getZERO();
        }
        this(credentialType2, walletId2, str, coin2, i, satoshis2);
    }

    @NotNull
    public final Satoshis getSatoshis() {
        return this.satoshis;
    }

    @NotNull
    public final WalletInfoBalance toWalletInfoBalance(@Nullable Map<Coin, FiatExchangeRate> map, @NotNull Currency currency) {
        Intrinsics.checkParameterIsNotNull(currency, Param.CURRENCY);
        BigDecimal bigDecimal = null;
        FiatExchangeRate fiatExchangeRate = map != null ? (FiatExchangeRate) map.get(this.coin) : null;
        if (fiatExchangeRate != null) {
            bigDecimal = fiatExchangeRate.toFiat(this.satoshis);
        }
        BigDecimal bigDecimal2 = bigDecimal;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(ApplicationClass.Companion.getCurrentApplicationContext());
        CredentialType credentialType2 = this.credentialType;
        WalletId walletId2 = this.walletId;
        String str = this.name;
        Coin coin2 = this.coin;
        boolean z = true;
        if (this.numCopayers <= 1) {
            z = false;
        }
        Satoshis satoshis2 = this.satoshis;
        WalletId walletId3 = this.walletId;
        Intrinsics.checkExpressionValueIsNotNull(defaultSharedPreferences, "sharedPreferences");
        WalletInfoBalance walletInfoBalance = new WalletInfoBalance(credentialType2, walletId2, str, coin2, z, satoshis2, currency, bigDecimal2, new WalletPreference(walletId3, defaultSharedPreferences));
        return walletInfoBalance;
    }
}
