package org.spongycastle.crypto.tls;

import org.spongycastle.util.Arrays;

public class SecurityParameters {
    int cipherSuite = -1;
    byte[] clientRandom = null;
    short compressionAlgorithm = -1;
    boolean encryptThenMAC = false;
    int entity = -1;
    byte[] masterSecret = null;
    short maxFragmentLength = -1;
    int prfAlgorithm = -1;
    byte[] serverRandom = null;
    boolean truncatedHMac = false;
    int verifyDataLength = -1;

    /* access modifiers changed from: 0000 */
    public void copySessionParametersFrom(SecurityParameters securityParameters) {
        this.entity = securityParameters.entity;
        this.cipherSuite = securityParameters.cipherSuite;
        this.compressionAlgorithm = securityParameters.compressionAlgorithm;
        this.prfAlgorithm = securityParameters.prfAlgorithm;
        this.verifyDataLength = securityParameters.verifyDataLength;
        this.masterSecret = Arrays.clone(securityParameters.masterSecret);
    }

    /* access modifiers changed from: 0000 */
    public void clear() {
        byte[] bArr = this.masterSecret;
        if (bArr != null) {
            Arrays.fill(bArr, 0);
            this.masterSecret = null;
        }
    }

    public int getEntity() {
        return this.entity;
    }

    public int getCipherSuite() {
        return this.cipherSuite;
    }

    public short getCompressionAlgorithm() {
        return this.compressionAlgorithm;
    }

    public int getPrfAlgorithm() {
        return this.prfAlgorithm;
    }

    public int getVerifyDataLength() {
        return this.verifyDataLength;
    }

    public byte[] getMasterSecret() {
        return this.masterSecret;
    }

    public byte[] getClientRandom() {
        return this.clientRandom;
    }

    public byte[] getServerRandom() {
        return this.serverRandom;
    }
}
