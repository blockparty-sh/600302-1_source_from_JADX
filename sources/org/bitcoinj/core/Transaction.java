package org.bitcoinj.core;

import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.Nullable;
import org.bitcoinj.core.TransactionConfidence.ConfidenceType;
import org.bitcoinj.core.VerificationException.CoinbaseHeightMismatch;
import org.bitcoinj.core.VerificationException.CoinbaseScriptSizeOutOfRange;
import org.bitcoinj.core.VerificationException.DuplicatedOutPoint;
import org.bitcoinj.core.VerificationException.EmptyInputsOrOutputs;
import org.bitcoinj.core.VerificationException.ExcessiveValue;
import org.bitcoinj.core.VerificationException.LargerThanMaxBlockSize;
import org.bitcoinj.core.VerificationException.NegativeValueOutput;
import org.bitcoinj.core.VerificationException.UnexpectedCoinbaseInput;
import org.bitcoinj.crypto.TransactionSignature;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.bitcoinj.script.ScriptOpCodes;
import org.bitcoinj.utils.ExchangeRate;
import org.bitcoinj.wallet.WalletTransaction.Pool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongycastle.crypto.params.KeyParameter;

public class Transaction extends ChildMessage {
    public static final int CURRENT_VERSION = 2;
    public static final Coin DEFAULT_TX_FEE = Coin.valueOf(5000);
    public static final int FORKID_VERSION = 2;
    public static final int LOCKTIME_THRESHOLD = 500000000;
    public static final BigInteger LOCKTIME_THRESHOLD_BIG = BigInteger.valueOf(500000000);
    public static final int MAX_STANDARD_TX_SIZE = 100000;
    public static final int MAX_STANDARD_VERSION = 2;
    public static final Coin MIN_NONDUST_OUTPUT = Coin.valueOf(546);
    public static final Coin REFERENCE_DEFAULT_MIN_TX_FEE = Coin.valueOf(1000);
    public static final byte SIGHASH_ANYONECANPAY_VALUE = Byte.MIN_VALUE;
    public static final Comparator<Transaction> SORT_TX_BY_HEIGHT = new Comparator<Transaction>() {
        public int compare(Transaction transaction, Transaction transaction2) {
            TransactionConfidence confidence = transaction.getConfidence();
            int i = -1;
            int appearedAtChainHeight = confidence.getConfidenceType() == ConfidenceType.BUILDING ? confidence.getAppearedAtChainHeight() : -1;
            TransactionConfidence confidence2 = transaction2.getConfidence();
            if (confidence2.getConfidenceType() == ConfidenceType.BUILDING) {
                i = confidence2.getAppearedAtChainHeight();
            }
            int i2 = -Ints.compare(appearedAtChainHeight, i);
            return i2 != 0 ? i2 : transaction.getHash().compareTo(transaction2.getHash());
        }
    };
    public static final Comparator<Transaction> SORT_TX_BY_UPDATE_TIME = new Comparator<Transaction>() {
        public int compare(Transaction transaction, Transaction transaction2) {
            int i = -Longs.compare(transaction.getUpdateTime().getTime(), transaction2.getUpdateTime().getTime());
            return i != 0 ? i : transaction.getHash().compareTo(transaction2.getHash());
        }
    };
    private static final Logger log = LoggerFactory.getLogger(Transaction.class);
    private Map<Sha256Hash, Integer> appearsInHashes;
    @Nullable
    private TransactionBag cachedForBag;
    @Nullable
    private Coin cachedValue;
    @Nullable
    private TransactionConfidence confidence;
    @Nullable
    private ExchangeRate exchangeRate;
    private Sha256Hash hash;
    private ArrayList<TransactionInput> inputs;
    private long lockTime;
    @Nullable
    private String memo;
    private int optimalEncodingMessageSize;
    private ArrayList<TransactionOutput> outputs;
    private Purpose purpose;
    private Date updatedAt;
    private long version;

    public enum Purpose {
        UNKNOWN,
        USER_PAYMENT,
        KEY_ROTATION,
        ASSURANCE_CONTRACT_CLAIM,
        ASSURANCE_CONTRACT_PLEDGE,
        ASSURANCE_CONTRACT_STUB,
        RAISE_FEE
    }

    public enum SigHash {
        ALL(1),
        NONE(2),
        SINGLE(3),
        FORKID(64),
        ANYONECANPAY(128),
        ANYONECANPAY_ALL(ScriptOpCodes.OP_RIGHT),
        ANYONECANPAY_NONE(ScriptOpCodes.OP_SIZE),
        ANYONECANPAY_SINGLE(ScriptOpCodes.OP_INVERT),
        UNSET(0);
        
        public final int value;

        private SigHash(int i) {
            this.value = i;
        }

        public byte byteValue() {
            return (byte) this.value;
        }
    }

    public Transaction(NetworkParameters networkParameters) {
        super(networkParameters);
        this.purpose = Purpose.UNKNOWN;
        this.version = 1;
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
        this.length = 8;
    }

