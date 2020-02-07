package org.apache.commons.collections4.map;

import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import org.apache.commons.collections4.KeyValue;

public final class StaticBucketMap<K, V> extends AbstractIterableMap<K, V> {
    private static final int DEFAULT_BUCKETS = 255;
    /* access modifiers changed from: private */
    public final Node<K, V>[] buckets;
    /* access modifiers changed from: private */
    public final Lock[] locks;

    private class BaseIterator {
        private int bucket;
        private final ArrayList<Entry<K, V>> current;
        private Entry<K, V> last;

        private BaseIterator() {
            this.current = new ArrayList<>();
        }

        public boolean hasNext() {
            if (this.current.size() > 0) {
                return true;
            }
            while (this.bucket < StaticBucketMap.this.buckets.length) {
                synchronized (StaticBucketMap.this.locks[this.bucket]) {
                    for (Node<K, V> node = StaticBucketMap.this.buckets[this.bucket]; node != null; node = node.next) {
                        this.current.add(node);
                    }
                    this.bucket++;
                    if (this.current.size() > 0) {
                        return true;
                    }
                }
            }
            return false;
        }

        /* access modifiers changed from: protected */
        public Entry<K, V> nextEntry() {
            if (hasNext()) {
                ArrayList<Entry<K, V>> arrayList = this.current;
                this.last = (Entry) arrayList.remove(arrayList.size() - 1);
                return this.last;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            Entry<K, V> entry = this.last;
            if (entry != null) {
                StaticBucketMap.this.remove(entry.getKey());
                this.last = null;
                return;
            }
            throw new IllegalStateException();
        }
    }

    private class EntryIterator extends BaseIterator implements Iterator<Entry<K, V>> {
        private EntryIterator() {
            super();
        }

        public Entry<K, V> next() {
            return nextEntry();
        }
    }

    private class EntrySet extends AbstractSet<Entry<K, V>> {
        private EntrySet() {
        }

        public int size() {
            return StaticBucketMap.this.size();
        }

        public void clear() {
            StaticBucketMap.this.clear();
        }

        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        public boolean contains(Object obj) {
            Entry entry = (Entry) obj;
            int access$900 = StaticBucketMap.this.getHash(entry.getKey());
            synchronized (StaticBucketMap.this.locks[access$900]) {
                for (Node<K, V> node = StaticBucketMap.this.buckets[access$900]; node != null; node = node.next) {
                    if (node.equals(entry)) {
                        return true;
                    }
                }
                return false;
            }
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            int access$900 = StaticBucketMap.this.getHash(entry.getKey());
            synchronized (StaticBucketMap.this.locks[access$900]) {
                for (Node<K, V> node = StaticBucketMap.this.buckets[access$900]; node != null; node = node.next) {
                    if (node.equals(entry)) {
                        StaticBucketMap.this.remove(node.getKey());
                        return true;
                    }
                }
                return false;
            }
        }
    }

    private class KeyIterator extends BaseIterator implements Iterator<K> {
        private KeyIterator() {
            super();
        }

        public K next() {
            return nextEntry().getKey();
        }
    }

    private class KeySet extends AbstractSet<K> {
        private KeySet() {
        }

        public int size() {
            return StaticBucketMap.this.size();
        }

        public void clear() {
            StaticBucketMap.this.clear();
        }

        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        public boolean contains(Object obj) {
            return StaticBucketMap.this.containsKey(obj);
        }

        public boolean remove(Object obj) {
            int access$900 = StaticBucketMap.this.getHash(obj);
            synchronized (StaticBucketMap.this.locks[access$900]) {
                Node<K, V> node = StaticBucketMap.this.buckets[access$900];
                while (node != null) {
                    Object key = node.getKey();
                    if (key != obj) {
                        if (key == null || !key.equals(obj)) {
                            node = node.next;
                        }
                    }
                    StaticBucketMap.this.remove(key);
                    return true;
                }
                return false;
            }
        }
    }

