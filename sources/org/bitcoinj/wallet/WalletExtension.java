package org.bitcoinj.wallet;

public interface WalletExtension {
    void deserializeWalletExtension(C3530Wallet wallet, byte[] bArr) throws Exception;

    String getWalletExtensionID();

    boolean isWalletExtensionMandatory();

    byte[] serializeWalletExtension();
}
