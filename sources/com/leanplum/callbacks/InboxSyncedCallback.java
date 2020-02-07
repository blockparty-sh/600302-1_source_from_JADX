package com.leanplum.callbacks;

public abstract class InboxSyncedCallback implements Runnable {
    private boolean success;

    public abstract void onForceContentUpdate(boolean z);

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public void run() {
        onForceContentUpdate(this.success);
    }
}
