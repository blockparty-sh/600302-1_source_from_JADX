package com.bitcoin.mwallet.core.models.slp;

import com.microsoft.appcenter.ingestion.models.CommonProperties;
import java.io.Serializable;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\b\u0010\u0015\u001a\u00020\u0003H\u0016R\u001b\u0010\u0005\u001a\u00020\u00068FX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "Ljava/io/Serializable;", "id", "", "(Ljava/lang/String;)V", "bytes", "", "getBytes", "()[B", "bytes$delegate", "Lkotlin/Lazy;", "getId", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Companion", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: SlpId.kt */
public final class SlpId implements Serializable {
    static final /* synthetic */ KProperty[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(SlpId.class), "bytes", "getBytes()[B"))};
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final Lazy bytes$delegate = LazyKt.lazy(new SlpId$bytes$2(this));
    @NotNull

    /* renamed from: id */
    private final String f372id;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/slp/SlpId$Companion;", "", "()V", "baseCoin", "Lcom/bitcoin/mwallet/core/models/slp/SlpId;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: SlpId.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final SlpId baseCoin() {
            return new SlpId("base");
        }
    }

    @NotNull
    public static /* synthetic */ SlpId copy$default(SlpId slpId, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = slpId.f372id;
        }
        return slpId.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.f372id;
    }

    @NotNull
    public final SlpId copy(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.f657ID);
        return new SlpId(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.f372id, (java.lang.Object) ((com.bitcoin.mwallet.core.models.slp.SlpId) r2).f372id) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof com.bitcoin.mwallet.core.models.slp.SlpId
            if (r0 == 0) goto L_0x0013
            com.bitcoin.mwallet.core.models.slp.SlpId r2 = (com.bitcoin.mwallet.core.models.slp.SlpId) r2
            java.lang.String r0 = r1.f372id
            java.lang.String r2 = r2.f372id
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
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.slp.SlpId.equals(java.lang.Object):boolean");
    }

    @NotNull
    public final byte[] getBytes() {
        Lazy lazy = this.bytes$delegate;
        KProperty kProperty = $$delegatedProperties[0];
        return (byte[]) lazy.getValue();
    }

    public int hashCode() {
        String str = this.f372id;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public SlpId(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, CommonProperties.f657ID);
        this.f372id = str;
    }

    @NotNull
    public final String getId() {
        return this.f372id;
    }

    @NotNull
    public String toString() {
        return this.f372id;
    }
}
