package org.bitcoinj.crypto;

import com.google.common.base.Objects;
import java.util.Arrays;

public final class EncryptedData {
    public final byte[] encryptedBytes;
    public final byte[] initialisationVector;

    public EncryptedData(byte[] bArr, byte[] bArr2) {
        this.initialisationVector = Arrays.copyOf(bArr, bArr.length);
        this.encryptedBytes = Arrays.copyOf(bArr2, bArr2.length);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EncryptedData encryptedData = (EncryptedData) obj;
        if (!Arrays.equals(this.encryptedBytes, encryptedData.encryptedBytes) || !Arrays.equals(this.initialisationVector, encryptedData.initialisationVector)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(Arrays.hashCode(this.encryptedBytes)), Integer.valueOf(Arrays.hashCode(this.initialisationVector)));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EncryptedData [initialisationVector=");
        sb.append(Arrays.toString(this.initialisationVector));
        sb.append(", encryptedPrivateKey=");
        sb.append(Arrays.toString(this.encryptedBytes));
        sb.append("]");
        return sb.toString();
    }
}
