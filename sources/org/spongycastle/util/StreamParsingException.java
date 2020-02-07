package org.spongycastle.util;

public class StreamParsingException extends Exception {

    /* renamed from: _e */
    Throwable f1563_e;

    public StreamParsingException(String str, Throwable th) {
        super(str);
        this.f1563_e = th;
    }

    public Throwable getCause() {
        return this.f1563_e;
    }
}
