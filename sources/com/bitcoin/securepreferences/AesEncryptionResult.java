package com.bitcoin.securepreferences;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, mo37405d2 = {"Lcom/bitcoin/securepreferences/AesEncryptionResult;", "", "key", "", "encrypted", "Lorg/json/JSONObject;", "([BLorg/json/JSONObject;)V", "getEncrypted", "()Lorg/json/JSONObject;", "getKey", "()[B", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "securepreferences_release"}, mo37406k = 1, mo37407mv = {1, 1, 13})
/* compiled from: aes.kt */
public final class AesEncryptionResult {
    @NotNull
    private final JSONObject encrypted;
    @NotNull
    private final byte[] key;

    @NotNull
    public static /* synthetic */ AesEncryptionResult copy$default(AesEncryptionResult aesEncryptionResult, byte[] bArr, JSONObject jSONObject, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr = aesEncryptionResult.key;
        }
        if ((i & 2) != 0) {
            jSONObject = aesEncryptionResult.encrypted;
        }
        return aesEncryptionResult.copy(bArr, jSONObject);
    }

    @NotNull
    public final byte[] component1() {
        return this.key;
    }

    @NotNull
    public final JSONObject component2() {
        return this.encrypted;
    }

    @NotNull
    public final AesEncryptionResult copy(@NotNull byte[] bArr, @NotNull JSONObject jSONObject) {
        Intrinsics.checkParameterIsNotNull(bArr, "key");
        Intrinsics.checkParameterIsNotNull(jSONObject, "encrypted");
        return new AesEncryptionResult(bArr, jSONObject);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2.encrypted, (java.lang.Object) r3.encrypted) != false) goto L_0x001f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(@org.jetbrains.annotations.Nullable java.lang.Object r3) {
        /*
            r2 = this;
            if (r2 == r3) goto L_0x001f
            boolean r0 = r3 instanceof com.bitcoin.securepreferences.AesEncryptionResult
            if (r0 == 0) goto L_0x001d
            com.bitcoin.securepreferences.AesEncryptionResult r3 = (com.bitcoin.securepreferences.AesEncryptionResult) r3
            byte[] r0 = r2.key
            byte[] r1 = r3.key
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L_0x001d
            org.json.JSONObject r0 = r2.encrypted
            org.json.JSONObject r3 = r3.encrypted
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
        throw new UnsupportedOperationException("Method not decompiled: com.bitcoin.securepreferences.AesEncryptionResult.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        byte[] bArr = this.key;
        int i = 0;
        int hashCode = (bArr != null ? Arrays.hashCode(bArr) : 0) * 31;
        JSONObject jSONObject = this.encrypted;
        if (jSONObject != null) {
            i = jSONObject.hashCode();
        }
        return hashCode + i;
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AesEncryptionResult(key=");
        sb.append(Arrays.toString(this.key));
        sb.append(", encrypted=");
        sb.append(this.encrypted);
        sb.append(")");
        return sb.toString();
    }

    public AesEncryptionResult(@NotNull byte[] bArr, @NotNull JSONObject jSONObject) {
        Intrinsics.checkParameterIsNotNull(bArr, "key");
        Intrinsics.checkParameterIsNotNull(jSONObject, "encrypted");
        this.key = bArr;
        this.encrypted = jSONObject;
    }

    @NotNull
    public final JSONObject getEncrypted() {
        return this.encrypted;
    }

    @NotNull
    public final byte[] getKey() {
        return this.key;
    }
}
