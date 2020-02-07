package com.bitcoin.mwallet.zion;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0019\u0012\b\u0010\u0003\u001a\u0004\u0018\u00018\u0000\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\f\u001a\u0004\u0018\u00018\u0000HÆ\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J,\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00018\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u000fJ\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0015\u0010\u0003\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n¨\u0006\u0017"}, mo37405d2 = {"Lcom/bitcoin/mwallet/zion/ZionResponse;", "T", "", "result", "error", "Lcom/bitcoin/mwallet/zion/ZionError;", "(Ljava/lang/Object;Lcom/bitcoin/mwallet/zion/ZionError;)V", "getError", "()Lcom/bitcoin/mwallet/zion/ZionError;", "getResult", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(Ljava/lang/Object;Lcom/bitcoin/mwallet/zion/ZionError;)Lcom/bitcoin/mwallet/zion/ZionResponse;", "equals", "", "other", "hashCode", "", "toString", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ZionResponse.kt */
public final class ZionResponse<T> {
    @Nullable
    private final ZionError error;
    @Nullable
    private final T result;

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.lang.Object, code=T, for r1v0, types: [java.lang.Object] */
    @org.jetbrains.annotations.NotNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static /* synthetic */ com.bitcoin.mwallet.zion.ZionResponse copy$default(com.bitcoin.mwallet.zion.ZionResponse r0, T r1, com.bitcoin.mwallet.zion.ZionError r2, int r3, java.lang.Object r4) {
        /*
            r4 = r3 & 1
            if (r4 == 0) goto L_0x0006
            T r1 = r0.result
        L_0x0006:
            r3 = r3 & 2
            if (r3 == 0) goto L_0x000c
            com.bitcoin.mwallet.zion.ZionError r2 = r0.error
        L_0x000c:
            com.bitcoin.mwallet.zion.ZionResponse r0 = r0.copy(r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionResponse.copy$default(com.bitcoin.mwallet.zion.ZionResponse, java.lang.Object, com.bitcoin.mwallet.zion.ZionError, int, java.lang.Object):com.bitcoin.mwallet.zion.ZionResponse");
    }

    @Nullable
    public final T component1() {
        return this.result;
    }

    @Nullable
    public final ZionError component2() {
        return this.error;
    }

    @NotNull
    public final ZionResponse<T> copy(@Nullable T t, @Nullable ZionError zionError) {
        return new ZionResponse<>(t, zionError);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.error, (java.lang.Object) r3.error) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.bitcoin.mwallet.zion.ZionResponse
            if (r0 == 0) goto L_0x001d
            com.bitcoin.mwallet.zion.ZionResponse r3 = (com.bitcoin.mwallet.zion.ZionResponse) r3
            T r0 = r2.result
            T r1 = r3.result
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            com.bitcoin.mwallet.zion.ZionError r0 = r2.error
            com.bitcoin.mwallet.zion.ZionError r3 = r3.error
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            if (r3 == 0) goto L_0x001d
            goto L_0x001f
        L_0x001d:
            r3 = 0
            return r3
        L_0x001f:
            r3 = 1
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.mwallet.zion.ZionResponse.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        T t = this.result;
        int i = 0;
        int hashCode = (t != null ? t.hashCode() : 0) * 31;
        ZionError zionError = this.error;
        if (zionError != null) {
            i = zionError.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ZionResponse(result=");
        sb.append(this.result);
        sb.append(", error=");
        sb.append(this.error);
        sb.append(")");
        return sb.toString();
    }

    public ZionResponse(@Nullable T t, @Nullable ZionError zionError) {
        this.result = t;
        this.error = zionError;
    }

    @Nullable
    public final T getResult() {
        return this.result;
    }

    @Nullable
    public final ZionError getError() {
        return this.error;
    }
}
