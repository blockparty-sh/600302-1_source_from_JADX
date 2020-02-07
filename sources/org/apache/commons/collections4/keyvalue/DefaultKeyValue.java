package org.apache.commons.collections4.keyvalue;

import java.util.Map.Entry;
import org.apache.commons.collections4.KeyValue;

public class DefaultKeyValue<K, V> extends AbstractKeyValue<K, V> {
    public DefaultKeyValue() {
        super(null, null);
    }

    public DefaultKeyValue(K k, V v) {
        super(k, v);
    }

    public DefaultKeyValue(KeyValue<? extends K, ? extends V> keyValue) {
        super(keyValue.getKey(), keyValue.getValue());
    }

    public DefaultKeyValue(Entry<? extends K, ? extends V> entry) {
        super(entry.getKey(), entry.getValue());
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=K, code=org.apache.commons.collections4.keyvalue.DefaultKeyValue, for r2v0, types: [java.lang.Object, K, org.apache.commons.collections4.keyvalue.DefaultKeyValue] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public K setKey(org.apache.commons.collections4.keyvalue.DefaultKeyValue r2) {
        /*
            r1 = this;
            if (r2 == r1) goto L_0x0007
            java.lang.Object r2 = super.setKey(r2)
            return r2
        L_0x0007:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "DefaultKeyValue may not contain itself as a key."
            r2.<init>(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.keyvalue.DefaultKeyValue.setKey(java.lang.Object):java.lang.Object");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=V, code=org.apache.commons.collections4.keyvalue.DefaultKeyValue, for r2v0, types: [V, java.lang.Object, org.apache.commons.collections4.keyvalue.DefaultKeyValue] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public V setValue(org.apache.commons.collections4.keyvalue.DefaultKeyValue r2) {
        /*
            r1 = this;
            if (r2 == r1) goto L_0x0007
            java.lang.Object r2 = super.setValue(r2)
            return r2
        L_0x0007:
            java.lang.IllegalArgumentException r2 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "DefaultKeyValue may not contain itself as a value."
            r2.<init>(r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.keyvalue.DefaultKeyValue.setValue(java.lang.Object):java.lang.Object");
    }

    public Entry<K, V> toMapEntry() {
        return new DefaultMapEntry((KeyValue<? extends K, ? extends V>) this);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof DefaultKeyValue)) {
            return false;
        }
        DefaultKeyValue defaultKeyValue = (DefaultKeyValue) obj;
        if (getKey() != null ? getKey().equals(defaultKeyValue.getKey()) : defaultKeyValue.getKey() == null) {
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
