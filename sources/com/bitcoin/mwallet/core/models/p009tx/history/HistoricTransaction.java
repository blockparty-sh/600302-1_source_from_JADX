package com.bitcoin.mwallet.core.models.p009tx.history;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.p009tx.TxAction;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import com.bitcoin.mwallet.core.models.wallet.WalletId;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0010\u000b\n\u0002\b\u0005\b\b\u0018\u00002\u00020\u0001Bg\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u000b\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0007¢\u0006\u0002\u0010\u0017J\t\u0010-\u001a\u00020\u0003HÆ\u0003J\t\u0010.\u001a\u00020\u000bHÆ\u0003J\t\u0010/\u001a\u00020\u0015HÆ\u0003J\t\u00100\u001a\u00020\u0007HÆ\u0003J\t\u00101\u001a\u00020\u0005HÆ\u0003J\t\u00102\u001a\u00020\u0007HÆ\u0003J\t\u00103\u001a\u00020\tHÆ\u0003J\t\u00104\u001a\u00020\u000bHÆ\u0003J\t\u00105\u001a\u00020\rHÆ\u0003J\t\u00106\u001a\u00020\u000fHÆ\u0003J\t\u00107\u001a\u00020\u0011HÆ\u0003J\u000b\u00108\u001a\u0004\u0018\u00010\u0011HÆ\u0003J\u0001\u00109\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u000b2\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0007HÆ\u0001J\u0013\u0010:\u001a\u00020;2\b\u0010<\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010=\u001a\u00020\tHÖ\u0001J\u0006\u0010>\u001a\u00020\u0011J\t\u0010?\u001a\u00020\u0011HÖ\u0001R\u0016\u0010\f\u001a\u00020\r8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0016\u0010\u000e\u001a\u00020\u000f8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0016\u0010\b\u001a\u00020\t8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\u0013\u001a\u00020\u000b8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0018\u0010\u0012\u001a\u0004\u0018\u00010\u00118\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001fR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0016\u0010\u0010\u001a\u00020\u00118\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010!R\u0016\u0010\u0016\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010$R\u0016\u0010\u0014\u001a\u00020\u00158\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,¨\u0006@"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/tx/history/HistoricTransaction;", "", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "walletId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "timestamp", "", "confirmations", "", "satoshis", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "action", "Lcom/bitcoin/mwallet/core/models/tx/TxAction;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "toAddress", "", "note", "fees", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "tokenAmount", "(Lcom/bitcoin/bitcoink/tx/TxId;Lcom/bitcoin/mwallet/core/models/wallet/WalletId;JILcom/bitcoin/bitcoink/tx/Satoshis;Lcom/bitcoin/mwallet/core/models/tx/TxAction;Lcom/bitcoin/mwallet/core/models/Coin;Ljava/lang/String;Ljava/lang/String;Lcom/bitcoin/bitcoink/tx/Satoshis;Lcom/bitcoin/mwallet/core/models/slp/SlpId;J)V", "getAction", "()Lcom/bitcoin/mwallet/core/models/tx/TxAction;", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "getConfirmations", "()I", "getFees", "()Lcom/bitcoin/bitcoink/tx/Satoshis;", "getNote", "()Ljava/lang/String;", "getSatoshis", "getTimestamp", "()J", "getToAddress", "getTokenAmount", "getTokenId", "()Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "getTxId", "()Lcom/bitcoin/bitcoink/tx/TxId;", "getWalletId", "()Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "toLocalTimeString", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(primaryKeys = {"tx_id", "wallet_id"}, tableName = "tx_history")
/* renamed from: com.bitcoin.mwallet.core.models.tx.history.HistoricTransaction */
/* compiled from: HistoricTransaction.kt */
public final class HistoricTransaction {
    @ColumnInfo(name = "action")
    @NotNull
    private final TxAction action;
    @ColumnInfo(name = "coin")
    @NotNull
    private final Coin coin;
    @ColumnInfo(name = "confirmations")
    private final int confirmations;
    @ColumnInfo(name = "fees")
    @NotNull
    private final Satoshis fees;
    @ColumnInfo(name = "note")
    @Nullable
    private final String note;
    @ColumnInfo(name = "satoshis")
    @NotNull
    private final Satoshis satoshis;
    @ColumnInfo(index = true, name = "timestamp")
    private final long timestamp;
    @ColumnInfo(name = "to_address")
    @NotNull
    private final String toAddress;
    @ColumnInfo(name = "token_amount")
    private final long tokenAmount;
    @ColumnInfo(name = "token_id")
    @NotNull
    private final SlpId tokenId;
    @ColumnInfo(name = "tx_id")
    @NotNull
    private final TxId txId;
    @ColumnInfo(index = true, name = "wallet_id")
    @NotNull
    private final WalletId walletId;

