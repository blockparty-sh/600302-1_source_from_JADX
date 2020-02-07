package org.spongycastle.crypto.p024io;

import java.io.IOException;

/* renamed from: org.spongycastle.crypto.io.CipherIOException */
public class CipherIOException extends IOException {
    private static final long serialVersionUID = 1;
    private final Throwable cause;

    public CipherIOException(String str, Throwable th) {
        super(str);
        this.cause = th;
    }

    public Throwable getCause() {
        return this.cause;
    }
}
