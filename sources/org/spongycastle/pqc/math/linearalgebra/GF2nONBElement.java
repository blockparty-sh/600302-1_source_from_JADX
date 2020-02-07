package org.spongycastle.pqc.math.linearalgebra;

import android.support.p000v4.media.session.PlaybackStateCompat;
import com.google.common.primitives.Longs;
import com.leanplum.core.BuildConfig;
import java.math.BigInteger;
import java.util.Random;
import kotlinx.coroutines.internal.LockFreeTaskQueueCore;
import org.bitcoinj.core.TransactionInput;

public class GF2nONBElement extends GF2nElement {
    private static final int MAXLONG = 64;
    private static final long[] mBitmask = {1, 2, 4, 8, 16, 32, 64, 128, 256, 512, PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID, PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH, PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM, PlaybackStateCompat.ACTION_PLAY_FROM_URI, PlaybackStateCompat.ACTION_PREPARE, PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID, PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH, PlaybackStateCompat.ACTION_PREPARE_FROM_URI, PlaybackStateCompat.ACTION_SET_REPEAT_MODE, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE_ENABLED, PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, 4194304, 8388608, 16777216, 33554432, 67108864, 134217728, 268435456, 536870912, 1073741824, 2147483648L, 4294967296L, 8589934592L, 17179869184L, 34359738368L, 68719476736L, 137438953472L, 274877906944L, 549755813888L, 1099511627776L, 2199023255552L, 4398046511104L, 8796093022208L, 17592186044416L, 35184372088832L, 70368744177664L, 140737488355328L, 281474976710656L, 562949953421312L, 1125899906842624L, 2251799813685248L, 4503599627370496L, 9007199254740992L, 18014398509481984L, 36028797018963968L, 72057594037927936L, 144115188075855872L, 288230376151711744L, 576460752303423488L, LockFreeTaskQueueCore.FROZEN_MASK, LockFreeTaskQueueCore.CLOSED_MASK, Longs.MAX_POWER_OF_TWO, Long.MIN_VALUE};
    private static final int[] mIBY64 = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
    private static final long[] mMaxmask = {1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131071, 262143, 524287, 1048575, 2097151, 4194303, 8388607, 16777215, 33554431, 67108863, 134217727, 268435455, 536870911, LockFreeTaskQueueCore.HEAD_MASK, 2147483647L, TransactionInput.NO_SEQUENCE, 8589934591L, 17179869183L, 34359738367L, 68719476735L, 137438953471L, 274877906943L, 549755813887L, 1099511627775L, 2199023255551L, 4398046511103L, 8796093022207L, 17592186044415L, 35184372088831L, 70368744177663L, 140737488355327L, 281474976710655L, 562949953421311L, 1125899906842623L, 2251799813685247L, 4503599627370495L, 9007199254740991L, 18014398509481983L, 36028797018963967L, 72057594037927935L, 144115188075855871L, 288230376151711743L, 576460752303423487L, 1152921504606846975L, 2305843009213693951L, 4611686018427387903L, Long.MAX_VALUE, -1};
    private int mBit;
    private int mLength;
    private long[] mPol;

    public GF2nONBElement(GF2nONBField gF2nONBField, Random random) {
        this.mField = gF2nONBField;
        this.mDegree = this.mField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        int i = this.mLength;
        this.mPol = new long[i];
        if (i > 1) {
            for (int i2 = 0; i2 < this.mLength - 1; i2++) {
                this.mPol[i2] = random.nextLong();
            }
            this.mPol[this.mLength - 1] = random.nextLong() >>> (64 - this.mBit);
            return;
        }
        this.mPol[0] = random.nextLong();
        long[] jArr = this.mPol;
        jArr[0] = jArr[0] >>> (64 - this.mBit);
    }

    public GF2nONBElement(GF2nONBField gF2nONBField, byte[] bArr) {
        this.mField = gF2nONBField;
        this.mDegree = this.mField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        this.mPol = new long[this.mLength];
        assign(bArr);
    }

