package org.bitcoinj.core.listeners;

import java.util.List;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.core.VerificationException;

public interface ReorganizeListener {
    void reorganize(StoredBlock storedBlock, List<StoredBlock> list, List<StoredBlock> list2) throws VerificationException;
}