    private static final class Lock {
        public int size;

        private Lock() {
        }
    }

    private static final class Node<K, V> implements Entry<K, V>, KeyValue<K, V> {
        protected K key;
        protected Node<K, V> next;
        protected V value;

        private Node() {
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public int hashCode() {
            K k = this.key;
            int i = 0;
            int hashCode = k == null ? 0 : k.hashCode();
            V v = this.value;
            if (v != null) {
                i = v.hashCode();
            }
            return hashCode ^ i;
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
            K k = this.key;
            if (k != null ? k.equals(entry.getKey()) : entry.getKey() == null) {
                V v = this.value;
                if (v != null) {
                }
            }
            z = false;
            return z;
        }

        public V setValue(V v) {
            V v2 = this.value;
            this.value = v;
            return v2;
        }
    }

    private class ValueIterator extends BaseIterator implements Iterator<V> {
        private ValueIterator() {
            super();
        }

        public V next() {
            return nextEntry().getValue();
        }
    }

    private class Values extends AbstractCollection<V> {
        private Values() {
        }

        public int size() {
            return StaticBucketMap.this.size();
        }

        public void clear() {
            StaticBucketMap.this.clear();
        }

        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }

    public StaticBucketMap() {
        this(255);
    }

    public StaticBucketMap(int i) {
        int max = Math.max(17, i);
        if (max % 2 == 0) {
            max--;
        }
        this.buckets = new Node[max];
        this.locks = new Lock[max];
        for (int i2 = 0; i2 < max; i2++) {
            this.locks[i2] = new Lock();
        }
    }

    /* access modifiers changed from: private */
    public int getHash(Object obj) {
        if (obj == null) {
            return 0;
        }
        int hashCode = obj.hashCode();
        int i = hashCode + (~(hashCode << 15));
        int i2 = i ^ (i >>> 10);
        int i3 = i2 + (i2 << 3);
        int i4 = i3 ^ (i3 >>> 6);
        int i5 = i4 + (~(i4 << 11));
        int length = (i5 ^ (i5 >>> 16)) % this.buckets.length;
        if (length < 0) {
            length *= -1;
        }
        return length;
    }

