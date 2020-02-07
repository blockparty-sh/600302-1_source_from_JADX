package org.spongycastle.crypto.engines;

import java.math.BigInteger;

public class CramerShoupCiphertext {

    /* renamed from: e */
    BigInteger f1132e;

    /* renamed from: u1 */
    BigInteger f1133u1;

    /* renamed from: u2 */
    BigInteger f1134u2;

    /* renamed from: v */
    BigInteger f1135v;

    public CramerShoupCiphertext() {
    }

    public CramerShoupCiphertext(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, BigInteger bigInteger4) {
        this.f1133u1 = bigInteger;
        this.f1134u2 = bigInteger2;
        this.f1132e = bigInteger3;
        this.f1135v = bigInteger4;
    }

    public CramerShoupCiphertext(byte[] bArr) {
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 0, bArr2, 0, 4);
        int byteArrayToInt = byteArrayToInt(bArr2);
        byte[] bArr3 = new byte[byteArrayToInt];
        System.arraycopy(bArr, 4, bArr3, 0, byteArrayToInt);
        int i = byteArrayToInt + 4;
        this.f1133u1 = new BigInteger(bArr3);
        System.arraycopy(bArr, i, bArr2, 0, 4);
        int byteArrayToInt2 = byteArrayToInt(bArr2);
        byte[] bArr4 = new byte[byteArrayToInt2];
        int i2 = i + 4;
        System.arraycopy(bArr, i2, bArr4, 0, byteArrayToInt2);
        int i3 = i2 + byteArrayToInt2;
        this.f1134u2 = new BigInteger(bArr4);
        System.arraycopy(bArr, i3, bArr2, 0, 4);
        int byteArrayToInt3 = byteArrayToInt(bArr2);
        byte[] bArr5 = new byte[byteArrayToInt3];
        int i4 = i3 + 4;
        System.arraycopy(bArr, i4, bArr5, 0, byteArrayToInt3);
        int i5 = i4 + byteArrayToInt3;
        this.f1132e = new BigInteger(bArr5);
        System.arraycopy(bArr, i5, bArr2, 0, 4);
        int byteArrayToInt4 = byteArrayToInt(bArr2);
        byte[] bArr6 = new byte[byteArrayToInt4];
        System.arraycopy(bArr, i5 + 4, bArr6, 0, byteArrayToInt4);
        this.f1135v = new BigInteger(bArr6);
    }

    public BigInteger getU1() {
        return this.f1133u1;
    }

    public void setU1(BigInteger bigInteger) {
        this.f1133u1 = bigInteger;
    }

    public BigInteger getU2() {
        return this.f1134u2;
    }

    public void setU2(BigInteger bigInteger) {
        this.f1134u2 = bigInteger;
    }

    public BigInteger getE() {
        return this.f1132e;
    }

    public void setE(BigInteger bigInteger) {
        this.f1132e = bigInteger;
    }

    public BigInteger getV() {
        return this.f1135v;
    }

    public void setV(BigInteger bigInteger) {
        this.f1135v = bigInteger;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder sb = new StringBuilder();
        sb.append("u1: ");
        sb.append(this.f1133u1.toString());
        stringBuffer.append(sb.toString());
        StringBuilder sb2 = new StringBuilder();
        sb2.append("\nu2: ");
        sb2.append(this.f1134u2.toString());
        stringBuffer.append(sb2.toString());
        StringBuilder sb3 = new StringBuilder();
        sb3.append("\ne: ");
        sb3.append(this.f1132e.toString());
        stringBuffer.append(sb3.toString());
        StringBuilder sb4 = new StringBuilder();
        sb4.append("\nv: ");
        sb4.append(this.f1135v.toString());
        stringBuffer.append(sb4.toString());
        return stringBuffer.toString();
    }

    public byte[] toByteArray() {
        byte[] byteArray = this.f1133u1.toByteArray();
        int length = byteArray.length;
        byte[] byteArray2 = this.f1134u2.toByteArray();
        int length2 = byteArray2.length;
        byte[] byteArray3 = this.f1132e.toByteArray();
        int length3 = byteArray3.length;
        byte[] byteArray4 = this.f1135v.toByteArray();
        int length4 = byteArray4.length;
        byte[] bArr = new byte[(length + length2 + length3 + length4 + 16)];
        System.arraycopy(intToByteArray(length), 0, bArr, 0, 4);
        System.arraycopy(byteArray, 0, bArr, 4, length);
        int i = length + 4;
        System.arraycopy(intToByteArray(length2), 0, bArr, i, 4);
        int i2 = i + 4;
        System.arraycopy(byteArray2, 0, bArr, i2, length2);
        int i3 = i2 + length2;
        System.arraycopy(intToByteArray(length3), 0, bArr, i3, 4);
        int i4 = i3 + 4;
        System.arraycopy(byteArray3, 0, bArr, i4, length3);
        int i5 = i4 + length3;
        System.arraycopy(intToByteArray(length4), 0, bArr, i5, 4);
        System.arraycopy(byteArray4, 0, bArr, i5 + 4, length4);
        return bArr;
    }

    private byte[] intToByteArray(int i) {
        byte[] bArr = new byte[4];
        for (int i2 = 0; i2 < 4; i2++) {
            bArr[3 - i2] = (byte) (i >>> (i2 * 8));
        }
        return bArr;
    }

    private int byteArrayToInt(byte[] bArr) {
        if (bArr.length != 4) {
            return -1;
        }
        int i = 0;
        for (int i2 = 3; i2 >= 0; i2--) {
            i += bArr[i2] << ((3 - i2) * 8);
        }
        return i;
    }
}
