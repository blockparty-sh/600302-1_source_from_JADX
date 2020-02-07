package org.bitcoinj.core;

import com.facebook.stetho.common.Utf8Charset;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.MoreObjects;
import com.google.common.base.MoreObjects.ToStringHelper;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Ints;
import com.google.common.primitives.UnsignedBytes;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.security.SecureRandom;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Comparator;
import javax.annotation.Nullable;
import org.bitcoin.NativeSecp256k1;
import org.bitcoin.NativeSecp256k1Util.AssertFailException;
import org.bitcoin.Secp256k1Context;
import org.bitcoinj.crypto.EncryptableItem;
import org.bitcoinj.crypto.EncryptedData;
import org.bitcoinj.crypto.KeyCrypter;
import org.bitcoinj.crypto.KeyCrypterException;
import org.bitcoinj.crypto.LazyECPoint;
import org.bitcoinj.crypto.LinuxSecureRandom;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.wallet.Protos.C3527Wallet.EncryptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.DERBitString;
import org.spongycastle.asn1.DEROctetString;
import org.spongycastle.asn1.DERSequenceGenerator;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.p022x9.X9ECParameters;
import org.spongycastle.asn1.p022x9.X9IntegerConverter;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.digests.SHA256Digest;
import org.spongycastle.crypto.generators.ECKeyPairGenerator;
import org.spongycastle.crypto.p023ec.CustomNamedCurves;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyGenerationParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.signers.ECDSASigner;
import org.spongycastle.crypto.signers.HMacDSAKCalculator;
import org.spongycastle.math.p025ec.ECAlgorithms;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.math.p025ec.FixedPointCombMultiplier;
import org.spongycastle.math.p025ec.FixedPointUtil;
import org.spongycastle.math.p025ec.custom.sec.SecP256K1Curve;
import org.spongycastle.util.encoders.Base64;

public class ECKey implements EncryptableItem {
    public static final Comparator<ECKey> AGE_COMPARATOR = new Comparator<ECKey>() {
        public int compare(ECKey eCKey, ECKey eCKey2) {
            if (eCKey.creationTimeSeconds == eCKey2.creationTimeSeconds) {
                return 0;
            }
            return eCKey.creationTimeSeconds > eCKey2.creationTimeSeconds ? 1 : -1;
        }
    };
    public static final ECDomainParameters CURVE = new ECDomainParameters(CURVE_PARAMS.getCurve(), CURVE_PARAMS.getG(), CURVE_PARAMS.getN(), CURVE_PARAMS.getH());
    private static final X9ECParameters CURVE_PARAMS = CustomNamedCurves.getByName("secp256k1");
    @VisibleForTesting
    public static boolean FAKE_SIGNATURES = false;
    public static final BigInteger HALF_CURVE_ORDER = CURVE_PARAMS.getN().shiftRight(1);
    public static final Comparator<ECKey> PUBKEY_COMPARATOR = new Comparator<ECKey>() {
        private Comparator<byte[]> comparator = UnsignedBytes.lexicographicalComparator();

        public int compare(ECKey eCKey, ECKey eCKey2) {
            return this.comparator.compare(eCKey.getPubKey(), eCKey2.getPubKey());
        }
    };
    private static final Logger log = LoggerFactory.getLogger(ECKey.class);
    private static final SecureRandom secureRandom = new SecureRandom();
    protected long creationTimeSeconds;
    protected EncryptedData encryptedPrivateKey;
    protected KeyCrypter keyCrypter;
    protected final BigInteger priv;
    protected final LazyECPoint pub;
    private byte[] pubKeyHash;

    public static class ECDSASignature {

        /* renamed from: r */
        public final BigInteger f796r;

        /* renamed from: s */
        public final BigInteger f797s;

        public ECDSASignature(BigInteger bigInteger, BigInteger bigInteger2) {
            this.f796r = bigInteger;
            this.f797s = bigInteger2;
        }

        public boolean isCanonical() {
            return this.f797s.compareTo(ECKey.HALF_CURVE_ORDER) <= 0;
        }

        public ECDSASignature toCanonicalised() {
            return !isCanonical() ? new ECDSASignature(this.f796r, ECKey.CURVE.getN().subtract(this.f797s)) : this;
        }

