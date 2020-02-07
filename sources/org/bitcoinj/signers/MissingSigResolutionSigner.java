package org.bitcoinj.signers;

import org.bitcoinj.core.ECKey.MissingPrivateKeyException;
import org.bitcoinj.core.TransactionInput;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptChunk;
import org.bitcoinj.signers.TransactionSigner.MissingSignatureException;
import org.bitcoinj.signers.TransactionSigner.ProposedTransaction;
import org.bitcoinj.wallet.C3530Wallet.MissingSigsMode;
import org.bitcoinj.wallet.KeyBag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MissingSigResolutionSigner extends StatelessTransactionSigner {
    private static final Logger log = LoggerFactory.getLogger(MissingSigResolutionSigner.class);
    public MissingSigsMode missingSigsMode = MissingSigsMode.USE_DUMMY_SIG;

    public boolean isReady() {
        return true;
    }

    public MissingSigResolutionSigner() {
    }

    public MissingSigResolutionSigner(MissingSigsMode missingSigsMode2) {
        this.missingSigsMode = missingSigsMode2;
    }

    public boolean signInputs(ProposedTransaction proposedTransaction, KeyBag keyBag) {
        if (this.missingSigsMode == MissingSigsMode.USE_OP_ZERO) {
            return true;
        }
        int size = proposedTransaction.partialTx.getInputs().size();
        byte[] encodeToBitcoin = TransactionSignature.dummy().encodeToBitcoin();
        for (int i = 0; i < size; i++) {
            TransactionInput input = proposedTransaction.partialTx.getInput((long) i);
            if (input.getConnectedOutput() == null) {
                log.warn("Missing connected output, assuming input {} is already signed.", (Object) Integer.valueOf(i));
            } else {
                Script scriptPubKey = input.getConnectedOutput().getScriptPubKey();
                Script scriptSig = input.getScriptSig();
                if (scriptPubKey.isPayToScriptHash() || scriptPubKey.isSentToMultiSig()) {
                    boolean isPayToScriptHash = scriptPubKey.isPayToScriptHash();
                    for (int i2 = 1; i2 < scriptSig.getChunks().size() - (isPayToScriptHash ? 1 : 0); i2++) {
                        if (((ScriptChunk) scriptSig.getChunks().get(i2)).equalsOpCode(0)) {
                            if (this.missingSigsMode == MissingSigsMode.THROW) {
                                throw new MissingSignatureException();
                            } else if (this.missingSigsMode == MissingSigsMode.USE_DUMMY_SIG) {
                                input.setScriptSig(scriptPubKey.getScriptSigWithSignature(scriptSig, encodeToBitcoin, i2 - 1));
                            }
                        }
                    }
                    continue;
                } else if (!((ScriptChunk) scriptSig.getChunks().get(0)).equalsOpCode(0)) {
                    continue;
                } else if (this.missingSigsMode == MissingSigsMode.THROW) {
                    throw new MissingPrivateKeyException();
                } else if (this.missingSigsMode == MissingSigsMode.USE_DUMMY_SIG) {
                    input.setScriptSig(scriptPubKey.getScriptSigWithSignature(scriptSig, encodeToBitcoin, 0));
                }
            }
        }
        return true;
    }
}
