package org.bitcoinj.store;

import com.google.common.base.Objects;
import com.microsoft.appcenter.Constants;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.UTXO;

/* compiled from: MemoryFullPrunedBlockStore */
class StoredTransactionOutPoint {
    Sha256Hash hash;
    long index;

    StoredTransactionOutPoint(Sha256Hash sha256Hash, long j) {
        this.hash = sha256Hash;
        this.index = j;
    }

    StoredTransactionOutPoint(UTXO utxo) {
        this.hash = utxo.getHash();
        this.index = utxo.getIndex();
    }

    /* access modifiers changed from: 0000 */
    public Sha256Hash getHash() {
        return this.hash;
    }

    /* access modifiers changed from: 0000 */
    public long getIndex() {
        return this.index;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(getIndex()), getHash());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Stored transaction out point: ");
        sb.append(this.hash);
        sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        sb.append(this.index);
        return sb.toString();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StoredTransactionOutPoint storedTransactionOutPoint = (StoredTransactionOutPoint) obj;
        if (getIndex() != storedTransactionOutPoint.getIndex() || !Objects.equal(getHash(), storedTransactionOutPoint.getHash())) {
            z = false;
        }
        return z;
    }
}
