package org.bitcoinj.signers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.script.Script;
import org.bitcoinj.wallet.KeyBag;

public interface TransactionSigner {

    public static class MissingSignatureException extends RuntimeException {
    }

    public static class ProposedTransaction {
        public final Map<Script, List<ChildNumber>> keyPaths;
        public final Transaction partialTx;
        boolean useForkId = false;

        public ProposedTransaction(Transaction transaction) {
            this.partialTx = transaction;
            this.keyPaths = new HashMap();
        }

        public ProposedTransaction(Transaction transaction, boolean z) {
            this.partialTx = transaction;
            this.keyPaths = new HashMap();
            this.useForkId = z;
        }
    }

    void deserialize(byte[] bArr);

    boolean isReady();

    byte[] serialize();

    boolean signInputs(ProposedTransaction proposedTransaction, KeyBag keyBag);
}
