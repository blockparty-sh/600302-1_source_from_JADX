package com.bitcoin.bitcoink.address;

import com.bitcoin.bitcoink.util.ByteUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\b\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0006J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\tJ\u0016\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0006¨\u0006\u0013"}, mo37405d2 = {"Lcom/bitcoin/bitcoink/address/AddressCashValidator;", "", "()V", "checkAllowedPadding", "", "extraBits", "", "checkDataLength", "data", "", "hashSize", "", "checkFirstBitIsZero", "versionByte", "checkNonEmptyPayload", "payload", "checkNonZeroPadding", "last", "mask", "bitcoink"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: AddressCashValidator.kt */
public final class AddressCashValidator {
    public static final AddressCashValidator INSTANCE = new AddressCashValidator();

    private AddressCashValidator() {
    }

    public final void checkNonEmptyPayload(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "payload");
        if (bArr.length == 0) {
            throw new IllegalArgumentException("No payload");
        }
    }

    public final void checkAllowedPadding(byte b) {
        if (b >= 5) {
            throw new IllegalArgumentException("More than allowed padding");
        }
    }

    public final void checkNonZeroPadding(byte b, byte b2) {
        if (((byte) (b & b2)) != 0) {
            throw new IllegalArgumentException("Nonzero padding bytes");
        }
    }

    public final void checkFirstBitIsZero(byte b) {
        if (ByteUtils.INSTANCE.and(b, 128) != 0 && ByteUtils.INSTANCE.and(b, 128) != 8) {
            throw new IllegalArgumentException("First bit is reserved");
        }
    }

    public final void checkDataLength(@NotNull byte[] bArr, int i) {
        Intrinsics.checkParameterIsNotNull(bArr, "data");
        if (bArr.length != i + 1) {
            StringBuilder sb = new StringBuilder();
            sb.append("Data length ");
            sb.append(bArr.length);
            sb.append(" != hash size ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
    }
}
