package com.htc.htcwalletsdk.Act;

import android.content.Context;

public class AsyncWaitDialogRunnable implements Runnable {
    private static final String TAG = "AsyncWaitDialogRunnable";
    private Context mContext;
    private int mErrorCode;

    public AsyncWaitDialogRunnable(Context context, int i) {
        this.mContext = context;
        this.mErrorCode = i;
    }

    public void run() {
        doShowGeneralFailure(this.mContext, this.mErrorCode);
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:6:0x0032 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void doShowGeneralFailure(final android.content.Context r4, final int r5) {
        /*
            r3 = this;
            java.lang.String r0 = "AsyncWaitDialogRunnable"
            java.lang.String r1 = "doShowGeneralFailure +++"
            com.htc.htcwalletsdk.Utils.ZKMALog.m275i(r0, r1)
            java.lang.Object r0 = new java.lang.Object
            r0.<init>()
            android.os.Handler r1 = new android.os.Handler
            android.os.Looper r2 = android.os.Looper.getMainLooper()
            r1.<init>(r2)
            com.htc.htcwalletsdk.Act.AsyncWaitDialogRunnable$1 r2 = new com.htc.htcwalletsdk.Act.AsyncWaitDialogRunnable$1
            r2.<init>(r4, r5, r0)
            r1.post(r2)
            java.lang.String r4 = "AsyncWaitDialogRunnable"
            java.lang.String r5 = "before lock"
            com.htc.htcwalletsdk.Utils.ZKMALog.m275i(r4, r5)
            monitor-enter(r0)
            java.lang.String r4 = "AsyncWaitDialogRunnable"
            java.lang.String r5 = "wait ...."
            com.htc.htcwalletsdk.Utils.ZKMALog.m275i(r4, r5)     // Catch:{ InterruptedException -> 0x0032 }
            r0.wait()     // Catch:{ InterruptedException -> 0x0032 }
            goto L_0x0032
        L_0x0030:
            r4 = move-exception
            goto L_0x0042
        L_0x0032:
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            java.lang.String r4 = "AsyncWaitDialogRunnable"
            java.lang.String r5 = "after lock"
            com.htc.htcwalletsdk.Utils.ZKMALog.m275i(r4, r5)
            java.lang.String r4 = "AsyncWaitDialogRunnable"
            java.lang.String r5 = "doShowGeneralFailure ---"
            com.htc.htcwalletsdk.Utils.ZKMALog.m275i(r4, r5)
            return
        L_0x0042:
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htc.htcwalletsdk.Act.AsyncWaitDialogRunnable.doShowGeneralFailure(android.content.Context, int):void");
    }
}
