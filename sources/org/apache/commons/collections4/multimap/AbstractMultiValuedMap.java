package org.apache.commons.collections4.multimap;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.iterators.LazyIteratorChain;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntry;
import org.apache.commons.collections4.keyvalue.UnmodifiableMapEntry;
import org.apache.commons.collections4.multiset.AbstractMultiSet;
import org.apache.commons.collections4.multiset.UnmodifiableMultiSet;

public abstract class AbstractMultiValuedMap<K, V> implements MultiValuedMap<K, V> {
    private transient AsMap asMapView;
    private transient EntryValues entryValuesView;
    private transient MultiSet<K> keysMultiSetView;
    /* access modifiers changed from: private */
    public transient Map<K, Collection<V>> map;
    private transient Collection<V> valuesView;

    private class AsMap extends AbstractMap<K, Collection<V>> {
        final transient Map<K, Collection<V>> decoratedMap;

        class AsMapEntrySet extends AbstractSet<Entry<K, Collection<V>>> {
            AsMapEntrySet() {
            }

            public Iterator<Entry<K, Collection<V>>> iterator() {
                AsMap asMap = AsMap.this;
                return new AsMapEntrySetIterator(asMap.decoratedMap.entrySet().iterator());
            }

            public int size() {
                return AsMap.this.size();
            }

            public void clear() {
                AsMap.this.clear();
            }

            public boolean contains(Object obj) {
                return AsMap.this.decoratedMap.entrySet().contains(obj);
            }

            public boolean remove(Object obj) {
                if (!contains(obj)) {
                    return false;
                }
                AbstractMultiValuedMap.this.remove(((Entry) obj).getKey());
                return true;
            }
        }

        class AsMapEntrySetIterator extends AbstractIteratorDecorator<Entry<K, Collection<V>>> {
            AsMapEntrySetIterator(Iterator<Entry<K, Collection<V>>> it) {
                super(it);
            }

            public Entry<K, Collection<V>> next() {
                Object key = ((Entry) super.next()).getKey();
                return new UnmodifiableMapEntry(key, AbstractMultiValuedMap.this.wrappedCollection(key));
            }
        }

        AsMap(Map<K, Collection<V>> map) {
            this.decoratedMap = map;
        }

        public Set<Entry<K, Collection<V>>> entrySet() {
            return new AsMapEntrySet();
        }

        public boolean containsKey(Object obj) {
            return this.decoratedMap.containsKey(obj);
        }

        public Collection<V> get(Object obj) {
            if (((Collection) this.decoratedMap.get(obj)) == null) {
                return null;
            }
            return AbstractMultiValuedMap.this.wrappedCollection(obj);
        }

        public Set<K> keySet() {
            return AbstractMultiValuedMap.this.keySet();
        }

        public int size() {
            return this.decoratedMap.size();
        }

        public Collection<V> remove(Object obj) {
            Collection collection = (Collection) this.decoratedMap.remove(obj);
            if (collection == null) {
                return null;
            }
            Collection<V> createCollection = AbstractMultiValuedMap.this.createCollection();
            createCollection.addAll(collection);
            collection.clear();
            return createCollection;
        }

        public boolean equals(Object obj) {
            return this == obj || this.decoratedMap.equals(obj);
        }

        public int hashCode() {
            return this.decoratedMap.hashCode();
        }

        public String toString() {
            return this.decoratedMap.toString();
        }

        public void clear() {
            AbstractMultiValuedMap.this.clear();
        }
    }

    private class EntryValues extends AbstractCollection<Entry<K, V>> {
        private EntryValues() {
        }

        public Iterator<Entry<K, V>> iterator() {
            return new LazyIteratorChain<Entry<K, V>>() {
                final Iterator<K> keyIterator = this.keysCol.iterator();
                final Collection<K> keysCol = new ArrayList(AbstractMultiValuedMap.this.getMap().keySet());

                /* access modifiers changed from: protected */
                public Iterator<? extends Entry<K, V>> nextIterator(int i) {
                    if (!this.keyIterator.hasNext()) {
                        return null;
                    }
                    final Object next = this.keyIterator.next();
                    return new TransformIterator(new ValuesIterator(next), new Transformer<V, Entry<K, V>>() {
                        public Entry<K, V> transform(V v) {
                            return new MultiValuedMapEntry(next, v);
                        }
                    });
                }
            };
        }

