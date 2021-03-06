package p015io.opencensus.common;

import java.math.BigInteger;

/* renamed from: io.opencensus.common.TimeUtils */
final class TimeUtils {
    private static final BigInteger MAX_LONG_VALUE = BigInteger.valueOf(Long.MAX_VALUE);
    static final int MAX_NANOS = 999999999;
    static final long MAX_SECONDS = 315576000000L;
    static final long MILLIS_PER_SECOND = 1000;
    private static final BigInteger MIN_LONG_VALUE = BigInteger.valueOf(Long.MIN_VALUE);
    static final long NANOS_PER_MILLI = 1000000;
    static final long NANOS_PER_SECOND = 1000000000;

    static int compareLongs(long j, long j2) {
        int i = (j > j2 ? 1 : (j == j2 ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i == 0 ? 0 : 1;
    }

    private TimeUtils() {
    }

    static long checkedAdd(long j, long j2) {
        BigInteger add = BigInteger.valueOf(j).add(BigInteger.valueOf(j2));
        if (add.compareTo(MAX_LONG_VALUE) <= 0 && add.compareTo(MIN_LONG_VALUE) >= 0) {
            return j + j2;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Long sum overflow: x=");
        sb.append(j);
        sb.append(", y=");
        sb.append(j2);
        throw new ArithmeticException(sb.toString());
    }
}
