package org.bitcoinj.core;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedBytes;
import java.io.Serializable;
import java.util.Arrays;

public class VersionedChecksummedBytes implements Serializable, Cloneable, Comparable<VersionedChecksummedBytes> {
    protected byte[] bytes;
    protected final int version;

    protected VersionedChecksummedBytes(String str) throws AddressFormatException {
        byte[] decodeChecked = Base58.decodeChecked(str);
        this.version = decodeChecked[0] & 255;
        this.bytes = new byte[(decodeChecked.length - 1)];
        System.arraycopy(decodeChecked, 1, this.bytes, 0, decodeChecked.length - 1);
    }

    protected VersionedChecksummedBytes(int i, byte[] bArr) {
        Preconditions.checkArgument(i >= 0 && i < 256);
        this.version = i;
        this.bytes = bArr;
    }

    public final String toBase58() {
        byte[] bArr = this.bytes;
        byte[] bArr2 = new byte[(bArr.length + 1 + 4)];
        bArr2[0] = (byte) this.version;
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        System.arraycopy(Sha256Hash.hashTwice(bArr2, 0, this.bytes.length + 1), 0, bArr2, this.bytes.length + 1, 4);
        return Base58.encode(bArr2);
    }

    public String toString() {
        return toBase58();
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.version), Integer.valueOf(Arrays.hashCode(this.bytes)));
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        VersionedChecksummedBytes versionedChecksummedBytes = (VersionedChecksummedBytes) obj;
        if (this.version != versionedChecksummedBytes.version || !Arrays.equals(this.bytes, versionedChecksummedBytes.bytes)) {
            z = false;
        }
        return z;
    }

    public VersionedChecksummedBytes clone() throws CloneNotSupportedException {
        return (VersionedChecksummedBytes) super.clone();
    }

    public int compareTo(VersionedChecksummedBytes versionedChecksummedBytes) {
        int compare = Ints.compare(this.version, versionedChecksummedBytes.version);
        return compare != 0 ? compare : UnsignedBytes.lexicographicalComparator().compare(this.bytes, versionedChecksummedBytes.bytes);
    }

    public int getVersion() {
        return this.version;
    }
}
