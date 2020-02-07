package com.bitcoin.mwallet.app.components.contactcreate.view;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, mo37405d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Boolean;)V"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: AddContactView.kt */
final class AddContactView$onCreateView$4<T> implements Observer<Boolean> {
    final /* synthetic */ AddContactView this$0;

    AddContactView$onCreateView$4(AddContactView addContactView) {
        this.this$0 = addContactView;
    }

    public final void onChanged(Boolean bool) {
        View view = this.this$0.getView();
        RadioButton radioButton = null;
        RadioGroup radioGroup = view != null ? (RadioGroup) view.findViewById(C1018R.C1021id.coinSelectionGroup) : null;
        if (radioGroup != null) {
            Intrinsics.checkExpressionValueIsNotNull(bool, "it");
            radioGroup.setVisibility(bool.booleanValue() ? 0 : 8);
        }
        if (!bool.booleanValue()) {
            View view2 = this.this$0.getView();
            RadioButton radioButton2 = view2 != null ? (RadioButton) view2.findViewById(C1018R.C1021id.bitcoinCashFilterButton) : null;
            View view3 = this.this$0.getView();
            RadioButton radioButton3 = view3 != null ? (RadioButton) view3.findViewById(C1018R.C1021id.bitcoinFilterButton) : null;
            View view4 = this.this$0.getView();
            if (view4 != null) {
                radioButton = (RadioButton) view4.findViewById(C1018R.C1021id.slpFilterButton);
            }
            if (radioButton2 != null) {
                radioButton2.setChecked(false);
            }
            if (radioButton3 != null) {
                radioButton3.setChecked(false);
            }
            if (radioButton != null) {
                radioButton.setChecked(false);
            }
        }
    }
}
