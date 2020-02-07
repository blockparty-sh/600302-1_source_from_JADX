package com.bitcoin.mwallet.core.models.p009tx.slputxo;

import com.bitcoin.bitcoink.p008tx.Satoshis;
import com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo;
import com.bitcoin.mwallet.core.models.slp.Slp;
import java.util.List;
import kotlin.Metadata;
import kotlin.ULong;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\b\u0018\u0000 '2\u00020\u0001:\u0002'(BH\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\rø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u0012\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003ø\u0001\u0000J\t\u0010\u001b\u001a\u00020\bHÆ\u0003J\u000b\u0010\u001c\u001a\u0004\u0018\u00010\bHÆ\u0003J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0005HÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\rHÆ\u0003JX\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\b2\u000e\b\u0002\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00052\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001ø\u0001\u0000J\u0013\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010#\u001a\u00020$HÖ\u0001J\t\u0010%\u001a\u00020&HÖ\u0001R\u0013\u0010\t\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005ø\u0001\u0000¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection;", "", "token", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "quantities", "", "Lkotlin/ULong;", "fee", "Lcom/bitcoin/bitcoink/tx/Satoshis;", "change", "utxos", "Lcom/bitcoin/mwallet/core/models/tx/utxo/Utxo;", "error", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection$Error;", "(Lcom/bitcoin/mwallet/core/models/slp/Slp;Ljava/util/List;Lcom/bitcoin/bitcoink/tx/Satoshis;Lcom/bitcoin/bitcoink/tx/Satoshis;Ljava/util/List;Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection$Error;)V", "getChange", "()Lcom/bitcoin/bitcoink/tx/Satoshis;", "getError", "()Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection$Error;", "getFee", "getQuantities", "()Ljava/util/List;", "getToken", "()Lcom/bitcoin/mwallet/core/models/slp/Slp;", "getUtxos", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Companion", "Error", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection */
/* compiled from: SlpUtxoSelection.kt */
public final class SlpUtxoSelection {
    public static final Companion Companion = new Companion(null);
    @Nullable
    private final Satoshis change;
    @Nullable
    private final Error error;
    @NotNull
    private final Satoshis fee;
    @NotNull
    private final List<ULong> quantities;
    @NotNull
    private final Slp token;
    @NotNull
    private final List<Utxo> utxos;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\t¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection$Companion;", "", "()V", "error", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection;", "token", "Lcom/bitcoin/mwallet/core/models/slp/Slp;", "sendTokenAmount", "", "Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection$Error;", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* renamed from: com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection$Companion */
    /* compiled from: SlpUtxoSelection.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final SlpUtxoSelection error(@NotNull Slp slp, long j, @NotNull Error error) {
            Intrinsics.checkParameterIsNotNull(slp, "token");
            Intrinsics.checkParameterIsNotNull(error, "error");
            SlpUtxoSelection slpUtxoSelection = new SlpUtxoSelection(slp, CollectionsKt.emptyList(), Satoshis.Companion.getZERO(), Satoshis.Companion.getZERO(), CollectionsKt.emptyList(), error);
            return slpUtxoSelection;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0003\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003¨\u0006\u0004"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/models/tx/slputxo/SlpUtxoSelection$Error;", "", "(Ljava/lang/String;I)V", "INSUFFICIENT_FUNDS", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* renamed from: com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection$Error */
    /* compiled from: SlpUtxoSelection.kt */
    public enum Error {
        INSUFFICIENT_FUNDS
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<com.bitcoin.mwallet.core.models.tx.utxo.Utxo>, for r9v0, types: [java.util.List] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<kotlin.ULong>, for r6v0, types: [java.util.List] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection copy$default(com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection r4, com.bitcoin.mwallet.core.models.slp.Slp r5, java.util.List<kotlin.ULong> r6, com.bitcoin.bitcoink.p008tx.Satoshis r7, com.bitcoin.bitcoink.p008tx.Satoshis r8, java.util.List<com.bitcoin.mwallet.core.models.p009tx.utxo.Utxo> r9, com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection.Error r10, int r11, java.lang.Object r12) {
        /*
            r12 = r11 & 1
            if (r12 == 0) goto L_0x0006
            com.bitcoin.mwallet.core.models.slp.Slp r5 = r4.token
        L_0x0006:
            r12 = r11 & 2
            if (r12 == 0) goto L_0x000c
            java.util.List<kotlin.ULong> r6 = r4.quantities
        L_0x000c:
            r12 = r6
            r6 = r11 & 4
            if (r6 == 0) goto L_0x0013
            com.bitcoin.bitcoink.tx.Satoshis r7 = r4.fee
        L_0x0013:
            r0 = r7
            r6 = r11 & 8
            if (r6 == 0) goto L_0x001a
            com.bitcoin.bitcoink.tx.Satoshis r8 = r4.change
        L_0x001a:
            r1 = r8
            r6 = r11 & 16
            if (r6 == 0) goto L_0x0021
            java.util.List<com.bitcoin.mwallet.core.models.tx.utxo.Utxo> r9 = r4.utxos
        L_0x0021:
            r2 = r9
            r6 = r11 & 32
            if (r6 == 0) goto L_0x0028
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection$Error r10 = r4.error
        L_0x0028:
            r3 = r10
            r6 = r4
            r7 = r5
            r8 = r12
            r9 = r0
            r10 = r1
            r11 = r2
            r12 = r3
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r4 = r6.copy(r7, r8, r9, r10, r11, r12)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection.copy$default(com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection, com.bitcoin.mwallet.core.models.slp.Slp, java.util.List, com.bitcoin.bitcoink.tx.Satoshis, com.bitcoin.bitcoink.tx.Satoshis, java.util.List, com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection$Error, int, java.lang.Object):com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection");
    }

    @NotNull
    public final Slp component1() {
        return this.token;
    }

    @NotNull
    public final List<ULong> component2() {
        return this.quantities;
    }

    @NotNull
    public final Satoshis component3() {
        return this.fee;
    }

    @Nullable
    public final Satoshis component4() {
        return this.change;
    }

    @NotNull
    public final List<Utxo> component5() {
        return this.utxos;
    }

    @Nullable
    public final Error component6() {
        return this.error;
    }

    @NotNull
    public final SlpUtxoSelection copy(@NotNull Slp slp, @NotNull List<ULong> list, @NotNull Satoshis satoshis, @Nullable Satoshis satoshis2, @NotNull List<Utxo> list2, @Nullable Error error2) {
        Intrinsics.checkParameterIsNotNull(slp, "token");
        Intrinsics.checkParameterIsNotNull(list, "quantities");
        Intrinsics.checkParameterIsNotNull(satoshis, "fee");
        Intrinsics.checkParameterIsNotNull(list2, "utxos");
        SlpUtxoSelection slpUtxoSelection = new SlpUtxoSelection(slp, list, satoshis, satoshis2, list2, error2);
        return slpUtxoSelection;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0042, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.error, (java.lang.Object) r3.error) != false) goto L_0x0047;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x0047
            boolean r0 = r3 instanceof com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection
            if (r0 == 0) goto L_0x0045
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection r3 = (com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection) r3
            com.bitcoin.mwallet.core.models.slp.Slp r0 = r2.token
            com.bitcoin.mwallet.core.models.slp.Slp r1 = r3.token
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0045
            java.util.List<kotlin.ULong> r0 = r2.quantities
            java.util.List<kotlin.ULong> r1 = r3.quantities
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0045
            com.bitcoin.bitcoink.tx.Satoshis r0 = r2.fee
            com.bitcoin.bitcoink.tx.Satoshis r1 = r3.fee
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0045
            com.bitcoin.bitcoink.tx.Satoshis r0 = r2.change
            com.bitcoin.bitcoink.tx.Satoshis r1 = r3.change
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0045
            java.util.List<com.bitcoin.mwallet.core.models.tx.utxo.Utxo> r0 = r2.utxos
            java.util.List<com.bitcoin.mwallet.core.models.tx.utxo.Utxo> r1 = r3.utxos
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x0045
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection$Error r0 = r2.error
            com.bitcoin.mwallet.core.models.tx.slputxo.SlpUtxoSelection$Error r3 = r3.error
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
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.core.models.p009tx.slputxo.SlpUtxoSelection.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        Slp slp = this.token;
        int i = 0;
        int hashCode = (slp != null ? slp.hashCode() : 0) * 31;
        List<ULong> list = this.quantities;
        int hashCode2 = (hashCode + (list != null ? list.hashCode() : 0)) * 31;
        Satoshis satoshis = this.fee;
        int hashCode3 = (hashCode2 + (satoshis != null ? satoshis.hashCode() : 0)) * 31;
        Satoshis satoshis2 = this.change;
        int hashCode4 = (hashCode3 + (satoshis2 != null ? satoshis2.hashCode() : 0)) * 31;
        List<Utxo> list2 = this.utxos;
        int hashCode5 = (hashCode4 + (list2 != null ? list2.hashCode() : 0)) * 31;
        Error error2 = this.error;
        if (error2 != null) {
            i = error2.hashCode();
        }
        return hashCode5 + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SlpUtxoSelection(token=");
        sb.append(this.token);
        sb.append(", quantities=");
        sb.append(this.quantities);
        sb.append(", fee=");
        sb.append(this.fee);
        sb.append(", change=");
        sb.append(this.change);
        sb.append(", utxos=");
        sb.append(this.utxos);
        sb.append(", error=");
        sb.append(this.error);
        sb.append(")");
        return sb.toString();
    }

    public SlpUtxoSelection(@NotNull Slp slp, @NotNull List<ULong> list, @NotNull Satoshis satoshis, @Nullable Satoshis satoshis2, @NotNull List<Utxo> list2, @Nullable Error error2) {
        Intrinsics.checkParameterIsNotNull(slp, "token");
        Intrinsics.checkParameterIsNotNull(list, "quantities");
        Intrinsics.checkParameterIsNotNull(satoshis, "fee");
        Intrinsics.checkParameterIsNotNull(list2, "utxos");
        this.token = slp;
        this.quantities = list;
        this.fee = satoshis;
        this.change = satoshis2;
        this.utxos = list2;
        this.error = error2;
    }

    @NotNull
    public final Slp getToken() {
        return this.token;
    }

    @NotNull
    public final List<ULong> getQuantities() {
        return this.quantities;
    }

    @NotNull
    public final Satoshis getFee() {
        return this.fee;
    }

    @Nullable
    public final Satoshis getChange() {
        return this.change;
    }

    @NotNull
    public final List<Utxo> getUtxos() {
        return this.utxos;
    }

    @Nullable
    public final Error getError() {
        return this.error;
    }
}
