package org.apache.commons.collections4.comparators;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;

public class ComparatorChain<E> implements Comparator<E>, Serializable {
    private static final long serialVersionUID = -721644942746081630L;
    private final List<Comparator<E>> comparatorChain;
    private boolean isLocked;
    private BitSet orderingBits;

    public ComparatorChain() {
        this((List<Comparator<E>>) new ArrayList<Comparator<E>>(), new BitSet());
    }

    public ComparatorChain(Comparator<E> comparator) {
        this(comparator, false);
    }

    public ComparatorChain(Comparator<E> comparator, boolean z) {
        this.orderingBits = null;
        this.isLocked = false;
        this.comparatorChain = new ArrayList(1);
        this.comparatorChain.add(comparator);
        this.orderingBits = new BitSet(1);
        if (z) {
            this.orderingBits.set(0);
        }
    }

    public ComparatorChain(List<Comparator<E>> list) {
        this(list, new BitSet(list.size()));
    }

    public ComparatorChain(List<Comparator<E>> list, BitSet bitSet) {
        this.orderingBits = null;
        this.isLocked = false;
        this.comparatorChain = list;
        this.orderingBits = bitSet;
    }

    public void addComparator(Comparator<E> comparator) {
        addComparator(comparator, false);
    }

    public void addComparator(Comparator<E> comparator, boolean z) {
        checkLocked();
        this.comparatorChain.add(comparator);
        if (z) {
            this.orderingBits.set(this.comparatorChain.size() - 1);
        }
    }

    public void setComparator(int i, Comparator<E> comparator) throws IndexOutOfBoundsException {
        setComparator(i, comparator, false);
    }

    public void setComparator(int i, Comparator<E> comparator, boolean z) {
        checkLocked();
        this.comparatorChain.set(i, comparator);
        if (z) {
            this.orderingBits.set(i);
        } else {
            this.orderingBits.clear(i);
        }
    }

    public void setForwardSort(int i) {
        checkLocked();
        this.orderingBits.clear(i);
    }

    public void setReverseSort(int i) {
        checkLocked();
        this.orderingBits.set(i);
    }

    public int size() {
        return this.comparatorChain.size();
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    private void checkLocked() {
        if (this.isLocked) {
            throw new UnsupportedOperationException("Comparator ordering cannot be changed after the first comparison is performed");
        }
    }

    private void checkChainIntegrity() {
        if (this.comparatorChain.size() == 0) {
            throw new UnsupportedOperationException("ComparatorChains must contain at least one Comparator");
        }
    }

    public int compare(E e, E e2) throws UnsupportedOperationException {
        int i = 1;
        if (!this.isLocked) {
            checkChainIntegrity();
            this.isLocked = true;
        }
        int i2 = 0;
        for (Comparator compare : this.comparatorChain) {
            int compare2 = compare.compare(e, e2);
            if (compare2 != 0) {
                if (!this.orderingBits.get(i2)) {
                    i = compare2;
                } else if (compare2 > 0) {
                    i = -1;
                }
                return i;
            }
            i2++;
        }
        return 0;
    }

    public int hashCode() {
        List<Comparator<E>> list = this.comparatorChain;
        int i = 0;
        if (list != null) {
            i = 0 ^ list.hashCode();
        }
        BitSet bitSet = this.orderingBits;
        return bitSet != null ? i ^ bitSet.hashCode() : i;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        ComparatorChain comparatorChain2 = (ComparatorChain) obj;
        BitSet bitSet = this.orderingBits;
        if (bitSet != null ? bitSet.equals(comparatorChain2.orderingBits) : comparatorChain2.orderingBits == null) {
            List<Comparator<E>> list = this.comparatorChain;
            if (list != null) {
            }
        }
        z = false;
        return z;
    }
}