        public byte[] encodeToDER() {
            try {
                return derByteStream().toByteArray();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:31:0x004d A[SYNTHETIC, Splitter:B:31:0x004d] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static org.bitcoinj.core.ECKey.ECDSASignature decodeFromDER(byte[] r3) {
            /*
                r0 = 0
                org.spongycastle.asn1.ASN1InputStream r1 = new org.spongycastle.asn1.ASN1InputStream     // Catch:{ IOException -> 0x0044 }
                r1.<init>(r3)     // Catch:{ IOException -> 0x0044 }
                org.spongycastle.asn1.ASN1Primitive r3 = r1.readObject()     // Catch:{ IOException -> 0x003e, all -> 0x003c }
                org.spongycastle.asn1.DLSequence r3 = (org.spongycastle.asn1.DLSequence) r3     // Catch:{ IOException -> 0x003e, all -> 0x003c }
                if (r3 == 0) goto L_0x0034
                r0 = 0
                org.spongycastle.asn1.ASN1Encodable r0 = r3.getObjectAt(r0)     // Catch:{ ClassCastException -> 0x002d }
                org.spongycastle.asn1.ASN1Integer r0 = (org.spongycastle.asn1.ASN1Integer) r0     // Catch:{ ClassCastException -> 0x002d }
                r2 = 1
                org.spongycastle.asn1.ASN1Encodable r3 = r3.getObjectAt(r2)     // Catch:{ ClassCastException -> 0x002d }
                org.spongycastle.asn1.ASN1Integer r3 = (org.spongycastle.asn1.ASN1Integer) r3     // Catch:{ ClassCastException -> 0x002d }
                org.bitcoinj.core.ECKey$ECDSASignature r2 = new org.bitcoinj.core.ECKey$ECDSASignature     // Catch:{ IOException -> 0x003e, all -> 0x003c }
                java.math.BigInteger r0 = r0.getPositiveValue()     // Catch:{ IOException -> 0x003e, all -> 0x003c }
                java.math.BigInteger r3 = r3.getPositiveValue()     // Catch:{ IOException -> 0x003e, all -> 0x003c }
                r2.<init>(r0, r3)     // Catch:{ IOException -> 0x003e, all -> 0x003c }
                r1.close()     // Catch:{ IOException -> 0x002c }
            L_0x002c:
                return r2
            L_0x002d:
                r3 = move-exception
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IOException -> 0x003e, all -> 0x003c }
                r0.<init>(r3)     // Catch:{ IOException -> 0x003e, all -> 0x003c }
                throw r0     // Catch:{ IOException -> 0x003e, all -> 0x003c }
            L_0x0034:
                java.lang.RuntimeException r3 = new java.lang.RuntimeException     // Catch:{ IOException -> 0x003e, all -> 0x003c }
                java.lang.String r0 = "Reached past end of ASN.1 stream."
                r3.<init>(r0)     // Catch:{ IOException -> 0x003e, all -> 0x003c }
                throw r3     // Catch:{ IOException -> 0x003e, all -> 0x003c }
            L_0x003c:
                r3 = move-exception
                goto L_0x004b
            L_0x003e:
                r3 = move-exception
                r0 = r1
                goto L_0x0045
            L_0x0041:
                r3 = move-exception
                r1 = r0
                goto L_0x004b
            L_0x0044:
                r3 = move-exception
            L_0x0045:
                java.lang.RuntimeException r1 = new java.lang.RuntimeException     // Catch:{ all -> 0x0041 }
                r1.<init>(r3)     // Catch:{ all -> 0x0041 }
                throw r1     // Catch:{ all -> 0x0041 }
            L_0x004b:
                if (r1 == 0) goto L_0x0050
                r1.close()     // Catch:{ IOException -> 0x0050 }
            L_0x0050:
                throw r3
            */
            throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.ECKey.ECDSASignature.decodeFromDER(byte[]):org.bitcoinj.core.ECKey$ECDSASignature");
        }

        /* access modifiers changed from: protected */
        public ByteArrayOutputStream derByteStream() throws IOException {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(72);
            DERSequenceGenerator dERSequenceGenerator = new DERSequenceGenerator(byteArrayOutputStream);
            dERSequenceGenerator.addObject(new ASN1Integer(this.f796r));
            dERSequenceGenerator.addObject(new ASN1Integer(this.f797s));
            dERSequenceGenerator.close();
            return byteArrayOutputStream;
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ECDSASignature eCDSASignature = (ECDSASignature) obj;
            if (!this.f796r.equals(eCDSASignature.f796r) || !this.f797s.equals(eCDSASignature.f797s)) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return Objects.hashCode(this.f796r, this.f797s);
        }
    }

    public static class KeyIsEncryptedException extends MissingPrivateKeyException {
    }

    public static class MissingPrivateKeyException extends RuntimeException {
    }

    static {
        if (C3447Utils.isAndroidRuntime()) {
            new LinuxSecureRandom();
        }
        FixedPointUtil.precompute(CURVE_PARAMS.getG(), 12);
    }

    public ECKey() {
        this(secureRandom);
    }

    public ECKey(SecureRandom secureRandom2) {
        ECKeyPairGenerator eCKeyPairGenerator = new ECKeyPairGenerator();
        eCKeyPairGenerator.init(new ECKeyGenerationParameters(CURVE, secureRandom2));
        AsymmetricCipherKeyPair generateKeyPair = eCKeyPairGenerator.generateKeyPair();
        ECPrivateKeyParameters eCPrivateKeyParameters = (ECPrivateKeyParameters) generateKeyPair.getPrivate();
        ECPublicKeyParameters eCPublicKeyParameters = (ECPublicKeyParameters) generateKeyPair.getPublic();
        this.priv = eCPrivateKeyParameters.getD();
        this.pub = new LazyECPoint(CURVE.getCurve(), eCPublicKeyParameters.getQ().getEncoded(true));
        this.creationTimeSeconds = C3447Utils.currentTimeSeconds();
    }

    protected ECKey(@Nullable BigInteger bigInteger, ECPoint eCPoint) {
        if (bigInteger != null) {
            Preconditions.checkArgument(!bigInteger.equals(BigInteger.ZERO));
            Preconditions.checkArgument(!bigInteger.equals(BigInteger.ONE));
        }
        this.priv = bigInteger;
        this.pub = new LazyECPoint((ECPoint) Preconditions.checkNotNull(eCPoint));
    }

    protected ECKey(@Nullable BigInteger bigInteger, LazyECPoint lazyECPoint) {
        this.priv = bigInteger;
        this.pub = (LazyECPoint) Preconditions.checkNotNull(lazyECPoint);
    }

    public static ECPoint compressPoint(ECPoint eCPoint) {
        return getPointWithCompression(eCPoint, true);
    }

    public static LazyECPoint compressPoint(LazyECPoint lazyECPoint) {
        return lazyECPoint.isCompressed() ? lazyECPoint : new LazyECPoint(compressPoint(lazyECPoint.get()));
    }

    public static ECPoint decompressPoint(ECPoint eCPoint) {
        return getPointWithCompression(eCPoint, false);
    }

    public static LazyECPoint decompressPoint(LazyECPoint lazyECPoint) {
        return !lazyECPoint.isCompressed() ? lazyECPoint : new LazyECPoint(decompressPoint(lazyECPoint.get()));
    }

    private static ECPoint getPointWithCompression(ECPoint eCPoint, boolean z) {
        if (eCPoint.isCompressed() == z) {
            return eCPoint;
        }
        ECPoint normalize = eCPoint.normalize();
        return CURVE.getCurve().createPoint(normalize.getAffineXCoord().toBigInteger(), normalize.getAffineYCoord().toBigInteger(), z);
    }

    public static ECKey fromASN1(byte[] bArr) {
        return extractKeyFromASN1(bArr);
    }

    public static ECKey fromPrivate(BigInteger bigInteger) {
        return fromPrivate(bigInteger, true);
    }

    public static ECKey fromPrivate(BigInteger bigInteger, boolean z) {
        return new ECKey(bigInteger, getPointWithCompression(publicPointFromPrivate(bigInteger), z));
    }

    public static ECKey fromPrivate(byte[] bArr) {
        return fromPrivate(new BigInteger(1, bArr));
    }

    public static ECKey fromPrivate(byte[] bArr, boolean z) {
        return fromPrivate(new BigInteger(1, bArr), z);
    }

    public static ECKey fromPrivateAndPrecalculatedPublic(BigInteger bigInteger, ECPoint eCPoint) {
        return new ECKey(bigInteger, eCPoint);
    }

    public static ECKey fromPrivateAndPrecalculatedPublic(byte[] bArr, byte[] bArr2) {
        Preconditions.checkNotNull(bArr);
        Preconditions.checkNotNull(bArr2);
        return new ECKey(new BigInteger(1, bArr), CURVE.getCurve().decodePoint(bArr2));
    }

    public static ECKey fromPublicOnly(ECPoint eCPoint) {
        return new ECKey((BigInteger) null, eCPoint);
    }

    public static ECKey fromPublicOnly(byte[] bArr) {
        return new ECKey((BigInteger) null, CURVE.getCurve().decodePoint(bArr));
    }

    public ECKey decompress() {
        if (!this.pub.isCompressed()) {
            return this;
        }
        return new ECKey(this.priv, decompressPoint(this.pub.get()));
    }

    @Deprecated
    public ECKey(@Nullable byte[] bArr, @Nullable byte[] bArr2) {
        this(bArr == null ? null : new BigInteger(1, bArr), bArr2);
    }

    @Deprecated
    public ECKey(EncryptedData encryptedData, byte[] bArr, KeyCrypter keyCrypter2) {
        this((byte[]) null, bArr);
        this.keyCrypter = (KeyCrypter) Preconditions.checkNotNull(keyCrypter2);
        this.encryptedPrivateKey = encryptedData;
    }

    public static ECKey fromEncrypted(EncryptedData encryptedData, KeyCrypter keyCrypter2, byte[] bArr) {
        ECKey fromPublicOnly = fromPublicOnly(bArr);
        fromPublicOnly.encryptedPrivateKey = (EncryptedData) Preconditions.checkNotNull(encryptedData);
        fromPublicOnly.keyCrypter = (KeyCrypter) Preconditions.checkNotNull(keyCrypter2);
        return fromPublicOnly;
    }

    @Deprecated
    public ECKey(@Nullable BigInteger bigInteger, @Nullable byte[] bArr, boolean z) {
        if (bigInteger == null && bArr == null) {
            throw new IllegalArgumentException("ECKey requires at least private or public key");
        }
        this.priv = bigInteger;
        if (bArr == null) {
            this.pub = new LazyECPoint(getPointWithCompression(publicPointFromPrivate(bigInteger), z));
        } else {
            this.pub = new LazyECPoint(CURVE.getCurve(), bArr);
        }
    }

    @Deprecated
    private ECKey(@Nullable BigInteger bigInteger, @Nullable byte[] bArr) {
        this(bigInteger, bArr, false);
    }

    public boolean isPubKeyOnly() {
        return this.priv == null;
    }

    public boolean hasPrivKey() {
        return this.priv != null;
    }

    public boolean isWatching() {
        return isPubKeyOnly() && !isEncrypted();
    }

    public byte[] toASN1() {
        try {
            byte[] privKeyBytes = getPrivKeyBytes();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(400);
            DERSequenceGenerator dERSequenceGenerator = new DERSequenceGenerator(byteArrayOutputStream);
            dERSequenceGenerator.addObject(new ASN1Integer(1));
            dERSequenceGenerator.addObject(new DEROctetString(privKeyBytes));
            dERSequenceGenerator.addObject(new DERTaggedObject(0, CURVE_PARAMS.toASN1Primitive()));
            dERSequenceGenerator.addObject(new DERTaggedObject(1, new DERBitString(getPubKey())));
            dERSequenceGenerator.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] publicKeyFromPrivate(BigInteger bigInteger, boolean z) {
        return publicPointFromPrivate(bigInteger).getEncoded(z);
    }

    public static ECPoint publicPointFromPrivate(BigInteger bigInteger) {
        if (bigInteger.bitLength() > CURVE.getN().bitLength()) {
            bigInteger = bigInteger.mod(CURVE.getN());
        }
        return new FixedPointCombMultiplier().multiply(CURVE.getG(), bigInteger);
    }

    public byte[] getPubKeyHash() {
        if (this.pubKeyHash == null) {
            this.pubKeyHash = C3447Utils.sha256hash160(this.pub.getEncoded());
        }
        return this.pubKeyHash;
    }

    public byte[] getPubKey() {
        return this.pub.getEncoded();
    }

    public ECPoint getPubKeyPoint() {
        return this.pub.get();
    }

    public BigInteger getPrivKey() {
        BigInteger bigInteger = this.priv;
        if (bigInteger != null) {
            return bigInteger;
        }
        throw new MissingPrivateKeyException();
    }

    public boolean isCompressed() {
        return this.pub.isCompressed();
    }

    public Address toAddress(NetworkParameters networkParameters) {
        return new Address(networkParameters, getPubKeyHash());
    }

    public ECDSASignature sign(Sha256Hash sha256Hash) throws KeyCrypterException {
        return sign(sha256Hash, null);
    }

    public ECDSASignature sign(Sha256Hash sha256Hash, @Nullable KeyParameter keyParameter) throws KeyCrypterException {
        if (getKeyCrypter() == null) {
            BigInteger bigInteger = this.priv;
            if (bigInteger != null) {
                return doSign(sha256Hash, bigInteger);
            }
            throw new MissingPrivateKeyException();
        } else if (keyParameter != null) {
            return decrypt(keyParameter).sign(sha256Hash);
        } else {
            throw new KeyIsEncryptedException();
        }
    }

    /* access modifiers changed from: protected */
    public ECDSASignature doSign(Sha256Hash sha256Hash, BigInteger bigInteger) {
        if (Secp256k1Context.isEnabled()) {
            try {
                return ECDSASignature.decodeFromDER(NativeSecp256k1.sign(sha256Hash.getBytes(), C3447Utils.bigIntegerToBytes(bigInteger, 32)));
            } catch (AssertFailException e) {
                log.error("Caught AssertFailException inside secp256k1", (Throwable) e);
                throw new RuntimeException(e);
            }
        } else if (FAKE_SIGNATURES) {
            return TransactionSignature.dummy();
        } else {
            Preconditions.checkNotNull(bigInteger);
            ECDSASigner eCDSASigner = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()));
            eCDSASigner.init(true, new ECPrivateKeyParameters(bigInteger, CURVE));
            BigInteger[] generateSignature = eCDSASigner.generateSignature(sha256Hash.getBytes());
            return new ECDSASignature(generateSignature[0], generateSignature[1]).toCanonicalised();
        }
    }

