package com.bitcoin.mwallet.core.interactors;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.squareup.picasso.Callback;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.BooleanRef;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016¨\u0006\b"}, mo37405d2 = {"com/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor$trySetImage$1", "Lcom/squareup/picasso/Callback;", "onError", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* compiled from: VerifiedTokenInteractor.kt */
public final class VerifiedTokenInteractor$trySetImage$1 implements Callback {
    final /* synthetic */ ImageView $imageView;
    final /* synthetic */ Resources $resources;
    final /* synthetic */ BooleanRef $result;
    final /* synthetic */ VerifiedTokenInteractor this$0;

    public void onError(@NotNull Exception exc) {
        Intrinsics.checkParameterIsNotNull(exc, "e");
    }

    VerifiedTokenInteractor$trySetImage$1(VerifiedTokenInteractor verifiedTokenInteractor, ImageView imageView, Resources resources, BooleanRef booleanRef) {
        this.this$0 = verifiedTokenInteractor;
        this.$imageView = imageView;
        this.$resources = resources;
        this.$result = booleanRef;
    }

    public void onSuccess() {
        Drawable drawable = this.$imageView.getDrawable();
        if (drawable != null) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            ImageView imageView = this.$imageView;
            VerifiedTokenInteractor verifiedTokenInteractor = this.this$0;
            Resources resources = this.$resources;
            Intrinsics.checkExpressionValueIsNotNull(bitmap, "imageBitmap");
            imageView.setImageDrawable(verifiedTokenInteractor.getImageDrawable(resources, bitmap));
            this.$result.element = true;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
    }
}