    public int size() {
        int i = 0;
        for (int i2 = 0; i2 < this.buckets.length; i2++) {
            synchronized (this.locks[i2]) {
                i += this.locks[i2].size;
            }
        }
        return i;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public V get(Object obj) {
        int hash = getHash(obj);
        synchronized (this.locks[hash]) {
            Node<K, V> node = this.buckets[hash];
            while (node != null) {
                if (node.key != obj) {
                    if (node.key == null || !node.key.equals(obj)) {
                        node = node.next;
                    }
                }
                V v = node.value;
                return v;
            }
            return null;
        }
    }

    public boolean containsKey(Object obj) {
        int hash = getHash(obj);
        synchronized (this.locks[hash]) {
            Node<K, V> node = this.buckets[hash];
            while (node != null) {
                if (node.key != obj) {
                    if (node.key == null || !node.key.equals(obj)) {
                        node = node.next;
                    }
                }
                return true;
            }
            return false;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0028, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x002a, code lost:
        r1 = r1 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean containsValue(java.lang.Object r6) {
        /*
            r5 = this;
            r0 = 0
            r1 = 0
        L_0x0002:
            org.apache.commons.collections4.map.StaticBucketMap$Node<K, V>[] r2 = r5.buckets
            int r2 = r2.length
            if (r1 >= r2) goto L_0x0030
            org.apache.commons.collections4.map.StaticBucketMap$Lock[] r2 = r5.locks
            r2 = r2[r1]
            monitor-enter(r2)
            org.apache.commons.collections4.map.StaticBucketMap$Node<K, V>[] r3 = r5.buckets     // Catch:{ all -> 0x002d }
            r3 = r3[r1]     // Catch:{ all -> 0x002d }
        L_0x0010:
            if (r3 == 0) goto L_0x0029
            V r4 = r3.value     // Catch:{ all -> 0x002d }
            if (r4 == r6) goto L_0x0026
            V r4 = r3.value     // Catch:{ all -> 0x002d }
            if (r4 == 0) goto L_0x0023
            V r4 = r3.value     // Catch:{ all -> 0x002d }
            boolean r4 = r4.equals(r6)     // Catch:{ all -> 0x002d }
            if (r4 == 0) goto L_0x0023
            goto L_0x0026
        L_0x0023:
            org.apache.commons.collections4.map.StaticBucketMap$Node<K, V> r3 = r3.next     // Catch:{ all -> 0x002d }
            goto L_0x0010
        L_0x0026:
            monitor-exit(r2)     // Catch:{ all -> 0x002d }
            r6 = 1
            return r6
        L_0x0029:
            monitor-exit(r2)     // Catch:{ all -> 0x002d }
            int r1 = r1 + 1
            goto L_0x0002
        L_0x002d:
            r6 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x002d }
            throw r6
        L_0x0030:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.map.StaticBucketMap.containsValue(java.lang.Object):boolean");
    }

    public V put(K k, V v) {
        int hash = getHash(k);
        synchronized (this.locks[hash]) {
            Node<K, V> node = this.buckets[hash];
            if (node == null) {
                Node<K, V> node2 = new Node<>();
                node2.key = k;
                node2.value = v;
                this.buckets[hash] = node2;
                Lock lock = this.locks[hash];
                lock.size++;
                return null;
            }
            Node<K, V> node3 = node;
            while (node != null) {
                if (node.key != k) {
                    if (node.key == null || !node.key.equals(k)) {
                        node3 = node;
                        node = node.next;
                    }
                }
                V v2 = node.value;
                node.value = v;
                return v2;
            }
            Node<K, V> node4 = new Node<>();
            node4.key = k;
            node4.value = v;
            node3.next = node4;
            Lock lock2 = this.locks[hash];
            lock2.size++;
            return null;
        }
    }

    public V remove(Object obj) {
        int hash = getHash(obj);
        synchronized (this.locks[hash]) {
            Node<K, V> node = this.buckets[hash];
            Node<K, V> node2 = null;
            while (node != null) {
                if (node.key != obj) {
                    if (node.key == null || !node.key.equals(obj)) {
                        node2 = node;
                        node = node.next;
                    }
                }
                if (node2 == null) {
                    this.buckets[hash] = node.next;
                } else {
                    node2.next = node.next;
                }
                Lock lock = this.locks[hash];
                lock.size--;
                V v = node.value;
                return v;
            }
            return null;
        }
    }

    public Set<K> keySet() {
        return new KeySet();
    }

    public Collection<V> values() {
        return new Values();
    }

    public Set<Entry<K, V>> entrySet() {
        return new EntrySet();
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public void clear() {
        for (int i = 0; i < this.buckets.length; i++) {
            Lock lock = this.locks[i];
            synchronized (lock) {
                this.buckets[i] = null;
                lock.size = 0;
            }
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Map)) {
            return false;
        }
        return entrySet().equals(((Map) obj).entrySet());
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < this.buckets.length; i2++) {
            synchronized (this.locks[i2]) {
                for (Node<K, V> node = this.buckets[i2]; node != null; node = node.next) {
                    i += node.hashCode();
                }
            }
        }
        return i;
    }

    public void atomic(Runnable runnable) {
        if (runnable != null) {
            atomic(runnable, 0);
            return;
        }
        throw new NullPointerException();
    }

    private void atomic(Runnable runnable, int i) {
        if (i >= this.buckets.length) {
            runnable.run();
            return;
        }
        synchronized (this.locks[i]) {
            atomic(runnable, i + 1);
        }
    }
}
