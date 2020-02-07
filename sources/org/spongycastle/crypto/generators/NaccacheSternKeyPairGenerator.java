package org.spongycastle.crypto.generators;

import androidx.core.view.InputDeviceCompat;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Vector;
import org.bitcoinj.script.ScriptOpCodes;
import org.spongycastle.crypto.AsymmetricCipherKeyPair;
import org.spongycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.spongycastle.crypto.KeyGenerationParameters;
import org.spongycastle.crypto.params.AsymmetricKeyParameter;
import org.spongycastle.crypto.params.NaccacheSternKeyGenerationParameters;
import org.spongycastle.crypto.params.NaccacheSternKeyParameters;
import org.spongycastle.crypto.params.NaccacheSternPrivateKeyParameters;
import org.spongycastle.crypto.tls.CipherSuite;
import p015io.grpc.internal.GrpcUtil;

public class NaccacheSternKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private static final BigInteger ONE = BigInteger.valueOf(1);
    private static int[] smallPrimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, ScriptOpCodes.OP_INVERT, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 199, 211, 223, 227, 229, 233, 239, 241, 251, InputDeviceCompat.SOURCE_KEYBOARD, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, GrpcUtil.DEFAULT_PORT_SSL, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557};
    private NaccacheSternKeyGenerationParameters param;

    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (NaccacheSternKeyGenerationParameters) keyGenerationParameters;
    }

    public AsymmetricCipherKeyPair generateKeyPair() {
        long j;
        BigInteger generatePrime;
        BigInteger add;
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger generatePrime2;
        BigInteger add2;
        BigInteger bigInteger3;
        BigInteger bigInteger4;
        BigInteger bigInteger5;
        BigInteger bigInteger6;
        boolean z;
        BigInteger bigInteger7;
        BigInteger bigInteger8;
        int i;
        SecureRandom secureRandom;
        int strength = this.param.getStrength();
        SecureRandom random = this.param.getRandom();
        int certainty = this.param.getCertainty();
        boolean isDebug = this.param.isDebug();
        if (isDebug) {
            PrintStream printStream = System.out;
            StringBuilder sb = new StringBuilder();
            sb.append("Fetching first ");
            sb.append(this.param.getCntSmallPrimes());
            sb.append(" primes.");
            printStream.println(sb.toString());
        }
        Vector permuteList = permuteList(findFirstPrimes(this.param.getCntSmallPrimes()), random);
        BigInteger bigInteger9 = ONE;
        BigInteger bigInteger10 = bigInteger9;
        for (int i2 = 0; i2 < permuteList.size() / 2; i2++) {
            bigInteger10 = bigInteger10.multiply((BigInteger) permuteList.elementAt(i2));
        }
        for (int size = permuteList.size() / 2; size < permuteList.size(); size++) {
            bigInteger9 = bigInteger9.multiply((BigInteger) permuteList.elementAt(size));
        }
        BigInteger multiply = bigInteger10.multiply(bigInteger9);
        int bitLength = (((strength - multiply.bitLength()) - 48) / 2) + 1;
        BigInteger generatePrime3 = generatePrime(bitLength, certainty, random);
        BigInteger generatePrime4 = generatePrime(bitLength, certainty, random);
        if (isDebug) {
            System.out.println("generating p and q");
        }
        BigInteger shiftLeft = generatePrime3.multiply(bigInteger10).shiftLeft(1);
        BigInteger shiftLeft2 = generatePrime4.multiply(bigInteger9).shiftLeft(1);
        long j2 = 0;
        while (true) {
            j = j2 + 1;
            generatePrime = generatePrime(24, certainty, random);
            add = generatePrime.multiply(shiftLeft).add(ONE);
            if (!add.isProbablePrime(certainty)) {
                bigInteger2 = shiftLeft2;
                bigInteger = shiftLeft;
            } else {
                while (true) {
                    do {
                        generatePrime2 = generatePrime(24, certainty, random);
                    } while (generatePrime.equals(generatePrime2));
                    bigInteger2 = shiftLeft2;
                    add2 = generatePrime2.multiply(shiftLeft2).add(ONE);
                    if (add2.isProbablePrime(certainty)) {
                        break;
                    }
                    BigInteger bigInteger11 = add;
                    shiftLeft2 = bigInteger2;
                }
                bigInteger = shiftLeft;
                if (multiply.gcd(generatePrime.multiply(generatePrime2)).equals(ONE)) {
                    if (add.multiply(add2).bitLength() >= strength) {
                        break;
                    } else if (isDebug) {
                        PrintStream printStream2 = System.out;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("key size too small. Should be ");
                        sb2.append(strength);
                        sb2.append(" but is actually ");
                        sb2.append(add.multiply(add2).bitLength());
                        printStream2.println(sb2.toString());
                    }
                } else {
                    continue;
                }
            }
            j2 = j;
            shiftLeft2 = bigInteger2;
            shiftLeft = bigInteger;
        }
        String str = "needed ";
        if (isDebug) {
            PrintStream printStream3 = System.out;
            bigInteger3 = generatePrime4;
            StringBuilder sb3 = new StringBuilder();
            sb3.append(str);
            sb3.append(j);
            sb3.append(" tries to generate p and q.");
            printStream3.println(sb3.toString());
        } else {
            bigInteger3 = generatePrime4;
        }
        BigInteger multiply2 = add.multiply(add2);
        BigInteger multiply3 = add.subtract(ONE).multiply(add2.subtract(ONE));
        if (isDebug) {
            System.out.println("generating g");
        }
        long j3 = 0;
        while (true) {
            Vector vector = new Vector();
            bigInteger4 = add2;
            bigInteger5 = add;
            int i3 = 0;
            while (i3 != permuteList.size()) {
                BigInteger divide = multiply3.divide((BigInteger) permuteList.elementAt(i3));
                while (true) {
                    j3++;
                    bigInteger8 = new BigInteger(strength, certainty, random);
                    i = strength;
                    secureRandom = random;
                    if (!bigInteger8.modPow(divide, multiply2).equals(ONE)) {
                        break;
                    }
                    strength = i;
                    random = secureRandom;
                }
                vector.addElement(bigInteger8);
                i3++;
                strength = i;
                random = secureRandom;
            }
            int i4 = strength;
            SecureRandom secureRandom2 = random;
            bigInteger6 = ONE;
            for (int i5 = 0; i5 < permuteList.size(); i5++) {
                bigInteger6 = bigInteger6.multiply(((BigInteger) vector.elementAt(i5)).modPow(multiply.divide((BigInteger) permuteList.elementAt(i5)), multiply2)).mod(multiply2);
            }
            int i6 = 0;
            while (true) {
                if (i6 >= permuteList.size()) {
                    z = false;
                    break;
                } else if (bigInteger6.modPow(multiply3.divide((BigInteger) permuteList.elementAt(i6)), multiply2).equals(ONE)) {
                    if (isDebug) {
                        PrintStream printStream4 = System.out;
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("g has order phi(n)/");
                        sb4.append(permuteList.elementAt(i6));
                        sb4.append("\n g: ");
                        sb4.append(bigInteger6);
                        printStream4.println(sb4.toString());
                    }
                    z = true;
                } else {
                    i6++;
                }
            }
            if (!z) {
                if (!bigInteger6.modPow(multiply3.divide(BigInteger.valueOf(4)), multiply2).equals(ONE)) {
                    if (!bigInteger6.modPow(multiply3.divide(generatePrime), multiply2).equals(ONE)) {
                        if (!bigInteger6.modPow(multiply3.divide(generatePrime2), multiply2).equals(ONE)) {
                            if (!bigInteger6.modPow(multiply3.divide(generatePrime3), multiply2).equals(ONE)) {
                                bigInteger7 = bigInteger3;
                                if (!bigInteger6.modPow(multiply3.divide(bigInteger7), multiply2).equals(ONE)) {
                                    break;
                                }
                                if (isDebug) {
                                    PrintStream printStream5 = System.out;
                                    StringBuilder sb5 = new StringBuilder();
                                    sb5.append("g has order phi(n)/b\n g: ");
                                    sb5.append(bigInteger6);
                                    printStream5.println(sb5.toString());
                                }
                                bigInteger3 = bigInteger7;
                                add = bigInteger5;
                                add2 = bigInteger4;
                                strength = i4;
                                random = secureRandom2;
                            } else if (isDebug) {
                                PrintStream printStream6 = System.out;
                                StringBuilder sb6 = new StringBuilder();
                                sb6.append("g has order phi(n)/a\n g: ");
                                sb6.append(bigInteger6);
                                printStream6.println(sb6.toString());
                            }
                        } else if (isDebug) {
                            PrintStream printStream7 = System.out;
                            StringBuilder sb7 = new StringBuilder();
                            sb7.append("g has order phi(n)/q'\n g: ");
                            sb7.append(bigInteger6);
                            printStream7.println(sb7.toString());
                        }
                    } else if (isDebug) {
                        PrintStream printStream8 = System.out;
                        StringBuilder sb8 = new StringBuilder();
                        sb8.append("g has order phi(n)/p'\n g: ");
                        sb8.append(bigInteger6);
                        printStream8.println(sb8.toString());
                    }
                } else if (isDebug) {
                    PrintStream printStream9 = System.out;
                    StringBuilder sb9 = new StringBuilder();
                    sb9.append("g has order phi(n)/4\n g:");
                    sb9.append(bigInteger6);
                    printStream9.println(sb9.toString());
                }
            }
            bigInteger7 = bigInteger3;
            bigInteger3 = bigInteger7;
            add = bigInteger5;
            add2 = bigInteger4;
            strength = i4;
            random = secureRandom2;
        }
        if (isDebug) {
            PrintStream printStream10 = System.out;
            StringBuilder sb10 = new StringBuilder();
            sb10.append(str);
            sb10.append(j3);
            sb10.append(" tries to generate g");
            printStream10.println(sb10.toString());
            System.out.println();
            System.out.println("found new NaccacheStern cipher variables:");
            PrintStream printStream11 = System.out;
            StringBuilder sb11 = new StringBuilder();
            sb11.append("smallPrimes: ");
            sb11.append(permuteList);
            printStream11.println(sb11.toString());
            PrintStream printStream12 = System.out;
            StringBuilder sb12 = new StringBuilder();
            sb12.append("sigma:...... ");
            sb12.append(multiply);
            sb12.append(" (");
            sb12.append(multiply.bitLength());
            sb12.append(" bits)");
            printStream12.println(sb12.toString());
            PrintStream printStream13 = System.out;
            StringBuilder sb13 = new StringBuilder();
            sb13.append("a:.......... ");
            sb13.append(generatePrime3);
            printStream13.println(sb13.toString());
            PrintStream printStream14 = System.out;
            StringBuilder sb14 = new StringBuilder();
            sb14.append("b:.......... ");
            sb14.append(bigInteger7);
            printStream14.println(sb14.toString());
            PrintStream printStream15 = System.out;
            StringBuilder sb15 = new StringBuilder();
            sb15.append("p':......... ");
            sb15.append(generatePrime);
            printStream15.println(sb15.toString());
            PrintStream printStream16 = System.out;
            StringBuilder sb16 = new StringBuilder();
            sb16.append("q':......... ");
            sb16.append(generatePrime2);
            printStream16.println(sb16.toString());
            PrintStream printStream17 = System.out;
            StringBuilder sb17 = new StringBuilder();
            sb17.append("p:.......... ");
            sb17.append(bigInteger5);
            printStream17.println(sb17.toString());
            PrintStream printStream18 = System.out;
            StringBuilder sb18 = new StringBuilder();
            sb18.append("q:.......... ");
            sb18.append(bigInteger4);
            printStream18.println(sb18.toString());
            PrintStream printStream19 = System.out;
            StringBuilder sb19 = new StringBuilder();
            sb19.append("n:.......... ");
            sb19.append(multiply2);
            printStream19.println(sb19.toString());
            PrintStream printStream20 = System.out;
            StringBuilder sb20 = new StringBuilder();
            sb20.append("phi(n):..... ");
            sb20.append(multiply3);
            printStream20.println(sb20.toString());
            PrintStream printStream21 = System.out;
            StringBuilder sb21 = new StringBuilder();
            sb21.append("g:.......... ");
            sb21.append(bigInteger6);
            printStream21.println(sb21.toString());
            System.out.println();
        }
        NaccacheSternKeyParameters naccacheSternKeyParameters = new NaccacheSternKeyParameters(false, bigInteger6, multiply2, multiply.bitLength());
        NaccacheSternPrivateKeyParameters naccacheSternPrivateKeyParameters = new NaccacheSternPrivateKeyParameters(bigInteger6, multiply2, multiply.bitLength(), permuteList, multiply3);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) naccacheSternKeyParameters, (AsymmetricKeyParameter) naccacheSternPrivateKeyParameters);
    }

    private static BigInteger generatePrime(int i, int i2, SecureRandom secureRandom) {
        BigInteger bigInteger = new BigInteger(i, i2, secureRandom);
        while (bigInteger.bitLength() != i) {
            bigInteger = new BigInteger(i, i2, secureRandom);
        }
        return bigInteger;
    }

    private static Vector permuteList(Vector vector, SecureRandom secureRandom) {
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        for (int i = 0; i < vector.size(); i++) {
            vector3.addElement(vector.elementAt(i));
        }
        vector2.addElement(vector3.elementAt(0));
        vector3.removeElementAt(0);
        while (vector3.size() != 0) {
            vector2.insertElementAt(vector3.elementAt(0), getInt(secureRandom, vector2.size() + 1));
            vector3.removeElementAt(0);
        }
        return vector2;
    }

    private static int getInt(SecureRandom secureRandom, int i) {
        int nextInt;
        int i2;
        if (((-i) & i) == i) {
            return (int) ((((long) i) * ((long) (secureRandom.nextInt() & Integer.MAX_VALUE))) >> 31);
        }
        do {
            nextInt = secureRandom.nextInt() & Integer.MAX_VALUE;
            i2 = nextInt % i;
        } while ((nextInt - i2) + (i - 1) < 0);
        return i2;
    }

    private static Vector findFirstPrimes(int i) {
        Vector vector = new Vector(i);
        for (int i2 = 0; i2 != i; i2++) {
            vector.addElement(BigInteger.valueOf((long) smallPrimes[i2]));
        }
        return vector;
    }
}
