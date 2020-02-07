package com.bitcoin.bitcoink.util;

import com.bitcoin.bitcoink.util.ByteUtils.Base58;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001H\u0002J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\u0006\u0010\u000f\u001a\u00020\u0010R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0012"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/util/Base58CheckEncoding;", "", "version", "", "bytes", "", "(I[B)V", "getBytes", "()[B", "getVersion", "()I", "equals", "", "other", "hashCode", "toBase58", "", "Companion", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Base58CheckEncoding.kt */
public class Base58CheckEncoding {
    public static final Companion Companion = new Companion(null);
    private static final int checksumLength = 4;
    private static final int versionLength = 1;
    @NotNull
    private final byte[] bytes;
    private final int version;

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/util/Base58CheckEncoding$Companion;", "", "()V", "checksumLength", "", "versionLength", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: Base58CheckEncoding.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public Base58CheckEncoding(int i, @NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "bytes");
        this.version = i;
        this.bytes = bArr;
    }

    /* access modifiers changed from: protected */
    public final int getVersion() {
        return this.version;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final byte[] getBytes() {
        return this.bytes;
    }

    @NotNull
    public final String toBase58() {
        byte[] bArr = this.bytes;
        byte[] bArr2 = new byte[(bArr.length + 1 + 4)];
        bArr2[0] = (byte) this.version;
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        System.arraycopy(Sha256Hash.INSTANCE.hashTwice(bArr2, 0, this.bytes.length + 1), 0, bArr2, this.bytes.length + 1, 4);
        return Base58.INSTANCE.encode(bArr2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) getClass(), (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            Base58CheckEncoding base58CheckEncoding = (Base58CheckEncoding) obj;
            if (this.version == base58CheckEncoding.version && Arrays.equals(this.bytes, base58CheckEncoding.bytes)) {
                return true;
            }
            return false;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.bitcoin.bitcoink.util.Base58CheckEncoding");
    }

    public int hashCode() {
        return (this.version * 31) + Arrays.hashCode(this.bytes);
    }
}
