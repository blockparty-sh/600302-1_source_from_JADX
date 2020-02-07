package org.bitcoinj.crypto;

import com.google.common.primitives.Ints;
import java.util.Locale;

public class ChildNumber implements Comparable<ChildNumber> {
    public static final int HARDENED_BIT = Integer.MIN_VALUE;
    public static final ChildNumber ONE = new ChildNumber(1);
    public static final ChildNumber ZERO = new ChildNumber(0);
    public static final ChildNumber ZERO_HARDENED = new ChildNumber(0, true);

    /* renamed from: i */
    private final int f803i;

    private static boolean hasHardenedBit(int i) {
        return (i & Integer.MIN_VALUE) != 0;
    }

    public ChildNumber(int i, boolean z) {
        if (!hasHardenedBit(i)) {
            if (z) {
                i |= Integer.MIN_VALUE;
            }
            this.f803i = i;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Most significant bit is reserved and shouldn't be set: ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    public ChildNumber(int i) {
        this.f803i = i;
    }

    public int getI() {
        return this.f803i;
    }

    /* renamed from: i */
    public int mo46017i() {
        return this.f803i;
    }

    public boolean isHardened() {
        return hasHardenedBit(this.f803i);
    }

    public int num() {
        return this.f803i & Integer.MAX_VALUE;
    }

    public String toString() {
        Locale locale = Locale.US;
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(num());
        objArr[1] = isHardened() ? "H" : "";
        return String.format(locale, "%d%s", objArr);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        if (this.f803i != ((ChildNumber) obj).f803i) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return this.f803i;
    }

    public int compareTo(ChildNumber childNumber) {
        return Ints.compare(num(), childNumber.num());
    }
}
