package org.bitcoinj.params;

import com.google.common.base.Preconditions;
import java.math.BigInteger;
import org.bitcoinj.core.Block;
import org.bitcoinj.core.NetworkParameters;

public class RegTestParams extends TestNet2Params {
    private static final BigInteger MAX_TARGET = new BigInteger("7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", 16);
    private static Block genesis;
    private static RegTestParams instance;

    public boolean allowEmptyPeerChain() {
        return true;
    }

    public String getPaymentProtocolId() {
        return NetworkParameters.PAYMENT_PROTOCOL_ID_REGTEST;
    }

    public RegTestParams() {
        this.interval = Integer.MAX_VALUE;
        this.maxTarget = MAX_TARGET;
        this.subsidyDecreaseBlockCount = 150;
        this.port = 18444;
        this.f799id = NetworkParameters.ID_REGTEST;
        this.majorityEnforceBlockUpgrade = 750;
        this.majorityRejectBlockOutdated = 950;
        this.majorityWindow = 1000;
        this.uahfHeight = 0;
        this.cashHardForkActivationTime = 0;
    }

    public Block getGenesisBlock() {
        Block block;
        synchronized (RegTestParams.class) {
            if (genesis == null) {
                genesis = super.getGenesisBlock();
                genesis.setNonce(2);
                genesis.setDifficultyTarget(Block.EASIEST_DIFFICULTY_TARGET);
                genesis.setTime(1296688602);
                Preconditions.checkState(genesis.getHashAsString().toLowerCase().equals("0f9188f13cb7b2c71f2a335e3a4fc328bf5beb436012afca590b1a11466e2206"));
            }
            block = genesis;
        }
        return block;
    }

    public static synchronized RegTestParams get() {
        RegTestParams regTestParams;
        synchronized (RegTestParams.class) {
            if (instance == null) {
                instance = new RegTestParams();
            }
            regTestParams = instance;
        }
        return regTestParams;
    }
}
