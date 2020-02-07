package com.bitcoin.mwallet.app.components.contactcreate.view;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, mo37405d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Boolean;)V"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: AddContactView.kt */
final class AddContactView$onCreateView$2<T> implements Observer<Boolean> {
    final /* synthetic */ AddContactView this$0;

    AddContactView$onCreateView$2(AddContactView addContactView) {
        this.this$0 = addContactView;
    }

    public final void onChanged(Boolean bool) {
        View view = this.this$0.getView();
        Button button = null;
        ImageView imageView = view != null ? (ImageView) view.findViewById(C1018R.C1021id.rawInputValidImageView) : null;
        View view2 = this.this$0.getView();
        if (view2 != null) {
            button = (Button) view2.findViewById(C1018R.C1021id.addButton);
        }
        Intrinsics.checkExpressionValueIsNotNull(bool, "it");
        if (bool.booleanValue()) {
            if (button != null) {
                button.setEnabled(true);
            }
            if (imageView != null) {
                imageView.setVisibility(0);
            }
            if (imageView != null) {
                imageView.setImageResource(C1018R.C1020drawable.ic_tick);
                return;
            }
            return;
        }
        if (button != null) {
            button.setEnabled(false);
        }
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        if (imageView != null) {
            imageView.setImageResource(C1018R.C1020drawable.ic_invalid_address);
        }
    }
}
