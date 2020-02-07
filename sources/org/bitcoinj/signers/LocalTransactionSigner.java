package org.bitcoinj.signers;

import java.util.EnumSet;
import org.bitcoinj.script.Script.VerifyFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalTransactionSigner extends StatelessTransactionSigner {
    private static final EnumSet<VerifyFlag> MINIMUM_VERIFY_FLAGS = EnumSet.of(VerifyFlag.P2SH, VerifyFlag.NULLDUMMY);
    private static final Logger log = LoggerFactory.getLogger(LocalTransactionSigner.class);

    public boolean isReady() {
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00da, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00db, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00da A[ExcHandler: KeyIsEncryptedException (r0v2 'e' org.bitcoinj.core.ECKey$KeyIsEncryptedException A[CUSTOM_DECLARE]), Splitter:B:17:0x0093] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean signInputs(org.bitcoinj.signers.TransactionSigner.ProposedTransaction r17, org.bitcoinj.wallet.KeyBag r18) {
        /*
            r16 = this;
            r0 = r17
            org.bitcoinj.core.Transaction r8 = r0.partialTx
            java.util.List r1 = r8.getInputs()
            int r9 = r1.size()
            r10 = 0
            r11 = 0
        L_0x000e:
            if (r11 >= r9) goto L_0x00dc
            long r12 = (long) r11
            org.bitcoinj.core.TransactionInput r14 = r8.getInput(r12)
            org.bitcoinj.core.TransactionOutput r1 = r14.getConnectedOutput()
            if (r1 != 0) goto L_0x002b
            org.slf4j.Logger r1 = log
            java.lang.Integer r2 = java.lang.Integer.valueOf(r11)
            java.lang.String r3 = "Missing connected output, assuming input {} is already signed."
            r1.warn(r3, r2)
        L_0x0026:
            r15 = r18
        L_0x0028:
            r2 = 0
            goto L_0x00d5
        L_0x002b:
            org.bitcoinj.script.Script r1 = r14.getScriptSig()     // Catch:{ ScriptException -> 0x0052 }
            org.bitcoinj.core.TransactionOutput r2 = r14.getConnectedOutput()     // Catch:{ ScriptException -> 0x0052 }
            org.bitcoinj.script.Script r5 = r2.getScriptPubKey()     // Catch:{ ScriptException -> 0x0052 }
            org.bitcoinj.core.TransactionOutput r2 = r14.getConnectedOutput()     // Catch:{ ScriptException -> 0x0052 }
            org.bitcoinj.core.Coin r6 = r2.getValue()     // Catch:{ ScriptException -> 0x0052 }
            java.util.EnumSet<org.bitcoinj.script.Script$VerifyFlag> r7 = MINIMUM_VERIFY_FLAGS     // Catch:{ ScriptException -> 0x0052 }
            r2 = r8
            r3 = r12
            r1.correctlySpends(r2, r3, r5, r6, r7)     // Catch:{ ScriptException -> 0x0052 }
            org.slf4j.Logger r1 = log     // Catch:{ ScriptException -> 0x0052 }
            java.lang.String r2 = "Input {} already correctly spends output, assuming SIGHASH type used will be safe and skipping signing."
            java.lang.Integer r3 = java.lang.Integer.valueOf(r11)     // Catch:{ ScriptException -> 0x0052 }
            r1.warn(r2, r3)     // Catch:{ ScriptException -> 0x0052 }
            goto L_0x0026
        L_0x0052:
            r15 = r18
            org.bitcoinj.wallet.RedeemData r1 = r14.getConnectedRedeemData(r15)
            org.bitcoinj.core.TransactionOutput r2 = r14.getConnectedOutput()
            org.bitcoinj.script.Script r7 = r2.getScriptPubKey()
            java.util.List<org.bitcoinj.core.ECKey> r2 = r1.keys
            java.lang.Object r2 = r2.get(r10)
            org.bitcoinj.core.ECKey r2 = (org.bitcoinj.core.ECKey) r2
            boolean r3 = r2 instanceof org.bitcoinj.crypto.DeterministicKey
            if (r3 == 0) goto L_0x0077
            java.util.Map<org.bitcoinj.script.Script, java.util.List<org.bitcoinj.crypto.ChildNumber>> r3 = r0.keyPaths
            org.bitcoinj.crypto.DeterministicKey r2 = (org.bitcoinj.crypto.DeterministicKey) r2
            com.google.common.collect.ImmutableList r2 = r2.getPath()
            r3.put(r7, r2)
        L_0x0077:
            org.bitcoinj.core.ECKey r3 = r1.getFullKey()
            if (r3 != 0) goto L_0x0089
            org.slf4j.Logger r1 = log
            java.lang.Integer r2 = java.lang.Integer.valueOf(r11)
            java.lang.String r3 = "No local key found for input {}"
            r1.warn(r3, r2)
            goto L_0x0028
        L_0x0089:
            org.bitcoinj.script.Script r6 = r14.getScriptSig()
            org.bitcoinj.script.Script r1 = r1.redeemScript
            byte[] r4 = r1.getProgram()
            boolean r1 = r0.useForkId     // Catch:{ KeyIsEncryptedException -> 0x00da, MissingPrivateKeyException -> 0x00c9 }
            if (r1 == 0) goto L_0x00b1
            org.bitcoinj.core.TransactionInput r1 = r8.getInput(r12)     // Catch:{ KeyIsEncryptedException -> 0x00da, MissingPrivateKeyException -> 0x00c9 }
            org.bitcoinj.core.TransactionOutput r1 = r1.getConnectedOutput()     // Catch:{ KeyIsEncryptedException -> 0x00da, MissingPrivateKeyException -> 0x00c9 }
            org.bitcoinj.core.Coin r5 = r1.getValue()     // Catch:{ KeyIsEncryptedException -> 0x00da, MissingPrivateKeyException -> 0x00c9 }
            org.bitcoinj.core.Transaction$SigHash r12 = org.bitcoinj.core.Transaction.SigHash.ALL     // Catch:{ KeyIsEncryptedException -> 0x00da, MissingPrivateKeyException -> 0x00c9 }
            r13 = 0
            r1 = r8
            r2 = r11
            r10 = r6
            r6 = r12
            r12 = r7
            r7 = r13
            org.bitcoinj.crypto.TransactionSignature r1 = r1.calculateWitnessSignature(r2, r3, r4, r5, r6, r7)     // Catch:{ KeyIsEncryptedException -> 0x00da, MissingPrivateKeyException -> 0x00c9 }
            goto L_0x00bc
        L_0x00b1:
            r10 = r6
            r12 = r7
            org.bitcoinj.core.Transaction$SigHash r5 = org.bitcoinj.core.Transaction.SigHash.ALL     // Catch:{ KeyIsEncryptedException -> 0x00da, MissingPrivateKeyException -> 0x00c9 }
            r6 = 0
            r1 = r8
            r2 = r11
            org.bitcoinj.crypto.TransactionSignature r1 = r1.calculateSignature(r2, r3, r4, r5, r6)     // Catch:{ KeyIsEncryptedException -> 0x00da, MissingPrivateKeyException -> 0x00c9 }
        L_0x00bc:
            byte[] r1 = r1.encodeToBitcoin()     // Catch:{ KeyIsEncryptedException -> 0x00da, MissingPrivateKeyException -> 0x00c9 }
            r2 = 0
            org.bitcoinj.script.Script r1 = r12.getScriptSigWithSignature(r10, r1, r2)     // Catch:{ KeyIsEncryptedException -> 0x00da, MissingPrivateKeyException -> 0x00ca }
            r14.setScriptSig(r1)     // Catch:{ KeyIsEncryptedException -> 0x00da, MissingPrivateKeyException -> 0x00ca }
            goto L_0x00d5
        L_0x00c9:
            r2 = 0
        L_0x00ca:
            org.slf4j.Logger r1 = log
            java.lang.Integer r3 = java.lang.Integer.valueOf(r11)
            java.lang.String r4 = "No private key in keypair for input {}"
            r1.warn(r4, r3)
        L_0x00d5:
            int r11 = r11 + 1
            r10 = 0
            goto L_0x000e
        L_0x00da:
            r0 = move-exception
            throw r0
        L_0x00dc:
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.signers.LocalTransactionSigner.signInputs(org.bitcoinj.signers.TransactionSigner$ProposedTransaction, org.bitcoinj.wallet.KeyBag):boolean");
    }
}
