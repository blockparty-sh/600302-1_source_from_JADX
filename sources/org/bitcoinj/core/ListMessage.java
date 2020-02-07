package org.bitcoinj.core;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bitcoinj.core.InventoryItem.C3406Type;

public abstract class ListMessage extends Message {
    public static final long MAX_INVENTORY_ITEMS = 50000;
    private long arrayLen;
    protected List<InventoryItem> items;

    public ListMessage(NetworkParameters networkParameters, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr, 0);
    }

    public ListMessage(NetworkParameters networkParameters, byte[] bArr, MessageSerializer messageSerializer, int i) throws ProtocolException {
        super(networkParameters, bArr, 0, messageSerializer, i);
    }

    public ListMessage(NetworkParameters networkParameters) {
        super(networkParameters);
        this.items = new ArrayList();
        this.length = 1;
    }

    public List<InventoryItem> getItems() {
        return Collections.unmodifiableList(this.items);
    }

    public void addItem(InventoryItem inventoryItem) {
        unCache();
        this.length -= VarInt.sizeOf((long) this.items.size());
        this.items.add(inventoryItem);
        this.length += VarInt.sizeOf((long) this.items.size()) + 36;
    }

    public void removeItem(int i) {
        unCache();
        this.length -= VarInt.sizeOf((long) this.items.size());
        this.items.remove(i);
        this.length += VarInt.sizeOf((long) this.items.size()) - 36;
    }

    /* access modifiers changed from: protected */
    public void parse() throws ProtocolException {
        C3406Type type;
        this.arrayLen = readVarInt();
        if (this.arrayLen <= MAX_INVENTORY_ITEMS) {
            long j = (long) (this.cursor - this.offset);
            long j2 = this.arrayLen;
            this.length = (int) (j + (36 * j2));
            this.items = new ArrayList((int) j2);
            int i = 0;
            while (((long) i) < this.arrayLen) {
                if (this.cursor + 36 <= this.payload.length) {
                    int readUint32 = (int) readUint32();
                    if (readUint32 == 0) {
                        type = C3406Type.Error;
                    } else if (readUint32 == 1) {
                        type = C3406Type.Transaction;
                    } else if (readUint32 == 2) {
                        type = C3406Type.Block;
                    } else if (readUint32 == 3) {
                        type = C3406Type.FilteredBlock;
                    } else {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unknown CInv type: ");
                        sb.append(readUint32);
                        throw new ProtocolException(sb.toString());
                    }
                    this.items.add(new InventoryItem(type, readHash()));
                    i++;
                } else {
                    throw new ProtocolException("Ran off the end of the INV");
                }
            }
            this.payload = null;
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append("Too many items in INV message: ");
        sb2.append(this.arrayLen);
        throw new ProtocolException(sb2.toString());
    }

    public void bitcoinSerializeToStream(OutputStream outputStream) throws IOException {
        outputStream.write(new VarInt((long) this.items.size()).encode());
        for (InventoryItem inventoryItem : this.items) {
            C3447Utils.uint32ToByteStreamLE((long) inventoryItem.type.ordinal(), outputStream);
            outputStream.write(inventoryItem.hash.getReversedBytes());
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.items.equals(((ListMessage) obj).items);
    }

    public int hashCode() {
        return this.items.hashCode();
    }
}
