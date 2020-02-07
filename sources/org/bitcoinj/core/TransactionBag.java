package org.bitcoinj.core;

import java.util.Map;
import org.bitcoinj.script.Script;
import org.bitcoinj.wallet.WalletTransaction.Pool;

public interface TransactionBag {
    Map<Sha256Hash, Transaction> getTransactionPool(Pool pool);

    boolean isPayToScriptHashMine(byte[] bArr);

    boolean isPubKeyHashMine(byte[] bArr);

    boolean isPubKeyMine(byte[] bArr);

    boolean isWatchedScript(Script script);
}
