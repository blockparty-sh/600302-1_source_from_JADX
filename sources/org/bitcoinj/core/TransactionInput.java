package org.bitcoinj.core;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.Map;
import javax.annotation.Nullable;
import org.bitcoinj.script.Script;
import org.bitcoinj.wallet.DefaultRiskAnalysis;
import org.bitcoinj.wallet.DefaultRiskAnalysis.RuleViolation;
import org.bitcoinj.wallet.KeyBag;
import org.bitcoinj.wallet.RedeemData;

public class TransactionInput extends ChildMessage {
    private static final byte[] EMPTY_ARRAY = new byte[0];
    public static final long NO_SEQUENCE = 4294967295L;
    private static final long UNCONNECTED = 4294967295L;
    private TransactionOutPoint outpoint;
    private byte[] scriptBytes;
    private WeakReference<Script> scriptSig;
    private long sequence;
    @Nullable
    private Coin value;

    public enum ConnectMode {
        DISCONNECT_ON_CONFLICT,
        ABORT_ON_CONFLICT
    }

    public enum ConnectionResult {
        NO_SUCH_TX,
        ALREADY_SPENT,
        SUCCESS
    }

    public TransactionInput(NetworkParameters networkParameters, @Nullable Transaction transaction, byte[] bArr) {
        this(networkParameters, transaction, bArr, new TransactionOutPoint(networkParameters, 4294967295L, (Transaction) null));
    }

    public TransactionInput(NetworkParameters networkParameters, @Nullable Transaction transaction, byte[] bArr, TransactionOutPoint transactionOutPoint) {
        this(networkParameters, transaction, bArr, transactionOutPoint, (Coin) null);
    }

    public TransactionInput(NetworkParameters networkParameters, @Nullable Transaction transaction, byte[] bArr, TransactionOutPoint transactionOutPoint, @Nullable Coin coin) {
        int i;
        super(networkParameters);
        this.scriptBytes = bArr;
        this.outpoint = transactionOutPoint;
        this.sequence = 4294967295L;
        this.value = coin;
        setParent(transaction);
        if (bArr == null) {
            i = 1;
        } else {
            i = VarInt.sizeOf((long) bArr.length) + bArr.length;
        }
        this.length = i + 40;
    }

    TransactionInput(NetworkParameters networkParameters, Transaction transaction, TransactionOutput transactionOutput) {
        super(networkParameters);
        long index = (long) transactionOutput.getIndex();
        if (transactionOutput.getParentTransaction() != null) {
            this.outpoint = new TransactionOutPoint(networkParameters, index, transactionOutput.getParentTransaction());
        } else {
            this.outpoint = new TransactionOutPoint(networkParameters, transactionOutput);
        }
        this.scriptBytes = EMPTY_ARRAY;
        this.sequence = 4294967295L;
        setParent(transaction);
        this.value = transactionOutput.getValue();
        this.length = 41;
    }

    public TransactionInput(NetworkParameters networkParameters, @Nullable Transaction transaction, byte[] bArr, int i) throws ProtocolException {
        super(networkParameters, bArr, i);
        setParent(transaction);
        this.value = null;
    }

    public TransactionInput(NetworkParameters networkParameters, Transaction transaction, byte[] bArr, int i, MessageSerializer messageSerializer) throws ProtocolException {
        super(networkParameters, bArr, i, transaction, messageSerializer, Integer.MIN_VALUE);
        this.value = null;
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        TransactionOutPoint transactionOutPoint = new TransactionOutPoint(this.params, this.payload, this.cursor, this, this.serializer);
        this.outpoint = transactionOutPoint;
        this.cursor += this.outpoint.getMessageSize();
        int readVarInt = (int) readVarInt();
        this.length = (this.cursor - this.offset) + readVarInt + 4;
        this.scriptBytes = readBytes(readVarInt);
        this.sequence = readUint32();
    }

