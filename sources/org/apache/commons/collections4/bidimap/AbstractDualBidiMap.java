package org.apache.commons.collections4.bidimap;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.ResettableIterator;
import org.apache.commons.collections4.collection.AbstractCollectionDecorator;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntryDecorator;

public abstract class AbstractDualBidiMap<K, V> implements BidiMap<K, V> {
    transient Set<Entry<K, V>> entrySet = null;
    transient BidiMap<V, K> inverseBidiMap = null;
    transient Set<K> keySet = null;
    transient Map<K, V> normalMap;
    transient Map<V, K> reverseMap;
    transient Set<V> values = null;

    protected static class BidiMapIterator<K, V> implements MapIterator<K, V>, ResettableIterator<K> {
        protected boolean canRemove = false;
        protected Iterator<Entry<K, V>> iterator;
        protected Entry<K, V> last = null;
        protected final AbstractDualBidiMap<K, V> parent;

        protected BidiMapIterator(AbstractDualBidiMap<K, V> abstractDualBidiMap) {
            this.parent = abstractDualBidiMap;
            this.iterator = abstractDualBidiMap.normalMap.entrySet().iterator();
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public K next() {
            this.last = (Entry) this.iterator.next();
            this.canRemove = true;
            return this.last.getKey();
        }

        public void remove() {
            if (this.canRemove) {
                Object value = this.last.getValue();
                this.iterator.remove();
                this.parent.reverseMap.remove(value);
                this.last = null;
                this.canRemove = false;
                return;
            }
            throw new IllegalStateException("Iterator remove() can only be called once after next()");
        }

        public K getKey() {
            Entry<K, V> entry = this.last;
            if (entry != null) {
                return entry.getKey();
            }
            throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
        }

        public V getValue() {
            Entry<K, V> entry = this.last;
            if (entry != null) {
                return entry.getValue();
            }
            throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
        }

        public V setValue(V v) {
            if (this.last == null) {
                throw new IllegalStateException("Iterator setValue() can only be called after next() and before remove()");
            } else if (!this.parent.reverseMap.containsKey(v) || this.parent.reverseMap.get(v) == this.last.getKey()) {
                return this.parent.put(this.last.getKey(), v);
            } else {
                throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map");
            }
        }

        public void reset() {
            this.iterator = this.parent.normalMap.entrySet().iterator();
            this.last = null;
            this.canRemove = false;
        }

        public String toString() {
            if (this.last == null) {
                return "MapIterator[]";
            }
            StringBuilder sb = new StringBuilder();
            sb.append("MapIterator[");
            sb.append(getKey());
            sb.append("=");
            sb.append(getValue());
            sb.append("]");
            return sb.toString();
        }
    }

    protected static class EntrySet<K, V> extends View<K, V, Entry<K, V>> implements Set<Entry<K, V>> {
        private static final long serialVersionUID = 4040410962603292348L;

        protected EntrySet(AbstractDualBidiMap<K, V> abstractDualBidiMap) {
            super(abstractDualBidiMap.normalMap.entrySet(), abstractDualBidiMap);
        }

        public Iterator<Entry<K, V>> iterator() {
            return this.parent.createEntrySetIterator(super.iterator());
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            Object key = entry.getKey();
            if (this.parent.containsKey(key)) {
                Object obj2 = this.parent.normalMap.get(key);
                if (obj2 != null ? obj2.equals(entry.getValue()) : entry.getValue() == null) {
                    this.parent.normalMap.remove(key);
                    this.parent.reverseMap.remove(obj2);
                    return true;
                }
            }
            return false;
        }
    }

    protected static class EntrySetIterator<K, V> extends AbstractIteratorDecorator<Entry<K, V>> {
        protected boolean canRemove = false;
        protected Entry<K, V> last = null;
        protected final AbstractDualBidiMap<K, V> parent;

        protected EntrySetIterator(Iterator<Entry<K, V>> it, AbstractDualBidiMap<K, V> abstractDualBidiMap) {
            super(it);
            this.parent = abstractDualBidiMap;
        }

        public Entry<K, V> next() {
            this.last = new MapEntry((Entry) super.next(), this.parent);
            this.canRemove = true;
            return this.last;
        }

