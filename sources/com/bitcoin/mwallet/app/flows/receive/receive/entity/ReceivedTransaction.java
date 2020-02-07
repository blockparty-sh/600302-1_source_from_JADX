package com.bitcoin.mwallet.app.flows.receive.receive.entity;

import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.io.Serializable;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u001b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0005HÆ\u0003J\t\u0010%\u001a\u00020\u0007HÆ\u0003J\u000b\u0010&\u001a\u0004\u0018\u00010\tHÆ\u0003J\t\u0010'\u001a\u00020\u000bHÆ\u0003J\t\u0010(\u001a\u00020\rHÆ\u0003J\t\u0010)\u001a\u00020\u000fHÆ\u0003J\t\u0010*\u001a\u00020\u0011HÆ\u0003J[\u0010+\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u0011HÆ\u0001J\u0013\u0010,\u001a\u00020-2\b\u0010.\u001a\u0004\u0018\u00010/HÖ\u0003J\t\u00100\u001a\u000201HÖ\u0001J\t\u00102\u001a\u00020\u000bHÖ\u0001R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u00063"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/receive/receive/entity/ReceivedTransaction;", "Ljava/io/Serializable;", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "satoshis", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "fiat", "Ljava/math/BigDecimal;", "currency", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "tokenAmount", "", "(Lcom/bitcoin/bitcoink/tx/TxId;Lcom/bitcoin/mwallet/core/models/wallet/WalletId;Lcom/bitcoin/bitcoink/tx/Satoshis;Ljava/math/BigDecimal;Ljava/lang/String;Lcom/bitcoin/mwallet/core/models/Coin;Lcom/bitcoin/mwallet/core/models/slp/SlpId;J)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "getCurrency", "()Ljava/lang/String;", "getFiat", "()Ljava/math/BigDecimal;", "getSatoshis", "()Lcom/bitcoin/bitcoink/tx/Satoshis;", "getTokenAmount", "()J", "getTokenId", "()Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "getTxId", "()Lcom/bitcoin/bitcoink/tx/TxId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ReceivedTransaction.kt */
public final class ReceivedTransaction implements Serializable {
    @NotNull
    private final Coin coin;
    @NotNull
    private final String currency;
    @Nullable
    private final BigDecimal fiat;
    @NotNull
    private final Satoshis satoshis;
    private final long tokenAmount;
    @NotNull
    private final SlpId tokenId;
    @NotNull
    private final TxId txId;
    @NotNull
    private final WalletId walletId;

    @NotNull
    public static /* synthetic */ ReceivedTransaction copy$default(ReceivedTransaction receivedTransaction, TxId txId2, WalletId walletId2, Satoshis satoshis2, BigDecimal bigDecimal, String str, Coin coin2, SlpId slpId, long j, int i, Object obj) {
        ReceivedTransaction receivedTransaction2 = receivedTransaction;
        int i2 = i;
        return receivedTransaction.copy((i2 & 1) != 0 ? receivedTransaction2.txId : txId2, (i2 & 2) != 0 ? receivedTransaction2.walletId : walletId2, (i2 & 4) != 0 ? receivedTransaction2.satoshis : satoshis2, (i2 & 8) != 0 ? receivedTransaction2.fiat : bigDecimal, (i2 & 16) != 0 ? receivedTransaction2.currency : str, (i2 & 32) != 0 ? receivedTransaction2.coin : coin2, (i2 & 64) != 0 ? receivedTransaction2.tokenId : slpId, (i2 & 128) != 0 ? receivedTransaction2.tokenAmount : j);
    }

    @NotNull
    public final TxId component1() {
        return this.txId;
    }

    @NotNull
    public final WalletId component2() {
        return this.walletId;
    }

    @NotNull
    public final Satoshis component3() {
        return this.satoshis;
    }

    @Nullable
    public final BigDecimal component4() {
        return this.fiat;
    }

    @NotNull
    public final String component5() {
        return this.currency;
    }

    @NotNull
    public final Coin component6() {
        return this.coin;
    }

    @NotNull
    public final SlpId component7() {
        return this.tokenId;
    }

    public final long component8() {
        return this.tokenAmount;
    }

