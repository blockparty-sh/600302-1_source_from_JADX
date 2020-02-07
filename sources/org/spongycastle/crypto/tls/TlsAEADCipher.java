package org.spongycastle.crypto.tls;

import java.io.IOException;
import org.spongycastle.crypto.modes.AEADBlockCipher;
import org.spongycastle.crypto.params.AEADParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.util.Arrays;

public class TlsAEADCipher implements TlsCipher {
    protected TlsContext context;
    protected AEADBlockCipher decryptCipher;
    protected byte[] decryptImplicitNonce;
    protected AEADBlockCipher encryptCipher;
    protected byte[] encryptImplicitNonce;
    protected int macSize;
    protected int nonce_explicit_length;

    public TlsAEADCipher(TlsContext tlsContext, AEADBlockCipher aEADBlockCipher, AEADBlockCipher aEADBlockCipher2, int i, int i2) throws IOException {
        if (TlsUtils.isTLSv12(tlsContext)) {
            this.context = tlsContext;
            this.macSize = i2;
            this.nonce_explicit_length = 8;
            int i3 = (i * 2) + 8;
            byte[] calculateKeyBlock = TlsUtils.calculateKeyBlock(tlsContext, i3);
            KeyParameter keyParameter = new KeyParameter(calculateKeyBlock, 0, i);
            int i4 = i + 0;
            KeyParameter keyParameter2 = new KeyParameter(calculateKeyBlock, i4, i);
            int i5 = i4 + i;
            int i6 = i5 + 4;
            byte[] copyOfRange = Arrays.copyOfRange(calculateKeyBlock, i5, i6);
            int i7 = i6 + 4;
            byte[] copyOfRange2 = Arrays.copyOfRange(calculateKeyBlock, i6, i7);
            if (i7 == i3) {
                if (tlsContext.isServer()) {
                    this.encryptCipher = aEADBlockCipher2;
                    this.decryptCipher = aEADBlockCipher;
                    this.encryptImplicitNonce = copyOfRange2;
                    this.decryptImplicitNonce = copyOfRange;
                    KeyParameter keyParameter3 = keyParameter2;
                    keyParameter2 = keyParameter;
                    keyParameter = keyParameter3;
                } else {
                    this.encryptCipher = aEADBlockCipher;
                    this.decryptCipher = aEADBlockCipher2;
                    this.encryptImplicitNonce = copyOfRange;
                    this.decryptImplicitNonce = copyOfRange2;
                }
                byte[] bArr = new byte[(this.nonce_explicit_length + 4)];
                int i8 = i2 * 8;
                this.encryptCipher.init(true, new AEADParameters(keyParameter, i8, bArr));
                this.decryptCipher.init(false, new AEADParameters(keyParameter2, i8, bArr));
                return;
            }
            throw new TlsFatalAlert(80);
        }
        throw new TlsFatalAlert(80);
    }

    public int getPlaintextLimit(int i) {
        return (i - this.macSize) - this.nonce_explicit_length;
    }

    public byte[] encodePlaintext(long j, short s, byte[] bArr, int i, int i2) throws IOException {
        long j2 = j;
        int i3 = i2;
        byte[] bArr2 = this.encryptImplicitNonce;
        byte[] bArr3 = new byte[(bArr2.length + this.nonce_explicit_length)];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        TlsUtils.writeUint64(j, bArr3, this.encryptImplicitNonce.length);
        int outputSize = this.encryptCipher.getOutputSize(i3);
        int i4 = this.nonce_explicit_length;
        byte[] bArr4 = new byte[(outputSize + i4)];
        System.arraycopy(bArr3, this.encryptImplicitNonce.length, bArr4, 0, i4);
        int i5 = this.nonce_explicit_length;
        short s2 = s;
        try {
            this.encryptCipher.init(true, new AEADParameters(null, this.macSize * 8, bArr3, getAdditionalData(j, s, i3)));
            int processBytes = i5 + this.encryptCipher.processBytes(bArr, i, i2, bArr4, i5);
            if (processBytes + this.encryptCipher.doFinal(bArr4, processBytes) == bArr4.length) {
                return bArr4;
            }
            throw new TlsFatalAlert(80);
        } catch (Exception unused) {
            throw new TlsFatalAlert(80);
        }
    }

    public byte[] decodeCiphertext(long j, short s, byte[] bArr, int i, int i2) throws IOException {
        int i3 = i;
        int i4 = i2;
        if (getPlaintextLimit(i4) >= 0) {
            byte[] bArr2 = this.decryptImplicitNonce;
            byte[] bArr3 = new byte[(bArr2.length + this.nonce_explicit_length)];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            System.arraycopy(bArr, i3, bArr3, this.decryptImplicitNonce.length, this.nonce_explicit_length);
            int i5 = this.nonce_explicit_length;
            int i6 = i3 + i5;
            int i7 = i4 - i5;
            int outputSize = this.decryptCipher.getOutputSize(i7);
            byte[] bArr4 = new byte[outputSize];
            long j2 = j;
            try {
                this.decryptCipher.init(false, new AEADParameters(null, this.macSize * 8, bArr3, getAdditionalData(j, s, outputSize)));
                int processBytes = this.decryptCipher.processBytes(bArr, i6, i7, bArr4, 0) + 0;
                if (processBytes + this.decryptCipher.doFinal(bArr4, processBytes) == bArr4.length) {
                    return bArr4;
                }
                throw new TlsFatalAlert(80);
            } catch (Exception unused) {
                throw new TlsFatalAlert(20);
            }
        } else {
            throw new TlsFatalAlert(50);
        }
    }

    /* access modifiers changed from: protected */
    public byte[] getAdditionalData(long j, short s, int i) throws IOException {
        byte[] bArr = new byte[13];
        TlsUtils.writeUint64(j, bArr, 0);
        TlsUtils.writeUint8(s, bArr, 8);
        TlsUtils.writeVersion(this.context.getServerVersion(), bArr, 9);
        TlsUtils.writeUint16(i, bArr, 11);
        return bArr;
    }
}
