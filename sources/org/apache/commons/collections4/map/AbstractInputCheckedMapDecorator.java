package org.apache.commons.collections4.map;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;
import org.apache.commons.collections4.keyvalue.AbstractMapEntryDecorator;
import org.apache.commons.collections4.set.AbstractSetDecorator;

abstract class AbstractInputCheckedMapDecorator<K, V> extends AbstractMapDecorator<K, V> {

    private class EntrySet extends AbstractSetDecorator<Entry<K, V>> {
        private static final long serialVersionUID = 4354731610923110264L;
        private final AbstractInputCheckedMapDecorator<K, V> parent;

        protected EntrySet(Set<Entry<K, V>> set, AbstractInputCheckedMapDecorator<K, V> abstractInputCheckedMapDecorator) {
            super(set);
            this.parent = abstractInputCheckedMapDecorator;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new EntrySetIterator(decorated().iterator(), this.parent);
        }

        public Object[] toArray() {
            Object[] array = decorated().toArray();
            for (int i = 0; i < array.length; i++) {
                array[i] = new MapEntry((Entry) array[i], this.parent);
            }
            return array;
        }

        public <T> T[] toArray(T[] tArr) {
            T[] array = decorated().toArray(tArr.length > 0 ? (Object[]) Array.newInstance(tArr.getClass().getComponentType(), 0) : tArr);
            for (int i = 0; i < array.length; i++) {
                array[i] = new MapEntry((Entry) array[i], this.parent);
            }
            if (array.length > tArr.length) {
                return (Object[]) array;
            }
            System.arraycopy(array, 0, tArr, 0, array.length);
            if (tArr.length > array.length) {
                tArr[array.length] = null;
            }
            return tArr;
        }
    }

    private class EntrySetIterator extends AbstractIteratorDecorator<Entry<K, V>> {
        private final AbstractInputCheckedMapDecorator<K, V> parent;

        protected EntrySetIterator(Iterator<Entry<K, V>> it, AbstractInputCheckedMapDecorator<K, V> abstractInputCheckedMapDecorator) {
            super(it);
            this.parent = abstractInputCheckedMapDecorator;
        }

        public Entry<K, V> next() {
            return new MapEntry((Entry) getIterator().next(), this.parent);
        }
    }

    private class MapEntry extends AbstractMapEntryDecorator<K, V> {
        private final AbstractInputCheckedMapDecorator<K, V> parent;

        protected MapEntry(Entry<K, V> entry, AbstractInputCheckedMapDecorator<K, V> abstractInputCheckedMapDecorator) {
            super(entry);
            this.parent = abstractInputCheckedMapDecorator;
        }

        public V setValue(V v) {
            return getMapEntry().setValue(this.parent.checkSetValue(v));
        }
    }

    /* access modifiers changed from: protected */
    public abstract V checkSetValue(V v);

    /* access modifiers changed from: protected */
    public boolean isSetValueChecking() {
        return true;
    }

    protected AbstractInputCheckedMapDecorator() {
    }

    protected AbstractInputCheckedMapDecorator(Map<K, V> map) {
        super(map);
    }

    public Set<Entry<K, V>> entrySet() {
        if (isSetValueChecking()) {
            return new EntrySet(this.map.entrySet(), this);
        }
        return this.map.entrySet();
    }
}
