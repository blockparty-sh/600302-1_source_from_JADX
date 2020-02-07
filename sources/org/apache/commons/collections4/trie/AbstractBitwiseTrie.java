package org.apache.commons.collections4.trie;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Map.Entry;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.Trie;

public abstract class AbstractBitwiseTrie<K, V> extends AbstractMap<K, V> implements Trie<K, V>, Serializable {
    private static final long serialVersionUID = 5826987063535505652L;
    private final KeyAnalyzer<? super K> keyAnalyzer;

    static abstract class BasicEntry<K, V> implements Entry<K, V>, Serializable {
        private static final long serialVersionUID = -944364551314110330L;
        protected K key;
        protected V value;

        public BasicEntry(K k) {
            this.key = k;
        }

        public BasicEntry(K k, V v) {
            this.key = k;
            this.value = v;
        }

        public V setKeyValue(K k, V v) {
            this.key = k;
            return setValue(v);
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public V setValue(V v) {
            V v2 = this.value;
            this.value = v;
            return v2;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = getKey() == null ? 0 : getKey().hashCode();
            if (getValue() != null) {
                i = getValue().hashCode();
            }
            return hashCode ^ i;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            return AbstractBitwiseTrie.compare(this.key, entry.getKey()) && AbstractBitwiseTrie.compare(this.value, entry.getValue());
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.key);
            sb.append("=");
            sb.append(this.value);
            return sb.toString();
        }
    }

    /* access modifiers changed from: 0000 */
    public final K castKey(Object obj) {
        return obj;
    }

    public /* bridge */ /* synthetic */ MapIterator mapIterator() {
        return mapIterator();
    }

    protected AbstractBitwiseTrie(KeyAnalyzer<? super K> keyAnalyzer2) {
        if (keyAnalyzer2 != null) {
            this.keyAnalyzer = keyAnalyzer2;
            return;
        }
        throw new NullPointerException("keyAnalyzer");
    }

    /* access modifiers changed from: protected */
    public KeyAnalyzer<? super K> getKeyAnalyzer() {
        return this.keyAnalyzer;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Trie[");
        sb.append(size());
        sb.append("]={\n");
        for (Entry entry : entrySet()) {
            sb.append("  ");
            sb.append(entry);
            sb.append("\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    /* access modifiers changed from: 0000 */
    public final int lengthInBits(K k) {
        if (k == null) {
            return 0;
        }
        return this.keyAnalyzer.lengthInBits(k);
    }

    /* access modifiers changed from: 0000 */
    public final int bitsPerElement() {
        return this.keyAnalyzer.bitsPerElement();
    }

    /* access modifiers changed from: 0000 */
    public final boolean isBitSet(K k, int i, int i2) {
        if (k == null) {
            return false;
        }
        return this.keyAnalyzer.isBitSet(k, i, i2);
    }

    /* access modifiers changed from: 0000 */
    public final int bitIndex(K k, K k2) {
        return this.keyAnalyzer.bitIndex(k, 0, lengthInBits(k), k2, 0, lengthInBits(k2));
    }

    /* access modifiers changed from: 0000 */
    public final boolean compareKeys(K k, K k2) {
        boolean z = true;
        if (k == null) {
            if (k2 != null) {
                z = false;
            }
            return z;
        } else if (k2 == null) {
            return false;
        } else {
            if (this.keyAnalyzer.compare(k, k2) != 0) {
                z = false;
            }
            return z;
        }
    }

    static boolean compare(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        }
        return obj.equals(obj2);
    }
}
