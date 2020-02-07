package org.bitcoinj.params;

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import java.math.BigInteger;
import java.util.concurrent.TimeUnit;
import org.bitcoinj.core.AbstractBlockChain;
import org.bitcoinj.core.BitcoinSerializer;
import org.bitcoinj.core.Block;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.NetworkParameters.ProtocolVersion;
import org.bitcoinj.core.StoredBlock;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.store.BlockStore;
import org.bitcoinj.store.BlockStoreException;
import org.bitcoinj.utils.MonetaryFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBitcoinNetParams extends NetworkParameters {
    public static final String BITCOIN_SCHEME = "bitcoincash";
    private static BigInteger LARGEST_HASH = BigInteger.ONE.shiftLeft(256);
    private static final Logger log = LoggerFactory.getLogger(AbstractBitcoinNetParams.class);
    protected long cashHardForkActivationTime;
    protected int daaHeight;
    int uahfHeight = 478559;

    public String getUriScheme() {
        return "bitcoincash";
    }

    public boolean hasMaxMoney() {
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean isDifficultyTransitionPoint(StoredBlock storedBlock) {
        return (storedBlock.getHeight() + 1) % getInterval() == 0;
    }

    public void checkDifficultyTransitions(StoredBlock storedBlock, Block block, BlockStore blockStore, AbstractBlockChain abstractBlockChain) throws VerificationException, BlockStoreException {
        Block header = storedBlock.getHeader();
        if (storedBlock.getHeight() + 1 >= this.daaHeight) {
            checkNextCashWorkRequired(storedBlock, block, blockStore);
            return;
        }
        int i = 0;
        if (!isDifficultyTransitionPoint(storedBlock)) {
            String str = "Unexpected change in difficulty at height ";
            String str2 = " vs ";
            String str3 = ": ";
            if (!storedBlock.getHeader().getDifficultyTargetAsInteger().equals(getMaxTarget())) {
                StoredBlock storedBlock2 = blockStore.get(header.getHash());
                while (i < 6) {
                    if (storedBlock2 != null) {
                        storedBlock2 = blockStore.get(storedBlock2.getHeader().getPrevBlockHash());
                        i++;
                    } else {
                        return;
                    }
                }
                try {
                    if (AbstractBlockChain.getMedianTimestampOfRecentBlocks(storedBlock, blockStore) - AbstractBlockChain.getMedianTimestampOfRecentBlocks(storedBlock2, blockStore) >= 43200) {
                        BigInteger difficultyTargetAsInteger = storedBlock.getHeader().getDifficultyTargetAsInteger();
                        BigInteger add = difficultyTargetAsInteger.add(difficultyTargetAsInteger.shiftRight(2));
                        if (add.compareTo(getMaxTarget()) > 0) {
                            add = getMaxTarget();
                        }
                        if (block.getDifficultyTarget() != C3447Utils.encodeCompactBits(add)) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Unexpected change in difficulty [6 blocks >12 hours] at height ");
                            sb.append(storedBlock.getHeight());
                            sb.append(str3);
                            sb.append(Long.toHexString(block.getDifficultyTarget()));
                            sb.append(str2);
                            sb.append(C3447Utils.encodeCompactBits(add));
                            throw new VerificationException(sb.toString());
                        }
                    } else if (block.getDifficultyTarget() != header.getDifficultyTarget()) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(str);
                        sb2.append(storedBlock.getHeight());
                        sb2.append(str3);
                        sb2.append(Long.toHexString(block.getDifficultyTarget()));
                        sb2.append(str2);
                        sb2.append(Long.toHexString(header.getDifficultyTarget()));
                        throw new VerificationException(sb2.toString());
                    }
                } catch (NullPointerException unused) {
                }
            } else if (block.getDifficultyTarget() != header.getDifficultyTarget()) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(str);
                sb3.append(storedBlock.getHeight());
                sb3.append(str3);
                sb3.append(Long.toHexString(block.getDifficultyTarget()));
                sb3.append(str2);
                sb3.append(Long.toHexString(header.getDifficultyTarget()));
                throw new VerificationException(sb3.toString());
            }
        } else {
            Stopwatch createStarted = Stopwatch.createStarted();
            StoredBlock storedBlock3 = blockStore.get(header.getHash());
            while (i < getInterval() - 1) {
                if (storedBlock3 != null) {
                    storedBlock3 = blockStore.get(storedBlock3.getHeader().getPrevBlockHash());
                    i++;
                } else {
                    throw new VerificationException("Difficulty transition point but we did not find a way back to the genesis block.");
                }
            }
            createStarted.stop();
            if (createStarted.elapsed(TimeUnit.MILLISECONDS) > 50) {
                log.info("Difficulty transition traversal took {}", (Object) createStarted);
            }
            int timeSeconds = (int) (header.getTimeSeconds() - storedBlock3.getHeader().getTimeSeconds());
            int targetTimespan = getTargetTimespan();
            int i2 = targetTimespan / 4;
            if (timeSeconds < i2) {
                timeSeconds = i2;
            }
            int i3 = targetTimespan * 4;
            if (timeSeconds > i3) {
                timeSeconds = i3;
            }
            verifyDifficulty(C3447Utils.decodeCompactBits(header.getDifficultyTarget()).multiply(BigInteger.valueOf((long) timeSeconds)).divide(BigInteger.valueOf((long) targetTimespan)), block);
        }
    }

    /* access modifiers changed from: 0000 */
    public void verifyDifficulty(BigInteger bigInteger, Block block) {
        if (bigInteger.compareTo(getMaxTarget()) > 0) {
            log.info("Difficulty hit proof of work limit: {}", (Object) bigInteger.toString(16));
            bigInteger = getMaxTarget();
        }
        int difficultyTarget = ((int) (block.getDifficultyTarget() >>> 24)) - 3;
        long difficultyTarget2 = block.getDifficultyTarget();
        long encodeCompactBits = C3447Utils.encodeCompactBits(bigInteger.and(BigInteger.valueOf(16777215).shiftLeft(difficultyTarget * 8)));
        if (encodeCompactBits != difficultyTarget2) {
            StringBuilder sb = new StringBuilder();
            sb.append("Network provided difficulty bits do not match what was calculated: ");
            sb.append(Long.toHexString(encodeCompactBits));
            sb.append(" vs ");
            sb.append(Long.toHexString(difficultyTarget2));
            throw new VerificationException(sb.toString());
        }
    }

    /* access modifiers changed from: 0000 */
    public BigInteger ComputeTarget(StoredBlock storedBlock, StoredBlock storedBlock2) {
        Preconditions.checkState(storedBlock2.getHeight() > storedBlock.getHeight());
        BigInteger multiply = storedBlock2.getChainWork().subtract(storedBlock.getChainWork()).multiply(BigInteger.valueOf(600));
        long timeSeconds = storedBlock2.getHeader().getTimeSeconds() - storedBlock.getHeader().getTimeSeconds();
        long j = 43200;
        if (timeSeconds > 172800) {
            j = 172800;
        } else if (timeSeconds >= 43200) {
            j = timeSeconds;
        }
        return LARGEST_HASH.divide(multiply.divide(BigInteger.valueOf(j))).subtract(BigInteger.ONE);
    }

    /* access modifiers changed from: 0000 */
    public StoredBlock GetSuitableBlock(StoredBlock storedBlock, BlockStore blockStore) throws BlockStoreException {
        StoredBlock[] storedBlockArr = new StoredBlock[3];
        storedBlockArr[2] = storedBlock;
        storedBlockArr[1] = storedBlock.getPrev(blockStore);
        storedBlockArr[0] = storedBlockArr[1].getPrev(blockStore);
        if (storedBlockArr[0].getHeader().getTimeSeconds() > storedBlockArr[2].getHeader().getTimeSeconds()) {
            StoredBlock storedBlock2 = storedBlockArr[0];
            storedBlockArr[0] = storedBlockArr[2];
            storedBlockArr[2] = storedBlock2;
        }
        if (storedBlockArr[0].getHeader().getTimeSeconds() > storedBlockArr[1].getHeader().getTimeSeconds()) {
            StoredBlock storedBlock3 = storedBlockArr[0];
            storedBlockArr[0] = storedBlockArr[1];
            storedBlockArr[1] = storedBlock3;
        }
        if (storedBlockArr[1].getHeader().getTimeSeconds() > storedBlockArr[2].getHeader().getTimeSeconds()) {
            StoredBlock storedBlock4 = storedBlockArr[1];
            storedBlockArr[1] = storedBlockArr[2];
            storedBlockArr[2] = storedBlock4;
        }
        return storedBlockArr[1];
    }

    /* access modifiers changed from: protected */
    public void checkNextCashWorkRequired(StoredBlock storedBlock, Block block, BlockStore blockStore) {
        Preconditions.checkState(storedBlock.getHeight() >= this.interval);
        try {
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
        } catch (BlockStoreException unused) {
        }
    }

    public Coin getMaxMoney() {
        return MAX_MONEY;
    }

    public Coin getMinNonDustOutput() {
        return Transaction.MIN_NONDUST_OUTPUT;
    }

    public MonetaryFormat getMonetaryFormat() {
        return new MonetaryFormat();
    }

    public int getProtocolVersionNum(ProtocolVersion protocolVersion) {
        return protocolVersion.getBitcoinProtocolVersion();
    }

    public BitcoinSerializer getSerializer(boolean z) {
        return new BitcoinSerializer(this, z);
    }
}
