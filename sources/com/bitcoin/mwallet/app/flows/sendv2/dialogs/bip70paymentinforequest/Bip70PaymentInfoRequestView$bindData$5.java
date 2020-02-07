package com.bitcoin.mwallet.app.flows.sendv2.dialogs.bip70paymentinforequest;

import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import com.ncorti.slidetoact.SlideToActView;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u000120\u0010\u0002\u001a,\u0012\u0004\u0012\u00020\u0004 \u0006*\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00050\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005H\n¢\u0006\u0002\b\u0007"}, mo37405d2 = {"<anonymous>", "", "errorList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "kotlin.jvm.PlatformType", "onChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: Bip70PaymentInfoRequestView.kt */
final class Bip70PaymentInfoRequestView$bindData$5<T> implements Observer<ArrayList<Integer>> {
    final /* synthetic */ Bip70PaymentInfoRequestView this$0;

    Bip70PaymentInfoRequestView$bindData$5(Bip70PaymentInfoRequestView bip70PaymentInfoRequestView) {
        this.this$0 = bip70PaymentInfoRequestView;
    }

    public final void onChanged(ArrayList<Integer> arrayList) {
        Intrinsics.checkExpressionValueIsNotNull(arrayList, "errorList");
        if (!arrayList.isEmpty()) {
            View view = this.this$0.getView();
            if (view != null) {
                TextView textView = (TextView) view.findViewById(C1018R.C1021id.errorMessage);
                if (textView != null) {
                    textView.setText(this.this$0.getString(((Number) CollectionsKt.first((List) arrayList)).intValue()));
                }
            }
            View view2 = this.this$0.getView();
            if (view2 != null) {
                SlideToActView slideToActView = (SlideToActView) view2.findViewById(C1018R.C1021id.sendButton);
                if (slideToActView != null) {
                    slideToActView.setInnerColor(ContextCompat.getColor(this.this$0.requireContext(), C1018R.C1019color.lightGray));
                }
            }
            View view3 = this.this$0.getView();
            if (view3 != null) {
                SlideToActView slideToActView2 = (SlideToActView) view3.findViewById(C1018R.C1021id.sendButton);
                if (slideToActView2 != null) {
                    slideToActView2.setEnabled(false);
                    return;
                }
                return;
            }
            return;
        }
        View view4 = this.this$0.getView();
        if (view4 != null) {
            TextView textView2 = (TextView) view4.findViewById(C1018R.C1021id.errorMessage);
            if (textView2 != null) {
                textView2.setText("");
            }
        }
        View view5 = this.this$0.getView();
        if (view5 != null) {
            SlideToActView slideToActView3 = (SlideToActView) view5.findViewById(C1018R.C1021id.sendButton);
            if (slideToActView3 != null) {
                slideToActView3.setEnabled(true);
            }
        }
        View view6 = this.this$0.getView();
        if (view6 != null) {
            SlideToActView slideToActView4 = (SlideToActView) view6.findViewById(C1018R.C1021id.sendButton);
            if (slideToActView4 != null) {
                slideToActView4.setInnerColor(ContextCompat.getColor(this.this$0.requireContext(), C1018R.C1019color.bchGreen));
            }
        }
    }
}
