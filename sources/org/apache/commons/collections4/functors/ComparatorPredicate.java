package org.apache.commons.collections4.functors;

import java.io.Serializable;
import java.util.Comparator;
import org.apache.commons.collections4.Predicate;

public class ComparatorPredicate<T> implements Predicate<T>, Serializable {
    private static final long serialVersionUID = -1863209236504077399L;
    private final Comparator<T> comparator;
    private final Criterion criterion;
    private final T object;

    /* renamed from: org.apache.commons.collections4.functors.ComparatorPredicate$1 */
    static /* synthetic */ class C33571 {

        /* renamed from: $SwitchMap$org$apache$commons$collections4$functors$ComparatorPredicate$Criterion */
        static final /* synthetic */ int[] f787xaf4caad = new int[Criterion.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(12:0|1|2|3|4|5|6|7|8|9|10|12) */
        /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0035 */
        static {
            /*
                org.apache.commons.collections4.functors.ComparatorPredicate$Criterion[] r0 = org.apache.commons.collections4.functors.ComparatorPredicate.Criterion.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f787xaf4caad = r0
                int[] r0 = f787xaf4caad     // Catch:{ NoSuchFieldError -> 0x0014 }
                org.apache.commons.collections4.functors.ComparatorPredicate$Criterion r1 = org.apache.commons.collections4.functors.ComparatorPredicate.Criterion.EQUAL     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f787xaf4caad     // Catch:{ NoSuchFieldError -> 0x001f }
                org.apache.commons.collections4.functors.ComparatorPredicate$Criterion r1 = org.apache.commons.collections4.functors.ComparatorPredicate.Criterion.GREATER     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f787xaf4caad     // Catch:{ NoSuchFieldError -> 0x002a }
                org.apache.commons.collections4.functors.ComparatorPredicate$Criterion r1 = org.apache.commons.collections4.functors.ComparatorPredicate.Criterion.LESS     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = f787xaf4caad     // Catch:{ NoSuchFieldError -> 0x0035 }
                org.apache.commons.collections4.functors.ComparatorPredicate$Criterion r1 = org.apache.commons.collections4.functors.ComparatorPredicate.Criterion.GREATER_OR_EQUAL     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                int[] r0 = f787xaf4caad     // Catch:{ NoSuchFieldError -> 0x0040 }
                org.apache.commons.collections4.functors.ComparatorPredicate$Criterion r1 = org.apache.commons.collections4.functors.ComparatorPredicate.Criterion.LESS_OR_EQUAL     // Catch:{ NoSuchFieldError -> 0x0040 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0040 }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0040 }
            L_0x0040:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.functors.ComparatorPredicate.C33571.<clinit>():void");
        }
    }

    public enum Criterion {
        EQUAL,
        GREATER,
        LESS,
        GREATER_OR_EQUAL,
        LESS_OR_EQUAL
    }

    public static <T> Predicate<T> comparatorPredicate(T t, Comparator<T> comparator2) {
        return comparatorPredicate(t, comparator2, Criterion.EQUAL);
    }

    public static <T> Predicate<T> comparatorPredicate(T t, Comparator<T> comparator2, Criterion criterion2) {
        if (comparator2 == null) {
            throw new NullPointerException("Comparator must not be null.");
        } else if (criterion2 != null) {
            return new ComparatorPredicate(t, comparator2, criterion2);
        } else {
            throw new NullPointerException("Criterion must not be null.");
        }
    }

    public ComparatorPredicate(T t, Comparator<T> comparator2, Criterion criterion2) {
        this.object = t;
        this.comparator = comparator2;
        this.criterion = criterion2;
    }

    public boolean evaluate(T t) {
        int compare = this.comparator.compare(this.object, t);
        int i = C33571.f787xaf4caad[this.criterion.ordinal()];
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        if (i != 5) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("The current criterion '");
                            sb.append(this.criterion);
                            sb.append("' is invalid.");
                            throw new IllegalStateException(sb.toString());
                        } else if (compare > 0) {
                            return false;
                        }
                    } else if (compare < 0) {
                        return false;
                    }
                } else if (compare >= 0) {
                    return false;
                }
            } else if (compare <= 0) {
                return false;
            }
        } else if (compare != 0) {
            return false;
        }
        return true;
    }
}
