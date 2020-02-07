package org.bitcoinj.core;

import com.google.common.base.Preconditions;
import com.google.common.p011io.ByteStreams;
import com.google.common.primitives.Ints;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Sha256Hash implements Serializable, Comparable<Sha256Hash> {
    public static final int LENGTH = 32;
    public static final Sha256Hash ZERO_HASH = wrap(new byte[32]);
    private final byte[] bytes;

    @Deprecated
    public Sha256Hash(byte[] bArr) {
        Preconditions.checkArgument(bArr.length == 32);
        this.bytes = bArr;
    }

    @Deprecated
    public Sha256Hash(String str) {
        Preconditions.checkArgument(str.length() == 64);
        this.bytes = C3447Utils.HEX.decode(str);
    }

    public static Sha256Hash wrap(byte[] bArr) {
        return new Sha256Hash(bArr);
    }

    public static Sha256Hash wrap(String str) {
        return wrap(C3447Utils.HEX.decode(str));
    }

    public static Sha256Hash wrapReversed(byte[] bArr) {
        return wrap(C3447Utils.reverseBytes(bArr));
    }

    @Deprecated
    public static Sha256Hash create(byte[] bArr) {
        return m338of(bArr);
    }

    /* renamed from: of */
    public static Sha256Hash m338of(byte[] bArr) {
        return wrap(hash(bArr));
    }

    @Deprecated
    public static Sha256Hash createDouble(byte[] bArr) {
        return twiceOf(bArr);
    }

    public static Sha256Hash twiceOf(byte[] bArr) {
        return wrap(hashTwice(bArr));
    }

    /* renamed from: of */
    public static Sha256Hash m337of(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        try {
            return m338of(ByteStreams.toByteArray(fileInputStream));
        } finally {
            fileInputStream.close();
        }
    }

    public static MessageDigest newDigest() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] hash(byte[] bArr) {
        return hash(bArr, 0, bArr.length);
    }

    public static byte[] hash(byte[] bArr, int i, int i2) {
        MessageDigest newDigest = newDigest();
        newDigest.update(bArr, i, i2);
        return newDigest.digest();
    }

    public static byte[] hashTwice(byte[] bArr) {
        return hashTwice(bArr, 0, bArr.length);
    }

    public static byte[] hashTwice(byte[] bArr, int i, int i2) {
        MessageDigest newDigest = newDigest();
        newDigest.update(bArr, i, i2);
        return newDigest.digest(newDigest.digest());
    }

    public static byte[] hashTwice(byte[] bArr, int i, int i2, byte[] bArr2, int i3, int i4) {
        MessageDigest newDigest = newDigest();
        newDigest.update(bArr, i, i2);
        newDigest.update(bArr2, i3, i4);
        return newDigest.digest(newDigest.digest());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Arrays.equals(this.bytes, ((Sha256Hash) obj).bytes);
    }

    public int hashCode() {
        byte[] bArr = this.bytes;
        return Ints.fromBytes(bArr[28], bArr[29], bArr[30], bArr[31]);
    }

    public String toString() {
        return C3447Utils.HEX.encode(this.bytes);
    }

    public BigInteger toBigInteger() {
        return new BigInteger(1, this.bytes);
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public byte[] getReversedBytes() {
        return C3447Utils.reverseBytes(this.bytes);
    }

    public int compareTo(Sha256Hash sha256Hash) {
        for (int i = 31; i >= 0; i--) {
            byte b = this.bytes[i] & 255;
            byte b2 = sha256Hash.bytes[i] & 255;
            if (b > b2) {
                return 1;
            }
            if (b < b2) {
                return -1;
            }
        }
        return 0;
    }
}
