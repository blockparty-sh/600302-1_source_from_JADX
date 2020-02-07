package walletutils;

import p014go.Seq;

public abstract class Walletutils {
    private static native void _init();

    public static native String decrypt(String str, String str2) throws Exception;

    public static native String decryptCFB(byte[] bArr, String str);

    public static native String decryptSJCLJsonString(String str, String str2) throws Exception;

    public static native String decryptSJCLTxNote(String str, String str2) throws Exception;

    public static native String encrypt(String str, String str2) throws Exception;

    public static native String encryptCFB(byte[] bArr, String str);

    public static native String getBuildCommit();

    public static native String getVersion();

    public static native Keypair newKeypairFromPrivateKey(byte[] bArr) throws Exception;

    public static native void setBuildCommit(String str);

    public static native void setVersion(String str);

    public static native byte[] signDeterministicWithXPrv(String str, String str2) throws Exception;

    public static native String signWithPrivateKey(byte[] bArr, String str) throws Exception;

    public static void touch() {
    }

    public static native boolean verifyWithPublicKey(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public static native boolean verifyWithXPub(String str, byte[] bArr, byte[] bArr2);

    public static native String xPubToCopayID(String str, String str2);

    static {
        Seq.touch();
        _init();
    }

    private Walletutils() {
    }
}
