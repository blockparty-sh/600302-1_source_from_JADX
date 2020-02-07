package org.bitcoinj.core;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.annotation.Nullable;
import org.bitcoinj.core.TransactionConfidence.ConfidenceType;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.util.Arrays;

public class TransactionOutput extends ChildMessage {
    private static final Logger log = LoggerFactory.getLogger(TransactionOutput.class);
    private boolean availableForSpending;
    private byte[] scriptBytes;
    private int scriptLen;
    private Script scriptPubKey;
    @Nullable
    private TransactionInput spentBy;
    private long value;

    public TransactionOutput(NetworkParameters networkParameters, @Nullable Transaction transaction, byte[] bArr, int i) throws ProtocolException {
        super(networkParameters, bArr, i);
        setParent(transaction);
        this.availableForSpending = true;
    }

    public TransactionOutput(NetworkParameters networkParameters, @Nullable Transaction transaction, byte[] bArr, int i, MessageSerializer messageSerializer) throws ProtocolException {
        super(networkParameters, bArr, i, transaction, messageSerializer, Integer.MIN_VALUE);
        this.availableForSpending = true;
    }

    public TransactionOutput(NetworkParameters networkParameters, @Nullable Transaction transaction, Coin coin, Address address) {
        this(networkParameters, transaction, coin, ScriptBuilder.createOutputScript(address).getProgram());
    }

    public TransactionOutput(NetworkParameters networkParameters, @Nullable Transaction transaction, Coin coin, ECKey eCKey) {
        this(networkParameters, transaction, coin, ScriptBuilder.createOutputScript(eCKey).getProgram());
    }

    public TransactionOutput(NetworkParameters networkParameters, @Nullable Transaction transaction, Coin coin, byte[] bArr) {
        super(networkParameters);
        boolean z = false;
        Preconditions.checkArgument(coin.signum() >= 0 || coin.equals(Coin.NEGATIVE_SATOSHI), "Negative values not allowed");
        if (!networkParameters.hasMaxMoney() || coin.compareTo(networkParameters.getMaxMoney()) <= 0) {
            z = true;
        }
        Preconditions.checkArgument(z, "Values larger than MAX_MONEY not allowed");
        this.value = coin.value;
        this.scriptBytes = bArr;
        setParent(transaction);
        this.availableForSpending = true;
        this.length = VarInt.sizeOf((long) bArr.length) + 8 + bArr.length;
    }

    public Script getScriptPubKey() throws ScriptException {
        if (this.scriptPubKey == null) {
            this.scriptPubKey = new Script(this.scriptBytes);
        }
        return this.scriptPubKey;
    }

    @Nullable
    public Address getAddressFromP2PKHScript(NetworkParameters networkParameters) throws ScriptException {
        if (getScriptPubKey().isSentToAddress()) {
            return getScriptPubKey().getToAddress(networkParameters);
        }
        return null;
    }

    @Nullable
    public Address getAddressFromP2SH(NetworkParameters networkParameters) throws ScriptException {
        if (getScriptPubKey().isPayToScriptHash()) {
            return getScriptPubKey().getToAddress(networkParameters);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        this.value = readInt64();
        this.scriptLen = (int) readVarInt();
        int i = this.cursor - this.offset;
        int i2 = this.scriptLen;
        this.length = i + i2;
        this.scriptBytes = readBytes(i2);
    }

    /* access modifiers changed from: protected */
    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        Preconditions.checkNotNull(this.scriptBytes);
        C3447Utils.int64ToByteStreamLE(this.value, outputStream);
        outputStream.write(new VarInt((long) this.scriptBytes.length).encode());
        outputStream.write(this.scriptBytes);
    }

