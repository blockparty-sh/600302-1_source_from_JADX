package org.apache.commons.collections4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.collections4.iterators.SingletonIterator;

public class FluentIterable<E> implements Iterable<E> {
    private final Iterable<E> iterable;

    public static <T> FluentIterable<T> empty() {
        return IterableUtils.EMPTY_ITERABLE;
    }

    /* renamed from: of */
    public static <T> FluentIterable<T> m333of(T t) {
        return m332of(IteratorUtils.asIterable(new SingletonIterator(t, false)));
    }

    /* renamed from: of */
    public static <T> FluentIterable<T> m334of(T... tArr) {
        return m332of((Iterable<T>) Arrays.asList(tArr));
    }

    /* renamed from: of */
    public static <T> FluentIterable<T> m332of(Iterable<T> iterable2) {
        IterableUtils.checkNotNull(iterable2);
        if (iterable2 instanceof FluentIterable) {
            return (FluentIterable) iterable2;
        }
        return new FluentIterable<>(iterable2);
    }

    FluentIterable() {
        this.iterable = this;
    }

    private FluentIterable(Iterable<E> iterable2) {
        this.iterable = iterable2;
    }

    public FluentIterable<E> append(E... eArr) {
        return append((Iterable<? extends E>) Arrays.asList(eArr));
    }

    public FluentIterable<E> append(Iterable<? extends E> iterable2) {
        return m332of(IterableUtils.chainedIterable(this.iterable, iterable2));
    }

    public FluentIterable<E> collate(Iterable<? extends E> iterable2) {
        return m332of(IterableUtils.collatedIterable(this.iterable, iterable2));
    }

    public FluentIterable<E> collate(Iterable<? extends E> iterable2, Comparator<? super E> comparator) {
        return m332of(IterableUtils.collatedIterable(comparator, this.iterable, iterable2));
    }

    public FluentIterable<E> eval() {
        return m332of((Iterable<T>) toList());
    }

    public FluentIterable<E> filter(Predicate<? super E> predicate) {
        return m332of(IterableUtils.filteredIterable(this.iterable, predicate));
    }

    public FluentIterable<E> limit(long j) {
        return m332of(IterableUtils.boundedIterable(this.iterable, j));
    }

    public FluentIterable<E> loop() {
        return m332of(IterableUtils.loopingIterable(this.iterable));
    }

    public FluentIterable<E> reverse() {
        return m332of(IterableUtils.reversedIterable(this.iterable));
    }

    public FluentIterable<E> skip(long j) {
        return m332of(IterableUtils.skippingIterable(this.iterable, j));
    }

    public <O> FluentIterable<O> transform(Transformer<? super E, ? extends O> transformer) {
        return m332of(IterableUtils.transformedIterable(this.iterable, transformer));
    }

    public FluentIterable<E> unique() {
        return m332of(IterableUtils.uniqueIterable(this.iterable));
    }

    public FluentIterable<E> unmodifiable() {
        return m332of(IterableUtils.unmodifiableIterable(this.iterable));
    }

    public FluentIterable<E> zip(Iterable<? extends E> iterable2) {
        return m332of(IterableUtils.zippingIterable(this.iterable, iterable2));
    }

    public FluentIterable<E> zip(Iterable<? extends E>... iterableArr) {
        return m332of(IterableUtils.zippingIterable(this.iterable, iterableArr));
    }

    public Iterator<E> iterator() {
        return this.iterable.iterator();
    }

    public Enumeration<E> asEnumeration() {
        return IteratorUtils.asEnumeration(iterator());
    }

    public boolean allMatch(Predicate<? super E> predicate) {
        return IterableUtils.matchesAll(this.iterable, predicate);
    }

    public boolean anyMatch(Predicate<? super E> predicate) {
        return IterableUtils.matchesAny(this.iterable, predicate);
    }

    public boolean isEmpty() {
        return IterableUtils.isEmpty(this.iterable);
    }

    public boolean contains(Object obj) {
        return IterableUtils.contains(this.iterable, obj);
    }

    public void forEach(Closure<? super E> closure) {
        IterableUtils.forEach(this.iterable, closure);
    }

    public E get(int i) {
        return IterableUtils.get(this.iterable, i);
    }

    public int size() {
        return IterableUtils.size(this.iterable);
    }

    public void copyInto(Collection<? super E> collection) {
        if (collection != null) {
            CollectionUtils.addAll(collection, this.iterable);
            return;
        }
        throw new NullPointerException("Collection must not be null");
    }

    public E[] toArray(Class<E> cls) {
        return IteratorUtils.toArray(iterator(), cls);
    }

    public List<E> toList() {
        return IterableUtils.toList(this.iterable);
    }

    public String toString() {
        return IterableUtils.toString(this.iterable);
    }
}