    @NotNull
    public static /* synthetic */ HistoricTransaction copy$default(HistoricTransaction historicTransaction, TxId txId2, WalletId walletId2, long j, int i, Satoshis satoshis2, TxAction txAction, Coin coin2, String str, String str2, Satoshis satoshis3, SlpId slpId, long j2, int i2, Object obj) {
        HistoricTransaction historicTransaction2 = historicTransaction;
        int i3 = i2;
        return historicTransaction.copy((i3 & 1) != 0 ? historicTransaction2.txId : txId2, (i3 & 2) != 0 ? historicTransaction2.walletId : walletId2, (i3 & 4) != 0 ? historicTransaction2.timestamp : j, (i3 & 8) != 0 ? historicTransaction2.confirmations : i, (i3 & 16) != 0 ? historicTransaction2.satoshis : satoshis2, (i3 & 32) != 0 ? historicTransaction2.action : txAction, (i3 & 64) != 0 ? historicTransaction2.coin : coin2, (i3 & 128) != 0 ? historicTransaction2.toAddress : str, (i3 & 256) != 0 ? historicTransaction2.note : str2, (i3 & 512) != 0 ? historicTransaction2.fees : satoshis3, (i3 & 1024) != 0 ? historicTransaction2.tokenId : slpId, (i3 & 2048) != 0 ? historicTransaction2.tokenAmount : j2);
    }

    @NotNull
    public final TxId component1() {
        return this.txId;
    }

    @NotNull
    public final Satoshis component10() {
        return this.fees;
    }

    @NotNull
    public final SlpId component11() {
        return this.tokenId;
    }

    public final long component12() {
        return this.tokenAmount;
    }

    @NotNull
    public final WalletId component2() {
        return this.walletId;
    }

    public final long component3() {
        return this.timestamp;
    }

    public final int component4() {
        return this.confirmations;
    }

    @NotNull
    public final Satoshis component5() {
        return this.satoshis;
    }

    @NotNull
    public final TxAction component6() {
        return this.action;
    }

    @NotNull
    public final Coin component7() {
        return this.coin;
    }

    @NotNull
    public final String component8() {
        return this.toAddress;
    }

    @Nullable
    public final String component9() {
        return this.note;
    }

