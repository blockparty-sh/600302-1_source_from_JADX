package org.bitcoinj.core;

import org.bitcoinj.core.InventoryItem.C3406Type;

public class GetDataMessage extends ListMessage {
    public GetDataMessage(NetworkParameters networkParameters, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr);
    }

    public GetDataMessage(NetworkParameters networkParameters, byte[] bArr, MessageSerializer messageSerializer, int i) throws ProtocolException {
        super(networkParameters, bArr, messageSerializer, i);
    }

    public GetDataMessage(NetworkParameters networkParameters) {
        super(networkParameters);
    }

    public void addTransaction(Sha256Hash sha256Hash) {
        addItem(new InventoryItem(C3406Type.Transaction, sha256Hash));
    }

    public void addBlock(Sha256Hash sha256Hash) {
        addItem(new InventoryItem(C3406Type.Block, sha256Hash));
    }

    public void addFilteredBlock(Sha256Hash sha256Hash) {
        addItem(new InventoryItem(C3406Type.FilteredBlock, sha256Hash));
    }

    public Sha256Hash getHashOf(int i) {
        return ((InventoryItem) getItems().get(i)).hash;
    }
}
