package org.apache.commons.collections4.keyvalue;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.collections4.KeyValue;

public class TiedMapEntry<K, V> implements Entry<K, V>, KeyValue<K, V>, Serializable {
    private static final long serialVersionUID = -8453869361373831205L;
    private final K key;
    private final Map<K, V> map;

    public TiedMapEntry(Map<K, V> map2, K k) {
        this.map = map2;
        this.key = k;
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.map.get(this.key);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=V, code=org.apache.commons.collections4.keyvalue.TiedMapEntry, for r3v0, types: [V, java.lang.Object, org.apache.commons.collections4.keyvalue.TiedMapEntry] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public V setValue(org.apache.commons.collections4.keyvalue.TiedMapEntry r3) {
        /*
            r2 = this;
            if (r3 == r2) goto L_0x000b
            java.util.Map<K, V> r0 = r2.map
            K r1 = r2.key
            java.lang.Object r3 = r0.put(r1, r3)
            return r3
        L_0x000b:
            java.lang.IllegalArgumentException r3 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Cannot set value to this map entry"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.keyvalue.TiedMapEntry.setValue(java.lang.Object):java.lang.Object");
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
        Object value = getValue();
        K k = this.key;
        if (k != null ? k.equals(entry.getKey()) : entry.getKey() == null) {
            if (value != null) {
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        Object value = getValue();
        int i = 0;
        int hashCode = getKey() == null ? 0 : getKey().hashCode();
        if (value != null) {
            i = value.hashCode();
        }
        return hashCode ^ i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getKey());
        sb.append("=");
        sb.append(getValue());
        return sb.toString();
    }
}
