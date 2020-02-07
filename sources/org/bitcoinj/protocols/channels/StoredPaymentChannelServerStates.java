package org.bitcoinj.protocols.channels;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.SettableFuture;
import com.google.protobuf.ByteString;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.Nullable;
import net.jcip.annotations.GuardedBy;
import org.bitcoinj.core.C3447Utils;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.InsufficientMoneyException;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionBroadcaster;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.protocols.channels.ServerState.StoredServerPaymentChannel;
import org.bitcoinj.protocols.channels.ServerState.StoredServerPaymentChannels;
import org.bitcoinj.protocols.channels.ServerState.StoredServerPaymentChannels.Builder;
import org.bitcoinj.utils.Threading;
import org.bitcoinj.wallet.C3530Wallet;
import org.bitcoinj.wallet.WalletExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoredPaymentChannelServerStates implements WalletExtension {
    public static final long CHANNEL_EXPIRE_OFFSET = -7200;
    static final String EXTENSION_ID = StoredPaymentChannelServerStates.class.getName();
    static final int MAX_SECONDS_TO_WAIT_FOR_BROADCASTER_TO_BE_SET = 10;
    /* access modifiers changed from: private */
    public static final Logger log = LoggerFactory.getLogger(StoredPaymentChannelServerStates.class);
    private final SettableFuture<TransactionBroadcaster> broadcasterFuture = SettableFuture.create();
    private final Timer channelTimeoutHandler = new Timer(true);
    private final ReentrantLock lock = Threading.lock("StoredPaymentChannelServerStates");
    @GuardedBy("lock")
    @VisibleForTesting
    final Map<Sha256Hash, StoredServerChannel> mapChannels = new HashMap();
    private C3530Wallet wallet;

    public boolean isWalletExtensionMandatory() {
        return false;
    }

    public StoredPaymentChannelServerStates(@Nullable C3530Wallet wallet2, TransactionBroadcaster transactionBroadcaster) {
        setTransactionBroadcaster(transactionBroadcaster);
        this.wallet = wallet2;
    }

    public StoredPaymentChannelServerStates(@Nullable C3530Wallet wallet2) {
        this.wallet = wallet2;
    }

    public final void setTransactionBroadcaster(TransactionBroadcaster transactionBroadcaster) {
        this.broadcasterFuture.set(Preconditions.checkNotNull(transactionBroadcaster));
    }

    @Nullable
    public static StoredPaymentChannelServerStates getFromWallet(C3530Wallet wallet2) {
        return (StoredPaymentChannelServerStates) wallet2.getExtensions().get(EXTENSION_ID);
    }

    public void closeChannel(StoredServerChannel storedServerChannel) {
        this.lock.lock();
        try {
            if (this.mapChannels.remove(storedServerChannel.contract.getHash()) != null) {
                this.lock.unlock();
                synchronized (storedServerChannel) {
                    storedServerChannel.closeConnectedHandler();
                    try {
                        storedServerChannel.getOrCreateState(this.wallet, getBroadcaster()).close();
                    } catch (InsufficientMoneyException e) {
                        log.error("Exception when closing channel", (Throwable) e);
                    } catch (VerificationException e2) {
                        log.error("Exception when closing channel", (Throwable) e2);
                    }
                    storedServerChannel.state = null;
                }
                updatedChannel(storedServerChannel);
            }
        } finally {
            this.lock.unlock();
        }
    }

    private TransactionBroadcaster getBroadcaster() {
        try {
            return (TransactionBroadcaster) this.broadcasterFuture.get(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e2) {
            throw new RuntimeException(e2);
        } catch (TimeoutException e3) {
            String str = "Transaction broadcaster not set";
            log.error(str);
            throw new RuntimeException(str, e3);
        }
    }

    public StoredServerChannel getChannel(Sha256Hash sha256Hash) {
        this.lock.lock();
        try {
            return (StoredServerChannel) this.mapChannels.get(sha256Hash);
        } finally {
            this.lock.unlock();
        }
    }

    public Map<Sha256Hash, StoredServerChannel> getChannelMap() {
        this.lock.lock();
        try {
            return ImmutableMap.copyOf(this.mapChannels);
        } finally {
            this.lock.unlock();
        }
    }

    public void updatedChannel(StoredServerChannel storedServerChannel) {
        log.info("Stored server channel {} was updated", (Object) Integer.valueOf(storedServerChannel.hashCode()));
        this.wallet.addOrUpdateExtension(this);
    }

    /* JADX INFO: finally extract failed */
    public void putChannel(final StoredServerChannel storedServerChannel) {
        this.lock.lock();
        try {
            Preconditions.checkArgument(this.mapChannels.put(storedServerChannel.contract.getHash(), Preconditions.checkNotNull(storedServerChannel)) == null);
            Date date = new Date(((storedServerChannel.refundTransactionUnlockTimeSecs + CHANNEL_EXPIRE_OFFSET) * 1000) + (System.currentTimeMillis() - C3447Utils.currentTimeMillis()));
            log.info("Scheduling channel for automatic closure at {}: {}", (Object) date, (Object) storedServerChannel);
            this.channelTimeoutHandler.schedule(new TimerTask() {
                public void run() {
                    StoredPaymentChannelServerStates.log.info("Auto-closing channel: {}", (Object) storedServerChannel);
                    try {
                        StoredPaymentChannelServerStates.this.closeChannel(storedServerChannel);
                    } catch (Exception e) {
                        StoredPaymentChannelServerStates.log.error("Auto-closing channel failed", (Throwable) e);
                    }
                }
            }, date);
            this.lock.unlock();
            updatedChannel(storedServerChannel);
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    public String getWalletExtensionID() {
        return EXTENSION_ID;
    }

    public byte[] serializeWalletExtension() {
        this.lock.lock();
        try {
            NetworkParameters networkParameters = getNetworkParameters();
            boolean hasMaxMoney = networkParameters != null ? networkParameters.hasMaxMoney() : true;
            Coin maxMoney = networkParameters != null ? networkParameters.getMaxMoney() : NetworkParameters.MAX_MONEY;
            Builder newBuilder = StoredServerPaymentChannels.newBuilder();
            for (StoredServerChannel storedServerChannel : this.mapChannels.values()) {
                boolean z = false;
                Preconditions.checkState(storedServerChannel.bestValueToMe.signum() >= 0 && (!hasMaxMoney || storedServerChannel.bestValueToMe.compareTo(maxMoney) <= 0));
                if (storedServerChannel.refundTransactionUnlockTimeSecs > 0) {
                    z = true;
                }
                Preconditions.checkState(z);
                Preconditions.checkNotNull(storedServerChannel.myKey.getPrivKeyBytes());
                StoredServerPaymentChannel.Builder myKey = StoredServerPaymentChannel.newBuilder().setMajorVersion(storedServerChannel.majorVersion).setBestValueToMe(storedServerChannel.bestValueToMe.value).setRefundTransactionUnlockTimeSecs(storedServerChannel.refundTransactionUnlockTimeSecs).setContractTransaction(ByteString.copyFrom(storedServerChannel.contract.unsafeBitcoinSerialize())).setMyKey(ByteString.copyFrom(storedServerChannel.myKey.getPrivKeyBytes()));
                if (storedServerChannel.majorVersion == 1) {
                    myKey.setClientOutput(ByteString.copyFrom(storedServerChannel.clientOutput.unsafeBitcoinSerialize()));
                } else {
                    myKey.setClientKey(ByteString.copyFrom(storedServerChannel.clientKey.getPubKey()));
                }
                if (storedServerChannel.bestValueSignature != null) {
                    myKey.setBestValueSignature(ByteString.copyFrom(storedServerChannel.bestValueSignature));
                }
                newBuilder.addChannels(myKey);
            }
            return newBuilder.build().toByteArray();
        } finally {
            this.lock.unlock();
        }
    }

    public void deserializeWalletExtension(C3530Wallet wallet2, byte[] bArr) throws Exception {
        ECKey eCKey;
        TransactionOutput transactionOutput;
        this.lock.lock();
        try {
            this.wallet = wallet2;
            StoredServerPaymentChannels parseFrom = StoredServerPaymentChannels.parseFrom(bArr);
            NetworkParameters params = wallet2.getParams();
            for (StoredServerPaymentChannel storedServerPaymentChannel : parseFrom.getChannelsList()) {
                int majorVersion = storedServerPaymentChannel.getMajorVersion();
                if (majorVersion == 1) {
                    transactionOutput = new TransactionOutput(params, (Transaction) null, storedServerPaymentChannel.getClientOutput().toByteArray(), 0);
                    eCKey = null;
                } else {
                    eCKey = ECKey.fromPublicOnly(storedServerPaymentChannel.getClientKey().toByteArray());
                    transactionOutput = null;
                }
                StoredServerChannel storedServerChannel = new StoredServerChannel(null, majorVersion, params.getDefaultSerializer().makeTransaction(storedServerPaymentChannel.getContractTransaction().toByteArray()), transactionOutput, storedServerPaymentChannel.getRefundTransactionUnlockTimeSecs(), ECKey.fromPrivate(storedServerPaymentChannel.getMyKey().toByteArray()), eCKey, Coin.valueOf(storedServerPaymentChannel.getBestValueToMe()), storedServerPaymentChannel.hasBestValueSignature() ? storedServerPaymentChannel.getBestValueSignature().toByteArray() : null);
                putChannel(storedServerChannel);
            }
        } finally {
            this.lock.unlock();
        }
    }

    public String toString() {
        this.lock.lock();
        try {
            StringBuilder sb = new StringBuilder();
            for (StoredServerChannel append : this.mapChannels.values()) {
                sb.append(append);
            }
            return sb.toString();
        } finally {
            this.lock.unlock();
        }
    }

    @Nullable
    private NetworkParameters getNetworkParameters() {
        C3530Wallet wallet2 = this.wallet;
        if (wallet2 != null) {
            return wallet2.getNetworkParameters();
        }
        return null;
    }
}
