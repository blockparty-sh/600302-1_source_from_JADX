package org.bitcoinj.core;

public interface PeerFilterProvider {
    void beginBloomFilterCalculation();

    void endBloomFilterCalculation();

    BloomFilter getBloomFilter(int i, double d, long j);

    int getBloomFilterElementCount();

    long getEarliestKeyCreationTime();

    boolean isRequiringUpdateAllBloomFilter();
}
