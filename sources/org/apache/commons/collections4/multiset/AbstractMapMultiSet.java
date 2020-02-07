package org.apache.commons.collections4.multiset;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiSet.Entry;
import org.apache.commons.collections4.iterators.AbstractIteratorDecorator;

public abstract class AbstractMapMultiSet<E> extends AbstractMultiSet<E> {
    /* access modifiers changed from: private */
    public transient Map<E, MutableInteger> map;
    /* access modifiers changed from: private */
    public transient int modCount;
    /* access modifiers changed from: private */
    public transient int size;

    protected static class EntrySetIterator<E> implements Iterator<Entry<E>> {
        protected boolean canRemove = false;
        protected final Iterator<Map.Entry<E, MutableInteger>> decorated;
        protected Entry<E> last = null;
        protected final AbstractMapMultiSet<E> parent;

        protected EntrySetIterator(Iterator<Map.Entry<E, MutableInteger>> it, AbstractMapMultiSet<E> abstractMapMultiSet) {
            this.decorated = it;
            this.parent = abstractMapMultiSet;
        }

        public boolean hasNext() {
            return this.decorated.hasNext();
        }

        public Entry<E> next() {
            this.last = new MultiSetEntry((Map.Entry) this.decorated.next());
            this.canRemove = true;
            return this.last;
        }

        public void remove() {
            if (this.canRemove) {
                this.decorated.remove();
                this.last = null;
                this.canRemove = false;
                return;
            }
            throw new IllegalStateException("Iterator remove() can only be called once after next()");
        }
    }

    private static class MapBasedMultiSetIterator<E> implements Iterator<E> {
        private boolean canRemove;
        private Map.Entry<E, MutableInteger> current = null;
        private final Iterator<Map.Entry<E, MutableInteger>> entryIterator;
        private int itemCount;
        private final int mods;
        private final AbstractMapMultiSet<E> parent;

        public MapBasedMultiSetIterator(AbstractMapMultiSet<E> abstractMapMultiSet) {
            this.parent = abstractMapMultiSet;
            this.entryIterator = abstractMapMultiSet.map.entrySet().iterator();
            this.mods = abstractMapMultiSet.modCount;
            this.canRemove = false;
        }

        public boolean hasNext() {
            return this.itemCount > 0 || this.entryIterator.hasNext();
        }

        public E next() {
            if (this.parent.modCount == this.mods) {
                if (this.itemCount == 0) {
                    this.current = (Map.Entry) this.entryIterator.next();
                    this.itemCount = ((MutableInteger) this.current.getValue()).value;
                }
                this.canRemove = true;
                this.itemCount--;
                return this.current.getKey();
            }
            throw new ConcurrentModificationException();
        }

        public void remove() {
            if (this.parent.modCount != this.mods) {
                throw new ConcurrentModificationException();
            } else if (this.canRemove) {
                MutableInteger mutableInteger = (MutableInteger) this.current.getValue();
                if (mutableInteger.value > 1) {
                    mutableInteger.value--;
                } else {
                    this.entryIterator.remove();
                }
                this.parent.size = this.parent.size - 1;
                this.canRemove = false;
            } else {
                throw new IllegalStateException();
            }
        }
    }

    protected static class MultiSetEntry<E> extends AbstractEntry<E> {
        protected final Map.Entry<E, MutableInteger> parentEntry;

        protected MultiSetEntry(Map.Entry<E, MutableInteger> entry) {
            this.parentEntry = entry;
        }

        public E getElement() {
            return this.parentEntry.getKey();
        }

        public int getCount() {
            return ((MutableInteger) this.parentEntry.getValue()).value;
        }
    }

    protected static class MutableInteger {
        protected int value;

