package org.bitcoinj.crypto;

import com.google.common.base.Preconditions;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.ECKey.ECDSASignature;
import org.bitcoinj.core.Transaction.SigHash;
import org.bitcoinj.core.VerificationException;

public class TransactionSignature extends ECDSASignature {
    public final int sighashFlags;

    public TransactionSignature(BigInteger bigInteger, BigInteger bigInteger2) {
        this(bigInteger, bigInteger2, SigHash.ALL.value);
    }

    public TransactionSignature(BigInteger bigInteger, BigInteger bigInteger2, int i) {
        super(bigInteger, bigInteger2);
        this.sighashFlags = i;
    }

    public TransactionSignature(ECDSASignature eCDSASignature, SigHash sigHash, boolean z) {
        super(eCDSASignature.f796r, eCDSASignature.f797s);
        this.sighashFlags = calcSigHashValue(sigHash, z);
    }

    public TransactionSignature(ECDSASignature eCDSASignature, SigHash sigHash, boolean z, boolean z2) {
        super(eCDSASignature.f796r, eCDSASignature.f797s);
        this.sighashFlags = calcSigHashValue(sigHash, z, z2);
    }

    public static TransactionSignature dummy() {
        BigInteger bigInteger = ECKey.HALF_CURVE_ORDER;
        return new TransactionSignature(bigInteger, bigInteger);
    }

    public static int calcSigHashValue(SigHash sigHash, boolean z) {
        Preconditions.checkArgument(SigHash.ALL == sigHash || SigHash.NONE == sigHash || SigHash.SINGLE == sigHash);
        int i = sigHash.value;
        return z ? i | SigHash.ANYONECANPAY.value : i;
    }

    public static int calcSigHashValue(SigHash sigHash, boolean z, boolean z2) {
        Preconditions.checkArgument(SigHash.ALL == sigHash || SigHash.NONE == sigHash || SigHash.SINGLE == sigHash);
        int i = sigHash.value;
        if (z) {
            i |= SigHash.ANYONECANPAY.value;
        }
        return z2 ? i | SigHash.FORKID.value : i;
    }

    public static boolean isEncodingCanonical(byte[] bArr) {
        if (bArr.length >= 9 && bArr.length <= 73) {
            byte b = bArr[bArr.length - 1] & 255 & (~(SigHash.ANYONECANPAY.value | SigHash.FORKID.value));
            if (b >= SigHash.ALL.value && b <= SigHash.SINGLE.value && (bArr[0] & 255) == 48 && (bArr[1] & 255) == bArr.length - 3) {
                byte b2 = bArr[3] & 255;
                int i = b2 + 5;
                if (i < bArr.length && b2 != 0) {
                    byte b3 = bArr[i] & 255;
                    if (b2 + b3 + 7 != bArr.length || b3 == 0 || bArr[2] != 2 || (bArr[4] & 128) == 128 || (b2 > 1 && bArr[4] == 0 && (bArr[5] & 128) != 128)) {
                        return false;
                    }
                    int i2 = b2 + 6;
                    if (bArr[i2 - 2] == 2 && (bArr[i2] & 128) != 128) {
                        if (b3 <= 1 || bArr[i2] != 0 || (bArr[i2 + 1] & 128) == 128) {
                            return true;
                        }
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public static boolean hasForkId(byte[] bArr) {
        if ((bArr[bArr.length - 1] & 255 & SigHash.FORKID.value) == SigHash.FORKID.value) {
            return true;
        }
        return false;
    }

    public boolean anyoneCanPay() {
        return (this.sighashFlags & SigHash.ANYONECANPAY.value) != 0;
    }

    public boolean useForkId() {
        return (this.sighashFlags & SigHash.FORKID.value) != 0;
    }

    public SigHash sigHashMode() {
        int i = this.sighashFlags & 31;
        if (i == SigHash.NONE.value) {
            return SigHash.NONE;
        }
        if (i == SigHash.SINGLE.value) {
            return SigHash.SINGLE;
        }
        return SigHash.ALL;
    }

    public byte[] encodeToBitcoin() {
        try {
            ByteArrayOutputStream derByteStream = derByteStream();
            derByteStream.write(this.sighashFlags);
            return derByteStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ECDSASignature toCanonicalised() {
        return new TransactionSignature(super.toCanonicalised(), sigHashMode(), anyoneCanPay(), useForkId());
    }

    @Deprecated
    public static TransactionSignature decodeFromBitcoin(byte[] bArr, boolean z) throws VerificationException {
        return decodeFromBitcoin(bArr, z, false);
    }

    public static TransactionSignature decodeFromBitcoin(byte[] bArr, boolean z, boolean z2) throws VerificationException {
        if (!z || isEncodingCanonical(bArr)) {
            try {
                ECDSASignature decodeFromDER = ECDSASignature.decodeFromDER(bArr);
                if (!z2 || decodeFromDER.isCanonical()) {
                    return new TransactionSignature(decodeFromDER.f796r, decodeFromDER.f797s, (int) bArr[bArr.length - 1]);
                }
                throw new VerificationException("S-value is not canonical.");
            } catch (IllegalArgumentException e) {
                throw new VerificationException("Could not decode DER", e);
            }
        } else {
            throw new VerificationException("Signature encoding is not canonical.");
        }
    }
}