        public int size() {
            return AbstractMultiValuedMap.this.size();
        }
    }

    private class KeysMultiSet extends AbstractMultiSet<K> {

        private final class MapEntryTransformer implements Transformer<Entry<K, Collection<V>>, MultiSet.Entry<K>> {
            private MapEntryTransformer() {
            }

            public MultiSet.Entry<K> transform(final Entry<K, Collection<V>> entry) {
                return new AbstractEntry<K>() {
                    public K getElement() {
                        return entry.getKey();
                    }

                    public int getCount() {
                        return ((Collection) entry.getValue()).size();
                    }
                };
            }
        }

        private KeysMultiSet() {
        }

        public boolean contains(Object obj) {
            return AbstractMultiValuedMap.this.getMap().containsKey(obj);
        }

        public boolean isEmpty() {
            return AbstractMultiValuedMap.this.getMap().isEmpty();
        }

        public int size() {
            return AbstractMultiValuedMap.this.size();
        }

        /* access modifiers changed from: protected */
        public int uniqueElements() {
            return AbstractMultiValuedMap.this.getMap().size();
        }

        public int getCount(Object obj) {
            Collection collection = (Collection) AbstractMultiValuedMap.this.getMap().get(obj);
            if (collection != null) {
                return collection.size();
            }
            return 0;
        }

        /* access modifiers changed from: protected */
        public Iterator<MultiSet.Entry<K>> createEntrySetIterator() {
            return IteratorUtils.transformedIterator(AbstractMultiValuedMap.this.map.entrySet().iterator(), new MapEntryTransformer());
        }
    }

    private class MultiValuedMapEntry extends AbstractMapEntry<K, V> {
        public MultiValuedMapEntry(K k, V v) {
            super(k, v);
        }

        public V setValue(V v) {
            throw new UnsupportedOperationException();
        }
    }

    private class MultiValuedMapIterator implements MapIterator<K, V> {
        private Entry<K, V> current = null;

        /* renamed from: it */
        private final Iterator<Entry<K, V>> f788it;

        public MultiValuedMapIterator() {
            this.f788it = AbstractMultiValuedMap.this.entries().iterator();
        }

        public boolean hasNext() {
            return this.f788it.hasNext();
        }

        public K next() {
            this.current = (Entry) this.f788it.next();
            return this.current.getKey();
        }

        public K getKey() {
            Entry<K, V> entry = this.current;
            if (entry != null) {
                return entry.getKey();
            }
            throw new IllegalStateException();
        }

        public V getValue() {
            Entry<K, V> entry = this.current;
            if (entry != null) {
                return entry.getValue();
            }
            throw new IllegalStateException();
        }

        public void remove() {
            this.f788it.remove();
        }

        public V setValue(V v) {
            Entry<K, V> entry = this.current;
            if (entry != null) {
                return entry.setValue(v);
            }
            throw new IllegalStateException();
        }
    }

    private class Values extends AbstractCollection<V> {
        private Values() {
        }

        public Iterator<V> iterator() {
            IteratorChain iteratorChain = new IteratorChain();
            for (Object valuesIterator : AbstractMultiValuedMap.this.keySet()) {
                iteratorChain.addIterator(new ValuesIterator(valuesIterator));
            }
            return iteratorChain;
        }

        public int size() {
            return AbstractMultiValuedMap.this.size();
        }

        public void clear() {
            AbstractMultiValuedMap.this.clear();
        }
    }

    private class ValuesIterator implements Iterator<V> {
        private final Iterator<V> iterator = this.values.iterator();
        private final Object key;
        private final Collection<V> values;

        public ValuesIterator(Object obj) {
            this.key = obj;
            this.values = (Collection) AbstractMultiValuedMap.this.getMap().get(obj);
        }

        public void remove() {
            this.iterator.remove();
            if (this.values.isEmpty()) {
                AbstractMultiValuedMap.this.remove(this.key);
            }
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public V next() {
            return this.iterator.next();
        }
    }

    class WrappedCollection implements Collection<V> {
        protected final K key;

        public WrappedCollection(K k) {
            this.key = k;
        }

        /* access modifiers changed from: protected */
        public Collection<V> getMapping() {
            return (Collection) AbstractMultiValuedMap.this.getMap().get(this.key);
        }