    public Coin getValue() {
        try {
            return Coin.valueOf(this.value);
        } catch (IllegalArgumentException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public void setValue(Coin coin) {
        Preconditions.checkNotNull(coin);
        unCache();
        this.value = coin.value;
    }

    public int getIndex() {
        List outputs = getParentTransaction().getOutputs();
        for (int i = 0; i < outputs.size(); i++) {
            if (outputs.get(i) == this) {
                return i;
            }
        }
        throw new IllegalStateException("Output linked to wrong parent transaction?");
    }

    public boolean isDust() {
        if (getScriptPubKey().isOpReturn()) {
            return false;
        }
        return getValue().isLessThan(getMinNonDustValue());
    }

    public Coin getMinNonDustValue(Coin coin) {
        return coin.multiply((long) (unsafeBitcoinSerialize().length + 148)).divide(1000);
    }

    public Coin getMinNonDustValue() {
        return getMinNonDustValue(Transaction.REFERENCE_DEFAULT_MIN_TX_FEE.multiply(3));
    }

    public void markAsSpent(TransactionInput transactionInput) {
        Preconditions.checkState(this.availableForSpending);
        this.availableForSpending = false;
        this.spentBy = transactionInput;
        if (this.parent == null) {
            return;
        }
        if (log.isDebugEnabled()) {
            log.debug("Marked {}:{} as spent by {}", getParentTransactionHash(), Integer.valueOf(getIndex()), transactionInput);
        } else if (log.isDebugEnabled()) {
            log.debug("Marked floating output as spent by {}", (Object) transactionInput);
        }
    }

    public void markAsUnspent() {
        if (this.parent != null) {
            if (log.isDebugEnabled()) {
                log.debug("Un-marked {}:{} as spent by {}", getParentTransactionHash(), Integer.valueOf(getIndex()), this.spentBy);
            } else if (log.isDebugEnabled()) {
                log.debug("Un-marked floating output as spent by {}", (Object) this.spentBy);
            }
        }
        this.availableForSpending = true;
        this.spentBy = null;
    }

    public boolean isAvailableForSpending() {
        return this.availableForSpending;
    }

    public byte[] getScriptBytes() {
        return this.scriptBytes;
    }

    public boolean isMineOrWatched(TransactionBag transactionBag) {
        return isMine(transactionBag) || isWatched(transactionBag);
    }

    public boolean isWatched(TransactionBag transactionBag) {
        try {
            return transactionBag.isWatchedScript(getScriptPubKey());
        } catch (ScriptException e) {
            log.debug("Could not parse tx output script: {}", (Object) e.toString());
            return false;
        }
    }

    public boolean isMine(TransactionBag transactionBag) {
        try {
            Script scriptPubKey2 = getScriptPubKey();
            if (scriptPubKey2.isSentToRawPubKey()) {
                return transactionBag.isPubKeyMine(scriptPubKey2.getPubKey());
            }
            if (scriptPubKey2.isPayToScriptHash()) {
                return transactionBag.isPayToScriptHashMine(scriptPubKey2.getPubKeyHash());
            }
            return transactionBag.isPubKeyHashMine(scriptPubKey2.getPubKeyHash());
        } catch (ScriptException e) {
            log.debug("Could not parse tx {} output script: {}", this.parent != null ? this.parent.getHash() : "(no parent)", (Object) e.toString());
            return false;
        }
    }

    public String toString() {
        try {
            Script scriptPubKey2 = getScriptPubKey();
            StringBuilder sb = new StringBuilder("TxOut of ");
            sb.append(Coin.valueOf(this.value).toFriendlyString());
            if (!scriptPubKey2.isSentToAddress()) {
                if (!scriptPubKey2.isPayToScriptHash()) {
                    if (scriptPubKey2.isSentToRawPubKey()) {
                        sb.append(" to pubkey ");
                        sb.append(C3447Utils.HEX.encode(scriptPubKey2.getPubKey()));
                    } else if (scriptPubKey2.isSentToMultiSig()) {
                        sb.append(" to multisig");
                    } else {
                        sb.append(" (unknown type)");
                    }
                    sb.append(" script:");
                    sb.append(scriptPubKey2);
                    return sb.toString();
                }
            }
            sb.append(" to ");
            sb.append(scriptPubKey2.getToAddress(this.params));
            sb.append(" script:");
            sb.append(scriptPubKey2);
            return sb.toString();
        } catch (ScriptException e) {
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public TransactionInput getSpentBy() {
        return this.spentBy;
    }

    @Nullable
    public Transaction getParentTransaction() {
        return (Transaction) this.parent;
    }

    @Nullable
    public Sha256Hash getParentTransactionHash() {
        if (this.parent == null) {
            return null;
        }
        return this.parent.getHash();
    }

    public int getParentTransactionDepthInBlocks() {
        if (getParentTransaction() != null) {
            TransactionConfidence confidence = getParentTransaction().getConfidence();
            if (confidence.getConfidenceType() == ConfidenceType.BUILDING) {
                return confidence.getDepthInBlocks();
            }
        }
        return -1;
    }

    public TransactionOutPoint getOutPointFor() {
        return new TransactionOutPoint(this.params, (long) getIndex(), getParentTransaction());
    }

    public TransactionOutput duplicateDetached() {
        return new TransactionOutput(this.params, (Transaction) null, Coin.valueOf(this.value), Arrays.clone(this.scriptBytes));
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TransactionOutput transactionOutput = (TransactionOutput) obj;
        if (this.value != transactionOutput.value || (!(this.parent == null || (this.parent == transactionOutput.parent && getIndex() == transactionOutput.getIndex())) || !java.util.Arrays.equals(this.scriptBytes, transactionOutput.scriptBytes))) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.value), this.parent, Integer.valueOf(java.util.Arrays.hashCode(this.scriptBytes)));
    }
}
