package org.bitcoinj.protocols.channels;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.google.common.util.concurrent.SettableFuture;
import com.google.protobuf.ByteString;
import java.util.Date;
import java.util.Set;
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
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.TransactionBroadcaster;
import org.bitcoinj.core.TransactionConfidence.Source;
import org.bitcoinj.protocols.channels.ClientState.StoredClientPaymentChannel;
import org.bitcoinj.protocols.channels.ClientState.StoredClientPaymentChannels;
import org.bitcoinj.protocols.channels.ClientState.StoredClientPaymentChannels.Builder;
import org.bitcoinj.utils.Threading;
import org.bitcoinj.wallet.C3530Wallet;
import org.bitcoinj.wallet.WalletExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StoredPaymentChannelClientStates implements WalletExtension {
    static final String EXTENSION_ID = StoredPaymentChannelClientStates.class.getName();
    static final int MAX_SECONDS_TO_WAIT_FOR_BROADCASTER_TO_BE_SET = 10;
    /* access modifiers changed from: private */
    public static final Logger log = LoggerFactory.getLogger(StoredPaymentChannelClientStates.class);
    private final SettableFuture<TransactionBroadcaster> announcePeerGroupFuture = SettableFuture.create();
    @VisibleForTesting
    final Timer channelTimeoutHandler = new Timer(true);
    private C3530Wallet containingWallet;
    protected final ReentrantLock lock = Threading.lock("StoredPaymentChannelClientStates");
    @GuardedBy("lock")
    @VisibleForTesting
    final HashMultimap<Sha256Hash, StoredClientChannel> mapChannels = HashMultimap.create();

    public boolean isWalletExtensionMandatory() {
        return false;
    }

    public StoredPaymentChannelClientStates(@Nullable C3530Wallet wallet, TransactionBroadcaster transactionBroadcaster) {
        setTransactionBroadcaster(transactionBroadcaster);
        this.containingWallet = wallet;
    }

    public StoredPaymentChannelClientStates(@Nullable C3530Wallet wallet) {
        this.containingWallet = wallet;
    }

    public final void setTransactionBroadcaster(TransactionBroadcaster transactionBroadcaster) {
        this.announcePeerGroupFuture.set(Preconditions.checkNotNull(transactionBroadcaster));
    }

    @Nullable
    public static StoredPaymentChannelClientStates getFromWallet(C3530Wallet wallet) {
        return (StoredPaymentChannelClientStates) wallet.getExtensions().get(EXTENSION_ID);
    }

    public Coin getBalanceForServer(Sha256Hash sha256Hash) {
        Coin coin = Coin.ZERO;
        this.lock.lock();
        try {
            for (StoredClientChannel storedClientChannel : this.mapChannels.get(sha256Hash)) {
                synchronized (storedClientChannel) {
                    if (storedClientChannel.close == null) {
                        coin = coin.add(storedClientChannel.valueToMe);
                    }
                }
            }
            this.lock.unlock();
            return coin;
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    public long getSecondsUntilExpiry(Sha256Hash sha256Hash) {
        this.lock.lock();
        try {
            Set<StoredClientChannel> set = this.mapChannels.get(sha256Hash);
            long currentTimeSeconds = C3447Utils.currentTimeSeconds();
            int i = Integer.MAX_VALUE;
            for (StoredClientChannel storedClientChannel : set) {
                synchronized (storedClientChannel) {
                    if (storedClientChannel.expiryTimeSeconds() > currentTimeSeconds) {
                        i = Math.min(i, (int) storedClientChannel.expiryTimeSeconds());
                    }
                }
            }
            long j = i == Integer.MAX_VALUE ? 0 : ((long) i) - currentTimeSeconds;
            this.lock.unlock();
            return j;
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    @Nullable
    public StoredClientChannel getUsableChannelForServerID(Sha256Hash sha256Hash) {
        this.lock.lock();
        try {
            for (StoredClientChannel storedClientChannel : this.mapChannels.get(sha256Hash)) {
                synchronized (storedClientChannel) {
                    log.info("Considering channel {} contract {}", (Object) Integer.valueOf(storedClientChannel.hashCode()), (Object) storedClientChannel.contract.getHash());
                    if (storedClientChannel.close == null) {
                        if (!storedClientChannel.valueToMe.equals(Coin.ZERO)) {
                            if (!storedClientChannel.active) {
                                log.info("  ... activating");
                                storedClientChannel.active = true;
                                this.lock.unlock();
                                return storedClientChannel;
                            }
                            log.info("  ... but is already active");
                        }
                    }
                    log.info("  ... but is closed or empty");
                }
            }
            this.lock.unlock();
            return null;
        } catch (Throwable th) {
            this.lock.unlock();
            throw th;
        }
    }

    @Nullable
    public StoredClientChannel getChannel(Sha256Hash sha256Hash, Sha256Hash sha256Hash2) {
        this.lock.lock();
        try {
            for (StoredClientChannel storedClientChannel : this.mapChannels.get(sha256Hash)) {
                if (storedClientChannel.contract.getHash().equals(sha256Hash2)) {
                    return storedClientChannel;
                }
            }
            this.lock.unlock();
            return null;
        } finally {
            this.lock.unlock();
        }
    }

    public Multimap<Sha256Hash, StoredClientChannel> getChannelMap() {
        this.lock.lock();
        try {
            return ImmutableMultimap.copyOf((Multimap<? extends K, ? extends V>) this.mapChannels);
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: 0000 */
    public void updatedChannel(StoredClientChannel storedClientChannel) {
        log.info("Stored client channel {} was updated", (Object) Integer.valueOf(storedClientChannel.hashCode()));
        this.containingWallet.addOrUpdateExtension(this);
    }

    /* access modifiers changed from: 0000 */
    public void putChannel(StoredClientChannel storedClientChannel) {
        putChannel(storedClientChannel, true);
    }

    private void putChannel(final StoredClientChannel storedClientChannel, boolean z) {
        this.lock.lock();
        try {
            this.mapChannels.put(storedClientChannel.f817id, storedClientChannel);
            this.channelTimeoutHandler.schedule(new TimerTask() {
                public void run() {
                    try {
                        TransactionBroadcaster access$000 = StoredPaymentChannelClientStates.this.getAnnouncePeerGroup();
                        StoredPaymentChannelClientStates.this.removeChannel(storedClientChannel);
                        access$000.broadcastTransaction(storedClientChannel.contract);
                        access$000.broadcastTransaction(storedClientChannel.refund);
                    } catch (Exception e) {
                        StoredPaymentChannelClientStates.log.error("Auto-closing channel failed", (Throwable) e);
                    }
                }
            }, new Date((storedClientChannel.expiryTimeSeconds() * 1000) + (System.currentTimeMillis() - C3447Utils.currentTimeMillis())));
            if (z) {
                updatedChannel(storedClientChannel);
            }
        } finally {
            this.lock.unlock();
        }
    }

    /* access modifiers changed from: private */
    public TransactionBroadcaster getAnnouncePeerGroup() {
        try {
            return (TransactionBroadcaster) this.announcePeerGroupFuture.get(10, TimeUnit.SECONDS);
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

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: 0000 */
    public void removeChannel(StoredClientChannel storedClientChannel) {
        this.lock.lock();
        try {
            this.mapChannels.remove(storedClientChannel.f817id, storedClientChannel);
            this.lock.unlock();
            updatedChannel(storedClientChannel);
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
            Builder newBuilder = StoredClientPaymentChannels.newBuilder();
            for (StoredClientChannel storedClientChannel : this.mapChannels.values()) {
                Preconditions.checkState(storedClientChannel.valueToMe.signum() >= 0 && (!hasMaxMoney || storedClientChannel.valueToMe.compareTo(maxMoney) <= 0));
                Preconditions.checkState(storedClientChannel.refundFees.signum() >= 0 && (!hasMaxMoney || storedClientChannel.refundFees.compareTo(maxMoney) <= 0));
                Preconditions.checkNotNull(storedClientChannel.myKey.getPubKey());
                Preconditions.checkState(storedClientChannel.refund.getConfidence().getSource() == Source.SELF);
                Preconditions.checkNotNull(storedClientChannel.myKey.getPubKey());
                StoredClientPaymentChannel.Builder expiryTime = StoredClientPaymentChannel.newBuilder().setMajorVersion(storedClientChannel.majorVersion).setId(ByteString.copyFrom(storedClientChannel.f817id.getBytes())).setContractTransaction(ByteString.copyFrom(storedClientChannel.contract.unsafeBitcoinSerialize())).setRefundFees(storedClientChannel.refundFees.value).setRefundTransaction(ByteString.copyFrom(storedClientChannel.refund.unsafeBitcoinSerialize())).setMyKey(ByteString.copyFrom(new byte[0])).setMyPublicKey(ByteString.copyFrom(storedClientChannel.myKey.getPubKey())).setServerKey(ByteString.copyFrom(storedClientChannel.serverKey.getPubKey())).setValueToMe(storedClientChannel.valueToMe.value).setExpiryTime(storedClientChannel.expiryTime);
                if (storedClientChannel.close != null) {
                    expiryTime.setCloseTransactionHash(ByteString.copyFrom(storedClientChannel.close.getHash().getBytes()));
                }
                newBuilder.addChannels(expiryTime);
            }
            return newBuilder.build().toByteArray();
        } finally {
            this.lock.unlock();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0031 A[Catch:{ all -> 0x00e8 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void deserializeWalletExtension(org.bitcoinj.wallet.C3530Wallet r21, byte[] r22) throws java.lang.Exception {
        /*
            r20 = this;
            r1 = r20
            r0 = r21
            java.util.concurrent.locks.ReentrantLock r2 = r1.lock
            r2.lock()
            org.bitcoinj.wallet.Wallet r2 = r1.containingWallet     // Catch:{ all -> 0x00e8 }
            r3 = 0
            if (r2 == 0) goto L_0x0015
            org.bitcoinj.wallet.Wallet r2 = r1.containingWallet     // Catch:{ all -> 0x00e8 }
            if (r2 != r0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r2 = 0
            goto L_0x0016
        L_0x0015:
            r2 = 1
        L_0x0016:
            com.google.common.base.Preconditions.checkState(r2)     // Catch:{ all -> 0x00e8 }
            r1.containingWallet = r0     // Catch:{ all -> 0x00e8 }
            org.bitcoinj.core.NetworkParameters r2 = r21.getParams()     // Catch:{ all -> 0x00e8 }
            org.bitcoinj.protocols.channels.ClientState$StoredClientPaymentChannels r4 = org.bitcoinj.protocols.channels.ClientState.StoredClientPaymentChannels.parseFrom(r22)     // Catch:{ all -> 0x00e8 }
            java.util.List r4 = r4.getChannelsList()     // Catch:{ all -> 0x00e8 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x00e8 }
        L_0x002b:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x00e8 }
            if (r5 == 0) goto L_0x00e2
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x00e8 }
            org.bitcoinj.protocols.channels.ClientState$StoredClientPaymentChannel r5 = (org.bitcoinj.protocols.channels.ClientState.StoredClientPaymentChannel) r5     // Catch:{ all -> 0x00e8 }
            org.bitcoinj.core.MessageSerializer r6 = r2.getDefaultSerializer()     // Catch:{ all -> 0x00e8 }
            com.google.protobuf.ByteString r7 = r5.getRefundTransaction()     // Catch:{ all -> 0x00e8 }
            byte[] r7 = r7.toByteArray()     // Catch:{ all -> 0x00e8 }
            org.bitcoinj.core.Transaction r12 = r6.makeTransaction(r7)     // Catch:{ all -> 0x00e8 }
            org.bitcoinj.core.TransactionConfidence r6 = r12.getConfidence()     // Catch:{ all -> 0x00e8 }
            org.bitcoinj.core.TransactionConfidence$Source r7 = org.bitcoinj.core.TransactionConfidence.Source.SELF     // Catch:{ all -> 0x00e8 }
            r6.setSource(r7)     // Catch:{ all -> 0x00e8 }
            com.google.protobuf.ByteString r6 = r5.getMyKey()     // Catch:{ all -> 0x00e8 }
            boolean r6 = r6.isEmpty()     // Catch:{ all -> 0x00e8 }
            if (r6 == 0) goto L_0x0067
            com.google.protobuf.ByteString r6 = r5.getMyPublicKey()     // Catch:{ all -> 0x00e8 }
            byte[] r6 = r6.toByteArray()     // Catch:{ all -> 0x00e8 }
            org.bitcoinj.core.ECKey r6 = r0.findKeyFromPubKey(r6)     // Catch:{ all -> 0x00e8 }
            goto L_0x0073
        L_0x0067:
            com.google.protobuf.ByteString r6 = r5.getMyKey()     // Catch:{ all -> 0x00e8 }
            byte[] r6 = r6.toByteArray()     // Catch:{ all -> 0x00e8 }
            org.bitcoinj.core.ECKey r6 = org.bitcoinj.core.ECKey.fromPrivate(r6)     // Catch:{ all -> 0x00e8 }
        L_0x0073:
            r13 = r6
            boolean r6 = r5.hasServerKey()     // Catch:{ all -> 0x00e8 }
            if (r6 == 0) goto L_0x0087
            com.google.protobuf.ByteString r6 = r5.getServerKey()     // Catch:{ all -> 0x00e8 }
            byte[] r6 = r6.toByteArray()     // Catch:{ all -> 0x00e8 }
            org.bitcoinj.core.ECKey r6 = org.bitcoinj.core.ECKey.fromPublicOnly(r6)     // Catch:{ all -> 0x00e8 }
            goto L_0x0088
        L_0x0087:
            r6 = 0
        L_0x0088:
            r14 = r6
            org.bitcoinj.protocols.channels.StoredClientChannel r6 = new org.bitcoinj.protocols.channels.StoredClientChannel     // Catch:{ all -> 0x00e8 }
            int r9 = r5.getMajorVersion()     // Catch:{ all -> 0x00e8 }
            com.google.protobuf.ByteString r7 = r5.getId()     // Catch:{ all -> 0x00e8 }
            byte[] r7 = r7.toByteArray()     // Catch:{ all -> 0x00e8 }
            org.bitcoinj.core.Sha256Hash r10 = org.bitcoinj.core.Sha256Hash.wrap(r7)     // Catch:{ all -> 0x00e8 }
            org.bitcoinj.core.MessageSerializer r7 = r2.getDefaultSerializer()     // Catch:{ all -> 0x00e8 }
            com.google.protobuf.ByteString r8 = r5.getContractTransaction()     // Catch:{ all -> 0x00e8 }
            byte[] r8 = r8.toByteArray()     // Catch:{ all -> 0x00e8 }
            org.bitcoinj.core.Transaction r11 = r7.makeTransaction(r8)     // Catch:{ all -> 0x00e8 }
            long r7 = r5.getValueToMe()     // Catch:{ all -> 0x00e8 }
            org.bitcoinj.core.Coin r15 = org.bitcoinj.core.Coin.valueOf(r7)     // Catch:{ all -> 0x00e8 }
            long r7 = r5.getRefundFees()     // Catch:{ all -> 0x00e8 }
            org.bitcoinj.core.Coin r16 = org.bitcoinj.core.Coin.valueOf(r7)     // Catch:{ all -> 0x00e8 }
            long r17 = r5.getExpiryTime()     // Catch:{ all -> 0x00e8 }
            r19 = 0
            r8 = r6
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17, r19)     // Catch:{ all -> 0x00e8 }
            boolean r7 = r5.hasCloseTransactionHash()     // Catch:{ all -> 0x00e8 }
            if (r7 == 0) goto L_0x00dd
            com.google.protobuf.ByteString r5 = r5.getCloseTransactionHash()     // Catch:{ all -> 0x00e8 }
            byte[] r5 = r5.toByteArray()     // Catch:{ all -> 0x00e8 }
            org.bitcoinj.core.Sha256Hash r5 = org.bitcoinj.core.Sha256Hash.wrap(r5)     // Catch:{ all -> 0x00e8 }
            org.bitcoinj.core.Transaction r5 = r0.getTransaction(r5)     // Catch:{ all -> 0x00e8 }
            r6.close = r5     // Catch:{ all -> 0x00e8 }
        L_0x00dd:
            r1.putChannel(r6, r3)     // Catch:{ all -> 0x00e8 }
            goto L_0x002b
        L_0x00e2:
            java.util.concurrent.locks.ReentrantLock r0 = r1.lock
            r0.unlock()
            return
        L_0x00e8:
            r0 = move-exception
            java.util.concurrent.locks.ReentrantLock r2 = r1.lock
            r2.unlock()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.bitcoinj.protocols.channels.StoredPaymentChannelClientStates.deserializeWalletExtension(org.bitcoinj.wallet.Wallet, byte[]):void");
    }

    public String toString() {
        this.lock.lock();
        try {
            StringBuilder sb = new StringBuilder("Client payment channel states:\n");
            for (StoredClientChannel storedClientChannel : this.mapChannels.values()) {
                sb.append("  ");
                sb.append(storedClientChannel);
                sb.append("\n");
            }
            return sb.toString();
        } finally {
            this.lock.unlock();
        }
    }

    @Nullable
    private NetworkParameters getNetworkParameters() {
        C3530Wallet wallet = this.containingWallet;
        if (wallet != null) {
            return wallet.getNetworkParameters();
        }
        return null;
    }
}
