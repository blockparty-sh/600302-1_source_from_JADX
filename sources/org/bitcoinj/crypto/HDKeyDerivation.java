package org.bitcoinj.crypto;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Arrays;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.ECKey;
import org.spongycastle.math.p025ec.ECPoint;

public final class HDKeyDerivation {
    public static final int MAX_CHILD_DERIVATION_ATTEMPTS = 100;
    private static final BigInteger RAND_INT = new BigInteger(256, new SecureRandom());

    /* renamed from: org.bitcoinj.crypto.HDKeyDerivation$1 */
    static /* synthetic */ class C34491 {
        static final /* synthetic */ int[] $SwitchMap$org$bitcoinj$crypto$HDKeyDerivation$PublicDeriveMode = new int[PublicDeriveMode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                org.bitcoinj.crypto.HDKeyDerivation$PublicDeriveMode[] r0 = org.bitcoinj.crypto.HDKeyDerivation.PublicDeriveMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$org$bitcoinj$crypto$HDKeyDerivation$PublicDeriveMode = r0
                int[] r0 = $SwitchMap$org$bitcoinj$crypto$HDKeyDerivation$PublicDeriveMode     // Catch:{ NoSuchFieldError -> 0x0014 }
                org.bitcoinj.crypto.HDKeyDerivation$PublicDeriveMode r1 = org.bitcoinj.crypto.HDKeyDerivation.PublicDeriveMode.NORMAL     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = $SwitchMap$org$bitcoinj$crypto$HDKeyDerivation$PublicDeriveMode     // Catch:{ NoSuchFieldError -> 0x001f }
                org.bitcoinj.crypto.HDKeyDerivation$PublicDeriveMode r1 = org.bitcoinj.crypto.HDKeyDerivation.PublicDeriveMode.WITH_INVERSION     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.crypto.HDKeyDerivation.C34491.<clinit>():void");
        }
    }

    public enum PublicDeriveMode {
        NORMAL,
        WITH_INVERSION
    }

    public static class RawKeyBytes {
        public final byte[] chainCode;
        public final byte[] keyBytes;

        public RawKeyBytes(byte[] bArr, byte[] bArr2) {
            this.keyBytes = bArr;
            this.chainCode = bArr2;
        }
    }

    static {
        if (C3447Utils.isAndroidRuntime()) {
            new LinuxSecureRandom();
        }
    }

    private HDKeyDerivation() {
    }

    public static DeterministicKey createMasterPrivateKey(byte[] bArr) throws HDDerivationException {
        boolean z = true;
        Preconditions.checkArgument(bArr.length > 8, "Seed is too short and could be brute forced");
        byte[] hmacSha512 = HDUtils.hmacSha512(HDUtils.createHmacSha512Digest("Bitcoin seed".getBytes()), bArr);
        if (hmacSha512.length != 64) {
            z = false;
        }
        Preconditions.checkState(z, Integer.valueOf(hmacSha512.length));
        byte[] copyOfRange = Arrays.copyOfRange(hmacSha512, 0, 32);
        byte[] copyOfRange2 = Arrays.copyOfRange(hmacSha512, 32, 64);
        Arrays.fill(hmacSha512, 0);
        DeterministicKey createMasterPrivKeyFromBytes = createMasterPrivKeyFromBytes(copyOfRange, copyOfRange2);
        Arrays.fill(copyOfRange, 0);
        Arrays.fill(copyOfRange2, 0);
        createMasterPrivKeyFromBytes.setCreationTimeSeconds(C3447Utils.currentTimeSeconds());
        return createMasterPrivKeyFromBytes;
    }

    public static DeterministicKey createMasterPrivKeyFromBytes(byte[] bArr, byte[] bArr2) throws HDDerivationException {
        BigInteger bigInteger = new BigInteger(1, bArr);
        String str = "Generated master key is invalid.";
        assertNonZero(bigInteger, str);
        assertLessThanN(bigInteger, str);
        return new DeterministicKey(ImmutableList.m126of(), bArr2, bigInteger, null);
    }

    public static DeterministicKey createMasterPubKeyFromBytes(byte[] bArr, byte[] bArr2) {
        DeterministicKey deterministicKey = new DeterministicKey(ImmutableList.m126of(), bArr2, new LazyECPoint(ECKey.CURVE.getCurve(), bArr), (BigInteger) null, (DeterministicKey) null);
        return deterministicKey;
    }

    public static DeterministicKey deriveChildKey(DeterministicKey deterministicKey, int i) {
        return deriveChildKey(deterministicKey, new ChildNumber(i));
    }

    public static DeterministicKey deriveThisOrNextChildKey(DeterministicKey deterministicKey, int i) {
        ChildNumber childNumber = new ChildNumber(i);
        boolean isHardened = childNumber.isHardened();
        int i2 = 0;
        while (i2 < 100) {
            try {
                ChildNumber childNumber2 = new ChildNumber(childNumber.num() + i2, isHardened);
                try {
                    return deriveChildKey(deterministicKey, childNumber2);
                } catch (HDDerivationException unused) {
                    childNumber = childNumber2;
                    i2++;
                }
            } catch (HDDerivationException unused2) {
                i2++;
            }
        }
        throw new HDDerivationException("Maximum number of child derivation attempts reached, this is probably an indication of a bug.");
    }

