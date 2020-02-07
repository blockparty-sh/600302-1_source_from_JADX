package org.apache.commons.collections4.list;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.apache.commons.collections4.BoundedCollection;
import org.apache.commons.collections4.iterators.AbstractListIteratorDecorator;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;

public class FixedSizeList<E> extends AbstractSerializableListDecorator<E> implements BoundedCollection<E> {
    private static final long serialVersionUID = -2218010673611160319L;

    private class FixedSizeListIterator extends AbstractListIteratorDecorator<E> {
        protected FixedSizeListIterator(ListIterator<E> listIterator) {
            super(listIterator);
        }

        public void remove() {
            throw FixedSizeList.unsupportedOperationException();
        }

        public void add(Object obj) {
            throw FixedSizeList.unsupportedOperationException();
        }
    }

    public boolean isFull() {
        return true;
    }

    public static <E> FixedSizeList<E> fixedSizeList(List<E> list) {
        return new FixedSizeList<>(list);
    }

    protected FixedSizeList(List<E> list) {
        super(list);
    }

    public boolean add(E e) {
        throw unsupportedOperationException();
    }

    public void add(int i, E e) {
        throw unsupportedOperationException();
    }

    public boolean addAll(Collection<? extends E> collection) {
        throw unsupportedOperationException();
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        throw unsupportedOperationException();
    }

    public void clear() {
        throw unsupportedOperationException();
    }

    public E get(int i) {
        return decorated().get(i);
    }

    public int indexOf(Object obj) {
        return decorated().indexOf(obj);
    }

    public Iterator<E> iterator() {
        return UnmodifiableIterator.unmodifiableIterator(decorated().iterator());
    }

    public int lastIndexOf(Object obj) {
        return decorated().lastIndexOf(obj);
    }

    public ListIterator<E> listIterator() {
        return new FixedSizeListIterator(decorated().listIterator(0));
    }

    public ListIterator<E> listIterator(int i) {
        return new FixedSizeListIterator(decorated().listIterator(i));
    }

    public E remove(int i) {
        throw unsupportedOperationException();
    }

    public boolean remove(Object obj) {
        throw unsupportedOperationException();
    }

    public boolean removeAll(Collection<?> collection) {
        throw unsupportedOperationException();
    }

    public boolean retainAll(Collection<?> collection) {
        throw unsupportedOperationException();
    }

    public E set(int i, E e) {
        return decorated().set(i, e);
    }

    public List<E> subList(int i, int i2) {
        return new FixedSizeList(decorated().subList(i, i2));
    }

    public int maxSize() {
        return size();
    }

    /* access modifiers changed from: private */
    public static UnsupportedOperationException unsupportedOperationException() {
        return new UnsupportedOperationException("List is fixed size");
    }
}