        MutableInteger(int i) {
            this.value = i;
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof MutableInteger)) {
                return false;
            }
            if (((MutableInteger) obj).value == this.value) {
                z = true;
            }
            return z;
        }

        public int hashCode() {
            return this.value;
        }
    }

    protected static class UniqueSetIterator<E> extends AbstractIteratorDecorator<E> {
        protected boolean canRemove = false;
        protected E lastElement = null;
        protected final AbstractMapMultiSet<E> parent;

        protected UniqueSetIterator(Iterator<E> it, AbstractMapMultiSet<E> abstractMapMultiSet) {
            super(it);
            this.parent = abstractMapMultiSet;
        }

        public E next() {
            this.lastElement = super.next();
            this.canRemove = true;
            return this.lastElement;
        }

        public void remove() {
            if (this.canRemove) {
                int count = this.parent.getCount(this.lastElement);
                super.remove();
                this.parent.remove(this.lastElement, count);
                this.lastElement = null;
                this.canRemove = false;
                return;
            }
            throw new IllegalStateException("Iterator remove() can only be called once after next()");
        }
    }

    protected AbstractMapMultiSet() {
    }

    protected AbstractMapMultiSet(Map<E, MutableInteger> map2) {
        this.map = map2;
    }

    /* access modifiers changed from: protected */
    public Map<E, MutableInteger> getMap() {
        return this.map;
    }

    /* access modifiers changed from: protected */
    public void setMap(Map<E, MutableInteger> map2) {
        this.map = map2;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public int getCount(Object obj) {
        MutableInteger mutableInteger = (MutableInteger) this.map.get(obj);
        if (mutableInteger != null) {
            return mutableInteger.value;
        }
        return 0;
    }

    public boolean contains(Object obj) {
        return this.map.containsKey(obj);
    }

    public Iterator<E> iterator() {
        return new MapBasedMultiSetIterator(this);
    }

    public int add(E e, int i) {
        if (i >= 0) {
            MutableInteger mutableInteger = (MutableInteger) this.map.get(e);
            int i2 = mutableInteger != null ? mutableInteger.value : 0;
            if (i > 0) {
                this.modCount++;
                this.size += i;
                if (mutableInteger == null) {
                    this.map.put(e, new MutableInteger(i));
                } else {
                    mutableInteger.value += i;
                }
            }
            return i2;
        }
        throw new IllegalArgumentException("Occurrences must not be negative.");
    }

    public void clear() {
        this.modCount++;
        this.map.clear();
        this.size = 0;
    }

    public int remove(Object obj, int i) {
        if (i >= 0) {
            MutableInteger mutableInteger = (MutableInteger) this.map.get(obj);
            if (mutableInteger == null) {
                return 0;
            }
            int i2 = mutableInteger.value;
            if (i > 0) {
                this.modCount++;
                if (i < mutableInteger.value) {
                    mutableInteger.value -= i;
                    this.size -= i;
                } else {
                    this.map.remove(obj);
                    this.size -= mutableInteger.value;
                }
            }
            return i2;
        }
        throw new IllegalArgumentException("Occurrences must not be negative.");
    }

    /* access modifiers changed from: protected */
    public Iterator<E> createUniqueSetIterator() {
        return new UniqueSetIterator(getMap().keySet().iterator(), this);
    }

    /* access modifiers changed from: protected */
    public int uniqueElements() {
        return this.map.size();
    }

    /* access modifiers changed from: protected */
    public Iterator<Entry<E>> createEntrySetIterator() {
        return new EntrySetIterator(this.map.entrySet().iterator(), this);
    }

    /* access modifiers changed from: protected */
    public void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.map.size());
        for (Map.Entry entry : this.map.entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeInt(((MutableInteger) entry.getValue()).value);
        }
    }

    /* access modifiers changed from: protected */
    public void doReadObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            Object readObject = objectInputStream.readObject();
            int readInt2 = objectInputStream.readInt();
            this.map.put(readObject, new MutableInteger(readInt2));
            this.size += readInt2;
        }
    }

    public Object[] toArray() {
        Object[] objArr = new Object[size()];
        int i = 0;
        for (Map.Entry entry : this.map.entrySet()) {
            Object key = entry.getKey();
            int i2 = ((MutableInteger) entry.getValue()).value;
            while (i2 > 0) {
                int i3 = i + 1;
                objArr[i] = key;
                i2--;
                i = i3;
            }
        }
        return objArr;
    }

    public <T> T[] toArray(T[] tArr) {
        int size2 = size();
        if (tArr.length < size2) {
            tArr = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size2);
        }
        int i = 0;
        for (Map.Entry entry : this.map.entrySet()) {
            T key = entry.getKey();
            int i2 = ((MutableInteger) entry.getValue()).value;
            while (i2 > 0) {
                int i3 = i + 1;
                tArr[i] = key;
                i2--;
                i = i3;
            }
        }
        while (i < tArr.length) {
            int i4 = i + 1;
            tArr[i] = null;
            i = i4;
        }
        return tArr;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MultiSet)) {
            return false;
        }
        MultiSet multiSet = (MultiSet) obj;
        if (multiSet.size() != size()) {
            return false;
        }
        for (Object next : this.map.keySet()) {
            if (multiSet.getCount(next) != getCount(next)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        for (Map.Entry entry : this.map.entrySet()) {
            Object key = entry.getKey();
            MutableInteger mutableInteger = (MutableInteger) entry.getValue();
            if (key == null) {
                i = 0;
            } else {
                i = key.hashCode();
            }
            i2 += mutableInteger.value ^ i;
        }
        return i2;
    }
}
