package com.bitcoin.mwallet.app.flows.onboarding.createuser;

import android.view.View;
import android.widget.TextView;
import androidx.lifecycle.Observer;
import com.bitcoin.mwallet.C1018R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(mo37403bv = {1, 0, 3}, mo37404d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, mo37405d2 = {"<anonymous>", "", "shouldDisplay", "", "kotlin.jvm.PlatformType", "onChanged", "(Ljava/lang/Boolean;)V"}, mo37406k = 3, mo37407mv = {1, 1, 15})
/* compiled from: CreateUserView.kt */
final class CreateUserView$bindDataObservers$3<T> implements Observer<Boolean> {
    final /* synthetic */ CreateUserView this$0;

    CreateUserView$bindDataObservers$3(CreateUserView createUserView) {
        this.this$0 = createUserView;
    }

    public final void onChanged(Boolean bool) {
        Intrinsics.checkExpressionValueIsNotNull(bool, "shouldDisplay");
        if (bool.booleanValue()) {
            View view = this.this$0.getView();
            if (view != null) {
                TextView textView = (TextView) view.findViewById(C1018R.C1021id.loadingMessage);
                if (textView != null) {
                    textView.setText(this.this$0.getText(C1018R.string.createuser_search_wallets_zion));
                }
            }
        }
    }
}
