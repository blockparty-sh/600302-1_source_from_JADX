package org.apache.commons.collections4.comparators;

import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FixedOrderComparator<T> implements Comparator<T>, Serializable {
    private static final long serialVersionUID = 82794675842863201L;
    private int counter;
    private boolean isLocked;
    private final Map<T, Integer> map;
    private UnknownObjectBehavior unknownObjectBehavior;

    /* renamed from: org.apache.commons.collections4.comparators.FixedOrderComparator$1 */
    static /* synthetic */ class C33561 {

        /* renamed from: $SwitchMap$org$apache$commons$collections4$comparators$FixedOrderComparator$UnknownObjectBehavior */
        static final /* synthetic */ int[] f786xfcab2de1 = new int[UnknownObjectBehavior.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                org.apache.commons.collections4.comparators.FixedOrderComparator$UnknownObjectBehavior[] r0 = org.apache.commons.collections4.comparators.FixedOrderComparator.UnknownObjectBehavior.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                f786xfcab2de1 = r0
                int[] r0 = f786xfcab2de1     // Catch:{ NoSuchFieldError -> 0x0014 }
                org.apache.commons.collections4.comparators.FixedOrderComparator$UnknownObjectBehavior r1 = org.apache.commons.collections4.comparators.FixedOrderComparator.UnknownObjectBehavior.BEFORE     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = f786xfcab2de1     // Catch:{ NoSuchFieldError -> 0x001f }
                org.apache.commons.collections4.comparators.FixedOrderComparator$UnknownObjectBehavior r1 = org.apache.commons.collections4.comparators.FixedOrderComparator.UnknownObjectBehavior.AFTER     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = f786xfcab2de1     // Catch:{ NoSuchFieldError -> 0x002a }
                org.apache.commons.collections4.comparators.FixedOrderComparator$UnknownObjectBehavior r1 = org.apache.commons.collections4.comparators.FixedOrderComparator.UnknownObjectBehavior.EXCEPTION     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.comparators.FixedOrderComparator.C33561.<clinit>():void");
        }
    }

    public enum UnknownObjectBehavior {
        BEFORE,
        AFTER,
        EXCEPTION
    }

    public FixedOrderComparator() {
        this.map = new HashMap();
        this.counter = 0;
        this.isLocked = false;
        this.unknownObjectBehavior = UnknownObjectBehavior.EXCEPTION;
    }

    public FixedOrderComparator(T... tArr) {
        this.map = new HashMap();
        this.counter = 0;
        this.isLocked = false;
        this.unknownObjectBehavior = UnknownObjectBehavior.EXCEPTION;
        if (tArr != null) {
            for (T add : tArr) {
                add(add);
            }
            return;
        }
        throw new NullPointerException("The list of items must not be null");
    }

    public FixedOrderComparator(List<T> list) {
        this.map = new HashMap();
        this.counter = 0;
        this.isLocked = false;
        this.unknownObjectBehavior = UnknownObjectBehavior.EXCEPTION;
        if (list != null) {
            for (T add : list) {
                add(add);
            }
            return;
        }
        throw new NullPointerException("The list of items must not be null");
    }

    public boolean isLocked() {
        return this.isLocked;
    }

    /* access modifiers changed from: protected */
    public void checkLocked() {
        if (isLocked()) {
            throw new UnsupportedOperationException("Cannot modify a FixedOrderComparator after a comparison");
        }
    }

    public UnknownObjectBehavior getUnknownObjectBehavior() {
        return this.unknownObjectBehavior;
    }

    public void setUnknownObjectBehavior(UnknownObjectBehavior unknownObjectBehavior2) {
        checkLocked();
        if (unknownObjectBehavior2 != null) {
            this.unknownObjectBehavior = unknownObjectBehavior2;
            return;
        }
        throw new NullPointerException("Unknown object behavior must not be null");
    }

    public boolean add(T t) {
        checkLocked();
        Map<T, Integer> map2 = this.map;
        int i = this.counter;
        this.counter = i + 1;
        return ((Integer) map2.put(t, Integer.valueOf(i))) == null;
    }

    public boolean addAsEqual(T t, T t2) {
        checkLocked();
        Integer num = (Integer) this.map.get(t);
        if (num != null) {
            return ((Integer) this.map.put(t2, num)) == null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(t);
        sb.append(" not known to ");
        sb.append(this);
        throw new IllegalArgumentException(sb.toString());
    }

    public int compare(T t, T t2) {
        int i = 1;
        this.isLocked = true;
        Integer num = (Integer) this.map.get(t);
        Integer num2 = (Integer) this.map.get(t2);
        if (num != null && num2 != null) {
            return num.compareTo(num2);
        }
        int i2 = C33561.f786xfcab2de1[this.unknownObjectBehavior.ordinal()];
        if (i2 == 1) {
            if (num == null) {
                i = num2 == null ? 0 : -1;
            }
            return i;
        } else if (i2 == 2) {
            if (num != null) {
                i = -1;
            } else if (num2 == null) {
                i = 0;
            }
            return i;
        } else if (i2 == 3) {
            if (num != null) {
                t = t2;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Attempting to compare unknown object ");
            sb.append(t);
            throw new IllegalArgumentException(sb.toString());
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Unknown unknownObjectBehavior: ");
            sb2.append(this.unknownObjectBehavior);
            throw new UnsupportedOperationException(sb2.toString());
        }
    }

    public int hashCode() {
        Map<T, Integer> map2 = this.map;
        int i = 0;
        int hashCode = (629 + (map2 == null ? 0 : map2.hashCode())) * 37;
        UnknownObjectBehavior unknownObjectBehavior2 = this.unknownObjectBehavior;
        if (unknownObjectBehavior2 != null) {
            i = unknownObjectBehavior2.hashCode();
        }
        return ((((hashCode + i) * 37) + this.counter) * 37) + (this.isLocked ^ true ? 1 : 0);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        if (r7.unknownObjectBehavior == null) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0042, code lost:
        if (r2 == r3) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r7) {
        /*
            r6 = this;
            r0 = 1
            if (r6 != r7) goto L_0x0004
            return r0
        L_0x0004:
            r1 = 0
            if (r7 != 0) goto L_0x0008
            return r1
        L_0x0008:
            java.lang.Class r2 = r7.getClass()
            java.lang.Class r3 = r6.getClass()
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0047
            org.apache.commons.collections4.comparators.FixedOrderComparator r7 = (org.apache.commons.collections4.comparators.FixedOrderComparator) r7
            java.util.Map<T, java.lang.Integer> r2 = r6.map
            if (r2 != 0) goto L_0x0021
            java.util.Map<T, java.lang.Integer> r2 = r7.map
            if (r2 != 0) goto L_0x0045
            goto L_0x0029
        L_0x0021:
            java.util.Map<T, java.lang.Integer> r3 = r7.map
            boolean r2 = r2.equals(r3)
            if (r2 == 0) goto L_0x0045
        L_0x0029:
            org.apache.commons.collections4.comparators.FixedOrderComparator$UnknownObjectBehavior r2 = r6.unknownObjectBehavior
            if (r2 != 0) goto L_0x0032
            org.apache.commons.collections4.comparators.FixedOrderComparator$UnknownObjectBehavior r7 = r7.unknownObjectBehavior
            if (r7 != 0) goto L_0x0045
            goto L_0x0046
        L_0x0032:
            org.apache.commons.collections4.comparators.FixedOrderComparator$UnknownObjectBehavior r3 = r7.unknownObjectBehavior
            if (r2 != r3) goto L_0x0045
            int r4 = r6.counter
            int r5 = r7.counter
            if (r4 != r5) goto L_0x0045
            boolean r4 = r6.isLocked
            boolean r7 = r7.isLocked
            if (r4 != r7) goto L_0x0045
            if (r2 != r3) goto L_0x0045
            goto L_0x0046
        L_0x0045:
            r0 = 0
        L_0x0046:
            return r0
        L_0x0047:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.comparators.FixedOrderComparator.equals(java.lang.Object):boolean");
    }
}
