package com.bitcoin.mwallet.core.models.wallet;

import com.bitcoin.mwallet.core.models.Coin;
import com.google.android.gms.common.internal.ImagesContract;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.io.Serializable;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\u0006\u0010\u000f\u001a\u00020\nJ\b\u0010\u0010\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "Ljava/io/Serializable;", "id", "", "(Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "isLocal", "toString", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: WalletId.kt */
public final class WalletId implements Serializable {
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: id */
    private final String f377id;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/wallet/WalletId$Companion;", "", "()V", "localPrefix", "", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "localTempId", "Lcom/bitcoin/mwallet/core/models/wallet/WalletId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: WalletId.kt */
    public static final class Companion {

        @Metadata(mo37403bv = {1, 0, 3}, mo37406k = 3, mo37407mv = {1, 1, 15})
        public final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[Coin.values().length];

            static {
                $EnumSwitchMapping$0[Coin.BCH.ordinal()] = 1;
                $EnumSwitchMapping$0[Coin.BTC.ordinal()] = 2;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final WalletId localTempId() {
            StringBuilder sb = new StringBuilder();
            sb.append(ImagesContract.LOCAL);
            sb.append(UUID.randomUUID().toString());
            return new WalletId(sb.toString());
        }

        @NotNull
        public final String localPrefix(@NotNull Coin coin) {
            Intrinsics.checkParameterIsNotNull(coin, "coin");
            int i = WhenMappings.$EnumSwitchMapping$0[coin.ordinal()];
            if (i == 1) {
                return "localBch";
            }
            if (i == 2) {
                return "localBtc";
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    @NotNull
    public static /* synthetic */ WalletId copy$default(WalletId walletId, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = walletId.f377id;
        }
        return walletId.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.f377id;
    }

    @NotNull
    public final WalletId copy(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.f657ID);
        return new WalletId(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.f377id, (java.lang.Object) ((com.bitcoin.mwallet.core.models.wallet.WalletId) r2).f377id) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof com.bitcoin.mwallet.core.models.wallet.WalletId
            if (r0 == 0) goto L_0x0013
            com.bitcoin.mwallet.core.models.wallet.WalletId r2 = (com.bitcoin.mwallet.core.models.wallet.WalletId) r2
            java.lang.String r0 = r1.f377id
            java.lang.String r2 = r2.f377id
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r2 == 0) goto L_0x0013
            goto L_0x0015
        L_0x0013:
            r2 = 0
            return r2
        L_0x0015:
            r2 = 1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.wallet.WalletId.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.f377id;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public WalletId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.f657ID);
        this.f377id = str;
    }

    @NotNull
    public final String getId() {
        return this.f377id;
    }

    @NotNull
    public String toString() {
        return this.f377id;
    }

    public final boolean isLocal() {
        return StringsKt.startsWith$default(this.f377id, ImagesContract.LOCAL, false, 2, null);
    }
}
