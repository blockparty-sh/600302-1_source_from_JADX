package com.leanplum.callbacks;

public abstract class RegisterDeviceFinishedCallback implements Runnable {
    private boolean success;

    public abstract void onResponse(boolean z);

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public void run() {
        onResponse(this.success);
    }
}
