package org.apache.commons.collections4.keyvalue;

import java.util.Map.Entry;

public abstract class AbstractMapEntry<K, V> extends AbstractKeyValue<K, V> implements Entry<K, V> {
    protected AbstractMapEntry(K k, V v) {
        super(k, v);
    }

    public V setValue(V v) {
        return super.setValue(v);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        if (getKey() != null ? getKey().equals(entry.getKey()) : entry.getKey() == null) {
            if (getValue() != null) {
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = getKey() == null ? 0 : getKey().hashCode();
        if (getValue() != null) {
            i = getValue().hashCode();
        }
        return hashCode ^ i;
    }
}
