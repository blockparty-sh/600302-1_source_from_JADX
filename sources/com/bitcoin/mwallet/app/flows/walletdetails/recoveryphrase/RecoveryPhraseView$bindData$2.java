package com.bitcoin.mwallet.app.flows.walletdetails.recoveryphrase;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import androidx.cardview.widget.CardView;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, mo37405d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/widget/CompoundButton;", "kotlin.jvm.PlatformType", "isChecked", "", "onCheckedChanged"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: RecoveryPhraseView.kt */
final class RecoveryPhraseView$bindData$2 implements OnCheckedChangeListener {
    final /* synthetic */ RecoveryPhraseView this$0;

    RecoveryPhraseView$bindData$2(RecoveryPhraseView recoveryPhraseView) {
        this.this$0 = recoveryPhraseView;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            LinearLayout linearLayout = (LinearLayout) this.this$0._$_findCachedViewById(C1018R.C1021id.recoveryQrLayout);
            Intrinsics.checkExpressionValueIsNotNull(linearLayout, "recoveryQrLayout");
            linearLayout.setVisibility(0);
            CardView cardView = (CardView) this.this$0._$_findCachedViewById(C1018R.C1021id.recoveryTextLayout);
            Intrinsics.checkExpressionValueIsNotNull(cardView, "recoveryTextLayout");
            cardView.setVisibility(8);
        }
    }
}
