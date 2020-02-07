package org.spongycastle.crypto.modes;

import android.support.p000v4.media.session.PlaybackStateCompat;
import com.google.common.base.Ascii;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.StreamBlockCipher;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.params.ParametersWithIV;
import org.spongycastle.crypto.params.ParametersWithRandom;
import org.spongycastle.crypto.params.ParametersWithSBox;

public class GCFBBlockCipher extends StreamBlockCipher {

    /* renamed from: C */
    private static final byte[] f1240C = {105, 0, 114, 34, 100, -55, 4, 35, -115, 58, -37, -106, 70, -23, 42, -60, Ascii.CAN, -2, -84, -108, 0, -19, 7, Ascii.DC2, -64, -122, -36, -62, -17, 76, -87, 43};
    private final CFBBlockCipher cfbEngine;
    private long counter = 0;
    private boolean forEncryption;
    private KeyParameter key;

    public GCFBBlockCipher(BlockCipher blockCipher) {
        super(blockCipher);
        this.cfbEngine = new CFBBlockCipher(blockCipher, blockCipher.getBlockSize() * 8);
    }

    public void init(boolean z, CipherParameters cipherParameters) throws IllegalArgumentException {
        this.counter = 0;
        this.cfbEngine.init(z, cipherParameters);
        this.forEncryption = z;
        if (cipherParameters instanceof ParametersWithIV) {
            cipherParameters = ((ParametersWithIV) cipherParameters).getParameters();
        }
        if (cipherParameters instanceof ParametersWithRandom) {
            cipherParameters = ((ParametersWithRandom) cipherParameters).getParameters();
        }
        if (cipherParameters instanceof ParametersWithSBox) {
            cipherParameters = ((ParametersWithSBox) cipherParameters).getParameters();
        }
        this.key = (KeyParameter) cipherParameters;
    }

    public String getAlgorithmName() {
        String algorithmName = this.cfbEngine.getAlgorithmName();
        StringBuilder sb = new StringBuilder();
        sb.append(algorithmName.substring(0, algorithmName.indexOf(47) - 1));
        sb.append("/G");
        sb.append(algorithmName.substring(algorithmName.indexOf(47) + 1));
        return sb.toString();
    }

    public int getBlockSize() {
        return this.cfbEngine.getBlockSize();
    }

    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) throws DataLengthException, IllegalStateException {
        processBytes(bArr, i, this.cfbEngine.getBlockSize(), bArr2, i2);
        return this.cfbEngine.getBlockSize();
    }

    /* access modifiers changed from: protected */
    public byte calculateByte(byte b) {
        long j = this.counter;
        if (j > 0 && j % PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID == 0) {
            BlockCipher underlyingCipher = this.cfbEngine.getUnderlyingCipher();
            underlyingCipher.init(false, this.key);
            byte[] bArr = new byte[32];
            underlyingCipher.processBlock(f1240C, 0, bArr, 0);
            underlyingCipher.processBlock(f1240C, 8, bArr, 8);
            underlyingCipher.processBlock(f1240C, 16, bArr, 16);
            underlyingCipher.processBlock(f1240C, 24, bArr, 24);
            this.key = new KeyParameter(bArr);
            underlyingCipher.init(true, this.key);
            byte[] currentIV = this.cfbEngine.getCurrentIV();
            underlyingCipher.processBlock(currentIV, 0, currentIV, 0);
            this.cfbEngine.init(this.forEncryption, new ParametersWithIV(this.key, currentIV));
        }
        this.counter++;
        return this.cfbEngine.calculateByte(b);
    }

    public void reset() {
        this.counter = 0;
        this.cfbEngine.reset();
    }
}
