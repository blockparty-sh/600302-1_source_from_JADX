package org.bitcoinj.wallet;

import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDUtils;
import org.bitcoinj.crypto.KeyCrypter;
import org.bitcoinj.wallet.Protos.C3509Key;

public class DefaultKeyChainFactory implements KeyChainFactory {
    public DeterministicKeyChain makeKeyChain(C3509Key key, C3509Key key2, DeterministicSeed deterministicSeed, KeyCrypter keyCrypter, boolean z) {
        if (z) {
            return new MarriedKeyChain(deterministicSeed, keyCrypter);
        }
        return new DeterministicKeyChain(deterministicSeed, keyCrypter);
    }

    public DeterministicKeyChain makeWatchingKeyChain(C3509Key key, C3509Key key2, DeterministicKey deterministicKey, boolean z, boolean z2) throws UnreadableWalletException {
        if (!deterministicKey.getPath().equals(DeterministicKeyChain.ACCOUNT_ZERO_PATH)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Expecting account key but found key with path: ");
            sb.append(HDUtils.formatPath(deterministicKey.getPath()));
            throw new UnreadableWalletException(sb.toString());
        } else if (z2) {
            return new MarriedKeyChain(deterministicKey);
        } else {
            return new DeterministicKeyChain(deterministicKey, z);
        }
    }
}