    public GF2nONBElement(GF2nONBField gF2nONBField, BigInteger bigInteger) {
        this.mField = gF2nONBField;
        this.mDegree = this.mField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        this.mPol = new long[this.mLength];
        assign(bigInteger);
    }

    private GF2nONBElement(GF2nONBField gF2nONBField, long[] jArr) {
        this.mField = gF2nONBField;
        this.mDegree = this.mField.getDegree();
        this.mLength = gF2nONBField.getONBLength();
        this.mBit = gF2nONBField.getONBBit();
        this.mPol = jArr;
    }

    public GF2nONBElement(GF2nONBElement gF2nONBElement) {
        this.mField = gF2nONBElement.mField;
        this.mDegree = this.mField.getDegree();
        this.mLength = ((GF2nONBField) this.mField).getONBLength();
        this.mBit = ((GF2nONBField) this.mField).getONBBit();
        this.mPol = new long[this.mLength];
        assign(gF2nONBElement.getElement());
    }

    public Object clone() {
        return new GF2nONBElement(this);
    }

    public static GF2nONBElement ZERO(GF2nONBField gF2nONBField) {
        return new GF2nONBElement(gF2nONBField, new long[gF2nONBField.getONBLength()]);
    }

    public static GF2nONBElement ONE(GF2nONBField gF2nONBField) {
        int oNBLength = gF2nONBField.getONBLength();
        long[] jArr = new long[oNBLength];
        int i = 0;
        while (true) {
            int i2 = oNBLength - 1;
            if (i < i2) {
                jArr[i] = -1;
                i++;
            } else {
                jArr[i2] = mMaxmask[gF2nONBField.getONBBit() - 1];
                return new GF2nONBElement(gF2nONBField, jArr);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void assignZero() {
        this.mPol = new long[this.mLength];
    }

    /* access modifiers changed from: 0000 */
    public void assignOne() {
        int i = 0;
        while (true) {
            int i2 = this.mLength;
            if (i < i2 - 1) {
                this.mPol[i] = -1;
                i++;
            } else {
                this.mPol[i2 - 1] = mMaxmask[this.mBit - 1];
                return;
            }
        }
    }

    private void assign(BigInteger bigInteger) {
        assign(bigInteger.toByteArray());
    }

    private void assign(long[] jArr) {
        System.arraycopy(jArr, 0, this.mPol, 0, this.mLength);
    }

    private void assign(byte[] bArr) {
        this.mPol = new long[this.mLength];
        for (int i = 0; i < bArr.length; i++) {
            long[] jArr = this.mPol;
            int i2 = i >>> 3;
            jArr[i2] = jArr[i2] | ((((long) bArr[(bArr.length - 1) - i]) & 255) << ((i & 7) << 3));
        }
    }

    public boolean isZero() {
        boolean z = true;
        for (int i = 0; i < this.mLength && z; i++) {
            z = z && (this.mPol[i] & -1) == 0;
        }
        return z;
    }

    public boolean isOne() {
        boolean z = true;
        for (int i = 0; i < this.mLength - 1 && z; i++) {
            z = z && (this.mPol[i] & -1) == -1;
        }
        if (!z) {
            return z;
        }
        if (z) {
            long j = this.mPol[this.mLength - 1];
            long[] jArr = mMaxmask;
            int i2 = this.mBit;
            if ((j & jArr[i2 - 1]) == jArr[i2 - 1]) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof GF2nONBElement)) {
            return false;
        }
        GF2nONBElement gF2nONBElement = (GF2nONBElement) obj;
        for (int i = 0; i < this.mLength; i++) {
            if (this.mPol[i] != gF2nONBElement.mPol[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        return this.mPol.hashCode();
    }

    public boolean testRightmostBit() {
        return (this.mPol[this.mLength - 1] & mBitmask[this.mBit - 1]) != 0;
    }

    /* access modifiers changed from: 0000 */
    public boolean testBit(int i) {
        if (i < 0 || i > this.mDegree || (this.mPol[i >>> 6] & mBitmask[i & 63]) == 0) {
            return false;
        }
        return true;
    }

    private long[] getElement() {
        long[] jArr = this.mPol;
        long[] jArr2 = new long[jArr.length];
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length);
        return jArr2;
    }

    private long[] getElementReverseOrder() {
        long[] jArr = new long[this.mPol.length];
        for (int i = 0; i < this.mDegree; i++) {
            if (testBit((this.mDegree - i) - 1)) {
                int i2 = i >>> 6;
                jArr[i2] = jArr[i2] | mBitmask[i & 63];
            }
        }
        return jArr;
    }

    /* access modifiers changed from: 0000 */
    public void reverseOrder() {
        this.mPol = getElementReverseOrder();
    }

    public GFElement add(GFElement gFElement) throws RuntimeException {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.addToThis(gFElement);
        return gF2nONBElement;
    }

    public void addToThis(GFElement gFElement) throws RuntimeException {
        if (gFElement instanceof GF2nONBElement) {
            GF2nONBElement gF2nONBElement = (GF2nONBElement) gFElement;
            if (this.mField.equals(gF2nONBElement.mField)) {
                for (int i = 0; i < this.mLength; i++) {
                    long[] jArr = this.mPol;
                    jArr[i] = jArr[i] ^ gF2nONBElement.mPol[i];
                }
                return;
            }
            throw new RuntimeException();
        }
        throw new RuntimeException();
    }

    public GF2nElement increase() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.increaseThis();
        return gF2nONBElement;
    }

    public void increaseThis() {
        addToThis(ONE((GF2nONBField) this.mField));
    }

    public GFElement multiply(GFElement gFElement) throws RuntimeException {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.multiplyThisBy(gFElement);
        return gF2nONBElement;
    }

    public void multiplyThisBy(GFElement gFElement) throws RuntimeException {
        GFElement gFElement2 = gFElement;
        if (gFElement2 instanceof GF2nONBElement) {
            GF2nONBElement gF2nONBElement = (GF2nONBElement) gFElement2;
            if (!this.mField.equals(gF2nONBElement.mField)) {
                throw new RuntimeException();
            } else if (equals(gFElement)) {
                squareThis();
            } else {
                long[] jArr = this.mPol;
                long[] jArr2 = gF2nONBElement.mPol;
                long[] jArr3 = new long[this.mLength];
                int[][] iArr = ((GF2nONBField) this.mField).mMult;
                int i = this.mLength - 1;
                int i2 = this.mBit - 1;
                long[] jArr4 = mBitmask;
                long j = jArr4[63];
                long j2 = jArr4[i2];
                char c = 0;
                for (int i3 = 0; i3 < this.mDegree; i3++) {
                    int i4 = 0;
                    boolean z = false;
                    while (i4 < this.mDegree) {
                        int[] iArr2 = mIBY64;
                        int i5 = iArr2[i4];
                        int i6 = i4 & 63;
                        int i7 = iArr2[iArr[i4][c]];
                        int i8 = iArr[i4][c] & 63;
                        long j3 = jArr[i5];
                        long[] jArr5 = mBitmask;
                        if ((j3 & jArr5[i6]) != 0) {
                            if ((jArr2[i7] & jArr5[i8]) != 0) {
                                z = !z;
                            }
                            if (iArr[i4][1] != -1) {
                                if ((jArr2[mIBY64[iArr[i4][1]]] & mBitmask[iArr[i4][1] & 63]) != 0) {
                                    z = !z;
                                }
                            }
                        }
                        i4++;
                        c = 0;
                    }
                    int i9 = mIBY64[i3];
                    int i10 = i3 & 63;
                    if (z) {
                        jArr3[i9] = jArr3[i9] ^ mBitmask[i10];
                    }
                    if (this.mLength > 1) {
                        int i11 = i - 1;
                        boolean z2 = (jArr[i] & 1) == 1;
                        int i12 = i11;
                        while (i12 >= 0) {
                            boolean z3 = (jArr[i12] & 1) != 0;
                            jArr[i12] = jArr[i12] >>> 1;
                            if (z2) {
                                jArr[i12] = jArr[i12] ^ j;
                            }
                            i12--;
                            z2 = z3;
                        }
                        jArr[i] = jArr[i] >>> 1;
                        if (z2) {
                            jArr[i] = jArr[i] ^ j2;
                        }
                        boolean z4 = (jArr2[i] & 1) == 1;
                        while (i11 >= 0) {
                            boolean z5 = (jArr2[i11] & 1) != 0;
                            jArr2[i11] = jArr2[i11] >>> 1;
                            if (z4) {
                                jArr2[i11] = jArr2[i11] ^ j;
                            }
                            i11--;
                            z4 = z5;
                        }
                        jArr2[i] = jArr2[i] >>> 1;
                        if (z4) {
                            jArr2[i] = jArr2[i] ^ j2;
                        }
                        c = 0;
                    } else {
                        c = 0;
                        boolean z6 = (jArr[0] & 1) == 1;
                        jArr[0] = jArr[0] >>> 1;
                        if (z6) {
                            jArr[0] = jArr[0] ^ j2;
                        }
                        boolean z7 = (jArr2[0] & 1) == 1;
                        jArr2[0] = jArr2[0] >>> 1;
                        if (z7) {
                            jArr2[0] = jArr2[0] ^ j2;
                        }
                    }
                }
                assign(jArr3);
            }
        } else {
            throw new RuntimeException("The elements have different representation: not yet implemented");
        }
    }

    public GF2nElement square() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.squareThis();
        return gF2nONBElement;
    }

    public void squareThis() {
        long[] element = getElement();
        int i = this.mLength - 1;
        int i2 = this.mBit - 1;
        long[] jArr = mBitmask;
        long j = jArr[63];
        boolean z = false;
        boolean z2 = (element[i] & jArr[i2]) != 0;
        int i3 = 0;
        while (i3 < i) {
            boolean z3 = (element[i3] & j) != 0;
            element[i3] = element[i3] << 1;
            if (z2) {
                element[i3] = 1 ^ element[i3];
            }
            i3++;
            z2 = z3;
        }
        if ((element[i] & mBitmask[i2]) != 0) {
            z = true;
        }
        element[i] = element[i] << 1;
        if (z2) {
            element[i] = element[i] ^ 1;
        }
        if (z) {
            element[i] = mBitmask[i2 + 1] ^ element[i];
        }
        assign(element);
    }

    public GFElement invert() throws ArithmeticException {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.invertThis();
        return gF2nONBElement;
    }

    public void invertThis() throws ArithmeticException {
        if (!isZero()) {
            int i = 31;
            boolean z = false;
            while (!z && i >= 0) {
                if ((((long) (this.mDegree - 1)) & mBitmask[i]) != 0) {
                    z = true;
                }
                i--;
            }
            int i2 = i + 1;
            ZERO((GF2nONBField) this.mField);
            GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
            int i3 = 1;
            for (int i4 = i2 - 1; i4 >= 0; i4--) {
                GF2nElement gF2nElement = (GF2nElement) gF2nONBElement.clone();
                for (int i5 = 1; i5 <= i3; i5++) {
                    gF2nElement.squareThis();
                }
                gF2nONBElement.multiplyThisBy(gF2nElement);
                i3 <<= 1;
                if ((((long) (this.mDegree - 1)) & mBitmask[i4]) != 0) {
                    gF2nONBElement.squareThis();
                    gF2nONBElement.multiplyThisBy(this);
                    i3++;
                }
            }
            gF2nONBElement.squareThis();
            return;
        }
        throw new ArithmeticException();
    }

    public GF2nElement squareRoot() {
        GF2nONBElement gF2nONBElement = new GF2nONBElement(this);
        gF2nONBElement.squareRootThis();
        return gF2nONBElement;
    }

    public void squareRootThis() {
        long[] element = getElement();
        int i = this.mLength - 1;
        int i2 = this.mBit - 1;
        long j = mBitmask[63];
        boolean z = (element[0] & 1) != 0;
        int i3 = i;
        while (i3 >= 0) {
            boolean z2 = (element[i3] & 1) != 0;
            element[i3] = element[i3] >>> 1;
            if (z) {
                if (i3 == i) {
                    element[i3] = element[i3] ^ mBitmask[i2];
                } else {
                    element[i3] = element[i3] ^ j;
                }
            }
            i3--;
            z = z2;
        }
        assign(element);
    }

    public int trace() {
        int i = this.mLength - 1;
        int i2 = 0;
        int i3 = 0;
        while (i2 < i) {
            int i4 = i3;
            for (int i5 = 0; i5 < 64; i5++) {
                if ((this.mPol[i2] & mBitmask[i5]) != 0) {
                    i4 ^= 1;
                }
            }
            i2++;
            i3 = i4;
        }
        int i6 = this.mBit;
        for (int i7 = 0; i7 < i6; i7++) {
            if ((this.mPol[i] & mBitmask[i7]) != 0) {
                i3 ^= 1;
            }
        }
        return i3;
    }

    public GF2nElement solveQuadraticEquation() throws RuntimeException {
        if (trace() != 1) {
            long j = mBitmask[63];
            long[] jArr = new long[this.mLength];
            int i = 0;
            long j2 = 0;
            for (int i2 = 1; i < this.mLength - i2; i2 = 1) {
                long j3 = j2;
                for (int i3 = 1; i3 < 64; i3++) {
                    long[] jArr2 = mBitmask;
                    if ((jArr2[i3] & this.mPol[i]) == 0 || (j3 & jArr2[i3 - 1]) == 0) {
                        long j4 = this.mPol[i];
                        long[] jArr3 = mBitmask;
                        if ((j4 & jArr3[i3]) != 0 || (jArr3[i3 - 1] & j3) != 0) {
                            j3 ^= mBitmask[i3];
                        }
                    }
                }
                jArr[i] = j3;
                int i4 = ((j & j3) > 0 ? 1 : ((j & j3) == 0 ? 0 : -1));
                j2 = ((i4 == 0 || (1 & this.mPol[i + 1]) != 1) && !(i4 == 0 && (this.mPol[i + 1] & 1) == 0)) ? 1 : 0;
                i++;
            }
            int i5 = this.mDegree & 63;
            long j5 = this.mPol[this.mLength - 1];
            for (int i6 = 1; i6 < i5; i6++) {
                long[] jArr4 = mBitmask;
                if ((jArr4[i6] & j5) == 0 || (jArr4[i6 - 1] & j2) == 0) {
                    long[] jArr5 = mBitmask;
                    if ((jArr5[i6] & j5) != 0 || (jArr5[i6 - 1] & j2) != 0) {
                        j2 ^= mBitmask[i6];
                    }
                }
            }
            jArr[this.mLength - 1] = j2;
            return new GF2nONBElement((GF2nONBField) this.mField, jArr);
        }
        throw new RuntimeException();
    }

    public String toString() {
        return toString(16);
    }

    public String toString(int i) {
        String str;
        String str2;
        String str3;
        long[] element = getElement();
        int i2 = this.mBit;
        String str4 = "";
        if (i == 2) {
            while (true) {
                i2--;
                str = BuildConfig.BUILD_NUMBER;
                str2 = "1";
                if (i2 < 0) {
                    break;
                }
                if ((element[element.length - 1] & (1 << i2)) == 0) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(str4);
                    sb.append(str);
                    str3 = sb.toString();
                } else {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(str4);
                    sb2.append(str2);
                    str3 = sb2.toString();
                }
                str4 = str3;
            }
            for (int length = element.length - 2; length >= 0; length--) {
                for (int i3 = 63; i3 >= 0; i3--) {
                    if ((element[length] & mBitmask[i3]) == 0) {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append(str4);
                        sb3.append(str);
                        str4 = sb3.toString();
                    } else {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(str4);
                        sb4.append(str2);
                        str4 = sb4.toString();
                    }
                }
            }
        } else if (i == 16) {
            char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            for (int length2 = element.length - 1; length2 >= 0; length2--) {
                StringBuilder sb5 = new StringBuilder();
                sb5.append(str4);
                sb5.append(cArr[((int) (element[length2] >>> 60)) & 15]);
                String sb6 = sb5.toString();
                StringBuilder sb7 = new StringBuilder();
                sb7.append(sb6);
                sb7.append(cArr[((int) (element[length2] >>> 56)) & 15]);
                String sb8 = sb7.toString();
                StringBuilder sb9 = new StringBuilder();
                sb9.append(sb8);
                sb9.append(cArr[((int) (element[length2] >>> 52)) & 15]);
                String sb10 = sb9.toString();
                StringBuilder sb11 = new StringBuilder();
                sb11.append(sb10);
                sb11.append(cArr[((int) (element[length2] >>> 48)) & 15]);
                String sb12 = sb11.toString();
                StringBuilder sb13 = new StringBuilder();
                sb13.append(sb12);
                sb13.append(cArr[((int) (element[length2] >>> 44)) & 15]);
                String sb14 = sb13.toString();
                StringBuilder sb15 = new StringBuilder();
                sb15.append(sb14);
                sb15.append(cArr[((int) (element[length2] >>> 40)) & 15]);
                String sb16 = sb15.toString();
                StringBuilder sb17 = new StringBuilder();
                sb17.append(sb16);
                sb17.append(cArr[((int) (element[length2] >>> 36)) & 15]);
                String sb18 = sb17.toString();
                StringBuilder sb19 = new StringBuilder();
                sb19.append(sb18);
                sb19.append(cArr[((int) (element[length2] >>> 32)) & 15]);
                String sb20 = sb19.toString();
                StringBuilder sb21 = new StringBuilder();
                sb21.append(sb20);
                sb21.append(cArr[((int) (element[length2] >>> 28)) & 15]);
                String sb22 = sb21.toString();
                StringBuilder sb23 = new StringBuilder();
                sb23.append(sb22);
                sb23.append(cArr[((int) (element[length2] >>> 24)) & 15]);
                String sb24 = sb23.toString();
                StringBuilder sb25 = new StringBuilder();
                sb25.append(sb24);
                sb25.append(cArr[((int) (element[length2] >>> 20)) & 15]);
                String sb26 = sb25.toString();
                StringBuilder sb27 = new StringBuilder();
                sb27.append(sb26);
                sb27.append(cArr[((int) (element[length2] >>> 16)) & 15]);
                String sb28 = sb27.toString();
                StringBuilder sb29 = new StringBuilder();
                sb29.append(sb28);
                sb29.append(cArr[((int) (element[length2] >>> 12)) & 15]);
                String sb30 = sb29.toString();
                StringBuilder sb31 = new StringBuilder();
                sb31.append(sb30);
                sb31.append(cArr[((int) (element[length2] >>> 8)) & 15]);
                String sb32 = sb31.toString();
                StringBuilder sb33 = new StringBuilder();
                sb33.append(sb32);
                sb33.append(cArr[((int) (element[length2] >>> 4)) & 15]);
                String sb34 = sb33.toString();
                StringBuilder sb35 = new StringBuilder();
                sb35.append(sb34);
                sb35.append(cArr[((int) element[length2]) & 15]);
                String sb36 = sb35.toString();
                StringBuilder sb37 = new StringBuilder();
                sb37.append(sb36);
                sb37.append(" ");
                str4 = sb37.toString();
            }
        }
        return str4;
    }

    public BigInteger toFlexiBigInt() {
        return new BigInteger(1, toByteArray());
    }

    public byte[] toByteArray() {
        int i = ((this.mDegree - 1) >> 3) + 1;
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = (i2 & 7) << 3;
            bArr[(i - i2) - 1] = (byte) ((int) ((this.mPol[i2 >>> 3] & (255 << i3)) >>> i3));
        }
        return bArr;
    }
}
