package org.bitcoinj.store;

public class ChainFileLockedException extends BlockStoreException {
    public ChainFileLockedException(String str) {
        super(str);
    }

    public ChainFileLockedException(Throwable th) {
        super(th);
    }
}