    public static DeterministicKey deriveChildKey(DeterministicKey deterministicKey, ChildNumber childNumber) throws HDDerivationException {
        if (!deterministicKey.hasPrivKey()) {
            RawKeyBytes deriveChildKeyBytesFromPublic = deriveChildKeyBytesFromPublic(deterministicKey, childNumber, PublicDeriveMode.NORMAL);
            DeterministicKey deterministicKey2 = new DeterministicKey(HDUtils.append(deterministicKey.getPath(), childNumber), deriveChildKeyBytesFromPublic.chainCode, new LazyECPoint(ECKey.CURVE.getCurve(), deriveChildKeyBytesFromPublic.keyBytes), (BigInteger) null, deterministicKey);
            return deterministicKey2;
        }
        RawKeyBytes deriveChildKeyBytesFromPrivate = deriveChildKeyBytesFromPrivate(deterministicKey, childNumber);
        return new DeterministicKey(HDUtils.append(deterministicKey.getPath(), childNumber), deriveChildKeyBytesFromPrivate.chainCode, new BigInteger(1, deriveChildKeyBytesFromPrivate.keyBytes), deterministicKey);
    }

    public static RawKeyBytes deriveChildKeyBytesFromPrivate(DeterministicKey deterministicKey, ChildNumber childNumber) throws HDDerivationException {
        Preconditions.checkArgument(deterministicKey.hasPrivKey(), "Parent key must have private key bytes for this method.");
        byte[] encoded = deterministicKey.getPubKeyPoint().getEncoded(true);
        boolean z = encoded.length == 33;
        StringBuilder sb = new StringBuilder();
        sb.append("Parent pubkey must be 33 bytes, but is ");
        sb.append(encoded.length);
        Preconditions.checkState(z, sb.toString());
        ByteBuffer allocate = ByteBuffer.allocate(37);
        if (childNumber.isHardened()) {
            allocate.put(deterministicKey.getPrivKeyBytes33());
        } else {
            allocate.put(encoded);
        }
        allocate.putInt(childNumber.mo46017i());
        byte[] hmacSha512 = HDUtils.hmacSha512(deterministicKey.getChainCode(), allocate.array());
        Preconditions.checkState(hmacSha512.length == 64, Integer.valueOf(hmacSha512.length));
        byte[] copyOfRange = Arrays.copyOfRange(hmacSha512, 0, 32);
        byte[] copyOfRange2 = Arrays.copyOfRange(hmacSha512, 32, 64);
        BigInteger bigInteger = new BigInteger(1, copyOfRange);
        assertLessThanN(bigInteger, "Illegal derived key: I_L >= n");
        BigInteger mod = deterministicKey.getPrivKey().add(bigInteger).mod(ECKey.CURVE.getN());
        assertNonZero(mod, "Illegal derived key: derived private key equals 0.");
        return new RawKeyBytes(mod.toByteArray(), copyOfRange2);
    }

    public static RawKeyBytes deriveChildKeyBytesFromPublic(DeterministicKey deterministicKey, ChildNumber childNumber, PublicDeriveMode publicDeriveMode) throws HDDerivationException {
        ECPoint eCPoint;
        Preconditions.checkArgument(!childNumber.isHardened(), "Can't use private derivation with public keys only.");
        byte[] encoded = deterministicKey.getPubKeyPoint().getEncoded(true);
        boolean z = encoded.length == 33;
        StringBuilder sb = new StringBuilder();
        sb.append("Parent pubkey must be 33 bytes, but is ");
        sb.append(encoded.length);
        Preconditions.checkState(z, sb.toString());
        ByteBuffer allocate = ByteBuffer.allocate(37);
        allocate.put(encoded);
        allocate.putInt(childNumber.mo46017i());
        byte[] hmacSha512 = HDUtils.hmacSha512(deterministicKey.getChainCode(), allocate.array());
        Preconditions.checkState(hmacSha512.length == 64, Integer.valueOf(hmacSha512.length));
        byte[] copyOfRange = Arrays.copyOfRange(hmacSha512, 0, 32);
        byte[] copyOfRange2 = Arrays.copyOfRange(hmacSha512, 32, 64);
        BigInteger bigInteger = new BigInteger(1, copyOfRange);
        assertLessThanN(bigInteger, "Illegal derived key: I_L >= n");
        BigInteger n = ECKey.CURVE.getN();
        int i = C34491.$SwitchMap$org$bitcoinj$crypto$HDKeyDerivation$PublicDeriveMode[publicDeriveMode.ordinal()];
        if (i == 1) {
            eCPoint = ECKey.publicPointFromPrivate(bigInteger).add(deterministicKey.getPubKeyPoint());
        } else if (i == 2) {
            eCPoint = ECKey.publicPointFromPrivate(bigInteger.add(RAND_INT).mod(n)).add(ECKey.publicPointFromPrivate(RAND_INT.negate().mod(n))).add(deterministicKey.getPubKeyPoint());
        } else {
            throw new AssertionError();
        }
        assertNonInfinity(eCPoint, "Illegal derived key: derived public key equals infinity.");
        return new RawKeyBytes(eCPoint.getEncoded(true), copyOfRange2);
    }

    private static void assertNonZero(BigInteger bigInteger, String str) {
        if (bigInteger.equals(BigInteger.ZERO)) {
            throw new HDDerivationException(str);
        }
    }

    private static void assertNonInfinity(ECPoint eCPoint, String str) {
        if (eCPoint.equals(ECKey.CURVE.getCurve().getInfinity())) {
            throw new HDDerivationException(str);
        }
    }

    private static void assertLessThanN(BigInteger bigInteger, String str) {
        if (bigInteger.compareTo(ECKey.CURVE.getN()) > 0) {
            throw new HDDerivationException(str);
        }
    }
}
