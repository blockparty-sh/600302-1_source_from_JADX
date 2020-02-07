package org.spongycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Hashtable;
import org.spongycastle.asn1.p022x9.ECNamedCurveTable;
import org.spongycastle.asn1.p022x9.X9ECParameters;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.agreement.ECDHBasicAgreement;
import org.spongycastle.crypto.generators.ECKeyPairGenerator;
import org.spongycastle.crypto.p023ec.CustomNamedCurves;
import org.spongycastle.crypto.params.ECDomainParameters;
import org.spongycastle.crypto.params.ECKeyGenerationParameters;
import org.spongycastle.crypto.params.ECPrivateKeyParameters;
import org.spongycastle.crypto.params.ECPublicKeyParameters;
import org.spongycastle.math.field.PolynomialExtensionField;
import org.spongycastle.math.p025ec.ECAlgorithms;
import org.spongycastle.math.p025ec.ECCurve;
import org.spongycastle.math.p025ec.ECCurve.C3639Fp;
import org.spongycastle.math.p025ec.ECCurve.F2m;
import org.spongycastle.math.p025ec.ECFieldElement;
import org.spongycastle.math.p025ec.ECPoint;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.BigIntegers;
import org.spongycastle.util.Integers;

public class TlsECCUtils {
    public static final Integer EXT_ec_point_formats = Integers.valueOf(11);
    public static final Integer EXT_elliptic_curves = Integers.valueOf(10);
    private static final String[] curveNames = {"sect163k1", "sect163r1", "sect163r2", "sect193r1", "sect193r2", "sect233k1", "sect233r1", "sect239k1", "sect283k1", "sect283r1", "sect409k1", "sect409r1", "sect571k1", "sect571r1", "secp160k1", "secp160r1", "secp160r2", "secp192k1", "secp192r1", "secp224k1", "secp224r1", "secp256k1", "secp256r1", "secp384r1", "secp521r1", "brainpoolP256r1", "brainpoolP384r1", "brainpoolP512r1"};

    public static boolean isECCCipherSuite(int i) {
        switch (i) {
            case CipherSuite.TLS_ECDH_ECDSA_WITH_NULL_SHA /*49153*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_RC4_128_SHA /*49154*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA /*49155*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA /*49156*/:
            case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA /*49157*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA /*49158*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA /*49159*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA /*49160*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA /*49161*/:
            case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA /*49162*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_NULL_SHA /*49163*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_RC4_128_SHA /*49164*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_3DES_EDE_CBC_SHA /*49165*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA /*49166*/:
            case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA /*49167*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_NULL_SHA /*49168*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_RC4_128_SHA /*49169*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_3DES_EDE_CBC_SHA /*49170*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA /*49171*/:
            case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA /*49172*/:
            case CipherSuite.TLS_ECDH_anon_WITH_NULL_SHA /*49173*/:
            case CipherSuite.TLS_ECDH_anon_WITH_RC4_128_SHA /*49174*/:
            case CipherSuite.TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA /*49175*/:
            case CipherSuite.TLS_ECDH_anon_WITH_AES_128_CBC_SHA /*49176*/:
            case CipherSuite.TLS_ECDH_anon_WITH_AES_256_CBC_SHA /*49177*/:
                break;
            default:
                switch (i) {
                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256 /*49187*/:
                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384 /*49188*/:
                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256 /*49189*/:
                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384 /*49190*/:
                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256 /*49191*/:
                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384 /*49192*/:
                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256 /*49193*/:
                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384 /*49194*/:
                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256 /*49195*/:
                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 /*49196*/:
                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256 /*49197*/:
                    case CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_GCM_SHA384 /*49198*/:
                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 /*49199*/:
                    case CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384 /*49200*/:
                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_128_GCM_SHA256 /*49201*/:
                    case CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384 /*49202*/:
                    case CipherSuite.TLS_ECDHE_PSK_WITH_RC4_128_SHA /*49203*/:
                    case CipherSuite.TLS_ECDHE_PSK_WITH_3DES_EDE_CBC_SHA /*49204*/:
                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA /*49205*/:
                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA /*49206*/:
                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA256 /*49207*/:
                    case CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA384 /*49208*/:
                    case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA /*49209*/:
                    case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA256 /*49210*/:
                    case CipherSuite.TLS_ECDHE_PSK_WITH_NULL_SHA384 /*49211*/:
                        break;
                    default:
                        switch (i) {
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /*49266*/:
                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /*49267*/:
                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_CBC_SHA256 /*49268*/:
                            case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_CBC_SHA384 /*49269*/:
                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_CBC_SHA256 /*49270*/:
                            case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_CBC_SHA384 /*49271*/:
                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_CBC_SHA256 /*49272*/:
                            case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_CBC_SHA384 /*49273*/:
                                break;
                            default:
                                switch (i) {
                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /*49286*/:
                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /*49287*/:
                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_128_GCM_SHA256 /*49288*/:
                                    case CipherSuite.TLS_ECDH_ECDSA_WITH_CAMELLIA_256_GCM_SHA384 /*49289*/:
                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_128_GCM_SHA256 /*49290*/:
                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CAMELLIA_256_GCM_SHA384 /*49291*/:
                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_128_GCM_SHA256 /*49292*/:
                                    case CipherSuite.TLS_ECDH_RSA_WITH_CAMELLIA_256_GCM_SHA384 /*49293*/:
                                        break;
                                    default:
                                        switch (i) {
                                            case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_128_CBC_SHA256 /*49306*/:
                                            case CipherSuite.TLS_ECDHE_PSK_WITH_CAMELLIA_256_CBC_SHA384 /*49307*/:
                                                break;
                                            default:
                                                switch (i) {
                                                    case CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256 /*52243*/:
                                                    case CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256 /*52244*/:
                                                        break;
                                                    default:
                                                        switch (i) {
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_ESTREAM_SALSA20_SHA1 /*58386*/:
                                                            case CipherSuite.TLS_ECDHE_RSA_WITH_SALSA20_SHA1 /*58387*/:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_ESTREAM_SALSA20_SHA1 /*58388*/:
                                                            case CipherSuite.TLS_ECDHE_ECDSA_WITH_SALSA20_SHA1 /*58389*/:
                                                                break;
                                                            default:
                                                                switch (i) {
                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_ESTREAM_SALSA20_SHA1 /*58392*/:
                                                                    case CipherSuite.TLS_ECDHE_PSK_WITH_SALSA20_SHA1 /*58393*/:
                                                                        break;
                                                                    default:
                                                                        return false;
                                                                }
                                                        }
                                                }
                                        }
                                }
                        }
                }
        }
        return true;
    }

