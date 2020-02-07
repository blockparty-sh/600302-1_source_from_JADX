package org.bitcoinj.core;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import javax.annotation.Nullable;
import org.bitcoinj.core.TransactionConfidence.Source;
import org.bitcoinj.script.Script;
import org.bitcoinj.script.ScriptBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Block extends Message {
    static final long ALLOWED_TIME_DRIFT = 7200;
    public static final int BLOCK_HEIGHT_GENESIS = 0;
    public static final int BLOCK_HEIGHT_UNKNOWN = -1;
    public static final long BLOCK_VERSION_BIP34 = 2;
    public static final long BLOCK_VERSION_BIP65 = 4;
    public static final long BLOCK_VERSION_BIP66 = 3;
    public static final long BLOCK_VERSION_GENESIS = 1;
    public static final long EASIEST_DIFFICULTY_TARGET = 545259519;
    static final byte[] EMPTY_BYTES = new byte[32];
    public static final int HEADER_SIZE = 80;
    private static BigInteger LARGEST_HASH = BigInteger.ONE.shiftLeft(256);
    public static final int MAX_BLOCK_SIGOPS = 160000;
    public static final int MAX_BLOCK_SIZE = 8000000;
    private static final Logger log = LoggerFactory.getLogger(Block.class);
    private static final byte[] pubkeyForTesting = new ECKey().getPubKey();
    private static int txCounter;
    private long difficultyTarget;
    private Sha256Hash hash;
    protected boolean headerBytesValid;
    private Sha256Hash merkleRoot;
    private long nonce;
    protected int optimalEncodingMessageSize;
    private Sha256Hash prevBlockHash;
    private long time;
    protected boolean transactionBytesValid;
    @Nullable
    List<Transaction> transactions;
    private long version;

    public enum VerifyFlag {
        HEIGHT_IN_COINBASE
    }

    Block(NetworkParameters networkParameters, long j) {
        super(networkParameters);
        this.version = j;
        this.difficultyTarget = 487063544;
        this.time = System.currentTimeMillis() / 1000;
        this.prevBlockHash = Sha256Hash.ZERO_HASH;
        this.length = 80;
    }

    @Deprecated
    public Block(NetworkParameters networkParameters, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr, 0, networkParameters.getDefaultSerializer(), bArr.length);
    }

    public Block(NetworkParameters networkParameters, byte[] bArr, MessageSerializer messageSerializer, int i) throws ProtocolException {
        super(networkParameters, bArr, 0, messageSerializer, i);
    }

    public Block(NetworkParameters networkParameters, byte[] bArr, int i, MessageSerializer messageSerializer, int i2) throws ProtocolException {
        super(networkParameters, bArr, i, messageSerializer, i2);
    }

    public Block(NetworkParameters networkParameters, byte[] bArr, int i, @Nullable Message message, MessageSerializer messageSerializer, int i2) throws ProtocolException {
        super(networkParameters, bArr, i, messageSerializer, i2);
    }

    public Block(NetworkParameters networkParameters, long j, Sha256Hash sha256Hash, Sha256Hash sha256Hash2, long j2, long j3, long j4, List<Transaction> list) {
        super(networkParameters);
        this.version = j;
        this.prevBlockHash = sha256Hash;
        this.merkleRoot = sha256Hash2;
        this.time = j2;
        this.difficultyTarget = j3;
        this.nonce = j4;
        this.transactions = new LinkedList();
        this.transactions.addAll(list);
    }

    public Coin getBlockInflation(int i) {
        return Coin.FIFTY_COINS.shiftRight(i / this.params.getSubsidyDecreaseBlockCount());
    }

    /* access modifiers changed from: protected */
    public void parseTransactions(int i) throws ProtocolException {
        this.cursor = i;
        this.optimalEncodingMessageSize = 80;
        if (this.payload.length == this.cursor) {
            this.transactionBytesValid = false;
            return;
        }
        int readVarInt = (int) readVarInt();
        this.optimalEncodingMessageSize += VarInt.sizeOf((long) readVarInt);
        this.transactions = new ArrayList(readVarInt);
        for (int i2 = 0; i2 < readVarInt; i2++) {
            Transaction transaction = new Transaction(this.params, this.payload, this.cursor, this, this.serializer, Integer.MIN_VALUE);
            transaction.getConfidence().setSource(Source.NETWORK);
            this.transactions.add(transaction);
            this.cursor += transaction.getMessageSize();
            this.optimalEncodingMessageSize += transaction.getOptimalEncodingMessageSize();
        }
        this.transactionBytesValid = this.serializer.isParseRetainMode();
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        this.cursor = this.offset;
        this.version = readUint32();
        this.prevBlockHash = readHash();
        this.merkleRoot = readHash();
        this.time = readUint32();
        this.difficultyTarget = readUint32();
        this.nonce = readUint32();
        this.hash = Sha256Hash.wrapReversed(Sha256Hash.hashTwice(this.payload, this.offset, this.cursor - this.offset));
        this.headerBytesValid = this.serializer.isParseRetainMode();
        parseTransactions(this.offset + 80);
        this.length = this.cursor - this.offset;
    }

    public int getOptimalEncodingMessageSize() {
        int i = this.optimalEncodingMessageSize;
        if (i != 0) {
            return i;
        }
        this.optimalEncodingMessageSize = bitcoinSerialize().length;
        return this.optimalEncodingMessageSize;
    }

    /* access modifiers changed from: 0000 */
    public void writeHeader(OutputStream outputStream) throws IOException {
        if (!this.headerBytesValid || this.payload == null || this.payload.length < this.offset + 80) {
            C3447Utils.uint32ToByteStreamLE(this.version, outputStream);
            outputStream.write(this.prevBlockHash.getReversedBytes());
            outputStream.write(getMerkleRoot().getReversedBytes());
            C3447Utils.uint32ToByteStreamLE(this.time, outputStream);
            C3447Utils.uint32ToByteStreamLE(this.difficultyTarget, outputStream);
            C3447Utils.uint32ToByteStreamLE(this.nonce, outputStream);
            return;
        }
        outputStream.write(this.payload, this.offset, 80);
    }

    private void writeTransactions(OutputStream outputStream) throws IOException {
        if (this.transactions != null) {
            if (!this.transactionBytesValid || this.payload == null || this.payload.length < this.offset + this.length) {
                List<Transaction> list = this.transactions;
                if (list != null) {
                    outputStream.write(new VarInt((long) list.size()).encode());
                    for (Transaction bitcoinSerialize : this.transactions) {
                        bitcoinSerialize.bitcoinSerialize(outputStream);
                    }
                }
                return;
            }
            outputStream.write(this.payload, this.offset + 80, this.length - 80);
        }
    }

    public byte[] bitcoinSerialize() {
        if (!this.headerBytesValid || !this.transactionBytesValid) {
            UnsafeByteArrayOutputStream unsafeByteArrayOutputStream = new UnsafeByteArrayOutputStream(this.length == Integer.MIN_VALUE ? guessTransactionsLength() + 80 : this.length);
            try {
                writeHeader(unsafeByteArrayOutputStream);
                writeTransactions(unsafeByteArrayOutputStream);
            } catch (IOException unused) {
            }
            return unsafeByteArrayOutputStream.toByteArray();
        }
        Preconditions.checkNotNull(this.payload, "Bytes should never be null if headerBytesValid && transactionBytesValid");
        if (this.length == this.payload.length) {
            return this.payload;
        }
        byte[] bArr = new byte[this.length];
        System.arraycopy(this.payload, this.offset, bArr, 0, this.length);
        return bArr;
    }

    /* access modifiers changed from: protected */
    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        writeHeader(outputStream);
        writeTransactions(outputStream);
    }

    private int guessTransactionsLength() {
        if (this.transactionBytesValid) {
            return this.payload.length - 80;
        }
        List<Transaction> list = this.transactions;
        if (list == null) {
            return 0;
        }
        int sizeOf = VarInt.sizeOf((long) list.size());
        for (Transaction transaction : this.transactions) {
            sizeOf += transaction.length == Integer.MIN_VALUE ? 255 : transaction.length;
        }
        return sizeOf;
    }

    /* access modifiers changed from: protected */
    public void unCache() {
        unCacheTransactions();
    }

    private void unCacheHeader() {
        this.headerBytesValid = false;
        if (!this.transactionBytesValid) {
            this.payload = null;
        }
        this.hash = null;
    }

    private void unCacheTransactions() {
        this.transactionBytesValid = false;
        if (!this.headerBytesValid) {
            this.payload = null;
        }
        unCacheHeader();
        this.merkleRoot = null;
    }

    private Sha256Hash calculateHash() {
        try {
            UnsafeByteArrayOutputStream unsafeByteArrayOutputStream = new UnsafeByteArrayOutputStream(80);
            writeHeader(unsafeByteArrayOutputStream);
            return Sha256Hash.wrapReversed(Sha256Hash.hashTwice(unsafeByteArrayOutputStream.toByteArray()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getHashAsString() {
        return getHash().toString();
    }

    public Sha256Hash getHash() {
        if (this.hash == null) {
            this.hash = calculateHash();
        }
        return this.hash;
    }

    public BigInteger getWork() throws VerificationException {
        return LARGEST_HASH.divide(getDifficultyTargetAsInteger().add(BigInteger.ONE));
    }

    public Block cloneAsHeader() {
        Block block = new Block(this.params, 1);
        copyBitcoinHeaderTo(block);
        return block;
    }

    /* access modifiers changed from: protected */
    public final void copyBitcoinHeaderTo(Block block) {
        block.nonce = this.nonce;
        block.prevBlockHash = this.prevBlockHash;
        block.merkleRoot = getMerkleRoot();
        block.version = this.version;
        block.time = this.time;
        block.difficultyTarget = this.difficultyTarget;
        block.transactions = null;
        block.hash = getHash();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" block: \n");
        sb.append("   hash: ");
        sb.append(getHashAsString());
        sb.append(10);
        sb.append("   version: ");
        sb.append(this.version);
        Joiner skipNulls = Joiner.m96on(", ").skipNulls();
        String str = null;
        Object obj = isBIP34() ? "BIP34" : null;
        Object obj2 = isBIP66() ? "BIP66" : null;
        Object[] objArr = new Object[1];
        if (isBIP65()) {
            str = "BIP65";
        }
        objArr[0] = str;
        String join = skipNulls.join(obj, obj2, objArr);
        String str2 = " (";
        if (!join.isEmpty()) {
            sb.append(str2);
            sb.append(join);
            sb.append(')');
        }
        sb.append(10);
        sb.append("   previous block: ");
        sb.append(getPrevBlockHash());
        String str3 = "\n";
        sb.append(str3);
        sb.append("   merkle root: ");
        sb.append(getMerkleRoot());
        sb.append(str3);
        sb.append("   time: ");
        sb.append(this.time);
        sb.append(str2);
        sb.append(C3447Utils.dateTimeFormat(this.time * 1000));
        sb.append(")\n");
        sb.append("   difficulty target (nBits): ");
        sb.append(this.difficultyTarget);
        sb.append(str3);
        sb.append("   nonce: ");
        sb.append(this.nonce);
        sb.append(str3);
        List<Transaction> list = this.transactions;
        if (list != null && list.size() > 0) {
            sb.append("   with ");
            sb.append(this.transactions.size());
            sb.append(" transaction(s):\n");
            for (Transaction append : this.transactions) {
                sb.append(append);
            }
        }
        return sb.toString();
    }

    public void solve() {
        while (!checkProofOfWork(false)) {
            try {
                setNonce(getNonce() + 1);
            } catch (VerificationException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public BigInteger getDifficultyTargetAsInteger() throws VerificationException {
        BigInteger decodeCompactBits = C3447Utils.decodeCompactBits(this.difficultyTarget);
        if (decodeCompactBits.signum() > 0 && decodeCompactBits.compareTo(this.params.maxTarget) <= 0) {
            return decodeCompactBits;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Difficulty target is bad: ");
        sb.append(decodeCompactBits.toString());
        throw new VerificationException(sb.toString());
    }

    /* access modifiers changed from: protected */
    public boolean checkProofOfWork(boolean z) throws VerificationException {
        BigInteger difficultyTargetAsInteger = getDifficultyTargetAsInteger();
        if (getHash().toBigInteger().compareTo(difficultyTargetAsInteger) <= 0) {
            return true;
        }
        if (!z) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Hash is higher than target: ");
        sb.append(getHashAsString());
        sb.append(" vs ");
        sb.append(difficultyTargetAsInteger.toString(16));
        throw new VerificationException(sb.toString());
    }

    private void checkTimestamp() throws VerificationException {
        long currentTimeSeconds = C3447Utils.currentTimeSeconds() + 7200;
        if (this.time > currentTimeSeconds) {
            throw new VerificationException(String.format(Locale.US, "Block too far in future: %d vs %d", new Object[]{Long.valueOf(this.time), Long.valueOf(currentTimeSeconds)}));
        }
    }

    private void checkSigOps() throws VerificationException {
        int i = 0;
        for (Transaction sigOpCount : this.transactions) {
            i += sigOpCount.getSigOpCount();
        }
        if (i > 160000) {
            throw new VerificationException("Block had too many Signature Operations");
        }
    }

    private void checkMerkleRoot() throws VerificationException {
        Sha256Hash calculateMerkleRoot = calculateMerkleRoot();
        if (!calculateMerkleRoot.equals(this.merkleRoot)) {
            log.error("Merkle tree did not verify");
            StringBuilder sb = new StringBuilder();
            sb.append("Merkle hashes do not match: ");
            sb.append(calculateMerkleRoot);
            sb.append(" vs ");
            sb.append(this.merkleRoot);
            throw new VerificationException(sb.toString());
        }
    }

    private Sha256Hash calculateMerkleRoot() {
        List buildMerkleTree = buildMerkleTree();
        return Sha256Hash.wrap((byte[]) buildMerkleTree.get(buildMerkleTree.size() - 1));
    }

    private List<byte[]> buildMerkleTree() {
        ArrayList arrayList = new ArrayList();
        for (Transaction hash2 : this.transactions) {
            arrayList.add(hash2.getHash().getBytes());
        }
        int i = 0;
        for (int size = this.transactions.size(); size > 1; size = (size + 1) / 2) {
            for (int i2 = 0; i2 < size; i2 += 2) {
                arrayList.add(C3447Utils.reverseBytes(Sha256Hash.hashTwice(C3447Utils.reverseBytes((byte[]) arrayList.get(i + i2)), 0, 32, C3447Utils.reverseBytes((byte[]) arrayList.get(Math.min(i2 + 1, size - 1) + i)), 0, 32)));
            }
            i += size;
        }
        return arrayList;
    }

    private void checkTransactions(int i, EnumSet<VerifyFlag> enumSet) throws VerificationException {
        if (((Transaction) this.transactions.get(0)).isCoinBase()) {
            if (enumSet.contains(VerifyFlag.HEIGHT_IN_COINBASE) && i >= 0) {
                ((Transaction) this.transactions.get(0)).checkCoinBaseHeight(i);
            }
            int i2 = 1;
            while (i2 < this.transactions.size()) {
                if (!((Transaction) this.transactions.get(i2)).isCoinBase()) {
                    i2++;
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("TX ");
                    sb.append(i2);
                    sb.append(" is coinbase when it should not be.");
                    throw new VerificationException(sb.toString());
                }
            }
            return;
        }
        throw new VerificationException("First tx is not coinbase");
    }

    public void verifyHeader() throws VerificationException {
        checkProofOfWork(true);
        checkTimestamp();
    }

    public void verifyTransactions(int i, EnumSet<VerifyFlag> enumSet) throws VerificationException {
        if (this.transactions.isEmpty()) {
            throw new VerificationException("Block had no transactions");
        } else if (getOptimalEncodingMessageSize() <= 8000000) {
            checkTransactions(i, enumSet);
            checkMerkleRoot();
            checkSigOps();
            for (Transaction verify : this.transactions) {
                verify.verify();
            }
        } else {
            throw new VerificationException("Block larger than MAX_BLOCK_SIZE");
        }
    }

    public void verify(int i, EnumSet<VerifyFlag> enumSet) throws VerificationException {
        verifyHeader();
        verifyTransactions(i, enumSet);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return getHash().equals(((Block) obj).getHash());
    }

    public int hashCode() {
        return getHash().hashCode();
    }

    public Sha256Hash getMerkleRoot() {
        if (this.merkleRoot == null) {
            unCacheHeader();
            this.merkleRoot = calculateMerkleRoot();
        }
        return this.merkleRoot;
    }

    /* access modifiers changed from: 0000 */
    public void setMerkleRoot(Sha256Hash sha256Hash) {
        unCacheHeader();
        this.merkleRoot = sha256Hash;
        this.hash = null;
    }

    public void addTransaction(Transaction transaction) {
        addTransaction(transaction, true);
    }

    /* access modifiers changed from: 0000 */
    public void addTransaction(Transaction transaction, boolean z) {
        unCacheTransactions();
        if (this.transactions == null) {
            this.transactions = new ArrayList();
        }
        transaction.setParent(this);
        if (z && this.transactions.size() == 0 && !transaction.isCoinBase()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Attempted to add a non-coinbase transaction as the first transaction: ");
            sb.append(transaction);
            throw new RuntimeException(sb.toString());
        } else if (!z || this.transactions.size() <= 0 || !transaction.isCoinBase()) {
            this.transactions.add(transaction);
            adjustLength(this.transactions.size(), transaction.length);
            this.merkleRoot = null;
            this.hash = null;
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Attempted to add a coinbase transaction when there already is one: ");
            sb2.append(transaction);
            throw new RuntimeException(sb2.toString());
        }
    }

    public long getVersion() {
        return this.version;
    }

    public Sha256Hash getPrevBlockHash() {
        return this.prevBlockHash;
    }

    /* access modifiers changed from: 0000 */
    public void setPrevBlockHash(Sha256Hash sha256Hash) {
        unCacheHeader();
        this.prevBlockHash = sha256Hash;
        this.hash = null;
    }

    public long getTimeSeconds() {
        return this.time;
    }

    public Date getTime() {
        return new Date(getTimeSeconds() * 1000);
    }

    public void setTime(long j) {
        unCacheHeader();
        this.time = j;
        this.hash = null;
    }

    public long getDifficultyTarget() {
        return this.difficultyTarget;
    }

    public void setDifficultyTarget(long j) {
        unCacheHeader();
        this.difficultyTarget = j;
        this.hash = null;
    }

    public long getNonce() {
        return this.nonce;
    }

    public void setNonce(long j) {
        unCacheHeader();
        this.nonce = j;
        this.hash = null;
    }

    @Nullable
    public List<Transaction> getTransactions() {
        List<Transaction> list = this.transactions;
        if (list == null) {
            return null;
        }
        return ImmutableList.copyOf((Collection<? extends E>) list);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public void addCoinbaseTransaction(byte[] bArr, Coin coin, int i) {
        unCacheTransactions();
        this.transactions = new ArrayList();
        Transaction transaction = new Transaction(this.params);
        ScriptBuilder scriptBuilder = new ScriptBuilder();
        if (i >= 0) {
            scriptBuilder.number((long) i);
        }
        int i2 = txCounter;
        txCounter = i2 + 1;
        scriptBuilder.data(new byte[]{(byte) i2, (byte) (i2 >> 8)});
        transaction.addInput(new TransactionInput(this.params, transaction, scriptBuilder.build().getProgram()));
        transaction.addOutput(new TransactionOutput(this.params, transaction, coin, ScriptBuilder.createOutputScript(ECKey.fromPublicOnly(bArr)).getProgram()));
        this.transactions.add(transaction);
        transaction.setParent(this);
        transaction.length = transaction.unsafeBitcoinSerialize().length;
        adjustLength(this.transactions.size(), transaction.length);
    }

    @VisibleForTesting
    public Block createNextBlock(Address address, long j, long j2, int i) {
        return createNextBlock(address, j, null, j2, pubkeyForTesting, Coin.FIFTY_COINS, i);
    }

    /* access modifiers changed from: 0000 */
    public Block createNextBlock(@Nullable Address address, long j, @Nullable TransactionOutPoint transactionOutPoint, long j2, byte[] bArr, Coin coin, int i) {
        TransactionInput transactionInput;
        Block block = new Block(this.params, j);
        block.setDifficultyTarget(this.difficultyTarget);
        block.addCoinbaseTransaction(bArr, coin, i);
        if (address != null) {
            Transaction transaction = new Transaction(this.params);
            transaction.addOutput(new TransactionOutput(this.params, transaction, Coin.FIFTY_COINS, address));
            if (transactionOutPoint == null) {
                NetworkParameters networkParameters = this.params;
                byte[] bArr2 = EMPTY_BYTES;
                transactionInput = new TransactionInput(networkParameters, transaction, Script.createInputScript(bArr2, bArr2));
                byte[] bArr3 = new byte[32];
                int i2 = txCounter;
                bArr3[0] = (byte) i2;
                txCounter = i2 + 1;
                bArr3[1] = (byte) (i2 >> 8);
                transactionInput.getOutpoint().setHash(Sha256Hash.wrap(bArr3));
            } else {
                NetworkParameters networkParameters2 = this.params;
                byte[] bArr4 = EMPTY_BYTES;
                transactionInput = new TransactionInput(networkParameters2, transaction, Script.createInputScript(bArr4, bArr4), transactionOutPoint);
            }
            transaction.addInput(transactionInput);
            block.addTransaction(transaction);
        }
        block.setPrevBlockHash(getHash());
        if (getTimeSeconds() >= j2) {
            block.setTime(getTimeSeconds() + 1);
        } else {
            block.setTime(j2);
        }
        block.solve();
        try {
            block.verifyHeader();
            if (block.getVersion() == j) {
                return block;
            }
            throw new RuntimeException();
        } catch (VerificationException e) {
            throw new RuntimeException(e);
        }
    }

    @VisibleForTesting
    public Block createNextBlock(@Nullable Address address, TransactionOutPoint transactionOutPoint) {
        return createNextBlock(address, 1, transactionOutPoint, getTimeSeconds() + 5, pubkeyForTesting, Coin.FIFTY_COINS, -1);
    }

    @VisibleForTesting
    public Block createNextBlock(@Nullable Address address, Coin coin) {
        return createNextBlock(address, 1, null, getTimeSeconds() + 5, pubkeyForTesting, coin, -1);
    }

    @VisibleForTesting
    public Block createNextBlock(@Nullable Address address) {
        return createNextBlock(address, Coin.FIFTY_COINS);
    }

    @VisibleForTesting
    public Block createNextBlockWithCoinbase(long j, byte[] bArr, Coin coin, int i) {
        return createNextBlock(null, j, null, C3447Utils.currentTimeSeconds(), bArr, coin, i);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public Block createNextBlockWithCoinbase(long j, byte[] bArr, int i) {
        return createNextBlock(null, j, null, C3447Utils.currentTimeSeconds(), bArr, Coin.FIFTY_COINS, i);
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public boolean isHeaderBytesValid() {
        return this.headerBytesValid;
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public boolean isTransactionBytesValid() {
        return this.transactionBytesValid;
    }

    public boolean hasTransactions() {
        return !this.transactions.isEmpty();
    }

    public boolean isBIP34() {
        return this.version >= 2;
    }

    public boolean isBIP66() {
        return this.version >= 3;
    }

    public boolean isBIP65() {
        return this.version >= 4;
    }
}
