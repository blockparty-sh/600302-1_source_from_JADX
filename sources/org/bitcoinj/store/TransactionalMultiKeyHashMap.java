package org.bitcoinj.store;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

/* compiled from: MemoryFullPrunedBlockStore */
class TransactionalMultiKeyHashMap<UniqueKeyType, MultiKeyType, ValueType> {
    HashMap<MultiKeyType, Set<UniqueKeyType>> mapKeys = new HashMap<>();
    TransactionalHashMap<UniqueKeyType, ValueType> mapValues = new TransactionalHashMap<>();

    public void BeginTransaction() {
        this.mapValues.beginDatabaseBatchWrite();
    }

    public void CommitTransaction() {
        this.mapValues.commitDatabaseBatchWrite();
    }

    public void AbortTransaction() {
        this.mapValues.abortDatabaseBatchWrite();
    }

    @Nullable
    public ValueType get(UniqueKeyType uniquekeytype) {
        return this.mapValues.get(uniquekeytype);
    }

    public void put(UniqueKeyType uniquekeytype, MultiKeyType multikeytype, ValueType valuetype) {
        this.mapValues.put(uniquekeytype, valuetype);
        Set set = (Set) this.mapKeys.get(multikeytype);
        if (set == null) {
            HashSet hashSet = new HashSet();
            hashSet.add(uniquekeytype);
            this.mapKeys.put(multikeytype, hashSet);
            return;
        }
        set.add(uniquekeytype);
    }

    @Nullable
    public ValueType removeByUniqueKey(UniqueKeyType uniquekeytype) {
        return this.mapValues.remove(uniquekeytype);
    }

    public void removeByMultiKey(MultiKeyType multikeytype) {
        Set<Object> set = (Set) this.mapKeys.remove(multikeytype);
        if (set != null) {
            for (Object removeByUniqueKey : set) {
                removeByUniqueKey(removeByUniqueKey);
            }
        }
    }
}
