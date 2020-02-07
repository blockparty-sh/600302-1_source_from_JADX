package org.bitcoinj.wallet;

import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.KeyCrypter;
import org.bitcoinj.wallet.Protos.C3509Key;

public interface KeyChainFactory {
    DeterministicKeyChain makeKeyChain(C3509Key key, C3509Key key2, DeterministicSeed deterministicSeed, KeyCrypter keyCrypter, boolean z);

    DeterministicKeyChain makeWatchingKeyChain(C3509Key key, C3509Key key2, DeterministicKey deterministicKey, boolean z, boolean z2) throws UnreadableWalletException;
}
