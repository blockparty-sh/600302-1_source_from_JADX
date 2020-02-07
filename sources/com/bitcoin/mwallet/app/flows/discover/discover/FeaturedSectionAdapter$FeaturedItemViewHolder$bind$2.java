package com.bitcoin.mwallet.app.flows.discover.discover;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.bitcoin.mwallet.app.flows.discover.discover.FeaturedSectionAdapter.FeaturedItemViewHolder;
import com.bitcoin.mwallet.core.models.discover.FeaturedBusiness;
import com.bitcoin.mwallet.core.utils.PixelsKt;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, mo37405d2 = {"<anonymous>", "", "onGlobalLayout"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: FeaturedSectionAdapter.kt */
final class FeaturedSectionAdapter$FeaturedItemViewHolder$bind$2 implements OnGlobalLayoutListener {
    final /* synthetic */ FeaturedBusiness $item;
    final /* synthetic */ FeaturedItemViewHolder this$0;

    FeaturedSectionAdapter$FeaturedItemViewHolder$bind$2(FeaturedItemViewHolder featuredItemViewHolder, FeaturedBusiness featuredBusiness) {
        this.this$0 = featuredItemViewHolder;
        this.$item = featuredBusiness;
    }

    public final void onGlobalLayout() {
        Picasso.get().load(this.$item.getImageURL()).resize(this.this$0.linkBanner.getWidth(), this.this$0.linkBanner.getHeight()).centerCrop().into(this.this$0.linkBanner, new Callback(this) {
            final /* synthetic */ FeaturedSectionAdapter$FeaturedItemViewHolder$bind$2 this$0;

            public void onError(@NotNull Exception exc) {
                Intrinsics.checkParameterIsNotNull(exc, "e");
            }

            {
                this.this$0 = r1;
            }

            public void onSuccess() {
                Drawable drawable = this.this$0.this$0.linkBanner.getDrawable();
                if (drawable != null) {
                    RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(this.this$0.this$0.getView().getResources(), ((BitmapDrawable) drawable).getBitmap());
                    Intrinsics.checkExpressionValueIsNotNull(create, "RoundedBitmapDrawableFac…w.resources, imageBitmap)");
                    create.setCircular(true);
                    Context context = this.this$0.this$0.getView().getContext();
                    Intrinsics.checkExpressionValueIsNotNull(context, "view.context");
                    create.setCornerRadius(PixelsKt.pxFromDp(context, 12.0f));
                    this.this$0.this$0.linkBanner.setImageDrawable(create);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type android.graphics.drawable.BitmapDrawable");
            }
        });
    }
}