        public boolean add(V v) {
            Collection mapping = getMapping();
            if (mapping == null) {
                mapping = AbstractMultiValuedMap.this.createCollection();
                AbstractMultiValuedMap.this.map.put(this.key, mapping);
            }
            return mapping.add(v);
        }

        public boolean addAll(Collection<? extends V> collection) {
            Collection mapping = getMapping();
            if (mapping == null) {
                mapping = AbstractMultiValuedMap.this.createCollection();
                AbstractMultiValuedMap.this.map.put(this.key, mapping);
            }
            return mapping.addAll(collection);
        }

        public void clear() {
            Collection mapping = getMapping();
            if (mapping != null) {
                mapping.clear();
                AbstractMultiValuedMap.this.remove(this.key);
            }
        }

        public Iterator<V> iterator() {
            if (getMapping() == null) {
                return IteratorUtils.EMPTY_ITERATOR;
            }
            return new ValuesIterator(this.key);
        }

        public int size() {
            Collection mapping = getMapping();
            if (mapping == null) {
                return 0;
            }
            return mapping.size();
        }

        public boolean contains(Object obj) {
            Collection mapping = getMapping();
            if (mapping == null) {
                return false;
            }
            return mapping.contains(obj);
        }

        public boolean containsAll(Collection<?> collection) {
            Collection mapping = getMapping();
            if (mapping == null) {
                return false;
            }
            return mapping.containsAll(collection);
        }

        public boolean isEmpty() {
            Collection mapping = getMapping();
            if (mapping == null) {
                return true;
            }
            return mapping.isEmpty();
        }

        public boolean remove(Object obj) {
            Collection mapping = getMapping();
            if (mapping == null) {
                return false;
            }
            boolean remove = mapping.remove(obj);
            if (mapping.isEmpty()) {
                AbstractMultiValuedMap.this.remove(this.key);
            }
            return remove;
        }

        public boolean removeAll(Collection<?> collection) {
            Collection mapping = getMapping();
            if (mapping == null) {
                return false;
            }
            boolean removeAll = mapping.removeAll(collection);
            if (mapping.isEmpty()) {
                AbstractMultiValuedMap.this.remove(this.key);
            }
            return removeAll;
        }

        public boolean retainAll(Collection<?> collection) {
            Collection mapping = getMapping();
            if (mapping == null) {
                return false;
            }
            boolean retainAll = mapping.retainAll(collection);
            if (mapping.isEmpty()) {
                AbstractMultiValuedMap.this.remove(this.key);
            }
            return retainAll;
        }

        public Object[] toArray() {
            Collection mapping = getMapping();
            if (mapping == null) {
                return CollectionUtils.EMPTY_COLLECTION.toArray();
            }
            return mapping.toArray();
        }

        public <T> T[] toArray(T[] tArr) {
            Collection mapping = getMapping();
            if (mapping == null) {
                return CollectionUtils.EMPTY_COLLECTION.toArray(tArr);
            }
            return mapping.toArray(tArr);
        }

        public String toString() {
            Collection mapping = getMapping();
            if (mapping == null) {
                return CollectionUtils.EMPTY_COLLECTION.toString();
            }
            return mapping.toString();
        }
    }

    /* access modifiers changed from: protected */
    public abstract Collection<V> createCollection();

    protected AbstractMultiValuedMap() {
    }

    protected AbstractMultiValuedMap(Map<K, ? extends Collection<V>> map2) {
        if (map2 != null) {
            this.map = map2;
            return;
        }
        throw new NullPointerException("Map must not be null.");
    }

    /* access modifiers changed from: protected */
    public Map<K, ? extends Collection<V>> getMap() {
        return this.map;
    }

    /* access modifiers changed from: protected */
    public void setMap(Map<K, ? extends Collection<V>> map2) {
        this.map = map2;
    }

    public boolean containsKey(Object obj) {
        return getMap().containsKey(obj);
    }

    public boolean containsValue(Object obj) {
        return values().contains(obj);
    }

    public boolean containsMapping(Object obj, Object obj2) {
        Collection collection = (Collection) getMap().get(obj);
        return collection != null && collection.contains(obj2);
    }

    public Collection<Entry<K, V>> entries() {
        EntryValues entryValues = this.entryValuesView;
        if (entryValues != null) {
            return entryValues;
        }
        EntryValues entryValues2 = new EntryValues<>();
        this.entryValuesView = entryValues2;
        return entryValues2;
    }

