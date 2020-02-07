package org.spongycastle.util;

public class StoreException extends RuntimeException {

    /* renamed from: _e */
    private Throwable f1562_e;

    public StoreException(String str, Throwable th) {
        super(str);
        this.f1562_e = th;
    }

    public Throwable getCause() {
        return this.f1562_e;
    }
}
