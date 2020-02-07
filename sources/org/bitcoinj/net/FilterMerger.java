package org.bitcoinj.net;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Iterator;
import java.util.LinkedList;
import org.bitcoinj.core.BloomFilter;
import org.bitcoinj.core.BloomFilter.BloomUpdate;
import org.bitcoinj.core.PeerFilterProvider;
import org.bitcoinj.protocols.channels.PaymentChannelServer;

public class FilterMerger {
    private final long bloomFilterTweak = ((long) (Math.random() * 9.223372036854776E18d));
    private int lastBloomFilterElementCount;
    private BloomFilter lastFilter;
    private volatile double vBloomFilterFPRate;

    public static class Result {
        public boolean changed;
        public long earliestKeyTimeSecs;
        public BloomFilter filter;
    }

    public FilterMerger(double d) {
        this.vBloomFilterFPRate = d;
    }

    public Result calculate(ImmutableList<PeerFilterProvider> immutableList) {
        boolean z;
        LinkedList newLinkedList = Lists.newLinkedList();
        try {
            UnmodifiableIterator it = immutableList.iterator();
            while (it.hasNext()) {
                PeerFilterProvider peerFilterProvider = (PeerFilterProvider) it.next();
                peerFilterProvider.beginBloomFilterCalculation();
                newLinkedList.add(peerFilterProvider);
            }
            Result result = new Result();
            result.earliestKeyTimeSecs = Long.MAX_VALUE;
            UnmodifiableIterator it2 = immutableList.iterator();
            boolean z2 = false;
            int i = 0;
            loop1:
            while (true) {
                z = false;
                while (true) {
                    if (!it2.hasNext()) {
                        break loop1;
                    }
                    PeerFilterProvider peerFilterProvider2 = (PeerFilterProvider) it2.next();
                    result.earliestKeyTimeSecs = Math.min(result.earliestKeyTimeSecs, peerFilterProvider2.getEarliestKeyCreationTime());
                    i += peerFilterProvider2.getBloomFilterElementCount();
                    if (z || peerFilterProvider2.isRequiringUpdateAllBloomFilter()) {
                        z = true;
                    }
                }
            }
            if (i > 0) {
                this.lastBloomFilterElementCount = i > this.lastBloomFilterElementCount ? i + 100 : this.lastBloomFilterElementCount;
                BloomUpdate bloomUpdate = z ? BloomUpdate.UPDATE_ALL : BloomUpdate.UPDATE_P2PUBKEY_ONLY;
                double d = this.vBloomFilterFPRate;
                BloomFilter bloomFilter = new BloomFilter(this.lastBloomFilterElementCount, d, this.bloomFilterTweak, bloomUpdate);
                UnmodifiableIterator it3 = immutableList.iterator();
                while (it3.hasNext()) {
                    bloomFilter.merge(((PeerFilterProvider) it3.next()).getBloomFilter(this.lastBloomFilterElementCount, d, this.bloomFilterTweak));
                }
                if (!bloomFilter.equals(this.lastFilter)) {
                    z2 = true;
                }
                result.changed = z2;
                this.lastFilter = bloomFilter;
                result.filter = bloomFilter;
            }
            result.earliestKeyTimeSecs -= PaymentChannelServer.DEFAULT_MAX_TIME_WINDOW;
            return result;
        } finally {
            Iterator it4 = newLinkedList.iterator();
            while (it4.hasNext()) {
                ((PeerFilterProvider) it4.next()).endBloomFilterCalculation();
            }
        }
    }

    public void setBloomFilterFPRate(double d) {
        this.vBloomFilterFPRate = d;
    }

    public double getBloomFilterFPRate() {
        return this.vBloomFilterFPRate;
    }

    public BloomFilter getLastFilter() {
        return this.lastFilter;
    }
}
