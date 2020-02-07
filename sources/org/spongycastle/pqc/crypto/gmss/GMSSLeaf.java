package org.spongycastle.pqc.crypto.gmss;

import org.spongycastle.crypto.Digest;
import org.spongycastle.pqc.crypto.gmss.util.GMSSRandom;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.encoders.Hex;

public class GMSSLeaf {
    private byte[] concHashs;
    private GMSSRandom gmssRandom;

    /* renamed from: i */
    private int f1439i;

    /* renamed from: j */
    private int f1440j;
    private int keysize;
    private byte[] leaf;
    private int mdsize;
    private Digest messDigestOTS;
    byte[] privateKeyOTS;
    private byte[] seed;
    private int steps;
    private int two_power_w;

    /* renamed from: w */
    private int f1441w;

    private int getLog(int i) {
        int i2 = 1;
        int i3 = 2;
        while (i3 < i) {
            i3 <<= 1;
            i2++;
        }
        return i2;
    }

    public GMSSLeaf(Digest digest, byte[][] bArr, int[] iArr) {
        this.f1439i = iArr[0];
        this.f1440j = iArr[1];
        this.steps = iArr[2];
        this.f1441w = iArr[3];
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        int ceil = (int) Math.ceil(((double) (this.mdsize << 3)) / ((double) this.f1441w));
        this.keysize = ceil + ((int) Math.ceil(((double) getLog((ceil << this.f1441w) + 1)) / ((double) this.f1441w)));
        this.two_power_w = 1 << this.f1441w;
        this.privateKeyOTS = bArr[0];
        this.seed = bArr[1];
        this.concHashs = bArr[2];
        this.leaf = bArr[3];
    }

    GMSSLeaf(Digest digest, int i, int i2) {
        this.f1441w = i;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        double d = (double) i;
        int ceil = (int) Math.ceil(((double) (this.mdsize << 3)) / d);
        this.keysize = ceil + ((int) Math.ceil(((double) getLog((ceil << i) + 1)) / d));
        int i3 = 1 << i;
        this.two_power_w = i3;
        int i4 = i3 - 1;
        int i5 = this.keysize;
        this.steps = (int) Math.ceil(((double) (((i4 * i5) + 1) + i5)) / ((double) i2));
        int i6 = this.mdsize;
        this.seed = new byte[i6];
        this.leaf = new byte[i6];
        this.privateKeyOTS = new byte[i6];
        this.concHashs = new byte[(i6 * this.keysize)];
    }

    public GMSSLeaf(Digest digest, int i, int i2, byte[] bArr) {
        this.f1441w = i;
        this.messDigestOTS = digest;
        this.gmssRandom = new GMSSRandom(this.messDigestOTS);
        this.mdsize = this.messDigestOTS.getDigestSize();
        double d = (double) i;
        int ceil = (int) Math.ceil(((double) (this.mdsize << 3)) / d);
        this.keysize = ceil + ((int) Math.ceil(((double) getLog((ceil << i) + 1)) / d));
        int i3 = 1 << i;
        this.two_power_w = i3;
        int i4 = i3 - 1;
        int i5 = this.keysize;
        this.steps = (int) Math.ceil(((double) (((i4 * i5) + 1) + i5)) / ((double) i2));
        int i6 = this.mdsize;
        this.seed = new byte[i6];
        this.leaf = new byte[i6];
        this.privateKeyOTS = new byte[i6];
        this.concHashs = new byte[(i6 * this.keysize)];
        initLeafCalc(bArr);
    }

    private GMSSLeaf(GMSSLeaf gMSSLeaf) {
        this.messDigestOTS = gMSSLeaf.messDigestOTS;
        this.mdsize = gMSSLeaf.mdsize;
        this.keysize = gMSSLeaf.keysize;
        this.gmssRandom = gMSSLeaf.gmssRandom;
        this.leaf = Arrays.clone(gMSSLeaf.leaf);
        this.concHashs = Arrays.clone(gMSSLeaf.concHashs);
        this.f1439i = gMSSLeaf.f1439i;
        this.f1440j = gMSSLeaf.f1440j;
        this.two_power_w = gMSSLeaf.two_power_w;
        this.f1441w = gMSSLeaf.f1441w;
        this.steps = gMSSLeaf.steps;
        this.seed = Arrays.clone(gMSSLeaf.seed);
        this.privateKeyOTS = Arrays.clone(gMSSLeaf.privateKeyOTS);
    }

