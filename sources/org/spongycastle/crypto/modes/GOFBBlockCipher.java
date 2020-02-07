package org.spongycastle.crypto.modes;

import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import com.google.common.base.Ascii;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.StreamBlockCipher;
import org.spongycastle.crypto.params.ParametersWithIV;

public class GOFBBlockCipher extends StreamBlockCipher {

    /* renamed from: C1 */
    static final int f1244C1 = 16843012;

    /* renamed from: C2 */
    static final int f1245C2 = 16843009;

    /* renamed from: IV */
    private byte[] f1246IV;

    /* renamed from: N3 */
    int f1247N3;

    /* renamed from: N4 */
    int f1248N4;
    private final int blockSize;
    private int byteCount;
    private final BlockCipher cipher;
    boolean firstStep = true;
    private byte[] ofbOutV;
    private byte[] ofbV;

    public GOFBBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.cipher = blockCipher;
        this.blockSize = blockCipher.getBlockSize();
        if (this.blockSize == 8) {
            this.f1246IV = new byte[blockCipher.getBlockSize()];
            this.ofbV = new byte[blockCipher.getBlockSize()];
            this.ofbOutV = new byte[blockCipher.getBlockSize()];
            return;
        }
        throw new IllegalArgumentException("GCTR only for 64 bit block ciphers");
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.firstStep = true;
        this.f1247N3 = 0;
        this.f1248N4 = 0;
        if (cipherParameters instanceof ParametersWithIV) {
            ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
            byte[] iv = parametersWithIV.getIV();
            int length = iv.length;
            byte[] bArr = this.f1246IV;
            if (length < bArr.length) {
                System.arraycopy(iv, 0, bArr, bArr.length - iv.length, iv.length);
                int i = 0;
                while (true) {
                    byte[] bArr2 = this.f1246IV;
                    if (i >= bArr2.length - iv.length) {
                        break;
                    }
                    bArr2[i] = 0;
                    i++;
                }
            } else {
                System.arraycopy(iv, 0, bArr, 0, bArr.length);
            }
            reset();
            if (parametersWithIV.getParameters() != null) {
                this.cipher.init(true, parametersWithIV.getParameters());
                return;
            }
            return;
        }
        reset();
        if (cipherParameters != null) {
            this.cipher.init(true, cipherParameters);
        }
    }

    public String getAlgorithmName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.cipher.getAlgorithmName());
        sb.append("/GCTR");
        return sb.toString();
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        processBytes(bArr, i, this.blockSize, bArr2, i2);
        return this.blockSize;
    }

    public void reset() {
        this.firstStep = true;
        this.f1247N3 = 0;
        this.f1248N4 = 0;
        byte[] bArr = this.f1246IV;
        System.arraycopy(bArr, 0, this.ofbV, 0, bArr.length);
        this.byteCount = 0;
        this.cipher.reset();
    }

    private int bytesToint(byte[] bArr, int i) {
        return ((bArr[i + 3] << Ascii.CAN) & ViewCompat.MEASURED_STATE_MASK) + ((bArr[i + 2] << 16) & 16711680) + ((bArr[i + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) + (bArr[i] & 255);
    }

    private void intTobytes(int i, byte[] bArr, int i2) {
        bArr[i2 + 3] = (byte) (i >>> 24);
        bArr[i2 + 2] = (byte) (i >>> 16);
        bArr[i2 + 1] = (byte) (i >>> 8);
        bArr[i2] = (byte) i;
    }

    /* access modifiers changed from: protected */
    public byte calculateByte(byte b) {
        if (this.byteCount == 0) {
            if (this.firstStep) {
                this.firstStep = false;
                this.cipher.processBlock(this.ofbV, 0, this.ofbOutV, 0);
                this.f1247N3 = bytesToint(this.ofbOutV, 0);
                this.f1248N4 = bytesToint(this.ofbOutV, 4);
            }
            this.f1247N3 += f1245C2;
            this.f1248N4 += f1244C1;
            intTobytes(this.f1247N3, this.ofbV, 0);
            intTobytes(this.f1248N4, this.ofbV, 4);
            this.cipher.processBlock(this.ofbV, 0, this.ofbOutV, 0);
        }
        byte[] bArr = this.ofbOutV;
        int i = this.byteCount;
        this.byteCount = i + 1;
        byte b2 = (byte) (b ^ bArr[i]);
        int i2 = this.byteCount;
        int i3 = this.blockSize;
        if (i2 == i3) {
            this.byteCount = 0;
            byte[] bArr2 = this.ofbV;
            System.arraycopy(bArr2, i3, bArr2, 0, bArr2.length - i3);
            byte[] bArr3 = this.ofbOutV;
            byte[] bArr4 = this.ofbV;
            int length = bArr4.length;
            int i4 = this.blockSize;
            System.arraycopy(bArr3, 0, bArr4, length - i4, i4);
        }
        return b2;
    }
}
