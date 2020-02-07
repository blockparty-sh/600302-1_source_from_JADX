package com.bitcoin.mwallet.core.models.merchant;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import com.bitcoin.bitcoink.p008tx.TxId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0005HÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u001eHÖ\u0001J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u0007\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0016\u0010\b\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\t\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006 "}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/merchant/Merchant;", "", "txId", "Lcom/bitcoin/bitcoink/tx/TxId;", "name", "", "description", "email", "phone", "website", "(Lcom/bitcoin/bitcoink/tx/TxId;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "getEmail", "getName", "getPhone", "getTxId", "()Lcom/bitcoin/bitcoink/tx/TxId;", "getWebsite", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
@Entity(primaryKeys = {"tx_id"}, tableName = "merchant")
/* compiled from: Merchant.kt */
public final class Merchant {
    @ColumnInfo(name = "description")
    @NotNull
    private final String description;
    @ColumnInfo(name = "email")
    @NotNull
    private final String email;
    @ColumnInfo(name = "merchant_name")
    @NotNull
    private final String name;
    @ColumnInfo(name = "phone")
    @NotNull
    private final String phone;
    @ColumnInfo(name = "tx_id")
    @NotNull
    private final TxId txId;
    @ColumnInfo(name = "website")
    @NotNull
    private final String website;

    @NotNull
    public static /* synthetic */ Merchant copy$default(Merchant merchant, TxId txId2, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            txId2 = merchant.txId;
        }
        if ((i & 2) != 0) {
            str = merchant.name;
        }
        String str6 = str;
        if ((i & 4) != 0) {
            str2 = merchant.description;
        }
        String str7 = str2;
        if ((i & 8) != 0) {
            str3 = merchant.email;
        }
        String str8 = str3;
        if ((i & 16) != 0) {
            str4 = merchant.phone;
        }
        String str9 = str4;
        if ((i & 32) != 0) {
            str5 = merchant.website;
        }
        return merchant.copy(txId2, str6, str7, str8, str9, str5);
    }

    @NotNull
    public final TxId component1() {
        return this.txId;
    }

    @NotNull
    public final String component2() {
        return this.name;
    }

    @NotNull
    public final String component3() {
        return this.description;
    }

    @NotNull
    public final String component4() {
        return this.email;
    }

    @NotNull
    public final String component5() {
        return this.phone;
    }

    @NotNull
    public final String component6() {
        return this.website;
    }

    @NotNull
    public final Merchant copy(@NotNull TxId txId2, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5) {
        Intrinsics.checkParameterIsNotNull(txId2, "txId");
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(str2, "description");
        Intrinsics.checkParameterIsNotNull(str3, "email");
        Intrinsics.checkParameterIsNotNull(str4, "phone");
        Intrinsics.checkParameterIsNotNull(str5, "website");
        Merchant merchant = new Merchant(txId2, str, str2, str3, str4, str5);
        return merchant;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0042, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.website, (java.lang.Object) r3.website) != false) goto L_0x0047;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0047
            boolean r0 = r3 instanceof com.bitcoin.mwallet.core.models.merchant.Merchant
            if (r0 == 0) goto L_0x0045
            com.bitcoin.mwallet.core.models.merchant.Merchant r3 = (com.bitcoin.mwallet.core.models.merchant.Merchant) r3
            com.bitcoin.bitcoink.tx.TxId r0 = r2.txId
            com.bitcoin.bitcoink.tx.TxId r1 = r3.txId
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0045
            java.lang.String r0 = r2.name
            java.lang.String r1 = r3.name
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0045
            java.lang.String r0 = r2.description
            java.lang.String r1 = r3.description
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0045
            java.lang.String r0 = r2.email
            java.lang.String r1 = r3.email
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0045
            java.lang.String r0 = r2.phone
            java.lang.String r1 = r3.phone
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0045
            java.lang.String r0 = r2.website
            java.lang.String r3 = r3.website
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x0045
            goto L_0x0047
        L_0x0045:
            r3 = 0
            return r3
        L_0x0047:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.merchant.Merchant.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        TxId txId2 = this.txId;
        int i = 0;
        int hashCode = (txId2 != null ? txId2.hashCode() : 0) * 31;
        String str = this.name;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.description;
        int hashCode3 = (hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.email;
        int hashCode4 = (hashCode3 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.phone;
        int hashCode5 = (hashCode4 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.website;
        if (str5 != null) {
            i = str5.hashCode();
        }
        return hashCode5 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Merchant(txId=");
        sb.append(this.txId);
        sb.append(", name=");
        sb.append(this.name);
        sb.append(", description=");
        sb.append(this.description);
        sb.append(", email=");
        sb.append(this.email);
        sb.append(", phone=");
        sb.append(this.phone);
        sb.append(", website=");
        sb.append(this.website);
        sb.append(")");
        return sb.toString();
    }

    public Merchant(@NotNull TxId txId2, @NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5) {
        Intrinsics.checkParameterIsNotNull(txId2, "txId");
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(str2, "description");
        Intrinsics.checkParameterIsNotNull(str3, "email");
        Intrinsics.checkParameterIsNotNull(str4, "phone");
        Intrinsics.checkParameterIsNotNull(str5, "website");
        this.txId = txId2;
        this.name = str;
        this.description = str2;
        this.email = str3;
        this.phone = str4;
        this.website = str5;
    }

    @NotNull
    public final TxId getTxId() {
        return this.txId;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getDescription() {
        return this.description;
    }

    @NotNull
    public final String getEmail() {
        return this.email;
    }

    @NotNull
    public final String getPhone() {
        return this.phone;
    }

    @NotNull
    public final String getWebsite() {
        return this.website;
    }
}
