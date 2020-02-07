package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset;

import com.bitcoin.mwallet.core.models.Coin;
import com.bitcoin.mwallet.core.models.slp.SlpId;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\bHÆ\u0003J3\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, mo37405d2 = {"Lcom/bitcoin/mwallet/app/flows/sendv2/dialogs/selectsendingasset/SendableAssetModel;", "", "name", "", "ticker", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "tokenId", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "(Ljava/lang/String;Ljava/lang/String;Lcom/bitcoin/mwallet/core/models/Coin;Lcom/bitcoin/mwallet/core/models/slp/SlpId;)V", "getCoin", "()Lcom/bitcoin/mwallet/core/models/Coin;", "getName", "()Ljava/lang/String;", "getTicker", "getTokenId", "()Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SelectSendingAssetData.kt */
public final class SendableAssetModel {
    @NotNull
    private final Coin coin;
    @NotNull
    private final String name;
    @NotNull
    private final String ticker;
    @Nullable
    private final SlpId tokenId;

    @NotNull
    public static /* synthetic */ SendableAssetModel copy$default(SendableAssetModel sendableAssetModel, String str, String str2, Coin coin2, SlpId slpId, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sendableAssetModel.name;
        }
        if ((i & 2) != 0) {
            str2 = sendableAssetModel.ticker;
        }
        if ((i & 4) != 0) {
            coin2 = sendableAssetModel.coin;
        }
        if ((i & 8) != 0) {
            slpId = sendableAssetModel.tokenId;
        }
        return sendableAssetModel.copy(str, str2, coin2, slpId);
    }

    @NotNull
    public final String component1() {
        return this.name;
    }

    @NotNull
    public final String component2() {
        return this.ticker;
    }

    @NotNull
    public final Coin component3() {
        return this.coin;
    }

    @Nullable
    public final SlpId component4() {
        return this.tokenId;
    }

    @NotNull
    public final SendableAssetModel copy(@NotNull String str, @NotNull String str2, @NotNull Coin coin2, @Nullable SlpId slpId) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(str2, "ticker");
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        return new SendableAssetModel(str, str2, coin2, slpId);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002e, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.tokenId, (java.lang.Object) r3.tokenId) != false) goto L_0x0033;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0033
            boolean r0 = r3 instanceof com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.SendableAssetModel
            if (r0 == 0) goto L_0x0031
            com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.SendableAssetModel r3 = (com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.SendableAssetModel) r3
            java.lang.String r0 = r2.name
            java.lang.String r1 = r3.name
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0031
            java.lang.String r0 = r2.ticker
            java.lang.String r1 = r3.ticker
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0031
            com.bitcoin.mwallet.core.models.Coin r0 = r2.coin
            com.bitcoin.mwallet.core.models.Coin r1 = r3.coin
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0031
            com.bitcoin.mwallet.core.models.slp.SlpId r0 = r2.tokenId
            com.bitcoin.mwallet.core.models.slp.SlpId r3 = r3.tokenId
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x0031
            goto L_0x0033
        L_0x0031:
            r3 = 0
            return r3
        L_0x0033:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset.SendableAssetModel.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.name;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.ticker;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Coin coin2 = this.coin;
        int hashCode3 = (hashCode2 + (coin2 != null ? coin2.hashCode() : 0)) * 31;
        SlpId slpId = this.tokenId;
        if (slpId != null) {
            i = slpId.hashCode();
        }
        return hashCode3 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SendableAssetModel(name=");
        sb.append(this.name);
        sb.append(", ticker=");
        sb.append(this.ticker);
        sb.append(", coin=");
        sb.append(this.coin);
        sb.append(", tokenId=");
        sb.append(this.tokenId);
        sb.append(")");
        return sb.toString();
    }

    public SendableAssetModel(@NotNull String str, @NotNull String str2, @NotNull Coin coin2, @Nullable SlpId slpId) {
        Intrinsics.checkParameterIsNotNull(str, "name");
        Intrinsics.checkParameterIsNotNull(str2, "ticker");
        Intrinsics.checkParameterIsNotNull(coin2, "coin");
        this.name = str;
        this.ticker = str2;
        this.coin = coin2;
        this.tokenId = slpId;
    }

    @NotNull
    public final String getName() {
        return this.name;
    }

    @NotNull
    public final String getTicker() {
        return this.ticker;
    }

    @NotNull
    public final Coin getCoin() {
        return this.coin;
    }

    @Nullable
    public final SlpId getTokenId() {
        return this.tokenId;
    }
}
