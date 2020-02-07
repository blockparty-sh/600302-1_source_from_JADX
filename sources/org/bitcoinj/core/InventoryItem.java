package org.bitcoinj.core;

import com.google.common.base.Objects;

public class InventoryItem {
    static final int MESSAGE_LENGTH = 36;
    public final Sha256Hash hash;
    public final C3406Type type;

    /* renamed from: org.bitcoinj.core.InventoryItem$Type */
    public enum C3406Type {
        Error,
        Transaction,
        Block,
        FilteredBlock
    }

    public InventoryItem(C3406Type type2, Sha256Hash sha256Hash) {
        this.type = type2;
        this.hash = sha256Hash;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.type);
        sb.append(": ");
        sb.append(this.hash);
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
        InventoryItem inventoryItem = (InventoryItem) obj;
        if (this.type != inventoryItem.type || !this.hash.equals(inventoryItem.hash)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(this.type, this.hash);
    }
}
