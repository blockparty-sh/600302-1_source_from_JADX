package org.spongycastle.crypto.tls;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.agreement.DHBasicAgreement;
import org.spongycastle.crypto.generators.DHBasicKeyPairGenerator;
import org.spongycastle.crypto.params.DHKeyGenerationParameters;
import org.spongycastle.crypto.params.DHParameters;
import org.spongycastle.crypto.params.DHPrivateKeyParameters;
import org.spongycastle.crypto.params.DHPublicKeyParameters;
import org.spongycastle.util.BigIntegers;

public class TlsDHUtils {
    static final BigInteger ONE = BigInteger.valueOf(1);
    static final BigInteger TWO = BigInteger.valueOf(2);

    public static boolean areCompatibleParameters(DHParameters dHParameters, DHParameters dHParameters2) {
        return dHParameters.getP().equals(dHParameters2.getP()) && dHParameters.getG().equals(dHParameters2.getG());
    }

    public static byte[] calculateDHBasicAgreement(DHPublicKeyParameters dHPublicKeyParameters, DHPrivateKeyParameters dHPrivateKeyParameters) {
        DHBasicAgreement dHBasicAgreement = new DHBasicAgreement();
        dHBasicAgreement.init(dHPrivateKeyParameters);
        return BigIntegers.asUnsignedByteArray(dHBasicAgreement.calculateAgreement(dHPublicKeyParameters));
    }

    public static AsymmetricCipherKeyPair generateDHKeyPair(SecureRandom secureRandom, DHParameters dHParameters) {
        DHBasicKeyPairGenerator dHBasicKeyPairGenerator = new DHBasicKeyPairGenerator();
        dHBasicKeyPairGenerator.init(new DHKeyGenerationParameters(secureRandom, dHParameters));
        return dHBasicKeyPairGenerator.generateKeyPair();
    }

    public static DHPrivateKeyParameters generateEphemeralClientKeyExchange(SecureRandom secureRandom, DHParameters dHParameters, OutputStream outputStream) throws IOException {
        AsymmetricCipherKeyPair generateDHKeyPair = generateDHKeyPair(secureRandom, dHParameters);
        writeDHParameter(((DHPublicKeyParameters) generateDHKeyPair.getPublic()).getY(), outputStream);
        return (DHPrivateKeyParameters) generateDHKeyPair.getPrivate();
    }

    public static DHPrivateKeyParameters generateEphemeralServerKeyExchange(SecureRandom secureRandom, DHParameters dHParameters, OutputStream outputStream) throws IOException {
        AsymmetricCipherKeyPair generateDHKeyPair = generateDHKeyPair(secureRandom, dHParameters);
        new ServerDHParams((DHPublicKeyParameters) generateDHKeyPair.getPublic()).encode(outputStream);
        return (DHPrivateKeyParameters) generateDHKeyPair.getPrivate();
    }

    public static DHPublicKeyParameters validateDHPublicKey(DHPublicKeyParameters dHPublicKeyParameters) throws IOException {
        BigInteger y = dHPublicKeyParameters.getY();
        DHParameters parameters = dHPublicKeyParameters.getParameters();
        BigInteger p = parameters.getP();
        BigInteger g = parameters.getG();
        if (!p.isProbablePrime(2)) {
            throw new TlsFatalAlert(47);
        } else if (g.compareTo(TWO) < 0 || g.compareTo(p.subtract(TWO)) > 0) {
            throw new TlsFatalAlert(47);
        } else if (y.compareTo(TWO) >= 0 && y.compareTo(p.subtract(ONE)) <= 0) {
            return dHPublicKeyParameters;
        } else {
            throw new TlsFatalAlert(47);
        }
    }

    public static BigInteger readDHParameter(InputStream inputStream) throws IOException {
        return new BigInteger(1, TlsUtils.readOpaque16(inputStream));
    }

    public static void writeDHParameter(BigInteger bigInteger, OutputStream outputStream) throws IOException {
        TlsUtils.writeOpaque16(BigIntegers.asUnsignedByteArray(bigInteger), outputStream);
    }
}
