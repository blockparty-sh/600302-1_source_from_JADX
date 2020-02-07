package com.bitcoin.mwallet.app.flows.sendv2.dialogs.selectsendingasset;

import android.graphics.Bitmap;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"getImageDrawable", "Landroidx/core/graphics/drawable/RoundedBitmapDrawable;", "imageBitmap", "Landroid/graphics/Bitmap;", "invoke"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: SelectSendingAssetViewHolder.kt */
final class SelectSendingAssetViewHolder$bind$1 extends Lambda implements Function1<Bitmap, RoundedBitmapDrawable> {
    final /* synthetic */ SelectSendingAssetViewHolder this$0;

    SelectSendingAssetViewHolder$bind$1(SelectSendingAssetViewHolder selectSendingAssetViewHolder) {
        this.this$0 = selectSendingAssetViewHolder;
        super(1);
    }

    @NotNull
    public final RoundedBitmapDrawable invoke(@NotNull Bitmap bitmap) {
        Intrinsics.checkParameterIsNotNull(bitmap, "imageBitmap");
        RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(this.this$0.getView().getResources(), bitmap);
        Intrinsics.checkExpressionValueIsNotNull(create, "RoundedBitmapDrawableFac…w.resources, imageBitmap)");
        create.setCircular(true);
        create.setCornerRadius(((float) bitmap.getHeight()) / 2.0f);
        return create;
    }
}
