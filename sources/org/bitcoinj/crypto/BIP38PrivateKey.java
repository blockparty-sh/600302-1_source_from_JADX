package org.bitcoinj.crypto;

import com.google.common.base.Charsets;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Bytes;
import com.lambdaworks.crypto.SCrypt;
import com.yakivmospan.scytale.Options;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.text.Normalizer;
import java.text.Normalizer.Form;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.bitcoinj.core.AddressFormatException;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.VersionedChecksummedBytes;

public class BIP38PrivateKey extends VersionedChecksummedBytes {
    public final byte[] addressHash;
    public final boolean compressed;
    public final byte[] content;
    public final boolean ecMultiply;
    public final boolean hasLotAndSequence;
    public transient NetworkParameters params;

    public static final class BadPassphraseException extends Exception {
    }

    public static BIP38PrivateKey fromBase58(NetworkParameters networkParameters, String str) throws AddressFormatException {
        return new BIP38PrivateKey(networkParameters, str);
    }

    @Deprecated
    public BIP38PrivateKey(NetworkParameters networkParameters, String str) throws AddressFormatException {
        super(str);
        this.params = (NetworkParameters) Preconditions.checkNotNull(networkParameters);
        if (this.version != 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("Mismatched version number: ");
            sb.append(this.version);
            throw new AddressFormatException(sb.toString());
        } else if (this.bytes.length == 38) {
            this.hasLotAndSequence = (this.bytes[1] & 4) != 0;
            this.compressed = (this.bytes[1] & 32) != 0;
            if ((this.bytes[1] & 1) != 0) {
                throw new AddressFormatException("Bit 0x01 reserved for future use.");
            } else if ((this.bytes[1] & 2) != 0) {
                throw new AddressFormatException("Bit 0x02 reserved for future use.");
            } else if ((this.bytes[1] & 8) != 0) {
                throw new AddressFormatException("Bit 0x08 reserved for future use.");
            } else if ((this.bytes[1] & 16) == 0) {
                byte b = this.bytes[0] & 255;
                if (b == 66) {
                    if ((this.bytes[1] & 192) == 192) {
                        this.ecMultiply = false;
                        if (this.hasLotAndSequence) {
                            throw new AddressFormatException("Non-EC-multiplied keys cannot have lot/sequence.");
                        }
                    } else {
                        throw new AddressFormatException("Bits 0x40 and 0x80 must be set for non-EC-multiplied keys.");
                    }
                } else if (b != 67) {
                    throw new AddressFormatException("Second byte must by 0x42 or 0x43.");
                } else if ((this.bytes[1] & 192) == 0) {
                    this.ecMultiply = true;
                } else {
                    throw new AddressFormatException("Bits 0x40 and 0x80 must be cleared for EC-multiplied keys.");
                }
                this.addressHash = Arrays.copyOfRange(this.bytes, 2, 6);
                this.content = Arrays.copyOfRange(this.bytes, 6, 38);
            } else {
                throw new AddressFormatException("Bit 0x10 reserved for future use.");
            }
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Wrong number of bytes, excluding version byte: ");
            sb2.append(this.bytes.length);
            throw new AddressFormatException(sb2.toString());
        }
    }

    public ECKey decrypt(String str) throws BadPassphraseException {
        String normalize = Normalizer.normalize(str, Form.NFC);
        ECKey decryptEC = this.ecMultiply ? decryptEC(normalize) : decryptNoEC(normalize);
        if (Arrays.equals(Arrays.copyOfRange(Sha256Hash.twiceOf(decryptEC.toAddress(this.params).toString().getBytes(Charsets.US_ASCII)).getBytes(), 0, 4), this.addressHash)) {
            return decryptEC;
        }
        throw new BadPassphraseException();
    }

    private ECKey decryptNoEC(String str) {
        try {
            byte[] scrypt = SCrypt.scrypt(str.getBytes(Charsets.UTF_8), this.addressHash, 16384, 8, 8, 64);
            SecretKeySpec secretKeySpec = new SecretKeySpec(Arrays.copyOfRange(scrypt, 32, 64), Options.ALGORITHM_AES);
            DRMWorkaround.maybeDisableExportControls();
            Cipher instance = Cipher.getInstance("AES/ECB/NoPadding");
            instance.init(2, secretKeySpec);
            byte[] doFinal = instance.doFinal(this.content, 0, 32);
            for (int i = 0; i < 32; i++) {
                doFinal[i] = (byte) (doFinal[i] ^ scrypt[i]);
            }
            return ECKey.fromPrivate(doFinal, this.compressed);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    private ECKey decryptEC(String str) {
        try {
            boolean z = false;
            byte[] copyOfRange = Arrays.copyOfRange(this.content, 0, 8);
            byte[] scrypt = SCrypt.scrypt(str.getBytes(Charsets.UTF_8), this.hasLotAndSequence ? Arrays.copyOfRange(copyOfRange, 0, 4) : copyOfRange, 16384, 8, 8, 32);
            if (this.hasLotAndSequence) {
                byte[] concat = Bytes.concat(scrypt, copyOfRange);
                Preconditions.checkState(concat.length == 40);
                scrypt = Sha256Hash.hashTwice(concat);
            }
            BigInteger bigInteger = new BigInteger(1, scrypt);
            ECKey fromPrivate = ECKey.fromPrivate(bigInteger, true);
            byte[] concat2 = Bytes.concat(this.addressHash, copyOfRange);
            Preconditions.checkState(concat2.length == 12);
            byte[] scrypt2 = SCrypt.scrypt(fromPrivate.getPubKey(), concat2, 1024, 1, 1, 64);
            SecretKeySpec secretKeySpec = new SecretKeySpec(Arrays.copyOfRange(scrypt2, 32, 64), Options.ALGORITHM_AES);
            Cipher instance = Cipher.getInstance("AES/ECB/NoPadding");
            instance.init(2, secretKeySpec);
            byte[] doFinal = instance.doFinal(Arrays.copyOfRange(this.content, 16, 32));
            Preconditions.checkState(doFinal.length == 16);
            for (int i = 0; i < 16; i++) {
                doFinal[i] = (byte) (doFinal[i] ^ scrypt2[i + 16]);
            }
            byte[] doFinal2 = instance.doFinal(Bytes.concat(Arrays.copyOfRange(this.content, 8, 16), Arrays.copyOfRange(doFinal, 0, 8)));
            Preconditions.checkState(doFinal2.length == 16);
            for (int i2 = 0; i2 < 16; i2++) {
                doFinal2[i2] = (byte) (doFinal2[i2] ^ scrypt2[i2]);
            }
            byte[] concat3 = Bytes.concat(doFinal2, Arrays.copyOfRange(doFinal, 8, 16));
            Preconditions.checkState(concat3.length == 24);
            BigInteger bigInteger2 = new BigInteger(1, Sha256Hash.hashTwice(concat3));
            Preconditions.checkState(bigInteger.signum() >= 0);
            if (bigInteger2.signum() >= 0) {
                z = true;
            }
            Preconditions.checkState(z);
            return ECKey.fromPrivate(bigInteger.multiply(bigInteger2).mod(ECKey.CURVE.getN()), this.compressed);
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BIP38PrivateKey bIP38PrivateKey = (BIP38PrivateKey) obj;
        if (!super.equals(bIP38PrivateKey) || !Objects.equal(this.params, bIP38PrivateKey.params)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(super.hashCode()), this.params);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeUTF(this.params.getId());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.params = (NetworkParameters) Preconditions.checkNotNull(NetworkParameters.fromID(objectInputStream.readUTF()));
    }
}
