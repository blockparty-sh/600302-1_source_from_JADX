package com.bitcoin.mwallet.app.flows.receive.receive;

import android.graphics.Bitmap;
import android.widget.ImageView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, mo37405d2 = {"<anonymous>", "", "image", "Landroid/graphics/Bitmap;", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReceiveView.kt */
final class ReceiveView$bindDataObservers$3<T> implements Observer<Bitmap> {
    final /* synthetic */ ReceiveView this$0;

    ReceiveView$bindDataObservers$3(ReceiveView receiveView) {
        this.this$0 = receiveView;
    }

    public final void onChanged(@Nullable Bitmap bitmap) {
        String str = "qrCodeImage";
        if (bitmap != null) {
            ((ImageView) this.this$0._$_findCachedViewById(C1018R.C1021id.qrCodeImage)).setImageBitmap(bitmap);
            ImageView imageView = (ImageView) this.this$0._$_findCachedViewById(C1018R.C1021id.qrCodeImage);
            Intrinsics.checkExpressionValueIsNotNull(imageView, str);
            String str2 = (String) ((ReceivePresenter) this.this$0.getPresenter()).getDisplayedAddress().getValue();
            if (str2 == null) {
                str2 = "";
            }
            imageView.setContentDescription(str2);
            ImageView imageView2 = (ImageView) this.this$0._$_findCachedViewById(C1018R.C1021id.qrCodeImage);
            Intrinsics.checkExpressionValueIsNotNull(imageView2, str);
            imageView2.setVisibility(0);
            return;
        }
        ImageView imageView3 = (ImageView) this.this$0._$_findCachedViewById(C1018R.C1021id.qrCodeImage);
        Intrinsics.checkExpressionValueIsNotNull(imageView3, str);
        imageView3.setVisibility(4);
    }
}
