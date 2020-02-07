package org.bitcoinj.params;

import com.google.common.base.Preconditions;
import java.util.Date;
import org.bitcoinj.core.AbstractBlockChain;
import org.bitcoinj.core.Block;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;
import org.spongycastle.crypto.tls.CipherSuite;

public class TestNet3Params extends AbstractBitcoinNetParams {
    private static TestNet3Params instance;
    private static final Date testnetDiffDate = new Date(1329264000000L);

    public String getPaymentProtocolId() {
        return NetworkParameters.PAYMENT_PROTOCOL_ID_TESTNET;
    }

    public TestNet3Params() {
        this.f799id = NetworkParameters.ID_TESTNET;
        this.packetMagic = 4108710900L;
        this.interval = NetworkParameters.INTERVAL;
        this.targetTimespan = NetworkParameters.TARGET_TIMESPAN;
        this.maxTarget = C3447Utils.decodeCompactBits(486604799);
        this.port = 18333;
        this.addressHeader = 111;
        this.p2shHeader = CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256;
        this.acceptableAddressCodes = new int[]{this.addressHeader, this.p2shHeader};
        this.dumpedPrivateKeyHeader = 239;
        this.genesisBlock.setTime(1296688602);
        this.genesisBlock.setDifficultyTarget(486604799);
        this.genesisBlock.setNonce(414098458);
        this.spendableCoinbaseDepth = 100;
        this.subsidyDecreaseBlockCount = 210000;
        Preconditions.checkState(this.genesisBlock.getHashAsString().equals("000000000933ea01ad0ee984209779baaec3ced90fa3f408719526f8d77f4943"));
        this.alertSigningKey = C3447Utils.HEX.decode("04302390343f91cc401d56d68b123028bf52e5fca1939df127f63c6467cdf9c8e2c14b61104cf817d0b780da337893ecc4aaff1309e536162dabbdb45200ca2b0a");
        this.dnsSeeds = new String[]{"testnet-seed.bitcoinabc.org", "testnet-seed-abc.bitcoinforks.org", "testnet-seed.bitcoinunlimited.info", "testnet-seed.bitprim.org", "testnet-seed.deadalnix.me", "testnet-seeder.criptolayer.net"};
        this.addrSeeds = null;
        this.bip32HeaderPub = 70617039;
        this.bip32HeaderPriv = 70615956;
        this.majorityEnforceBlockUpgrade = 51;
        this.majorityRejectBlockOutdated = 75;
        this.majorityWindow = 100;
        this.uahfHeight = 1155876;
        this.cashHardForkActivationTime = 1510600000;
        this.daaHeight = 1188697;
    }

    public static synchronized TestNet3Params get() {
        TestNet3Params testNet3Params;
        synchronized (TestNet3Params.class) {
            if (instance == null) {
                instance = new TestNet3Params();
            }
            testNet3Params = instance;
        }
        return testNet3Params;
    }

    public void checkDifficultyTransitions(StoredBlock storedBlock, Block block, BlockStore blockStore, AbstractBlockChain abstractBlockChain) throws VerificationException, BlockStoreException {
        if (storedBlock.getHeight() >= this.daaHeight || isDifficultyTransitionPoint(storedBlock) || !block.getTime().after(testnetDiffDate)) {
            super.checkDifficultyTransitions(storedBlock, block, blockStore, abstractBlockChain);
            return;
        }
        long timeSeconds = block.getTimeSeconds() - storedBlock.getHeader().getTimeSeconds();
        if (timeSeconds >= 0 && timeSeconds <= 1200) {
            while (!storedBlock.getHeader().equals(getGenesisBlock()) && storedBlock.getHeight() % getInterval() != 0 && storedBlock.getHeader().getDifficultyTargetAsInteger().equals(getMaxTarget())) {
                storedBlock = storedBlock.getPrev(blockStore);
            }
            if (!storedBlock.getHeader().getDifficultyTargetAsInteger().equals(block.getDifficultyTargetAsInteger())) {
                StringBuilder sb = new StringBuilder();
                sb.append("Testnet block transition that is not allowed: ");
                sb.append(Long.toHexString(storedBlock.getHeader().getDifficultyTarget()));
                sb.append(" vs ");
                sb.append(Long.toHexString(block.getDifficultyTarget()));
                throw new VerificationException(sb.toString());
            }
        }
    }

    /* access modifiers changed from: protected */
    public void checkNextCashWorkRequired(StoredBlock storedBlock, Block block, BlockStore blockStore) {
        Preconditions.checkState(storedBlock.getHeight() >= this.interval);
        try {
            long timeSeconds = block.getTimeSeconds() - storedBlock.getHeader().getTimeSeconds();
            if (timeSeconds < 0 || timeSeconds <= 1200) {
                StoredBlock GetSuitableBlock = GetSuitableBlock(storedBlock, blockStore);
                int i = 144;
                while (i > 0) {
                    storedBlock = storedBlock.getPrev(blockStore);
                    if (storedBlock != null) {
                        i--;
                    } else {
                        return;
                    }
                }
                verifyDifficulty(ComputeTarget(GetSuitableBlock(storedBlock, blockStore), GetSuitableBlock), block);
            } else if (!this.maxTarget.equals(block.getDifficultyTargetAsInteger())) {
                StringBuilder sb = new StringBuilder();
                sb.append("Testnet block transition that is not allowed: ");
                sb.append(Long.toHexString(C3447Utils.encodeCompactBits(this.maxTarget)));
                sb.append(" (required min difficulty) vs ");
                sb.append(Long.toHexString(block.getDifficultyTarget()));
                throw new VerificationException(sb.toString());
            }
        } catch (BlockStoreException unused) {
        }
    }
}
