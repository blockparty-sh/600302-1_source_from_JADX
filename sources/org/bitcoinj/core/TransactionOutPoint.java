package org.bitcoinj.core;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.microsoft.appcenter.Constants;
import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.Nullable;
import org.bitcoinj.script.Script;
import org.bitcoinj.wallet.KeyBag;
import org.bitcoinj.wallet.RedeemData;

public class TransactionOutPoint extends ChildMessage {
    static final int MESSAGE_LENGTH = 36;
    private TransactionOutput connectedOutput;
    Transaction fromTx;
    private Sha256Hash hash;
    private long index;

    public TransactionOutPoint(NetworkParameters networkParameters, long j, @Nullable Transaction transaction) {
        super(networkParameters);
        this.index = j;
        if (transaction != null) {
            this.hash = transaction.getHash();
            this.fromTx = transaction;
        } else {
            this.hash = Sha256Hash.ZERO_HASH;
        }
        this.length = 36;
    }

    public TransactionOutPoint(NetworkParameters networkParameters, long j, Sha256Hash sha256Hash) {
        super(networkParameters);
        this.index = j;
        this.hash = sha256Hash;
        this.length = 36;
    }

    public TransactionOutPoint(NetworkParameters networkParameters, TransactionOutput transactionOutput) {
        this(networkParameters, (long) transactionOutput.getIndex(), transactionOutput.getParentTransactionHash());
        this.connectedOutput = transactionOutput;
    }

    public TransactionOutPoint(NetworkParameters networkParameters, byte[] bArr, int i) throws ProtocolException {
        super(networkParameters, bArr, i);
    }

    public TransactionOutPoint(NetworkParameters networkParameters, byte[] bArr, int i, Message message, MessageSerializer messageSerializer) throws ProtocolException {
        super(networkParameters, bArr, i, message, messageSerializer, 36);
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        this.length = 36;
        this.hash = readHash();
        this.index = readUint32();
    }

    /* access modifiers changed from: protected */
    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        outputStream.write(this.hash.getReversedBytes());
        C3447Utils.uint32ToByteStreamLE(this.index, outputStream);
    }

    @Nullable
    public TransactionOutput getConnectedOutput() {
        Transaction transaction = this.fromTx;
        if (transaction != null) {
            return (TransactionOutput) transaction.getOutputs().get((int) this.index);
        }
        TransactionOutput transactionOutput = this.connectedOutput;
        if (transactionOutput != null) {
            return transactionOutput;
        }
        return null;
    }

    public byte[] getConnectedPubKeyScript() {
        byte[] scriptBytes = ((TransactionOutput) Preconditions.checkNotNull(getConnectedOutput())).getScriptBytes();
        Preconditions.checkState(scriptBytes.length > 0);
        return scriptBytes;
    }

    @Nullable
    public ECKey getConnectedKey(KeyBag keyBag) throws ScriptException {
        TransactionOutput connectedOutput2 = getConnectedOutput();
        Preconditions.checkNotNull(connectedOutput2, "Input is not connected so cannot retrieve key");
        Script scriptPubKey = connectedOutput2.getScriptPubKey();
        if (scriptPubKey.isSentToAddress()) {
            return keyBag.findKeyFromPubHash(scriptPubKey.getPubKeyHash());
        }
        if (scriptPubKey.isSentToRawPubKey()) {
            return keyBag.findKeyFromPubKey(scriptPubKey.getPubKey());
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Could not understand form of connected output script: ");
        sb.append(scriptPubKey);
        throw new ScriptException(sb.toString());
    }

    @Nullable
    public RedeemData getConnectedRedeemData(KeyBag keyBag) throws ScriptException {
        TransactionOutput connectedOutput2 = getConnectedOutput();
        Preconditions.checkNotNull(connectedOutput2, "Input is not connected so cannot retrieve key");
        Script scriptPubKey = connectedOutput2.getScriptPubKey();
        if (scriptPubKey.isSentToAddress()) {
            return RedeemData.m344of(keyBag.findKeyFromPubHash(scriptPubKey.getPubKeyHash()), scriptPubKey);
        }
        if (scriptPubKey.isSentToRawPubKey()) {
            return RedeemData.m344of(keyBag.findKeyFromPubKey(scriptPubKey.getPubKey()), scriptPubKey);
        }
        if (scriptPubKey.isPayToScriptHash()) {
            return keyBag.findRedeemDataFromScriptHash(scriptPubKey.getPubKeyHash());
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Could not understand form of connected output script: ");
        sb.append(scriptPubKey);
        throw new ScriptException(sb.toString());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.hash);
        sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
        sb.append(this.index);
        return sb.toString();
    }

    public Sha256Hash getHash() {
        return this.hash;
    }

    /* access modifiers changed from: 0000 */
    public void setHash(Sha256Hash sha256Hash) {
        this.hash = sha256Hash;
    }

    public long getIndex() {
        return this.index;
    }

    public void setIndex(long j) {
        this.index = j;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TransactionOutPoint transactionOutPoint = (TransactionOutPoint) obj;
        if (getIndex() != transactionOutPoint.getIndex() || !getHash().equals(transactionOutPoint.getHash())) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(getIndex()), getHash());
    }
}
