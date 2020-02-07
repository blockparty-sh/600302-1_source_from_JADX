package com.bitcoin.mwallet.core.models;

import androidx.room.ColumnInfo;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0005\b\b\u0018\u0000  2\u00020\u0001:\u0001 B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0007HÆ\u0003J\u000f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\nHÆ\u0003JA\u0010\u001a\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00072\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nHÆ\u0001J\u0013\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001e\u001a\u00020\u0007HÖ\u0001J\t\u0010\u001f\u001a\u00020\u0005HÖ\u0001R\u001c\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n8\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\b\u001a\u00020\u00078\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/Copayers;", "", "walletCopayerId", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "walletCopayerName", "", "numCopayers", "", "requiredSignatures", "copayerNames", "", "(Lcom/bitcoin/mwallet/core/models/CopayerId;Ljava/lang/String;IILjava/util/List;)V", "getCopayerNames", "()Ljava/util/List;", "getNumCopayers", "()I", "getRequiredSignatures", "getWalletCopayerId", "()Lcom/bitcoin/mwallet/core/models/CopayerId;", "getWalletCopayerName", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Copayers.kt */
public final class Copayers {
    public static final Companion Companion = new Companion(null);
    @ColumnInfo(name = "copayer_names")
    @NotNull
    private final List<String> copayerNames;
    @ColumnInfo(name = "num_copayers")
    private final int numCopayers;
    @ColumnInfo(name = "required_signatures")
    private final int requiredSignatures;
    @ColumnInfo(name = "wallet_copayer_id")
    @NotNull
    private final CopayerId walletCopayerId;
    @ColumnInfo(name = "wallet_copayer_names")
    @NotNull
    private final String walletCopayerName;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/Copayers$Companion;", "", "()V", "createSingleSig", "Lcom/bitcoin/mwallet/core/models/Copayers;", "id", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: Copayers.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Copayers createSingleSig(@NotNull CopayerId copayerId) {
            Intrinsics.checkParameterIsNotNull(copayerId, CommonProperties.f657ID);
            CopayerId copayerId2 = copayerId;
            Copayers copayers = new Copayers(copayerId2, "me", 1, 1, CollectionsKt.emptyList());
            return copayers;
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<java.lang.String>, for r8v0, types: [java.util.List] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.bitcoin.mwallet.core.models.Copayers copy$default(com.bitcoin.mwallet.core.models.Copayers r3, com.bitcoin.mwallet.core.models.CopayerId r4, java.lang.String r5, int r6, int r7, java.util.List<java.lang.String> r8, int r9, java.lang.Object r10) {
        /*
            r10 = r9 & 1
            if (r10 == 0) goto L_0x0006
            com.bitcoin.mwallet.core.models.CopayerId r4 = r3.walletCopayerId
        L_0x0006:
            r10 = r9 & 2
            if (r10 == 0) goto L_0x000c
            java.lang.String r5 = r3.walletCopayerName
        L_0x000c:
            r10 = r5
            r5 = r9 & 4
            if (r5 == 0) goto L_0x0013
            int r6 = r3.numCopayers
        L_0x0013:
            r0 = r6
            r5 = r9 & 8
            if (r5 == 0) goto L_0x001a
            int r7 = r3.requiredSignatures
        L_0x001a:
            r1 = r7
            r5 = r9 & 16
            if (r5 == 0) goto L_0x0021
            java.util.List<java.lang.String> r8 = r3.copayerNames
        L_0x0021:
            r2 = r8
            r5 = r3
            r6 = r4
            r7 = r10
            r8 = r0
            r9 = r1
            r10 = r2
            com.bitcoin.mwallet.core.models.Copayers r3 = r5.copy(r6, r7, r8, r9, r10)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.Copayers.copy$default(com.bitcoin.mwallet.core.models.Copayers, com.bitcoin.mwallet.core.models.CopayerId, java.lang.String, int, int, java.util.List, int, java.lang.Object):com.bitcoin.mwallet.core.models.Copayers");
    }

    @NotNull
    public final CopayerId component1() {
        return this.walletCopayerId;
    }

    @NotNull
    public final String component2() {
        return this.walletCopayerName;
    }

    public final int component3() {
        return this.numCopayers;
    }

    public final int component4() {
        return this.requiredSignatures;
    }

    @NotNull
    public final List<String> component5() {
        return this.copayerNames;
    }

    @NotNull
    public final Copayers copy(@NotNull CopayerId copayerId, @NotNull String str, int i, int i2, @NotNull List<String> list) {
        Intrinsics.checkParameterIsNotNull(copayerId, "walletCopayerId");
        Intrinsics.checkParameterIsNotNull(str, "walletCopayerName");
        Intrinsics.checkParameterIsNotNull(list, "copayerNames");
        Copayers copayers = new Copayers(copayerId, str, i, i2, list);
        return copayers;
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof Copayers) {
                Copayers copayers = (Copayers) obj;
                if (Intrinsics.areEqual((Object) this.walletCopayerId, (Object) copayers.walletCopayerId) && Intrinsics.areEqual((Object) this.walletCopayerName, (Object) copayers.walletCopayerName)) {
                    if (this.numCopayers == copayers.numCopayers) {
                        if (!(this.requiredSignatures == copayers.requiredSignatures) || !Intrinsics.areEqual((Object) this.copayerNames, (Object) copayers.copayerNames)) {
                            return false;
                        }
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        CopayerId copayerId = this.walletCopayerId;
        int i = 0;
        int hashCode = (copayerId != null ? copayerId.hashCode() : 0) * 31;
        String str = this.walletCopayerName;
        int hashCode2 = (((((hashCode + (str != null ? str.hashCode() : 0)) * 31) + this.numCopayers) * 31) + this.requiredSignatures) * 31;
        List<String> list = this.copayerNames;
        if (list != null) {
            i = list.hashCode();
        }
        return hashCode2 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Copayers(walletCopayerId=");
        sb.append(this.walletCopayerId);
        sb.append(", walletCopayerName=");
        sb.append(this.walletCopayerName);
        sb.append(", numCopayers=");
        sb.append(this.numCopayers);
        sb.append(", requiredSignatures=");
        sb.append(this.requiredSignatures);
        sb.append(", copayerNames=");
        sb.append(this.copayerNames);
        sb.append(")");
        return sb.toString();
    }

    public Copayers(@NotNull CopayerId copayerId, @NotNull String str, int i, int i2, @NotNull List<String> list) {
        Intrinsics.checkParameterIsNotNull(copayerId, "walletCopayerId");
        Intrinsics.checkParameterIsNotNull(str, "walletCopayerName");
        Intrinsics.checkParameterIsNotNull(list, "copayerNames");
        this.walletCopayerId = copayerId;
        this.walletCopayerName = str;
        this.numCopayers = i;
        this.requiredSignatures = i2;
        this.copayerNames = list;
    }

    @NotNull
    public final CopayerId getWalletCopayerId() {
        return this.walletCopayerId;
    }

    @NotNull
    public final String getWalletCopayerName() {
        return this.walletCopayerName;
    }

    public final int getNumCopayers() {
        return this.numCopayers;
    }

    public final int getRequiredSignatures() {
        return this.requiredSignatures;
    }

    @NotNull
    public final List<String> getCopayerNames() {
        return this.copayerNames;
    }
}
