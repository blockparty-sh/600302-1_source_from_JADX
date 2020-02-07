package org.bitcoinj.core;

import com.google.common.base.Objects;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartialMerkleTree extends Message {
    private List<Sha256Hash> hashes;
    private byte[] matchedChildBits;
    private int transactionCount;

    private static class ValuesUsed {
        public int bitsUsed;
        public int hashesUsed;

        private ValuesUsed() {
            this.bitsUsed = 0;
            this.hashesUsed = 0;
        }
    }

    private static int getTreeWidth(int i, int i2) {
        return ((i + (1 << i2)) - 1) >> i2;
    }

    public PartialMerkleTree(NetworkParameters networkParameters, byte[] bArr, int i) throws ProtocolException {
        super(networkParameters, bArr, i);
    }

    public PartialMerkleTree(NetworkParameters networkParameters, byte[] bArr, List<Sha256Hash> list, int i) {
        super(networkParameters);
        this.matchedChildBits = bArr;
        this.hashes = list;
        this.transactionCount = i;
    }

    public static PartialMerkleTree buildFromLeaves(NetworkParameters networkParameters, byte[] bArr, List<Sha256Hash> list) {
        int i = 0;
        while (getTreeWidth(list.size(), i) > 1) {
            i++;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        traverseAndBuild(i, 0, list, bArr, arrayList, arrayList2);
        byte[] bArr2 = new byte[((int) Math.ceil(((double) arrayList.size()) / 8.0d))];
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            if (((Boolean) arrayList.get(i2)).booleanValue()) {
                C3447Utils.setBitLE(bArr2, i2);
            }
        }
        return new PartialMerkleTree(networkParameters, bArr2, arrayList2, list.size());
    }

    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        C3447Utils.uint32ToByteStreamLE((long) this.transactionCount, outputStream);
        outputStream.write(new VarInt((long) this.hashes.size()).encode());
        for (Sha256Hash reversedBytes : this.hashes) {
            outputStream.write(reversedBytes.getReversedBytes());
        }
        outputStream.write(new VarInt((long) this.matchedChildBits.length).encode());
        outputStream.write(this.matchedChildBits);
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        this.transactionCount = (int) readUint32();
        int readVarInt = (int) readVarInt();
        this.hashes = new ArrayList(readVarInt);
        for (int i = 0; i < readVarInt; i++) {
            this.hashes.add(readHash());
        }
        this.matchedChildBits = readBytes((int) readVarInt());
        this.length = this.cursor - this.offset;
    }

    private static void traverseAndBuild(int i, int i2, List<Sha256Hash> list, byte[] bArr, List<Boolean> list2, List<Sha256Hash> list3) {
        boolean z;
        int i3 = i2 << i;
        while (true) {
            if (i3 >= ((i2 + 1) << i) || i3 >= list.size()) {
                z = false;
            } else if (C3447Utils.checkBitLE(bArr, i3)) {
                z = true;
                break;
            } else {
                i3++;
            }
        }
        z = false;
        list2.add(Boolean.valueOf(z));
        if (i == 0 || !z) {
            list3.add(calcHash(i, i2, list));
            return;
        }
        int i4 = i - 1;
        int i5 = i2 * 2;
        traverseAndBuild(i4, i5, list, bArr, list2, list3);
        int i6 = i5 + 1;
        if (i6 < getTreeWidth(list.size(), i4)) {
            traverseAndBuild(i4, i6, list, bArr, list2, list3);
        }
    }

    private static Sha256Hash calcHash(int i, int i2, List<Sha256Hash> list) {
        if (i == 0) {
            return (Sha256Hash) list.get(i2);
        }
        int i3 = i - 1;
        int i4 = i2 * 2;
        Sha256Hash calcHash = calcHash(i3, i4, list);
        int i5 = i4 + 1;
        return combineLeftRight(calcHash.getBytes(), (i5 < getTreeWidth(list.size(), i3) ? calcHash(i3, i5, list) : calcHash).getBytes());
    }

    private Sha256Hash recursiveExtractHashes(int i, int i2, ValuesUsed valuesUsed, List<Sha256Hash> list) throws VerificationException {
        byte[] bArr;
        int i3 = valuesUsed.bitsUsed;
        byte[] bArr2 = this.matchedChildBits;
        if (i3 < bArr2.length * 8) {
            int i4 = valuesUsed.bitsUsed;
            valuesUsed.bitsUsed = i4 + 1;
            boolean checkBitLE = C3447Utils.checkBitLE(bArr2, i4);
            if (i != 0 && checkBitLE) {
                int i5 = i - 1;
                int i6 = i2 * 2;
                byte[] bytes = recursiveExtractHashes(i5, i6, valuesUsed, list).getBytes();
                int i7 = i6 + 1;
                if (i7 < getTreeWidth(this.transactionCount, i5)) {
                    bArr = recursiveExtractHashes(i5, i7, valuesUsed, list).getBytes();
                    if (Arrays.equals(bArr, bytes)) {
                        throw new VerificationException("Invalid merkle tree with duplicated left/right branches");
                    }
                } else {
                    bArr = bytes;
                }
                return combineLeftRight(bytes, bArr);
            } else if (valuesUsed.hashesUsed < this.hashes.size()) {
                List<Sha256Hash> list2 = this.hashes;
                int i8 = valuesUsed.hashesUsed;
                valuesUsed.hashesUsed = i8 + 1;
                Sha256Hash sha256Hash = (Sha256Hash) list2.get(i8);
                if (i == 0 && checkBitLE) {
                    list.add(sha256Hash);
                }
                return sha256Hash;
            } else {
                throw new VerificationException("PartialMerkleTree overflowed its hash array");
            }
        } else {
            throw new VerificationException("PartialMerkleTree overflowed its bits array");
        }
    }

    private static Sha256Hash combineLeftRight(byte[] bArr, byte[] bArr2) {
        return Sha256Hash.wrapReversed(Sha256Hash.hashTwice(C3447Utils.reverseBytes(bArr), 0, 32, C3447Utils.reverseBytes(bArr2), 0, 32));
    }

    public Sha256Hash getTxnHashAndMerkleRoot(List<Sha256Hash> list) throws VerificationException {
        list.clear();
        int i = this.transactionCount;
        if (i == 0) {
            throw new VerificationException("Got a CPartialMerkleTree with 0 transactions");
        } else if (i > 133333) {
            throw new VerificationException("Got a CPartialMerkleTree with more transactions than is possible");
        } else if (this.hashes.size() > this.transactionCount) {
            throw new VerificationException("Got a CPartialMerkleTree with more hashes than transactions");
        } else if (this.matchedChildBits.length * 8 >= this.hashes.size()) {
            int i2 = 0;
            while (getTreeWidth(this.transactionCount, i2) > 1) {
                i2++;
            }
            ValuesUsed valuesUsed = new ValuesUsed();
            Sha256Hash recursiveExtractHashes = recursiveExtractHashes(i2, 0, valuesUsed, list);
            if ((valuesUsed.bitsUsed + 7) / 8 == this.matchedChildBits.length && valuesUsed.hashesUsed == this.hashes.size()) {
                return recursiveExtractHashes;
            }
            throw new VerificationException("Got a CPartialMerkleTree that didn't need all the data it provided");
        } else {
            throw new VerificationException("Got a CPartialMerkleTree with fewer matched bits than hashes");
        }
    }

    public int getTransactionCount() {
        return this.transactionCount;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PartialMerkleTree partialMerkleTree = (PartialMerkleTree) obj;
        if (this.transactionCount != partialMerkleTree.transactionCount || !this.hashes.equals(partialMerkleTree.hashes) || !Arrays.equals(this.matchedChildBits, partialMerkleTree.matchedChildBits)) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.transactionCount), this.hashes, Integer.valueOf(Arrays.hashCode(this.matchedChildBits)));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PartialMerkleTree{transactionCount=");
        sb.append(this.transactionCount);
        sb.append(", matchedChildBits=");
        sb.append(Arrays.toString(this.matchedChildBits));
        sb.append(", hashes=");
        sb.append(this.hashes);
        sb.append('}');
        return sb.toString();
    }
}