    /* access modifiers changed from: 0000 */
    public void initLeafCalc(byte[] bArr) {
        this.f1439i = 0;
        this.f1440j = 0;
        byte[] bArr2 = new byte[this.mdsize];
        System.arraycopy(bArr, 0, bArr2, 0, this.seed.length);
        this.seed = this.gmssRandom.nextSeed(bArr2);
    }

    /* access modifiers changed from: 0000 */
    public GMSSLeaf nextLeaf() {
        GMSSLeaf gMSSLeaf = new GMSSLeaf(this);
        gMSSLeaf.updateLeafCalc();
        return gMSSLeaf;
    }

    private void updateLeafCalc() {
        byte[] bArr = new byte[this.messDigestOTS.getDigestSize()];
        for (int i = 0; i < this.steps + 10000; i++) {
            if (this.f1439i == this.keysize && this.f1440j == this.two_power_w - 1) {
                Digest digest = this.messDigestOTS;
                byte[] bArr2 = this.concHashs;
                digest.update(bArr2, 0, bArr2.length);
                this.leaf = new byte[this.messDigestOTS.getDigestSize()];
                this.messDigestOTS.doFinal(this.leaf, 0);
                return;
            }
            if (this.f1439i == 0 || this.f1440j == this.two_power_w - 1) {
                this.f1439i++;
                this.f1440j = 0;
                this.privateKeyOTS = this.gmssRandom.nextSeed(this.seed);
            } else {
                Digest digest2 = this.messDigestOTS;
                byte[] bArr3 = this.privateKeyOTS;
                digest2.update(bArr3, 0, bArr3.length);
                this.privateKeyOTS = bArr;
                this.messDigestOTS.doFinal(this.privateKeyOTS, 0);
                this.f1440j++;
                if (this.f1440j == this.two_power_w - 1) {
                    byte[] bArr4 = this.privateKeyOTS;
                    byte[] bArr5 = this.concHashs;
                    int i2 = this.mdsize;
                    System.arraycopy(bArr4, 0, bArr5, (this.f1439i - 1) * i2, i2);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("unable to updateLeaf in steps: ");
        sb.append(this.steps);
        String str = " ";
        sb.append(str);
        sb.append(this.f1439i);
        sb.append(str);
        sb.append(this.f1440j);
        throw new IllegalStateException(sb.toString());
    }

    public byte[] getLeaf() {
        return Arrays.clone(this.leaf);
    }

    public byte[][] getStatByte() {
        int i = this.mdsize;
        byte[][] bArr = {new byte[i], new byte[i], new byte[(this.keysize * i)], new byte[i]};
        bArr[0] = this.privateKeyOTS;
        bArr[1] = this.seed;
        bArr[2] = this.concHashs;
        bArr[3] = this.leaf;
        return bArr;
    }

    public int[] getStatInt() {
        return new int[]{this.f1439i, this.f1440j, this.steps, this.f1441w};
    }

    public String toString() {
        String str;
        String str2 = "";
        int i = 0;
        while (true) {
            str = " ";
            if (i >= 4) {
                break;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(getStatInt()[i]);
            sb.append(str);
            str2 = sb.toString();
            i++;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str2);
        sb2.append(str);
        sb2.append(this.mdsize);
        sb2.append(str);
        sb2.append(this.keysize);
        sb2.append(str);
        sb2.append(this.two_power_w);
        sb2.append(str);
        String sb3 = sb2.toString();
        byte[][] statByte = getStatByte();
        for (int i2 = 0; i2 < 4; i2++) {
            if (statByte[i2] != null) {
                StringBuilder sb4 = new StringBuilder();
                sb4.append(sb3);
                sb4.append(new String(Hex.encode(statByte[i2])));
                sb4.append(str);
                sb3 = sb4.toString();
            } else {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(sb3);
                sb5.append("null ");
                sb3 = sb5.toString();
            }
        }
        return sb3;
    }
}
