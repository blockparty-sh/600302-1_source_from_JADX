package org.bitcoinj.store;

public class BlockStoreException extends Exception {
    public BlockStoreException(String str) {
        super(str);
    }

    public BlockStoreException(Throwable th) {
        super(th);
    }

    public BlockStoreException(String str, Throwable th) {
        super(str, th);
    }
}
