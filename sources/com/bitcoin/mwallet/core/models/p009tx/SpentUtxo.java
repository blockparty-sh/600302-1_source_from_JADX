package com.bitcoin.mwallet.core.models.p009tx;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.bitcoin.bitcoink.p008tx.TxId;
import com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001H\u0002J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/tx/SpentUtxo;", "", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "outputIndex", "", "(Lcom/bitcoin/bitcoink/tx/TxId;I)V", "getOutputIndex", "()I", "getTxId", "()Lcom/bitcoin/bitcoink/tx/TxId;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(primaryKeys = {"tx_id", "output_index"}, tableName = "spent_utxo")
/* renamed from: com.bitcoin.mwallet.core.models.tx.SpentUtxo */
/* compiled from: SpentUtxo.kt */
public final class SpentUtxo {
    @ColumnInfo(name = "output_index")
    private final int outputIndex;
    @ColumnInfo(name = "tx_id")
    @NotNull
    private final TxId txId;

    @NotNull
    public static /* synthetic */ SpentUtxo copy$default(SpentUtxo spentUtxo, TxId txId2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            txId2 = spentUtxo.txId;
        }
        if ((i2 & 2) != 0) {
            i = spentUtxo.outputIndex;
        }
        return spentUtxo.copy(txId2, i);
    }

    @NotNull
    public final TxId component1() {
        return this.txId;
    }

    public final int component2() {
        return this.outputIndex;
    }

    @NotNull
    public final SpentUtxo copy(@NotNull TxId txId2, int i) {
        Intrinsics.checkParameterIsNotNull(txId2, "txId");
        return new SpentUtxo(txId2, i);
    }

    public int hashCode() {
        TxId txId2 = this.txId;
        return ((txId2 != null ? txId2.hashCode() : 0) * 31) + this.outputIndex;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SpentUtxo(txId=");
        sb.append(this.txId);
        sb.append(", outputIndex=");
        sb.append(this.outputIndex);
        sb.append(")");
        return sb.toString();
    }

    public SpentUtxo(@NotNull TxId txId2, int i) {
        Intrinsics.checkParameterIsNotNull(txId2, "txId");
        this.txId = txId2;
        this.outputIndex = i;
    }

    @NotNull
    public final TxId getTxId() {
        return this.txId;
    }

    public final int getOutputIndex() {
        return this.outputIndex;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            Utxo utxo = (Utxo) obj;
            if (!(!Intrinsics.areEqual((Object) this.txId, (Object) utxo.getTxId())) && this.outputIndex == utxo.getOutputIndex()) {
                return true;
            }
            return false;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.mwallet.core.models.tx.utxo.Utxo");
    }
}
