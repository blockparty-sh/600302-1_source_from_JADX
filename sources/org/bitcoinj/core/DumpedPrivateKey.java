package org.bitcoinj.core;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.util.Arrays;
import javax.annotation.Nullable;

public class DumpedPrivateKey extends VersionedChecksummedBytes {
    private boolean compressed;

    public static DumpedPrivateKey fromBase58(@Nullable NetworkParameters networkParameters, String str) throws AddressFormatException {
        return new DumpedPrivateKey(networkParameters, str);
    }

    DumpedPrivateKey(NetworkParameters networkParameters, byte[] bArr, boolean z) {
        super(networkParameters.getDumpedPrivateKeyHeader(), encode(bArr, z));
        this.compressed = z;
    }

    private static byte[] encode(byte[] bArr, boolean z) {
        Preconditions.checkArgument(bArr.length == 32, "Private keys must be 32 bytes");
        if (!z) {
            return bArr;
        }
        byte[] bArr2 = new byte[33];
        System.arraycopy(bArr, 0, bArr2, 0, 32);
        bArr2[32] = 1;
        return bArr2;
    }

    @Deprecated
    public DumpedPrivateKey(@Nullable NetworkParameters networkParameters, String str) throws AddressFormatException {
        super(str);
        if (networkParameters != null && this.version != networkParameters.getDumpedPrivateKeyHeader()) {
            throw new WrongNetworkException(this.version, new int[]{networkParameters.getDumpedPrivateKeyHeader()});
        } else if (this.bytes.length == 33 && this.bytes[32] == 1) {
            this.compressed = true;
            this.bytes = Arrays.copyOf(this.bytes, 32);
        } else if (this.bytes.length == 32) {
            this.compressed = false;
        } else {
            throw new AddressFormatException("Wrong number of bytes for a private key, not 32 or 33");
        }
    }

    public ECKey getKey() {
        ECKey fromPrivate = ECKey.fromPrivate(this.bytes);
        return this.compressed ? fromPrivate : fromPrivate.decompress();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DumpedPrivateKey dumpedPrivateKey = (DumpedPrivateKey) obj;
        if (!(this.version == dumpedPrivateKey.version && this.compressed == dumpedPrivateKey.compressed && Arrays.equals(this.bytes, dumpedPrivateKey.bytes))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.version), Boolean.valueOf(this.compressed), Integer.valueOf(Arrays.hashCode(this.bytes)));
    }
}
