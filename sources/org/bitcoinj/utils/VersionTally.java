package org.bitcoinj.utils;

import java.util.Stack;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;

public class VersionTally {
    private final long[] versionWindow;
    private int versionWriteHead = 0;
    private int versionsStored = 0;

    public VersionTally(NetworkParameters networkParameters) {
        this.versionWindow = new long[networkParameters.getMajorityWindow()];
    }

    public void add(long j) {
        long[] jArr = this.versionWindow;
        int i = this.versionWriteHead;
        this.versionWriteHead = i + 1;
        jArr[i] = j;
        if (this.versionWriteHead == jArr.length) {
            this.versionWriteHead = 0;
        }
        this.versionsStored++;
    }

    public Integer getCountAtOrAbove(long j) {
        if (this.versionsStored < this.versionWindow.length) {
            return null;
        }
        int i = 0;
        int i2 = 0;
        while (true) {
            long[] jArr = this.versionWindow;
            if (i >= jArr.length) {
                return Integer.valueOf(i2);
            }
            if (jArr[i] >= j) {
                i2++;
            }
            i++;
        }
    }

    public void initialize(BlockStore blockStore, StoredBlock storedBlock) throws BlockStoreException {
        Stack stack = new Stack();
        stack.push(Long.valueOf(storedBlock.getHeader().getVersion()));
        for (int i = 0; i < this.versionWindow.length; i++) {
            storedBlock = storedBlock.getPrev(blockStore);
            if (storedBlock == null) {
                break;
            }
            stack.push(Long.valueOf(storedBlock.getHeader().getVersion()));
        }
        while (!stack.isEmpty()) {
            add(((Long) stack.pop()).longValue());
        }
    }

    public int size() {
        return this.versionWindow.length;
    }
}
