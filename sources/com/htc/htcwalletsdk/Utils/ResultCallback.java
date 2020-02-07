package com.htc.htcwalletsdk.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.htc.htcwalletsdk.Export.RESULT;

public abstract class ResultCallback extends Handler {
    private static final int MSG_FAILED = 200;
    private static final int MSG_SUCCESS = 100;
    Bundle mOutput = new Bundle();

    /* access modifiers changed from: protected */
    public abstract void onFailed(int i);

    /* access modifiers changed from: protected */
    public abstract void onFailed(String str);

    /* access modifiers changed from: protected */
    public abstract void onSuccess();

    public ResultCallback(Looper looper) {
        super(looper);
    }

    public void makeSuccess() {
        Message obtain = Message.obtain(null, 100, 0, 0);
        obtain.setData(new Bundle());
        sendMessage(obtain);
    }

    public void makeFailure(int i) {
        Message obtain = Message.obtain(null, 200, i, 0);
        obtain.setData(new Bundle());
        sendMessage(obtain);
    }

    public void makeFailure(String str) {
        Message obtain = Message.obtain(null, 200, RESULT.E_SDK_CUSTOM_ERRORMSG, 0);
        Bundle bundle = new Bundle();
        bundle.putString("errorMessage", str);
        obtain.setData(bundle);
        sendMessage(obtain);
    }

    public Bundle getOutput() {
        return this.mOutput;
    }

    public final void handleMessage(Message message) {
        int i = message.what;
        if (i == 100) {
            onSuccess();
        } else if (i == 200) {
            if (message.arg1 == -70000) {
                onFailed(message.getData().getString("errorMessage"));
            } else {
                onFailed(message.arg1);
            }
        }
    }
}
