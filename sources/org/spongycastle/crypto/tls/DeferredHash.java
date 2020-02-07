package org.spongycastle.crypto.tls;

import java.util.Enumeration;
import java.util.Hashtable;
import org.spongycastle.crypto.Digest;
import org.spongycastle.util.Shorts;

class DeferredHash implements TlsHandshakeHash {
    protected static final int BUFFERING_HASH_LIMIT = 4;
    private DigestInputBuffer buf;
    protected TlsContext context;
    private Hashtable hashes;
    private Short prfHashAlgorithm;

    DeferredHash() {
        this.buf = new DigestInputBuffer();
        this.hashes = new Hashtable();
        this.prfHashAlgorithm = null;
    }

    private DeferredHash(Short sh, Digest digest) {
        this.buf = null;
        this.hashes = new Hashtable();
        this.prfHashAlgorithm = sh;
        this.hashes.put(sh, digest);
    }

    public void init(TlsContext tlsContext) {
        this.context = tlsContext;
    }

    public TlsHandshakeHash notifyPRFDetermined() {
        int prfAlgorithm = this.context.getSecurityParameters().getPrfAlgorithm();
        if (prfAlgorithm == 0) {
            CombinedHash combinedHash = new CombinedHash();
            combinedHash.init(this.context);
            this.buf.updateDigest(combinedHash);
            return combinedHash.notifyPRFDetermined();
        }
        this.prfHashAlgorithm = Shorts.valueOf(TlsUtils.getHashAlgorithmForPRFAlgorithm(prfAlgorithm));
        checkTrackingHash(this.prfHashAlgorithm);
        return this;
    }

    public void trackHashAlgorithm(short s) {
        if (this.buf != null) {
            checkTrackingHash(Shorts.valueOf(s));
            return;
        }
        throw new IllegalStateException("Too late to track more hash algorithms");
    }

    public void sealHashAlgorithms() {
        checkStopBuffering();
    }

    public TlsHandshakeHash stopTracking() {
        Digest cloneHash = TlsUtils.cloneHash(this.prfHashAlgorithm.shortValue(), (Digest) this.hashes.get(this.prfHashAlgorithm));
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.updateDigest(cloneHash);
        }
        DeferredHash deferredHash = new DeferredHash(this.prfHashAlgorithm, cloneHash);
        deferredHash.init(this.context);
        return deferredHash;
    }

    public Digest forkPRFHash() {
        checkStopBuffering();
        if (this.buf == null) {
            return TlsUtils.cloneHash(this.prfHashAlgorithm.shortValue(), (Digest) this.hashes.get(this.prfHashAlgorithm));
        }
        Digest createHash = TlsUtils.createHash(this.prfHashAlgorithm.shortValue());
        this.buf.updateDigest(createHash);
        return createHash;
    }

    public byte[] getFinalHash(short s) {
        Digest digest = (Digest) this.hashes.get(Shorts.valueOf(s));
        if (digest != null) {
            Digest cloneHash = TlsUtils.cloneHash(s, digest);
            DigestInputBuffer digestInputBuffer = this.buf;
            if (digestInputBuffer != null) {
                digestInputBuffer.updateDigest(cloneHash);
            }
            byte[] bArr = new byte[cloneHash.getDigestSize()];
            cloneHash.doFinal(bArr, 0);
            return bArr;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("HashAlgorithm ");
        sb.append(s);
        sb.append(" is not being tracked");
        throw new IllegalStateException(sb.toString());
    }

    public String getAlgorithmName() {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    public int getDigestSize() {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    public void update(byte b) {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.write(b);
            return;
        }
        Enumeration elements = this.hashes.elements();
        while (elements.hasMoreElements()) {
            ((Digest) elements.nextElement()).update(b);
        }
    }

    public void update(byte[] bArr, int i, int i2) {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.write(bArr, i, i2);
            return;
        }
        Enumeration elements = this.hashes.elements();
        while (elements.hasMoreElements()) {
            ((Digest) elements.nextElement()).update(bArr, i, i2);
        }
    }

    public int doFinal(byte[] bArr, int i) {
        throw new IllegalStateException("Use fork() to get a definite Digest");
    }

    public void reset() {
        DigestInputBuffer digestInputBuffer = this.buf;
        if (digestInputBuffer != null) {
            digestInputBuffer.reset();
            return;
        }
        Enumeration elements = this.hashes.elements();
        while (elements.hasMoreElements()) {
            ((Digest) elements.nextElement()).reset();
        }
    }

    /* access modifiers changed from: protected */
    public void checkStopBuffering() {
        if (this.buf != null && this.hashes.size() <= 4) {
            Enumeration elements = this.hashes.elements();
            while (elements.hasMoreElements()) {
                this.buf.updateDigest((Digest) elements.nextElement());
            }
            this.buf = null;
        }
    }

    /* access modifiers changed from: protected */
    public void checkTrackingHash(Short sh) {
        if (!this.hashes.containsKey(sh)) {
            this.hashes.put(sh, TlsUtils.createHash(sh.shortValue()));
        }
    }
}
