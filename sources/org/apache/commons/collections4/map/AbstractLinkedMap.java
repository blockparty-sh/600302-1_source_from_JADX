package org.apache.commons.collections4.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import org.apache.commons.collections4.OrderedIterator;
import org.apache.commons.collections4.OrderedMap;
import org.apache.commons.collections4.OrderedMapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedMapIterator;

public abstract class AbstractLinkedMap<K, V> extends AbstractHashedMap<K, V> implements OrderedMap<K, V> {
    transient LinkEntry<K, V> header;

    protected static class EntrySetIterator<K, V> extends LinkIterator<K, V> implements OrderedIterator<Entry<K, V>>, ResettableIterator<Entry<K, V>> {
        protected EntrySetIterator(AbstractLinkedMap<K, V> abstractLinkedMap) {
            super(abstractLinkedMap);
        }

        public Entry<K, V> next() {
            return super.nextEntry();
        }

        public Entry<K, V> previous() {
            return super.previousEntry();
        }
    }

    protected static class KeySetIterator<K> extends LinkIterator<K, Object> implements OrderedIterator<K>, ResettableIterator<K> {
        protected KeySetIterator(AbstractLinkedMap<K, ?> abstractLinkedMap) {
            super(abstractLinkedMap);
        }

        public K next() {
            return super.nextEntry().getKey();
        }

        public K previous() {
            return super.previousEntry().getKey();
        }
    }

    protected static class LinkEntry<K, V> extends HashEntry<K, V> {
        protected LinkEntry<K, V> after;
        protected LinkEntry<K, V> before;

        protected LinkEntry(HashEntry<K, V> hashEntry, int i, Object obj, V v) {
            super(hashEntry, i, obj, v);
        }
    }

    protected static abstract class LinkIterator<K, V> {
        protected int expectedModCount;
        protected LinkEntry<K, V> last;
        protected LinkEntry<K, V> next;
        protected final AbstractLinkedMap<K, V> parent;

        protected LinkIterator(AbstractLinkedMap<K, V> abstractLinkedMap) {
            this.parent = abstractLinkedMap;
            this.next = abstractLinkedMap.header.after;
            this.expectedModCount = abstractLinkedMap.modCount;
        }

        public boolean hasNext() {
            return this.next != this.parent.header;
        }

        public boolean hasPrevious() {
            return this.next.before != this.parent.header;
        }

        /* access modifiers changed from: protected */
        public LinkEntry<K, V> nextEntry() {
            if (this.parent.modCount != this.expectedModCount) {
                throw new ConcurrentModificationException();
            } else if (this.next != this.parent.header) {
                LinkEntry<K, V> linkEntry = this.next;
                this.last = linkEntry;
                this.next = linkEntry.after;
                return this.last;
            } else {
                throw new NoSuchElementException("No next() entry in the iteration");
            }
        }

        /* access modifiers changed from: protected */
        public LinkEntry<K, V> previousEntry() {
            if (this.parent.modCount == this.expectedModCount) {
                LinkEntry<K, V> linkEntry = this.next.before;
                if (linkEntry != this.parent.header) {
                    this.next = linkEntry;
                    this.last = linkEntry;
                    return this.last;
                }
                throw new NoSuchElementException("No previous() entry in the iteration");
            }
            throw new ConcurrentModificationException();
        }

        /* access modifiers changed from: protected */
        public LinkEntry<K, V> currentEntry() {
            return this.last;
        }

        public void remove() {
            if (this.last == null) {
                throw new IllegalStateException("remove() can only be called once after next()");
            } else if (this.parent.modCount == this.expectedModCount) {
                this.parent.remove(this.last.getKey());
                this.last = null;
                this.expectedModCount = this.parent.modCount;
            } else {
                throw new ConcurrentModificationException();
            }
        }

        public void reset() {
            this.last = null;
            this.next = this.parent.header.after;
        }

        public String toString() {
            if (this.last == null) {
                return "Iterator[]";
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Iterator[");
            sb.append(this.last.getKey());
            sb.append("=");
            sb.append(this.last.getValue());
            sb.append("]");
            return sb.toString();
        }
    }

    protected static class LinkMapIterator<K, V> extends LinkIterator<K, V> implements OrderedMapIterator<K, V>, ResettableIterator<K> {
        protected LinkMapIterator(AbstractLinkedMap<K, V> abstractLinkedMap) {
            super(abstractLinkedMap);
        }

        public K next() {
            return super.nextEntry().getKey();
        }

        public K previous() {
            return super.previousEntry().getKey();
        }

        public K getKey() {
            LinkEntry currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.getKey();
            }
            throw new IllegalStateException("getKey() can only be called after next() and before remove()");
        }

        public V getValue() {
            LinkEntry currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.getValue();
            }
            throw new IllegalStateException("getValue() can only be called after next() and before remove()");
        }

        public V setValue(V v) {
            LinkEntry currentEntry = currentEntry();
            if (currentEntry != null) {
                return currentEntry.setValue(v);
            }
            throw new IllegalStateException("setValue() can only be called after next() and before remove()");
        }
    }

    protected static class ValuesIterator<V> extends LinkIterator<Object, V> implements OrderedIterator<V>, ResettableIterator<V> {
        protected ValuesIterator(AbstractLinkedMap<?, V> abstractLinkedMap) {
            super(abstractLinkedMap);
        }

        public V next() {
            return super.nextEntry().getValue();
        }

        public V previous() {
            return super.previousEntry().getValue();
        }
    }

    protected AbstractLinkedMap() {
    }

