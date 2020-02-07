package org.bitcoinj.core;

import com.google.common.base.Objects;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;
import org.bitcoinj.core.Block.VerifyFlag;
import org.bitcoinj.net.discovery.HttpDiscovery.Details;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.params.TestNet2Params;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.params.UnitTestParams;
import org.bitcoinj.script.Script;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.utils.MonetaryFormat;
import org.bitcoinj.utils.VersionTally;

public abstract class NetworkParameters {
    public static final int BIP16_ENFORCE_TIME = 1333238400;
    public static final String ID_MAINNET = "org.bitcoin.production";
    public static final String ID_REGTEST = "org.bitcoin.regtest";
    public static final String ID_TESTNET = "org.bitcoin.test";
    public static final String ID_UNITTESTNET = "org.bitcoinj.unittest";
    public static final int INTERVAL = 2016;
    public static final long MAX_COINS = 21000000;
    public static final Coin MAX_MONEY = Coin.COIN.multiply(MAX_COINS);
    public static final String PAYMENT_PROTOCOL_ID_MAINNET = "main";
    public static final String PAYMENT_PROTOCOL_ID_REGTEST = "regtest";
    public static final String PAYMENT_PROTOCOL_ID_TESTNET = "test";
    public static final String PAYMENT_PROTOCOL_ID_UNIT_TESTS = "unittest";
    public static final byte[] SATOSHI_KEY = C3447Utils.HEX.decode("04fc9702847840aaf195de8442ebecedf5b095cdbb9bc716bda9110971b28a49e0ead8564ff0db22209e0374782c093bb899692d524e9d6a6956e7c5ecbcd68284");
    public static final int TARGET_SPACING = 600;
    public static final int TARGET_TIMESPAN = 1209600;
    protected int[] acceptableAddressCodes;
    protected int[] addrSeeds;
    protected int addressHeader;
    protected byte[] alertSigningKey = SATOSHI_KEY;
    protected int bip32HeaderPriv;
    protected int bip32HeaderPub;
    protected Map<Integer, Sha256Hash> checkpoints = new HashMap();
    protected transient MessageSerializer defaultSerializer = null;
    protected String[] dnsSeeds;
    protected int dumpedPrivateKeyHeader;
    protected Block genesisBlock = createGenesis(this);
    protected Details[] httpSeeds = new Details[0];

    /* renamed from: id */
    protected String f799id;
    protected int interval;
    protected int majorityEnforceBlockUpgrade;
    protected int majorityRejectBlockOutdated;
    protected int majorityWindow;
    protected BigInteger maxTarget;
    protected int p2shHeader;
    protected long packetMagic;
    protected int port;
    protected int spendableCoinbaseDepth;
    protected int subsidyDecreaseBlockCount;
    protected int targetTimespan;

    public enum ProtocolVersion {
        MINIMUM(70000),
        PONG(60001),
        BLOOM_FILTER(70000),
        CURRENT(70013);
        
        private final int bitcoinProtocol;

        private ProtocolVersion(int i) {
            this.bitcoinProtocol = i;
        }

        public int getBitcoinProtocolVersion() {
            return this.bitcoinProtocol;
        }
    }

    public boolean allowEmptyPeerChain() {
        return true;
    }

    public abstract void checkDifficultyTransitions(StoredBlock storedBlock, Block block, BlockStore blockStore, AbstractBlockChain abstractBlockChain) throws VerificationException, BlockStoreException;

    public abstract Coin getMaxMoney();

    public abstract Coin getMinNonDustOutput();

    public abstract MonetaryFormat getMonetaryFormat();

    public abstract String getPaymentProtocolId();

    public abstract int getProtocolVersionNum(ProtocolVersion protocolVersion);

    public abstract BitcoinSerializer getSerializer(boolean z);

    public abstract String getUriScheme();

    public abstract boolean hasMaxMoney();

    protected NetworkParameters() {
    }

