package org.apache.commons.collections4.trie;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.slf4j.Logger;

abstract class AbstractPatriciaTrie<K, V> extends AbstractBitwiseTrie<K, V> {
    private static final long serialVersionUID = 5155253417231339498L;
    private volatile transient Set<Entry<K, V>> entrySet;
    private volatile transient Set<K> keySet;
    protected transient int modCount = 0;
    private transient TrieEntry<K, V> root = new TrieEntry<>(null, null, -1);
    private transient int size = 0;
    private volatile transient Collection<V> values;

    private class EntrySet extends AbstractSet<Entry<K, V>> {

        private class EntryIterator extends TrieIterator<Entry<K, V>> {
            private EntryIterator() {
                super();
            }

            public Entry<K, V> next() {
                return nextEntry();
            }
        }

        private EntrySet() {
        }

        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }

        public boolean contains(Object obj) {
            boolean z = false;
            if (!(obj instanceof Entry)) {
                return false;
            }
            TrieEntry entry = AbstractPatriciaTrie.this.getEntry(((Entry) obj).getKey());
            if (entry != null && entry.equals(obj)) {
                z = true;
            }
            return z;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry) || !contains(obj)) {
                return false;
            }
            AbstractPatriciaTrie.this.remove(((Entry) obj).getKey());
            return true;
        }

        public int size() {
            return AbstractPatriciaTrie.this.size();
        }

        public void clear() {
            AbstractPatriciaTrie.this.clear();
        }
    }

    private class KeySet extends AbstractSet<K> {

        private class KeyIterator extends TrieIterator<K> {
            private KeyIterator() {
                super();
            }

            public K next() {
                return nextEntry().getKey();
            }
        }

        private KeySet() {
        }

        public Iterator<K> iterator() {
            return new KeyIterator();
        }

        public int size() {
            return AbstractPatriciaTrie.this.size();
        }

        public boolean contains(Object obj) {
            return AbstractPatriciaTrie.this.containsKey(obj);
        }

        public boolean remove(Object obj) {
            int size = size();
            AbstractPatriciaTrie.this.remove(obj);
            return size != size();
        }

        public void clear() {
            AbstractPatriciaTrie.this.clear();
        }
    }

    private final class PrefixRangeEntrySet extends RangeEntrySet {
        private final PrefixRangeMap delegate;
        private int expectedModCount = 0;
        private TrieEntry<K, V> prefixStart;

        private final class EntryIterator extends TrieIterator<Entry<K, V>> {
            private boolean lastOne;
            private final int lengthInBits;
            private final int offset;
            private final K prefix;
            private TrieEntry<K, V> subtree;

            EntryIterator(TrieEntry<K, V> trieEntry, K k, int i, int i2) {
                super();
                this.subtree = trieEntry;
                this.next = AbstractPatriciaTrie.this.followLeft(trieEntry);
                this.prefix = k;
                this.offset = i;
                this.lengthInBits = i2;
            }

            public Entry<K, V> next() {
                TrieEntry nextEntry = nextEntry();
                if (this.lastOne) {
                    this.next = null;
                }
                return nextEntry;
            }

            /* access modifiers changed from: protected */
            public TrieEntry<K, V> findNext(TrieEntry<K, V> trieEntry) {
                return AbstractPatriciaTrie.this.nextEntryInSubtree(trieEntry, this.subtree);
            }

            public void remove() {
                int i = this.subtree.bitIndex;
                boolean z = this.current == this.subtree;
                super.remove();
                if (i != this.subtree.bitIndex || z) {
                    this.subtree = AbstractPatriciaTrie.this.subtree(this.prefix, this.offset, this.lengthInBits);
                }
                if (this.lengthInBits >= this.subtree.bitIndex) {
                    this.lastOne = true;
                }
            }
        }

        private final class SingletonIterator implements Iterator<Entry<K, V>> {
            private final TrieEntry<K, V> entry;
            private int hit = 0;

            public SingletonIterator(TrieEntry<K, V> trieEntry) {
                this.entry = trieEntry;
            }

            public boolean hasNext() {
                return this.hit == 0;
            }

            public Entry<K, V> next() {
                int i = this.hit;
                if (i == 0) {
                    this.hit = i + 1;
                    return this.entry;
                }
                throw new NoSuchElementException();
            }

            public void remove() {
                int i = this.hit;
                if (i == 1) {
                    this.hit = i + 1;
                    AbstractPatriciaTrie.this.removeEntry(this.entry);
                    return;
                }
                throw new IllegalStateException();
            }
        }

        public PrefixRangeEntrySet(PrefixRangeMap prefixRangeMap) {
            super(prefixRangeMap);
            this.delegate = prefixRangeMap;
        }

        public int size() {
            return this.delegate.fixup();
        }

        public Iterator<Entry<K, V>> iterator() {
            if (AbstractPatriciaTrie.this.modCount != this.expectedModCount) {
                this.prefixStart = AbstractPatriciaTrie.this.subtree(this.delegate.prefix, this.delegate.offsetInBits, this.delegate.lengthInBits);
                this.expectedModCount = AbstractPatriciaTrie.this.modCount;
            }
            if (this.prefixStart == null) {
                return Collections.emptySet().iterator();
            }
            if (this.delegate.lengthInBits > this.prefixStart.bitIndex) {
                return new SingletonIterator(this.prefixStart);
            }
            EntryIterator entryIterator = new EntryIterator(this.prefixStart, this.delegate.prefix, this.delegate.offsetInBits, this.delegate.lengthInBits);
            return entryIterator;
        }
    }

    private class PrefixRangeMap extends RangeMap {
        private transient int expectedModCount;
        private K fromKey;
        /* access modifiers changed from: private */
        public final int lengthInBits;
        /* access modifiers changed from: private */
        public final int offsetInBits;
        /* access modifiers changed from: private */
        public final K prefix;
        private int size;
        private K toKey;

        public boolean isFromInclusive() {
            return false;
        }

        public boolean isToInclusive() {
            return false;
        }

        private PrefixRangeMap(K k, int i, int i2) {
            super();
            this.fromKey = null;
            this.toKey = null;
            this.expectedModCount = 0;
            this.size = -1;
            this.prefix = k;
            this.offsetInBits = i;
            this.lengthInBits = i2;
        }

        /* access modifiers changed from: private */
        public int fixup() {
            Entry entry;
            K k;
            K k2;
            K k3;
            if (this.size == -1 || AbstractPatriciaTrie.this.modCount != this.expectedModCount) {
                Iterator it = super.entrySet().iterator();
                this.size = 0;
                K k4 = null;
                if (it.hasNext()) {
                    entry = (Entry) it.next();
                    this.size = 1;
                } else {
                    entry = null;
                }
                if (entry == null) {
                    k = null;
                } else {
                    k = entry.getKey();
                }
                this.fromKey = k;
                if (this.fromKey != null) {
                    TrieEntry previousEntry = AbstractPatriciaTrie.this.previousEntry((TrieEntry) entry);
                    if (previousEntry == null) {
                        k3 = null;
                    } else {
                        k3 = previousEntry.getKey();
                    }
                    this.fromKey = k3;
                }
                this.toKey = this.fromKey;
                while (it.hasNext()) {
                    this.size++;
                    entry = (Entry) it.next();
                }
                if (entry == null) {
                    k2 = null;
                } else {
                    k2 = entry.getKey();
                }
                this.toKey = k2;
                if (this.toKey != null) {
                    TrieEntry nextEntry = AbstractPatriciaTrie.this.nextEntry((TrieEntry) entry);
                    if (nextEntry != null) {
                        k4 = nextEntry.getKey();
                    }
                    this.toKey = k4;
                }
                this.expectedModCount = AbstractPatriciaTrie.this.modCount;
            }
            return this.size;
        }

        public K firstKey() {
            TrieEntry trieEntry;
            fixup();
            K k = this.fromKey;
            if (k == null) {
                trieEntry = AbstractPatriciaTrie.this.firstEntry();
            } else {
                trieEntry = AbstractPatriciaTrie.this.higherEntry(k);
            }
            K key = trieEntry != null ? trieEntry.getKey() : null;
            if (trieEntry != null && AbstractPatriciaTrie.this.getKeyAnalyzer().isPrefix(this.prefix, this.offsetInBits, this.lengthInBits, key)) {
                return key;
            }
            throw new NoSuchElementException();
        }

        public K lastKey() {
            TrieEntry trieEntry;
            fixup();
            K k = this.toKey;
            if (k == null) {
                trieEntry = AbstractPatriciaTrie.this.lastEntry();
            } else {
                trieEntry = AbstractPatriciaTrie.this.lowerEntry(k);
            }
            K key = trieEntry != null ? trieEntry.getKey() : null;
            if (trieEntry != null && AbstractPatriciaTrie.this.getKeyAnalyzer().isPrefix(this.prefix, this.offsetInBits, this.lengthInBits, key)) {
                return key;
            }
            throw new NoSuchElementException();
        }

        /* access modifiers changed from: protected */
        public boolean inRange(K k) {
            return AbstractPatriciaTrie.this.getKeyAnalyzer().isPrefix(this.prefix, this.offsetInBits, this.lengthInBits, k);
        }

        /* access modifiers changed from: protected */
        public boolean inRange2(K k) {
            return inRange(k);
        }

        /* access modifiers changed from: protected */
        public boolean inFromRange(K k, boolean z) {
            return AbstractPatriciaTrie.this.getKeyAnalyzer().isPrefix(this.prefix, this.offsetInBits, this.lengthInBits, k);
        }

        /* access modifiers changed from: protected */
        public boolean inToRange(K k, boolean z) {
            return AbstractPatriciaTrie.this.getKeyAnalyzer().isPrefix(this.prefix, this.offsetInBits, this.lengthInBits, k);
        }

        /* access modifiers changed from: protected */
        public Set<Entry<K, V>> createEntrySet() {
            return new PrefixRangeEntrySet(this);
        }

        public K getFromKey() {
            return this.fromKey;
        }

        public K getToKey() {
            return this.toKey;
        }

        /* access modifiers changed from: protected */
        public SortedMap<K, V> createRangeMap(K k, boolean z, K k2, boolean z2) {
            RangeEntryMap rangeEntryMap = new RangeEntryMap(k, z, k2, z2);
            return rangeEntryMap;
        }

        public void clear() {
            Iterator it = AbstractPatriciaTrie.this.entrySet().iterator();
            Set keySet = keySet();
            while (it.hasNext()) {
                if (keySet.contains(((Entry) it.next()).getKey())) {
                    it.remove();
                }
            }
        }
    }

    private class RangeEntryMap extends RangeMap {
        private final boolean fromInclusive;
        private final K fromKey;
        private final boolean toInclusive;
        private final K toKey;

        protected RangeEntryMap(AbstractPatriciaTrie abstractPatriciaTrie, K k, K k2) {
            this(k, true, k2, false);
        }

        protected RangeEntryMap(K k, boolean z, K k2, boolean z2) {
            super();
            if (k == null && k2 == null) {
                throw new IllegalArgumentException("must have a from or to!");
            } else if (k == null || k2 == null || AbstractPatriciaTrie.this.getKeyAnalyzer().compare(k, k2) <= 0) {
                this.fromKey = k;
                this.fromInclusive = z;
                this.toKey = k2;
                this.toInclusive = z2;
            } else {
                throw new IllegalArgumentException("fromKey > toKey");
            }
        }

        public K firstKey() {
            TrieEntry trieEntry;
            K k = this.fromKey;
            if (k == null) {
                trieEntry = AbstractPatriciaTrie.this.firstEntry();
            } else if (this.fromInclusive) {
                trieEntry = AbstractPatriciaTrie.this.ceilingEntry(k);
            } else {
                trieEntry = AbstractPatriciaTrie.this.higherEntry(k);
            }
            K key = trieEntry != null ? trieEntry.getKey() : null;
            if (trieEntry != null && (this.toKey == null || inToRange(key, false))) {
                return key;
            }
            throw new NoSuchElementException();
        }

        public K lastKey() {
            TrieEntry trieEntry;
            K k = this.toKey;
            if (k == null) {
                trieEntry = AbstractPatriciaTrie.this.lastEntry();
            } else if (this.toInclusive) {
                trieEntry = AbstractPatriciaTrie.this.floorEntry(k);
            } else {
                trieEntry = AbstractPatriciaTrie.this.lowerEntry(k);
            }
            K key = trieEntry != null ? trieEntry.getKey() : null;
            if (trieEntry != null && (this.fromKey == null || inFromRange(key, false))) {
                return key;
            }
            throw new NoSuchElementException();
        }

        /* access modifiers changed from: protected */
        public Set<Entry<K, V>> createEntrySet() {
            return new RangeEntrySet(this);
        }

        public K getFromKey() {
            return this.fromKey;
        }

        public K getToKey() {
            return this.toKey;
        }

        public boolean isFromInclusive() {
            return this.fromInclusive;
        }

        public boolean isToInclusive() {
            return this.toInclusive;
        }

        /* access modifiers changed from: protected */
        public SortedMap<K, V> createRangeMap(K k, boolean z, K k2, boolean z2) {
            RangeEntryMap rangeEntryMap = new RangeEntryMap(k, z, k2, z2);
            return rangeEntryMap;
        }
    }

    private class RangeEntrySet extends AbstractSet<Entry<K, V>> {
        private final RangeMap delegate;
        private transient int expectedModCount;
        private transient int size = -1;

        private final class EntryIterator extends TrieIterator<Entry<K, V>> {
            private final K excludedKey;

            private EntryIterator(TrieEntry<K, V> trieEntry, TrieEntry<K, V> trieEntry2) {
                super(trieEntry);
                this.excludedKey = trieEntry2 != null ? trieEntry2.getKey() : null;
            }

            public boolean hasNext() {
                return this.next != null && !AbstractBitwiseTrie.compare(this.next.key, this.excludedKey);
            }

            public Entry<K, V> next() {
                if (this.next != null && !AbstractBitwiseTrie.compare(this.next.key, this.excludedKey)) {
                    return nextEntry();
                }
                throw new NoSuchElementException();
            }
        }

        public RangeEntrySet(RangeMap rangeMap) {
            if (rangeMap != null) {
                this.delegate = rangeMap;
                return;
            }
            throw new NullPointerException("delegate");
        }

        public Iterator<Entry<K, V>> iterator() {
            TrieEntry trieEntry;
            Object fromKey = this.delegate.getFromKey();
            Object toKey = this.delegate.getToKey();
            if (fromKey == null) {
                trieEntry = AbstractPatriciaTrie.this.firstEntry();
            } else {
                trieEntry = AbstractPatriciaTrie.this.ceilingEntry(fromKey);
            }
            return new EntryIterator(trieEntry, toKey != null ? AbstractPatriciaTrie.this.ceilingEntry(toKey) : null);
        }

        public int size() {
            if (this.size == -1 || this.expectedModCount != AbstractPatriciaTrie.this.modCount) {
                this.size = 0;
                Iterator it = iterator();
                while (it.hasNext()) {
                    this.size++;
                    it.next();
                }
                this.expectedModCount = AbstractPatriciaTrie.this.modCount;
            }
            return this.size;
        }

        public boolean isEmpty() {
            return !iterator().hasNext();
        }

        public boolean contains(Object obj) {
            boolean z = false;
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            Object key = entry.getKey();
            if (!this.delegate.inRange(key)) {
                return false;
            }
            TrieEntry entry2 = AbstractPatriciaTrie.this.getEntry(key);
            if (entry2 != null && AbstractBitwiseTrie.compare(entry2.getValue(), entry.getValue())) {
                z = true;
            }
            return z;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            Object key = entry.getKey();
            if (!this.delegate.inRange(key)) {
                return false;
            }
            TrieEntry entry2 = AbstractPatriciaTrie.this.getEntry(key);
            if (entry2 == null || !AbstractBitwiseTrie.compare(entry2.getValue(), entry.getValue())) {
                return false;
            }
            AbstractPatriciaTrie.this.removeEntry(entry2);
            return true;
        }
    }

    private abstract class RangeMap extends AbstractMap<K, V> implements SortedMap<K, V> {
        private volatile transient Set<Entry<K, V>> entrySet;

        /* access modifiers changed from: protected */
        public abstract Set<Entry<K, V>> createEntrySet();

        /* access modifiers changed from: protected */
        public abstract SortedMap<K, V> createRangeMap(K k, boolean z, K k2, boolean z2);

        /* access modifiers changed from: protected */
        public abstract K getFromKey();

        /* access modifiers changed from: protected */
        public abstract K getToKey();

        /* access modifiers changed from: protected */
        public abstract boolean isFromInclusive();

        /* access modifiers changed from: protected */
        public abstract boolean isToInclusive();

        private RangeMap() {
        }

        public Comparator<? super K> comparator() {
            return AbstractPatriciaTrie.this.comparator();
        }

        public boolean containsKey(Object obj) {
            if (!inRange(AbstractPatriciaTrie.this.castKey(obj))) {
                return false;
            }
            return AbstractPatriciaTrie.this.containsKey(obj);
        }

        public V remove(Object obj) {
            if (!inRange(AbstractPatriciaTrie.this.castKey(obj))) {
                return null;
            }
            return AbstractPatriciaTrie.this.remove(obj);
        }

        public V get(Object obj) {
            if (!inRange(AbstractPatriciaTrie.this.castKey(obj))) {
                return null;
            }
            return AbstractPatriciaTrie.this.get(obj);
        }

        public V put(K k, V v) {
            if (inRange(k)) {
                return AbstractPatriciaTrie.this.put(k, v);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Key is out of range: ");
            sb.append(k);
            throw new IllegalArgumentException(sb.toString());
        }

        public Set<Entry<K, V>> entrySet() {
            if (this.entrySet == null) {
                this.entrySet = createEntrySet();
            }
            return this.entrySet;
        }

        public SortedMap<K, V> subMap(K k, K k2) {
            if (!inRange2(k)) {
                StringBuilder sb = new StringBuilder();
                sb.append("FromKey is out of range: ");
                sb.append(k);
                throw new IllegalArgumentException(sb.toString());
            } else if (inRange2(k2)) {
                return createRangeMap(k, isFromInclusive(), k2, isToInclusive());
            } else {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("ToKey is out of range: ");
                sb2.append(k2);
                throw new IllegalArgumentException(sb2.toString());
            }
        }

        public SortedMap<K, V> headMap(K k) {
            if (inRange2(k)) {
                return createRangeMap(getFromKey(), isFromInclusive(), k, isToInclusive());
            }
            StringBuilder sb = new StringBuilder();
            sb.append("ToKey is out of range: ");
            sb.append(k);
            throw new IllegalArgumentException(sb.toString());
        }

        public SortedMap<K, V> tailMap(K k) {
            if (inRange2(k)) {
                return createRangeMap(k, isFromInclusive(), getToKey(), isToInclusive());
            }
            StringBuilder sb = new StringBuilder();
            sb.append("FromKey is out of range: ");
            sb.append(k);
            throw new IllegalArgumentException(sb.toString());
        }

        /* access modifiers changed from: protected */
        public boolean inRange(K k) {
            Object fromKey = getFromKey();
            Object toKey = getToKey();
            if (fromKey != null && !inFromRange(k, false)) {
                return false;
            }
            if (toKey == null || inToRange(k, false)) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: protected */
        public boolean inRange2(K k) {
            Object fromKey = getFromKey();
            Object toKey = getToKey();
            if ((fromKey == null || inFromRange(k, false)) && (toKey == null || inToRange(k, true))) {
                return true;
            }
            return false;
        }

        /* access modifiers changed from: protected */
        public boolean inFromRange(K k, boolean z) {
            Object fromKey = getFromKey();
            boolean isFromInclusive = isFromInclusive();
            int compare = AbstractPatriciaTrie.this.getKeyAnalyzer().compare(k, fromKey);
            boolean z2 = true;
            if (isFromInclusive || z) {
                if (compare < 0) {
                    z2 = false;
                }
                return z2;
            }
            if (compare <= 0) {
                z2 = false;
            }
            return z2;
        }

        /* access modifiers changed from: protected */
        public boolean inToRange(K k, boolean z) {
            Object toKey = getToKey();
            boolean isToInclusive = isToInclusive();
            int compare = AbstractPatriciaTrie.this.getKeyAnalyzer().compare(k, toKey);
            boolean z2 = true;
            if (isToInclusive || z) {
                if (compare > 0) {
                    z2 = false;
                }
                return z2;
            }
            if (compare >= 0) {
                z2 = false;
            }
            return z2;
        }
    }

    private static class Reference<E> {
        private E item;

        private Reference() {
        }

        public void set(E e) {
            this.item = e;
        }

        public E get() {
            return this.item;
        }
    }

    protected static class TrieEntry<K, V> extends BasicEntry<K, V> {
        private static final long serialVersionUID = 4596023148184140013L;
        protected int bitIndex;
        protected TrieEntry<K, V> left = this;
        protected TrieEntry<K, V> parent = null;
        protected TrieEntry<K, V> predecessor = this;
        protected TrieEntry<K, V> right = null;

        public TrieEntry(K k, V v, int i) {
            super(k, v);
            this.bitIndex = i;
        }

        public boolean isEmpty() {
            return this.key == null;
        }

        public boolean isInternalNode() {
            return (this.left == this || this.right == this) ? false : true;
        }

        public boolean isExternalNode() {
            return !isInternalNode();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.bitIndex == -1) {
                sb.append("RootEntry(");
            } else {
                sb.append("Entry(");
            }
            sb.append("key=");
            sb.append(getKey());
            String str = " [";
            sb.append(str);
            sb.append(this.bitIndex);
            sb.append("], ");
            sb.append("value=");
            sb.append(getValue());
            String str2 = ", ";
            sb.append(str2);
            TrieEntry<K, V> trieEntry = this.parent;
            String str3 = "null";
            String str4 = "parent=";
            String str5 = Logger.ROOT_LOGGER_NAME;
            String str6 = "]";
            if (trieEntry == null) {
                sb.append(str4);
                sb.append(str3);
            } else if (trieEntry.bitIndex == -1) {
                sb.append(str4);
                sb.append(str5);
            } else {
                sb.append(str4);
                sb.append(this.parent.getKey());
                sb.append(str);
                sb.append(this.parent.bitIndex);
                sb.append(str6);
            }
            sb.append(str2);
            TrieEntry<K, V> trieEntry2 = this.left;
            String str7 = "left=";
            if (trieEntry2 == null) {
                sb.append(str7);
                sb.append(str3);
            } else if (trieEntry2.bitIndex == -1) {
                sb.append(str7);
                sb.append(str5);
            } else {
                sb.append(str7);
                sb.append(this.left.getKey());
                sb.append(str);
                sb.append(this.left.bitIndex);
                sb.append(str6);
            }
            sb.append(str2);
            TrieEntry<K, V> trieEntry3 = this.right;
            String str8 = "right=";
            if (trieEntry3 == null) {
                sb.append(str8);
                sb.append(str3);
            } else if (trieEntry3.bitIndex == -1) {
                sb.append(str8);
                sb.append(str5);
            } else {
                sb.append(str8);
                sb.append(this.right.getKey());
                sb.append(str);
                sb.append(this.right.bitIndex);
                sb.append(str6);
            }
            sb.append(str2);
            TrieEntry<K, V> trieEntry4 = this.predecessor;
            if (trieEntry4 != null) {
                String str9 = "predecessor=";
                if (trieEntry4.bitIndex == -1) {
                    sb.append(str9);
                    sb.append(str5);
                } else {
                    sb.append(str9);
                    sb.append(this.predecessor.getKey());
                    sb.append(str);
                    sb.append(this.predecessor.bitIndex);
                    sb.append(str6);
                }
            }
            sb.append(")");
            return sb.toString();
        }
    }

    abstract class TrieIterator<E> implements Iterator<E> {
        protected TrieEntry<K, V> current;
        protected int expectedModCount = AbstractPatriciaTrie.this.modCount;
        protected TrieEntry<K, V> next;

        protected TrieIterator() {
            this.next = AbstractPatriciaTrie.this.nextEntry(null);
        }

        protected TrieIterator(TrieEntry<K, V> trieEntry) {
            this.next = trieEntry;
        }

        /* access modifiers changed from: protected */
        public TrieEntry<K, V> nextEntry() {
            if (this.expectedModCount == AbstractPatriciaTrie.this.modCount) {
                TrieEntry<K, V> trieEntry = this.next;
                if (trieEntry != null) {
                    this.next = findNext(trieEntry);
                    this.current = trieEntry;
                    return trieEntry;
                }
                throw new NoSuchElementException();
            }
            throw new ConcurrentModificationException();
        }

        /* access modifiers changed from: protected */
        public TrieEntry<K, V> findNext(TrieEntry<K, V> trieEntry) {
            return AbstractPatriciaTrie.this.nextEntry(trieEntry);
        }

        public boolean hasNext() {
            return this.next != null;
        }

        public void remove() {
            if (this.current == null) {
                throw new IllegalStateException();
            } else if (this.expectedModCount == AbstractPatriciaTrie.this.modCount) {
                TrieEntry<K, V> trieEntry = this.current;
                this.current = null;
                AbstractPatriciaTrie.this.removeEntry(trieEntry);
                this.expectedModCount = AbstractPatriciaTrie.this.modCount;
            } else {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class TrieMapIterator extends TrieIterator<K> implements OrderedMapIterator<K, V> {
        protected TrieEntry<K, V> previous;

        private TrieMapIterator() {
            super();
        }

        public K next() {
            return nextEntry().getKey();
        }

        public K getKey() {
            if (this.current != null) {
                return this.current.getKey();
            }
            throw new IllegalStateException();
        }

        public V getValue() {
            if (this.current != null) {
                return this.current.getValue();
            }
            throw new IllegalStateException();
        }

        public V setValue(V v) {
            if (this.current != null) {
                return this.current.setValue(v);
            }
            throw new IllegalStateException();
        }

        public boolean hasPrevious() {
            return this.previous != null;
        }

        public K previous() {
            return previousEntry().getKey();
        }

        /* access modifiers changed from: protected */
        public TrieEntry<K, V> nextEntry() {
            TrieEntry<K, V> nextEntry = super.nextEntry();
            this.previous = nextEntry;
            return nextEntry;
        }

        /* access modifiers changed from: protected */
        public TrieEntry<K, V> previousEntry() {
            if (this.expectedModCount == AbstractPatriciaTrie.this.modCount) {
                TrieEntry<K, V> trieEntry = this.previous;
                if (trieEntry != null) {
                    this.previous = AbstractPatriciaTrie.this.previousEntry(trieEntry);
                    this.next = this.current;
                    this.current = trieEntry;
                    return this.current;
                }
                throw new NoSuchElementException();
            }
            throw new ConcurrentModificationException();
        }
    }

    private class Values extends AbstractCollection<V> {

        private class ValueIterator extends TrieIterator<V> {
            private ValueIterator() {
                super();
            }

            public V next() {
                return nextEntry().getValue();
            }
        }

        private Values() {
        }

        public Iterator<V> iterator() {
            return new ValueIterator();
        }

        public int size() {
            return AbstractPatriciaTrie.this.size();
        }

        public boolean contains(Object obj) {
            return AbstractPatriciaTrie.this.containsValue(obj);
        }

        public void clear() {
            AbstractPatriciaTrie.this.clear();
        }

        public boolean remove(Object obj) {
            Iterator it = iterator();
            while (it.hasNext()) {
                if (AbstractBitwiseTrie.compare(it.next(), obj)) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    }

    protected AbstractPatriciaTrie(KeyAnalyzer<? super K> keyAnalyzer) {
        super(keyAnalyzer);
    }

    protected AbstractPatriciaTrie(KeyAnalyzer<? super K> keyAnalyzer, Map<? extends K, ? extends V> map) {
        super(keyAnalyzer);
        putAll(map);
    }

    public void clear() {
        TrieEntry<K, V> trieEntry = this.root;
        trieEntry.key = null;
        trieEntry.bitIndex = -1;
        trieEntry.value = null;
        trieEntry.parent = null;
        trieEntry.left = trieEntry;
        trieEntry.right = null;
        trieEntry.predecessor = trieEntry;
        this.size = 0;
        incrementModCount();
    }

    public int size() {
        return this.size;
    }

    /* access modifiers changed from: 0000 */
    public void incrementSize() {
        this.size++;
        incrementModCount();
    }

    /* access modifiers changed from: 0000 */
    public void decrementSize() {
        this.size--;
        incrementModCount();
    }

    private void incrementModCount() {
        this.modCount++;
    }

    public V put(K k, V v) {
        if (k != null) {
            int lengthInBits = lengthInBits(k);
            if (lengthInBits == 0) {
                if (this.root.isEmpty()) {
                    incrementSize();
                } else {
                    incrementModCount();
                }
                return this.root.setKeyValue(k, v);
            }
            TrieEntry<K, V> nearestEntryForKey = getNearestEntryForKey(k, lengthInBits);
            if (compareKeys(k, nearestEntryForKey.key)) {
                if (nearestEntryForKey.isEmpty()) {
                    incrementSize();
                } else {
                    incrementModCount();
                }
                return nearestEntryForKey.setKeyValue(k, v);
            }
            int bitIndex = bitIndex(k, nearestEntryForKey.key);
            if (!KeyAnalyzer.isOutOfBoundsIndex(bitIndex)) {
                if (KeyAnalyzer.isValidBitIndex(bitIndex)) {
                    addEntry(new TrieEntry(k, v, bitIndex), lengthInBits);
                    incrementSize();
                    return null;
                } else if (KeyAnalyzer.isNullBitKey(bitIndex)) {
                    if (this.root.isEmpty()) {
                        incrementSize();
                    } else {
                        incrementModCount();
                    }
                    return this.root.setKeyValue(k, v);
                } else if (KeyAnalyzer.isEqualBitKey(bitIndex) && nearestEntryForKey != this.root) {
                    incrementModCount();
                    return nearestEntryForKey.setKeyValue(k, v);
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Failed to put: ");
            sb.append(k);
            sb.append(" -> ");
            sb.append(v);
            sb.append(", ");
            sb.append(bitIndex);
            throw new IllegalArgumentException(sb.toString());
        }
        throw new NullPointerException("Key cannot be null");
    }

    /* access modifiers changed from: 0000 */
    public TrieEntry<K, V> addEntry(TrieEntry<K, V> trieEntry, int i) {
        TrieEntry<K, V> trieEntry2;
        TrieEntry<K, V> trieEntry3 = this.root.left;
        TrieEntry<K, V> trieEntry4 = this.root;
        while (true) {
            TrieEntry<K, V> trieEntry5 = trieEntry4;
            trieEntry4 = trieEntry3;
            trieEntry2 = trieEntry5;
            if (trieEntry4.bitIndex >= trieEntry.bitIndex || trieEntry4.bitIndex <= trieEntry2.bitIndex) {
                trieEntry.predecessor = trieEntry;
            } else if (!isBitSet(trieEntry.key, trieEntry4.bitIndex, i)) {
                trieEntry3 = trieEntry4.left;
            } else {
                trieEntry3 = trieEntry4.right;
            }
        }
        trieEntry.predecessor = trieEntry;
        if (!isBitSet(trieEntry.key, trieEntry.bitIndex, i)) {
            trieEntry.left = trieEntry;
            trieEntry.right = trieEntry4;
        } else {
            trieEntry.left = trieEntry4;
            trieEntry.right = trieEntry;
        }
        trieEntry.parent = trieEntry2;
        if (trieEntry4.bitIndex >= trieEntry.bitIndex) {
            trieEntry4.parent = trieEntry;
        }
        if (trieEntry4.bitIndex <= trieEntry2.bitIndex) {
            trieEntry4.predecessor = trieEntry;
        }
        if (trieEntry2 == this.root || !isBitSet(trieEntry.key, trieEntry2.bitIndex, i)) {
            trieEntry2.left = trieEntry;
        } else {
            trieEntry2.right = trieEntry;
        }
        return trieEntry;
    }

    public V get(Object obj) {
        TrieEntry entry = getEntry(obj);
        if (entry != null) {
            return entry.getValue();
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public TrieEntry<K, V> getEntry(Object obj) {
        Object castKey = castKey(obj);
        TrieEntry<K, V> trieEntry = null;
        if (castKey == null) {
            return null;
        }
        TrieEntry<K, V> nearestEntryForKey = getNearestEntryForKey(castKey, lengthInBits(castKey));
        if (!nearestEntryForKey.isEmpty() && compareKeys(castKey, nearestEntryForKey.key)) {
            trieEntry = nearestEntryForKey;
        }
        return trieEntry;
    }

    public Entry<K, V> select(K k) {
        int lengthInBits = lengthInBits(k);
        Reference reference = new Reference();
        if (!selectR(this.root.left, -1, k, lengthInBits, reference)) {
            return (Entry) reference.get();
        }
        return null;
    }

    public K selectKey(K k) {
        Entry select = select(k);
        if (select == null) {
            return null;
        }
        return select.getKey();
    }

    public V selectValue(K k) {
        Entry select = select(k);
        if (select == null) {
            return null;
        }
        return select.getValue();
    }

    private boolean selectR(TrieEntry<K, V> trieEntry, int i, K k, int i2, Reference<Entry<K, V>> reference) {
        if (trieEntry.bitIndex > i) {
            if (!isBitSet(k, trieEntry.bitIndex, i2)) {
                if (selectR(trieEntry.left, trieEntry.bitIndex, k, i2, reference)) {
                    return selectR(trieEntry.right, trieEntry.bitIndex, k, i2, reference);
                }
            } else {
                if (selectR(trieEntry.right, trieEntry.bitIndex, k, i2, reference)) {
                    return selectR(trieEntry.left, trieEntry.bitIndex, k, i2, reference);
                }
            }
            return false;
        } else if (trieEntry.isEmpty()) {
            return true;
        } else {
            reference.set(trieEntry);
            return false;
        }
    }

    public boolean containsKey(Object obj) {
        boolean z = false;
        if (obj == null) {
            return false;
        }
        Object castKey = castKey(obj);
        TrieEntry nearestEntryForKey = getNearestEntryForKey(castKey, lengthInBits(castKey));
        if (!nearestEntryForKey.isEmpty() && compareKeys(castKey, nearestEntryForKey.key)) {
            z = true;
        }
        return z;
    }

    public Set<Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new EntrySet();
        }
        return this.entrySet;
    }

    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new KeySet();
        }
        return this.keySet;
    }

    public Collection<V> values() {
        if (this.values == null) {
            this.values = new Values();
        }
        return this.values;
    }

    public V remove(Object obj) {
        if (obj == null) {
            return null;
        }
        Object castKey = castKey(obj);
        int lengthInBits = lengthInBits(castKey);
        TrieEntry<K, V> trieEntry = this.root.left;
        TrieEntry<K, V> trieEntry2 = this.root;
        while (true) {
            TrieEntry<K, V> trieEntry3 = trieEntry2;
            trieEntry2 = trieEntry;
            if (trieEntry2.bitIndex <= trieEntry3.bitIndex) {
                break;
            } else if (!isBitSet(castKey, trieEntry2.bitIndex, lengthInBits)) {
                trieEntry = trieEntry2.left;
            } else {
                trieEntry = trieEntry2.right;
            }
        }
        if (trieEntry2.isEmpty() || !compareKeys(castKey, trieEntry2.key)) {
            return null;
        }
        return removeEntry(trieEntry2);
    }

    /* access modifiers changed from: 0000 */
    public TrieEntry<K, V> getNearestEntryForKey(K k, int i) {
        TrieEntry<K, V> trieEntry = this.root.left;
        TrieEntry<K, V> trieEntry2 = this.root;
        while (true) {
            TrieEntry<K, V> trieEntry3 = trieEntry2;
            trieEntry2 = trieEntry;
            if (trieEntry2.bitIndex <= trieEntry3.bitIndex) {
                return trieEntry2;
            }
            if (!isBitSet(k, trieEntry2.bitIndex, i)) {
                trieEntry = trieEntry2.left;
            } else {
                trieEntry = trieEntry2.right;
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public V removeEntry(TrieEntry<K, V> trieEntry) {
        if (trieEntry != this.root) {
            if (trieEntry.isInternalNode()) {
                removeInternalEntry(trieEntry);
            } else {
                removeExternalEntry(trieEntry);
            }
        }
        decrementSize();
        return trieEntry.setKeyValue(null, null);
    }

    private void removeExternalEntry(TrieEntry<K, V> trieEntry) {
        if (trieEntry == this.root) {
            throw new IllegalArgumentException("Cannot delete root Entry!");
        } else if (trieEntry.isExternalNode()) {
            TrieEntry<K, V> trieEntry2 = trieEntry.parent;
            TrieEntry<K, V> trieEntry3 = trieEntry.left == trieEntry ? trieEntry.right : trieEntry.left;
            if (trieEntry2.left == trieEntry) {
                trieEntry2.left = trieEntry3;
            } else {
                trieEntry2.right = trieEntry3;
            }
            if (trieEntry3.bitIndex > trieEntry2.bitIndex) {
                trieEntry3.parent = trieEntry2;
            } else {
                trieEntry3.predecessor = trieEntry2;
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(trieEntry);
            sb.append(" is not an external Entry!");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    private void removeInternalEntry(TrieEntry<K, V> trieEntry) {
        if (trieEntry == this.root) {
            throw new IllegalArgumentException("Cannot delete root Entry!");
        } else if (trieEntry.isInternalNode()) {
            TrieEntry<K, V> trieEntry2 = trieEntry.predecessor;
            trieEntry2.bitIndex = trieEntry.bitIndex;
            TrieEntry<K, V> trieEntry3 = trieEntry2.parent;
            TrieEntry<K, V> trieEntry4 = trieEntry2.left == trieEntry ? trieEntry2.right : trieEntry2.left;
            if (trieEntry2.predecessor == trieEntry2 && trieEntry2.parent != trieEntry) {
                trieEntry2.predecessor = trieEntry2.parent;
            }
            if (trieEntry3.left == trieEntry2) {
                trieEntry3.left = trieEntry4;
            } else {
                trieEntry3.right = trieEntry4;
            }
            if (trieEntry4.bitIndex > trieEntry3.bitIndex) {
                trieEntry4.parent = trieEntry3;
            }
            if (trieEntry.left.parent == trieEntry) {
                trieEntry.left.parent = trieEntry2;
            }
            if (trieEntry.right.parent == trieEntry) {
                trieEntry.right.parent = trieEntry2;
            }
            if (trieEntry.parent.left == trieEntry) {
                trieEntry.parent.left = trieEntry2;
            } else {
                trieEntry.parent.right = trieEntry2;
            }
            trieEntry2.parent = trieEntry.parent;
            trieEntry2.left = trieEntry.left;
            trieEntry2.right = trieEntry.right;
            if (isValidUplink(trieEntry2.left, trieEntry2)) {
                trieEntry2.left.predecessor = trieEntry2;
            }
            if (isValidUplink(trieEntry2.right, trieEntry2)) {
                trieEntry2.right.predecessor = trieEntry2;
            }
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(trieEntry);
            sb.append(" is not an internal Entry!");
            throw new IllegalArgumentException(sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public TrieEntry<K, V> nextEntry(TrieEntry<K, V> trieEntry) {
        if (trieEntry == null) {
            return firstEntry();
        }
        return nextEntryImpl(trieEntry.predecessor, trieEntry, null);
    }

    /* access modifiers changed from: 0000 */
    public TrieEntry<K, V> nextEntryImpl(TrieEntry<K, V> trieEntry, TrieEntry<K, V> trieEntry2, TrieEntry<K, V> trieEntry3) {
        if (trieEntry2 == null || trieEntry != trieEntry2.predecessor) {
            while (!trieEntry.left.isEmpty() && trieEntry2 != trieEntry.left) {
                if (isValidUplink(trieEntry.left, trieEntry)) {
                    return trieEntry.left;
                }
                trieEntry = trieEntry.left;
            }
        }
        if (trieEntry.isEmpty() || trieEntry.right == null) {
            return null;
        }
        if (trieEntry2 == trieEntry.right) {
            while (trieEntry == trieEntry.parent.right) {
                if (trieEntry == trieEntry3) {
                    return null;
                }
                trieEntry = trieEntry.parent;
            }
            if (trieEntry == trieEntry3 || trieEntry.parent.right == null) {
                return null;
            }
            if (trieEntry2 != trieEntry.parent.right && isValidUplink(trieEntry.parent.right, trieEntry.parent)) {
                return trieEntry.parent.right;
            }
            if (trieEntry.parent.right == trieEntry.parent) {
                return null;
            }
            return nextEntryImpl(trieEntry.parent.right, trieEntry2, trieEntry3);
        } else if (isValidUplink(trieEntry.right, trieEntry)) {
            return trieEntry.right;
        } else {
            return nextEntryImpl(trieEntry.right, trieEntry2, trieEntry3);
        }
    }

    /* access modifiers changed from: 0000 */
    public TrieEntry<K, V> firstEntry() {
        if (isEmpty()) {
            return null;
        }
        return followLeft(this.root);
    }

    /* access modifiers changed from: 0000 */
    public TrieEntry<K, V> followLeft(TrieEntry<K, V> trieEntry) {
        while (true) {
            TrieEntry<K, V> trieEntry2 = trieEntry.left;
            if (trieEntry2.isEmpty()) {
                trieEntry2 = trieEntry.right;
            }
            if (trieEntry2.bitIndex <= trieEntry.bitIndex) {
                return trieEntry2;
            }
            trieEntry = trieEntry2;
        }
    }

    public Comparator<? super K> comparator() {
        return getKeyAnalyzer();
    }

    public K firstKey() {
        if (size() != 0) {
            return firstEntry().getKey();
        }
        throw new NoSuchElementException();
    }

    public K lastKey() {
        TrieEntry lastEntry = lastEntry();
        if (lastEntry != null) {
            return lastEntry.getKey();
        }
        throw new NoSuchElementException();
    }

    public K nextKey(K k) {
        if (k != null) {
            TrieEntry entry = getEntry(k);
            if (entry == null) {
                return null;
            }
            TrieEntry nextEntry = nextEntry(entry);
            if (nextEntry != null) {
                return nextEntry.getKey();
            }
            return null;
        }
        throw new NullPointerException();
    }

    public K previousKey(K k) {
        if (k != null) {
            TrieEntry entry = getEntry(k);
            if (entry == null) {
                return null;
            }
            TrieEntry previousEntry = previousEntry(entry);
            if (previousEntry != null) {
                return previousEntry.getKey();
            }
            return null;
        }
        throw new NullPointerException();
    }

    public OrderedMapIterator<K, V> mapIterator() {
        return new TrieMapIterator();
    }

    public SortedMap<K, V> prefixMap(K k) {
        return getPrefixMapByBits(k, 0, lengthInBits(k));
    }

    private SortedMap<K, V> getPrefixMapByBits(K k, int i, int i2) {
        int i3 = i + i2;
        if (i3 > lengthInBits(k)) {
            StringBuilder sb = new StringBuilder();
            sb.append(i);
            sb.append(" + ");
            sb.append(i2);
            sb.append(" > ");
            sb.append(lengthInBits(k));
            throw new IllegalArgumentException(sb.toString());
        } else if (i3 == 0) {
            return this;
        } else {
            PrefixRangeMap prefixRangeMap = new PrefixRangeMap(k, i, i2);
            return prefixRangeMap;
        }
    }

    public SortedMap<K, V> headMap(K k) {
        return new RangeEntryMap(this, null, k);
    }

    public SortedMap<K, V> subMap(K k, K k2) {
        return new RangeEntryMap(this, k, k2);
    }

    public SortedMap<K, V> tailMap(K k) {
        return new RangeEntryMap(this, k, null);
    }

    /* access modifiers changed from: 0000 */
    public TrieEntry<K, V> higherEntry(K k) {
        int lengthInBits = lengthInBits(k);
        if (lengthInBits != 0) {
            TrieEntry nearestEntryForKey = getNearestEntryForKey(k, lengthInBits);
            if (compareKeys(k, nearestEntryForKey.key)) {
                return nextEntry(nearestEntryForKey);
            }
            int bitIndex = bitIndex(k, nearestEntryForKey.key);
            if (KeyAnalyzer.isValidBitIndex(bitIndex)) {
                TrieEntry trieEntry = new TrieEntry(k, null, bitIndex);
                addEntry(trieEntry, lengthInBits);
                incrementSize();
                TrieEntry<K, V> nextEntry = nextEntry(trieEntry);
                removeEntry(trieEntry);
                this.modCount -= 2;
                return nextEntry;
            } else if (KeyAnalyzer.isNullBitKey(bitIndex)) {
                if (!this.root.isEmpty()) {
                    return firstEntry();
                }
                if (size() > 1) {
                    return nextEntry(firstEntry());
                }
                return null;
            } else if (KeyAnalyzer.isEqualBitKey(bitIndex)) {
                return nextEntry(nearestEntryForKey);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("invalid lookup: ");
                sb.append(k);
                throw new IllegalStateException(sb.toString());
            }
        } else if (this.root.isEmpty()) {
            return firstEntry();
        } else {
            if (size() > 1) {
                return nextEntry(this.root);
            }
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public TrieEntry<K, V> ceilingEntry(K k) {
        int lengthInBits = lengthInBits(k);
        if (lengthInBits != 0) {
            TrieEntry<K, V> nearestEntryForKey = getNearestEntryForKey(k, lengthInBits);
            if (compareKeys(k, nearestEntryForKey.key)) {
                return nearestEntryForKey;
            }
            int bitIndex = bitIndex(k, nearestEntryForKey.key);
            if (KeyAnalyzer.isValidBitIndex(bitIndex)) {
                TrieEntry trieEntry = new TrieEntry(k, null, bitIndex);
                addEntry(trieEntry, lengthInBits);
                incrementSize();
                TrieEntry<K, V> nextEntry = nextEntry(trieEntry);
                removeEntry(trieEntry);
                this.modCount -= 2;
                return nextEntry;
            } else if (KeyAnalyzer.isNullBitKey(bitIndex)) {
                if (!this.root.isEmpty()) {
                    return this.root;
                }
                return firstEntry();
            } else if (KeyAnalyzer.isEqualBitKey(bitIndex)) {
                return nearestEntryForKey;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("invalid lookup: ");
                sb.append(k);
                throw new IllegalStateException(sb.toString());
            }
        } else if (!this.root.isEmpty()) {
            return this.root;
        } else {
            return firstEntry();
        }
    }

    /* access modifiers changed from: 0000 */
    public TrieEntry<K, V> lowerEntry(K k) {
        int lengthInBits = lengthInBits(k);
        if (lengthInBits == 0) {
            return null;
        }
        TrieEntry nearestEntryForKey = getNearestEntryForKey(k, lengthInBits);
        if (compareKeys(k, nearestEntryForKey.key)) {
            return previousEntry(nearestEntryForKey);
        }
        int bitIndex = bitIndex(k, nearestEntryForKey.key);
        if (KeyAnalyzer.isValidBitIndex(bitIndex)) {
            TrieEntry trieEntry = new TrieEntry(k, null, bitIndex);
            addEntry(trieEntry, lengthInBits);
            incrementSize();
            TrieEntry<K, V> previousEntry = previousEntry(trieEntry);
            removeEntry(trieEntry);
            this.modCount -= 2;
            return previousEntry;
        } else if (KeyAnalyzer.isNullBitKey(bitIndex)) {
            return null;
        } else {
            if (KeyAnalyzer.isEqualBitKey(bitIndex)) {
                return previousEntry(nearestEntryForKey);
            }
            StringBuilder sb = new StringBuilder();
            sb.append("invalid lookup: ");
            sb.append(k);
            throw new IllegalStateException(sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public TrieEntry<K, V> floorEntry(K k) {
        int lengthInBits = lengthInBits(k);
        if (lengthInBits != 0) {
            TrieEntry<K, V> nearestEntryForKey = getNearestEntryForKey(k, lengthInBits);
            if (compareKeys(k, nearestEntryForKey.key)) {
                return nearestEntryForKey;
            }
            int bitIndex = bitIndex(k, nearestEntryForKey.key);
            if (KeyAnalyzer.isValidBitIndex(bitIndex)) {
                TrieEntry trieEntry = new TrieEntry(k, null, bitIndex);
                addEntry(trieEntry, lengthInBits);
                incrementSize();
                TrieEntry<K, V> previousEntry = previousEntry(trieEntry);
                removeEntry(trieEntry);
                this.modCount -= 2;
                return previousEntry;
            } else if (KeyAnalyzer.isNullBitKey(bitIndex)) {
                if (!this.root.isEmpty()) {
                    return this.root;
                }
                return null;
            } else if (KeyAnalyzer.isEqualBitKey(bitIndex)) {
                return nearestEntryForKey;
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append("invalid lookup: ");
                sb.append(k);
                throw new IllegalStateException(sb.toString());
            }
        } else if (!this.root.isEmpty()) {
            return this.root;
        } else {
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    public TrieEntry<K, V> subtree(K k, int i, int i2) {
        TrieEntry<K, V> trieEntry;
        TrieEntry<K, V> trieEntry2 = this.root.left;
        TrieEntry<K, V> trieEntry3 = this.root;
        while (true) {
            TrieEntry<K, V> trieEntry4 = trieEntry3;
            trieEntry3 = trieEntry2;
            trieEntry = trieEntry4;
            if (trieEntry3.bitIndex > trieEntry.bitIndex && i2 > trieEntry3.bitIndex) {
                if (!isBitSet(k, trieEntry3.bitIndex + i, i + i2)) {
                    trieEntry2 = trieEntry3.left;
                } else {
                    trieEntry2 = trieEntry3.right;
                }
            }
        }
        if (!trieEntry3.isEmpty()) {
            trieEntry = trieEntry3;
        }
        if (trieEntry.isEmpty()) {
            return null;
        }
        int i3 = i + i2;
        if ((trieEntry == this.root && lengthInBits(trieEntry.getKey()) < i3) || isBitSet(k, i3 - 1, i3) != isBitSet(trieEntry.key, i2 - 1, lengthInBits(trieEntry.key))) {
            return null;
        }
        int bitIndex = getKeyAnalyzer().bitIndex(k, i, i2, trieEntry.key, 0, lengthInBits(trieEntry.getKey()));
        if (bitIndex < 0 || bitIndex >= i2) {
            return trieEntry;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public TrieEntry<K, V> lastEntry() {
        return followRight(this.root.left);
    }

    /* access modifiers changed from: 0000 */
    public TrieEntry<K, V> followRight(TrieEntry<K, V> trieEntry) {
        if (trieEntry.right == null) {
            return null;
        }
        while (trieEntry.right.bitIndex > trieEntry.bitIndex) {
            trieEntry = trieEntry.right;
        }
        return trieEntry.right;
    }

    /* access modifiers changed from: 0000 */
    public TrieEntry<K, V> previousEntry(TrieEntry<K, V> trieEntry) {
        if (trieEntry.predecessor == null) {
            throw new IllegalArgumentException("must have come from somewhere!");
        } else if (trieEntry.predecessor.right != trieEntry) {
            TrieEntry<K, V> trieEntry2 = trieEntry.predecessor;
            while (trieEntry2.parent != null && trieEntry2 == trieEntry2.parent.left) {
                trieEntry2 = trieEntry2.parent;
            }
            if (trieEntry2.parent == null) {
                return null;
            }
            if (!isValidUplink(trieEntry2.parent.left, trieEntry2.parent)) {
                return followRight(trieEntry2.parent.left);
            }
            TrieEntry<K, V> trieEntry3 = trieEntry2.parent.left;
            TrieEntry<K, V> trieEntry4 = this.root;
            if (trieEntry3 != trieEntry4) {
                return trieEntry2.parent.left;
            }
            if (trieEntry4.isEmpty()) {
                return null;
            }
            return this.root;
        } else if (isValidUplink(trieEntry.predecessor.left, trieEntry.predecessor)) {
            return trieEntry.predecessor.left;
        } else {
            return followRight(trieEntry.predecessor.left);
        }
    }

    /* access modifiers changed from: 0000 */
    public TrieEntry<K, V> nextEntryInSubtree(TrieEntry<K, V> trieEntry, TrieEntry<K, V> trieEntry2) {
        if (trieEntry == null) {
            return firstEntry();
        }
        return nextEntryImpl(trieEntry.predecessor, trieEntry, trieEntry2);
    }

    static boolean isValidUplink(TrieEntry<?, ?> trieEntry, TrieEntry<?, ?> trieEntry2) {
        return trieEntry != null && trieEntry.bitIndex <= trieEntry2.bitIndex && !trieEntry.isEmpty();
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.root = new TrieEntry<>(null, null, -1);
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            put(objectInputStream.readObject(), objectInputStream.readObject());
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        for (Entry entry : entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }
}
