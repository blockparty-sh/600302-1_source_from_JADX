package org.bitcoinj.core;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import javax.annotation.Nullable;
import org.bitcoinj.params.Networks;
import org.bitcoinj.script.Script;

public class Address extends VersionedChecksummedBytes {
    public static final int LENGTH = 20;
    private transient NetworkParameters params;

    public Address(NetworkParameters networkParameters, int i, byte[] bArr) throws WrongNetworkException {
        super(i, bArr);
        Preconditions.checkNotNull(networkParameters);
        Preconditions.checkArgument(bArr.length == 20, "Addresses are 160-bit hashes, so you must provide 20 bytes");
        if (isAcceptableVersion(networkParameters, i)) {
            this.params = networkParameters;
            return;
        }
        throw new WrongNetworkException(i, networkParameters.getAcceptableAddressCodes());
    }

    public static Address fromP2SHHash(NetworkParameters networkParameters, byte[] bArr) {
        try {
            return new Address(networkParameters, networkParameters.getP2SHHeader(), bArr);
        } catch (WrongNetworkException e) {
            throw new RuntimeException(e);
        }
    }

    public static Address fromP2SHScript(NetworkParameters networkParameters, Script script) {
        Preconditions.checkArgument(script.isPayToScriptHash(), "Not a P2SH script");
        return fromP2SHHash(networkParameters, script.getPubKeyHash());
    }

    public static Address fromBase58(@Nullable NetworkParameters networkParameters, String str) throws AddressFormatException {
        return new Address(networkParameters, str);
    }

    public Address(NetworkParameters networkParameters, byte[] bArr) {
        super(networkParameters.getAddressHeader(), bArr);
        Preconditions.checkArgument(bArr.length == 20, "Addresses are 160-bit hashes, so you must provide 20 bytes");
        this.params = networkParameters;
    }

    @Deprecated
    public Address(@Nullable NetworkParameters networkParameters, String str) throws AddressFormatException {
        super(str);
        if (networkParameters == null) {
            NetworkParameters networkParameters2 = null;
            Iterator it = Networks.get().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                NetworkParameters networkParameters3 = (NetworkParameters) it.next();
                if (isAcceptableVersion(networkParameters3, this.version)) {
                    networkParameters2 = networkParameters3;
                    break;
                }
            }
            if (networkParameters2 != null) {
                this.params = networkParameters2;
                return;
            }
            StringBuilder sb = new StringBuilder();
            sb.append("No network found for ");
            sb.append(str);
            throw new AddressFormatException(sb.toString());
        } else if (isAcceptableVersion(networkParameters, this.version)) {
            this.params = networkParameters;
        } else {
            throw new WrongNetworkException(this.version, networkParameters.getAcceptableAddressCodes());
        }
    }

    public byte[] getHash160() {
        return this.bytes;
    }

    public boolean isP2SHAddress() {
        NetworkParameters parameters = getParameters();
        return parameters != null && this.version == parameters.p2shHeader;
    }

    public NetworkParameters getParameters() {
        return this.params;
    }

    public static NetworkParameters getParametersFromAddress(String str) throws AddressFormatException {
        try {
            return fromBase58(null, str).getParameters();
        } catch (WrongNetworkException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isAcceptableVersion(NetworkParameters networkParameters, int i) {
        for (int i2 : networkParameters.getAcceptableAddressCodes()) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }

    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeUTF(this.params.f799id);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.params = NetworkParameters.fromID(objectInputStream.readUTF());
    }
}