    public static ECPublicKeyParameters validateECPublicKey(ECPublicKeyParameters eCPublicKeyParameters) throws IOException {
        return eCPublicKeyParameters;
    }

    public static void addSupportedEllipticCurvesExtension(Hashtable hashtable, int[] iArr) throws IOException {
        hashtable.put(EXT_elliptic_curves, createSupportedEllipticCurvesExtension(iArr));
    }

    public static void addSupportedPointFormatsExtension(Hashtable hashtable, short[] sArr) throws IOException {
        hashtable.put(EXT_ec_point_formats, createSupportedPointFormatsExtension(sArr));
    }

    public static int[] getSupportedEllipticCurvesExtension(Hashtable hashtable) throws IOException {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, EXT_elliptic_curves);
        if (extensionData == null) {
            return null;
        }
        return readSupportedEllipticCurvesExtension(extensionData);
    }

    public static short[] getSupportedPointFormatsExtension(Hashtable hashtable) throws IOException {
        byte[] extensionData = TlsUtils.getExtensionData(hashtable, EXT_ec_point_formats);
        if (extensionData == null) {
            return null;
        }
        return readSupportedPointFormatsExtension(extensionData);
    }

    public static byte[] createSupportedEllipticCurvesExtension(int[] iArr) throws IOException {
        if (iArr != null && iArr.length >= 1) {
            return TlsUtils.encodeUint16ArrayWithUint16Length(iArr);
        }
        throw new TlsFatalAlert(80);
    }

    public static byte[] createSupportedPointFormatsExtension(short[] sArr) throws IOException {
        if (sArr == null || !Arrays.contains(sArr, 0)) {
            sArr = Arrays.append(sArr, 0);
        }
        return TlsUtils.encodeUint8ArrayWithUint8Length(sArr);
    }

    public static int[] readSupportedEllipticCurvesExtension(byte[] bArr) throws IOException {
        if (bArr != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            int readUint16 = TlsUtils.readUint16(byteArrayInputStream);
            if (readUint16 < 2 || (readUint16 & 1) != 0) {
                throw new TlsFatalAlert(50);
            }
            int[] readUint16Array = TlsUtils.readUint16Array(readUint16 / 2, byteArrayInputStream);
            TlsProtocol.assertEmpty(byteArrayInputStream);
            return readUint16Array;
        }
        throw new IllegalArgumentException("'extensionData' cannot be null");
    }

    public static short[] readSupportedPointFormatsExtension(byte[] bArr) throws IOException {
        if (bArr != null) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            short readUint8 = TlsUtils.readUint8(byteArrayInputStream);
            if (readUint8 >= 1) {
                short[] readUint8Array = TlsUtils.readUint8Array(readUint8, byteArrayInputStream);
                TlsProtocol.assertEmpty(byteArrayInputStream);
                if (Arrays.contains(readUint8Array, 0)) {
                    return readUint8Array;
                }
                throw new TlsFatalAlert(47);
            }
            throw new TlsFatalAlert(50);
        }
        throw new IllegalArgumentException("'extensionData' cannot be null");
    }

    public static String getNameOfNamedCurve(int i) {
        if (isSupportedNamedCurve(i)) {
            return curveNames[i - 1];
        }
        return null;
    }

    public static ECDomainParameters getParametersForNamedCurve(int i) {
        String nameOfNamedCurve = getNameOfNamedCurve(i);
        if (nameOfNamedCurve == null) {
            return null;
        }
        X9ECParameters byName = CustomNamedCurves.getByName(nameOfNamedCurve);
        if (byName == null) {
            byName = ECNamedCurveTable.getByName(nameOfNamedCurve);
            if (byName == null) {
                return null;
            }
        }
        ECDomainParameters eCDomainParameters = new ECDomainParameters(byName.getCurve(), byName.getG(), byName.getN(), byName.getH(), byName.getSeed());
        return eCDomainParameters;
    }

    public static boolean hasAnySupportedNamedCurves() {
        return curveNames.length > 0;
    }

    public static boolean containsECCCipherSuites(int[] iArr) {
        for (int isECCCipherSuite : iArr) {
            if (isECCCipherSuite(isECCCipherSuite)) {
                return true;
            }
        }
        return false;
    }

    public static boolean areOnSameCurve(ECDomainParameters eCDomainParameters, ECDomainParameters eCDomainParameters2) {
        return eCDomainParameters.getCurve().equals(eCDomainParameters2.getCurve()) && eCDomainParameters.getG().equals(eCDomainParameters2.getG()) && eCDomainParameters.getN().equals(eCDomainParameters2.getN()) && eCDomainParameters.getH().equals(eCDomainParameters2.getH());
    }

    public static boolean isSupportedNamedCurve(int i) {
        return i > 0 && i <= curveNames.length;
    }

    public static boolean isCompressionPreferred(short[] sArr, short s) {
        if (sArr == null) {
            return false;
        }
        for (short s2 : sArr) {
            if (s2 == 0) {
                return false;
            }
            if (s2 == s) {
                return true;
            }
        }
        return false;
    }

    public static byte[] serializeECFieldElement(int i, BigInteger bigInteger) throws IOException {
        return BigIntegers.asUnsignedByteArray((i + 7) / 8, bigInteger);
    }

    public static byte[] serializeECPoint(short[] sArr, ECPoint eCPoint) throws IOException {
        ECCurve curve = eCPoint.getCurve();
        boolean z = ECAlgorithms.isFpCurve(curve) ? isCompressionPreferred(sArr, 1) : ECAlgorithms.isF2mCurve(curve) ? isCompressionPreferred(sArr, 2) : false;
        return eCPoint.getEncoded(z);
    }

    public static byte[] serializeECPublicKey(short[] sArr, ECPublicKeyParameters eCPublicKeyParameters) throws IOException {
        return serializeECPoint(sArr, eCPublicKeyParameters.getQ());
    }

    public static BigInteger deserializeECFieldElement(int i, byte[] bArr) throws IOException {
        if (bArr.length == (i + 7) / 8) {
            return new BigInteger(1, bArr);
        }
        throw new TlsFatalAlert(50);
    }

    public static ECPoint deserializeECPoint(short[] sArr, ECCurve eCCurve, byte[] bArr) throws IOException {
        if (bArr == null || bArr.length < 1) {
            throw new TlsFatalAlert(47);
        }
        short s = 0;
        byte b = bArr[0];
        if (b == 2 || b == 3) {
            if (ECAlgorithms.isF2mCurve(eCCurve)) {
                s = 2;
            } else if (ECAlgorithms.isFpCurve(eCCurve)) {
                s = 1;
            } else {
                throw new TlsFatalAlert(47);
            }
        } else if (b != 4) {
            throw new TlsFatalAlert(47);
        }
        if (Arrays.contains(sArr, s)) {
            return eCCurve.decodePoint(bArr);
        }
        throw new TlsFatalAlert(47);
    }

    public static ECPublicKeyParameters deserializeECPublicKey(short[] sArr, ECDomainParameters eCDomainParameters, byte[] bArr) throws IOException {
        try {
            return new ECPublicKeyParameters(deserializeECPoint(sArr, eCDomainParameters.getCurve(), bArr), eCDomainParameters);
        } catch (RuntimeException unused) {
            throw new TlsFatalAlert(47);
        }
    }

    public static byte[] calculateECDHBasicAgreement(ECPublicKeyParameters eCPublicKeyParameters, ECPrivateKeyParameters eCPrivateKeyParameters) {
        ECDHBasicAgreement eCDHBasicAgreement = new ECDHBasicAgreement();
        eCDHBasicAgreement.init(eCPrivateKeyParameters);
        return BigIntegers.asUnsignedByteArray(eCDHBasicAgreement.getFieldSize(), eCDHBasicAgreement.calculateAgreement(eCPublicKeyParameters));
    }

    public static AsymmetricCipherKeyPair generateECKeyPair(SecureRandom secureRandom, ECDomainParameters eCDomainParameters) {
        ECKeyPairGenerator eCKeyPairGenerator = new ECKeyPairGenerator();
        eCKeyPairGenerator.init(new ECKeyGenerationParameters(eCDomainParameters, secureRandom));
        return eCKeyPairGenerator.generateKeyPair();
    }

    public static ECPrivateKeyParameters generateEphemeralClientKeyExchange(SecureRandom secureRandom, short[] sArr, ECDomainParameters eCDomainParameters, OutputStream outputStream) throws IOException {
        AsymmetricCipherKeyPair generateECKeyPair = generateECKeyPair(secureRandom, eCDomainParameters);
        writeECPoint(sArr, ((ECPublicKeyParameters) generateECKeyPair.getPublic()).getQ(), outputStream);
        return (ECPrivateKeyParameters) generateECKeyPair.getPrivate();
    }

    public static int readECExponent(int i, InputStream inputStream) throws IOException {
        BigInteger readECParameter = readECParameter(inputStream);
        if (readECParameter.bitLength() < 32) {
            int intValue = readECParameter.intValue();
            if (intValue > 0 && intValue < i) {
                return intValue;
            }
        }
        throw new TlsFatalAlert(47);
    }

    public static BigInteger readECFieldElement(int i, InputStream inputStream) throws IOException {
        return deserializeECFieldElement(i, TlsUtils.readOpaque8(inputStream));
    }

    public static BigInteger readECParameter(InputStream inputStream) throws IOException {
        return new BigInteger(1, TlsUtils.readOpaque8(inputStream));
    }

    public static ECDomainParameters readECParameters(int[] iArr, short[] sArr, InputStream inputStream) throws IOException {
        int i;
        int i2;
        BigInteger bigInteger;
        F2m f2m;
        int[] iArr2 = iArr;
        short[] sArr2 = sArr;
        InputStream inputStream2 = inputStream;
        try {
            short readUint8 = TlsUtils.readUint8(inputStream);
            if (readUint8 == 1) {
                checkNamedCurve(iArr2, 65281);
                BigInteger readECParameter = readECParameter(inputStream);
                BigInteger readECFieldElement = readECFieldElement(readECParameter.bitLength(), inputStream2);
                BigInteger readECFieldElement2 = readECFieldElement(readECParameter.bitLength(), inputStream2);
                byte[] readOpaque8 = TlsUtils.readOpaque8(inputStream);
                BigInteger readECParameter2 = readECParameter(inputStream);
                BigInteger readECParameter3 = readECParameter(inputStream);
                C3639Fp fp = new C3639Fp(readECParameter, readECFieldElement, readECFieldElement2, readECParameter2, readECParameter3);
                return new ECDomainParameters(fp, deserializeECPoint(sArr2, fp, readOpaque8), readECParameter2, readECParameter3);
            } else if (readUint8 == 2) {
                checkNamedCurve(iArr2, NamedCurve.arbitrary_explicit_char2_curves);
                int readUint16 = TlsUtils.readUint16(inputStream);
                short readUint82 = TlsUtils.readUint8(inputStream);
                if (ECBasisType.isValid(readUint82)) {
                    int readECExponent = readECExponent(readUint16, inputStream2);
                    if (readUint82 == 2) {
                        i2 = readECExponent(readUint16, inputStream2);
                        i = readECExponent(readUint16, inputStream2);
                    } else {
                        i2 = -1;
                        i = -1;
                    }
                    BigInteger readECFieldElement3 = readECFieldElement(readUint16, inputStream2);
                    BigInteger readECFieldElement4 = readECFieldElement(readUint16, inputStream2);
                    byte[] readOpaque82 = TlsUtils.readOpaque8(inputStream);
                    BigInteger readECParameter4 = readECParameter(inputStream);
                    BigInteger readECParameter5 = readECParameter(inputStream);
                    if (readUint82 == 2) {
                        bigInteger = readECParameter4;
                        f2m = new F2m(readUint16, readECExponent, i2, i, readECFieldElement3, readECFieldElement4, readECParameter4, readECParameter5);
                    } else {
                        bigInteger = readECParameter4;
                        f2m = new F2m(readUint16, readECExponent, readECFieldElement3, readECFieldElement4, bigInteger, readECParameter5);
                    }
                    return new ECDomainParameters(f2m, deserializeECPoint(sArr2, f2m, readOpaque82), bigInteger, readECParameter5);
                }
                throw new TlsFatalAlert(47);
            } else if (readUint8 == 3) {
                int readUint162 = TlsUtils.readUint16(inputStream);
                if (NamedCurve.refersToASpecificNamedCurve(readUint162)) {
                    checkNamedCurve(iArr2, readUint162);
                    return getParametersForNamedCurve(readUint162);
                }
                throw new TlsFatalAlert(47);
            } else {
                throw new TlsFatalAlert(47);
            }
        } catch (RuntimeException unused) {
            throw new TlsFatalAlert(47);
        }
    }

    private static void checkNamedCurve(int[] iArr, int i) throws IOException {
        if (iArr != null && !Arrays.contains(iArr, i)) {
            throw new TlsFatalAlert(47);
        }
    }

    public static void writeECExponent(int i, OutputStream outputStream) throws IOException {
        writeECParameter(BigInteger.valueOf((long) i), outputStream);
    }

    public static void writeECFieldElement(ECFieldElement eCFieldElement, OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque8(eCFieldElement.getEncoded(), outputStream);
    }

    public static void writeECFieldElement(int i, BigInteger bigInteger, OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque8(serializeECFieldElement(i, bigInteger), outputStream);
    }

    public static void writeECParameter(BigInteger bigInteger, OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque8(BigIntegers.asUnsignedByteArray(bigInteger), outputStream);
    }

    public static void writeExplicitECParameters(short[] sArr, ECDomainParameters eCDomainParameters, OutputStream outputStream) throws IOException {
        ECCurve curve = eCDomainParameters.getCurve();
        if (ECAlgorithms.isFpCurve(curve)) {
            TlsUtils.writeUint8(1, outputStream);
            writeECParameter(curve.getField().getCharacteristic(), outputStream);
        } else if (ECAlgorithms.isF2mCurve(curve)) {
            int[] exponentsPresent = ((PolynomialExtensionField) curve.getField()).getMinimalPolynomial().getExponentsPresent();
            TlsUtils.writeUint8(2, outputStream);
            int i = exponentsPresent[exponentsPresent.length - 1];
            TlsUtils.checkUint16(i);
            TlsUtils.writeUint16(i, outputStream);
            if (exponentsPresent.length == 3) {
                TlsUtils.writeUint8(1, outputStream);
                writeECExponent(exponentsPresent[1], outputStream);
            } else if (exponentsPresent.length == 5) {
                TlsUtils.writeUint8(2, outputStream);
                writeECExponent(exponentsPresent[1], outputStream);
                writeECExponent(exponentsPresent[2], outputStream);
                writeECExponent(exponentsPresent[3], outputStream);
            } else {
                throw new IllegalArgumentException("Only trinomial and pentomial curves are supported");
            }
        } else {
            throw new IllegalArgumentException("'ecParameters' not a known curve type");
        }
        writeECFieldElement(curve.getA(), outputStream);
        writeECFieldElement(curve.getB(), outputStream);
        TlsUtils.writeOpaque8(serializeECPoint(sArr, eCDomainParameters.getG()), outputStream);
        writeECParameter(eCDomainParameters.getN(), outputStream);
        writeECParameter(eCDomainParameters.getH(), outputStream);
    }

    public static void writeECPoint(short[] sArr, ECPoint eCPoint, OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque8(serializeECPoint(sArr, eCPoint), outputStream);
    }

    public static void writeNamedECParameters(int i, OutputStream outputStream) throws IOException {
        if (NamedCurve.refersToASpecificNamedCurve(i)) {
            TlsUtils.writeUint8(3, outputStream);
            TlsUtils.checkUint16(i);
            TlsUtils.writeUint16(i, outputStream);
            return;
        }
        throw new TlsFatalAlert(80);
    }
}
