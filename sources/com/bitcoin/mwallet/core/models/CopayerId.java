package com.bitcoin.mwallet.core.models;

import com.bitcoin.bitcoink.Hex;
import com.bitcoin.bitcoink.util.Sha256Hash;
import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\t\u001a\u00020\u0005HÆ\u0003J\u0013\u0010\n\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\b\u0010\u0010\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/CopayerId;", "", "id", "", "(Ljava/lang/String;)V", "Lcom/bitcoin/bitcoink/Hex;", "(Lcom/bitcoin/bitcoink/Hex;)V", "getId", "()Lcom/bitcoin/bitcoink/Hex;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: CopayerId.kt */
public final class CopayerId {
    public static final Companion Companion = new Companion(null);
    @NotNull

    /* renamed from: id */
    private final Hex f363id;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/CopayerId$Companion;", "", "()V", "fromXPubKey", "Lcom/bitcoin/mwallet/core/models/CopayerId;", "coin", "Lcom/bitcoin/mwallet/core/models/Coin;", "key", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: CopayerId.kt */
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
        public final CopayerId fromXPubKey(@NotNull Coin coin, @NotNull String str) {
            Intrinsics.checkParameterIsNotNull(coin, "coin");
            Intrinsics.checkParameterIsNotNull(str, "key");
            int i = WhenMappings.$EnumSwitchMapping$0[coin.ordinal()];
            if (i == 1) {
                StringBuilder sb = new StringBuilder();
                sb.append("bch");
                sb.append(str);
                str = sb.toString();
            } else if (i != 2) {
                throw new NoWhenBranchMatchedException();
            }
            Sha256Hash sha256Hash = Sha256Hash.INSTANCE;
            Charset charset = Charsets.UTF_8;
            if (str != null) {
                byte[] bytes = str.getBytes(charset);
                Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
                return new CopayerId(Hex.Companion.encode(sha256Hash.hashOnce(bytes)));
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
    }

    @NotNull
    public static /* synthetic */ CopayerId copy$default(CopayerId copayerId, Hex hex, int i, Object obj) {
        if ((i & 1) != 0) {
            hex = copayerId.f363id;
        }
        return copayerId.copy(hex);
    }

    @NotNull
    public final Hex component1() {
        return this.f363id;
    }

    @NotNull
    public final CopayerId copy(@NotNull Hex hex) {
        Intrinsics.checkParameterIsNotNull(hex, CommonProperties.f657ID);
        return new CopayerId(hex);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.f363id, (java.lang.Object) ((com.bitcoin.mwallet.core.models.CopayerId) r2).f363id) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof com.bitcoin.mwallet.core.models.CopayerId
            if (r0 == 0) goto L_0x0013
            com.bitcoin.mwallet.core.models.CopayerId r2 = (com.bitcoin.mwallet.core.models.CopayerId) r2
            com.bitcoin.bitcoink.Hex r0 = r1.f363id
            com.bitcoin.bitcoink.Hex r2 = r2.f363id
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
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.CopayerId.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        Hex hex = this.f363id;
        if (hex != null) {
            return hex.hashCode();
        }
        return 0;
    }

    public CopayerId(@NotNull Hex hex) {
        Intrinsics.checkParameterIsNotNull(hex, CommonProperties.f657ID);
        this.f363id = hex;
    }

    @NotNull
    public final Hex getId() {
        return this.f363id;
    }

    public CopayerId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.f657ID);
        this(new Hex(str));
    }

    @NotNull
    public String toString() {
        return this.f363id.getHex();
    }
}
