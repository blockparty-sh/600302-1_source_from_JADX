package org.apache.commons.collections4.bag;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.collections4.Bag;
import org.apache.commons.collections4.set.UnmodifiableSet;

public abstract class AbstractMapBag<E> implements Bag<E> {
    /* access modifiers changed from: private */
    public transient Map<E, MutableInteger> map;
    /* access modifiers changed from: private */
    public transient int modCount;
    /* access modifiers changed from: private */
    public int size;
    private transient Set<E> uniqueSet;

    static class BagIterator<E> implements Iterator<E> {
        private boolean canRemove;
        private Entry<E, MutableInteger> current = null;
        private final Iterator<Entry<E, MutableInteger>> entryIterator;
        private int itemCount;
        private final int mods;
        private final AbstractMapBag<E> parent;

        public BagIterator(AbstractMapBag<E> abstractMapBag) {
            this.parent = abstractMapBag;
            this.entryIterator = abstractMapBag.map.entrySet().iterator();
            this.mods = abstractMapBag.modCount;
            this.canRemove = false;
        }

        public boolean hasNext() {
            return this.itemCount > 0 || this.entryIterator.hasNext();
        }

        public E next() {
            if (this.parent.modCount == this.mods) {
                if (this.itemCount == 0) {
                    this.current = (Entry) this.entryIterator.next();
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

    protected AbstractMapBag() {
    }

    protected AbstractMapBag(Map<E, MutableInteger> map2) {
        this.map = map2;
    }

    /* access modifiers changed from: protected */
    public Map<E, MutableInteger> getMap() {
        return this.map;
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

    public boolean containsAll(Collection<?> collection) {
        if (collection instanceof Bag) {
            return containsAll((Bag) collection);
        }
        return containsAll((Bag<?>) new HashBag<Object>(collection));
    }

    /* access modifiers changed from: 0000 */
    public boolean containsAll(Bag<?> bag) {
        for (Object next : bag.uniqueSet()) {
            if (getCount(next) < bag.getCount(next)) {
                return false;
            }
        }
        return true;
    }

    public Iterator<E> iterator() {
        return new BagIterator(this);
    }

    public boolean add(E e) {
        return add(e, 1);
    }

    public boolean add(E e, int i) {
        this.modCount++;
        if (i > 0) {
            MutableInteger mutableInteger = (MutableInteger) this.map.get(e);
            this.size += i;
            if (mutableInteger == null) {
                this.map.put(e, new MutableInteger(i));
                return true;
            }
            mutableInteger.value += i;
        }
        return false;
    }

    public boolean addAll(Collection<? extends E> collection) {
        Iterator it = collection.iterator();
        while (true) {
            boolean z = false;
            while (true) {
                if (!it.hasNext()) {
                    return z;
                }
                boolean add = add(it.next());
                if (z || add) {
                    z = true;
                }
            }
        }
    }

    public void clear() {
        this.modCount++;
        this.map.clear();
        this.size = 0;
    }

    public boolean remove(Object obj) {
        MutableInteger mutableInteger = (MutableInteger) this.map.get(obj);
        if (mutableInteger == null) {
            return false;
        }
        this.modCount++;
        this.map.remove(obj);
        this.size -= mutableInteger.value;
        return true;
    }

    public boolean remove(Object obj, int i) {
        MutableInteger mutableInteger = (MutableInteger) this.map.get(obj);
        if (mutableInteger == null || i <= 0) {
            return false;
        }
        this.modCount++;
        if (i < mutableInteger.value) {
            mutableInteger.value -= i;
            this.size -= i;
        } else {
            this.map.remove(obj);
            this.size -= mutableInteger.value;
        }
        return true;
    }

    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            return false;
        }
        Iterator it = collection.iterator();
        while (true) {
            boolean z = false;
            while (true) {
                if (!it.hasNext()) {
                    return z;
                }
                boolean remove = remove(it.next(), 1);
                if (z || remove) {
                    z = true;
                }
            }
        }
    }

    public boolean retainAll(Collection<?> collection) {
        if (collection instanceof Bag) {
            return retainAll((Bag) collection);
        }
        return retainAll((Bag<?>) new HashBag<Object>(collection));
    }

    /* access modifiers changed from: 0000 */
    public boolean retainAll(Bag<?> bag) {
        HashBag hashBag = new HashBag();
        for (Object next : uniqueSet()) {
            int count = getCount(next);
            int count2 = bag.getCount(next);
            if (1 > count2 || count2 > count) {
                hashBag.add(next, count);
            } else {
                hashBag.add(next, count - count2);
            }
        }
        if (!hashBag.isEmpty()) {
            return removeAll(hashBag);
        }
        return false;
    }

    public Object[] toArray() {
        Object[] objArr = new Object[size()];
        int i = 0;
        for (Object next : this.map.keySet()) {
            int count = getCount(next);
            while (count > 0) {
                int i2 = i + 1;
                objArr[i] = next;
                count--;
                i = i2;
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
        for (T next : this.map.keySet()) {
            int count = getCount(next);
            while (count > 0) {
                int i2 = i + 1;
                tArr[i] = next;
                count--;
                i = i2;
            }
        }
        while (i < tArr.length) {
            int i3 = i + 1;
            tArr[i] = null;
            i = i3;
        }
        return tArr;
    }

    public Set<E> uniqueSet() {
        if (this.uniqueSet == null) {
            this.uniqueSet = UnmodifiableSet.unmodifiableSet(this.map.keySet());
        }
        return this.uniqueSet;
    }

    /* access modifiers changed from: protected */
    public void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(this.map.size());
        for (Entry entry : this.map.entrySet()) {
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeInt(((MutableInteger) entry.getValue()).value);
        }
    }

    /* access modifiers changed from: protected */
    public void doReadObject(Map<E, MutableInteger> map2, ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.map = map2;
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            Object readObject = objectInputStream.readObject();
            int readInt2 = objectInputStream.readInt();
            map2.put(readObject, new MutableInteger(readInt2));
            this.size += readInt2;
        }
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Bag)) {
            return false;
        }
        Bag bag = (Bag) obj;
        if (bag.size() != size()) {
            return false;
        }
        for (Object next : this.map.keySet()) {
            if (bag.getCount(next) != getCount(next)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        for (Entry entry : this.map.entrySet()) {
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

    public String toString() {
        if (size() == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Iterator it = uniqueSet().iterator();
        while (it.hasNext()) {
            Object next = it.next();
            sb.append(getCount(next));
            sb.append(':');
            sb.append(next);
            if (it.hasNext()) {
                sb.append(',');
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
