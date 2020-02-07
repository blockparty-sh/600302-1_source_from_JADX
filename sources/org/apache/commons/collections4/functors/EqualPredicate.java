package org.apache.commons.collections4.functors;

import java.io.Serializable;
import org.apache.commons.collections4.Equator;
import org.apache.commons.collections4.Predicate;

public final class EqualPredicate<T> implements Predicate<T>, Serializable {
    private static final long serialVersionUID = 5633766978029907089L;
    private final Equator<T> equator;
    private final T iValue;

    public static <T> Predicate<T> equalPredicate(T t) {
        if (t == null) {
            return NullPredicate.nullPredicate();
        }
        return new EqualPredicate(t);
    }

    public static <T> Predicate<T> equalPredicate(T t, Equator<T> equator2) {
        if (t == null) {
            return NullPredicate.nullPredicate();
        }
        return new EqualPredicate(t, equator2);
    }

    public EqualPredicate(T t) {
        this(t, null);
    }

    public EqualPredicate(T t, Equator<T> equator2) {
        this.iValue = t;
        this.equator = equator2;
    }

    public boolean evaluate(T t) {
        Equator<T> equator2 = this.equator;
        if (equator2 != null) {
            return equator2.equate(this.iValue, t);
        }
        return this.iValue.equals(t);
    }

    public Object getValue() {
        return this.iValue;
    }
}
