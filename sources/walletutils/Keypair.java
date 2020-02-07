package walletutils;

import java.util.Arrays;
import p014go.Seq;
import p014go.Seq.Proxy;

public final class Keypair implements Proxy {
    private final int refnum;

    private static native int __NewKeypairFromPrivateKey(byte[] bArr);

    public final native byte[] getPrivateKey();

    public final native byte[] getPublicKey();

    public final native void setPrivateKey(byte[] bArr);

    public final native void setPublicKey(byte[] bArr);

    static {
        Walletutils.touch();
    }

    public final int incRefnum() {
        Seq.incGoRef(this.refnum, this);
        return this.refnum;
    }

    public Keypair(byte[] bArr) {
        this.refnum = __NewKeypairFromPrivateKey(bArr);
        Seq.trackGoRef(this.refnum, this);
    }

    Keypair(int i) {
        this.refnum = i;
        Seq.trackGoRef(i, this);
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Keypair)) {
            return false;
        }
        Keypair keypair = (Keypair) obj;
        byte[] privateKey = getPrivateKey();
        byte[] privateKey2 = keypair.getPrivateKey();
        if (privateKey == null) {
            if (privateKey2 != null) {
                return false;
            }
        } else if (!privateKey.equals(privateKey2)) {
            return false;
        }
        byte[] publicKey = getPublicKey();
        byte[] publicKey2 = keypair.getPublicKey();
        if (publicKey == null) {
            if (publicKey2 != null) {
                return false;
            }
        } else if (!publicKey.equals(publicKey2)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{getPrivateKey(), getPublicKey()});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Keypair");
        sb.append("{");
        sb.append("PrivateKey:");
        sb.append(getPrivateKey());
        String str = ",";
        sb.append(str);
        sb.append("PublicKey:");
        sb.append(getPublicKey());
        sb.append(str);
        sb.append("}");
        return sb.toString();
    }
}
