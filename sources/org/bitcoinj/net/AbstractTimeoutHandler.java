package org.bitcoinj.net;

import java.util.Timer;
import java.util.TimerTask;

public abstract class AbstractTimeoutHandler {
    private static final Timer timeoutTimer = new Timer("AbstractTimeoutHandler timeouts", true);
    private boolean timeoutEnabled = true;
    private long timeoutMillis = 0;
    private TimerTask timeoutTask;

    /* access modifiers changed from: protected */
    public abstract void timeoutOccurred();

    public final synchronized void setTimeoutEnabled(boolean z) {
        this.timeoutEnabled = z;
        resetTimeout();
    }

    public final synchronized void setSocketTimeout(int i) {
        this.timeoutMillis = (long) i;
        resetTimeout();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void resetTimeout() {
        /*
            r5 = this;
            monitor-enter(r5)
            java.util.TimerTask r0 = r5.timeoutTask     // Catch:{ all -> 0x002b }
            if (r0 == 0) goto L_0x000a
            java.util.TimerTask r0 = r5.timeoutTask     // Catch:{ all -> 0x002b }
            r0.cancel()     // Catch:{ all -> 0x002b }
        L_0x000a:
            long r0 = r5.timeoutMillis     // Catch:{ all -> 0x002b }
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0029
            boolean r0 = r5.timeoutEnabled     // Catch:{ all -> 0x002b }
            if (r0 != 0) goto L_0x0017
            goto L_0x0029
        L_0x0017:
            org.bitcoinj.net.AbstractTimeoutHandler$1 r0 = new org.bitcoinj.net.AbstractTimeoutHandler$1     // Catch:{ all -> 0x002b }
            r0.<init>()     // Catch:{ all -> 0x002b }
            r5.timeoutTask = r0     // Catch:{ all -> 0x002b }
            java.util.Timer r0 = timeoutTimer     // Catch:{ all -> 0x002b }
            java.util.TimerTask r1 = r5.timeoutTask     // Catch:{ all -> 0x002b }
            long r2 = r5.timeoutMillis     // Catch:{ all -> 0x002b }
            r0.schedule(r1, r2)     // Catch:{ all -> 0x002b }
            monitor-exit(r5)
            return
        L_0x0029:
            monitor-exit(r5)
            return
        L_0x002b:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.net.AbstractTimeoutHandler.resetTimeout():void");
    }
}
