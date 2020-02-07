package org.bitcoinj.params;

import java.math.BigInteger;
import org.bitcoinj.core.Block;
import org.bitcoinj.core.NetworkParameters;
import org.spongycastle.crypto.tls.CipherSuite;

public class UnitTestParams extends AbstractBitcoinNetParams {
    public static final int TESTNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 4;
    public static final int TESTNET_MAJORITY_REJECT_BLOCK_OUTDATED = 6;
    public static final int UNITNET_MAJORITY_WINDOW = 8;
    private static UnitTestParams instance;

    public String getPaymentProtocolId() {
        return NetworkParameters.PAYMENT_PROTOCOL_ID_UNIT_TESTS;
    }

    public UnitTestParams() {
        this.f799id = NetworkParameters.ID_UNITTESTNET;
        this.packetMagic = 4108710900L;
        this.addressHeader = 111;
        this.p2shHeader = CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256;
        this.acceptableAddressCodes = new int[]{this.addressHeader, this.p2shHeader};
        this.maxTarget = new BigInteger("00ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", 16);
        this.genesisBlock.setTime(System.currentTimeMillis() / 1000);
        this.genesisBlock.setDifficultyTarget(Block.EASIEST_DIFFICULTY_TARGET);
        this.genesisBlock.solve();
        this.port = 18333;
        this.interval = 10;
        this.dumpedPrivateKeyHeader = 239;
        this.targetTimespan = 200000000;
        this.spendableCoinbaseDepth = 5;
        this.subsidyDecreaseBlockCount = 100;
        this.dnsSeeds = null;
        this.addrSeeds = null;
        this.bip32HeaderPub = 70617039;
        this.bip32HeaderPriv = 70615956;
        this.majorityEnforceBlockUpgrade = 3;
        this.majorityRejectBlockOutdated = 4;
        this.majorityWindow = 7;
        this.cashHardForkActivationTime = (System.currentTimeMillis() / 1000) + 86400;
        this.daaHeight = 1000000;
    }

    public static synchronized UnitTestParams get() {
        UnitTestParams unitTestParams;
        synchronized (UnitTestParams.class) {
            if (instance == null) {
                instance = new UnitTestParams();
            }
            unitTestParams = instance;
        }
        return unitTestParams;
    }
}