    /* access modifiers changed from: protected */
    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        this.outpoint.bitcoinSerialize(outputStream);
        outputStream.write(new VarInt((long) this.scriptBytes.length).encode());
        outputStream.write(this.scriptBytes);
        C3447Utils.uint32ToByteStreamLE(this.sequence, outputStream);
    }

    public boolean isCoinBase() {
        return this.outpoint.getHash().equals(Sha256Hash.ZERO_HASH) && (this.outpoint.getIndex() & 4294967295L) == 4294967295L;
    }

    public Script getScriptSig() throws ScriptException {
        WeakReference<Script> weakReference = this.scriptSig;
        Script script = weakReference == null ? null : (Script) weakReference.get();
        if (script != null) {
            return script;
        }
        Script script2 = new Script(this.scriptBytes);
        this.scriptSig = new WeakReference<>(script2);
        return script2;
    }

    public void setScriptSig(Script script) {
        this.scriptSig = new WeakReference<>(Preconditions.checkNotNull(script));
        setScriptBytes(script.getProgram());
    }

    @Deprecated
    public Address getFromAddress() throws ScriptException {
        if (!isCoinBase()) {
            return getScriptSig().getFromAddress(this.params);
        }
        throw new ScriptException("This is a coinbase transaction which generates new coins. It does not have a from address.");
    }

    public long getSequenceNumber() {
        return this.sequence;
    }

    public void setSequenceNumber(long j) {
        unCache();
        this.sequence = j;
    }

    public TransactionOutPoint getOutpoint() {
        return this.outpoint;
    }

    public byte[] getScriptBytes() {
        return this.scriptBytes;
    }

    public void clearScriptBytes() {
        setScriptBytes(EMPTY_ARRAY);
    }

    /* access modifiers changed from: 0000 */
    public void setScriptBytes(byte[] bArr) {
        int i;
        unCache();
        this.scriptSig = null;
        int i2 = this.length;
        this.scriptBytes = bArr;
        if (bArr == null) {
            i = 1;
        } else {
            i = bArr.length + VarInt.sizeOf((long) bArr.length);
        }
        adjustLength((i + 40) - i2);
    }

    public Transaction getParentTransaction() {
        return (Transaction) this.parent;
    }

    @Nullable
    public Coin getValue() {
        return this.value;
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public TransactionOutput getConnectedOutput(Map<Sha256Hash, Transaction> map) {
        Transaction transaction = (Transaction) map.get(this.outpoint.getHash());
        if (transaction == null) {
            return null;
        }
        return (TransactionOutput) transaction.getOutputs().get((int) this.outpoint.getIndex());
    }

    @Nullable
    public RedeemData getConnectedRedeemData(KeyBag keyBag) throws ScriptException {
        return getOutpoint().getConnectedRedeemData(keyBag);
    }

    public ConnectionResult connect(Map<Sha256Hash, Transaction> map, ConnectMode connectMode) {
        Transaction transaction = (Transaction) map.get(this.outpoint.getHash());
        if (transaction == null) {
            return ConnectionResult.NO_SUCH_TX;
        }
        return connect(transaction, connectMode);
    }

    public ConnectionResult connect(Transaction transaction, ConnectMode connectMode) {
        if (!transaction.getHash().equals(this.outpoint.getHash())) {
            return ConnectionResult.NO_SUCH_TX;
        }
        Preconditions.checkElementIndex((int) this.outpoint.getIndex(), transaction.getOutputs().size(), "Corrupt transaction");
        TransactionOutput output = transaction.getOutput((long) ((int) this.outpoint.getIndex()));
        if (!output.isAvailableForSpending()) {
            if (getParentTransaction().equals(this.outpoint.fromTx)) {
                return ConnectionResult.SUCCESS;
            }
            if (connectMode == ConnectMode.DISCONNECT_ON_CONFLICT) {
                output.markAsUnspent();
            } else if (connectMode == ConnectMode.ABORT_ON_CONFLICT) {
                this.outpoint.fromTx = output.getParentTransaction();
                return ConnectionResult.ALREADY_SPENT;
            }
        }
        connect(output);
        return ConnectionResult.SUCCESS;
    }

    public void connect(TransactionOutput transactionOutput) {
        this.outpoint.fromTx = transactionOutput.getParentTransaction();
        transactionOutput.markAsSpent(this);
        this.value = transactionOutput.getValue();
    }

    public boolean disconnect() {
        if (this.outpoint.fromTx == null) {
            return false;
        }
        TransactionOutput output = this.outpoint.fromTx.getOutput((long) ((int) this.outpoint.getIndex()));
        if (output.getSpentBy() != this) {
            return false;
        }
        output.markAsUnspent();
        this.outpoint.fromTx = null;
        return true;
    }

    public boolean hasSequence() {
        return this.sequence != 4294967295L;
    }

    public boolean isOptInFullRBF() {
        return this.sequence < 4294967294L;
    }

    public void verify() throws VerificationException {
        Transaction transaction = getOutpoint().fromTx;
        long index = getOutpoint().getIndex();
        Preconditions.checkNotNull(transaction, "Not connected");
        verify(transaction.getOutput((long) ((int) index)));
    }

    public void verify(TransactionOutput transactionOutput) throws VerificationException {
        if (transactionOutput.parent != null) {
            if (!getOutpoint().getHash().equals(transactionOutput.getParentTransaction().getHash())) {
                throw new VerificationException("This input does not refer to the tx containing the output.");
            } else if (getOutpoint().getIndex() != ((long) transactionOutput.getIndex())) {
                throw new VerificationException("This input refers to a different output on the given tx.");
            }
        }
        getScriptSig().correctlySpends(getParentTransaction(), (long) getParentTransaction().getInputs().indexOf(this), transactionOutput.getScriptPubKey());
    }

    @Nullable
    public TransactionOutput getConnectedOutput() {
        return getOutpoint().getConnectedOutput();
    }

    @Nullable
    public Transaction getConnectedTransaction() {
        return getOutpoint().fromTx;
    }

    public TransactionInput duplicateDetached() {
        return new TransactionInput(this.params, (Transaction) null, bitcoinSerialize(), 0);
    }

    public RuleViolation isStandard() {
        return DefaultRiskAnalysis.isInputStandard(this);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TransactionInput transactionInput = (TransactionInput) obj;
        if (this.sequence != transactionInput.sequence || this.parent != transactionInput.parent || !this.outpoint.equals(transactionInput.outpoint) || !Arrays.equals(this.scriptBytes, transactionInput.scriptBytes)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.sequence), this.outpoint, Integer.valueOf(Arrays.hashCode(this.scriptBytes)));
    }

    public String toString() {
        Object obj;
        StringBuilder sb = new StringBuilder("TxIn");
        try {
            if (isCoinBase()) {
                sb.append(": COINBASE");
            } else {
                sb.append(" for [");
                sb.append(this.outpoint);
                sb.append("]: ");
                sb.append(getScriptSig());
                Joiner skipNulls = Joiner.m96on(", ").skipNulls();
                String str = null;
                if (hasSequence()) {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("sequence: ");
                    sb2.append(Long.toHexString(this.sequence));
                    obj = sb2.toString();
                } else {
                    obj = null;
                }
                if (isOptInFullRBF()) {
                    str = "opts into full RBF";
                }
                String join = skipNulls.join(obj, str, new Object[0]);
                if (!join.isEmpty()) {
                    sb.append(" (");
                    sb.append(join);
                    sb.append(')');
                }
            }
            return sb.toString();
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }
}