    @NotNull
    public final ReceivedTransaction copy(@NotNull TxId txId2, @NotNull WalletId walletId2, @NotNull Satoshis satoshis2, @Nullable BigDecimal bigDecimal, @NotNull String str, @NotNull Coin coin2, @NotNull SlpId slpId, long j) {
        TxId txId3 = txId2;
        Intrinsics.checkParameterIsNotNull(txId2, "txId");
        WalletId walletId3 = walletId2;
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Satoshis satoshis3 = satoshis2;
        Intrinsics.checkParameterIsNotNull(satoshis2, "satoshis");
        String str2 = str;
        Intrinsics.checkParameterIsNotNull(str2, Param.CURRENCY);
        Coin coin3 = coin2;
        Intrinsics.checkParameterIsNotNull(coin3, "coin");
        SlpId slpId2 = slpId;
        Intrinsics.checkParameterIsNotNull(slpId2, "tokenId");
        ReceivedTransaction receivedTransaction = new ReceivedTransaction(txId3, walletId3, satoshis3, bigDecimal, str2, coin3, slpId2, j);
        return receivedTransaction;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof ReceivedTransaction) {
                ReceivedTransaction receivedTransaction = (ReceivedTransaction) obj;
                if (Intrinsics.areEqual((Object) this.txId, (Object) receivedTransaction.txId) && Intrinsics.areEqual((Object) this.walletId, (Object) receivedTransaction.walletId) && Intrinsics.areEqual((Object) this.satoshis, (Object) receivedTransaction.satoshis) && Intrinsics.areEqual((Object) this.fiat, (Object) receivedTransaction.fiat) && Intrinsics.areEqual((Object) this.currency, (Object) receivedTransaction.currency) && Intrinsics.areEqual((Object) this.coin, (Object) receivedTransaction.coin) && Intrinsics.areEqual((Object) this.tokenId, (Object) receivedTransaction.tokenId)) {
                    if (this.tokenAmount == receivedTransaction.tokenAmount) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        TxId txId2 = this.txId;
        int i = 0;
        int hashCode = (txId2 != null ? txId2.hashCode() : 0) * 31;
        WalletId walletId2 = this.walletId;
        int hashCode2 = (hashCode + (walletId2 != null ? walletId2.hashCode() : 0)) * 31;
        Satoshis satoshis2 = this.satoshis;
        int hashCode3 = (hashCode2 + (satoshis2 != null ? satoshis2.hashCode() : 0)) * 31;
        BigDecimal bigDecimal = this.fiat;
        int hashCode4 = (hashCode3 + (bigDecimal != null ? bigDecimal.hashCode() : 0)) * 31;
        String str = this.currency;
        int hashCode5 = (hashCode4 + (str != null ? str.hashCode() : 0)) * 31;
        Coin coin2 = this.coin;
        int hashCode6 = (hashCode5 + (coin2 != null ? coin2.hashCode() : 0)) * 31;
        SlpId slpId = this.tokenId;
        if (slpId != null) {
            i = slpId.hashCode();
        }
        int i2 = (hashCode6 + i) * 31;
        long j = this.tokenAmount;
        return i2 + ((int) (j ^ (j >>> 32)));
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ReceivedTransaction(txId=");
        sb.append(this.txId);
        sb.append(", walletId=");
        sb.append(this.walletId);
        sb.append(", satoshis=");
        sb.append(this.satoshis);
        sb.append(", fiat=");
        sb.append(this.fiat);
        sb.append(", currency=");
        sb.append(this.currency);
        sb.append(", coin=");
        sb.append(this.coin);
        sb.append(", tokenId=");
        sb.append(this.tokenId);
        sb.append(", tokenAmount=");
        sb.append(this.tokenAmount);
        sb.append(")");
        return sb.toString();
    }

    public ReceivedTransaction(@NotNull TxId txId2, @NotNull WalletId walletId2, @NotNull Satoshis satoshis2, @Nullable BigDecimal bigDecimal, @NotNull String str, @NotNull Coin coin2, @NotNull SlpId slpId, long j) {
        Intrinsics.checkParameterIsNotNull(txId2, "txId");
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Intrinsics.checkParameterIsNotNull(satoshis2, "satoshis");
        Intrinsics.checkParameterIsNotNull(str, Param.CURRENCY);
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(slpId, "tokenId");
        this.txId = txId2;
        this.walletId = walletId2;
        this.satoshis = satoshis2;
        this.fiat = bigDecimal;
        this.currency = str;
        this.coin = coin2;
        this.tokenId = slpId;
        this.tokenAmount = j;
    }

    @NotNull
    public final TxId getTxId() {
        return this.txId;
    }

    @NotNull
    public final WalletId getWalletId() {
        return this.walletId;
    }

    @NotNull
    public final Satoshis getSatoshis() {
        return this.satoshis;
    }

    @Nullable
    public final BigDecimal getFiat() {
        return this.fiat;
    }

    @NotNull
    public final String getCurrency() {
        return this.currency;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    @NotNull
    public final SlpId getTokenId() {
        return this.tokenId;
    }

    public final long getTokenAmount() {
        return this.tokenAmount;
    }
}
