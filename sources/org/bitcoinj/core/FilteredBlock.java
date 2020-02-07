package org.bitcoinj.core;

import com.google.common.base.Objects;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FilteredBlock extends Message {
    private Map<Sha256Hash, Transaction> associatedTransactions = new HashMap();
    private List<Sha256Hash> cachedTransactionHashes = null;
    private Block header;
    private PartialMerkleTree merkleTree;

    public FilteredBlock(NetworkParameters networkParameters, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr, 0);
    }

    public FilteredBlock(NetworkParameters networkParameters, Block block, PartialMerkleTree partialMerkleTree) {
        super(networkParameters);
        this.header = block;
        this.merkleTree = partialMerkleTree;
    }

    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        if (this.header.transactions == null) {
            this.header.bitcoinSerializeToStream(outputStream);
        } else {
            this.header.cloneAsHeader().bitcoinSerializeToStream(outputStream);
        }
        this.merkleTree.bitcoinSerializeToStream(outputStream);
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        byte[] bArr = new byte[80];
        System.arraycopy(this.payload, 0, bArr, 0, 80);
        this.header = this.params.getDefaultSerializer().makeBlock(bArr);
        this.merkleTree = new PartialMerkleTree(this.params, this.payload, 80);
        this.length = this.merkleTree.getMessageSize() + 80;
    }

    public List<Sha256Hash> getTransactionHashes() throws VerificationException {
        List<Sha256Hash> list = this.cachedTransactionHashes;
        if (list != null) {
            return Collections.unmodifiableList(list);
        }
        LinkedList linkedList = new LinkedList();
        if (this.header.getMerkleRoot().equals(this.merkleTree.getTxnHashAndMerkleRoot(linkedList))) {
            this.cachedTransactionHashes = linkedList;
            return Collections.unmodifiableList(this.cachedTransactionHashes);
        }
        throw new VerificationException("Merkle root of block header does not match merkle root of partial merkle tree.");
    }

    public Block getBlockHeader() {
        return this.header.cloneAsHeader();
    }

    public Sha256Hash getHash() {
        return this.header.getHash();
    }

    public boolean provideTransaction(Transaction transaction) throws VerificationException {
        Sha256Hash hash = transaction.getHash();
        if (!getTransactionHashes().contains(hash)) {
            return false;
        }
        this.associatedTransactions.put(hash, transaction);
        return true;
    }

    public PartialMerkleTree getPartialMerkleTree() {
        return this.merkleTree;
    }

    public Map<Sha256Hash, Transaction> getAssociatedTransactions() {
        return Collections.unmodifiableMap(this.associatedTransactions);
    }

    public int getTransactionCount() {
        return this.merkleTree.getTransactionCount();
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FilteredBlock filteredBlock = (FilteredBlock) obj;
        if (!this.associatedTransactions.equals(filteredBlock.associatedTransactions) || !this.header.equals(filteredBlock.header) || !this.merkleTree.equals(filteredBlock.merkleTree)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(this.associatedTransactions, this.header, this.merkleTree);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("FilteredBlock{merkleTree=");
        sb.append(this.merkleTree);
        sb.append(", header=");
        sb.append(this.header);
        sb.append('}');
        return sb.toString();
    }
}
