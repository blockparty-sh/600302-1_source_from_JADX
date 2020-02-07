package com.bitcoin.bitcoink.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bJ\u001e\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fR\u0014\u0010\u0003\u001a\u00020\u00048BX\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000e"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/util/Sha256Hash;", "", "()V", "sha256", "Ljava/security/MessageDigest;", "getSha256", "()Ljava/security/MessageDigest;", "hashOnce", "", "bytes", "hashTwice", "offset", "", "length", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: Sha256Hash.kt */
public final class Sha256Hash {
    public static final Sha256Hash INSTANCE = new Sha256Hash();

    private Sha256Hash() {
    }

    private final MessageDigest getSha256() {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            Intrinsics.checkExpressionValueIsNotNull(instance, "MessageDigest.getInstance(\"SHA-256\")");
            return instance;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @NotNull
    public final byte[] hashOnce(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "bytes");
        MessageDigest sha256 = getSha256();
        sha256.update(bArr, 0, bArr.length);
        byte[] digest = sha256.digest();
        Intrinsics.checkExpressionValueIsNotNull(digest, "sha256.digest()");
        return digest;
    }

    @NotNull
    public final byte[] hashTwice(@NotNull byte[] bArr, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(bArr, "bytes");
        MessageDigest sha256 = getSha256();
        sha256.update(bArr, i, i2);
        byte[] digest = sha256.digest(sha256.digest());
        Intrinsics.checkExpressionValueIsNotNull(digest, "sha256.digest(sha256.digest())");
        return digest;
    }
}
