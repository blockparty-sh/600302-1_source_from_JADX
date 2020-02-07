package com.bitcoin.mwallet.app.flows.sendv2.review;

import android.view.View;
import android.widget.Toast;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.bitcoin.mwallet.app.android.Event;
import com.ncorti.slidetoact.SlideToActView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0005"}, mo37405d2 = {"<anonymous>", "", "error", "Lcom/bitcoin/mwallet/app/android/Event;", "", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: ReviewView.kt */
final class ReviewView$bindObservers$5<T> implements Observer<Event<? extends String>> {
    final /* synthetic */ ReviewView this$0;

    ReviewView$bindObservers$5(ReviewView reviewView) {
        this.this$0 = reviewView;
    }

    public final void onChanged(@Nullable Event<String> event) {
        Boolean valueOf = event != null ? Boolean.valueOf(event.getHasBeenHandled()) : null;
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        if (!valueOf.booleanValue()) {
            View view = this.this$0.getView();
            if (view != null) {
                SlideToActView slideToActView = (SlideToActView) view.findViewById(C1018R.C1021id.sendButton);
                if (slideToActView != null) {
                    slideToActView.resetSlider();
                }
            }
            Toast.makeText(this.this$0.getContext(), (CharSequence) event.getContentIfNotHandled(), 0).show();
        }
    }
}
