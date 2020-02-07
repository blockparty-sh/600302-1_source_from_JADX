package org.bitcoinj.protocols.channels;

import com.google.android.gms.measurement.api.AppMeasurementSdk.ConditionalUserProperty;
import java.util.Locale;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;

/* compiled from: StoredPaymentChannelClientStates */
class StoredClientChannel {
    boolean active = false;
    Transaction close;
    Transaction contract;
    long expiryTime;

    /* renamed from: id */
    Sha256Hash f817id;
    int majorVersion;
    ECKey myKey;
    Transaction refund;
    Coin refundFees;
    ECKey serverKey;
    Coin valueToMe;

    StoredClientChannel(int i, Sha256Hash sha256Hash, Transaction transaction, Transaction transaction2, ECKey eCKey, ECKey eCKey2, Coin coin, Coin coin2, long j, boolean z) {
        this.majorVersion = i;
        this.f817id = sha256Hash;
        this.contract = transaction;
        this.refund = transaction2;
        this.myKey = eCKey;
        this.serverKey = eCKey2;
        this.valueToMe = coin;
        this.refundFees = coin2;
        this.expiryTime = j;
        this.active = z;
    }

    /* access modifiers changed from: 0000 */
    public long expiryTimeSeconds() {
        long lockTime;
        int i = this.majorVersion;
        if (i == 1) {
            lockTime = this.refund.getLockTime();
        } else if (i == 2) {
            lockTime = this.expiryTime;
        } else {
            throw new IllegalStateException("Invalid version");
        }
        return lockTime + 300;
    }

    public String toString() {
        String str;
        String format = String.format(Locale.US, "%n", new Object[0]);
        Transaction transaction = this.close;
        if (transaction == null) {
            str = "still open";
        } else {
            String transaction2 = transaction.toString();
            StringBuilder sb = new StringBuilder();
            sb.append(format);
            sb.append("   ");
            str = transaction2.replaceAll(format, sb.toString());
        }
        Locale locale = Locale.US;
        Object[] objArr = new Object[11];
        objArr[0] = this.f817id;
        objArr[1] = this.active ? ConditionalUserProperty.ACTIVE : "inactive";
        objArr[2] = Integer.valueOf(this.majorVersion);
        objArr[3] = this.myKey;
        objArr[4] = this.serverKey;
        objArr[5] = this.valueToMe;
        objArr[6] = this.refundFees;
        objArr[7] = Long.valueOf(this.expiryTime);
        String transaction3 = this.contract.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(format);
        String str2 = "    ";
        sb2.append(str2);
        objArr[8] = transaction3.replaceAll(format, sb2.toString());
        String transaction4 = this.refund.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(format);
        sb3.append(str2);
        objArr[9] = transaction4.replaceAll(format, sb3.toString());
        objArr[10] = str;
        return String.format(locale, "Stored client channel for server ID %s (%s)%n    Version:     %d%n    Key:         %s%n    Server key:  %s%n    Value left:  %s%n    Refund fees: %s%n    Expiry     : %s%n    Contract:  %sRefund:    %sClose:     %s", objArr);
    }
}
