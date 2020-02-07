package org.bitcoinj.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

public class TransactionOutputChanges {
    public final List<UTXO> txOutsCreated;
    public final List<UTXO> txOutsSpent;

    public TransactionOutputChanges(List<UTXO> list, List<UTXO> list2) {
        this.txOutsCreated = list;
        this.txOutsSpent = list2;
    }

    public TransactionOutputChanges(InputStream inputStream) throws IOException {
        int read = (inputStream.read() & 255) | ((inputStream.read() & 255) << 8) | ((inputStream.read() & 255) << 16) | ((inputStream.read() & 255) << 24);
        this.txOutsCreated = new LinkedList();
        for (int i = 0; i < read; i++) {
            this.txOutsCreated.add(new UTXO(inputStream));
        }
        int read2 = (inputStream.read() & 255) | ((inputStream.read() & 255) << 8) | ((inputStream.read() & 255) << 16) | ((inputStream.read() & 255) << 24);
        this.txOutsSpent = new LinkedList();
        for (int i2 = 0; i2 < read2; i2++) {
            this.txOutsSpent.add(new UTXO(inputStream));
        }
    }

    public void serializeToStream(OutputStream outputStream) throws IOException {
        int size = this.txOutsCreated.size();
        outputStream.write(size & 255);
        outputStream.write((size >> 8) & 255);
        outputStream.write((size >> 16) & 255);
        outputStream.write((size >> 24) & 255);
        for (UTXO serializeToStream : this.txOutsCreated) {
            serializeToStream.serializeToStream(outputStream);
        }
        int size2 = this.txOutsSpent.size();
        outputStream.write(size2 & 255);
        outputStream.write((size2 >> 8) & 255);
        outputStream.write((size2 >> 16) & 255);
        outputStream.write((size2 >> 24) & 255);
        for (UTXO serializeToStream2 : this.txOutsSpent) {
            serializeToStream2.serializeToStream(outputStream);
        }
    }
}