    public Transaction(NetworkParameters networkParameters, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr, 0);
        this.purpose = Purpose.UNKNOWN;
    }

    public Transaction(NetworkParameters networkParameters, byte[] bArr, int i) throws ProtocolException {
        super(networkParameters, bArr, i);
        this.purpose = Purpose.UNKNOWN;
    }

    public Transaction(NetworkParameters networkParameters, byte[] bArr, int i, @Nullable Message message, MessageSerializer messageSerializer, int i2) throws ProtocolException {
        super(networkParameters, bArr, i, message, messageSerializer, i2);
        this.purpose = Purpose.UNKNOWN;
    }

    public Transaction(NetworkParameters networkParameters, byte[] bArr, @Nullable Message message, MessageSerializer messageSerializer, int i) throws ProtocolException {
        super(networkParameters, bArr, 0, message, messageSerializer, i);
        this.purpose = Purpose.UNKNOWN;
    }

    public Sha256Hash getHash() {
        if (this.hash == null) {
            this.hash = Sha256Hash.wrapReversed(Sha256Hash.hashTwice(unsafeBitcoinSerialize()));
        }
        return this.hash;
    }

    /* access modifiers changed from: 0000 */
    public void setHash(Sha256Hash sha256Hash) {
        this.hash = sha256Hash;
    }

    public String getHashAsString() {
        return getHash().toString();
    }

    public Coin getInputSum() {
        Coin coin = Coin.ZERO;
        Iterator it = this.inputs.iterator();
        while (it.hasNext()) {
            Coin value = ((TransactionInput) it.next()).getValue();
            if (value != null) {
                coin = coin.add(value);
            }
        }
        return coin;
    }

    public Coin getValueSentToMe(TransactionBag transactionBag) {
        Coin coin = Coin.ZERO;
        Iterator it = this.outputs.iterator();
        while (it.hasNext()) {
            TransactionOutput transactionOutput = (TransactionOutput) it.next();
            if (transactionOutput.isMineOrWatched(transactionBag)) {
                coin = coin.add(transactionOutput.getValue());
            }
        }
        return coin;
    }

    @Nullable
    public Map<Sha256Hash, Integer> getAppearsInHashes() {
        Map<Sha256Hash, Integer> map = this.appearsInHashes;
        if (map != null) {
            return ImmutableMap.copyOf(map);
        }
        return null;
    }

    public boolean isPending() {
        return getConfidence().getConfidenceType() == ConfidenceType.PENDING;
    }

    public void setBlockAppearance(StoredBlock storedBlock, boolean z, int i) {
        long timeSeconds = storedBlock.getHeader().getTimeSeconds() * 1000;
        if (z) {
            Date date = this.updatedAt;
            if (date == null || date.getTime() == 0 || this.updatedAt.getTime() > timeSeconds) {
                this.updatedAt = new Date(timeSeconds);
            }
        }
        addBlockAppearance(storedBlock.getHeader().getHash(), i);
        if (z) {
            getConfidence().setAppearedAtChainHeight(storedBlock.getHeight());
        }
    }

    public void addBlockAppearance(Sha256Hash sha256Hash, int i) {
        if (this.appearsInHashes == null) {
            this.appearsInHashes = new TreeMap();
        }
        this.appearsInHashes.put(sha256Hash, Integer.valueOf(i));
    }

    public Coin getValueSentFromMe(TransactionBag transactionBag) throws ScriptException {
        Coin coin = Coin.ZERO;
        Iterator it = this.inputs.iterator();
        while (it.hasNext()) {
            TransactionInput transactionInput = (TransactionInput) it.next();
            TransactionOutput connectedOutput = transactionInput.getConnectedOutput(transactionBag.getTransactionPool(Pool.UNSPENT));
            if (connectedOutput == null) {
                connectedOutput = transactionInput.getConnectedOutput(transactionBag.getTransactionPool(Pool.SPENT));
            }
            if (connectedOutput == null) {
                connectedOutput = transactionInput.getConnectedOutput(transactionBag.getTransactionPool(Pool.PENDING));
            }
            if (connectedOutput != null && connectedOutput.isMineOrWatched(transactionBag)) {
                coin = coin.add(connectedOutput.getValue());
            }
        }
        return coin;
    }

    public Coin getOutputSum() {
        Coin coin = Coin.ZERO;
        Iterator it = this.outputs.iterator();
        while (it.hasNext()) {
            coin = coin.add(((TransactionOutput) it.next()).getValue());
        }
        return coin;
    }

    public Coin getValue(TransactionBag transactionBag) throws ScriptException {
        boolean isAndroidRuntime = C3447Utils.isAndroidRuntime();
        if (isAndroidRuntime) {
            Coin coin = this.cachedValue;
            if (coin != null && this.cachedForBag == transactionBag) {
                return coin;
            }
        }
        Coin subtract = getValueSentToMe(transactionBag).subtract(getValueSentFromMe(transactionBag));
        if (isAndroidRuntime) {
            this.cachedValue = subtract;
            this.cachedForBag = transactionBag;
        }
        return subtract;
    }

    public Coin getFee() {
        Coin coin = Coin.ZERO;
        Iterator it = this.inputs.iterator();
        while (it.hasNext()) {
            TransactionInput transactionInput = (TransactionInput) it.next();
            if (transactionInput.getValue() == null) {
                return null;
            }
            coin = coin.add(transactionInput.getValue());
        }
        Iterator it2 = this.outputs.iterator();
        while (it2.hasNext()) {
            coin = coin.subtract(((TransactionOutput) it2.next()).getValue());
        }
        return coin;
    }

    public boolean isAnyOutputSpent() {
        Iterator it = this.outputs.iterator();
        while (it.hasNext()) {
            if (!((TransactionOutput) it.next()).isAvailableForSpending()) {
                return true;
            }
        }
        return false;
    }

    public boolean isEveryOwnedOutputSpent(TransactionBag transactionBag) {
        Iterator it = this.outputs.iterator();
        while (it.hasNext()) {
            TransactionOutput transactionOutput = (TransactionOutput) it.next();
            if (transactionOutput.isAvailableForSpending() && transactionOutput.isMineOrWatched(transactionBag)) {
                return false;
            }
        }
        return true;
    }

    public Date getUpdateTime() {
        if (this.updatedAt == null) {
            this.updatedAt = new Date(0);
        }
        return this.updatedAt;
    }

    public void setUpdateTime(Date date) {
        this.updatedAt = date;
    }

    /* access modifiers changed from: protected */
    public void unCache() {
        super.unCache();
        this.hash = null;
    }

    protected static int calcLength(byte[] bArr, int i) {
        int i2 = i + 4;
        VarInt varInt = new VarInt(bArr, i2);
        long j = varInt.value;
        int originalSizeInBytes = i2 + varInt.getOriginalSizeInBytes();
        int i3 = originalSizeInBytes;
        for (int i4 = 0; ((long) i4) < j; i4++) {
            int i5 = i3 + 36;
            VarInt varInt2 = new VarInt(bArr, i5);
            i3 = (int) (((long) i5) + varInt2.value + 4 + ((long) varInt2.getOriginalSizeInBytes()));
        }
        VarInt varInt3 = new VarInt(bArr, i3);
        long j2 = varInt3.value;
        int originalSizeInBytes2 = i3 + varInt3.getOriginalSizeInBytes();
        for (int i6 = 0; ((long) i6) < j2; i6++) {
            int i7 = originalSizeInBytes2 + 8;
            VarInt varInt4 = new VarInt(bArr, i7);
            originalSizeInBytes2 = (int) (((long) i7) + varInt4.value + ((long) varInt4.getOriginalSizeInBytes()));
        }
        return (originalSizeInBytes2 - i) + 4;
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        this.cursor = this.offset;
        this.version = readUint32();
        this.optimalEncodingMessageSize = 4;
        long readVarInt = readVarInt();
        this.optimalEncodingMessageSize += VarInt.sizeOf(readVarInt);
        this.inputs = new ArrayList<>((int) readVarInt);
        for (long j = 0; j < readVarInt; j++) {
            TransactionInput transactionInput = r0;
            TransactionInput transactionInput2 = new TransactionInput(this.params, this, this.payload, this.cursor, this.serializer);
            this.inputs.add(transactionInput);
            long readVarInt2 = readVarInt(36);
            this.optimalEncodingMessageSize = (int) (((long) this.optimalEncodingMessageSize) + ((long) (VarInt.sizeOf(readVarInt2) + 36)) + readVarInt2 + 4);
            this.cursor = (int) (((long) this.cursor) + readVarInt2 + 4);
        }
        long readVarInt3 = readVarInt();
        this.optimalEncodingMessageSize += VarInt.sizeOf(readVarInt3);
        this.outputs = new ArrayList<>((int) readVarInt3);
        for (long j2 = 0; j2 < readVarInt3; j2++) {
            TransactionOutput transactionOutput = new TransactionOutput(this.params, this, this.payload, this.cursor, this.serializer);
            this.outputs.add(transactionOutput);
            long readVarInt4 = readVarInt(8);
            this.optimalEncodingMessageSize = (int) (((long) this.optimalEncodingMessageSize) + ((long) (VarInt.sizeOf(readVarInt4) + 8)) + readVarInt4);
            this.cursor = (int) (((long) this.cursor) + readVarInt4);
        }
        this.lockTime = readUint32();
        this.optimalEncodingMessageSize += 4;
        this.length = this.cursor - this.offset;
    }

    public int getOptimalEncodingMessageSize() {
        int i = this.optimalEncodingMessageSize;
        if (i != 0) {
            return i;
        }
        this.optimalEncodingMessageSize = getMessageSize();
        return this.optimalEncodingMessageSize;
    }

    public int getMessageSizeForPriorityCalc() {
        int messageSize = getMessageSize();
        Iterator it = this.inputs.iterator();
        while (it.hasNext()) {
            int min = Math.min(110, ((TransactionInput) it.next()).getScriptSig().getProgram().length) + 41;
            if (messageSize > min) {
                messageSize -= min;
            }
        }
        return messageSize;
    }

    public boolean isCoinBase() {
        return this.inputs.size() == 1 && ((TransactionInput) this.inputs.get(0)).isCoinBase();
    }

    public boolean isMature() {
        boolean z = true;
        if (!isCoinBase()) {
            return true;
        }
        if (getConfidence().getConfidenceType() != ConfidenceType.BUILDING) {
            return false;
        }
        if (getConfidence().getDepthInBlocks() < this.params.getSpendableCoinbaseDepth()) {
            z = false;
        }
        return z;
    }

    public String toString() {
        return toString(null);
    }

    public String toString(@Nullable AbstractBlockChain abstractBlockChain) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6 = "???";
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        sb.append(getHashAsString());
        sb.append(10);
        if (this.updatedAt != null) {
            sb.append("  updated: ");
            sb.append(C3447Utils.dateTimeFormat(this.updatedAt));
            sb.append(10);
        }
        if (this.version != 1) {
            sb.append("  version ");
            sb.append(this.version);
            sb.append(10);
        }
        if (isTimeLocked()) {
            sb.append("  time locked until ");
            long j = this.lockTime;
            if (j < 500000000) {
                sb.append("block ");
                sb.append(this.lockTime);
                if (abstractBlockChain != null) {
                    sb.append(" (estimated to be reached at ");
                    sb.append(C3447Utils.dateTimeFormat(abstractBlockChain.estimateBlockTime((int) this.lockTime)));
                    sb.append(')');
                }
            } else {
                sb.append(C3447Utils.dateTimeFormat(j * 1000));
            }
            sb.append(10);
        }
        if (isOptInFullRBF()) {
            sb.append("  opts into full replace-by-fee\n");
        }
        if (this.inputs.size() == 0) {
            sb.append("  INCOMPLETE: No inputs!\n");
            return sb.toString();
        } else if (isCoinBase()) {
            try {
                str5 = ((TransactionInput) this.inputs.get(0)).getScriptSig().toString();
                str6 = ((TransactionOutput) this.outputs.get(0)).getScriptPubKey().toString();
            } catch (ScriptException unused) {
                str5 = str6;
            }
            sb.append("     == COINBASE TXN (scriptSig ");
            sb.append(str5);
            sb.append(")  (scriptPubKey ");
            sb.append(str6);
            sb.append(")\n");
            return sb.toString();
        } else {
            Iterator it = this.inputs.iterator();
            while (true) {
                str = " ";
                str2 = "]";
                str3 = "[exception: ";
                str4 = "     ";
                if (!it.hasNext()) {
                    break;
                }
                TransactionInput transactionInput = (TransactionInput) it.next();
                sb.append(str4);
                sb.append("in   ");
                try {
                    sb.append(transactionInput.getScriptSig());
                    if (transactionInput.getValue() != null) {
                        sb.append(str);
                        sb.append(transactionInput.getValue().toFriendlyString());
                    }
                    sb.append("\n          ");
                    sb.append("outpoint:");
                    TransactionOutPoint outpoint = transactionInput.getOutpoint();
                    sb.append(outpoint.toString());
                    TransactionOutput connectedOutput = outpoint.getConnectedOutput();
                    if (connectedOutput != null) {
                        Script scriptPubKey = connectedOutput.getScriptPubKey();
                        if (scriptPubKey.isSentToAddress() || scriptPubKey.isPayToScriptHash()) {
                            sb.append(" hash160:");
                            sb.append(C3447Utils.HEX.encode(scriptPubKey.getPubKeyHash()));
                        }
                    }
                    if (transactionInput.hasSequence()) {
                        sb.append("\n          sequence:");
                        sb.append(Long.toHexString(transactionInput.getSequenceNumber()));
                        if (transactionInput.isOptInFullRBF()) {
                            sb.append(", opts into full RBF");
                        }
                    }
                } catch (Exception e) {
                    sb.append(str3);
                    sb.append(e.getMessage());
                    sb.append(str2);
                }
                sb.append(10);
            }
            Iterator it2 = this.outputs.iterator();
            while (it2.hasNext()) {
                TransactionOutput transactionOutput = (TransactionOutput) it2.next();
                sb.append(str4);
                sb.append("out  ");
                try {
                    sb.append(transactionOutput.getScriptPubKey());
                    sb.append(str);
                    sb.append(transactionOutput.getValue().toFriendlyString());
                    if (!transactionOutput.isAvailableForSpending()) {
                        sb.append(" Spent");
                    }
                    if (transactionOutput.getSpentBy() != null) {
                        sb.append(" by ");
                        sb.append(transactionOutput.getSpentBy().getParentTransaction().getHashAsString());
                    }
                } catch (Exception e2) {
                    sb.append(str3);
                    sb.append(e2.getMessage());
                    sb.append(str2);
                }
                sb.append(10);
            }
            Coin fee = getFee();
            if (fee != null) {
                int length = unsafeBitcoinSerialize().length;
                sb.append("     fee  ");
                sb.append(fee.multiply(1000).divide((long) length).toFriendlyString());
                sb.append("/kB, ");
                sb.append(fee.toFriendlyString());
                sb.append(" for ");
                sb.append(length);
                sb.append(" bytes\n");
            }
            if (this.purpose != null) {
                sb.append("     prps ");
                sb.append(this.purpose);
                sb.append(10);
            }
            return sb.toString();
        }
    }

    public void clearInputs() {
        unCache();
        Iterator it = this.inputs.iterator();
        while (it.hasNext()) {
            ((TransactionInput) it.next()).setParent(null);
        }
        this.inputs.clear();
        this.length = unsafeBitcoinSerialize().length;
    }

    public TransactionInput addInput(TransactionOutput transactionOutput) {
        return addInput(new TransactionInput(this.params, this, transactionOutput));
    }

    public TransactionInput addInput(TransactionInput transactionInput) {
        unCache();
        transactionInput.setParent(this);
        this.inputs.add(transactionInput);
        adjustLength(this.inputs.size(), transactionInput.length);
        return transactionInput;
    }

    public TransactionInput addInput(Sha256Hash sha256Hash, long j, Script script) {
        return addInput(new TransactionInput(this.params, this, script.getProgram(), new TransactionOutPoint(this.params, j, sha256Hash)));
    }

    public TransactionInput addSignedInput(TransactionOutPoint transactionOutPoint, Script script, ECKey eCKey, SigHash sigHash, boolean z) throws ScriptException {
        Preconditions.checkState(!this.outputs.isEmpty(), "Attempting to sign tx without outputs.");
        TransactionInput transactionInput = new TransactionInput(this.params, this, new byte[0], transactionOutPoint);
        addInput(transactionInput);
        TransactionSignature transactionSignature = new TransactionSignature(eCKey.sign(hashForSignature(this.inputs.size() - 1, script, sigHash, z)), sigHash, z, false);
        if (script.isSentToRawPubKey()) {
            transactionInput.setScriptSig(ScriptBuilder.createInputScript(transactionSignature));
        } else if (script.isSentToAddress()) {
            transactionInput.setScriptSig(ScriptBuilder.createInputScript(transactionSignature, eCKey));
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Don't know how to sign for this kind of scriptPubKey: ");
            sb.append(script);
            throw new ScriptException(sb.toString());
        }
        return transactionInput;
    }

    public TransactionInput addSignedInput(TransactionOutPoint transactionOutPoint, Script script, ECKey eCKey, SigHash sigHash, boolean z, boolean z2) throws ScriptException {
        Sha256Hash sha256Hash;
        Preconditions.checkState(!this.outputs.isEmpty(), "Attempting to sign tx without outputs.");
        TransactionInput transactionInput = new TransactionInput(this.params, this, new byte[0], transactionOutPoint);
        addInput(transactionInput);
        if (z2) {
            sha256Hash = hashForSignatureWitness(this.inputs.size() - 1, script, transactionOutPoint.getConnectedOutput().getValue(), sigHash, z);
        } else {
            sha256Hash = hashForSignature(this.inputs.size() - 1, script, sigHash, z);
        }
        TransactionSignature transactionSignature = new TransactionSignature(eCKey.sign(sha256Hash), sigHash, z, z2);
        if (script.isSentToRawPubKey()) {
            transactionInput.setScriptSig(ScriptBuilder.createInputScript(transactionSignature));
        } else if (script.isSentToAddress()) {
            transactionInput.setScriptSig(ScriptBuilder.createInputScript(transactionSignature, eCKey));
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Don't know how to sign for this kind of scriptPubKey: ");
            sb.append(script);
            throw new ScriptException(sb.toString());
        }
        return transactionInput;
    }

    public TransactionInput addSignedInput(TransactionOutPoint transactionOutPoint, Script script, ECKey eCKey) throws ScriptException {
        return addSignedInput(transactionOutPoint, script, eCKey, SigHash.ALL, false);
    }

    public TransactionInput addSignedInput(TransactionOutput transactionOutput, ECKey eCKey) {
        return addSignedInput(transactionOutput.getOutPointFor(), transactionOutput.getScriptPubKey(), eCKey);
    }

    public TransactionInput addSignedInput(TransactionOutput transactionOutput, ECKey eCKey, SigHash sigHash, boolean z) {
        return addSignedInput(transactionOutput.getOutPointFor(), transactionOutput.getScriptPubKey(), eCKey, sigHash, z);
    }

    public void clearOutputs() {
        unCache();
        Iterator it = this.outputs.iterator();
        while (it.hasNext()) {
            ((TransactionOutput) it.next()).setParent(null);
        }
        this.outputs.clear();
        this.length = unsafeBitcoinSerialize().length;
    }

    public TransactionOutput addOutput(TransactionOutput transactionOutput) {
        unCache();
        transactionOutput.setParent(this);
        this.outputs.add(transactionOutput);
        adjustLength(this.outputs.size(), transactionOutput.length);
        return transactionOutput;
    }

    public TransactionOutput addOutput(Coin coin, Address address) {
        return addOutput(new TransactionOutput(this.params, this, coin, address));
    }

    public TransactionOutput addOutput(Coin coin, ECKey eCKey) {
        return addOutput(new TransactionOutput(this.params, this, coin, eCKey));
    }

    public TransactionOutput addOutput(Coin coin, Script script) {
        return addOutput(new TransactionOutput(this.params, this, coin, script.getProgram()));
    }

    public TransactionSignature calculateSignature(int i, ECKey eCKey, byte[] bArr, SigHash sigHash, boolean z) {
        return new TransactionSignature(eCKey.sign(hashForSignature(i, bArr, sigHash, z)), sigHash, z);
    }

    public TransactionSignature calculateWitnessSignature(int i, ECKey eCKey, byte[] bArr, Coin coin, SigHash sigHash, boolean z) {
        return new TransactionSignature(eCKey.sign(hashForSignatureWitness(i, bArr, coin, sigHash, z)), sigHash, z, true);
    }

    public TransactionSignature calculateSignature(int i, ECKey eCKey, Script script, SigHash sigHash, boolean z) {
        return new TransactionSignature(eCKey.sign(hashForSignature(i, script.getProgram(), sigHash, z)), sigHash, z);
    }

    public TransactionSignature calculateWitnessSignature(int i, ECKey eCKey, Script script, Coin coin, SigHash sigHash, boolean z) {
        return new TransactionSignature(eCKey.sign(hashForSignatureWitness(i, script.getProgram(), coin, sigHash, z)), sigHash, z, true);
    }

    public TransactionSignature calculateSignature(int i, ECKey eCKey, @Nullable KeyParameter keyParameter, byte[] bArr, SigHash sigHash, boolean z, boolean z2) {
        return new TransactionSignature(eCKey.sign(hashForSignature(i, bArr, sigHash, z), keyParameter), sigHash, z);
    }

    public TransactionSignature calculateWitnessSignature(int i, ECKey eCKey, @Nullable KeyParameter keyParameter, byte[] bArr, Coin coin, SigHash sigHash, boolean z) {
        return new TransactionSignature(eCKey.sign(hashForSignatureWitness(i, bArr, coin, sigHash, z), keyParameter), sigHash, z, true);
    }

    public TransactionSignature calculateSignature(int i, ECKey eCKey, @Nullable KeyParameter keyParameter, Script script, SigHash sigHash, boolean z) {
        return new TransactionSignature(eCKey.sign(hashForSignature(i, script.getProgram(), sigHash, z), keyParameter), sigHash, z, false);
    }

    public TransactionSignature calculateWitnessSignature(int i, ECKey eCKey, @Nullable KeyParameter keyParameter, Script script, Coin coin, SigHash sigHash, boolean z) {
        return new TransactionSignature(eCKey.sign(hashForSignatureWitness(i, script.getProgram(), coin, sigHash, z), keyParameter), sigHash, z, true);
    }

    public Sha256Hash hashForSignature(int i, byte[] bArr, SigHash sigHash, boolean z) {
        return hashForSignature(i, bArr, (byte) TransactionSignature.calcSigHashValue(sigHash, z));
    }

    public Sha256Hash hashForSignature(int i, Script script, SigHash sigHash, boolean z) {
        return hashForSignature(i, script.getProgram(), (byte) TransactionSignature.calcSigHashValue(sigHash, z));
    }

    public Sha256Hash hashForSignature(int i, byte[] bArr, byte b) {
        try {
            Transaction makeTransaction = this.params.getDefaultSerializer().makeTransaction(bitcoinSerialize());
            int i2 = 0;
            for (int i3 = 0; i3 < makeTransaction.inputs.size(); i3++) {
                ((TransactionInput) makeTransaction.inputs.get(i3)).clearScriptBytes();
            }
            TransactionInput transactionInput = (TransactionInput) makeTransaction.inputs.get(i);
            transactionInput.setScriptBytes(Script.removeAllInstancesOfOp(bArr, 171));
            byte b2 = b & Ascii.f531US;
            if (b2 == SigHash.NONE.value) {
                makeTransaction.outputs = new ArrayList<>(0);
                while (i2 < makeTransaction.inputs.size()) {
                    if (i2 != i) {
                        ((TransactionInput) makeTransaction.inputs.get(i2)).setSequenceNumber(0);
                    }
                    i2++;
                }
            } else if (b2 == SigHash.SINGLE.value) {
                if (i >= makeTransaction.outputs.size()) {
                    return Sha256Hash.wrap("0100000000000000000000000000000000000000000000000000000000000000");
                }
                makeTransaction.outputs = new ArrayList<>(makeTransaction.outputs.subList(0, i + 1));
                for (int i4 = 0; i4 < i; i4++) {
                    makeTransaction.outputs.set(i4, new TransactionOutput(makeTransaction.params, makeTransaction, Coin.NEGATIVE_SATOSHI, new byte[0]));
                }
                while (i2 < makeTransaction.inputs.size()) {
                    if (i2 != i) {
                        ((TransactionInput) makeTransaction.inputs.get(i2)).setSequenceNumber(0);
                    }
                    i2++;
                }
            }
            if ((SigHash.ANYONECANPAY.value & b) == SigHash.ANYONECANPAY.value) {
                makeTransaction.inputs = new ArrayList<>();
                makeTransaction.inputs.add(transactionInput);
            }
            UnsafeByteArrayOutputStream unsafeByteArrayOutputStream = new UnsafeByteArrayOutputStream(makeTransaction.length == Integer.MIN_VALUE ? 256 : makeTransaction.length + 4);
            makeTransaction.bitcoinSerialize(unsafeByteArrayOutputStream);
            C3447Utils.uint32ToByteStreamLE((long) (b & 255), unsafeByteArrayOutputStream);
            Sha256Hash twiceOf = Sha256Hash.twiceOf(unsafeByteArrayOutputStream.toByteArray());
            unsafeByteArrayOutputStream.close();
            return twiceOf;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized Sha256Hash hashForSignatureWitness(int i, Script script, Coin coin, SigHash sigHash, boolean z) {
        return hashForSignatureWitness(i, script.getProgram(), coin, sigHash, z);
    }

    public synchronized Sha256Hash hashForSignatureWitness(int i, byte[] bArr, Coin coin, SigHash sigHash, boolean z) {
        Sha256Hash twiceOf;
        int i2 = i;
        byte[] bArr2 = bArr;
        SigHash sigHash2 = sigHash;
        synchronized (this) {
            boolean z2 = true;
            byte calcSigHashValue = (byte) TransactionSignature.calcSigHashValue(sigHash2, z, true);
            UnsafeByteArrayOutputStream unsafeByteArrayOutputStream = new UnsafeByteArrayOutputStream(this.length == Integer.MIN_VALUE ? 256 : this.length + 4);
            try {
                byte[] bArr3 = new byte[32];
                byte[] bArr4 = new byte[32];
                byte[] bArr5 = new byte[32];
                if ((calcSigHashValue & Byte.MIN_VALUE) != Byte.MIN_VALUE) {
                    z2 = false;
                }
                if (!z2) {
                    UnsafeByteArrayOutputStream unsafeByteArrayOutputStream2 = new UnsafeByteArrayOutputStream(256);
                    for (int i3 = 0; i3 < this.inputs.size(); i3++) {
                        unsafeByteArrayOutputStream2.write(((TransactionInput) this.inputs.get(i3)).getOutpoint().getHash().getReversedBytes());
                        C3447Utils.uint32ToByteStreamLE(((TransactionInput) this.inputs.get(i3)).getOutpoint().getIndex(), unsafeByteArrayOutputStream2);
                    }
                    bArr3 = Sha256Hash.hashTwice(unsafeByteArrayOutputStream2.toByteArray());
                }
                if (!(z2 || sigHash2 == SigHash.SINGLE || sigHash2 == SigHash.NONE)) {
                    UnsafeByteArrayOutputStream unsafeByteArrayOutputStream3 = new UnsafeByteArrayOutputStream(256);
                    for (int i4 = 0; i4 < this.inputs.size(); i4++) {
                        C3447Utils.uint32ToByteStreamLE(((TransactionInput) this.inputs.get(i4)).getSequenceNumber(), unsafeByteArrayOutputStream3);
                    }
                    bArr4 = Sha256Hash.hashTwice(unsafeByteArrayOutputStream3.toByteArray());
                }
                if (sigHash2 != SigHash.SINGLE && sigHash2 != SigHash.NONE) {
                    UnsafeByteArrayOutputStream unsafeByteArrayOutputStream4 = new UnsafeByteArrayOutputStream(256);
                    for (int i5 = 0; i5 < this.outputs.size(); i5++) {
                        C3447Utils.uint64ToByteStreamLE(BigInteger.valueOf(((TransactionOutput) this.outputs.get(i5)).getValue().getValue()), unsafeByteArrayOutputStream4);
                        unsafeByteArrayOutputStream4.write(new VarInt((long) ((TransactionOutput) this.outputs.get(i5)).getScriptBytes().length).encode());
                        unsafeByteArrayOutputStream4.write(((TransactionOutput) this.outputs.get(i5)).getScriptBytes());
                    }
                    bArr5 = Sha256Hash.hashTwice(unsafeByteArrayOutputStream4.toByteArray());
                } else if (sigHash2 == SigHash.SINGLE && i2 < this.outputs.size()) {
                    UnsafeByteArrayOutputStream unsafeByteArrayOutputStream5 = new UnsafeByteArrayOutputStream(256);
                    C3447Utils.uint64ToByteStreamLE(BigInteger.valueOf(((TransactionOutput) this.outputs.get(i2)).getValue().getValue()), unsafeByteArrayOutputStream5);
                    unsafeByteArrayOutputStream5.write(new VarInt((long) ((TransactionOutput) this.outputs.get(i2)).getScriptBytes().length).encode());
                    unsafeByteArrayOutputStream5.write(((TransactionOutput) this.outputs.get(i2)).getScriptBytes());
                    bArr5 = Sha256Hash.hashTwice(unsafeByteArrayOutputStream5.toByteArray());
                }
                C3447Utils.uint32ToByteStreamLE(this.version, unsafeByteArrayOutputStream);
                unsafeByteArrayOutputStream.write(bArr3);
                unsafeByteArrayOutputStream.write(bArr4);
                unsafeByteArrayOutputStream.write(((TransactionInput) this.inputs.get(i2)).getOutpoint().getHash().getReversedBytes());
                C3447Utils.uint32ToByteStreamLE(((TransactionInput) this.inputs.get(i2)).getOutpoint().getIndex(), unsafeByteArrayOutputStream);
                unsafeByteArrayOutputStream.write(new VarInt((long) bArr2.length).encode());
                unsafeByteArrayOutputStream.write(bArr2);
                C3447Utils.uint64ToByteStreamLE(BigInteger.valueOf(coin.getValue()), unsafeByteArrayOutputStream);
                C3447Utils.uint32ToByteStreamLE(((TransactionInput) this.inputs.get(i2)).getSequenceNumber(), unsafeByteArrayOutputStream);
                unsafeByteArrayOutputStream.write(bArr5);
                C3447Utils.uint32ToByteStreamLE(this.lockTime, unsafeByteArrayOutputStream);
                C3447Utils.uint32ToByteStreamLE((long) (calcSigHashValue & 255), unsafeByteArrayOutputStream);
                twiceOf = Sha256Hash.twiceOf(unsafeByteArrayOutputStream.toByteArray());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return twiceOf;
    }

    /* access modifiers changed from: protected */
    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        C3447Utils.uint32ToByteStreamLE(this.version, outputStream);
        outputStream.write(new VarInt((long) this.inputs.size()).encode());
        Iterator it = this.inputs.iterator();
        while (it.hasNext()) {
            ((TransactionInput) it.next()).bitcoinSerialize(outputStream);
        }
        outputStream.write(new VarInt((long) this.outputs.size()).encode());
        Iterator it2 = this.outputs.iterator();
        while (it2.hasNext()) {
            ((TransactionOutput) it2.next()).bitcoinSerialize(outputStream);
        }
        C3447Utils.uint32ToByteStreamLE(this.lockTime, outputStream);
    }

    public long getLockTime() {
        return this.lockTime;
    }

    public void setLockTime(long j) {
        boolean z;
        unCache();
        Iterator it = this.inputs.iterator();
        while (true) {
            if (it.hasNext()) {
                if (((TransactionInput) it.next()).getSequenceNumber() != TransactionInput.NO_SEQUENCE) {
                    z = true;
                    break;
                }
            } else {
                z = false;
                break;
            }
        }
        if (j != 0 && (!z || this.inputs.isEmpty())) {
            log.warn("You are setting the lock time on a transaction but none of the inputs have non-default sequence numbers. This will not do what you expect!");
        }
        this.lockTime = j;
    }

    public long getVersion() {
        return this.version;
    }

    public void setVersion(int i) {
        this.version = (long) i;
        unCache();
    }

    public List<TransactionInput> getInputs() {
        return Collections.unmodifiableList(this.inputs);
    }

    public List<TransactionOutput> getOutputs() {
        return Collections.unmodifiableList(this.outputs);
    }

    public List<TransactionOutput> getWalletOutputs(TransactionBag transactionBag) {
        LinkedList linkedList = new LinkedList();
        Iterator it = this.outputs.iterator();
        while (it.hasNext()) {
            TransactionOutput transactionOutput = (TransactionOutput) it.next();
            if (transactionOutput.isMineOrWatched(transactionBag)) {
                linkedList.add(transactionOutput);
            }
        }
        return linkedList;
    }

    public void shuffleOutputs() {
        Collections.shuffle(this.outputs);
    }

    public TransactionInput getInput(long j) {
        return (TransactionInput) this.inputs.get((int) j);
    }

    public TransactionOutput getOutput(long j) {
        return (TransactionOutput) this.outputs.get((int) j);
    }

    public TransactionConfidence getConfidence() {
        return getConfidence(Context.get());
    }

    public TransactionConfidence getConfidence(Context context) {
        return getConfidence(context.getConfidenceTable());
    }

    public TransactionConfidence getConfidence(TxConfidenceTable txConfidenceTable) {
        if (this.confidence == null) {
            this.confidence = txConfidenceTable.getOrCreate(getHash());
        }
        return this.confidence;
    }

    public boolean hasConfidence() {
        return getConfidence().getConfidenceType() != ConfidenceType.UNKNOWN;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return getHash().equals(((Transaction) obj).getHash());
    }

    public int hashCode() {
        return getHash().hashCode();
    }

    public int getSigOpCount() throws ScriptException {
        Iterator it = this.inputs.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += Script.getSigOpCount(((TransactionInput) it.next()).getScriptBytes());
        }
        Iterator it2 = this.outputs.iterator();
        while (it2.hasNext()) {
            i += Script.getSigOpCount(((TransactionOutput) it2.next()).getScriptBytes());
        }
        return i;
    }

    public void checkCoinBaseHeight(int i) throws VerificationException {
        int i2 = 0;
        Preconditions.checkArgument(i >= 0);
        Preconditions.checkState(isCoinBase());
        TransactionInput transactionInput = (TransactionInput) getInputs().get(0);
        ScriptBuilder scriptBuilder = new ScriptBuilder();
        scriptBuilder.number((long) i);
        byte[] program = scriptBuilder.build().getProgram();
        byte[] scriptBytes = transactionInput.getScriptBytes();
        String str = "Block height mismatch in coinbase.";
        if (scriptBytes.length >= program.length) {
            while (i2 < program.length) {
                if (scriptBytes[i2] == program[i2]) {
                    i2++;
                } else {
                    throw new CoinbaseHeightMismatch(str);
                }
            }
            return;
        }
        throw new CoinbaseHeightMismatch(str);
    }

    public void verify() throws VerificationException {
        if (this.inputs.size() == 0 || this.outputs.size() == 0) {
            throw new EmptyInputsOrOutputs();
        } else if (getMessageSize() <= 8000000) {
            Coin coin = Coin.ZERO;
            HashSet hashSet = new HashSet();
            Iterator it = this.inputs.iterator();
            while (it.hasNext()) {
                TransactionInput transactionInput = (TransactionInput) it.next();
                if (!hashSet.contains(transactionInput.getOutpoint())) {
                    hashSet.add(transactionInput.getOutpoint());
                } else {
                    throw new DuplicatedOutPoint();
                }
            }
            try {
                Iterator it2 = this.outputs.iterator();
                while (it2.hasNext()) {
                    TransactionOutput transactionOutput = (TransactionOutput) it2.next();
                    if (transactionOutput.getValue().signum() >= 0) {
                        coin = coin.add(transactionOutput.getValue());
                        if (this.params.hasMaxMoney()) {
                            if (coin.compareTo(this.params.getMaxMoney()) > 0) {
                                throw new IllegalArgumentException();
                            }
                        }
                    } else {
                        throw new NegativeValueOutput();
                    }
                }
                if (!isCoinBase()) {
                    Iterator it3 = this.inputs.iterator();
                    while (it3.hasNext()) {
                        if (((TransactionInput) it3.next()).isCoinBase()) {
                            throw new UnexpectedCoinbaseInput();
                        }
                    }
                } else if (((TransactionInput) this.inputs.get(0)).getScriptBytes().length < 2 || ((TransactionInput) this.inputs.get(0)).getScriptBytes().length > 100) {
                    throw new CoinbaseScriptSizeOutOfRange();
                }
            } catch (IllegalStateException unused) {
                throw new ExcessiveValue();
            } catch (IllegalArgumentException unused2) {
                throw new ExcessiveValue();
            }
        } else {
            throw new LargerThanMaxBlockSize();
        }
    }

    public boolean isTimeLocked() {
        if (getLockTime() == 0) {
            return false;
        }
        for (TransactionInput hasSequence : getInputs()) {
            if (hasSequence.hasSequence()) {
                return true;
            }
        }
        return false;
    }

    public boolean isOptInFullRBF() {
        for (TransactionInput isOptInFullRBF : getInputs()) {
            if (isOptInFullRBF.isOptInFullRBF()) {
                return true;
            }
        }
        return false;
    }

    public boolean isFinal(int i, long j) {
        long lockTime2 = getLockTime();
        if (lockTime2 < 500000000) {
            j = (long) i;
        }
        return lockTime2 < j || !isTimeLocked();
    }

    public Date estimateLockTime(AbstractBlockChain abstractBlockChain) {
        if (this.lockTime < 500000000) {
            return abstractBlockChain.estimateBlockTime((int) getLockTime());
        }
        return new Date(getLockTime() * 1000);
    }

    public Purpose getPurpose() {
        return this.purpose;
    }

    public void setPurpose(Purpose purpose2) {
        this.purpose = purpose2;
    }

    @Nullable
    public ExchangeRate getExchangeRate() {
        return this.exchangeRate;
    }

    public void setExchangeRate(ExchangeRate exchangeRate2) {
        this.exchangeRate = exchangeRate2;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String str) {
        this.memo = str;
    }
}
