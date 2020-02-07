package com.bitcoin.mwallet.core.interactors;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.bitcoin.mwallet.core.models.verifiedtoken.VerifiedToken;
import com.squareup.picasso.Callback;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.BooleanRef;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016¨\u0006\b¸\u0006\u0000"}, mo37405d2 = {"com/bitcoin/mwallet/core/interactors/VerifiedTokenInteractor$trySetVerifiedTokenImage$1$1", "Lcom/squareup/picasso/Callback;", "onError", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onSuccess", "app_replaceRelease"}, mo37406k = 1, mo37407mv = {1, 1, 15})
/* renamed from: com.bitcoin.mwallet.core.interactors.VerifiedTokenInteractor$trySetVerifiedTokenImage$$inlined$let$lambda$1 */
/* compiled from: VerifiedTokenInteractor.kt */
public final class C1260x77dad321 implements Callback {
    final /* synthetic */ ImageView $imageView$inlined;
    final /* synthetic */ Resources $resources$inlined;
    final /* synthetic */ BooleanRef $result$inlined;
    final /* synthetic */ VerifiedToken $veriToken$inlined;
    final /* synthetic */ VerifiedTokenInteractor this$0;

    public void onError(@NotNull Exception exc) {
        Intrinsics.checkParameterIsNotNull(exc, "e");
    }

    C1260x77dad321(VerifiedTokenInteractor verifiedTokenInteractor, VerifiedToken verifiedToken, ImageView imageView, Resources resources, BooleanRef booleanRef) {
        this.this$0 = verifiedTokenInteractor;
        this.$veriToken$inlined = verifiedToken;
        this.$imageView$inlined = imageView;
        this.$resources$inlined = resources;
        this.$result$inlined = booleanRef;
    }

    public void onSuccess() {
        Drawable drawable = this.$imageView$inlined.getDrawable();
        if (drawable != null) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            ImageView imageView = this.$imageView$inlined;
            VerifiedTokenInteractor verifiedTokenInteractor = this.this$0;
            Resources resources = this.$resources$inlined;
            Intrinsics.checkExpressionValueIsNotNull(bitmap, "imageBitmap");
            imageView.setImageDrawable(verifiedTokenInteractor.getImageDrawable(resources, bitmap));
            this.$result$inlined.element = true;
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
    }
}
