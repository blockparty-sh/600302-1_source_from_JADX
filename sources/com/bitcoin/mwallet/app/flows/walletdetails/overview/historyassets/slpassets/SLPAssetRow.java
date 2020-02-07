package com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets;

import com.bitcoin.mwallet.core.models.slp.Slp;
import java.math.BigDecimal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0007HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/walletdetails/overview/historyassets/slpassets/SLPAssetRow;", "", "balance", "Ljava/math/BigDecimal;", "token", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "image", "", "(Ljava/math/BigDecimal;Lcom/bitcoin/mwallet/core/models/slp/Slp;Ljava/lang/String;)V", "getBalance", "()Ljava/math/BigDecimal;", "getImage", "()Ljava/lang/String;", "getToken", "()Lcom/bitcoin/mwallet/core/models/slp/Slp;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SLPAssetRow.kt */
public final class SLPAssetRow {
    @NotNull
    private final BigDecimal balance;
    @NotNull
    private final String image;
    @NotNull
    private final Slp token;

    @NotNull
    public static /* synthetic */ SLPAssetRow copy$default(SLPAssetRow sLPAssetRow, BigDecimal bigDecimal, Slp slp, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            bigDecimal = sLPAssetRow.balance;
        }
        if ((i & 2) != 0) {
            slp = sLPAssetRow.token;
        }
        if ((i & 4) != 0) {
            str = sLPAssetRow.image;
        }
        return sLPAssetRow.copy(bigDecimal, slp, str);
    }

    @NotNull
    public final BigDecimal component1() {
        return this.balance;
    }

    @NotNull
    public final Slp component2() {
        return this.token;
    }

    @NotNull
    public final String component3() {
        return this.image;
    }

    @NotNull
    public final SLPAssetRow copy(@NotNull BigDecimal bigDecimal, @NotNull Slp slp, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "balance");
        Intrinsics.checkParameterIsNotNull(slp, "token");
        Intrinsics.checkParameterIsNotNull(str, "image");
        return new SLPAssetRow(bigDecimal, slp, str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0024, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.image, (java.lang.Object) r3.image) != false) goto L_0x0029;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0029
            boolean r0 = r3 instanceof com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetRow
            if (r0 == 0) goto L_0x0027
            com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetRow r3 = (com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetRow) r3
            java.math.BigDecimal r0 = r2.balance
            java.math.BigDecimal r1 = r3.balance
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0027
            com.bitcoin.mwallet.core.models.slp.Slp r0 = r2.token
            com.bitcoin.mwallet.core.models.slp.Slp r1 = r3.token
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0027
            java.lang.String r0 = r2.image
            java.lang.String r3 = r3.image
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x0027
            goto L_0x0029
        L_0x0027:
            r3 = 0
            return r3
        L_0x0029:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.walletdetails.overview.historyassets.slpassets.SLPAssetRow.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        BigDecimal bigDecimal = this.balance;
        int i = 0;
        int hashCode = (bigDecimal != null ? bigDecimal.hashCode() : 0) * 31;
        Slp slp = this.token;
        int hashCode2 = (hashCode + (slp != null ? slp.hashCode() : 0)) * 31;
        String str = this.image;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SLPAssetRow(balance=");
        sb.append(this.balance);
        sb.append(", token=");
        sb.append(this.token);
        sb.append(", image=");
        sb.append(this.image);
        sb.append(")");
        return sb.toString();
    }

    public SLPAssetRow(@NotNull BigDecimal bigDecimal, @NotNull Slp slp, @NotNull String str) {
        Intrinsics.checkParameterIsNotNull(bigDecimal, "balance");
        Intrinsics.checkParameterIsNotNull(slp, "token");
        Intrinsics.checkParameterIsNotNull(str, "image");
        this.balance = bigDecimal;
        this.token = slp;
        this.image = str;
    }

    @NotNull
    public final BigDecimal getBalance() {
        return this.balance;
    }

    @NotNull
    public final String getImage() {
        return this.image;
    }

    @NotNull
    public final Slp getToken() {
        return this.token;
    }
}