    private static Block createGenesis(NetworkParameters networkParameters) {
        Block block = new Block(networkParameters, 1);
        Transaction transaction = new Transaction(networkParameters);
        try {
            transaction.addInput(new TransactionInput(networkParameters, transaction, C3447Utils.HEX.decode("04ffff001d0104455468652054696d65732030332f4a616e2f32303039204368616e63656c6c6f72206f6e206272696e6b206f66207365636f6e64206261696c6f757420666f722062616e6b73")));
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            Script.writeBytes(byteArrayOutputStream, C3447Utils.HEX.decode("04678afdb0fe5548271967f1a67130b7105cd6a828e03909a67962e0ea1f61deb649f6bc3f4cef38c4f35504e51ec112de5c384df7ba0b8d578a4c702b6bf11d5f"));
            byteArrayOutputStream.write(172);
            transaction.addOutput(new TransactionOutput(networkParameters, transaction, Coin.FIFTY_COINS, byteArrayOutputStream.toByteArray()));
            block.addTransaction(transaction);
            return block;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public static NetworkParameters testNet() {
        return TestNet3Params.get();
    }

    @Deprecated
    public static NetworkParameters testNet2() {
        return TestNet2Params.get();
    }

    @Deprecated
    public static NetworkParameters testNet3() {
        return TestNet3Params.get();
    }

    @Deprecated
    public static NetworkParameters prodNet() {
        return MainNetParams.get();
    }

    @Deprecated
    public static NetworkParameters unitTests() {
        return UnitTestParams.get();
    }

    @Deprecated
    public static NetworkParameters regTests() {
        return RegTestParams.get();
    }

    public String getId() {
        return this.f799id;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return getId().equals(((NetworkParameters) obj).getId());
    }

    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Nullable
    public static NetworkParameters fromID(String str) {
        if (str.equals(ID_MAINNET)) {
            return MainNetParams.get();
        }
        if (str.equals(ID_TESTNET)) {
            return TestNet3Params.get();
        }
        if (str.equals(ID_UNITTESTNET)) {
            return UnitTestParams.get();
        }
        if (str.equals(ID_REGTEST)) {
            return RegTestParams.get();
        }
        return null;
    }

    @Nullable
    public static NetworkParameters fromPmtProtocolID(String str) {
        if (str.equals(PAYMENT_PROTOCOL_ID_MAINNET)) {
            return MainNetParams.get();
        }
        if (str.equals(PAYMENT_PROTOCOL_ID_TESTNET)) {
            return TestNet3Params.get();
        }
        if (str.equals(PAYMENT_PROTOCOL_ID_UNIT_TESTS)) {
            return UnitTestParams.get();
        }
        if (str.equals(PAYMENT_PROTOCOL_ID_REGTEST)) {
            return RegTestParams.get();
        }
        return null;
    }

    public int getSpendableCoinbaseDepth() {
        return this.spendableCoinbaseDepth;
    }

    public boolean passesCheckpoint(int i, Sha256Hash sha256Hash) {
        Sha256Hash sha256Hash2 = (Sha256Hash) this.checkpoints.get(Integer.valueOf(i));
        return sha256Hash2 == null || sha256Hash2.equals(sha256Hash);
    }

    public boolean isCheckpoint(int i) {
        return ((Sha256Hash) this.checkpoints.get(Integer.valueOf(i))) != null;
    }

    public int getSubsidyDecreaseBlockCount() {
        return this.subsidyDecreaseBlockCount;
    }

    public String[] getDnsSeeds() {
        return this.dnsSeeds;
    }

    public int[] getAddrSeeds() {
        return this.addrSeeds;
    }

    public Details[] getHttpSeeds() {
        return this.httpSeeds;
    }

    public Block getGenesisBlock() {
        return this.genesisBlock;
    }

    public int getPort() {
        return this.port;
    }

    public long getPacketMagic() {
        return this.packetMagic;
    }

    public int getAddressHeader() {
        return this.addressHeader;
    }

    public int getP2SHHeader() {
        return this.p2shHeader;
    }

    public int getDumpedPrivateKeyHeader() {
        return this.dumpedPrivateKeyHeader;
    }

    public int getTargetTimespan() {
        return this.targetTimespan;
    }

    public int[] getAcceptableAddressCodes() {
        return this.acceptableAddressCodes;
    }

    public int getInterval() {
        return this.interval;
    }

    public BigInteger getMaxTarget() {
        return this.maxTarget;
    }

    public byte[] getAlertSigningKey() {
        return this.alertSigningKey;
    }

    public int getBip32HeaderPub() {
        return this.bip32HeaderPub;
    }

    public int getBip32HeaderPriv() {
        return this.bip32HeaderPriv;
    }

    public final MessageSerializer getDefaultSerializer() {
        if (this.defaultSerializer == null) {
            synchronized (this) {
                if (this.defaultSerializer == null) {
                    this.defaultSerializer = getSerializer(false);
                }
            }
        }
        return this.defaultSerializer;
    }

    public int getMajorityEnforceBlockUpgrade() {
        return this.majorityEnforceBlockUpgrade;
    }

    public int getMajorityRejectBlockOutdated() {
        return this.majorityRejectBlockOutdated;
    }

    public int getMajorityWindow() {
        return this.majorityWindow;
    }

    public EnumSet<VerifyFlag> getBlockVerificationFlags(Block block, VersionTally versionTally, Integer num) {
        EnumSet<VerifyFlag> noneOf = EnumSet.noneOf(VerifyFlag.class);
        if (block.isBIP34()) {
            Integer countAtOrAbove = versionTally.getCountAtOrAbove(2);
            if (countAtOrAbove != null && countAtOrAbove.intValue() >= getMajorityEnforceBlockUpgrade()) {
                noneOf.add(VerifyFlag.HEIGHT_IN_COINBASE);
            }
        }
        return noneOf;
    }

    public EnumSet<Script.VerifyFlag> getTransactionVerificationFlags(Block block, Transaction transaction, VersionTally versionTally, Integer num) {
        EnumSet<Script.VerifyFlag> noneOf = EnumSet.noneOf(Script.VerifyFlag.class);
        if (block.getTimeSeconds() >= 1333238400) {
            noneOf.add(Script.VerifyFlag.P2SH);
        }
        if (block.getVersion() >= 4 && versionTally.getCountAtOrAbove(4).intValue() > getMajorityEnforceBlockUpgrade()) {
            noneOf.add(Script.VerifyFlag.CHECKLOCKTIMEVERIFY);
        }
        return noneOf;
    }
}
