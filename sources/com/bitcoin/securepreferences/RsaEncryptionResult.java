package com.bitcoin.securepreferences;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, mo37405d2 = {"Lcom/bitcoin/securepreferences/RsaEncryptionResult;", "", "ciphertext", "", "version", "", "([BI)V", "getCiphertext", "()[B", "getVersion", "()I", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "securepreferences_release"}, mo37406k = 1, mo37407mv = {1, 1, 13})
/* compiled from: rsa.kt */
public final class RsaEncryptionResult {
    @NotNull
    private final byte[] ciphertext;
    private final int version;

    @NotNull
    public static /* synthetic */ RsaEncryptionResult copy$default(RsaEncryptionResult rsaEncryptionResult, byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            bArr = rsaEncryptionResult.ciphertext;
        }
        if ((i2 & 2) != 0) {
            i = rsaEncryptionResult.version;
        }
        return rsaEncryptionResult.copy(bArr, i);
    }

    @NotNull
    public final byte[] component1() {
        return this.ciphertext;
    }

    public final int component2() {
        return this.version;
    }

    @NotNull
    public final RsaEncryptionResult copy(@NotNull byte[] bArr, int i) {
        Intrinsics.checkParameterIsNotNull(bArr, "ciphertext");
        return new RsaEncryptionResult(bArr, i);
    }

    public boolean equals(@Nullable Object obj) {
        if (this != obj) {
            if (obj instanceof RsaEncryptionResult) {
                RsaEncryptionResult rsaEncryptionResult = (RsaEncryptionResult) obj;
                if (Intrinsics.areEqual((Object) this.ciphertext, (Object) rsaEncryptionResult.ciphertext)) {
                    if (this.version == rsaEncryptionResult.version) {
                        return true;
                    }
                }
            }
            return false;
        }
        return true;
    }

    public int hashCode() {
        byte[] bArr = this.ciphertext;
        return ((bArr != null ? Arrays.hashCode(bArr) : 0) * 31) + this.version;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RsaEncryptionResult(ciphertext=");
        sb.append(Arrays.toString(this.ciphertext));
        sb.append(", version=");
        sb.append(this.version);
        sb.append(")");
        return sb.toString();
    }

    public RsaEncryptionResult(@NotNull byte[] bArr, int i) {
        Intrinsics.checkParameterIsNotNull(bArr, "ciphertext");
        this.ciphertext = bArr;
        this.version = i;
    }

    @NotNull
    public final byte[] getCiphertext() {
        return this.ciphertext;
    }

    public final int getVersion() {
        return this.version;
    }
}
