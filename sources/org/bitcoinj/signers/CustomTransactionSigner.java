package org.bitcoinj.signers;

import com.google.common.base.Preconditions;
import java.util.List;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.ECKey.ECDSASignature;
import org.bitcoinj.core.ScriptException;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.Transaction.SigHash;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.script.Script;
import org.bitcoinj.signers.TransactionSigner.ProposedTransaction;
import org.bitcoinj.wallet.KeyBag;
import org.bitcoinj.wallet.RedeemData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class CustomTransactionSigner extends StatelessTransactionSigner {
    private static final Logger log = LoggerFactory.getLogger(CustomTransactionSigner.class);

    public class SignatureAndKey {
        public final ECKey pubKey;
        public final ECDSASignature sig;

        public SignatureAndKey(ECDSASignature eCDSASignature, ECKey eCKey) {
            this.sig = eCDSASignature;
            this.pubKey = eCKey;
        }
    }

    /* access modifiers changed from: protected */
    public abstract SignatureAndKey getSignature(Sha256Hash sha256Hash, List<ChildNumber> list);

    public boolean isReady() {
        return true;
    }

    public boolean signInputs(ProposedTransaction proposedTransaction, KeyBag keyBag) {
        Sha256Hash sha256Hash;
        Transaction transaction = proposedTransaction.partialTx;
        int size = transaction.getInputs().size();
        for (int i = 0; i < size; i++) {
            long j = (long) i;
            TransactionInput input = transaction.getInput(j);
            TransactionOutput connectedOutput = input.getConnectedOutput();
            if (connectedOutput != null) {
                Script scriptPubKey = connectedOutput.getScriptPubKey();
                if (!scriptPubKey.isPayToScriptHash()) {
                    log.warn("CustomTransactionSigner works only with P2SH transactions");
                    return false;
                }
                Script script = (Script) Preconditions.checkNotNull(input.getScriptSig());
                try {
                    input.getScriptSig().correctlySpends(transaction, j, input.getConnectedOutput().getScriptPubKey());
                    log.warn("Input {} already correctly spends output, assuming SIGHASH type used will be safe and skipping signing.", (Object) Integer.valueOf(i));
                } catch (ScriptException unused) {
                    RedeemData connectedRedeemData = input.getConnectedRedeemData(keyBag);
                    if (connectedRedeemData == null) {
                        log.warn("No redeem data found for input {}", (Object) Integer.valueOf(i));
                    } else {
                        if (proposedTransaction.useForkId) {
                            sha256Hash = transaction.hashForSignatureWitness(i, connectedRedeemData.redeemScript, transaction.getInput(j).getConnectedOutput().getValue(), SigHash.ALL, false);
                        } else {
                            sha256Hash = transaction.hashForSignature(i, connectedRedeemData.redeemScript, SigHash.ALL, false);
                        }
                        SignatureAndKey signature = getSignature(sha256Hash, (List) proposedTransaction.keyPaths.get(scriptPubKey));
                        TransactionSignature transactionSignature = new TransactionSignature(signature.sig, SigHash.ALL, false, proposedTransaction.useForkId);
                        input.setScriptSig(scriptPubKey.getScriptSigWithSignature(script, transactionSignature.encodeToBitcoin(), script.getSigInsertionIndex(sha256Hash, signature.pubKey)));
                    }
                }
            }
        }
        return true;
    }
}
