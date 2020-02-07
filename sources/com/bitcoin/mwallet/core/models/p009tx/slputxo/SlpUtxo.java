package com.bitcoin.mwallet.core.models.p009tx.slputxo;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0019\u001a\u00020\nHÆ\u0003J;\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001H\u0002J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\b\u0010 \u001a\u00020\u0007H\u0016R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\b\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0016\u0010\t\u001a\u00020\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxo;", "", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "tokenAmount", "", "tokenType", "", "transactionType", "utxo", "Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;", "(Lcom/bitcoin/mwallet/core/models/slp/SlpId;JLjava/lang/String;Ljava/lang/String;Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;)V", "getTokenAmount", "()J", "getTokenId", "()Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "getTokenType", "()Ljava/lang/String;", "getTransactionType", "getUtxo", "()Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(primaryKeys = {"wallet_id", "token_id", "tx_id", "output_index"}, tableName = "slp_utxo")
/* renamed from: com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxo */
/* compiled from: SlpUtxo.kt */
public final class SlpUtxo {
    @ColumnInfo(name = "token_amount")
    private final long tokenAmount;
    @ColumnInfo(name = "token_id")
    @NotNull
    private final SlpId tokenId;
    @ColumnInfo(name = "token_type")
    @NotNull
    private final String tokenType;
    @ColumnInfo(name = "transaction_type")
    @NotNull
    private final String transactionType;
    @NotNull
    @Embedded
    private final Utxo utxo;

    @NotNull
    public static /* synthetic */ SlpUtxo copy$default(SlpUtxo slpUtxo, SlpId slpId, long j, String str, String str2, Utxo utxo2, int i, Object obj) {
        if ((i & 1) != 0) {
            slpId = slpUtxo.tokenId;
        }
        if ((i & 2) != 0) {
            j = slpUtxo.tokenAmount;
        }
        long j2 = j;
        if ((i & 4) != 0) {
            str = slpUtxo.tokenType;
        }
        String str3 = str;
        if ((i & 8) != 0) {
            str2 = slpUtxo.transactionType;
        }
        String str4 = str2;
        if ((i & 16) != 0) {
            utxo2 = slpUtxo.utxo;
        }
        return slpUtxo.copy(slpId, j2, str3, str4, utxo2);
    }

    @NotNull
    public final SlpId component1() {
        return this.tokenId;
    }

    public final long component2() {
        return this.tokenAmount;
    }

    @NotNull
    public final String component3() {
        return this.tokenType;
    }

    @NotNull
    public final String component4() {
        return this.transactionType;
    }

    @NotNull
    public final Utxo component5() {
        return this.utxo;
    }

    @NotNull
    public final SlpUtxo copy(@NotNull SlpId slpId, long j, @NotNull String str, @NotNull String str2, @NotNull Utxo utxo2) {
        Intrinsics.checkParameterIsNotNull(slpId, "tokenId");
        Intrinsics.checkParameterIsNotNull(str, "tokenType");
        Intrinsics.checkParameterIsNotNull(str2, "transactionType");
        Intrinsics.checkParameterIsNotNull(utxo2, "utxo");
        SlpUtxo slpUtxo = new SlpUtxo(slpId, j, str, str2, utxo2);
        return slpUtxo;
    }

    public int hashCode() {
        SlpId slpId = this.tokenId;
        int i = 0;
        int hashCode = (slpId != null ? slpId.hashCode() : 0) * 31;
        long j = this.tokenAmount;
        int i2 = (hashCode + ((int) (j ^ (j >>> 32)))) * 31;
        String str = this.tokenType;
        int hashCode2 = (i2 + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.transactionType;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        Utxo utxo2 = this.utxo;
        if (utxo2 != null) {
            i = utxo2.hashCode();
        }
        return hashCode3 + i;
    }

    public SlpUtxo(@NotNull SlpId slpId, long j, @NotNull String str, @NotNull String str2, @NotNull Utxo utxo2) {
        Intrinsics.checkParameterIsNotNull(slpId, "tokenId");
        Intrinsics.checkParameterIsNotNull(str, "tokenType");
        Intrinsics.checkParameterIsNotNull(str2, "transactionType");
        Intrinsics.checkParameterIsNotNull(utxo2, "utxo");
        this.tokenId = slpId;
        this.tokenAmount = j;
        this.tokenType = str;
        this.transactionType = str2;
        this.utxo = utxo2;
    }

    @NotNull
    public final SlpId getTokenId() {
        return this.tokenId;
    }

    public final long getTokenAmount() {
        return this.tokenAmount;
    }

    @NotNull
    public final String getTokenType() {
        return this.tokenType;
    }

    @NotNull
    public final String getTransactionType() {
        return this.transactionType;
    }

    @NotNull
    public final Utxo getUtxo() {
        return this.utxo;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            SlpUtxo slpUtxo = (SlpUtxo) obj;
            if (!(!Intrinsics.areEqual((Object) this.tokenId, (Object) slpUtxo.tokenId)) && this.tokenAmount == slpUtxo.tokenAmount && !(!Intrinsics.areEqual((Object) this.utxo, (Object) slpUtxo.utxo))) {
                return true;
            }
            return false;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxo");
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SlpUtxo(TokenId=");
        sb.append(this.tokenId);
        sb.append(", token_amount=");
        sb.append(this.tokenAmount);
        sb.append(", tokenType=");
        sb.append(this.tokenType);
        sb.append(", utxo=");
        sb.append(Utxo.Companion);
        sb.append(')');
        return sb.toString();
    }
}
