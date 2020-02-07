package org.apache.commons.collections4.comparators;

import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.collections4.ComparatorUtils;

public class ReverseComparator<E> implements Comparator<E>, Serializable {
    private static final long serialVersionUID = 2858887242028539265L;
    private final Comparator<? super E> comparator;

    public ReverseComparator() {
        this(null);
    }

    public ReverseComparator(Comparator<? super E> comparator2) {
        if (comparator2 == null) {
            comparator2 = ComparatorUtils.NATURAL_COMPARATOR;
        }
        this.comparator = comparator2;
    }

    public int compare(E e, E e2) {
        return this.comparator.compare(e2, e);
    }

    public int hashCode() {
        return "ReverseComparator".hashCode() ^ this.comparator.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        return this.comparator.equals(((ReverseComparator) obj).comparator);
    }
}
