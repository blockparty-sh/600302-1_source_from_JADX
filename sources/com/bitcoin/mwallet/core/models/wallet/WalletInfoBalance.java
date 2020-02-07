package com.bitcoin.mwallet.core.models.wallet;

import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.credential.CredentialType;
import com.bitcoin.mwallet.core.preferences.WalletPreference;
import java.math.BigDecimal;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0005HÆ\u0003J\t\u0010+\u001a\u00020\u0007HÆ\u0003J\t\u0010,\u001a\u00020\tHÆ\u0003J\t\u0010-\u001a\u00020\u000bHÆ\u0003J\t\u0010.\u001a\u00020\rHÆ\u0003J\t\u0010/\u001a\u00020\u000fHÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\t\u00101\u001a\u00020\u0013HÆ\u0003Je\u00102\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u0013HÆ\u0001J\u0013\u00103\u001a\u00020\u000b2\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00105\u001a\u000206HÖ\u0001J\t\u00107\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0011\u0010\u0012\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(¨\u00068"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/wallet/WalletInfoBalance;", "", "credentialType", "Lcom/bitcoin/mwallet/core/models/credential/CredentialType;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "name", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "multiSig", "", "satoshis", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "fiatCurrency", "Ljava/util/Currency;", "fiatBalance", "Ljava/math/BigDecimal;", "walletPreference", "Lcom/bitcoin/mwallet/core/preferences/WalletPreference;", "(Lcom/bitcoin/mwallet/core/models/credential/CredentialType;Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Ljava/lang/String;Lcom/bitcoin/mwallet/core/models/Coin;ZLcom/bitcoin/bitcoink/tx/Satoshis;Ljava/util/Currency;Ljava/math/BigDecimal;Lcom/bitcoin/mwallet/core/preferences/WalletPreference;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "getCredentialType", "()Lcom/bitcoin/mwallet/core/models/credential/CredentialType;", "getFiatBalance", "()Ljava/math/BigDecimal;", "setFiatBalance", "(Ljava/math/BigDecimal;)V", "getFiatCurrency", "()Ljava/util/Currency;", "getMultiSig", "()Z", "getName", "()Ljava/lang/String;", "getSatoshis", "()Lcom/bitcoin/bitcoink/tx/Satoshis;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "getWalletPreference", "()Lcom/bitcoin/mwallet/core/preferences/WalletPreference;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletInfoBalance.kt */
public final class WalletInfoBalance {
    @NotNull
    private final Coin coin;
    @NotNull
    private final CredentialType credentialType;
    @Nullable
    private BigDecimal fiatBalance;
    @NotNull
    private final Currency fiatCurrency;
    private final boolean multiSig;
    @NotNull
    private final String name;
    @NotNull
    private final Satoshis satoshis;
    @NotNull
    private final WalletId walletId;
    @NotNull
    private final WalletPreference walletPreference;

    @NotNull
    public static /* synthetic */ WalletInfoBalance copy$default(WalletInfoBalance walletInfoBalance, CredentialType credentialType2, WalletId walletId2, String str, Coin coin2, boolean z, Satoshis satoshis2, Currency currency, BigDecimal bigDecimal, WalletPreference walletPreference2, int i, Object obj) {
        WalletInfoBalance walletInfoBalance2 = walletInfoBalance;
        int i2 = i;
        return walletInfoBalance.copy((i2 & 1) != 0 ? walletInfoBalance2.credentialType : credentialType2, (i2 & 2) != 0 ? walletInfoBalance2.walletId : walletId2, (i2 & 4) != 0 ? walletInfoBalance2.name : str, (i2 & 8) != 0 ? walletInfoBalance2.coin : coin2, (i2 & 16) != 0 ? walletInfoBalance2.multiSig : z, (i2 & 32) != 0 ? walletInfoBalance2.satoshis : satoshis2, (i2 & 64) != 0 ? walletInfoBalance2.fiatCurrency : currency, (i2 & 128) != 0 ? walletInfoBalance2.fiatBalance : bigDecimal, (i2 & 256) != 0 ? walletInfoBalance2.walletPreference : walletPreference2);
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

    public final boolean component5() {
        return this.multiSig;
    }

    @NotNull
    public final Satoshis component6() {
        return this.satoshis;
    }

    @NotNull
    public final Currency component7() {
        return this.fiatCurrency;
    }

    @Nullable
    public final BigDecimal component8() {
        return this.fiatBalance;
    }

    @NotNull
    public final WalletPreference component9() {
        return this.walletPreference;
    }

    @NotNull
    public final WalletInfoBalance copy(@NotNull CredentialType credentialType2, @NotNull WalletId walletId2, @NotNull String str, @NotNull Coin coin2, boolean z, @NotNull Satoshis satoshis2, @NotNull Currency currency, @Nullable BigDecimal bigDecimal, @NotNull WalletPreference walletPreference2) {
        CredentialType credentialType3 = credentialType2;
        Intrinsics.checkParameterIsNotNull(credentialType2, "credentialType");
        WalletId walletId3 = walletId2;
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        String str2 = str;
        Intrinsics.checkParameterIsNotNull(str, "name");
        Coin coin3 = coin2;
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Satoshis satoshis3 = satoshis2;
        Intrinsics.checkParameterIsNotNull(satoshis3, "satoshis");
        Currency currency2 = currency;
        Intrinsics.checkParameterIsNotNull(currency2, "fiatCurrency");
        WalletPreference walletPreference3 = walletPreference2;
        Intrinsics.checkParameterIsNotNull(walletPreference3, "walletPreference");
        WalletInfoBalance walletInfoBalance = new WalletInfoBalance(credentialType3, walletId3, str2, coin3, z, satoshis3, currency2, bigDecimal, walletPreference3);
        return walletInfoBalance;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof WalletInfoBalance) {
                WalletInfoBalance walletInfoBalance = (WalletInfoBalance) obj;
                if (Intrinsics.areEqual((Object) this.credentialType, (Object) walletInfoBalance.credentialType) && Intrinsics.areEqual((Object) this.walletId, (Object) walletInfoBalance.walletId) && Intrinsics.areEqual((Object) this.name, (Object) walletInfoBalance.name) && Intrinsics.areEqual((Object) this.coin, (Object) walletInfoBalance.coin)) {
                    if (!(this.multiSig == walletInfoBalance.multiSig) || !Intrinsics.areEqual((Object) this.satoshis, (Object) walletInfoBalance.satoshis) || !Intrinsics.areEqual((Object) this.fiatCurrency, (Object) walletInfoBalance.fiatCurrency) || !Intrinsics.areEqual((Object) this.fiatBalance, (Object) walletInfoBalance.fiatBalance) || !Intrinsics.areEqual((Object) this.walletPreference, (Object) walletInfoBalance.walletPreference)) {
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
        int hashCode4 = (hashCode3 + (coin2 != null ? coin2.hashCode() : 0)) * 31;
        boolean z = this.multiSig;
        if (z) {
            z = true;
        }
        int i2 = (hashCode4 + (z ? 1 : 0)) * 31;
        Satoshis satoshis2 = this.satoshis;
        int hashCode5 = (i2 + (satoshis2 != null ? satoshis2.hashCode() : 0)) * 31;
        Currency currency = this.fiatCurrency;
        int hashCode6 = (hashCode5 + (currency != null ? currency.hashCode() : 0)) * 31;
        BigDecimal bigDecimal = this.fiatBalance;
        int hashCode7 = (hashCode6 + (bigDecimal != null ? bigDecimal.hashCode() : 0)) * 31;
        WalletPreference walletPreference2 = this.walletPreference;
        if (walletPreference2 != null) {
            i = walletPreference2.hashCode();
        }
        return hashCode7 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WalletInfoBalance(credentialType=");
        sb.append(this.credentialType);
        sb.append(", walletId=");
        sb.append(this.walletId);
        sb.append(", name=");
        sb.append(this.name);
        sb.append(", coin=");
        sb.append(this.coin);
        sb.append(", multiSig=");
        sb.append(this.multiSig);
        sb.append(", satoshis=");
        sb.append(this.satoshis);
        sb.append(", fiatCurrency=");
        sb.append(this.fiatCurrency);
        sb.append(", fiatBalance=");
        sb.append(this.fiatBalance);
        sb.append(", walletPreference=");
        sb.append(this.walletPreference);
        sb.append(")");
        return sb.toString();
    }

    public WalletInfoBalance(@NotNull CredentialType credentialType2, @NotNull WalletId walletId2, @NotNull String str, @NotNull Coin coin2, boolean z, @NotNull Satoshis satoshis2, @NotNull Currency currency, @Nullable BigDecimal bigDecimal, @NotNull WalletPreference walletPreference2) {
        Intrinsics.checkParameterIsNotNull(credentialType2, "credentialType");
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(satoshis2, "satoshis");
        Intrinsics.checkParameterIsNotNull(currency, "fiatCurrency");
        Intrinsics.checkParameterIsNotNull(walletPreference2, "walletPreference");
        this.credentialType = credentialType2;
        this.walletId = walletId2;
        this.name = str;
        this.coin = coin2;
        this.multiSig = z;
        this.satoshis = satoshis2;
        this.fiatCurrency = currency;
        this.fiatBalance = bigDecimal;
        this.walletPreference = walletPreference2;
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

    public final boolean getMultiSig() {
        return this.multiSig;
    }

    @NotNull
    public final Satoshis getSatoshis() {
        return this.satoshis;
    }

    @NotNull
    public final Currency getFiatCurrency() {
        return this.fiatCurrency;
    }

    @Nullable
    public final BigDecimal getFiatBalance() {
        return this.fiatBalance;
    }

    public final void setFiatBalance(@Nullable BigDecimal bigDecimal) {
        this.fiatBalance = bigDecimal;
    }

    @NotNull
    public final WalletPreference getWalletPreference() {
        return this.walletPreference;
    }
}
