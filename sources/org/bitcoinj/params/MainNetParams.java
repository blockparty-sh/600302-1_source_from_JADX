package org.bitcoinj.params;

import com.google.common.base.Preconditions;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;

public class MainNetParams extends AbstractBitcoinNetParams {
    public static final int MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 750;
    public static final int MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED = 950;
    public static final int MAINNET_MAJORITY_WINDOW = 1000;
    private static MainNetParams instance;

    public String getPaymentProtocolId() {
        return NetworkParameters.PAYMENT_PROTOCOL_ID_MAINNET;
    }

    public MainNetParams() {
        this.interval = NetworkParameters.INTERVAL;
        this.targetTimespan = NetworkParameters.TARGET_TIMESPAN;
        this.maxTarget = C3447Utils.decodeCompactBits(486604799);
        this.dumpedPrivateKeyHeader = 128;
        this.addressHeader = 0;
        this.p2shHeader = 5;
        this.acceptableAddressCodes = new int[]{this.addressHeader, this.p2shHeader};
        this.port = 8333;
        this.packetMagic = 3823236072L;
        this.bip32HeaderPub = 76067358;
        this.bip32HeaderPriv = 76066276;
        this.majorityEnforceBlockUpgrade = 750;
        this.majorityRejectBlockOutdated = 950;
        this.majorityWindow = 1000;
        this.genesisBlock.setDifficultyTarget(486604799);
        this.genesisBlock.setTime(1231006505);
        this.genesisBlock.setNonce(2083236893);
        this.f799id = NetworkParameters.ID_MAINNET;
        this.subsidyDecreaseBlockCount = 210000;
        this.spendableCoinbaseDepth = 100;
        String hashAsString = this.genesisBlock.getHashAsString();
        Preconditions.checkState(hashAsString.equals("000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f"), hashAsString);
        this.checkpoints.put(Integer.valueOf(91722), Sha256Hash.wrap("00000000000271a2dc26e7667f8419f2e15416dc6955e5a6c6cdf3f2574dd08e"));
        this.checkpoints.put(Integer.valueOf(91812), Sha256Hash.wrap("00000000000af0aed4792b1acee3d966af36cf5def14935db8de83d6f9306f2f"));
        this.checkpoints.put(Integer.valueOf(91842), Sha256Hash.wrap("00000000000a4d0a398161ffc163c503763b1f4360639393e0e4c8e300e0caec"));
        this.checkpoints.put(Integer.valueOf(91880), Sha256Hash.wrap("00000000000743f190a18c5577a3c2d2a1f610ae9601ac046a38084ccb7cd721"));
        this.checkpoints.put(Integer.valueOf(200000), Sha256Hash.wrap("000000000000034a7dedef4a161fa058a2d67a173a90155f3a2fe6fc132e0ebf"));
        this.checkpoints.put(Integer.valueOf(478559), Sha256Hash.wrap("000000000000000000651ef99cb9fcbe0dadde1d424bd9f15ff20136191a5eec"));
        this.dnsSeeds = new String[]{"seed.bitcoinabc.org", "seed-abc.bitcoinforks.org", "btccash-seeder.bitcoinunlimited.info", "seed.bitprim.org", "seed.deadalnix.me", "seeder.criptolayer.net"};
        this.httpSeeds = null;
        this.addrSeeds = null;
        this.uahfHeight = 478559;
        this.cashHardForkActivationTime = 1510600000;
        this.daaHeight = 504032;
    }

    public static synchronized MainNetParams get() {
        MainNetParams mainNetParams;
        synchronized (MainNetParams.class) {
            if (instance == null) {
                instance = new MainNetParams();
            }
            mainNetParams = instance;
        }
        return mainNetParams;
    }
}
