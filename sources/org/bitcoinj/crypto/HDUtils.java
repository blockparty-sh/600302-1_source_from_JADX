package org.bitcoinj.crypto;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nonnull;
import org.bitcoinj.core.ECKey;
import org.spongycastle.crypto.digests.SHA512Digest;
import org.spongycastle.crypto.macs.HMac;
import org.spongycastle.crypto.params.KeyParameter;

public final class HDUtils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Joiner PATH_JOINER = Joiner.m96on("/");

    static HMac createHmacSha512Digest(byte[] bArr) {
        HMac hMac = new HMac(new SHA512Digest());
        hMac.init(new KeyParameter(bArr));
        return hMac;
    }

    static byte[] hmacSha512(HMac hMac, byte[] bArr) {
        hMac.reset();
        hMac.update(bArr, 0, bArr.length);
        byte[] bArr2 = new byte[64];
        hMac.doFinal(bArr2, 0);
        return bArr2;
    }

    public static byte[] hmacSha512(byte[] bArr, byte[] bArr2) {
        return hmacSha512(createHmacSha512Digest(bArr), bArr2);
    }

    static byte[] toCompressed(byte[] bArr) {
        return ECKey.CURVE.getCurve().decodePoint(bArr).getEncoded(true);
    }

    static byte[] longTo4ByteArray(long j) {
        return Arrays.copyOfRange(ByteBuffer.allocate(8).putLong(j).array(), 4, 8);
    }

    public static ImmutableList<ChildNumber> append(List<ChildNumber> list, ChildNumber childNumber) {
        return ImmutableList.builder().addAll((Iterable) list).add((Object) childNumber).build();
    }

    public static ImmutableList<ChildNumber> concat(List<ChildNumber> list, List<ChildNumber> list2) {
        return ImmutableList.builder().addAll((Iterable) list).addAll((Iterable) list2).build();
    }

    public static String formatPath(List<ChildNumber> list) {
        return PATH_JOINER.join(Iterables.concat(Collections.singleton("M"), list));
    }

    public static List<ChildNumber> parsePath(@Nonnull String str) {
        String str2 = "";
        String[] split = str.replace("M", str2).split("/");
        ArrayList arrayList = new ArrayList();
        for (String replaceAll : split) {
            String replaceAll2 = replaceAll.replaceAll(" ", str2);
            if (replaceAll2.length() != 0) {
                boolean endsWith = replaceAll2.endsWith("H");
                if (endsWith) {
                    replaceAll2 = replaceAll2.substring(0, replaceAll2.length() - 1);
                }
                arrayList.add(new ChildNumber(Integer.parseInt(replaceAll2), endsWith));
            }
        }
        return arrayList;
    }
}