    public static boolean verify(byte[] bArr, ECDSASignature eCDSASignature, byte[] bArr2) {
        if (FAKE_SIGNATURES) {
            return true;
        }
        if (Secp256k1Context.isEnabled()) {
            try {
                return NativeSecp256k1.verify(bArr, eCDSASignature.encodeToDER(), bArr2);
            } catch (AssertFailException e) {
                log.error("Caught AssertFailException inside secp256k1", (Throwable) e);
                return false;
            }
        } else {
            ECDSASigner eCDSASigner = new ECDSASigner();
            eCDSASigner.init(false, new ECPublicKeyParameters(CURVE.getCurve().decodePoint(bArr2), CURVE));
            try {
                return eCDSASigner.verifySignature(bArr, eCDSASignature.f796r, eCDSASignature.f797s);
            } catch (NullPointerException e2) {
                log.error("Caught NPE inside bouncy castle", (Throwable) e2);
                return false;
            }
        }
    }

    public static boolean verify(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (!Secp256k1Context.isEnabled()) {
            return verify(bArr, ECDSASignature.decodeFromDER(bArr2), bArr3);
        }
        try {
            return NativeSecp256k1.verify(bArr, bArr2, bArr3);
        } catch (AssertFailException e) {
            log.error("Caught AssertFailException inside secp256k1", (Throwable) e);
            return false;
        }
    }

