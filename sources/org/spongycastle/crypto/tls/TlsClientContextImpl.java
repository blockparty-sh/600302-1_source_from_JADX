package org.spongycastle.crypto.tls;

import java.security.SecureRandom;

class TlsClientContextImpl extends AbstractTlsContext implements TlsClientContext {
    public boolean isServer() {
        return false;
    }

    TlsClientContextImpl(SecureRandom secureRandom, SecurityParameters securityParameters) {
        super(secureRandom, securityParameters);
    }
}