    protected AbstractLinkedMap(int i, float f, int i2) {
        super(i, f, i2);
    }

    protected AbstractLinkedMap(int i) {
        super(i);
    }

    protected AbstractLinkedMap(int i, float f) {
        super(i, f);
    }

    protected AbstractLinkedMap(Map<? extends K, ? extends V> map) {
        super(map);
    }

    /* access modifiers changed from: protected */
    public void init() {
        this.header = createEntry((HashEntry) null, -1, (Object) null, (Object) null);
        LinkEntry<K, V> linkEntry = this.header;
        linkEntry.after = linkEntry;
        linkEntry.before = linkEntry;
    }

    public boolean containsValue(Object obj) {
        if (obj == null) {
            LinkEntry<K, V> linkEntry = this.header;
            do {
                linkEntry = linkEntry.after;
                if (linkEntry != this.header) {
                }
            } while (linkEntry.getValue() != null);
            return true;
        }
        LinkEntry<K, V> linkEntry2 = this.header;
        do {
            linkEntry2 = linkEntry2.after;
            if (linkEntry2 != this.header) {
            }
        } while (!isEqualValue(obj, linkEntry2.getValue()));
        return true;
        return false;
    }

    public void clear() {
        super.clear();
        LinkEntry<K, V> linkEntry = this.header;
        linkEntry.after = linkEntry;
        linkEntry.before = linkEntry;
    }

    public K firstKey() {
        if (this.size != 0) {
            return this.header.after.getKey();
        }
        throw new NoSuchElementException("Map is empty");
    }

    public K lastKey() {
        if (this.size != 0) {
            return this.header.before.getKey();
        }
        throw new NoSuchElementException("Map is empty");
    }

    public K nextKey(Object obj) {
        LinkEntry entry = getEntry(obj);
        if (entry == null || entry.after == this.header) {
            return null;
        }
        return entry.after.getKey();
    }

    /* access modifiers changed from: protected */
    public LinkEntry<K, V> getEntry(Object obj) {
        return (LinkEntry) super.getEntry(obj);
    }

    public K previousKey(Object obj) {
        LinkEntry entry = getEntry(obj);
        if (entry == null || entry.before == this.header) {
            return null;
        }
        return entry.before.getKey();
    }

    /* access modifiers changed from: protected */
    public LinkEntry<K, V> getEntry(int i) {
        LinkEntry<K, V> linkEntry;
        String str = "Index ";
        if (i < 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(i);
            sb.append(" is less than zero");
            throw new IndexOutOfBoundsException(sb.toString());
        } else if (i < this.size) {
            if (i < this.size / 2) {
                linkEntry = this.header.after;
                for (int i2 = 0; i2 < i; i2++) {
                    linkEntry = linkEntry.after;
                }
            } else {
                LinkEntry<K, V> linkEntry2 = this.header;
                for (int i3 = this.size; i3 > i; i3--) {
                    linkEntry2 = linkEntry.before;
                }
            }
            return linkEntry;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(str);
            sb2.append(i);
            sb2.append(" is invalid for size ");
            sb2.append(this.size);
            throw new IndexOutOfBoundsException(sb2.toString());
        }
    }

    /* access modifiers changed from: protected */
    public void addEntry(HashEntry<K, V> hashEntry, int i) {
        LinkEntry<K, V> linkEntry = (LinkEntry) hashEntry;
        LinkEntry<K, V> linkEntry2 = this.header;
        linkEntry.after = linkEntry2;
        linkEntry.before = linkEntry2.before;
        this.header.before.after = linkEntry;
        this.header.before = linkEntry;
        this.data[i] = linkEntry;
    }

    /* access modifiers changed from: protected */
    public LinkEntry<K, V> createEntry(HashEntry<K, V> hashEntry, int i, K k, V v) {
        return new LinkEntry<>(hashEntry, i, convertKey(k), v);
    }

    /* access modifiers changed from: protected */
    public void removeEntry(HashEntry<K, V> hashEntry, int i, HashEntry<K, V> hashEntry2) {
        LinkEntry linkEntry = (LinkEntry) hashEntry;
        linkEntry.before.after = linkEntry.after;
        linkEntry.after.before = linkEntry.before;
        linkEntry.after = null;
        linkEntry.before = null;
        super.removeEntry(hashEntry, i, hashEntry2);
    }

    /* access modifiers changed from: protected */
    public LinkEntry<K, V> entryBefore(LinkEntry<K, V> linkEntry) {
        return linkEntry.before;
    }

    /* access modifiers changed from: protected */
    public LinkEntry<K, V> entryAfter(LinkEntry<K, V> linkEntry) {
        return linkEntry.after;
    }

    public OrderedMapIterator<K, V> mapIterator() {
        if (this.size == 0) {
            return EmptyOrderedMapIterator.emptyOrderedMapIterator();
        }
        return new LinkMapIterator(this);
    }

    /* access modifiers changed from: protected */
    public Iterator<Entry<K, V>> createEntrySetIterator() {
        if (size() == 0) {
            return EmptyOrderedIterator.emptyOrderedIterator();
        }
        return new EntrySetIterator(this);
    }

    /* access modifiers changed from: protected */
    public Iterator<K> createKeySetIterator() {
        if (size() == 0) {
            return EmptyOrderedIterator.emptyOrderedIterator();
        }
        return new KeySetIterator(this);
    }

    /* access modifiers changed from: protected */
    public Iterator<V> createValuesIterator() {
        if (size() == 0) {
            return EmptyOrderedIterator.emptyOrderedIterator();
        }
        return new ValuesIterator(this);
    }
}