        public void remove() {
            if (this.canRemove) {
                Object value = this.last.getValue();
                super.remove();
                this.parent.reverseMap.remove(value);
                this.last = null;
                this.canRemove = false;
                return;
            }
            throw new IllegalStateException("Iterator remove() can only be called once after next()");
        }
    }

    protected static class KeySet<K> extends View<K, Object, K> implements Set<K> {
        private static final long serialVersionUID = -7107935777385040694L;

        protected KeySet(AbstractDualBidiMap<K, ?> abstractDualBidiMap) {
            super(abstractDualBidiMap.normalMap.keySet(), abstractDualBidiMap);
        }

        public Iterator<K> iterator() {
            return this.parent.createKeySetIterator(super.iterator());
        }

        public boolean contains(Object obj) {
            return this.parent.normalMap.containsKey(obj);
        }

        public boolean remove(Object obj) {
            if (!this.parent.normalMap.containsKey(obj)) {
                return false;
            }
            this.parent.reverseMap.remove(this.parent.normalMap.remove(obj));
            return true;
        }
    }

    protected static class KeySetIterator<K> extends AbstractIteratorDecorator<K> {
        protected boolean canRemove = false;
        protected K lastKey = null;
        protected final AbstractDualBidiMap<K, ?> parent;

        protected KeySetIterator(Iterator<K> it, AbstractDualBidiMap<K, ?> abstractDualBidiMap) {
            super(it);
            this.parent = abstractDualBidiMap;
        }

        public K next() {
            this.lastKey = super.next();
            this.canRemove = true;
            return this.lastKey;
        }

        public void remove() {
            if (this.canRemove) {
                Object obj = this.parent.normalMap.get(this.lastKey);
                super.remove();
                this.parent.reverseMap.remove(obj);
                this.lastKey = null;
                this.canRemove = false;
                return;
            }
            throw new IllegalStateException("Iterator remove() can only be called once after next()");
        }
    }

    protected static class MapEntry<K, V> extends AbstractMapEntryDecorator<K, V> {
        protected final AbstractDualBidiMap<K, V> parent;

        protected MapEntry(Entry<K, V> entry, AbstractDualBidiMap<K, V> abstractDualBidiMap) {
            super(entry);
            this.parent = abstractDualBidiMap;
        }

        public V setValue(V v) {
            Object key = getKey();
            if (!this.parent.reverseMap.containsKey(v) || this.parent.reverseMap.get(v) == key) {
                this.parent.put(key, v);
                return super.setValue(v);
            }
            throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map");
        }
    }

    protected static class Values<V> extends View<Object, V, V> implements Set<V> {
        private static final long serialVersionUID = 4023777119829639864L;

        protected Values(AbstractDualBidiMap<?, V> abstractDualBidiMap) {
            super(abstractDualBidiMap.normalMap.values(), abstractDualBidiMap);
        }

        public Iterator<V> iterator() {
            return this.parent.createValuesIterator(super.iterator());
        }

        public boolean contains(Object obj) {
            return this.parent.reverseMap.containsKey(obj);
        }

        public boolean remove(Object obj) {
            if (!this.parent.reverseMap.containsKey(obj)) {
                return false;
            }
            this.parent.normalMap.remove(this.parent.reverseMap.remove(obj));
            return true;
        }
    }

    protected static class ValuesIterator<V> extends AbstractIteratorDecorator<V> {
        protected boolean canRemove = false;
        protected V lastValue = null;
        protected final AbstractDualBidiMap<Object, V> parent;

        protected ValuesIterator(Iterator<V> it, AbstractDualBidiMap<?, V> abstractDualBidiMap) {
            super(it);
            this.parent = abstractDualBidiMap;
        }

        public V next() {
            this.lastValue = super.next();
            this.canRemove = true;
            return this.lastValue;
        }

        public void remove() {
            if (this.canRemove) {
                super.remove();
                this.parent.reverseMap.remove(this.lastValue);
                this.lastValue = null;
                this.canRemove = false;
                return;
            }
            throw new IllegalStateException("Iterator remove() can only be called once after next()");
        }
    }

    protected static abstract class View<K, V, E> extends AbstractCollectionDecorator<E> {
        private static final long serialVersionUID = 4621510560119690639L;
        protected final AbstractDualBidiMap<K, V> parent;

        protected View(Collection<E> collection, AbstractDualBidiMap<K, V> abstractDualBidiMap) {
            super(collection);
            this.parent = abstractDualBidiMap;
        }

