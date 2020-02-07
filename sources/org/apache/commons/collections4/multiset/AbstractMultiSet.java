package org.apache.commons.collections4.multiset;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.commons.collections4.MultiSet;
import org.apache.commons.collections4.MultiSet.Entry;
import org.apache.commons.collections4.Transformer;

public abstract class AbstractMultiSet<E> extends AbstractCollection<E> implements MultiSet<E> {
    private transient Set<Entry<E>> entrySet;
    private transient Set<E> uniqueSet;

    protected static abstract class AbstractEntry<E> implements Entry<E> {
        protected AbstractEntry() {
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            Object element = getElement();
            Object element2 = entry.getElement();
            if (getCount() != entry.getCount()) {
                return false;
            }
            if (element == element2 || (element != null && element.equals(element2))) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int i;
            Object element = getElement();
            if (element == null) {
                i = 0;
            } else {
                i = element.hashCode();
            }
            return i ^ getCount();
        }

        public String toString() {
            return String.format("%s:%d", new Object[]{getElement(), Integer.valueOf(getCount())});
        }
    }

    protected static class EntrySet<E> extends AbstractSet<Entry<E>> {
        private final AbstractMultiSet<E> parent;

        protected EntrySet(AbstractMultiSet<E> abstractMultiSet) {
            this.parent = abstractMultiSet;
        }

        public int size() {
            return this.parent.uniqueElements();
        }

        public Iterator<Entry<E>> iterator() {
            return this.parent.createEntrySetIterator();
        }

        public boolean contains(Object obj) {
            boolean z = false;
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (this.parent.getCount(entry.getElement()) == entry.getCount()) {
                z = true;
            }
            return z;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            Object element = entry.getElement();
            if (this.parent.contains(element)) {
                int count = this.parent.getCount(element);
                if (entry.getCount() == count) {
                    this.parent.remove(element, count);
                    return true;
                }
            }
            return false;
        }
    }

    private static class MultiSetIterator<E> implements Iterator<E> {
        private boolean canRemove = false;
        private Entry<E> current = null;
        private final Iterator<Entry<E>> entryIterator;
        private int itemCount;
        private final AbstractMultiSet<E> parent;

        public MultiSetIterator(AbstractMultiSet<E> abstractMultiSet) {
            this.parent = abstractMultiSet;
            this.entryIterator = abstractMultiSet.entrySet().iterator();
        }

        public boolean hasNext() {
            return this.itemCount > 0 || this.entryIterator.hasNext();
        }

        public E next() {
            if (this.itemCount == 0) {
                this.current = (Entry) this.entryIterator.next();
                this.itemCount = this.current.getCount();
            }
            this.canRemove = true;
            this.itemCount--;
            return this.current.getElement();
        }

        public void remove() {
            if (this.canRemove) {
                if (this.current.getCount() > 1) {
                    this.parent.remove(this.current.getElement());
                } else {
                    this.entryIterator.remove();
                }
                this.canRemove = false;
                return;
            }
            throw new IllegalStateException();
        }
    }

    protected static class UniqueSet<E> extends AbstractSet<E> {
        protected final AbstractMultiSet<E> parent;

        protected UniqueSet(AbstractMultiSet<E> abstractMultiSet) {
            this.parent = abstractMultiSet;
        }

        public Iterator<E> iterator() {
            return this.parent.createUniqueSetIterator();
        }

        public boolean contains(Object obj) {
            return this.parent.contains(obj);
        }

        public boolean containsAll(Collection<?> collection) {
            return this.parent.containsAll(collection);
        }

        public boolean remove(Object obj) {
            AbstractMultiSet<E> abstractMultiSet = this.parent;
            return abstractMultiSet.remove(obj, abstractMultiSet.getCount(obj)) != 0;
        }

        public int size() {
            return this.parent.uniqueElements();
        }

        public void clear() {
            this.parent.clear();
        }
    }

    /* access modifiers changed from: protected */
    public abstract Iterator<Entry<E>> createEntrySetIterator();

    /* access modifiers changed from: protected */
    public abstract int uniqueElements();

    protected AbstractMultiSet() {
    }

    public int size() {
        int i = 0;
        for (Entry count : entrySet()) {
            i += count.getCount();
        }
        return i;
    }

    public int getCount(Object obj) {
        for (Entry entry : entrySet()) {
            Object element = entry.getElement();
            if (element == obj || (element != null && element.equals(obj))) {
                return entry.getCount();
            }
        }
        return 0;
    }

    public int setCount(E e, int i) {
        if (i >= 0) {
            int count = getCount(e);
            if (count < i) {
                add(e, i - count);
            } else {
                remove(e, count - i);
            }
            return count;
        }
        throw new IllegalArgumentException("Count must not be negative.");
    }

    public boolean contains(Object obj) {
        return getCount(obj) > 0;
    }

    public Iterator<E> iterator() {
        return new MultiSetIterator(this);
    }

    public boolean add(E e) {
        add(e, 1);
        return true;
    }

    public int add(E e, int i) {
        throw new UnsupportedOperationException();
    }

    public void clear() {
        Iterator it = entrySet().iterator();
        while (it.hasNext()) {
            it.next();
            it.remove();
        }
    }

    public boolean remove(Object obj) {
        return remove(obj, 1) != 0;
    }

    public int remove(Object obj, int i) {
        throw new UnsupportedOperationException();
    }

    public boolean removeAll(Collection<?> collection) {
        Iterator it = collection.iterator();
        while (true) {
            boolean z = false;
            while (true) {
                if (!it.hasNext()) {
                    return z;
                }
                Object next = it.next();
                boolean z2 = remove(next, getCount(next)) != 0;
                if (z || z2) {
                    z = true;
                }
            }
        }
    }

    public Set<E> uniqueSet() {
        if (this.uniqueSet == null) {
            this.uniqueSet = createUniqueSet();
        }
        return this.uniqueSet;
    }

    /* access modifiers changed from: protected */
    public Set<E> createUniqueSet() {
        return new UniqueSet(this);
    }

    /* access modifiers changed from: protected */
    public Iterator<E> createUniqueSetIterator() {
        return IteratorUtils.transformedIterator(entrySet().iterator(), new Transformer<Entry<E>, E>() {
            public E transform(Entry<E> entry) {
                return entry.getElement();
            }
        });
    }

    public Set<Entry<E>> entrySet() {
        if (this.entrySet == null) {
            this.entrySet = createEntrySet();
        }
        return this.entrySet;
    }

    /* access modifiers changed from: protected */
    public Set<Entry<E>> createEntrySet() {
        return new EntrySet(this);
    }

    /* access modifiers changed from: protected */
    public void doWriteObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeInt(entrySet().size());
        for (Entry entry : entrySet()) {
            objectOutputStream.writeObject(entry.getElement());
            objectOutputStream.writeInt(entry.getCount());
        }
    }

    /* access modifiers changed from: protected */
    public void doReadObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        int readInt = objectInputStream.readInt();
        for (int i = 0; i < readInt; i++) {
            setCount(objectInputStream.readObject(), objectInputStream.readInt());
        }
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
        for (Entry entry : entrySet()) {
            if (multiSet.getCount(entry.getElement()) != getCount(entry.getElement())) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return entrySet().hashCode();
    }

    public String toString() {
        return entrySet().toString();
    }
}
