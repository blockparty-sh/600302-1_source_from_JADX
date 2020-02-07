package org.bitcoinj.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.annotation.Nullable;

/* compiled from: MemoryFullPrunedBlockStore */
class TransactionalHashMap<KeyType, ValueType> {
    private ThreadLocal<Boolean> inTransaction = new ThreadLocal<>();
    HashMap<KeyType, ValueType> map = new HashMap<>();
    ThreadLocal<HashMap<KeyType, ValueType>> tempMap = new ThreadLocal<>();
    ThreadLocal<HashSet<KeyType>> tempSetRemoved = new ThreadLocal<>();

    public void beginDatabaseBatchWrite() {
        this.inTransaction.set(Boolean.valueOf(true));
    }

    public void commitDatabaseBatchWrite() {
        if (this.tempSetRemoved.get() != null) {
            Iterator it = ((HashSet) this.tempSetRemoved.get()).iterator();
            while (it.hasNext()) {
                this.map.remove(it.next());
            }
        }
        if (this.tempMap.get() != null) {
            for (Entry entry : ((HashMap) this.tempMap.get()).entrySet()) {
                this.map.put(entry.getKey(), entry.getValue());
            }
        }
        abortDatabaseBatchWrite();
    }

    public void abortDatabaseBatchWrite() {
        this.inTransaction.set(Boolean.valueOf(false));
        this.tempSetRemoved.remove();
        this.tempMap.remove();
    }

    @Nullable
    public ValueType get(KeyType keytype) {
        if (Boolean.TRUE.equals(this.inTransaction.get())) {
            if (this.tempMap.get() != null) {
                ValueType valuetype = ((HashMap) this.tempMap.get()).get(keytype);
                if (valuetype != null) {
                    return valuetype;
                }
            }
            if (this.tempSetRemoved.get() != null && ((HashSet) this.tempSetRemoved.get()).contains(keytype)) {
                return null;
            }
        }
        return this.map.get(keytype);
    }

    public List<ValueType> values() {
        ArrayList arrayList = new ArrayList();
        for (Object obj : this.map.keySet()) {
            arrayList.add(get(obj));
        }
        return arrayList;
    }

    public void put(KeyType keytype, ValueType valuetype) {
        if (Boolean.TRUE.equals(this.inTransaction.get())) {
            if (this.tempSetRemoved.get() != null) {
                ((HashSet) this.tempSetRemoved.get()).remove(keytype);
            }
            if (this.tempMap.get() == null) {
                this.tempMap.set(new HashMap());
            }
            ((HashMap) this.tempMap.get()).put(keytype, valuetype);
            return;
        }
        this.map.put(keytype, valuetype);
    }

    @Nullable
    public ValueType remove(KeyType keytype) {
        if (!Boolean.TRUE.equals(this.inTransaction.get())) {
            return this.map.remove(keytype);
        }
        ValueType valuetype = this.map.get(keytype);
        if (valuetype != null) {
            if (this.tempSetRemoved.get() == null) {
                this.tempSetRemoved.set(new HashSet());
            }
            ((HashSet) this.tempSetRemoved.get()).add(keytype);
        }
        if (this.tempMap.get() != null) {
            ValueType remove = ((HashMap) this.tempMap.get()).remove(keytype);
            if (remove != null) {
                return remove;
            }
        }
        return valuetype;
    }
}
