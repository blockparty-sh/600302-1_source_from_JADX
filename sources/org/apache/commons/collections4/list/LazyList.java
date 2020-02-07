package org.apache.commons.collections4.list;

import java.util.List;
import org.apache.commons.collections4.Factory;

public class LazyList<E> extends AbstractSerializableListDecorator<E> {
    private static final long serialVersionUID = -1708388017160694542L;
    private final Factory<? extends E> factory;

    public static <E> LazyList<E> lazyList(List<E> list, Factory<? extends E> factory2) {
        return new LazyList<>(list, factory2);
    }

    protected LazyList(List<E> list, Factory<? extends E> factory2) {
        super(list);
        if (factory2 != null) {
            this.factory = factory2;
            return;
        }
        throw new IllegalArgumentException("Factory must not be null");
    }

    public E get(int i) {
        int size = decorated().size();
        if (i < size) {
            E e = decorated().get(i);
            if (e == null) {
                e = this.factory.create();
                decorated().set(i, e);
            }
            return e;
        }
        while (size < i) {
            decorated().add(null);
            size++;
        }
        E create = this.factory.create();
        decorated().add(create);
        return create;
    }

    public List<E> subList(int i, int i2) {
        return new LazyList(decorated().subList(i, i2), this.factory);
    }
}