        public boolean equals(Object obj) {
            return obj == this || decorated().equals(obj);
        }

        public int hashCode() {
            return decorated().hashCode();
        }

        public boolean removeAll(Collection<?> collection) {
            boolean z = false;
            if (!this.parent.isEmpty() && !collection.isEmpty()) {
                for (Object remove : collection) {
                    z |= remove(remove);
                }
            }
            return z;
        }

        public boolean retainAll(Collection<?> collection) {
            boolean z = false;
            if (this.parent.isEmpty()) {
                return false;
            }
            if (collection.isEmpty()) {
                this.parent.clear();
                return true;
            }
            Iterator it = iterator();
            while (it.hasNext()) {
                if (!collection.contains(it.next())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        public void clear() {
            this.parent.clear();
        }
    }

    /* access modifiers changed from: protected */
    public abstract BidiMap<V, K> createBidiMap(Map<V, K> map, Map<K, V> map2, BidiMap<K, V> bidiMap);

    protected AbstractDualBidiMap() {
    }

    protected AbstractDualBidiMap(Map<K, V> map, Map<V, K> map2) {
        this.normalMap = map;
        this.reverseMap = map2;
    }

    protected AbstractDualBidiMap(Map<K, V> map, Map<V, K> map2, BidiMap<V, K> bidiMap) {
        this.normalMap = map;
        this.reverseMap = map2;
        this.inverseBidiMap = bidiMap;
    }

    public V get(Object obj) {
        return this.normalMap.get(obj);
    }

    public int size() {
        return this.normalMap.size();
    }

    public boolean isEmpty() {
        return this.normalMap.isEmpty();
    }

    public boolean containsKey(Object obj) {
        return this.normalMap.containsKey(obj);
    }

    public boolean equals(Object obj) {
        return this.normalMap.equals(obj);
    }

    public int hashCode() {
        return this.normalMap.hashCode();
    }

    public String toString() {
        return this.normalMap.toString();
    }

    public V put(K k, V v) {
        if (this.normalMap.containsKey(k)) {
            this.reverseMap.remove(this.normalMap.get(k));
        }
        if (this.reverseMap.containsKey(v)) {
            this.normalMap.remove(this.reverseMap.get(v));
        }
        V put = this.normalMap.put(k, v);
        this.reverseMap.put(v, k);
        return put;
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public V remove(Object obj) {
        if (!this.normalMap.containsKey(obj)) {
            return null;
        }
        V remove = this.normalMap.remove(obj);
        this.reverseMap.remove(remove);
        return remove;
    }

    public void clear() {
        this.normalMap.clear();
        this.reverseMap.clear();
    }

    public boolean containsValue(Object obj) {
        return this.reverseMap.containsKey(obj);
    }

    public MapIterator<K, V> mapIterator() {
        return new BidiMapIterator(this);
    }

    public K getKey(Object obj) {
        return this.reverseMap.get(obj);
    }

    public K removeValue(Object obj) {
        if (!this.reverseMap.containsKey(obj)) {
            return null;
        }
        K remove = this.reverseMap.remove(obj);
        this.normalMap.remove(remove);
        return remove;
    }

    public BidiMap<V, K> inverseBidiMap() {
        if (this.inverseBidiMap == null) {
            this.inverseBidiMap = createBidiMap(this.reverseMap, this.normalMap, this);
        }
        return this.inverseBidiMap;
    }

    public Set<K> keySet() {
        if (this.keySet == null) {
            this.keySet = new KeySet(this);
        }
        return this.keySet;
    }

    /* access modifiers changed from: protected */
    public Iterator<K> createKeySetIterator(Iterator<K> it) {
        return new KeySetIterator(it, this);
    }

    public Set<V> values() {
        if (this.values == null) {
            this.values = new Values(this);
        }
        return this.values;
    }

    /* access modifiers changed from: protected */
    public Iterator<V> createValuesIterator(Iterator<V> it) {
        return new ValuesIterator(it, this);
    }

    public Set<Entry<K, V>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = new EntrySet(this);
        }
        return this.entrySet;
    }

    /* access modifiers changed from: protected */
    public Iterator<Entry<K, V>> createEntrySetIterator(Iterator<Entry<K, V>> it) {
        return new EntrySetIterator(it, this);
    }
}
