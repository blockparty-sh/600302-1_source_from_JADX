package com.bitcoin.mwallet.app.flows.receive.receive.entity;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u001d\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0007HÆ\u0003J\t\u0010\u001f\u001a\u00020\nHÆ\u0003J?\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010!\u001a\u00020\u00032\b\u0010\"\u001a\u0004\u0018\u00010#HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0005HÖ\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0005X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\b\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001a¨\u0006'"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receive/entity/ReceiveAmount;", "Ljava/io/Serializable;", "selectedAmountIsCrypto", "", "cryptoTicker", "", "crypto", "Ljava/math/BigDecimal;", "fiat", "currency", "Ljava/util/Currency;", "(ZLjava/lang/String;Ljava/math/BigDecimal;Ljava/math/BigDecimal;Ljava/util/Currency;)V", "getCrypto", "()Ljava/math/BigDecimal;", "setCrypto", "(Ljava/math/BigDecimal;)V", "getCryptoTicker", "()Ljava/lang/String;", "setCryptoTicker", "(Ljava/lang/String;)V", "getCurrency", "()Ljava/util/Currency;", "getFiat", "getSelectedAmountIsCrypto", "()Z", "setSelectedAmountIsCrypto", "(Z)V", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceivedAmount.kt */
public final class ReceiveAmount implements Serializable {
    @Nullable
    private BigDecimal crypto;
    @NotNull
    private String cryptoTicker;
    @NotNull
    private final Currency currency;
    @Nullable
    private final BigDecimal fiat;
    private boolean selectedAmountIsCrypto;

    @NotNull
    public static /* synthetic */ ReceiveAmount copy$default(ReceiveAmount receiveAmount, boolean z, String str, BigDecimal bigDecimal, BigDecimal bigDecimal2, Currency currency2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = receiveAmount.selectedAmountIsCrypto;
        }
        if ((i & 2) != 0) {
            str = receiveAmount.cryptoTicker;
        }
        String str2 = str;
        if ((i & 4) != 0) {
            bigDecimal = receiveAmount.crypto;
        }
        BigDecimal bigDecimal3 = bigDecimal;
        if ((i & 8) != 0) {
            bigDecimal2 = receiveAmount.fiat;
        }
        BigDecimal bigDecimal4 = bigDecimal2;
        if ((i & 16) != 0) {
            currency2 = receiveAmount.currency;
        }
        return receiveAmount.copy(z, str2, bigDecimal3, bigDecimal4, currency2);
    }

    public final boolean component1() {
        return this.selectedAmountIsCrypto;
    }

    @NotNull
    public final String component2() {
        return this.cryptoTicker;
    }

    @Nullable
    public final BigDecimal component3() {
        return this.crypto;
    }

    @Nullable
    public final BigDecimal component4() {
        return this.fiat;
    }

    @NotNull
    public final Currency component5() {
        return this.currency;
    }

    @NotNull
    public final ReceiveAmount copy(boolean z, @NotNull String str, @Nullable BigDecimal bigDecimal, @Nullable BigDecimal bigDecimal2, @NotNull Currency currency2) {
        Intrinsics.checkParameterIsNotNull(str, "cryptoTicker");
        Intrinsics.checkParameterIsNotNull(currency2, Param.CURRENCY);
        ReceiveAmount receiveAmount = new ReceiveAmount(z, str, bigDecimal, bigDecimal2, currency2);
        return receiveAmount;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof ReceiveAmount) {
                ReceiveAmount receiveAmount = (ReceiveAmount) obj;
                if (!(this.selectedAmountIsCrypto == receiveAmount.selectedAmountIsCrypto) || !Intrinsics.areEqual((Object) this.cryptoTicker, (Object) receiveAmount.cryptoTicker) || !Intrinsics.areEqual((Object) this.crypto, (Object) receiveAmount.crypto) || !Intrinsics.areEqual((Object) this.fiat, (Object) receiveAmount.fiat) || !Intrinsics.areEqual((Object) this.currency, (Object) receiveAmount.currency)) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        boolean z = this.selectedAmountIsCrypto;
        if (z) {
            z = true;
        }
        int i = (z ? 1 : 0) * true;
        String str = this.cryptoTicker;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        BigDecimal bigDecimal = this.crypto;
        int hashCode2 = (hashCode + (bigDecimal != null ? bigDecimal.hashCode() : 0)) * 31;
        BigDecimal bigDecimal2 = this.fiat;
        int hashCode3 = (hashCode2 + (bigDecimal2 != null ? bigDecimal2.hashCode() : 0)) * 31;
        Currency currency2 = this.currency;
        if (currency2 != null) {
            i2 = currency2.hashCode();
        }
        return hashCode3 + i2;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ReceiveAmount(selectedAmountIsCrypto=");
        sb.append(this.selectedAmountIsCrypto);
        sb.append(", cryptoTicker=");
        sb.append(this.cryptoTicker);
        sb.append(", crypto=");
        sb.append(this.crypto);
        sb.append(", fiat=");
        sb.append(this.fiat);
        sb.append(", currency=");
        sb.append(this.currency);
        sb.append(")");
        return sb.toString();
    }

    public ReceiveAmount(boolean z, @NotNull String str, @Nullable BigDecimal bigDecimal, @Nullable BigDecimal bigDecimal2, @NotNull Currency currency2) {
        Intrinsics.checkParameterIsNotNull(str, "cryptoTicker");
        Intrinsics.checkParameterIsNotNull(currency2, Param.CURRENCY);
        this.selectedAmountIsCrypto = z;
        this.cryptoTicker = str;
        this.crypto = bigDecimal;
        this.fiat = bigDecimal2;
        this.currency = currency2;
    }

    public final boolean getSelectedAmountIsCrypto() {
        return this.selectedAmountIsCrypto;
    }

    public final void setSelectedAmountIsCrypto(boolean z) {
        this.selectedAmountIsCrypto = z;
    }

    @NotNull
    public final String getCryptoTicker() {
        return this.cryptoTicker;
    }

    public final void setCryptoTicker(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.cryptoTicker = str;
    }

    @Nullable
    public final BigDecimal getCrypto() {
        return this.crypto;
    }

    public final void setCrypto(@Nullable BigDecimal bigDecimal) {
        this.crypto = bigDecimal;
    }

    @Nullable
    public final BigDecimal getFiat() {
        return this.fiat;
    }

    @NotNull
    public final Currency getCurrency() {
        return this.currency;
    }
}