    @NotNull
    public final HistoricTransaction copy(@NotNull TxId txId2, @NotNull WalletId walletId2, long j, int i, @NotNull Satoshis satoshis2, @NotNull TxAction txAction, @NotNull Coin coin2, @NotNull String str, @Nullable String str2, @NotNull Satoshis satoshis3, @NotNull SlpId slpId, long j2) {
        TxId txId3 = txId2;
        Intrinsics.checkParameterIsNotNull(txId3, "txId");
        WalletId walletId3 = walletId2;
        Intrinsics.checkParameterIsNotNull(walletId3, "walletId");
        Satoshis satoshis4 = satoshis2;
        Intrinsics.checkParameterIsNotNull(satoshis4, "satoshis");
        TxAction txAction2 = txAction;
        Intrinsics.checkParameterIsNotNull(txAction2, "action");
        Coin coin3 = coin2;
        Intrinsics.checkParameterIsNotNull(coin3, "coin");
        String str3 = str;
        Intrinsics.checkParameterIsNotNull(str3, "toAddress");
        Satoshis satoshis5 = satoshis3;
        Intrinsics.checkParameterIsNotNull(satoshis5, "fees");
        SlpId slpId2 = slpId;
        Intrinsics.checkParameterIsNotNull(slpId2, "tokenId");
        HistoricTransaction historicTransaction = new HistoricTransaction(txId3, walletId3, j, i, satoshis4, txAction2, coin3, str3, str2, satoshis5, slpId2, j2);
        return historicTransaction;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof HistoricTransaction) {
                HistoricTransaction historicTransaction = (HistoricTransaction) obj;
                if (Intrinsics.areEqual((Object) this.txId, (Object) historicTransaction.txId) && Intrinsics.areEqual((Object) this.walletId, (Object) historicTransaction.walletId)) {
                    if (this.timestamp == historicTransaction.timestamp) {
                        if ((this.confirmations == historicTransaction.confirmations) && Intrinsics.areEqual((Object) this.satoshis, (Object) historicTransaction.satoshis) && Intrinsics.areEqual((Object) this.action, (Object) historicTransaction.action) && Intrinsics.areEqual((Object) this.coin, (Object) historicTransaction.coin) && Intrinsics.areEqual((Object) this.toAddress, (Object) historicTransaction.toAddress) && Intrinsics.areEqual((Object) this.note, (Object) historicTransaction.note) && Intrinsics.areEqual((Object) this.fees, (Object) historicTransaction.fees) && Intrinsics.areEqual((Object) this.tokenId, (Object) historicTransaction.tokenId)) {
                            if (this.tokenAmount == historicTransaction.tokenAmount) {
                                return true;
                            }
                        }
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
        long j = this.timestamp;
        int i2 = (((hashCode2 + ((int) (j ^ (j >>> 32)))) * 31) + this.confirmations) * 31;
        Satoshis satoshis2 = this.satoshis;
        int hashCode3 = (i2 + (satoshis2 != null ? satoshis2.hashCode() : 0)) * 31;
        TxAction txAction = this.action;
        int hashCode4 = (hashCode3 + (txAction != null ? txAction.hashCode() : 0)) * 31;
        Coin coin2 = this.coin;
        int hashCode5 = (hashCode4 + (coin2 != null ? coin2.hashCode() : 0)) * 31;
        String str = this.toAddress;
        int hashCode6 = (hashCode5 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.note;
        int hashCode7 = (hashCode6 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Satoshis satoshis3 = this.fees;
        int hashCode8 = (hashCode7 + (satoshis3 != null ? satoshis3.hashCode() : 0)) * 31;
        SlpId slpId = this.tokenId;
        if (slpId != null) {
            i = slpId.hashCode();
        }
        int i3 = (hashCode8 + i) * 31;
        long j2 = this.tokenAmount;
        return i3 + ((int) (j2 ^ (j2 >>> 32)));
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("HistoricTransaction(txId=");
        sb.append(this.txId);
        sb.append(", walletId=");
        sb.append(this.walletId);
        sb.append(", timestamp=");
        sb.append(this.timestamp);
        sb.append(", confirmations=");
        sb.append(this.confirmations);
        sb.append(", satoshis=");
        sb.append(this.satoshis);
        sb.append(", action=");
        sb.append(this.action);
        sb.append(", coin=");
        sb.append(this.coin);
        sb.append(", toAddress=");
        sb.append(this.toAddress);
        sb.append(", note=");
        sb.append(this.note);
        sb.append(", fees=");
        sb.append(this.fees);
        sb.append(", tokenId=");
        sb.append(this.tokenId);
        sb.append(", tokenAmount=");
        sb.append(this.tokenAmount);
        sb.append(")");
        return sb.toString();
    }

    public HistoricTransaction(@NotNull TxId txId2, @NotNull WalletId walletId2, long j, int i, @NotNull Satoshis satoshis2, @NotNull TxAction txAction, @NotNull Coin coin2, @NotNull String str, @Nullable String str2, @NotNull Satoshis satoshis3, @NotNull SlpId slpId, long j2) {
        Intrinsics.checkParameterIsNotNull(txId2, "txId");
        Intrinsics.checkParameterIsNotNull(walletId2, "walletId");
        Intrinsics.checkParameterIsNotNull(satoshis2, "satoshis");
        Intrinsics.checkParameterIsNotNull(txAction, "action");
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        Intrinsics.checkParameterIsNotNull(str, "toAddress");
        Intrinsics.checkParameterIsNotNull(satoshis3, "fees");
        Intrinsics.checkParameterIsNotNull(slpId, "tokenId");
        this.txId = txId2;
        this.walletId = walletId2;
        this.timestamp = j;
        this.confirmations = i;
        this.satoshis = satoshis2;
        this.action = txAction;
        this.coin = coin2;
        this.toAddress = str;
        this.note = str2;
        this.fees = satoshis3;
        this.tokenId = slpId;
        this.tokenAmount = j2;
    }

    @NotNull
    public final TxId getTxId() {
        return this.txId;
    }

    @NotNull
    public final WalletId getWalletId() {
        return this.walletId;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final int getConfirmations() {
        return this.confirmations;
    }

    @NotNull
    public final Satoshis getSatoshis() {
        return this.satoshis;
    }

    @NotNull
    public final TxAction getAction() {
        return this.action;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    @NotNull
    public final String getToAddress() {
        return this.toAddress;
    }

    @Nullable
    public final String getNote() {
        return this.note;
    }

    @NotNull
    public final Satoshis getFees() {
        return this.fees;
    }

    @NotNull
    public final SlpId getTokenId() {
        return this.tokenId;
    }

    public final long getTokenAmount() {
        return this.tokenAmount;
    }

    @NotNull
    public final String toLocalTimeString() {
        Calendar instance = Calendar.getInstance();
        Intrinsics.checkExpressionValueIsNotNull(instance, "Calendar.getInstance()");
        TimeZone timeZone = instance.getTimeZone();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm");
        simpleDateFormat.setTimeZone(timeZone);
        String format = simpleDateFormat.format(new Date(this.timestamp));
        Intrinsics.checkExpressionValueIsNotNull(format, "dateFormatter.format(Date(timestamp))");
        return format;
    }
}
