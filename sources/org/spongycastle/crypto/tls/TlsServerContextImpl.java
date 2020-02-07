package org.spongycastle.crypto.tls;

import java.security.SecureRandom;

class TlsServerContextImpl extends AbstractTlsContext implements TlsServerContext {
    public boolean isServer() {
        return true;
    }

    TlsServerContextImpl(SecureRandom secureRandom, SecurityParameters securityParameters) {
        super(secureRandom, securityParameters);
    }
}