    public boolean verify(byte[] bArr, byte[] bArr2) {
        return verify(bArr, bArr2, getPubKey());
    }

    public boolean verify(Sha256Hash sha256Hash, ECDSASignature eCDSASignature) {
        return verify(sha256Hash.getBytes(), eCDSASignature, getPubKey());
    }

    public void verifyOrThrow(byte[] bArr, byte[] bArr2) throws SignatureException {
        if (!verify(bArr, bArr2)) {
            throw new SignatureException();
        }
    }

    public void verifyOrThrow(Sha256Hash sha256Hash, ECDSASignature eCDSASignature) throws SignatureException {
        if (!verify(sha256Hash.getBytes(), eCDSASignature, getPubKey())) {
            throw new SignatureException();
        }
    }

    public static boolean isPubKeyCanonical(byte[] bArr) {
        if (bArr.length < 33) {
            return false;
        }
        if (bArr[0] == 4) {
            if (bArr.length != 65) {
                return false;
            }
        } else if (!((bArr[0] == 2 || bArr[0] == 3) && bArr.length == 33)) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0097 A[Catch:{ IOException -> 0x00b1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a8 A[Catch:{ IOException -> 0x00b1 }, RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a9 A[Catch:{ IOException -> 0x00b1 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.bitcoinj.core.ECKey extractKeyFromASN1(byte[] r7) {
        /*
            org.spongycastle.asn1.ASN1InputStream r0 = new org.spongycastle.asn1.ASN1InputStream     // Catch:{ IOException -> 0x00b1 }
            r0.<init>(r7)     // Catch:{ IOException -> 0x00b1 }
            org.spongycastle.asn1.ASN1Primitive r7 = r0.readObject()     // Catch:{ IOException -> 0x00b1 }
            org.spongycastle.asn1.DLSequence r7 = (org.spongycastle.asn1.DLSequence) r7     // Catch:{ IOException -> 0x00b1 }
            org.spongycastle.asn1.ASN1Primitive r1 = r0.readObject()     // Catch:{ IOException -> 0x00b1 }
            r2 = 0
            r3 = 1
            if (r1 != 0) goto L_0x0015
            r1 = 1
            goto L_0x0016
        L_0x0015:
            r1 = 0
        L_0x0016:
            java.lang.String r4 = "Input contains extra bytes"
            com.google.common.base.Preconditions.checkArgument(r1, r4)     // Catch:{ IOException -> 0x00b1 }
            r0.close()     // Catch:{ IOException -> 0x00b1 }
            int r0 = r7.size()     // Catch:{ IOException -> 0x00b1 }
            r1 = 4
            if (r0 != r1) goto L_0x0027
            r0 = 1
            goto L_0x0028
        L_0x0027:
            r0 = 0
        L_0x0028:
            java.lang.String r4 = "Input does not appear to be an ASN.1 OpenSSL EC private key"
            com.google.common.base.Preconditions.checkArgument(r0, r4)     // Catch:{ IOException -> 0x00b1 }
            org.spongycastle.asn1.ASN1Encodable r0 = r7.getObjectAt(r2)     // Catch:{ IOException -> 0x00b1 }
            org.spongycastle.asn1.ASN1Integer r0 = (org.spongycastle.asn1.ASN1Integer) r0     // Catch:{ IOException -> 0x00b1 }
            java.math.BigInteger r0 = r0.getValue()     // Catch:{ IOException -> 0x00b1 }
            java.math.BigInteger r4 = java.math.BigInteger.ONE     // Catch:{ IOException -> 0x00b1 }
            boolean r0 = r0.equals(r4)     // Catch:{ IOException -> 0x00b1 }
            java.lang.String r4 = "Input is of wrong version"
            com.google.common.base.Preconditions.checkArgument(r0, r4)     // Catch:{ IOException -> 0x00b1 }
            org.spongycastle.asn1.ASN1Encodable r0 = r7.getObjectAt(r3)     // Catch:{ IOException -> 0x00b1 }
            org.spongycastle.asn1.ASN1OctetString r0 = (org.spongycastle.asn1.ASN1OctetString) r0     // Catch:{ IOException -> 0x00b1 }
            byte[] r0 = r0.getOctets()     // Catch:{ IOException -> 0x00b1 }
            java.math.BigInteger r4 = new java.math.BigInteger     // Catch:{ IOException -> 0x00b1 }
            r4.<init>(r3, r0)     // Catch:{ IOException -> 0x00b1 }
            r0 = 3
            org.spongycastle.asn1.ASN1Encodable r7 = r7.getObjectAt(r0)     // Catch:{ IOException -> 0x00b1 }
            org.spongycastle.asn1.ASN1TaggedObject r7 = (org.spongycastle.asn1.ASN1TaggedObject) r7     // Catch:{ IOException -> 0x00b1 }
            int r0 = r7.getTagNo()     // Catch:{ IOException -> 0x00b1 }
            if (r0 != r3) goto L_0x0060
            r0 = 1
            goto L_0x0061
        L_0x0060:
            r0 = 0
        L_0x0061:
            java.lang.String r5 = "Input has 'publicKey' with bad tag number"
            com.google.common.base.Preconditions.checkArgument(r0, r5)     // Catch:{ IOException -> 0x00b1 }
            org.spongycastle.asn1.ASN1Primitive r7 = r7.getObject()     // Catch:{ IOException -> 0x00b1 }
            org.spongycastle.asn1.DERBitString r7 = (org.spongycastle.asn1.DERBitString) r7     // Catch:{ IOException -> 0x00b1 }
            byte[] r7 = r7.getBytes()     // Catch:{ IOException -> 0x00b1 }
            int r0 = r7.length     // Catch:{ IOException -> 0x00b1 }
            r5 = 33
            if (r0 == r5) goto L_0x007d
            int r0 = r7.length     // Catch:{ IOException -> 0x00b1 }
            r6 = 65
            if (r0 != r6) goto L_0x007b
            goto L_0x007d
        L_0x007b:
            r0 = 0
            goto L_0x007e
        L_0x007d:
            r0 = 1
        L_0x007e:
            java.lang.String r6 = "Input has 'publicKey' with invalid length"
            com.google.common.base.Preconditions.checkArgument(r0, r6)     // Catch:{ IOException -> 0x00b1 }
            byte r0 = r7[r2]     // Catch:{ IOException -> 0x00b1 }
            r0 = r0 & 255(0xff, float:3.57E-43)
            r6 = 2
            if (r0 < r6) goto L_0x008e
            if (r0 > r1) goto L_0x008e
            r0 = 1
            goto L_0x008f
        L_0x008e:
            r0 = 0
        L_0x008f:
            java.lang.String r1 = "Input has 'publicKey' with invalid encoding"
            com.google.common.base.Preconditions.checkArgument(r0, r1)     // Catch:{ IOException -> 0x00b1 }
            int r0 = r7.length     // Catch:{ IOException -> 0x00b1 }
            if (r0 != r5) goto L_0x0098
            r2 = 1
        L_0x0098:
            org.bitcoinj.core.ECKey r0 = new org.bitcoinj.core.ECKey     // Catch:{ IOException -> 0x00b1 }
            r1 = 0
            r0.<init>(r4, r1, r2)     // Catch:{ IOException -> 0x00b1 }
            byte[] r1 = r0.getPubKey()     // Catch:{ IOException -> 0x00b1 }
            boolean r7 = java.util.Arrays.equals(r1, r7)     // Catch:{ IOException -> 0x00b1 }
            if (r7 == 0) goto L_0x00a9
            return r0
        L_0x00a9:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException     // Catch:{ IOException -> 0x00b1 }
            java.lang.String r0 = "Public key in ASN.1 structure does not match private key."
            r7.<init>(r0)     // Catch:{ IOException -> 0x00b1 }
            throw r7     // Catch:{ IOException -> 0x00b1 }
        L_0x00b1:
            r7 = move-exception
            java.lang.RuntimeException r0 = new java.lang.RuntimeException
            r0.<init>(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.core.ECKey.extractKeyFromASN1(byte[]):org.bitcoinj.core.ECKey");
    }

    public String signMessage(String str) throws KeyCrypterException {
        return signMessage(str, null);
    }

    public String signMessage(String str, @Nullable KeyParameter keyParameter) throws KeyCrypterException {
        int i;
        Sha256Hash twiceOf = Sha256Hash.twiceOf(C3447Utils.formatMessageForSigning(str));
        ECDSASignature sign = sign(twiceOf, keyParameter);
        int i2 = 0;
        while (true) {
            i = 4;
            if (i2 >= 4) {
                i2 = -1;
                break;
            }
            ECKey recoverFromSignature = recoverFromSignature(i2, sign, twiceOf, isCompressed());
            if (recoverFromSignature != null && recoverFromSignature.pub.equals((Object) this.pub)) {
                break;
            }
            i2++;
        }
        if (i2 != -1) {
            int i3 = i2 + 27;
            if (!isCompressed()) {
                i = 0;
            }
            byte[] bArr = new byte[65];
            bArr[0] = (byte) (i3 + i);
            System.arraycopy(C3447Utils.bigIntegerToBytes(sign.f796r, 32), 0, bArr, 1, 32);
            System.arraycopy(C3447Utils.bigIntegerToBytes(sign.f797s, 32), 0, bArr, 33, 32);
            return new String(Base64.encode(bArr), Charset.forName(Utf8Charset.NAME));
        }
        throw new RuntimeException("Could not construct a recoverable key. This should never happen.");
    }

    public static ECKey signedMessageToKey(String str, String str2) throws SignatureException {
        try {
            byte[] decode = Base64.decode(str2);
            if (decode.length >= 65) {
                boolean z = false;
                int i = decode[0] & 255;
                if (i < 27 || i > 34) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Header byte out of range: ");
                    sb.append(i);
                    throw new SignatureException(sb.toString());
                }
                ECDSASignature eCDSASignature = new ECDSASignature(new BigInteger(1, Arrays.copyOfRange(decode, 1, 33)), new BigInteger(1, Arrays.copyOfRange(decode, 33, 65)));
                Sha256Hash twiceOf = Sha256Hash.twiceOf(C3447Utils.formatMessageForSigning(str));
                if (i >= 31) {
                    i -= 4;
                    z = true;
                }
                ECKey recoverFromSignature = recoverFromSignature(i - 27, eCDSASignature, twiceOf, z);
                if (recoverFromSignature != null) {
                    return recoverFromSignature;
                }
                throw new SignatureException("Could not recover public key from signature");
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Signature truncated, expected 65 bytes and got ");
            sb2.append(decode.length);
            throw new SignatureException(sb2.toString());
        } catch (RuntimeException e) {
            throw new SignatureException("Could not decode base64", e);
        }
    }

    public void verifyMessage(String str, String str2) throws SignatureException {
        if (!signedMessageToKey(str, str2).pub.equals((Object) this.pub)) {
            throw new SignatureException("Signature did not match for message");
        }
    }

    @Nullable
    public static ECKey recoverFromSignature(int i, ECDSASignature eCDSASignature, Sha256Hash sha256Hash, boolean z) {
        boolean z2 = false;
        Preconditions.checkArgument(i >= 0, "recId must be positive");
        Preconditions.checkArgument(eCDSASignature.f796r.signum() >= 0, "r must be positive");
        Preconditions.checkArgument(eCDSASignature.f797s.signum() >= 0, "s must be positive");
        Preconditions.checkNotNull(sha256Hash);
        BigInteger n = CURVE.getN();
        BigInteger add = eCDSASignature.f796r.add(BigInteger.valueOf(((long) i) / 2).multiply(n));
        if (add.compareTo(SecP256K1Curve.f1394q) >= 0) {
            return null;
        }
        if ((i & 1) == 1) {
            z2 = true;
        }
        ECPoint decompressKey = decompressKey(add, z2);
        if (!decompressKey.multiply(n).isInfinity()) {
            return null;
        }
        BigInteger mod = BigInteger.ZERO.subtract(sha256Hash.toBigInteger()).mod(n);
        BigInteger modInverse = eCDSASignature.f796r.modInverse(n);
        return fromPublicOnly(ECAlgorithms.sumOfTwoMultiplies(CURVE.getG(), modInverse.multiply(mod).mod(n), decompressKey, modInverse.multiply(eCDSASignature.f797s).mod(n)).getEncoded(z));
    }

    private static ECPoint decompressKey(BigInteger bigInteger, boolean z) {
        X9IntegerConverter x9IntegerConverter = new X9IntegerConverter();
        byte[] integerToBytes = x9IntegerConverter.integerToBytes(bigInteger, x9IntegerConverter.getByteLength(CURVE.getCurve()) + 1);
        integerToBytes[0] = (byte) (z ? 3 : 2);
        return CURVE.getCurve().decodePoint(integerToBytes);
    }

    public byte[] getPrivKeyBytes() {
        return C3447Utils.bigIntegerToBytes(getPrivKey(), 32);
    }

    public DumpedPrivateKey getPrivateKeyEncoded(NetworkParameters networkParameters) {
        return new DumpedPrivateKey(networkParameters, getPrivKeyBytes(), isCompressed());
    }

    public long getCreationTimeSeconds() {
        return this.creationTimeSeconds;
    }

    public void setCreationTimeSeconds(long j) {
        if (j >= 0) {
            this.creationTimeSeconds = j;
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Cannot set creation time to negative value: ");
        sb.append(j);
        throw new IllegalArgumentException(sb.toString());
    }

    public ECKey encrypt(KeyCrypter keyCrypter2, KeyParameter keyParameter) throws KeyCrypterException {
        Preconditions.checkNotNull(keyCrypter2);
        ECKey fromEncrypted = fromEncrypted(keyCrypter2.encrypt(getPrivKeyBytes(), keyParameter), keyCrypter2, getPubKey());
        fromEncrypted.setCreationTimeSeconds(this.creationTimeSeconds);
        return fromEncrypted;
    }

    public ECKey decrypt(KeyCrypter keyCrypter2, KeyParameter keyParameter) throws KeyCrypterException {
        Preconditions.checkNotNull(keyCrypter2);
        KeyCrypter keyCrypter3 = this.keyCrypter;
        if (keyCrypter3 == null || keyCrypter3.equals(keyCrypter2)) {
            Preconditions.checkState(this.encryptedPrivateKey != null, "This key is not encrypted");
            ECKey fromPrivate = fromPrivate(keyCrypter2.decrypt(this.encryptedPrivateKey, keyParameter));
            if (!isCompressed()) {
                fromPrivate = fromPrivate.decompress();
            }
            if (Arrays.equals(fromPrivate.getPubKey(), getPubKey())) {
                fromPrivate.setCreationTimeSeconds(this.creationTimeSeconds);
                return fromPrivate;
            }
            throw new KeyCrypterException("Provided AES key is wrong");
        }
        throw new KeyCrypterException("The keyCrypter being used to decrypt the key is different to the one that was used to encrypt it");
    }

    public ECKey decrypt(KeyParameter keyParameter) throws KeyCrypterException {
        KeyCrypter keyCrypter2 = getKeyCrypter();
        if (keyCrypter2 != null) {
            return decrypt(keyCrypter2, keyParameter);
        }
        throw new KeyCrypterException("No key crypter available");
    }

    public ECKey maybeDecrypt(@Nullable KeyParameter keyParameter) throws KeyCrypterException {
        return (!isEncrypted() || keyParameter == null) ? this : decrypt(keyParameter);
    }

    public static boolean encryptionIsReversible(ECKey eCKey, ECKey eCKey2, KeyCrypter keyCrypter2, KeyParameter keyParameter) {
        try {
            if (Arrays.equals(eCKey.getPrivKeyBytes(), eCKey2.decrypt(keyCrypter2, keyParameter).getPrivKeyBytes())) {
                return true;
            }
            log.error("The check that encryption could be reversed failed for {}", (Object) eCKey);
            return false;
        } catch (KeyCrypterException e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public boolean isEncrypted() {
        if (this.keyCrypter != null) {
            EncryptedData encryptedData = this.encryptedPrivateKey;
            if (encryptedData != null && encryptedData.encryptedBytes.length > 0) {
                return true;
            }
        }
        return false;
    }

    @Nullable
    public EncryptionType getEncryptionType() {
        KeyCrypter keyCrypter2 = this.keyCrypter;
        return keyCrypter2 != null ? keyCrypter2.getUnderstoodEncryptionType() : EncryptionType.UNENCRYPTED;
    }

    @Nullable
    public byte[] getSecretBytes() {
        if (hasPrivKey()) {
            return getPrivKeyBytes();
        }
        return null;
    }

    @Nullable
    public EncryptedData getEncryptedData() {
        return getEncryptedPrivateKey();
    }

    @Nullable
    public EncryptedData getEncryptedPrivateKey() {
        return this.encryptedPrivateKey;
    }

    @Nullable
    public KeyCrypter getKeyCrypter() {
        return this.keyCrypter;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ECKey)) {
            return false;
        }
        ECKey eCKey = (ECKey) obj;
        if (!Objects.equal(this.priv, eCKey.priv) || !Objects.equal(this.pub, eCKey.pub) || !Objects.equal(Long.valueOf(this.creationTimeSeconds), Long.valueOf(eCKey.creationTimeSeconds)) || !Objects.equal(this.keyCrypter, eCKey.keyCrypter) || !Objects.equal(this.encryptedPrivateKey, eCKey.encryptedPrivateKey)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        byte[] pubKey = getPubKey();
        return Ints.fromBytes(pubKey[0], pubKey[1], pubKey[2], pubKey[3]);
    }

    public String toString() {
        return toString(false, null);
    }

    public String toStringWithPrivate(NetworkParameters networkParameters) {
        return toString(true, networkParameters);
    }

    public String getPrivateKeyAsHex() {
        return C3447Utils.HEX.encode(getPrivKeyBytes());
    }

    public String getPublicKeyAsHex() {
        return C3447Utils.HEX.encode(this.pub.getEncoded());
    }

    public String getPrivateKeyAsWiF(NetworkParameters networkParameters) {
        return getPrivateKeyEncoded(networkParameters).toString();
    }

    private String toString(boolean z, NetworkParameters networkParameters) {
        String str;
        ToStringHelper omitNullValues = MoreObjects.toStringHelper((Object) this).omitNullValues();
        omitNullValues.add("pub HEX", (Object) getPublicKeyAsHex());
        if (z) {
            try {
                omitNullValues.add("priv HEX", (Object) getPrivateKeyAsHex());
                omitNullValues.add("priv WIF", (Object) getPrivateKeyAsWiF(networkParameters));
            } catch (IllegalStateException unused) {
            } catch (Exception e) {
                String message = e.getMessage();
                StringBuilder sb = new StringBuilder();
                sb.append(e.getClass().getName());
                if (message != null) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(": ");
                    sb2.append(message);
                    str = sb2.toString();
                } else {
                    str = "";
                }
                sb.append(str);
                omitNullValues.add("priv EXCEPTION", (Object) sb.toString());
            }
        }
        long j = this.creationTimeSeconds;
        if (j > 0) {
            omitNullValues.add("creationTimeSeconds", j);
        }
        omitNullValues.add("keyCrypter", (Object) this.keyCrypter);
        if (z) {
            omitNullValues.add("encryptedPrivateKey", (Object) this.encryptedPrivateKey);
        }
        omitNullValues.add("isEncrypted", isEncrypted());
        omitNullValues.add("isPubKeyOnly", isPubKeyOnly());
        return omitNullValues.toString();
    }

    public void formatKeyWithAddress(boolean z, StringBuilder sb, NetworkParameters networkParameters) {
        Address address = toAddress(networkParameters);
        sb.append("  addr:");
        sb.append(address.toString());
        sb.append("  hash160:");
        sb.append(C3447Utils.HEX.encode(getPubKeyHash()));
        if (this.creationTimeSeconds > 0) {
            sb.append("  creationTimeSeconds:");
            sb.append(this.creationTimeSeconds);
        }
        String str = "\n";
        sb.append(str);
        if (z) {
            sb.append("  ");
            sb.append(toStringWithPrivate(networkParameters));
            sb.append(str);
        }
    }
}
