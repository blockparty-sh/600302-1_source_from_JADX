package org.bitcoinj.utils;

import com.google.common.base.Preconditions;
import com.google.common.primitives.Longs;
import org.bitcoinj.core.C3447Utils;

public class ExponentialBackoff implements Comparable<ExponentialBackoff> {
    public static final int DEFAULT_INITIAL_MILLIS = 100;
    public static final int DEFAULT_MAXIMUM_MILLIS = 30000;
    public static final float DEFAULT_MULTIPLIER = 1.1f;
    private float backoff;
    private final Params params;
    private long retryTime;

    public static class Params {
        /* access modifiers changed from: private */
        public final float initial;
        /* access modifiers changed from: private */
        public final float maximum;
        /* access modifiers changed from: private */
        public final float multiplier;

        public Params(long j, float f, long j2) {
            boolean z = true;
            Preconditions.checkArgument(f > 1.0f, "multiplier must be greater than 1.0");
            if (j2 < j) {
                z = false;
            }
            Preconditions.checkArgument(z, "maximum must not be less than initial");
            this.initial = (float) j;
            this.multiplier = f;
            this.maximum = (float) j2;
        }

        public Params() {
            this.initial = 100.0f;
            this.multiplier = 1.1f;
            this.maximum = 30000.0f;
        }
    }

    public ExponentialBackoff(Params params2) {
        this.params = params2;
        trackSuccess();
    }

    public final void trackSuccess() {
        this.backoff = this.params.initial;
        this.retryTime = C3447Utils.currentTimeMillis();
    }

    public void trackFailure() {
        long currentTimeMillis = C3447Utils.currentTimeMillis();
        float f = this.backoff;
        this.retryTime = currentTimeMillis + ((long) f);
        this.backoff = Math.min(f * this.params.multiplier, this.params.maximum);
    }

    public long getRetryTime() {
        return this.retryTime;
    }

    public int compareTo(ExponentialBackoff exponentialBackoff) {
        return Longs.compare(this.retryTime, exponentialBackoff.retryTime);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ExponentialBackoff retry=");
        sb.append(this.retryTime);
        sb.append(" backoff=");
        sb.append(this.backoff);
        return sb.toString();
    }
}
