package com.bitcoin.mwallet.core.utils.slp;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.ULong;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongycastle.util.encoders.DecoderException;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\n\u000bB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u0015\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006ø\u0001\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\f"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/utils/slp/ByteUtils;", "", "()V", "toInt", "", "bytes", "", "toULong", "Lkotlin/ULong;", "([B)J", "Base58", "Hex", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: ByteUtils.kt */
public final class ByteUtils {
    public static final ByteUtils INSTANCE = new ByteUtils();

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/utils/slp/ByteUtils$Base58;", "", "()V", "encode", "", "input", "", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ByteUtils.kt */
    public static final class Base58 {
        public static final Base58 INSTANCE = new Base58();

        private Base58() {
        }

        @NotNull
        public final String encode(@NotNull byte[] bArr) {
            Intrinsics.checkParameterIsNotNull(bArr, "input");
            String encode = org.bitcoinj.core.Base58.encode(bArr);
            Intrinsics.checkExpressionValueIsNotNull(encode, "org.bitcoinj.core.Base58.encode(input)");
            return encode;
        }
    }

    @Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004¨\u0006\t"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/utils/slp/ByteUtils$Hex;", "", "()V", "decode", "", "hex", "", "encode", "bytes", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
    /* compiled from: ByteUtils.kt */
    public static final class Hex {
        public static final Hex INSTANCE = new Hex();

        private Hex() {
        }

        @NotNull
        public final String encode(@NotNull byte[] bArr) {
            Intrinsics.checkParameterIsNotNull(bArr, "bytes");
            String hexString = org.spongycastle.util.encoders.Hex.toHexString(bArr);
            Intrinsics.checkExpressionValueIsNotNull(hexString, "org.spongycastle.util.en…rs.Hex.toHexString(bytes)");
            return hexString;
        }

        @NotNull
        public final byte[] decode(@NotNull String str) {
            Intrinsics.checkParameterIsNotNull(str, "hex");
            try {
                byte[] decode = org.spongycastle.util.encoders.Hex.decode(str);
                Intrinsics.checkExpressionValueIsNotNull(decode, "org.spongycastle.util.encoders.Hex.decode(hex)");
                return decode;
            } catch (DecoderException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private ByteUtils() {
    }

    public final long toULong(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "bytes");
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        Intrinsics.checkExpressionValueIsNotNull(wrap, "ByteBuffer.wrap(bytes)");
        return ULong.m633constructorimpl(wrap.getLong());
    }

    @Nullable
    /* renamed from: toULong reason: collision with other method in class */
    public final ULong m475toULong(@Nullable byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        Intrinsics.checkExpressionValueIsNotNull(wrap, "ByteBuffer.wrap(it)");
        return ULong.m627boximpl(ULong.m633constructorimpl(wrap.getLong()));
    }

    public final int toInt(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "bytes");
        Buffer position = ByteBuffer.allocate(4).position(4 - bArr.length);
        if (position != null) {
            ByteBuffer byteBuffer = (ByteBuffer) position;
            byteBuffer.put(bArr);
            return byteBuffer.getInt(0);
        }
        throw new TypeCastException("null cannot be cast to non-null type java.nio.ByteBuffer");
    }
}
