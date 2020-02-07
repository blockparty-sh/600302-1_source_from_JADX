package org.bitcoinj.core.listeners;

import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.core.VerificationException;

public interface NewBestBlockListener {
    void notifyNewBestBlock(StoredBlock storedBlock) throws VerificationException;
}
