package org.apache.commons.collections4.keyvalue;

import java.io.Serializable;
import java.util.Arrays;

public class MultiKey<K> implements Serializable {
    private static final long serialVersionUID = 4465448607415788805L;
    private transient int hashCode;
    private final K[] keys;

    public MultiKey(K k, K k2) {
        this((K[]) new Object[]{k, k2}, false);
    }

    public MultiKey(K k, K k2, K k3) {
        this((K[]) new Object[]{k, k2, k3}, false);
    }

    public MultiKey(K k, K k2, K k3, K k4) {
        this((K[]) new Object[]{k, k2, k3, k4}, false);
    }

    public MultiKey(K k, K k2, K k3, K k4, K k5) {
        this((K[]) new Object[]{k, k2, k3, k4, k5}, false);
    }

    public MultiKey(K[] kArr) {
        this(kArr, true);
    }

    public MultiKey(K[] kArr, boolean z) {
        if (kArr != null) {
            if (z) {
                this.keys = (Object[]) kArr.clone();
            } else {
                this.keys = kArr;
            }
            calculateHashCode(kArr);
            return;
        }
        throw new IllegalArgumentException("The array of keys must not be null");
    }

    public K[] getKeys() {
        return (Object[]) this.keys.clone();
    }

    public K getKey(int i) {
        return this.keys[i];
    }

    public int size() {
        return this.keys.length;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MultiKey)) {
            return false;
        }
        return Arrays.equals(this.keys, ((MultiKey) obj).keys);
    }

    public int hashCode() {
        return this.hashCode;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("MultiKey");
        sb.append(Arrays.toString(this.keys));
        return sb.toString();
    }

    private void calculateHashCode(Object[] objArr) {
        int i = 0;
        for (Object obj : objArr) {
            if (obj != null) {
                i ^= obj.hashCode();
            }
        }
        this.hashCode = i;
    }

    /* access modifiers changed from: protected */
    public Object readResolve() {
        calculateHashCode(this.keys);
        return this;
    }
}
