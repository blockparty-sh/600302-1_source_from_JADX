package org.bitcoinj.core;

import com.google.common.base.Preconditions;
import org.bitcoinj.core.InventoryItem.C3406Type;

public class InventoryMessage extends ListMessage {
    public static final int MAX_INV_SIZE = 50000;

    public InventoryMessage(NetworkParameters networkParameters, byte[] bArr) throws ProtocolException {
        super(networkParameters, bArr);
    }

    public InventoryMessage(NetworkParameters networkParameters, byte[] bArr, MessageSerializer messageSerializer, int i) throws ProtocolException {
        super(networkParameters, bArr, messageSerializer, i);
    }

    public InventoryMessage(NetworkParameters networkParameters) {
        super(networkParameters);
    }

    public void addBlock(Block block) {
        addItem(new InventoryItem(C3406Type.Block, block.getHash()));
    }

    public void addTransaction(Transaction transaction) {
        addItem(new InventoryItem(C3406Type.Transaction, transaction.getHash()));
    }

    public static InventoryMessage with(Transaction... transactionArr) {
        Preconditions.checkArgument(transactionArr.length > 0);
        InventoryMessage inventoryMessage = new InventoryMessage(transactionArr[0].getParams());
        for (Transaction addTransaction : transactionArr) {
            inventoryMessage.addTransaction(addTransaction);
        }
        return inventoryMessage;
    }
}
