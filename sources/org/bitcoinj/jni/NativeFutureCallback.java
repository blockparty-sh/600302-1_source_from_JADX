package org.bitcoinj.jni;

import com.google.common.util.concurrent.FutureCallback;

public class NativeFutureCallback implements FutureCallback {
    public long ptr;

    public native void onFailure(Throwable th);

    public native void onSuccess(Object obj);
}
