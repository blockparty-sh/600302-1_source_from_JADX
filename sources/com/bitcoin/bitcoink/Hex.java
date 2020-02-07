package com.bitcoin.bitcoink;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\b\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\b\u0010\u000e\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/Hex;", "", "hex", "", "(Ljava/lang/String;)V", "getHex", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Hex.kt */
public final class Hex {
    public static final Companion Companion = new Companion(null);
    @NotNull
    private final String hex;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004¨\u0006\n"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/Hex$Companion;", "", "()V", "decode", "", "hex", "", "encode", "Lcom/bitcoin/bitcoink/Hex;", "bytes", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: Hex.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Hex encode(@NotNull byte[] bArr) {
            Intrinsics.checkParameterIsNotNull(bArr, "bytes");
            return new Hex(com.bitcoin.bitcoink.util.ByteUtils.Hex.INSTANCE.encode(bArr));
        }

        @NotNull
        public final byte[] decode(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "hex");
            return com.bitcoin.bitcoink.util.ByteUtils.Hex.INSTANCE.decode(str);
        }
    }

    @NotNull
    public static /* synthetic */ Hex copy$default(Hex hex2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = hex2.hex;
        }
        return hex2.copy(str);
    }

    @NotNull
    public final String component1() {
        return this.hex;
    }

    @NotNull
    public final Hex copy(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "hex");
        return new Hex(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x0010, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r1.hex, (java.lang.Object) ((com.bitcoin.bitcoink.Hex) r2).hex) != false) goto L_0x0015;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r2) {
        /*
            r1 = this;
            if (r1 == r2) goto L_0x0015
            boolean r0 = r2 instanceof com.bitcoin.bitcoink.Hex
            if (r0 == 0) goto L_0x0013
            com.bitcoin.bitcoink.Hex r2 = (com.bitcoin.bitcoink.Hex) r2
            java.lang.String r0 = r1.hex
            java.lang.String r2 = r2.hex
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
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.bitcoink.Hex.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        String str = this.hex;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public Hex(@NotNull String str) {
        Intrinsics.checkParameterIsNotNull(str, "hex");
        this.hex = str;
    }

    @NotNull
    public final String getHex() {
        return this.hex;
    }

    @NotNull
    public String toString() {
        return this.hex;
    }
}
