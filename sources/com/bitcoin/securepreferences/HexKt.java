package com.bitcoin.securepreferences;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0000\u001a\n\u0010\u0002\u001a\u00020\u0003*\u00020\u0004\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, mo37405d2 = {"hexArray", "", "toHexString", "", "", "securepreferences_release"}, mo37406k = 2, mo37407mv = {1, 1, 13})
/* compiled from: hex.kt */
public final class HexKt {
    private static final char[] hexArray;

    static {
        char[] charArray = "0123456789ABCDEF".toCharArray();
        Intrinsics.checkExpressionValueIsNotNull(charArray, "(this as java.lang.String).toCharArray()");
        hexArray = charArray;
    }

    @NotNull
    public static final String toHexString(@NotNull byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "receiver$0");
        char[] cArr = new char[(bArr.length * 2)];
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            byte r3 = UByte.m497constructorimpl(bArr[i]);
            int i2 = i * 2;
            char[] cArr2 = hexArray;
            cArr[i2] = cArr2[(r3 & 255) >>> 4];
            cArr[i2 + 1] = cArr2[UByte.m497constructorimpl((byte) (r3 & UByte.m497constructorimpl((byte) 15))) & 255];
        }
        return new String(cArr);
    }
}
