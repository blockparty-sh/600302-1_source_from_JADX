package org.spongycastle.crypto.tls;

import java.io.IOException;

public abstract class AbstractTlsPeer implements TlsPeer {
    public void notifyAlertRaised(short s, short s2, String str, Exception exc) {
    }

    public void notifyAlertReceived(short s, short s2) {
    }

    public void notifyHandshakeComplete() throws IOException {
    }

    public boolean shouldUseGMTUnixTime() {
        return false;
    }

    public void notifySecureRenegotiation(boolean z) throws IOException {
        if (!z) {
            throw new TlsFatalAlert(40);
        }
    }
}
