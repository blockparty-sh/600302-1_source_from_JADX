package org.bitcoinj.params;

import com.google.common.base.Preconditions;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.NetworkParameters;
import org.spongycastle.crypto.tls.CipherSuite;

public class TestNet2Params extends AbstractBitcoinNetParams {
    public static final int TESTNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 51;
    public static final int TESTNET_MAJORITY_REJECT_BLOCK_OUTDATED = 75;
    public static final int TESTNET_MAJORITY_WINDOW = 100;
    private static TestNet2Params instance;

    public String getPaymentProtocolId() {
        return null;
    }

    public TestNet2Params() {
        this.f799id = NetworkParameters.ID_TESTNET;
        this.packetMagic = 3669344250L;
        this.port = 18333;
        this.addressHeader = 111;
        this.p2shHeader = CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256;
        this.acceptableAddressCodes = new int[]{this.addressHeader, this.p2shHeader};
        this.interval = NetworkParameters.INTERVAL;
        this.targetTimespan = NetworkParameters.TARGET_TIMESPAN;
        this.maxTarget = C3447Utils.decodeCompactBits(487587839);
        this.dumpedPrivateKeyHeader = 239;
        this.genesisBlock.setTime(1296688602);
        this.genesisBlock.setDifficultyTarget(487063544);
        this.genesisBlock.setNonce(384568319);
        this.spendableCoinbaseDepth = 100;
        this.subsidyDecreaseBlockCount = 210000;
        Preconditions.checkState(this.genesisBlock.getHashAsString().equals("00000007199508e34a9ff81e6ec0c477a4cccff2a4767a8eee39c11db367b008"));
        this.dnsSeeds = null;
        this.addrSeeds = null;
        this.bip32HeaderPub = 70617039;
        this.bip32HeaderPriv = 70615956;
        this.majorityEnforceBlockUpgrade = 51;
        this.majorityRejectBlockOutdated = 75;
        this.majorityWindow = 100;
        this.cashHardForkActivationTime = 1510600000;
        this.daaHeight = 1188697;
    }

    public static synchronized TestNet2Params get() {
        TestNet2Params testNet2Params;
        synchronized (TestNet2Params.class) {
            if (instance == null) {
                instance = new TestNet2Params();
            }
            testNet2Params = instance;
        }
        return testNet2Params;
    }
}
