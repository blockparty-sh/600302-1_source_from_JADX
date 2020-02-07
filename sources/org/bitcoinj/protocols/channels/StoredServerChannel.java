package org.bitcoinj.protocols.channels;

import com.google.common.base.Preconditions;
import java.util.Date;
import java.util.Locale;
import javax.annotation.Nullable;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionBroadcaster;
import org.bitcoinj.core.TransactionOutput;
import org.bitcoinj.core.VerificationException;
import org.bitcoinj.wallet.C3530Wallet;

public class StoredServerChannel {
    byte[] bestValueSignature;
    Coin bestValueToMe;
    ECKey clientKey;
    TransactionOutput clientOutput;
    private PaymentChannelServer connectedHandler = null;
    Transaction contract;
    int majorVersion;
    ECKey myKey;
    long refundTransactionUnlockTimeSecs;
    PaymentChannelServerState state = null;

    StoredServerChannel(@Nullable PaymentChannelServerState paymentChannelServerState, int i, Transaction transaction, TransactionOutput transactionOutput, long j, ECKey eCKey, ECKey eCKey2, Coin coin, @Nullable byte[] bArr) {
        this.majorVersion = i;
        this.contract = transaction;
        this.clientOutput = transactionOutput;
        this.refundTransactionUnlockTimeSecs = j;
        this.myKey = eCKey;
        this.clientKey = eCKey2;
        this.bestValueToMe = coin;
        this.bestValueSignature = bArr;
        this.state = paymentChannelServerState;
    }

    /* access modifiers changed from: 0000 */
    public synchronized void updateValueToMe(Coin coin, byte[] bArr) {
        this.bestValueToMe = coin;
        this.bestValueSignature = bArr;
    }

    /* access modifiers changed from: 0000 */
    public synchronized PaymentChannelServer setConnectedHandler(PaymentChannelServer paymentChannelServer, boolean z) {
        if (this.connectedHandler == null || z) {
            this.connectedHandler = paymentChannelServer;
            return paymentChannelServer;
        }
        return this.connectedHandler;
    }

    /* access modifiers changed from: 0000 */
    public synchronized void clearConnectedHandler() {
        this.connectedHandler = null;
    }

    /* access modifiers changed from: 0000 */
    public synchronized void closeConnectedHandler() {
        if (this.connectedHandler != null) {
            this.connectedHandler.close();
        }
    }

    public synchronized PaymentChannelServerState getOrCreateState(C3530Wallet wallet, TransactionBroadcaster transactionBroadcaster) throws VerificationException {
        boolean z = true;
        if (this.state == null) {
            int i = this.majorVersion;
            if (i == 1) {
                this.state = new PaymentChannelV1ServerState(this, wallet, transactionBroadcaster);
            } else if (i == 2) {
                this.state = new PaymentChannelV2ServerState(this, wallet, transactionBroadcaster);
            } else {
                throw new IllegalStateException("Invalid version number found");
            }
        }
        if (wallet != this.state.wallet) {
            z = false;
        }
        Preconditions.checkArgument(z);
        return this.state;
    }

    public synchronized String toString() {
        Locale locale;
        String str;
        Object[] objArr;
        String format = String.format(Locale.US, "%n", new Object[0]);
        locale = Locale.US;
        str = "Stored server channel (%s)%n    Version:       %d%n    Key:           %s%n    Value to me:   %s%n    Client output: %s%n    Refund unlock: %s (%d unix time)%n    Contract:    %s%n";
        objArr = new Object[8];
        objArr[0] = this.connectedHandler != null ? "connected" : "disconnected";
        objArr[1] = Integer.valueOf(this.majorVersion);
        objArr[2] = this.myKey;
        objArr[3] = this.bestValueToMe;
        objArr[4] = this.clientOutput;
        objArr[5] = new Date(this.refundTransactionUnlockTimeSecs * 1000);
        objArr[6] = Long.valueOf(this.refundTransactionUnlockTimeSecs);
        String transaction = this.contract.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(format);
        sb.append("    ");
        objArr[7] = transaction.replaceAll(format, sb.toString());
        return String.format(locale, str, objArr);
    }
}
