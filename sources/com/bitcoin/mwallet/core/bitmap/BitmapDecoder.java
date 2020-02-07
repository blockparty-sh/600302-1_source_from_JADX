package com.bitcoin.mwallet.core.bitmap;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004J&\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004¨\u0006\u000e"}, mo37405d2 = {"Lcom/bitcoin/mwallet/core/bitmap/BitmapDecoder;", "", "()V", "calculateInSampleSize", "", "options", "Landroid/graphics/BitmapFactory$Options;", "reqWidth", "reqHeight", "decodeSampledBitmapFromResource", "Landroid/graphics/Bitmap;", "res", "Landroid/content/res/Resources;", "resId", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: BitmapDecoder.kt */
public final class BitmapDecoder {
    public final int calculateInSampleSize(@NotNull Options options, int i, int i2) {
        Intrinsics.checkParameterIsNotNull(options, "options");
        Pair pair = TuplesKt.m309to(Integer.valueOf(options.outHeight), Integer.valueOf(options.outWidth));
        int intValue = ((Number) pair.component1()).intValue();
        int intValue2 = ((Number) pair.component2()).intValue();
        int i3 = 1;
        if (intValue > i2 || intValue2 > i) {
            int i4 = intValue / 2;
            int i5 = intValue2 / 2;
            while (i4 / i3 >= i2 && i5 / i3 >= i) {
                i3 *= 2;
            }
        }
        return i3;
    }

    @NotNull
    public final Bitmap decodeSampledBitmapFromResource(@NotNull Resources resources, int i, int i2, int i3) {
        Intrinsics.checkParameterIsNotNull(resources, "res");
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources, i, options);
        options.inSampleSize = calculateInSampleSize(options, i2, i3);
        options.inJustDecodeBounds = false;
        Bitmap decodeResource = BitmapFactory.decodeResource(resources, i, options);
        Intrinsics.checkExpressionValueIsNotNull(decodeResource, "BitmapFactory.decodeResource(res, resId, this)");
        Intrinsics.checkExpressionValueIsNotNull(decodeResource, "BitmapFactory.Options().…s, resId, this)\n        }");
        return decodeResource;
    }
}