    public Collection<V> get(K k) {
        return wrappedCollection(k);
    }

    /* access modifiers changed from: 0000 */
    public Collection<V> wrappedCollection(K k) {
        return new WrappedCollection(k);
    }

    public Collection<V> remove(Object obj) {
        return CollectionUtils.emptyIfNull((Collection) getMap().remove(obj));
    }

    public boolean removeMapping(Object obj, Object obj2) {
        Collection collection = (Collection) getMap().get(obj);
        if (collection == null) {
            return false;
        }
        boolean remove = collection.remove(obj2);
        if (collection.isEmpty()) {
            getMap().remove(obj);
        }
        return remove;
    }

    public boolean isEmpty() {
        return getMap().isEmpty();
    }

    public Set<K> keySet() {
        return getMap().keySet();
    }

    public int size() {
        int i = 0;
        for (Collection size : getMap().values()) {
            i += size.size();
        }
        return i;
    }

    public Collection<V> values() {
        Collection<V> collection = this.valuesView;
        if (collection != null) {
            return collection;
        }
        Values values = new Values();
        this.valuesView = values;
        return values;
    }

    public void clear() {
        getMap().clear();
    }

    public boolean put(K k, V v) {
        Collection collection = (Collection) getMap().get(k);
        if (collection != null) {
            return collection.add(v);
        }
        Collection createCollection = createCollection();
        if (!createCollection.add(v)) {
            return false;
        }
        this.map.put(k, createCollection);
        return true;
    }

    public boolean putAll(Map<? extends K, ? extends V> map2) {
        if (map2 != null) {
            boolean z = false;
            for (Entry entry : map2.entrySet()) {
                z |= put(entry.getKey(), entry.getValue());
            }
            return z;
        }
        throw new NullPointerException("Map must not be null.");
    }

    public boolean putAll(MultiValuedMap<? extends K, ? extends V> multiValuedMap) {
        if (multiValuedMap != null) {
            boolean z = false;
            for (Entry entry : multiValuedMap.entries()) {
                z |= put(entry.getKey(), entry.getValue());
            }
            return z;
        }
        throw new NullPointerException("Map must not be null.");
    }

    public MultiSet<K> keys() {
        if (this.keysMultiSetView == null) {
            this.keysMultiSetView = UnmodifiableMultiSet.unmodifiableMultiSet(new KeysMultiSet());
        }
        return this.keysMultiSetView;
    }

    public Map<K, Collection<V>> asMap() {
        AsMap asMap = this.asMapView;
        if (asMap != null) {
            return asMap;
        }
        AsMap asMap2 = new AsMap<>(this.map);
        this.asMapView = asMap2;
        return asMap2;
    }

    public boolean putAll(K k, Iterable<? extends V> iterable) {
        if (iterable != null) {
            boolean z = true;
            if (iterable instanceof Collection) {
                Collection collection = (Collection) iterable;
                if (collection.isEmpty() || !get(k).addAll(collection)) {
                    z = false;
                }
                return z;
            }
            Iterator it = iterable.iterator();
            if (!it.hasNext() || !CollectionUtils.addAll(get(k), it)) {
                z = false;
            }
            return z;
        }
        throw new NullPointerException("Values must not be null.");
    }

    public MapIterator<K, V> mapIterator() {
        if (size() == 0) {
            return EmptyMapIterator.emptyMapIterator();
        }
        return new MultiValuedMapIterator();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof MultiValuedMap) {
            return asMap().equals(((MultiValuedMap) obj).asMap());
        }
        return false;
    }

    public int hashCode() {
        return getMap().hashCode();
    }

    public String toString() {
        return getMap().toString();
    }

    /* access modifiers changed from: protected */
    public void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.map.size());
        for (Entry entry : this.map.entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeInt(((Collection) entry.getValue()).size());
            for (Object writeObject : (Collection) entry.getValue()) {
                objectOutputStream.writeObject(writeObject);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void doReadObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            Collection collection = get(objectInputStream.readObject());
            int readInt2 = objectInputStream.readInt();
            for (int i2 = 0; i2 < readInt2; i2++) {
                collection.add(objectInputStream.readObject());
            }
        }
    }
}